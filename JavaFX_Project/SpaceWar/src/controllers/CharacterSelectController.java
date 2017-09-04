package controllers;

import java.io.*;
import java.net.*;
import java.util.*;

import classes.*;
import classes.PopupImageView.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;



public class CharacterSelectController implements Initializable {
	@FXML private AnchorPane top;
	@FXML private GridPane titleBar; 
	@FXML private ListView<UnitDescriptor> listView;
	@FXML private TextArea textAssault, textSniper, textShielder, textMedic;
	@FXML private PopupImageView pivAssault, pivSniper, pivShielder, pivMedic;
	@FXML private Button buttonConfirm;
	
	private final static int PICK_COUNT = 6; // 장바구니 선택할 유닛 개수
	
	Ability[] abilityList;
	int idNumber;
	Socket sock;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	ArrayList<Ability> confirmedList;
	
	
	public CharacterSelectController(int idNumber, Socket sock, ObjectInputStream ois, ObjectOutputStream oos)
	{
		this.idNumber=idNumber;
		this.abilityList=new Ability[4];
		this.sock=sock;
		this.ois=ois;
		this.oos=oos;
	
		this.confirmedList=new ArrayList<Ability>();
	}
	
	// 유닛 정보를 리스트 뷰에 삽입하기 위한 클래스 
	class UnitDescriptor {
		String name;
		ImageView image;
		
		public UnitDescriptor(String name, ImageView imageView) {
			this.name = name;
			this.image = imageView;
		}
		
		public String getName(){
			return name;
		}
		
		public ImageView getImageView() {
			return image;
		}
	}
	
	Window window;
	double dx, dy;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			oos.writeInt(0); // 서버로 0을 보냄
			oos.reset();
			abilityList=(Ability[])ois.readObject(); // 서버로부터 유닛 정보를 받음
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// 팝업 이미지 뷰 초기화
        pivAssault.setImage(new Image(getClass().getResource("/images/assault.png").toString()));
		pivAssault.setUserData("Assault");
		
		pivSniper.setImage(new Image(getClass().getResource("/images/sniper.png").toString()));
		pivSniper.setUserData("Sniper");
		
		pivShielder.setImage(new Image(getClass().getResource("/images/shielder.png").toString()));
		pivShielder.setUserData("Shielder");
		
		pivMedic.setImage(new Image(getClass().getResource("/images/medic.png").toString()));
		pivMedic.setUserData("Medic");
		
		// 리스트 뷰 사이즈 조절 및 항목 업데이트 메소드 오버라이드
		// 항목 업데이트시 유닛의 이미지와 직업 명이 리스트에 추가
		listView.setFixedCellSize(83);
		listView.setCellFactory(e->new ListCell<UnitDescriptor>(){
			@Override
			protected void updateItem(UnitDescriptor item, boolean empty) {
				super.updateItem(item, empty);
				
				if(empty){
					this.setText(null);
					this.setGraphic(null);
				} else {
					this.setOnMouseClicked(ev->{
						if(ev.getClickCount() == 2){
							UnitDescriptor ud = listView.getSelectionModel().getSelectedItem();
							if(ud != null){
								listView.getItems().remove(ud);
							}
						}
					});
					this.setText(item.getName());
					this.setGraphic(item.getImageView());
				}
			}
		});
		
		// 팝업 이미지 뷰에 리스너 부착 및 리스트에 추가
		Vector<PopupImageView> pivs = new Vector<>();
		pivs.addAll(FXCollections.observableArrayList(pivAssault, pivSniper, pivShielder, pivMedic));
		pivs.forEach(e->{
			e.setPane(top);
			e.addReleasedListener(new ReleasedListener() {
				@Override
				public void ImageReleased(ImageView source, double x, double y) {
					// 이미지가 리스트뷰 영역으로 드래그 되었을 경우, 리스트에 최대 6개 항목까지 유닛 추가
					if(listView.getBoundsInParent().contains(x, y) && (listView.getItems().size() < PICK_COUNT)){
						listView.getItems().add(new UnitDescriptor((String)e.getUserData(), new ImageView(source.getImage())));
					} 
				}
			});
		});
		
		// 확정 버튼 클릭 시
		buttonConfirm.setOnAction(ev->{
			// 유닛을 정확히 6기 선택하지 않았으면 무시
			if(listView.getItems().size() != PICK_COUNT){
				return;
			}

			ObservableList<UnitDescriptor> list = listView.getItems(); // 장바구니 리스트에 선택된 유닛 목록
			
		    while(true)
		    { // 장바구니에 선택된 유닛 리스트를 확정 리스트로 복사 
			   if(list.isEmpty())
				   break;
			   
			   if(list.get(0).getName().equals("Assault")) {
				   abilityList[0].setName(list.get(0).getName());
				   abilityList[0].setid_num(idNumber);
				   confirmedList.add(abilityList[0]);
			   } else if(list.get(0).getName().equals("Shielder")) {
				   abilityList[1].setName(list.get(0).getName());
				   abilityList[1].setid_num(idNumber);
				   confirmedList.add(abilityList[1]);
			   } else if(list.get(0).getName().equals("Sniper")) {
				   abilityList[2].setName(list.get(0).getName());
				   abilityList[2].setid_num(idNumber);
				   confirmedList.add(abilityList[2]);
			   } else if(list.get(0).getName().equals("Medic")) {
				   abilityList[3].setName(list.get(0).getName());
				   abilityList[3].setid_num(idNumber);
				   confirmedList.add(abilityList[3]);
			   }
			 
			   list.remove(0);
		   }
		   
		   try {
			   oos.reset();
			   oos.writeObject(confirmedList); // 확정된 리스트를 서버로 전송
			   oos.reset();
		   } catch (Exception e1) {
			   e1.printStackTrace();
		   }
		   
		   // 로비 화면으로 이동
		   try { 
				Stage stage = (Stage)titleBar.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/lobby.fxml"));
				loader.setController(new LobbyController(idNumber,sock,ois,oos));
				
				Scene scene = new Scene((Parent)loader.load());
				
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
		   } catch(Exception ex){
				ex.printStackTrace();
		   }
		});
		
		// 창이 종료될 경우 소켓을 폐기하도록 설정
		Platform.runLater(()->{
			((Stage)titleBar.getScene().getWindow()).setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					try {
    	   				sock.close();
       				} catch (IOException e) {
       					e.printStackTrace();
       				}
				}
			});
		});
		
	}

}
