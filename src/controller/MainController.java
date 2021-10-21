package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    void AddDataAction(ActionEvent event) {
        try {
            FxmlLoader obj = new FxmlLoader();
            Pane view = obj.hentSide("/FXML/AddData.fxml");
            mainPane.setCenter(view);
        }catch (Exception e){
            AlertClass.showAlert(e.toString());
        }
    }

    @FXML
    void KontrollPanelAction(ActionEvent event) {
        try {
            FxmlLoader obj = new FxmlLoader();
            Pane view = obj.hentSide("/FXML/Kontrollpanel.fxml");
            mainPane.setCenter(view);
        }catch (Exception e){
            AlertClass.showAlert(e.toString());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FxmlLoader obj = new FxmlLoader();
            Pane view = obj.hentSide("/FXML/Kontrollpanel.fxml");
            mainPane.setCenter(view);
        }catch (Exception e){
            AlertClass.showAlert(e.toString());
        }
    }
}
