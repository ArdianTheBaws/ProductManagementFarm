package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertClass {

  static Alert alert=new Alert(AlertType.INFORMATION);

  //en alert klasse for å vise feilmeldinger i form av pop up alerts
  public static void showAlert(String message) {
    alert.setContentText(message);
    alert.show();
  }
  
}
