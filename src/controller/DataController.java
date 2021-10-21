package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DataController implements Initializable {

	@FXML
	private TextField tekstKategori;

	@FXML
	private TextField tekstNavn;

	@FXML
	private TableView<GetSetData> dataTable;

	@FXML
	private TableColumn<GetSetData,String> kategoriCol;

	@FXML
	private TableColumn<GetSetData,String> navnCol;

	// En liste som følger med på endringer når de skjer
	static ObservableList<GetSetData> liste = FXCollections.observableArrayList();
	String kategori;
	String navn;

	// Metode som henter informasjo fra inputtekst
	void getValues(){
		kategori = tekstKategori.getText();
		navn = tekstNavn.getText();
	}

	// Metode som sjekker om input tekstene er tomme, hver for seg
	boolean isEmpty(){
		if(kategori.isEmpty()|| navn.isEmpty()){
			return true;
		}
		else {
			return false;
		}
	}

	// Metode brukt for å legge til produkter
	@FXML
	void addAction(ActionEvent event) {

		// Verdier blir hentet, og sjekkes mot tomme felt
		getValues();
		if(isEmpty()){
			AlertClass.showAlert("Fyll inn alle feltene!");
		}else{

					if(Functions.tallFinnes(kategori)==true){
						AlertClass.showAlert("Ikke tast inn tall i kategorifeltet!");
					}else {
						Functions.setData(kategori + "," + navn);
						oppdaterTabell();
					}
				}

		}

	// Metode brukt for å fjerne produkter
	@FXML
	void fjernAction(ActionEvent event) {

		// Verdier blir hentet, og sjekkes mot tomme felt
		getValues();
		if(isEmpty()){
			AlertClass.showAlert("Fyll inn alle feltene!");
		}else{

			Functions.fjernData(kategori +","+ navn,"data");
			oppdaterTabell();

		}
	}


	String sorterNavn, sorterKategori;

	// Metoden gjør det mulig å kunne sortere produkter mot pordukt navn, og produkt kategori
	@FXML
	void sorterAction(MouseEvent event) {
	sorterNavn =dataTable.getSelectionModel().getSelectedItem().getNavn();
	sorterKategori =dataTable.getSelectionModel().getSelectedItem().getKategori();
	setTekst();
	}

	void setTekst(){
		tekstKategori.setText(sorterKategori);
		tekstNavn.setText(sorterNavn);
	}

	// Metode brukt for å oppdatere produkter
	@FXML
	void oppdaterAction(ActionEvent event) {
		// Verdier blir hentet, og sjekkes mot tomme felt
		getValues();
		if(isEmpty()){
			AlertClass.showAlert("Fyll inn alle feltene!");
		}else{

			if(Functions.tallFinnes(kategori)==true){
				AlertClass.showAlert("Ikke tast inn tall i kategorifeltet!");
			}else {
				Functions.oppdaterData(sorterKategori + "," + sorterNavn, kategori + "," + navn, "data");
				oppdaterTabell();
			}

		}
	}

	public  void oppdaterTabell(){
		// tabellen rengjøres
		liste.clear();
		dataTable.getItems().clear();
		try {

			// Data hentes fram og framvist i tabellen
			Functions.hentData();
			navnCol.setCellValueFactory(new PropertyValueFactory<GetSetData,String>("navn"));
			kategoriCol.setCellValueFactory(new PropertyValueFactory<GetSetData,String>("kategori"));

			dataTable.setItems(null);
			dataTable.setItems(liste);


		}
		catch (Exception e) {
			AlertClass.showAlert(e.toString());
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		oppdaterTabell();
	}
}
