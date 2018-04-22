package Modele;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Compte{
	
	private StringProperty numCpte;
	private SimpleObjectProperty<LocalDate> datecreationCpte= new SimpleObjectProperty<LocalDate>();
	private StringProperty soldeCpte;
	private Client cli= new Client();
	
	
	
	public Compte(){
	
	}


	public Compte(StringProperty numCpte, SimpleObjectProperty<LocalDate> datecreationCpte, StringProperty soldeCpte, Client cli) {
		super();
		this.numCpte = numCpte;
		this.datecreationCpte = datecreationCpte;
		this.soldeCpte = soldeCpte;
		this.cli=cli;
	}
	
	

	public Compte(String numCpte, LocalDate datecreationCpte, String soldeCpte, Client cli) {
		super();
		this.numCpte = new SimpleStringProperty(numCpte);
		this.datecreationCpte = new SimpleObjectProperty<LocalDate>(datecreationCpte);
		this.soldeCpte = new SimpleStringProperty(soldeCpte);
		this.cli= cli;
	}

//------------------------------------------------------------------------------------------------------
	public String getNumCpte() {
		return numCpte.get();
	}
	
	public StringProperty numCpte(){
		
		return numCpte;
	}


	public void setNumCpte(String numCpte) {
		this.numCpte.set(numCpte);
	}

//--------------------------------------------------------------------------------------------------------
	
	public LocalDate getDatecreationCpte() {
		return datecreationCpte.get();
	}
	
	public ObjectProperty<LocalDate> dateCreationCpte(){

		return datecreationCpte;
	}


	public void setDatecreationCpte(LocalDate datecreationCpte) {
		this.datecreationCpte.set(datecreationCpte);
	}
	
//----------------------------------------------------------------------------------------------------------	


	public String getSoldeCpte() {
		return soldeCpte.get();
	}
	
	public StringProperty soldeCpte(){
		
		return soldeCpte;
	}


	public void setSoldeCpte(String soldeCpte) {
		this.soldeCpte.set(soldeCpte);
	}

//-------------------------------------------------------------------------------------------------------------
	public Client getCli() {
		return cli;
	}


	public void setCli(Client cli) {
		this.cli = cli;
	}
	
//-------------------------------------------------------------------------------------------------------------
	
	
		
	
	
	

}