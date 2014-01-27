package registrar;

public class Instructor {
	
	private String name;
	private int idNum;
	private String department;
	
	public Instructor(){
		this.name = "Not Specified";
		this.idNum = 0;
		this.department = "Not Specified";		
	}
	
	public Instructor(String newInstructor){
		this.name = newInstructor;
		this.idNum = 0;
		this.department = "Not Specified";
	}
	
	public Instructor(String newInstructor, int num){
		this.name = newInstructor;
		this.idNum = num;
		this.department = "Not Specified";
	}
	
	//Method to Set Department
	public void setDept(String givenDept){
		this.department = givenDept;
	}
	
	//Method to Get Department
	public String getDept(){
		return department;
	}
	
	

}
