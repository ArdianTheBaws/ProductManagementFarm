package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Objects;


public class Main extends Application {
  Alert alert=new Alert(AlertType.CONFIRMATION);

  @Override
  public void start(Stage primaryStage) {
      try {

          Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Main.fxml")));
          Scene scene = new Scene(root);

          scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/app/style.css")).toExternalForm());
          primaryStage.setScene(scene);

          primaryStage.show();
      } catch(Exception e) {
          alert.setContentText("Error Loading Main Class");
          alert.show();
      }
  }
  
  public static void main(String[] args) {
      launch(args);
  }
}
