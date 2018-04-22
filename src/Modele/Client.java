package Modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Client {

    private StringProperty numClt;
    private StringProperty nomClt;
    private StringProperty prenomClt;
    private StringProperty villeClt;
    private StringProperty telClt;
    private StringProperty codePostalClt;
    
    /**
     * Default constructor.
     */
    public Client() {
        
    }
    
    


	public Client(StringProperty numClt, StringProperty nomClt, StringProperty prenomClt, StringProperty villeClt,StringProperty telClt, StringProperty codePostalClt) {
		super();
		this.numClt = numClt;
		this.nomClt = nomClt;
		this.prenomClt = prenomClt;
		this.villeClt = villeClt;
		this.telClt=telClt;
		this.codePostalClt= codePostalClt;
		
	}




	public Client(String numClt, String nomClt, String prenomClt, String villeClt, String telClt, String codePostalClt) {
		super();
		this.numClt = new SimpleStringProperty(numClt);
		this.nomClt = new SimpleStringProperty(nomClt);
		this.prenomClt = new SimpleStringProperty(prenomClt);
		this.villeClt = new SimpleStringProperty(villeClt);
		this.telClt= new SimpleStringProperty(telClt);
		this.codePostalClt= new SimpleStringProperty(codePostalClt);
	}
	
	public Client(String numClt, String nomClt, String prenomClt, String villeClt, String codePostalClt) {
		super();
		this.numClt = new SimpleStringProperty(numClt);
		this.nomClt = new SimpleStringProperty(nomClt);
		this.prenomClt = new SimpleStringProperty(prenomClt);
		this.villeClt = new SimpleStringProperty(villeClt);
		this.codePostalClt= new SimpleStringProperty(codePostalClt);
		
	}

//------------------------------------------------------------------------------------------------------------

	public String getNumClt() {
		return numClt.get();
	}


	public void setNumClt(String numClt) {
		this.numClt.set(numClt);
	}
	
	public StringProperty numClt(){
		
		return numClt;
	}
//-------------------------------------------------------------------------------------------------------------

	public String getNomClt() {
		return nomClt.get();
	}


	public void setNomClt(String nomClt) {
		this.nomClt.set(nomClt);
	}
	
	public StringProperty nomClt(){
		
		return nomClt;
	}

//------------------------------------------------------------------------------------------------------------
	

	public String getPrenomClt() {
		return prenomClt.get();
	}


	public void setPrenomClt(String prenomClt) {
		this.prenomClt.set(prenomClt);
	}
	
	public StringProperty prenomClt (){
		
		return prenomClt;
	}

//-------------------------------------------------------------------------------------------------------

	public String getVilleClt() {
		return villeClt.get();
	}


	public void setVilleClt(String villeClt) {
		this.villeClt.set(villeClt);
	}
	
	public StringProperty villeClt(){
		return villeClt;
	}
	
//------------------------------------------------------------------------------------------------------
	
	public String getTelClt(){
		
		return telClt.get();
	}

	public void setTelClt(String telClt){
		
		this.telClt.set(telClt);
	}
	
	public StringProperty telClt(){
		
		return telClt;
	}

	
//-----------------------------------------------------------------------------------------------------------------------
	
	public String getCodePostal(){
		
		return codePostalClt.get();
	}
	
	public void setCodePostal(String codePostal){
		
		this.codePostalClt.set(codePostal);
		
	}
	
	public StringProperty codePostal(){
		
		return codePostalClt;
	}
	
    

    
    
}