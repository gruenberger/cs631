package registrar;

import java.io.*;
public class Registrar {

	public static void main(String[] args) {
		
		Student s1 = new Student("Charles Stewart II", 1648);
		Student s2 = new Student("Henry Tudor", 1509);
		Student s3 = new Student("Aethelred the Unready", 1016);
		Student s4 = new Student("Edward the Martyr", 978);
		Student s5 = new Student("Edward Longshanks Plantagenet", 1307);
		Student s6 = new Student("Richard the Lionheart", 1199);
		Student s7 = new Student("William the Conqueror", 1087);
		Student s8 = new Student("Edward the Confessor", 1066);
		Student s9 = new Student("Cnut Forkbeardson", 1035);
		Student s10 = new Student("Alfred the Great", 899);
		
		Course c1 = new Course("Diplomacy", 1);
		Course c2 = new Course("English", 2);
		Course c3 = new Course("Military Science", 3);
		Course c4 = new Course("Public Speaking", 4);
		
//------------Student Class Testing Section-------------------------
		
		//Method, getID Test
		if(s1.getId() == 1648 && s2.getId() == 1509){
			System.out.println("Get Id Works.");
		}else{
			System.out.println("Fix getId()");
		}
		
		if(s1.getId() != 1649 && s2.getId() == 1509){
			System.out.println("Get Id Works.");
		}else{
			System.out.println("Fix getId()");
		}
		
		//Method getName test
		if(s3.getName().equals("Aethelred the Unready"))
			System.out.println("Getname appears to work");
		else
			System.out.println("Fix get name");
		
		if(!(s3.getName().equals("Aethelred the Unreadysssssssssssssssss")))
			System.out.println("Getname appears to work");
		else
			System.out.println("Fix get name");
		
		
		//Method Equals test
		if(s4.equals(new Student("Edward the Martyr", 978)))
			System.out.println("Equals seems to work");
		else
			System.out.println("Equals needs more work");
		
		if(!(s4.equals(new Student("Edward the Martyred", 978))))
			System.out.println("Equals seems to work");
		else
			System.out.println("Equals needs more work");
		
		//Set and get for credits
		s6.setCredits(12);
		if(s6.getCredits() == 12)
			System.out.println("Set and Get credits works");
		else
			System.out.println("Work on set/get for credits");
		
		//Set and get for grade points
		s7.setGpts(8);
		if(s7.getGpts()==8)
			System.out.println("Set and get grade points works.");
		else
			System.out.println("Fix set Grade points method");
		
		//GPA method test area
		s8.setGpts(31);
		s8.setCredits(8);
		if(s8.getGPA() == 3.875)
			System.out.println("The First GPA test worked");
		else
			System.out.println("The First GPA test failed!!");
		
		s8.setGpts(0);
		if(s8.getGPA()==0.0)
			System.out.println("The Second GPA test worked!");
		else
			System.out.println("Second GPA test failed real bad: "+s8.getGpts()+"  " + s8.getGPA());
		
		//CSV format method test
		s9.setCredits(3);
		s9.setGpts(12);
		if(s9.toCSV().equals("Cnut Forkbeardson,1035,3,12")){
			System.out.println("CSV Format Test Worked");
			System.out.println(s9.toCSV());
		}else{
			System.out.println("CSV format test failed!");
			System.out.println(s9.toCSV());
		}
		
		//toString Method Test
		if(s10.toString().equals("Alfred the Great - 899"))
			System.out.println("The to String worked!  "+s10.toString());
		else
			System.out.println("The to String failed:  "+s10.toString());
		
/*--------------------------End Student Testing Section-----------------------------------		
					
	
---------------------------Begin Course Testing Section-----------------------------*/
		
		//Set and Retrieve instructor's name
		Instructor ins1 = new Instructor("Bismarck, The Blood and Iron Chancellor");
		c1.setInstructor(ins1);
		if(c1.getInstructor().equals(ins1))
			System.out.println("The set and get instructor Method test passed");
		else
			System.out.println("The set and get instructor method needs work");
		
		//Populating course2 with students and error catching
		try {
			c2.addStudent(s1);
			c2.addStudent(s5);
			c2.addStudent(s8);
			c2.addStudent(s2);
			c2.addStudent(s6);
			c2.addStudent(s9);
			c2.addStudent(s3);
			c2.addStudent(s7);
			c2.addStudent(s10);
			c2.addStudent(s4);
			System.out.println("There is/are now "+c2.getNumStudents()+" student(s) in the class.");
			
			//At this point, there are 10 students in c2's student array
			//I will make a loop to fill up the array with identical dummies
			
			for(int ind=10;ind < 35; ind++){
				c2.addStudent(new Student("Dummie",0000));
				System.out.println(c2.getNumStudents());
				
			}
		} catch (CourseException e) {
			
			e.message();
		}
		try{//Adding an identical just one more time
			Student identical = new Student("Cnut Forkbeardson", 1035);
			
			c2.addStudent(identical);
			
		} catch (CourseException f) {
			f.message();
		}
		
		if(c2.findStudent(s4)){
			System.out.println("I have found that, "+s4.getName()+" is already in this course.");
		}else{
			System.out.println("The find student method doesn't work.");
		}
		
		c2.setInstructor(ins1);
		System.out.println(c2.toString());
		
	//-------------- Testing Serialization of a Course------------------------	
		try{
			
			ObjectOutputStream ooStream = new ObjectOutputStream(
					new FileOutputStream("Course.dat"));
			
			ooStream.writeObject(c2);
			ooStream.close();			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//At this point, the object should be serialized and stored
		//So we will try to read in the object to a different variable
		Course serialTest = null;
		
		try{			
			ObjectInputStream ioStream = new ObjectInputStream(
					new FileInputStream("Course.dat"));
			serialTest = (Course)ioStream.readObject();
			ioStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException f){
			f.printStackTrace();
		}
		
		//Hopefully this worked and now, the Course reference variable
		//"serialTest" is the same as c1's object.  I will do a toString
		System.out.println(serialTest.toString());
		
		//Tests the Remove Student method
		try{
			c2.remove(s1);
		}catch(CourseException e){
			e.printStackTrace();
		}
		
		//Testing the creation of the student list to a CSV
		//formatted file, with a \n after each student entry
		try{
			c2.makeStudentFile();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//Testing the read CSV file
		Course testFileRead = new Course("Economics", 345);
		try {
			testFileRead.readStudentFile("Students.dat");
		} catch (IOException | CourseException e) {
			// 
			e.printStackTrace();
		}
		
		testFileRead.printStudents();
	}
}
