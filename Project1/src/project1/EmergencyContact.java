package project1;

public class EmergencyContact {
	
	private Name name;
	private int phoneNumber;
	
	
	public EmergencyContact(Name name, int phoneNumber){
		this.name = name;
		this.phoneNumber = phoneNumber;
		
	}
	public Name getName() {
		return name;
	}
	
	public String toString(){
		return name.toString()+" "+phoneNumber;
	}
}
