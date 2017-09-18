package controllers;

import java.net.*;
import java.util.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 * 방 생성 창 컨트롤러
 * @author jaehyeon
 *
 */
public class CreateRoomController implements Initializable {
	@FXML private TextField textName;
	@FXML private Button buttonCreate;
	@FXML private Button buttonCancel;
	
	private boolean hasResult;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonCreate.setOnAction(ev->buttonCreateClicked(ev));
		buttonCancel.setOnAction(ev->buttonCancelClicked(ev));
	}
	
	private void close(){
		((Stage)textName.getScene().getWindow()).close();
	}
	
	private void buttonCreateClicked(ActionEvent event) {
		if(textName.getText() != null && textName.getText().isEmpty()){
			return;
		}
		
		hasResult = true;
		this.close();
	}

	private void buttonCancelClicked(ActionEvent event) {
		hasResult= false;
		this.close();
	}
	
	public String getResult(){
		return hasResult ? textName.getText() : null;
	}
}
