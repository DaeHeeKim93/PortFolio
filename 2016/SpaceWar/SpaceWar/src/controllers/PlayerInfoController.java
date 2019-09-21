package controllers;

import java.net.*;
import java.util.*;

import classes.*;
import javafx.application.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

public class PlayerInfoController implements Initializable {
	@FXML Group root;
	@FXML Label labelName;
	@FXML ListView<Unit> listViewUnit;
	@FXML Button buttonOk;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonOk.setOnAction(ev->okButtonClicked(ev));
		
		listViewUnit.setOrientation(Orientation.HORIZONTAL); // 리스트 뷰의 방향을 수평으로 설정
		
		// 리스트뷰 항목 업데이트 방법 설정
		listViewUnit.setCellFactory(e->new ListCell<Unit>(){
			ImageView imageView = new ImageView();
		
			@Override
			protected void updateItem(Unit item, boolean empty) {
				super.updateItem(item, empty);
				
				if(empty){
					this.setGraphic(null);
				} else {
					imageView.setImage(new Image(getClass().getResource("/images/" + item.getJob() + ".png").toString()));
					this.setGraphic(imageView);
				}
			}
		});
	}
	
	public void setName(String name) {
		Platform.runLater(()->labelName.setText(name));
	}
	
	public void setUnitList(List<Unit> units) {
		listViewUnit.getItems().addAll(units);
	}
	
	private void okButtonClicked(ActionEvent event) {
		((Stage)root.getScene().getWindow()).close();
	}
}
