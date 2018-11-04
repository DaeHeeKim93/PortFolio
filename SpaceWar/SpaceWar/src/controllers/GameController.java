package controllers;
import java.io.*;
import java.net.*;
import java.util.*;

import classes.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.util.*;

/**
 * 
 * @author jaehyeon
 *
 */
public class GameController implements Initializable {
	@FXML private AnchorPane stage;
	@FXML private Button buttonTurnPass;
	@FXML private HBox status;
	
	// 클라이언트의 상태
	private enum Status {
		WAIT_ACTION, // 행동 대기
		WAIT_PLAYER, // 다른 플레이어 행동 대기
		SELECT_MOVE, // 이동 범위 선택
		SELECT_ATTACK, // 공격 대상 선택
		BUSY // 초기화 및 연산 작업에 의한 대기
	}
	
	private ServerSocket server;
	private Socket socketServer;
	private ObjectOutputStream oosServer;
	private ObjectInputStream oisServer;
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private List<Unit> units;
	private String playerId;
	private int playerNumber; // 이 클라이언트의 플레이어 번호
	private int currentTurnPlayerNumber = 1; // 현재 차례인 플레이어 번호
	
	private Queue<Action> actionQueue = new LinkedList<Action>();
	
	private Board board;
	private Player[] players;
	
	private Status currentStatus; // 클라이언트의 현재 상태

	private Thread turnChecker;
	private Thread gameThread;

	private Unit selectedUnit;
	private ArrayList<Rectangle> panels; 

	/*********************************
	 * 턴 표시 레이블
	 *********************************/
	private VBox vboxTurn;
	private Label labelTurn;
	
	Label labelTimer;
	Thread timerThread;
	
	/*********************************
	 * 창 이동을 위한 변수
	 *********************************/
	private Window window;
	double dx, dy;
	
