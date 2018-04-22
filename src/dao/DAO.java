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
		 
		 
		 //connection � la BDD 
		 DAO.laConnection= DriverManager.getConnection("jdbc:mysql://localhost/applibancaire", "root","");

		 System.out.println("Connexion ok");
		 
	 }
	 catch(Exception e) 
	 {
		 System.out.println("Connexion fail");
		 System.out.println(e.getMessage());
	 }
		
		
	}
	
	//v�rifie s'il n'y a pas d�j� des connections et puis ensuite �tablie une connection 
	public static Connection initConnection(){
		
		if(laConnection==null) 
		{
			new DAO();
		}
		return laConnection;
	}
		
}
	

