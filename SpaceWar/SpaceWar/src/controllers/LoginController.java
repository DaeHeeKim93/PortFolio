package controllers;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import classes.*;
import javafx.application.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.stage.*;
import javafx.util.*;

public class LoginController implements Initializable {
	@FXML
	private GridPane titleBar;
	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonJoin;
	@FXML
	private Button buttonExit;
	@FXML
	private TextField textId;
	@FXML
	private TextField textPassword;

	private MediaPlayer player;

	private double dx, dy;
	private Window window;
	
	private static final String SERVER_IP = "14.52.161.196";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonLogin.setOnAction(ev->loginHandler(ev));
		buttonJoin.setOnAction(ev->joinHandler(ev));
		buttonExit.setOnAction(ev->Platform.exit());
		
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
		
		/***********************
		 * Play BGM
		 ***********************/
		player = new MediaPlayer(new Media(getClass().getResource("/bgms/bgmLogin.mp3").toString()));
		player.setOnEndOfMedia(()->{
			player.seek(Duration.ZERO);
		});
		player.play();
	}
	
	/********************************
	 * 캐릭터 선택 화면을 연다.
	 * @throws SQLException 
	 ********************************/
	private void openCharacterSelect(
			String id, 
			int idNumber, 
			Socket sock, 
			ObjectInputStream ois, 
			ObjectOutputStream oos 
			) throws SQLException {
		
		Stage stage = (Stage)titleBar.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/character_select.fxml"));
		try {
			loader.setController(new CharacterSelectController(idNumber,sock,ois,oos));
			Parent select = loader.load();
			stage.setScene(new Scene(select));
			stage.setTitle("Select Character");
			stage.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*******************************
	 * 로비 화면을 연다.
	 *******************************/
	private void showLobby(
			int idNumber, 
			Socket sock, 
			ObjectInputStream ois, 
			ObjectOutputStream oos
			) {
		
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
	}
	
	
	
	/********************************
	 * 로그인 버튼 클릭 핸들러
	 ********************************/
	private void loginHandler(ActionEvent ev){
		// 알림 창 생성
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle(null);
		int id_num = 0;
		
		try {
			Socket sock = new Socket(SERVER_IP, 10003);
			
			OutputStream os = sock.getOutputStream();
			InputStream is = sock.getInputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ObjectInputStream ois = new ObjectInputStream(is);
			
			if (textId.getText().isEmpty() || textPassword.getText().isEmpty()) {
				alert.setContentText("아이디와 비밀번호를 입력하세요.");
				alert.showAndWait();
				sock.close();
				return;
		    }
			
			// 로그인 정보를 서버로 보냄
			oos.writeObject(new IdInfo(textId.getText(), textPassword.getText(), 1));

			// 서버로부터 접속 성공 여부를 받음
			if(!((boolean)ois.readObject())) {
				oos.close();
				ois.close();
				sock.close();
				alert.setContentText("로그인 실패");
				alert.showAndWait();
				return;
			}
			
			// 
		    ((Stage)titleBar.getScene().getWindow()).close();// 창을 닫는다.
			
			if(ois.readBoolean()) { // 캐릭터 선택 미완료 
				id_num=ois.readInt();
				oos.reset();
				
				openCharacterSelect(textId.getText(),id_num,sock,ois,oos);
			} else {
				ois.readInt();// 원인불명의 데이터를 미리 읽어들임;;
				showLobby(id_num,sock,ois,oos);
			}
			
		} catch (IOException | ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		// Close login window and open main window
		player.stop();
		
	}
	
	/********************************
	 * 회원가입 버튼 클릭 핸들러
	 ********************************/
	private void joinHandler(ActionEvent ev){
		// 알림 창 생성
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle(null);
		
		int idNumber;
		try{
			Socket sock = new Socket(SERVER_IP, 10003);
			
			OutputStream os = sock.getOutputStream();
			InputStream is = sock.getInputStream();
			
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ObjectInputStream ois = new ObjectInputStream(is);
			
            if (textId.getText().isEmpty() || textPassword.getText().isEmpty()) {
				alert.setContentText("아이디와 비밀번호를 입력하세요.");
				alert.showAndWait();
				if(!sock.isClosed()){
					sock.close();
				}
				return;
	     	}

            // 서버로 로그인 정보를 보낸다.
			oos.writeObject(new IdInfo(textId.getText(), textPassword.getText(), 0));
			oos.flush();
			oos.reset();
			
			// 서버로부터 로그인 성공 여부를 받는다.
			if((boolean)ois.readObject()) {
				alert.setContentText("성공적으로 회원가입하였습니다.");
				alert.showAndWait();
			} else {
				oos.close();
				ois.close();
				sock.close();
				alert.setContentText("회원가입에 실패하였습니다.");
				alert.showAndWait();
				return;
			}
			
			idNumber = ois.readInt(); // 서버로부터 아이디 넘버를 받는다.
			((Stage)titleBar.getScene().getWindow()).close(); // 창을 닫는다.
			
			oos.reset();
			player.stop(); // BGM 종료
			
			// 캐릭터 선택 창을 연다.
			openCharacterSelect(textId.getText(),idNumber,sock,ois,oos);
			
		}catch (IOException | ClassNotFoundException | SQLException e1) {
			player.stop();
			e1.printStackTrace();
		}
	}
	
}