	/**
	 * 
	 * @param playerNumber 플레이어 번호(호스트: 1, 게스트: 2)
	 * @param startPlayerNumber 먼저 시작할 플레이어 번호
	 */
	public GameController(
			ServerSocket server,
			Socket socketServer,
			ObjectInputStream oisServer,
			ObjectOutputStream oosServer,
			int playerNumber, 
			int startPlayerNumber, 
			String playerId,
			Socket socket, 
			ObjectInputStream ois,
			ObjectOutputStream oos,
			List<Unit> units
			){
		this.server = server;
		this.socketServer = socketServer;
		this.oisServer = oisServer;
		this.oosServer = oosServer;
		
		this.socket = socket;
		this.ois = ois;
		this.oos = oos;
		this.units = units;
		this.playerId = playerId;
		this.playerNumber = playerNumber;
		this.currentTurnPlayerNumber = startPlayerNumber;
		
		System.out.println(socket.getInetAddress().getHostAddress());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/***********************
		 * 상태바 드래그를 이용한 창 이동
		 ***********************/
		status.setOnMousePressed(ev->{
			window = status.getScene().getWindow();
			dx = window.getX() - ev.getScreenX();
			dy = window.getY() - ev.getScreenY();
		});
		
		status.setOnMouseDragged(ev->{
			window.setX(ev.getScreenX() + dx);
			window.setY(ev.getScreenY() + dy);
		});
		
		// 초기화 중이므로 작업 불가
		currentStatus = Status.BUSY;
		
		// 보드 생성
		board = new Board(32, 24);
		
		// 격자 선을 그린다.
		for(int i=0; i<board.getRow(); i++){
			for(int j=0; j<board.getColumn(); j++){
				Rectangle rect = new Rectangle(32, 32);
				rect.setLayoutX(i*32);
				rect.setLayoutY(j*32);
				rect.setStroke(Color.GRAY);
				rect.setOpacity(0.3);
				stage.getChildren().add(0, rect);
			}
		}
		
		
		
		// 마우스 클릭 시 이벤트
		stage.setOnMouseClicked(ev->mouseClicked(ev));
		buttonTurnPass.setOnMouseClicked(ev->{
			try {
				Platform.runLater(()->{
					stage.getChildren().removeAll(panels);
				});
				oos.writeUTF("pass");
				oos.flush();
				
				passTurn();
			} catch(Exception ex) {
			}
		});
		
		// 플레이어 및 유닛 추가
		
		/********************************
		 * 플레이어 이름은 생성자로부터 받은 이름 사용하도록 수정
		 ********************************/
		players = new Player[]{
				new Player(stage, board, "이름1", 1),
				new Player(stage, board, "이름2", 2)
		};
		
		// 차례 알림 레이블 초기화
		labelTurn = new Label();
		labelTurn.setStyle(
				"-fx-font-size: 50;"
				+ "-fx-text-fill: linear-gradient(to bottom right, blue, skyblue);"
				+ "-fx-effect: dropshadow(gaussian, white, 2, 1, 0, 0);"
				);
		labelTurn.setLayoutX(350);
		labelTurn.setLayoutY(330);
		labelTurn.setOpacity(1);
		labelTurn.setText("READY");

		vboxTurn = new VBox();
		vboxTurn.getChildren().add(labelTurn);
		vboxTurn.setLayoutX(0);
		vboxTurn.setLayoutY(330);
		vboxTurn.setPrefWidth(1024);
		vboxTurn.setPrefHeight(70);
		vboxTurn.setAlignment(Pos.CENTER);
		vboxTurn.setStyle(
				"-fx-background-color: linear-gradient(to bottom right, skyblue, darkred);"
				+ "-fx-border-width: 1 0 1 0;"
				+ "-fx-border-color: gray"
				);
		vboxTurn.setOpacity(0);
		stage.getChildren().add(vboxTurn);
			
		// 내 유닛 목록 상대방에게 전송
		try {
			oos.reset();
			oos.writeUTF("init");
			oos.writeInt(units.size());
			oos.flush();
			
			for(Unit u : units){
				oos.writeUTF(u.getName());
				oos.writeUTF(u.getJob());
				oos.writeInt(u.getHp());
				oos.writeInt(u.getPower());
				oos.writeInt(u.getArmor());
				oos.writeInt(u.getX());
				oos.writeInt(u.getY());
				oos.writeInt(u.getAtttackRange());
				oos.writeInt(u.getMoveRange());
				oos.writeInt(u.getAttackCount());
				oos.flush();
			}		
		} catch(Exception ex) {
		}
		
		// 내 턴이 끝났는지, 내 유닛이 모두 죽었는지 확인하는 스레드
		turnChecker = new Thread(()->{
			while(true){
				try {
					if(checkDefeat(players[playerNumber - 1])){ // 내 유닛이 모두 죽었는지 확인
						Thread.sleep(1000);
						
						// 소켓을 통해 상대방이 승리했음을 전달.
						oos.writeUTF("win");
						oos.flush();
						
						// 패배 표시
						showLabel("LOSE!");
						endGame();
						
						break;
					} else if(currentTurnPlayerNumber == playerNumber){
						Thread.sleep(1000);
						if(checkTurnFinished(players[playerNumber - 1])){
							oos.writeUTF("pass");
							oos.flush();
							Platform.runLater(()->{							
								passTurn();
							});
						}
					}
					Thread.sleep(1000);
				} catch(InterruptedException ex){
					break;
				} catch(Exception ex) {
				}
			}
		});
		turnChecker.setDaemon(true);
		
		
		gameThread = new Thread(()->{
			try {
				while(true) {
					String instructor = "";
					if(ois.available() > 0) {
						instructor = ois.readUTF(); // 소켓을 통해 상대로부터 문자열을 받음
					}
					
					// 문자열을 해독하여 각각 다른 동작을 수행
					if(instructor.equals("init")){ 
						// init 명령일 경우 상대로부터 상대방의 유닛들을 받아와서 플레이어 초기화 
						
						// 내 유닛 초기화
						units.forEach(u->{
							players[(playerNumber == 1) ? 0 : 1].addUnit(
									u.getName(),
									u.getJob(),
									u.getHp(),
									u.getPower(),
									u.getArmor(),
									u.getX(), u.getY(),
									u.getAtttackRange(),
									u.getMoveRange(),
									u.getAttackCount()
									);
						});
						
						// 상대 유닛 초기화
						int size = ois.readInt();
						for(int i=0; i<size; i++){
							String name = ois.readUTF();
							String job = ois.readUTF();
							int hp = ois.readInt();
							int power = ois.readInt();
							int armor = ois.readInt();
							int x = ois.readInt();
							int y = ois.readInt();
							int attackRange = ois.readInt();
							int moveRange = ois.readInt();
							int attackCount = ois.readInt();
							
							players[(playerNumber == 1) ? 1 : 0].addUnit(
									name,
									job,
									hp,
									power,
									armor,
									x, y,
									attackRange,
									moveRange,
									attackCount
									);
						}
						
						// 화면에 1.5초간 "READY" 표시
						showLabel("READY");				
						Thread.sleep(1500);
						
						// 차례를 넘기고 시작
						giveTurn(players[currentTurnPlayerNumber - 1]);
						turnChecker.start();
						
					} else if(instructor.equals("action")){
						// action 명령일 경우 상대로부터 Action 객체를 받아서 실행시킴
						Action act = (Action)ois.readObject();
						actionQueue.add(act);
						executeAction(act);
						
					} else if(instructor.equals("pass")){
						// pass 명령일 경우 턴 넘기기 실행
						passTurn();
						
					} else if(instructor.equals("win")){
						// win 명령일 경우 화면에 "WIN!" 을 표시하고 종료
						showLabel("WIN!");
						endGame();
						
					}
				}
			} catch(Exception ex) {
			}
		});
		gameThread.setDaemon(true);
		gameThread.start();
		
		// 초기화가 끝났으므로 행동 대기 상태로 전환
		currentStatus = Status.WAIT_ACTION;
	}
	
