package Modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeOpe {
	
	private IntegerProperty codeType = new SimpleIntegerProperty();
	private StringProperty libelleType = new SimpleStringProperty();
	


	public TypeOpe(Integer codeType, String libelleType) {
		super();
		this.codeType.set(codeType);
		this.libelleType.set(libelleType);
	}
	
	public TypeOpe(){
		
	}



	public IntegerProperty getCodeType() {
		return codeType;
	}

	public Integer getCode(){
		
		return codeType.get();
		
	}
	
	public void setCodeType(IntegerProperty codeType) {
		this.codeType = codeType;
	}
	
	public void setCode(Integer code){
		
		this.codeType.set(code);
		
	}



	public StringProperty getLibelleType() {
		return libelleType;
	}
	
	public String getLibelle(){
		return libelleType.get();
	}



	public void setLibelleType(StringProperty libelleType) {
		this.libelleType = libelleType;
	}
	
	public void setLibelle(String libelle){
		this.libelleType.set(libelle);
	}
	
	
}
