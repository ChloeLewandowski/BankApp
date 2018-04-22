package View;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Modele.Client;
import Modele.Compte;
import Modele.Operations;
import Modele.Utilisateur;
import application.Main;
import dao.CompteDAO;
import dao.OperationsDao;
import dao.UtilisateurDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InterfaceUser {

	@FXML
	private Label lbNomPro;
	@FXML
	private Label lbDateConseiller;

	@FXML 
	private JFXComboBox SpNCpt;
	@FXML
	private TextField ztNomProprio;
	@FXML
	private TextField ztCodePostal;
	@FXML
	private TextField ztNumTel;
	@FXML
	private TableView<Compte>tabComptes;
	@FXML
	private TableColumn<Compte,String>clNumCpte;
	@FXML
	private TableColumn<Compte, String>clNomProprio;
	@FXML
	private TableColumn<Compte, String>clPrenomProprio;
	@FXML
	private TableColumn<Compte, String>clVilleProprio;
	@FXML
	private TableColumn<Compte, String>clCodePostal;
	@FXML
	private TableColumn<Compte, String>clNumTel;
	@FXML
	private JFXButton btRechercheCpte;
	@FXML
	private JFXButton btDetails;
	ObservableList<Compte> data;

	private Main main;
	private String login;
	UtilisateurDAO useDao= new UtilisateurDAO();

	//--------------------------------------------------------------------------------------------------	

	public void setMainApp(Main main) {
		this.main = main;

	}		 	
	//--------------------------------------------------------------------------------------------------

	public void setLogin(String login) {

		this.login=login;

	}		 	



	//--------------------------------------------------------------------------------------------------

	public void afficherUtilisateur(){


		Utilisateur user=useDao.renvoyerInfos(login);
		String nom= user.nomUtil()+" "+user.prenomUtil();
		LocalDate date= user.dateEmbauche();
		lbNomPro.setText(nom);
		lbDateConseiller.setText(date.toString());
	}

	//--------------------------------------------------------------------------------------------------
	//vient implémenter la liste des numéros de compte dans le selecteur combo box	
	public void RemplitChoixNumCpte(){

		ObservableList <String> listCpte= FXCollections.observableArrayList();
		CompteDAO cptedao= new CompteDAO();
		listCpte=cptedao.renvoyerListeCpte();
		SpNCpt.setItems(listCpte);
		


	}

	//--------------------------------------------------------------------------------------------------------
	public void rechercheCpte(){
		CompteDAO cptedao= new CompteDAO();
		ArrayList<Compte>listCpte= new ArrayList();
		// cette variable permettra de créer une requête sql sur mesure
		String crit="";
		System.out.println("zé parti");



		//on vient vérifier si le  champ est vide sinon on recupère la valeur 
		if(!ztNomProprio.getText().equals("")){

			
			String nom=ztNomProprio.getText();
			crit="nomClt='"+nom+"'";
		}

		if (!(SpNCpt.getValue()==null)){

			String numCpte= SpNCpt.getValue().toString();

			if (crit.equals("")){

				crit="numCpte='"+numCpte+"'";
			}else{

				crit=crit+"and numCpte='"+numCpte+"'";
			}		

		}



		if (!ztCodePostal.getText().equals("")){

			String codePostal= ztCodePostal.getText();

			if (crit.equals("")){

				crit="codePostalCLt='"+codePostal+"'";
			}else{

				crit=crit+"and codePostalClt='"+codePostal+"'";
			}		
		}

		if (!ztNumTel.getText().equals("")){

			String numTel= ztNumTel.getText();

			if (crit.equals("")){

				crit="telClt='"+numTel+"'";
			}else{

				crit=crit+"and telClt='"+numTel+"'";

			}


		}

		System.out.println(crit);
		listCpte=cptedao.renvoyerCpte(crit);

		String numCpte=listCpte.get(0).getNumCpte();

		//permet la conversion d'une arraylist en observablelist utilisée en javafx
		data= FXCollections.observableArrayList(listCpte);
		System.out.println("Test:->"+data.get(0).getCli().getNumClt());

		tabComptes.setItems(data);
		clNumCpte.setCellValueFactory(cellData -> cellData.getValue().numCpte());
		clNomProprio.setCellValueFactory(cellData-> cellData.getValue().getCli().nomClt());
		clPrenomProprio.setCellValueFactory(cellData-> cellData.getValue().getCli().prenomClt());
		clVilleProprio.setCellValueFactory(cellData -> cellData.getValue().getCli().villeClt());
		clCodePostal.setCellValueFactory(cellData-> cellData.getValue().getCli().codePostal());
		clNumTel.setCellValueFactory(cellData -> cellData.getValue().getCli().numClt());

		//clNomProprio.setCellValueFactory(cellData -> cellData.getValue().);

	}

//---------------------------------------------------------------------------------------------------------
	public void handleConsulterCpte(){

		CompteDAO cptedao= new CompteDAO();
		//on recupère le numéro de la ligne selectionnée dans le tableau  
		int selectedIndex = tabComptes.getSelectionModel().getSelectedIndex();
		
		//si une ligne a bien été sélectionnée, on utilise l'index récupéré pour récupérer le numéro de compte
		if (selectedIndex >=0)
		{
			String numCpte=tabComptes.getItems().get(selectedIndex).getNumCpte();
			//on passe en paramêtre de la méthode le numéro de compte 
			main.ModifierSupprimerOperation(numCpte);
			System.out.println("num bien présent"+numCpte);

		}

	}
	
//-------------------------------------------------------------------------------------------------------------
	public void refreshRecherche(){
		
		SpNCpt.setValue(null);
		ztNomProprio.setText("");
		ztCodePostal.setText("");
		ztNumTel.setText("");
		this.data.removeAll(data);
	
		
	}
}