	/**
	 * 
	 * @param player 모든 유닛이 죽었는지 확인할 플레이어
	 * @return
	 */
	private boolean checkDefeat(Player player) {
		for(Unit unit : player.getUnits()){
			if(unit.getHp() > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param kind 행동의 종류
	 * @param x 기준점의 x 좌표
	 * @param y 기준점의 y 좌표
	 * @param range 범위
	 * @return 생성된 범위 패널을 반환한다
	 */
	private ArrayList<Rectangle> makePanel(Action.Kind kind, int x, int y, int range){
		ArrayList<Rectangle> list = new ArrayList<>();
		for(int cx = x - range; cx <= x + range; cx++) {
			if(cx < 0) continue;
			if(cx > 31) break;
			
			for(int cy = y - range; cy <= y + range; cy++) {
				if(cy < 0) continue;
				if(cy > 23) break;
				
				// 클릭한 위치는 패널이 생성되지 않는다.
				if(cx == x && cy == y) {
					continue;
				}
				
				// 패널이 생성되는 범위 계산
				if(((Math.abs(cx - x) + Math.abs(cy - y)) <= range)){
					Rectangle rect = new Rectangle(32, 32);
					rect.setOpacity(0.5);
					rect.setLayoutX(cx * 32);
					rect.setLayoutY(cy * 32);
					switch(kind){
					case ATTACK:
					case HEAL: // 공격 혹은 회복 일 경우
						if(board.getArray()[cx][cy] == null){ 
							// 비어있으면 LIGHTGREEN 패널 생성
							rect.setFill(Color.LIGHTGREEN);
						} else if (board.getArray()[cx][cy].getPlayer() == board.getArray()[x][y].getPlayer()) {
							if(board.getArray()[x][y].getJob() == "medic"){ 
								// 선택된 유닛이 의무병이고 대상 유닛이 아군일 경우 ORANGE 패널 생성 (회복 가능) 
								rect.setFill(Color.ORANGE);
							} else { 
								// 선택된 유닛이 의무병이 아니고 대상 유닛이 아군일 경우 RED 패널 생성(공격 불가)
								rect.setFill(Color.RED);	
							}
						} else if (board.getArray()[cx][cy].getPlayer() != board.getArray()[x][y].getPlayer()) { 
							// 대상 유닛이 적일 경우 BLUEVIOLET 패널 생성 
							rect.setFill(Color.BLUEVIOLET);
						}
						break;
					case MOVE: // 이동일 경우
						if(board.getArray()[cx][cy] == null){
							// 비어있으면 DEEPSKYBLUE 패널 생성
							rect.setFill(Color.DEEPSKYBLUE);
						} else if (board.getArray()[cx][cy].getPlayer() == board.getArray()[x][y].getPlayer()) {
							// 아군 유닛일 경우 RED 패널 생성(이동 불가)
							rect.setFill(Color.RED);
						} else if (board.getArray()[cx][cy].getPlayer() != board.getArray()[x][y].getPlayer()) {
							// 적 유닛일 경우 RED 패널 생성(이동 불가)
							rect.setFill(Color.RED);
						}
						break;
					case SKILL:
						break;
					}
					list.add(rect); // 생성된 각각의 패널들을 리스트에 추가
				}	
			}
		}
		
		return list; // 생성된 패널 리스트를 반환
	}
	
	/*************************************
	 * 차례를 표시하는 메소드
	 ************************************/
	private void showTurnLabel(int playerNumber){
		Platform.runLater(()->{
			vboxTurn.toFront();
			vboxTurn.setOpacity(1);
			
			labelTurn.setText("Player" + playerNumber + " 차례");
			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(vboxTurn.opacityProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(5000), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		});		
	}
	
	/*************************************
	 * 화면에 문자열을 표시하는 메소드
	 *************************************/
	private void showLabel(String text){
		Platform.runLater(()->{
			vboxTurn.toFront();
			vboxTurn.setOpacity(1);
			
			labelTurn.setText(text);
			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(vboxTurn.opacityProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(4000), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		});		
	}
	
	/*************************************
	 * 차례가 끝났는지 확인하는 메소드
	 ************************************/
	private boolean checkTurnFinished(Player player){
		for(Unit unit : player.getUnits()){
			if(unit.getMovable() || unit.getAttackable()){
				return false;
			}
		}
		return true;
	}
	
	/*************************************
	 * 차례 부여(공격 및 이동 가능 설정)
	 *************************************/
	private void giveTurn(Player player){
		currentTurnPlayerNumber = player.getNumber();
		showTurnLabel(currentTurnPlayerNumber);
		
		// 해당 플레이어의 살아있는 유닛들에 대하여 공격성, 이동성 부여
		for(Unit unit : player.getUnits()){
			if(unit.getHp() > 0) {
				unit.setMovable(true);
				unit.setAttackable(true);
			}
		}
		
		if(timerThread != null && timerThread.isAlive()){
			timerThread.interrupt();
		}
		
		// 타이머 레이블 생성
		Platform.runLater(()->{
			status.getChildren().remove(labelTimer);
			labelTimer = new Label("60");
			labelTimer.setPrefWidth(50);
			labelTimer.setPrefHeight(50);
			labelTimer.setAlignment(Pos.CENTER);
			labelTimer.getStyleClass().add("Timer");
			status.getChildren().add(labelTimer);
		});
		
		// 타이머 작동 스레드 생성 및 실행 (60초 다운카운트)
		timerThread = new Thread(()->{
			while(true) {
				try {
					Thread.sleep(1000);
					int currentTime = Integer.valueOf(labelTimer.getText());
					if(currentTime == 0) { // 0초 도달 시 자동으로 턴 넘김
						passTurn();
						break;
					}
					Platform.runLater(()->labelTimer.setText(String.valueOf(currentTime-1)));
					
				} catch(Exception ex) {
					break;
				}
			}
		});
		timerThread.setDaemon(true);
		timerThread.start();
		
		// 내 턴일 경우 버튼 활성화. 아닐 경우 비활성화
		if(currentTurnPlayerNumber == playerNumber) {
			buttonTurnPass.setDisable(false);
		} else {
			buttonTurnPass.setDisable(true);
		}
	}
	
	/*************************************
	 * 차례 넘기기
	 *************************************/
	private void passTurn() {
		// 턴 넘기기
		try {
			Player nextPlayer = (currentTurnPlayerNumber == 1 ? players[1] : players[0]);
			Platform.runLater(()->stage.getChildren().removeAll(panels));
			giveTurn(nextPlayer);
		} catch(Exception ex){
		}
	}
	
	/*************************************
	 * 레코드 파일을 저장하는 메소드
	 *************************************/
	private void saveRecord() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("Game Record File", FXCollections.observableArrayList(".grec"))
				);
		
		File file = fileChooser.showSaveDialog(stage.getScene().getWindow());
		
		if(file == null) {
			return;
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(actionQueue);
			oos.close();
		} catch(Exception ex){
		}
	}
	
	/*************************************
	 * 레코드 파일을 읽어오는 메소드
	 *************************************/
	private void loadRecord() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("Game Record File", FXCollections.observableArrayList("*.grec"))
				);
		
		File file = fileChooser.showOpenDialog(stage.getScene().getWindow());
		
		if(file == null) {
			return;
		}
		
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			actionQueue = (Queue<Action>)ois.readObject();
			ois.close();
		} catch(Exception ex) {
		}
		
		if(actionQueue != null && !actionQueue.isEmpty()){
			currentTurnPlayerNumber = 0;
			
			actionQueue.stream().forEach((e)->{
				System.out.println(e.toString());
			});
			
			Thread t = new Thread(()->playActionQueue(actionQueue));
			t.start();
		}
	}
	
