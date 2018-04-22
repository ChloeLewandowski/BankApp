package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Modele.Utilisateur;

public class UtilisateurDAO {
	
	Connection laConnection=DAO.initConnection();

	public Boolean loginRequest(String login, String password) {

		Boolean connexionOk = false;

		try {

			Statement st = laConnection.createStatement();
			ResultSet rs = st.executeQuery("SELECT idUtil FROM tb_Utilisateurs where loginUtil='" + login
					+ "' and passwordUtil='" + password + "'");

			if (rs.next()) {

				connexionOk = true;
				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return connexionOk;
	}

	// ---------------------------------------------------------------------------------------------

	public Utilisateur renvoyerInfos(String login) {

		Utilisateur user = new Utilisateur();

		try {

			Statement st= laConnection.createStatement();
			ResultSet rs= st.executeQuery("SELECT * FROM tb_utilisateurs WHERE loginUtil='"+login+"'");
			
			
			if (rs.next()){
			
			Integer id= rs.getInt(1);
			String prenom= rs.getString("prenomUtil");
			String nom=rs.getString("nomUtil");
			LocalDate dateEmbauche= rs.getDate("dateEmbauche").toLocalDate();
			user= new Utilisateur(id, login, nom, prenom, dateEmbauche);
			System.out.println(id);
			
			}
			
		}catch (SQLException e){
			
			e.printStackTrace();
		}
	
	return user;
	}
}
