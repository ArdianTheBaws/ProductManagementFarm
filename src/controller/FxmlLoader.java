package controller;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class FxmlLoader {

  @FXML
  private Pane view;

  Alert alert=new Alert(AlertType.CONFIRMATION);

  public Pane hentSide(String filNavn){
      try{
          URL FileURl= app.Main.class.getResource(filNavn);
          if(FileURl==null){
               alert.setContentText("FXML FEIL");
               alert.show();
          }
          view=new FXMLLoader().load(FileURl);
      }catch (Exception e){
           alert.setContentText(e.toString());
           alert.show();
      }
      return view;
  }

}
