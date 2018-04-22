package Modele;

import java.time.LocalDate;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Operations {

	private StringProperty numOpe = new SimpleStringProperty();
	private FloatProperty montantOpe= new SimpleFloatProperty();
	private SimpleObjectProperty<LocalDate> dateOpe= new SimpleObjectProperty();
	private IntegerProperty codeType= new SimpleIntegerProperty();
	

	
	public Operations() {
		
		
	}


	//parameterized constructor 
	public Operations(String codeOpe, Float totalOpe, LocalDate dateOp, Integer codeType) {
		
		this.numOpe.set(codeOpe);
		this.montantOpe.set(totalOpe);
		this.dateOpe.set(dateOp);
		this.codeType.set(codeType);
	
		
	}

	public String getNumOpe() {
		return numOpe.get();
	}

	public void setNumOpe(String numOpe) {
		this.numOpe.set(numOpe);
	}
	
	public StringProperty numOpe(){
		
		return numOpe;
	}

	public Float getMontantOpe() {
		return montantOpe.get();
	}

	public void setMontantOpe(Float montantOpe) {
		this.montantOpe.set(montantOpe);
	}
	
	public FloatProperty montantOpe(){
		
		return montantOpe;
	}

	public LocalDate getDateOpe() {
		return dateOpe.get();
	}

	public void setDateOpe(LocalDate dateOpe) {
		this.dateOpe.set(dateOpe);
	}
	
	public ObjectProperty<LocalDate> dateOpe()
	{
		return dateOpe;
	}

	public Integer getCodeType() {
		return codeType.get();
	}

	public void setcodeOpe(Integer codeType) {
		this.codeType.set(codeType);
	}
	
	public IntegerProperty codeType(){
		
		return codeType;
	}
	
	
	
	
	}

	
	
	
	
	

