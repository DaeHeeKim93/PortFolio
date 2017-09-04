package controllers;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.*;

import classes.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class LobbyController implements Initializable {
	@FXML private ListView<Room> listViewRoom;
	@FXML private Button buttonEnter, buttonCreate, buttonProfile;
	@FXML private GridPane titleBar;
	
	int idNumber;
	Socket sock;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	ArrayList<Room> roomList;
	
	Thread receiveThread;
	private PlayerInfo playerInfo = null;
	
	public LobbyController(int idNumber,Socket sock,ObjectInputStream ois,ObjectOutputStream oos)
	{
		this.idNumber = idNumber;
		this.sock = sock;
		this.ois = ois;
		this.oos = oos;
		this.roomList = new ArrayList<Room>();
	}
	
	Window window;
	double dx, dy;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonEnter.setOnAction(ev->buttonEnterClicked(ev));
		buttonCreate.setOnAction(ev->buttonCreateClicked(ev));
		buttonProfile.setOnAction(ev->buttonProfileClicked(ev));
			
		/***********************
		 * 상태바 드래그를 이용한 창 이동
		 ***********************/
		
		titleBar.setOnMousePressed(ev->{
			window = titleBar.getScene().getWindow();
			dx = window.getX() - ev.getScreenX();
			dy = window.getY() - ev.getScreenY();
		});
		
		titleBar.setOnMouseDragged(ev->{
			window.setX(ev.getScreenX() + dx);
			window.setY(ev.getScreenY() + dy);
		});
		
		// 리스트 뷰 업데이트 방법 설정
		listViewRoom.setCellFactory(e->new ListCell<Room>(){
			@Override
			protected void updateItem(Room item, boolean empty) {
				super.updateItem(item, empty);
				
				if(empty) {
					this.setText(null);
				} else {
					this.setText(item.getTitle());
				}
			}
		});
		
		// 방 목록 새로고침 스레드
		receiveThread = new Thread(()->{
			while(true){
				try {
					Thread.sleep(100);
					String instructor = ois.readUTF();
					if(instructor.equals("refresh")){ 
						// 서버로부터 refresh 라는 명령어가 들어오면 ArrayList<Room> 형태의 객체를 읽어서 리스트 갱신
						roomList =(ArrayList<Room>)ois.readObject();
						Platform.runLater(()->{
							listViewRoom.getItems().clear();
							for(Room r : roomList){
								listViewRoom.getItems().add(r);
							}
						});
					} else if(instructor.equals("info")) { 
						// info 명령어가 들어오면 플레이어 ID와 유닛데이터를 받아서 플레이어 유닛 정보 갱신
						playerInfo = new PlayerInfo();
						
						playerInfo.setId(ois.readUTF());
						List<Unit> list = playerInfo.getCurrentUnits();
						
						
						// 서버로부터 받은 유닛 정보를 통해 플레이어 유닛 생성
						ArrayList<UnitData> temp = (ArrayList<UnitData>)ois.readObject();						
						for(UnitData u : temp){
							String job = null;
							switch(u.getClassId()) {
							case 1: job = "assault"; break;
							case 2: job = "shielder"; break;
							case 3: job = "sniper"; break;
							case 4: job = "medic"; break;
							}
							System.out.println(Thread.currentThread().getId() + ": " + job);
							
							list.add(
									new Unit(
											"", 
											job, 
											u.getHp(), 
											u.getStr(), 
											u.getAgility(), 
											0, 0, 
											u.getAttackRange(),
											u.getMoveRange(), 
											u.getAttackCount()
											)
									);
							
							oos.reset();
							oos.writeInt(4);
							oos.flush();
						}
						
					}
				} catch(EOFException ex) {
				} catch(InterruptedException | IOException ex) {
					break;
				} catch(Exception ex) {
				} 
			}
		});
		receiveThread.setDaemon(true);
		receiveThread.start();
		
		try {
			oos.reset();
			oos.writeInt(3); // 서버로 명령 3을 보내 플레이어 ID 및 유닛 정보를 요청
			oos.flush();
		} catch(Exception ex) {
		}
		
		Platform.runLater(()->{ // 창이 종료될 경우 소켓 폐기
			listViewRoom.getScene().getWindow().setOnCloseRequest(ev->{
				try {
					if(!sock.isClosed()) {
						sock.close();
					}
				} catch (Exception ex) {
				}
			});
		});
	}
	
	/**
	 * 방 생성 창을 띄우는 메소드
	 * @return 입력된 방 제목을 반환한다. 취소시 null
	 */
	private String showCreateRoom(){ 
		Stage stage = new Stage(StageStyle.TRANSPARENT);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/create_room.fxml"));
		CreateRoomController ctrl = new CreateRoomController();
		try {
			loader.setController(ctrl);
			
			stage.setScene(new Scene(loader.load()));
			stage.setOpacity(0.9);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(listViewRoom.getScene().getWindow());
			stage.showAndWait();
		} catch(Exception ex) {
		}
		
		return ctrl.getResult();
	}
	
	// 입장 버튼 클릭
	private void buttonEnterClicked(ActionEvent event){
		Room selectedRoom = listViewRoom.getSelectionModel().getSelectedItem();	
		if(selectedRoom == null) return; // 선택된 방이 없을 경우 무시
		
		enterRoom(selectedRoom); // 선택된 방으로 입장
	}
	
	// 생성 버튼 클릭
	private void buttonCreateClicked(ActionEvent event){
		String title = showCreateRoom(); // 방 생성 창을 띄우고 입력을 마칠 때 까지 대기
		
		if(title == null) { // 방 생성 취소 시 무시
			return;
		}
		
		try {
			oos.reset();
			oos.writeInt(1); // 서버로 명령 1을 보내 방 생성을 알림
			
			oos.flush();
			oos.reset();
			
			// 방 생성
			createRoom(title); 
		} catch (IOException e) {

		}
		
	}
	
	// 내 정보 버튼 클릭
 	private void buttonProfileClicked(ActionEvent event){
 		if(playerInfo == null) { // 서버로부터 플레이어 정보를 받지 못했을 경우 무시
 			return;
 		}
 		
 		try { 			
			Stage stage = new Stage(StageStyle.TRANSPARENT);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/player_info.fxml"));
			
			stage.setScene(new Scene(loader.load()));
			stage.setOpacity(0.9);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(listViewRoom.getScene().getWindow());
			
			PlayerInfoController ctrl = (PlayerInfoController)loader.getController();
			
			// 내 정보 화면의 이름과 유닛리스트 갱신
			ctrl.setName(playerInfo.getId());
			ctrl.setUnitList(playerInfo.getCurrentUnits());
			
			stage.showAndWait();
 		} catch(Exception ex) {
 		}
	}
	
	private void enterRoom(Room room){
		String hostIp = room.getIpList().get(0); // 방장의 IP 주소를 받는다.
		
		receiveThread.interrupt(); // 수신 스레드 종료
		
		try {
			oos.reset();
			oos.writeInt(2); // 서버에 명령 2를 보내서 방 접속을 알림
			oos.writeObject(room); // 서버에 접속할 방 정보를 보냄
			oos.flush();
		} catch(Exception ex) {
		}
		
		showWaiting(hostIp, 2, playerInfo); // 방에 접속하여 연결
	}
	
	private void createRoom(String title){
		try{
			oos.flush();
			oos.reset();
			
			Room r = new Room(title);
			
			//방생성
			oos.writeObject(r); // 서버로 방 정보를 보냄
			oos.flush();
			oos.reset();
			//서버에 전송
			
			System.out.println(r.getIpList().get(0));
			
			receiveThread.interrupt(); // 수신 스레드 종료
		
			showWaiting("127.0.0.1", 1, playerInfo); // 방에 접속하여 연결 대기
		}
		catch(Exception ex) {
		}
	}
	
	private void showWaiting(String ipAddress, int playerNumber, PlayerInfo playerInfo){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/game.fxml"));
			loader.setController(new GameSetController(sock, ois, oos, playerNumber, 1, playerInfo, ipAddress));
			
			Stage primaryStage = (Stage)listViewRoom.getScene().getWindow();
			primaryStage.setHeight(840);
			primaryStage.setScene(new Scene(loader.load()));
			
			primaryStage.show();
		} catch(Exception ex) {
		}
	}
}
