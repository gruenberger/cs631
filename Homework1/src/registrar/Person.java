package registrar;

import java.io.Serializable;

public class Person implements Serializable{
	
	private String name;
	private int id;
	
	public Person(){
		this.name = "Unknown";
		this.id = 0;
	}
	
	public Person(String inputName,int inputId){
		this.name = inputName;
		this.id = inputId;
	}
	
	public String toString(){
		return name+", "+id;
	}

}
