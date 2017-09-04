package controllers;
import java.io.*;
import java.net.*;
import java.util.*;

import classes.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

/**
 * 
 * @author jaehyeon
 *
 */
public class GameSetController implements Initializable {
	@FXML private AnchorPane stage;
	@FXML private Button buttonTurnPass;
	@FXML private HBox status;
	@FXML private Button buttonConfirm;
	
	private ServerSocket server;
	private Socket socket;
	private List<Unit> units;
	private String playerId;
	private String otherPlayerId;
	private int playerNumber;
	private int startPlayerNumber;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private Socket socketServer;
	private ObjectInputStream oisServer;
	private ObjectOutputStream oosServer;
	
	Board board = new Board(32, 24);
	
	private List<Unit> confirmedUnits = FXCollections.observableArrayList();
	
	private VBox vboxWaiting;
	public GameSetController(
			Socket socketServer,
			ObjectInputStream oisServer,
			ObjectOutputStream oosServer,
			int playerNumber, 
			int startPlayerNumber, 
			PlayerInfo playerInfo,
			String ipAddress
			){
		this.socketServer = socketServer;
		this.oisServer = oisServer;
		this.oosServer = oosServer;
		
		this.playerNumber = playerNumber;
		this.startPlayerNumber = startPlayerNumber;
		this.playerId = playerInfo.getId();
		this.units = playerInfo.getCurrentUnits();
		
		// 상대방의 연결을 대기하는 스레드
		Thread connectionThread = new Thread(()->{
			Platform.runLater(()->{				
				Label labelWaiting;
				labelWaiting = new Label();
				labelWaiting.setStyle(
						"-fx-font-size: 50;"
						+ "-fx-text-fill: linear-gradient(to bottom right, blue, skyblue);"
						+ "-fx-effect: dropshadow(gaussian, white, 2, 1, 0, 0);"
						);
				labelWaiting.setLayoutX(350);
				labelWaiting.setLayoutY(330);
				labelWaiting.setOpacity(1);
				labelWaiting.setText("연결 대기 중...");

				vboxWaiting = new VBox();
				vboxWaiting.getChildren().add(labelWaiting);
				vboxWaiting.setLayoutX(0);
				vboxWaiting.setLayoutY(0);
				vboxWaiting.setPrefWidth(1024);
				vboxWaiting.setPrefHeight(840);
				vboxWaiting.setOpacity(1);
				vboxWaiting.setAlignment(Pos.CENTER);
				vboxWaiting.setStyle(
						"-fx-background-color: linear-gradient(to bottom right, skyblue, darkred);"
						);
				stage.getChildren().add(vboxWaiting);
			});

			do {
				try {
					if(playerNumber == 1) { // 플레이어 번호가 1일 경우 P2P 통신의 호스트 역할
						server = new ServerSocket(10010);
						socket = server.accept();
					} else { // 플레이어 번호가 2일 경우 P2P 통신의 게스트로 입장
						socket = new Socket(ipAddress, 10010);
					}					

					oos = new ObjectOutputStream(socket.getOutputStream());	
					oos.flush();
					ois = new ObjectInputStream(socket.getInputStream());
					
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} while(socket == null); // 연결이 완료될 때까지 접속 시도
			
			
			try {
				// 연결이 완료되면 먼저 서로 ID를 주고 받는다.
				oos.writeUTF(playerId);
				oos.flush();
				otherPlayerId = ois.readUTF();
				
				// ID 레이블에 상대의 ID 표시
				Platform.runLater(()->{
					labelOtherName.setText("상대 ID: " + otherPlayerId);
					stage.getChildren().remove(vboxWaiting);
				});
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		connectionThread.setDaemon(true);
		connectionThread.start();
		
	}
	
	int count = 0;
	private Label labelOtherName = new Label();
	private VBox vboxBlind = new VBox();
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		// 격자 그리기
		for(int i=0; i<32; i++){
			for(int j=0; j<24; j++){
				Rectangle rect = new Rectangle(32, 32);
				rect.setLayoutX(i*32);
				rect.setLayoutY(j*32);
				rect.setStroke(Color.GRAY);
				rect.setOpacity(0.3);
				stage.getChildren().add(0, rect);
			}
		}
		
		// 가림막
		vboxBlind.setPrefWidth(512);
		vboxBlind.setPrefHeight(768);
		vboxBlind.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		vboxBlind.setStyle("-fx-opacity: 0.8");
		if(playerNumber == 1){
			vboxBlind.setLayoutX(512);
		}
		
		labelOtherName.setStyle(""
				+ "-fx-font-size: 30; "
				+ "-fx-text-fill: blue;"
				+ "-fx-effect: dropshadow(gaussian, white, 1.3, 1, 0, 0);"
				);
		labelOtherName.setPrefWidth(512);
		labelOtherName.setAlignment(Pos.CENTER);
		
		VBox.setMargin(labelOtherName, new Insets(400, 0, 300, 0));
		vboxBlind.getChildren().add(labelOtherName);
		stage.getChildren().add(vboxBlind);
		
		// 유닛 드래그 이동
		units.forEach(e->{
			PopupImageView piv = new PopupImageView(stage);
			
			piv.setUserData(e);
			piv.setImage(new Image(getClass().getResource("/images/" + e.getJob() + ".png").toString()));
			piv.addReleasedListener(new PopupImageView.ReleasedListener() {
				@Override
				public void ImageReleased(ImageView source, double x, double y) {
					int pX, pY;
					pX = (int)x / 32;
					pY = (int)y / 32;
					
					
					
					// 가려진 사각 영역은 유닛 배치 불가.
					if(vboxBlind.getBoundsInParent().contains(x, y)) return;
					if(pX < 0 || pX > 31) return;
					if(pY < 0 || pY > 23) return;
					if(board.getAt(pX, pY) != null)	return;
					
					Unit selectedUnit = (Unit)piv.getUserData();
					confirmedUnits.remove(selectedUnit);
					units.remove(selectedUnit);
					confirmedUnits.add(selectedUnit);
					
					board.setAt(pX, pY, selectedUnit);
					System.out.println(board.getAt(pX, pY).toString());
					selectedUnit.setPos(pX, pY);
					piv.setLayoutX(pX * 32);
					piv.setLayoutY(pY * 32);
					
					// 유닛 배치 완료 시 준비 버튼 활성화
					if(units.isEmpty()){
						buttonConfirm.setDisable(false);
					}
				}
			});
			piv.setLayoutX(count++ * 48 + 8);
			piv.setLayoutY(800);
			
			stage.getChildren().add(piv);
		});
		
		// 준비 버튼 클릭 시
		buttonConfirm.setOnAction(ev->{
			try {
				// 준비 버튼 비활성화
				buttonConfirm.setDisable(true);
				
				oos.writeUTF("confirm"); // 상대에게 확정 메시지 전송
				oos.flush();
				
				Thread t = new Thread(()->{ // 확정 시 상대에게서 확정 메시지가 들어올 때까지 대기
					while(true){
						try {
							String msg = ois.readUTF();
							if(msg.equals("confirm")){ // 확정 메시지를 받을 경우 게임 시작화면으로 이동
								showGameScene();
								break;
							} 
						} catch(Exception ex){
							ex.printStackTrace();
						}
					}
				});
				t.setDaemon(true);
				t.start();
			} catch(Exception ex){
				ex.printStackTrace();
			}
		});
		
		Platform.runLater(()->{ // 상단 상태바 제거
			((BorderPane)stage.getScene().getRoot()).setTop(null);
		});
		
		
		
	}

	/******************************
	 * 게임 화면을 보여주는 메소드
	 ******************************/
	private void showGameScene() {
		Platform.runLater(()->{			
			try { 
				Stage currentStage = (Stage)stage.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/game.fxml"));
				loader.setController(
						new GameController(
								server,
								socketServer, oisServer, oosServer, 
								playerNumber, 
								startPlayerNumber,
								playerId, 
								socket, ois, oos, 
								confirmedUnits
								)
						);
				currentStage.setScene(new Scene(loader.load()));
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		});
	}
}
