package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Modele.Client;
import Modele.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompteDAO {
	
	Connection laConnection=DAO.initConnection();
	

	public ArrayList<Compte> renvoyerCpte(String crit){
		
	ArrayList<Compte>listCpte= new ArrayList();
	try {
		
		Client c= new Client();
		Statement st= laConnection.createStatement();
		Statement st2= laConnection.createStatement();
		//recupère l'ensemble des comptes correspondants aux critères passés en paramêtre
		ResultSet rs= st.executeQuery("SELECT * FROM tb_compte LEFT JOIN tb_client on tb_compte.numClt=tb_client.numClt WHERE "+crit+";");
		
		
		while (rs.next()){
			
		String numcpte=rs.getString("numCpte");
		LocalDate datecreation=rs.getDate("datecreationCpte").toLocalDate();
		String solde= rs.getString("soldeCpte");
		String numclt= rs.getString("numClt");
		
		ResultSet rs2= st2.executeQuery("SELECT * FROM tb_client WHERE numClt='"+numclt+"'");
		
		while (rs2.next()){
		String nomClt= rs2.getString("nomClt");
		String prenomClt= rs2.getString("prenomClt");
		String villeClt= rs2.getString("villeClt");
		String codePostalClt= rs2.getString("codePostalClt");
		String telClt= rs2.getString("telClt");
		c= new Client(numclt, nomClt, prenomClt, villeClt, telClt, codePostalClt);
		
		
		}
		
		Compte cpt= new Compte(numcpte, datecreation, solde, c);
		listCpte.add(cpt);
			
			
			
		}
	}catch(SQLException e){
		
	 e.printStackTrace();
		
	}
	
	return listCpte;
	}
	
//-----------------------------------------------------------------------------------------------------------
//	public ArrayList<Compte> renvoyerCpteAvcNum(String numCpte){
//		
//		ArrayList<Compte>listCpte= new ArrayList();
//		try {
//			
//			System.out.println("la");
//			Statement st= laConnection.createStatement();
//			ResultSet rs= st.executeQuery("Select * from tb_compte WHERE numCpte='"+numCpte+"'");
//			while (rs.next()){
//				
//			String numcpte=rs.getString("numCpte");
//			LocalDate datecreation=rs.getDate("datecreationCpte").toLocalDate();
//			String solde= rs.getString("soldeCpte");
//			
//			Compte cpt= new Compte(numcpte, datecreation, solde);
//			listCpte.add(cpt);
//				
//				
//				
//			}
//		}catch(SQLException e){
//			
//		 e.printStackTrace();
//			
//		}
//		
//		return listCpte;
//		}
	
//--------------------------------------------------------------------------------------------------------------
	
	public ObservableList<String> renvoyerListeCpte(){
		
		ObservableList<String> listCpte= FXCollections.observableArrayList();
		
	
		try
		{
			Statement st= laConnection.createStatement();
		
			ResultSet rs= st.executeQuery("SELECT * FROM tb_compte");
			
			
			while(rs.next()){
				
				//je recupere le contenu de la colonne numOpe, montantOpe etc
				String numCpte= rs.getString("numCpte");
				
				
				//on parcours chacune des lignes de la requete, je recupère chacune des colonnes dans un attribut , et j'en crée un objet
				
				listCpte.add(numCpte);
			}
			}catch(SQLException e){
				
				
				e.printStackTrace();
			}
			
			return listCpte;
		
		
	}
	
//--------------------------------------------------------------------------------------------
	public void actualiserSolde(Float montantCpte, String numCpte){
		try
		{	
			Statement st= laConnection.createStatement();
			st.executeUpdate("UPDATE tb_compte set soldeCpte='"+montantCpte+"' where numCpte="+numCpte+"");
			
		}catch(SQLException e){
			
			e.printStackTrace();	
		}
	}
}
