package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import Modele.Client;

public class ClientDAO {
	
	Connection laConnection=DAO.initConnection();
	
	
//	//méthode permettant la recherche des clients à afficher dans le client overview
//	public ArrayList<Client> trouverClients (String nomClt){
//		
//		ArrayList<Client>listClt= new ArrayList();
//		Client clt= new Client();
//		
//		try
//		{
//			Statement st= laConnection.createStatement();
//		
//			ResultSet rs= st.executeQuery("SELECT * FROM tb_client WHERE nomClt='"+nomClt+"'");
//			
//			while(rs.next()){
//				
//				//je recupère le contenu de chacune des colonne de ma table et les place dans un variable
//				String numClient= rs.getString("numClt");
//				String nomClient= rs.getString("nomClt");
//				String prenomClient= rs.getString("prenomClt");
//				String villeClient= rs.getString("villeClt");
//				
//				//j'injecte ces valeurs dans le constructeur de l'objet client crée précedemment 
//				clt= new Client(numClient,nomClient, prenomClient, villeClient);
//				//j'ajoute à ma liste ce nouvel objet Client
//				listClt.add(clt);
//				
//				
//				
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		
//		
//		
//		return listClt;
//	}
}
	
//------------------------------------------------------------------------------------------------------------
	