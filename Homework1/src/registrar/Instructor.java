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
	
	//Method to get Name
	public String getName(){
		return name;
	}
	
	//Method to get ID number
	public int getId(){
		return idNum;
	}
	
	//Method for equals
	@Override
	public boolean equals(Object compTo){
		if(compTo == null || !(compTo instanceof Instructor)){
			return false;
		}
		if(this == compTo){
			return true;
		}
		//Setting another variable so that I do not
		//have to continue to cast it.
		Instructor other = (Instructor)compTo;
		
		//comparing all of the private variables
		if(other.getName().equals(this.name)
			&& other.getId() == this.idNum
			&& other.getDept() == this.department){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	

}
