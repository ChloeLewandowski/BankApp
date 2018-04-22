package View;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Modele.Operations;
import Modele.TypeOpe;
import application.Main;
import dao.OperationsDao;
import dao.TypeOpeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifSupprOpe {
	
	@FXML
	private TextField ztNumero;
	@FXML
	private TextField ztMontant;
	@FXML
	private Label ztType;
	@FXML
	private DatePicker dpDate;
	@FXML
	private TextField ztDebit;
	@FXML
	private TextField ztCredit;
	@FXML
	private Button btModifier;
	@FXML
	private Button btSupprimer;
	@FXML
	private Button btNouveau;
	@FXML
	private Button btPDF;
	@FXML
	private Button btAjouter;
	@FXML
	private TableView <Operations> tabOperations;
	@FXML 
	private TableColumn <Operations, String> clNumOpe;
	@FXML 
	private TableColumn <Operations, String> clNomOpe;	
	@FXML
	private JFXButton btFermer;
	@FXML
	private Label tfSolde;
	@FXML
	private JFXComboBox<String> cbTypeOpe;
	@FXML
	private JFXButton btActualiser;
	@FXML
	private Stage dialogStage;	
	private Main main= new Main();
	private String numCpte;
	TypeOpeDAO todao=new TypeOpeDAO();
	
	//----------------------------------------------------------------------------------------------------
	
	public void setnumCpte(String numCpte){
		
		this.numCpte=numCpte;
	}
	
	//------------------------------------------------------------------------------------------------
	public String getnumCpte(){
		
		return this.numCpte;
	}
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	//------------------------------------------------------------------------------------------------
	//affiche la liste des op�rations � l'ouverture de la fen�tre dans le tableview
	public void afficherOpe(){
		
		OperationsDao opeDao=new OperationsDao();
		ArrayList<Operations> listOpe =new ArrayList<Operations>();
		
		listOpe= opeDao.trouverOperations(numCpte);
		
		if (listOpe.size()==0){
			
			Main main= new Main();
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Aucune op�ration trouv�e");
			alert.setHeaderText("Aucune op�ration ne correspond � votre num�ro de compte saisi");
			alert.setContentText("V�rifiez le num�ro de compte saisi ou ajoutez de nouvelles op�rations sur ce compte ");
			
			alert.showAndWait();
		}
		
		else{
		
		//permet la conversion d'une arraylist en observablelist utilis�e en javafx
		ObservableList<Operations> data= FXCollections.observableArrayList(listOpe);
	
		tabOperations.setItems(data);
		String soldeCpt= opeDao.getSoldeCpte().toString();
		
		this.tfSolde.setText(soldeCpt);
		
		//on remplit chaque colonne
		clNumOpe.setCellValueFactory(cellData -> cellData.getValue().numOpe());
		
		//listener lan�ant l'affichage du d�tail de l'op�ration s�lectionn�e 
		tabOperations.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> afficherDetailsOpe(newValue));
		}
	}
	
	//------------------------------------------------------------------------------------------------
	//affiche dans les textfields les d�tails d'une op�ration choisie, est appel� par le listener
	public void afficherDetailsOpe(Operations newValue){
		
		
		ztNumero.setText(newValue.getNumOpe());
		ztMontant.setText(newValue.getMontantOpe().toString());
		dpDate.setValue(newValue.getDateOpe());
		
		//affiche le libell� du type d'op�ration
		if (newValue.getCodeType()==1){
			cbTypeOpe.setValue("Cr�dit");
		}
		else cbTypeOpe.setValue("D�bit");
		
		
		
		
	}
	
	//--------------------------------------------------------------------------------------------------
	
//-----------------------------------------------------------------------------------------------
	//on modifie une ligne choisie par l'utilisateur 
	
	public void handleModifierOpe(){
		
		OperationsDao opeDao=new OperationsDao();
		TypeOpe ope= new TypeOpe();
		Integer typeOpe;
	    int selectedIndex = tabOperations.getSelectionModel().getSelectedIndex();
	    
	    if (selectedIndex >=0)
	    {
	    
	    String numOpe= ztNumero.getText();
	    Float montantOpe= Float.parseFloat(ztMontant.getText());
	    LocalDate dateOpe= dpDate.getValue();
	    if(cbTypeOpe.getValue()=="Cr�dit"){
	    	typeOpe=1;
	    }
	    else{
	     typeOpe=2;
	    }
	   
	    
	    Operations o= new Operations(numOpe, montantOpe, dateOpe, typeOpe);
	    
	    
	    opeDao.updateOpe(o);
	    Main main= new Main();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Modification d'op�ration");
        alert.setHeaderText("Modification effectu�e");
        alert.setContentText("La modification de l'op�ration a bien �t� effectu�e");
        alert.showAndWait();
        
	    
	    }
	    
	    //rien n'a �t� s�lectionn�, on le notifie � l'utilisateur
	    else {
	    	Main main= new Main();
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Aucune op�ration");
	        alert.setHeaderText("Pas d'op�rations s�l�ctionn�e");
	        alert.setContentText("S�lectionner une op�ration dans la liste afin de la modifier");

	        alert.showAndWait();
	    }
	    
		
	}
//--------------------------------------------------------------------------------------------------
	//affiche dans le combobox les types d'op�ration s�lectionnables par l'utilisateur
	public void afficherTypeOpe(){
		ObservableList<String> listOpe;
		listOpe=todao.afficherLesOpe();
		cbTypeOpe.setItems(listOpe);
	}
//--------------------------------------------------------------------------------------------------
	public void handleAjouter(){
		
		main.AjouterOperationsOverview(numCpte);
	}
//-------------------------------------------------------------------------------------------------
	//supprime une ligne choisie 
		private void handleSupprimerOpe() {
			
			OperationsDao opeDao=new OperationsDao();
			//on recup�re l'index s�lectionn� dans la liste
		    int selectedIndex = tabOperations.getSelectionModel().getSelectedIndex();
		    
		    //si un index a bien �t� s�lectionn�, on recup�re la l'objet de la ligne correspondant � l'index
		    if (selectedIndex >=0)
		    {
		    
		    Operations o= tabOperations.getItems().get(selectedIndex);
		    String numOpe = o.getNumOpe();
		    //on supprime en base l'op�ration en appelant la m�thode du Dao 
		    opeDao.supprimerOpe(numOpe);
		    //on supprime aussi physiquement la ligne 
		    tabOperations.getItems().remove(selectedIndex);
		    }
		    
		    //rien n'a �t� s�lectionn�, on le notifie � l'utilisateur par un message d'avertissement 
		    else {
		    	Main main= new Main();
		        Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("Aucune op�ration");
		        alert.setHeaderText("Pas d'op�rations s�l�ctionn�e");
		        alert.setContentText("S�lectionner une op�ration dans la liste afin de la supprimer");

		        alert.showAndWait();
		    }
		    
		}
}