	/*************************************
	 * Action 수신 시 해당 동작 실행
	 *************************************/
	private void executeAction(Action act) {
		Unit sourceUnit = board.getArray()[act.getSourceX()][act.getSourceY()];
		Unit targetUnit = board.getArray()[act.getTargetX()][act.getTargetY()];
		
		Action.Kind actionType = act.getActionType();
		switch(actionType) {
		case MOVE:
			sourceUnit.move(act.getTargetX(), act.getTargetY());
			break;
		case ATTACK:
			sourceUnit.attack(targetUnit);
			break;
		case HEAL:
			sourceUnit.heal(targetUnit);
			break;
		case SKILL:
			break;
		}
	}
	
	/*************************************
	 * Action Queue에 있는 Action을 꺼내 수행하는 메소드
	 *************************************/
	private void playActionQueue(Queue<Action> queue) {
		while(!queue.isEmpty()){
			executeAction(queue.poll());
			try {
				Thread.sleep(750);
			} catch(Exception ex) {}
		}
	}

	/*************************************
	 * 마우스 클릭 핸들러
	 **************************************/
	private void mouseClicked(MouseEvent event){
		int x, y;
		x = (int)Math.floor(event.getX() / 32);
		y = (int)Math.floor(event.getY() / 32);
		
		Unit clickedUnit = board.getAt(x, y);
		
		// 내 턴이 아닐 경우 무시
		if(currentTurnPlayerNumber != playerNumber){
			return;
		}
		
		switch(currentStatus){ // 현재 상태에 따라 동작 결정
		case WAIT_ACTION: // 행동 대기 상태일 경우
			
			// 클릭된 유닛이 없거나 내 팀이 아닌 유닛일 경우 무시
			if((clickedUnit == null) || (clickedUnit.getPlayer().getNumber() != playerNumber)){
				return;
			}
			
			currentStatus = Status.BUSY; // 현재 상태를 BUSY로 설정
			selectedUnit = board.getArray()[x][y];
			
			if(event.getButton() == MouseButton.PRIMARY){ // 대기 상태에서 왼쪽 버튼 클릭 -> 공격 선택 상태
				if(selectedUnit.getAttackable() == false){ // 유닛이 공격 가능하지 않을 경우 무시
					currentStatus = Status.WAIT_ACTION;
					break;
				}
				panels = makePanel(Action.Kind.ATTACK, x, y, selectedUnit.getAtttackRange());
				stage.getChildren().addAll(panels);
				currentStatus = Status.SELECT_ATTACK; // 현재 상태를 ATTACK으로 설정
				
			} else if(event.getButton() == MouseButton.SECONDARY) { // 대기 상태에서 오른쪽 버튼 클릭 -> 이동 선택 상태
				if(selectedUnit.getMovable() == false){ // 유닛이 이동 가능하지 않을 경우 무시
					currentStatus = Status.WAIT_ACTION;
					break;
				}
				panels = makePanel(Action.Kind.MOVE, x, y, selectedUnit.getMoveRange());
				stage.getChildren().addAll(panels);
				currentStatus = Status.SELECT_MOVE; // 현재 상태를 MOVE로 설정
				
			}
			break;
		case SELECT_MOVE:
			if(event.getButton() == MouseButton.PRIMARY){ // MOVE 상태에서 마우스 왼쪽 클릭 시
				// 이동 범위 내에서 클릭 시
				if(((Math.abs(selectedUnit.getX() - x) + Math.abs(selectedUnit.getY() - y)) <= selectedUnit.getMoveRange())){
					if(clickedUnit == selectedUnit){ // 같은 유닛을 한번 더 선택했을 경우 행동 취소
						stage.getChildren().removeAll(panels);
						currentStatus = Status.WAIT_ACTION;
						
					} else if(board.getArray()[x][y] == null) { // 클릭한 위치에 유닛이  없을 경우(이동 가능한 경우)
						stage.getChildren().removeAll(panels);
						currentStatus = Status.BUSY;
						selectedUnit.setMovable(false);
						
						// 이동 액션 생성
						Action act = new Action(
								selectedUnit.getX(),
								selectedUnit.getY(),
								x, y,
								Action.Kind.MOVE
								);
						actionQueue.add(act);

						// 이동 액션을 상대방에게 전송
						try {
							oos.writeUTF("action");
							oos.writeObject(act);
						} catch(Exception ex) {
						}

						// 유닛을 해당 위치로 이동
						selectedUnit.move(x, y);
						
						currentStatus = Status.WAIT_ACTION;
					}
				} else { // 이동 범위 밖에서 클릭 시 행동 취소
					stage.getChildren().removeAll(panels); 
					currentStatus = Status.WAIT_ACTION;
					
				}
			}
			break;
		case SELECT_ATTACK:
			if(event.getButton() == MouseButton.PRIMARY){ // ATTACK 상태에서 마우스 왼쪽 클릭 시
				// 이동 범위 내에서 클릭 시
				if(((Math.abs(selectedUnit.getX() - x) + Math.abs(selectedUnit.getY() - y)) <= selectedUnit.getAtttackRange())){
					if(clickedUnit == selectedUnit){
						stage.getChildren().removeAll(panels);
						currentStatus = Status.WAIT_ACTION;
						
					} else if(clickedUnit != null) {
						if(clickedUnit.getPlayer() != selectedUnit.getPlayer()) { // 클릭한 유닛이 적 팀일 경우
							stage.getChildren().removeAll(panels);
							currentStatus = Status.BUSY;
							
							// 현재 유닛의 이동 가능성 및 공격 가능성 제거
							selectedUnit.setMovable(false);  
							selectedUnit.setAttackable(false);
							
							// 공격 액션 생성
							Action act = new Action(
									selectedUnit.getX(),
									selectedUnit.getY(),
									x, y,
									Action.Kind.ATTACK
									);
							
							actionQueue.add(act);
							
							// 공격 액션을 상대방에게 전송
							try {
								oos.writeUTF("action");
								oos.writeObject(act);
							} catch(Exception ex) {
							}
							
							// 대상 유닛을 공격
							selectedUnit.attack(clickedUnit);
							currentStatus = Status.WAIT_ACTION;
						} else if(selectedUnit.getJob() == "medic" 
								&& clickedUnit.getPlayer() == selectedUnit.getPlayer()){
							// 선택된 유닛이 의무병이고 대상 유닛이 내 유닛일 경우 
							stage.getChildren().removeAll(panels);
							
							if(clickedUnit.getHp() == clickedUnit.getMaxHp()){ // 대상 체력이 최대치일 경우 행동 취소
								currentStatus = Status.WAIT_ACTION;
								return;
							}
							
							currentStatus = Status.BUSY;
							
							// 현재 유닛의 이동 가능성 및 공격 가능성 제거
							selectedUnit.setMovable(false);
							selectedUnit.setAttackable(false);
							
							// 회복 액션 생성
							Action act = new Action(
									selectedUnit.getX(),
									selectedUnit.getY(),
									x, y,
									Action.Kind.HEAL
									);
							
							actionQueue.add(act);
							
							// 회복 액션을 상대방에게 전송
							try {
								oos.writeUTF("action");
								oos.writeObject(act);
							} catch(Exception ex) {
							}
							
							// 대상 유닛 회복
							selectedUnit.heal(clickedUnit);
							currentStatus = Status.WAIT_ACTION;
						}
					}
				} else { // 이동 범위 밖에서 클릭 시 행동 취소
					stage.getChildren().removeAll(panels);
					currentStatus = Status.WAIT_ACTION;
				}
			}
			break;
		case WAIT_PLAYER:
		case BUSY:
			break;
		}
		
	}
	
	private void endGame(){
		if(gameThread.isAlive()) {
			gameThread.interrupt();
		}
		if(turnChecker.isAlive()) {
			turnChecker.interrupt();
		}
		
		Thread t = new Thread(()->{
			
			try {
				socket.close();
				if(server != null){
					server.close();
				}
				Thread.sleep(5000);
			} catch(Exception ex){  
			}
			
			
			// 로비 화면으로 돌아감
			Platform.runLater(()->{
				try {
					Stage s = (Stage)stage.getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/lobby.fxml"));

					loader.setController(new LobbyController(0, socketServer, oisServer, oosServer));
					Scene scene = new Scene((Parent)loader.load());
					
					s.setHeight(768);
					s.setScene(scene);
				} catch(Exception ex) {
				}
			});
		});
		t.setDaemon(true);
		t.start();
	}
	
}

