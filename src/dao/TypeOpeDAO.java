package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Modele.TypeOpe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TypeOpeDAO {
	
	Connection laConnection=DAO.initConnection();
	

	public ObservableList<String> afficherType() {
		ObservableList<String> listType= FXCollections.observableArrayList();
		try
		{
			Statement st= laConnection.createStatement();
		
			ResultSet rs= st.executeQuery("SELECT * FROM tb_typeOpe");
			
			
			while(rs.next()){
				
				
				String libelleType= rs.getString("libelleType");
				System.out.println("salut"+libelleType);
				
				
				//on parcours chacune des lignes de la requete, je recupère chacune des colonnes dans un attribut , et j'en crée un objet
				
				listType.add(libelleType);
			}
			}catch(SQLException e){
				
				
				e.printStackTrace();
			}
			
			return listType;
	}
	
	



	public ObservableList<String> afficherLesOpe() {
		ObservableList<String> listType= FXCollections.observableArrayList();
		try
		{
			Statement st= laConnection.createStatement();
		
			ResultSet rs= st.executeQuery("SELECT * FROM tb_typeope");
			
			
			while(rs.next()){
				
				
				Integer codeType= rs.getInt("codeType");
				String libelleType= rs.getString("libelleType");
//				TypeOpe to= new TypeOpe(codeType, libelleType);
				
				
				//on parcours chacune des lignes de la requete, je recupère chacune des colonnes dans un attribut , et j'en crée un objet
				
				listType.add(libelleType);
			}
			}catch(SQLException e){
				
				
				e.printStackTrace();
			}
			
			return listType;
	}
	
	

}
