package View;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SelectionCpteModif {
	
	
	@FXML
	public TextField ztNumCpte;
	@FXML
	private Button btManager; 
	@FXML
	private Button btAjouter;
	@FXML
	private Button btSupprimer;
	
	@FXML
	private Stage dialogStage;
	
	private Main main; 
	
	//------------------------------------------------------------------------------------------------
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	//-------------------------------------------------------------------------------------------------
	
	public String getztNumcpte(){
		
		
		return ztNumCpte.getText();
	}
	
		
	
	//--------------------------------------------------------------------------------------------------
	
	@FXML
	private void initialize() {
	}
	
	//-------------------------------------------------------------------------------------------------
	
	 public void handleAjouterOperation(){
		 
		 String numCpte= ztNumCpte.getText();
		 
		 
		 if (ztNumCpte.getText().equals("")){
					 
					 Main main= new Main();
				     Alert alert = new Alert(AlertType.WARNING);
				     alert.initOwner(main.getPrimaryStage());
				     alert.setTitle("Aucun compte");
				     alert.setHeaderText("Pas de numéro de compte renseigné");
				     alert.setContentText("Renseignez le numéro de compte sur lequel ajouter une opération");
		
				     alert.showAndWait();
				 }
		 
		 
	 else if (!this.isInteger(numCpte)){
			 
			 Main main= new Main();
		     Alert alert = new Alert(AlertType.WARNING);
		     alert.initOwner(main.getPrimaryStage());
		     alert.setTitle("Numéro de compte non valide");
		     alert.setHeaderText("Le numéro de compte renseigné n'est pas valide.");
		     alert.setContentText("Renseignez le numéro de compte de la forme 00xx ( numérique uniquement) ");
			 alert.showAndWait();
		 }
		 

		 
		 
		 else {
		 main.AjouterOperationsOverview(numCpte);
		 }
	    	 	
	    	
	    }
	 
	//--------------------------------------------------------------------------------------------------
	 
	 public void handleManageOperation(){
		 
		
		 String numCpte= ztNumCpte.getText();
		 if (ztNumCpte.getText().equals("")){
			 
			 Main main= new Main();
		     Alert alert = new Alert(AlertType.WARNING);
		     alert.initOwner(main.getPrimaryStage());
		     alert.setTitle("Aucun compte");
		     alert.setHeaderText("Pas de numéro de compte renseigné");
		     alert.setContentText("Renseignez le numéro de compte sur lequel effectuer des modifications");

		     alert.showAndWait();
		 }
		
		 
		 else if (!this.isInteger(numCpte)){
			 
			 Main main= new Main();
		     Alert alert = new Alert(AlertType.WARNING);
		     alert.initOwner(main.getPrimaryStage());
		     alert.setTitle("Numéro de compte non valide");
		     alert.setHeaderText("Le numéro de compte renseigné n'est pas valide.");
		     alert.setContentText("Renseignez le numéro de compte de la forme 00xx ( numérique uniquement) ");
			 alert.showAndWait();
		 }
		 
		 
		 else{
		 main.ModifierSupprimerOperation(numCpte);
		 }
		 
	 }
	 
	 
	//--------------------------------------------------------------------------------------------------
	 
	 
	  public void setMainApp(Main main) {
	        this.main = main;
	  }
	  
	//-------------------------------------------------------------------------------------------------- 
	public boolean isInteger(String test){
		
		Boolean b=true;
		try {
			Integer.parseInt(test);
			
		}catch(NumberFormatException e){
			
			System.out.println("BAH");
			
			b=false;
		}
		
		return b;
	}

}
