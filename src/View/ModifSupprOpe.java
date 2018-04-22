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
	//affiche la liste des opérations à l'ouverture de la fenêtre dans le tableview
	public void afficherOpe(){
		
		OperationsDao opeDao=new OperationsDao();
		ArrayList<Operations> listOpe =new ArrayList<Operations>();
		
		listOpe= opeDao.trouverOperations(numCpte);
		
		if (listOpe.size()==0){
			
			Main main= new Main();
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("Aucune opération trouvée");
			alert.setHeaderText("Aucune opération ne correspond à votre numéro de compte saisi");
			alert.setContentText("Vérifiez le numéro de compte saisi ou ajoutez de nouvelles opérations sur ce compte ");
			
			alert.showAndWait();
		}
		
		else{
		
		//permet la conversion d'une arraylist en observablelist utilisée en javafx
		ObservableList<Operations> data= FXCollections.observableArrayList(listOpe);
	
		tabOperations.setItems(data);
		String soldeCpt= opeDao.getSoldeCpte().toString();
		
		this.tfSolde.setText(soldeCpt);
		
		//on remplit chaque colonne
		clNumOpe.setCellValueFactory(cellData -> cellData.getValue().numOpe());
		
		//listener lançant l'affichage du détail de l'opération sélectionnée 
		tabOperations.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> afficherDetailsOpe(newValue));
		}
	}
	
	//------------------------------------------------------------------------------------------------
	//affiche dans les textfields les détails d'une opération choisie, est appelé par le listener
	public void afficherDetailsOpe(Operations newValue){
		
		
		ztNumero.setText(newValue.getNumOpe());
		ztMontant.setText(newValue.getMontantOpe().toString());
		dpDate.setValue(newValue.getDateOpe());
		
		//affiche le libellé du type d'opération
		if (newValue.getCodeType()==1){
			cbTypeOpe.setValue("Crédit");
		}
		else cbTypeOpe.setValue("Débit");
		
		
		
		
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
	    if(cbTypeOpe.getValue()=="Crédit"){
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
        alert.setTitle("Modification d'opération");
        alert.setHeaderText("Modification effectuée");
        alert.setContentText("La modification de l'opération a bien été effectuée");
        alert.showAndWait();
        
	    
	    }
	    
	    //rien n'a été sélectionné, on le notifie à l'utilisateur
	    else {
	    	Main main= new Main();
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(main.getPrimaryStage());
	        alert.setTitle("Aucune opération");
	        alert.setHeaderText("Pas d'opérations séléctionnée");
	        alert.setContentText("Sélectionner une opération dans la liste afin de la modifier");

	        alert.showAndWait();
	    }
	    
		
	}
//--------------------------------------------------------------------------------------------------
	//affiche dans le combobox les types d'opération sélectionnables par l'utilisateur
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
			//on recupère l'index sélectionné dans la liste
		    int selectedIndex = tabOperations.getSelectionModel().getSelectedIndex();
		    
		    //si un index a bien été sélectionné, on recupère la l'objet de la ligne correspondant à l'index
		    if (selectedIndex >=0)
		    {
		    
		    Operations o= tabOperations.getItems().get(selectedIndex);
		    String numOpe = o.getNumOpe();
		    //on supprime en base l'opération en appelant la méthode du Dao 
		    opeDao.supprimerOpe(numOpe);
		    //on supprime aussi physiquement la ligne 
		    tabOperations.getItems().remove(selectedIndex);
		    }
		    
		    //rien n'a été sélectionné, on le notifie à l'utilisateur par un message d'avertissement 
		    else {
		    	Main main= new Main();
		        Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("Aucune opération");
		        alert.setHeaderText("Pas d'opérations séléctionnée");
		        alert.setContentText("Sélectionner une opération dans la liste afin de la supprimer");

		        alert.showAndWait();
		    }
		    
		}
}


