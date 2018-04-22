package application;


import java.io.IOException;

//import View.Accueil;
import View.AjouterOperation;
import View.InterfaceUser;
import View.Login;
import View.ModifSupprOpe;
//import View.RechercheClient;
import View.SelectionCpteModif;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

//----------------------------------------------------------------------------------------------------------------------------------------------------    
	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BankApp");

        initRootLayout();

        showLogin();
    }
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 //------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public void showLogin() {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/View/LoginView.fxml"));
            AnchorPane accueil = (AnchorPane) loader.load();

            //Met la fenêtre d'accueil au centre du root layout
            rootLayout.setCenter(accueil);
            
            //
            Login controller = loader.getController();
            
            controller.setMainApp(this);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public void showProUserInterface(String login) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/View/InterfaceUserView.fxml"));
            AnchorPane accueil = (AnchorPane) loader.load();

            //Met la fenêtre d'accueil au centre du root layout
            rootLayout.setCenter(accueil);
            
            //
           InterfaceUser controller = loader.getController();
            
            controller.setMainApp(this);
            System.out.println("bien passé");
            controller.setLogin(login);
            controller.afficherUtilisateur();
           controller.RemplitChoixNumCpte();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------
//  public void showAccueil(){
//	  
//	  try{
//		  FXMLLoader loader= new FXMLLoader();
//  		  
//  		loader.setLocation(Main.class.getResource("/View/AccueilView.fxml"));
//          AnchorPane page = (AnchorPane) loader.load();
//          
//       // Create the dialog Stage.
//          rootLayout.setCenter(page);
//
//          // Set the person into the controller.
//          Accueil controller = loader.getController();
//       
//          controller.setMainApp(this);
//         
//
//
//          
//  		
//  	}catch (IOException e){
//  		
//  		e.printStackTrace();
//  		
//  	}
//	  }
		  
//-------------------------------------------------------------------------------------------------	  
	  
  
//    public void showClientOverview(){
//    	
//    	try{
//    		FXMLLoader loader= new FXMLLoader();
//    		
//    		loader.setLocation(Main.class.getResource("/View/RechercheClientView.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//            
//         // Create the dialog Stage.
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Rechercher un client");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            // Set the person into the controller.
//            RechercheClient controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//           
//
//
//            // Show the dialog and wait until the user closes it
//            dialogStage.showAndWait();
//    		
//    	}catch (IOException e){
//    		
//    		e.printStackTrace();
//    		
//    	}
//    }
// 
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    
 public void ModifierOperationsOverview(){
    	
    	try{
    		FXMLLoader loader= new FXMLLoader();
    		
    		loader.setLocation(Main.class.getResource("/View/SelectionCpteModifView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
         // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Choisir un compte et modifier des opérations");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            SelectionCpteModif controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //permet l'accès aux méthodes de mon mainapp
            controller.setMainApp(this);
           


            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
    		
    	}catch (IOException e){
    		
    		e.printStackTrace();
    		
    	}
    }
 
 //-------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 public void AjouterOperationsOverview(String numCpte){
 	
 	try{
 		FXMLLoader loader= new FXMLLoader();
 		
 		loader.setLocation(Main.class.getResource("/View/AjouterOperations.fxml"));
         AnchorPane page = (AnchorPane) loader.load();
         
     
         Stage dialogStage = new Stage();
         dialogStage.setTitle("Ajouter une nouvelle opération");
         dialogStage.initModality(Modality.WINDOW_MODAL);
         dialogStage.initOwner(primaryStage);
         Scene scene = new Scene(page);
         dialogStage.setScene(scene);

        
         AjouterOperation controller = loader.getController();
         controller.setDialogStage(dialogStage);
         controller.setnumCpte(numCpte);
         controller.remplirTypeOpe();
        
         dialogStage.showAndWait();
 		
 	}catch (IOException e){
 		
 		e.printStackTrace();
 		
 	}
 }
 
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 public void ModifierSupprimerOperation(String numCpte){
	 	
	 	try{
	 		FXMLLoader loader= new FXMLLoader();
	 		//load la fenêtre d'affichage des opérations
	 		loader.setLocation(Main.class.getResource("/View/ModifSupprOpeView.fxml"));
	         AnchorPane page = (AnchorPane) loader.load();
	         
	      
	         Stage dialogStage = new Stage();
	         //affiche le titre de la fenêtre 
	         dialogStage.setTitle("Manager les opérations sur un compte");
	         dialogStage.initModality(Modality.WINDOW_MODAL);
	         dialogStage.initOwner(primaryStage);
	         Scene scene = new Scene(page);
	         dialogStage.setScene(scene);

	        
	         ModifSupprOpe controller = loader.getController();
	         controller.setDialogStage(dialogStage);
	         
	         //set le numéro de compte 
	         controller.setnumCpte(numCpte);
	         //affiche les opérations du compte dans la table
	         controller.afficherOpe();
	         //initialise le combobox des types d'opérations (crédit/débit)
	         controller.afficherTypeOpe();
	         
	        
	         
	    
	         dialogStage.showAndWait();
	 		
	 	}catch (IOException e){
	 		
	 		e.printStackTrace();
	 		
	 	}
	 }


//------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}