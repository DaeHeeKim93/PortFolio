package classes;

import controllers.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// login 화면을 불러와서 창을 띄운다.
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/login.fxml"));
		
		Parent root = loader.load();

		Scene scene = new Scene(root);
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
