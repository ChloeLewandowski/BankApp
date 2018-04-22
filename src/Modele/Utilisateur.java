package Modele;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {
	
	private IntegerProperty idUtil;
	private StringProperty loginUtil;
	private StringProperty nomUtil;
	private StringProperty prenomUtil;
	private SimpleObjectProperty<LocalDate> dateEmbauche= new SimpleObjectProperty<LocalDate>();
	
//Constructeur par défaut 
	public Utilisateur(){
		
		
	}
	
//-------------------------------------------------------------------------------------------------
	public Utilisateur(IntegerProperty idUtil, StringProperty loginUtil, StringProperty nomUtil, StringProperty prenomUtil, SimpleObjectProperty<LocalDate> dateEmbauche){
	
		this.idUtil=idUtil;
		this.loginUtil=loginUtil;
		this.nomUtil=nomUtil;
		this.prenomUtil=prenomUtil;
		this.dateEmbauche= dateEmbauche;
		
		
	}
	
//--------------------------------------------------------------------------------------------------
	public Utilisateur(Integer idUtil, String loginUtil, String nomUtil, String prenomUtil, LocalDate dateEmbauche){
		
		this.idUtil=new SimpleIntegerProperty(idUtil);
		this.loginUtil= new SimpleStringProperty(loginUtil);
		this.nomUtil=new SimpleStringProperty(nomUtil);
		this.prenomUtil= new SimpleStringProperty(prenomUtil);
		this.dateEmbauche= new SimpleObjectProperty<LocalDate>(dateEmbauche);
		
	}
//-------------------------------------------------------------------------------------------------
	public IntegerProperty getIdUtil() {
		return idUtil;
	}
	
	public Integer idUtil(){
		
		return idUtil.get();
	}

	
	
	public void setIdUtil (Integer idUtil){
		
		this.idUtil.set(idUtil);
	}
	
//--------------------------------------------------------------------------------------------------

	public StringProperty getLoginUtil() {
		return loginUtil;
	}
	
	public String loginUtil(){
		
		return loginUtil.get();
	}

	public void setLoginUtil(String loginUtil) {
		this.loginUtil.set(loginUtil);
	}
//---------------------------------------------------------------------------------------------------
	
	public StringProperty getNomUtil() {
		return nomUtil;
	}
	
	public String nomUtil(){
		
		return nomUtil.get();
	}

	public void setNomUtil(String nomUtil) {
		this.nomUtil.set(nomUtil);
	}
	
//--------------------------------------------------------------------------------------------------

	public StringProperty getPrenomUtil() {
		return prenomUtil;
	}
	
	public String prenomUtil(){
		
		return prenomUtil.get();
	}

	public void setPrenomUtil(String prenomUtil) {
		this.prenomUtil.set(prenomUtil);
	}
	
//----------------------------------------------------------------------------------------------------
	public SimpleObjectProperty<LocalDate> getDateEmbauche() {
		return dateEmbauche;
	}
	
	public LocalDate dateEmbauche(){
		
		return dateEmbauche.get();
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche.set(dateEmbauche);
	}
}
