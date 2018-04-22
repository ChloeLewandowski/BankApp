package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.time.LocalDate;

import com.jfoenix.controls.JFXComboBox;

import Modele.Operations;
import application.Main;
import dao.CompteDAO;
import dao.OperationsDao;
import dao.TypeOpeDAO;

 
public class AjouterOperation extends SelectionCpteModif {
	

	@FXML
	private TextField ztNumOpe;
	@FXML
	private TextField ztMontant;
	@FXML
	private DatePicker dtpOpe;
	@FXML
	private JFXComboBox ztTypeOpe;
	@FXML
	private TextField ztCredit;
	@FXML
	private TextField ztDebit;

	@FXML
	private Stage dialogStage;
	private Main main;
	private String numCpte;
	
//-----------------------------------------------------------------------------------------------------	
	public void setnumCpte(String numCpte){
		
		this.numCpte=numCpte;
	}

//----------------------------------------------------------------------------------------------------
	public void remplirTypeOpe(){
		
		TypeOpeDAO todao= new TypeOpeDAO();
		ObservableList <String> listTypeOpe= FXCollections.observableArrayList();
		listTypeOpe=todao.afficherType();
		ztTypeOpe.setItems(listTypeOpe);
		
	}
	
//----------------------------------------------------------------------------------------------------
	
	public void creerope(){
		
		OperationsDao op= new OperationsDao();
		Integer typeOpe=2;
		String numOpe=null;
		Float montantOpe= Float.parseFloat(ztMontant.getText());
		LocalDate dateOpe= dtpOpe.getValue();
		
		if (ztTypeOpe.getValue()=="Crédit"){
			typeOpe=1;
		}
		
		//on crée la nouvelle opération en utilisant les nouveaux attributs remplis par l'utilisateur
		Operations o= new Operations(numOpe, montantOpe,dateOpe, typeOpe);
		
		//on essaye d'ajouter l'opération, si l'opération est bien ajoutée, un booléen est passé en valeur 1
		Boolean ok=op.ajouterOpe(o, numCpte);
		
		//on informe l'utilisateur si l'opération a bien été ajoutée. 
		if (ok){
		
			Main main= new Main();
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Opération ajoutée");
	        alert.setHeaderText("L'opération a bien été ajoutée");
	        alert.setContentText("Opération ajoutée");

	        alert.showAndWait();
		}
		
	}

//----------------------------------------------------------------------------------------------------
	
	@Override
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

//----------------------------------------------------------------------------------------------------
	
	
	@FXML
	private void initialize() {
	}
	
//----------------------------------------------------------------------------------------------------
//permet de fermer la fenêtre d'ajout d'opérations
	@FXML
	private void handleFermer(){
		
		dialogStage.close();
		
	}

	
	}


