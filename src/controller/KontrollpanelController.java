package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class KontrollpanelController implements Initializable {
//
	@FXML
	private TableView<GetSetData> dataTable;

	@FXML
	private TableColumn<GetSetData,String> kategoriCol;

	@FXML
	private TableColumn<GetSetData,String> navnCol;

	@FXML
	private RadioButton radioKategori;

	@FXML
	private TextField txtSearch;

	static ObservableList<GetSetData> liste = FXCollections.observableArrayList();
	static ObservableList<GetSetData> nyliste = FXCollections.observableArrayList();

	@FXML
	void tabellKlikkAction(MouseEvent event) {

	}
	@FXML
	void KlikkAction(KeyEvent event) {
		nyliste.clear();
		for(GetSetData data: liste){
			if(radioKategori.isSelected()){
				if(data.getKategori().startsWith(txtSearch.getText())){
					nyliste.add( new GetSetData(data.getKategori(), data.getNavn()));
				}
			}else{
				if(data.getNavn().startsWith(txtSearch.getText())){
					nyliste.add( new GetSetData(data.getKategori(), data.getNavn()));
				}
			}
		}
		dataTable.setItems(nyliste);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		oppdaterTabell();
	}

	public  void oppdaterTabell(){
		liste.clear();
		dataTable.getItems().clear();
		try {

			Functions.hentData();
			navnCol.setCellValueFactory(new PropertyValueFactory<GetSetData,String>("navn"));
			kategoriCol.setCellValueFactory(new PropertyValueFactory<GetSetData,String>("kategori"));

			dataTable.setItems(null);
			dataTable.setItems(liste);


		}catch (Exception e){
		AlertClass.showAlert(e.toString());
		}
	}

}
