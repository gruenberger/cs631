package registrar;

public class CourseException extends Exception {
	

	public CourseException(){
		//Empty Constructor
	}
	
	public void message(){
		System.out.println("This course is full or you're already registered for it.");
	}
	
}
