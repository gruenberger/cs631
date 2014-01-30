package registrar;

import java.io.Serializable;

public class Student extends Person implements Serializable{
	
	
	private static final long serialVersionUID = 8031944245066345758L;
	private String name;
	private int id;
	private int credits;
	private int gpts;
	
	public Student(String inName, int inId){
		this.name = inName;
		this.id = inId;
		this.credits = 0; //Set to "default values"
		this.gpts = 0; //Set to "default values"
	}
	
	public Student(String inName, int inId, int creds, int grpts){
		this.name = inName;
		this.id = inId;
		this.credits = creds;
		this.gpts = grpts;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	@Override
	public boolean equals(Object compTo){
		if(compTo == null || !(compTo instanceof Student))
			return false;
		
		if(((Student) compTo).getName().equals(name)
			&& ((Student)compTo).getId() == id
			&& ((Student)compTo).getCredits() == credits
			&& ((Student)compTo).getGpts() == getGpts()){
			return true;
		}else{
			return false;
		}
	}
	
	public void setCredits(int creds){
		this.credits=creds;
	}
	
	public int getCredits(){
		return credits;
	}
	
	public void setGpts(int moreGpts){
		this.gpts = moreGpts;
	}
	
	public int getGpts(){
		return gpts;
	}
	
	public double getGPA(){
		if(gpts ==0)
			return 0.0;
		else		
			return (double)gpts/credits;
	}
	
	public String toCSV(){
		return name+","+id+","+credits+","+gpts;
	}
	@Override	
	public String toString(){
		return name+" - "+id;
	}
}
