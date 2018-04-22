package dao;

import java.sql.Connection;
import java.sql.DriverManager;



public class DAO {
	
	static Connection laConnection;
	
	
	public DAO()
	{
	 try 
	 {
		//connection au driver jdbc
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 
		 
		 //connection à la BDD 
		 DAO.laConnection= DriverManager.getConnection("jdbc:mysql://localhost/applibancaire", "root","");

		 System.out.println("Connexion ok");
		 
	 }
	 catch(Exception e) 
	 {
		 System.out.println("Connexion fail");
		 System.out.println(e.getMessage());
	 }
		
		
	}
	
	//vérifie s'il n'y a pas déjà des connections et puis ensuite établie une connection 
	public static Connection initConnection(){
		
		if(laConnection==null) 
		{
			new DAO();
		}
		return laConnection;
	}
		
}
	

