package registrar;

import java.io.Serializable;

public class Course implements Serializable{

	private String name;
	private int regCode;
	private final int MAX_STUDENTS = 35;
	private Instructor instructor;
	private int numStudents;
	private Student[] students;

	public Course(String inName, int code){
		this.name = inName;
		this.regCode = code;
		this.numStudents =0;
		this.students = new Student[MAX_STUDENTS];
	}

	public void setInstructor(Instructor instName){
		instructor = instName;
	}

	public Instructor getInstructor(){
		return instructor;
	}

	public void addStudent(Student newStudent) throws CourseException {

		if(numStudents == MAX_STUDENTS)
			throw new CourseException();
		else if(findStudent(newStudent))
			throw new CourseException();
		else{
			numStudents++;
			students[numStudents-1]= newStudent;
		}		
	}

	public int getNumStudents(){
		return numStudents;
	}

	//Returns boolean for student enrollment in course
	public boolean findStudent(Student qStudent){
		if(numStudents==0)
			return false;
		for(int index =0; index < numStudents; index++){
			if(students[index].equals(qStudent))
				return true;
		}		
		return false;
	}

	//Similar to previous findStudent, but returns the object
	//based on a given student id number
	public Student findStudentByID(int givenID){		
		for(int index =0; index < numStudents; index++){
			if(students[index].getId() == givenID)
				return students[index];
		}

		System.out.println("Student not found, returning Dummie Student");
		return new Student("DUMMIE", 0);
	}

	public String toString(){
		return "-------------------------------------\n"+
				"This is a brief Course Description:\n"+
				"Course: "+name+"\n"+
				"Instructor: "+instructor.getName()+"\n"+
				"Number of Students: "+numStudents+"\n"+
				"Registration Number: "+regCode+"\n"+
				"-------------------------------------\n";
	}
	//Remove a Student from the Course
	//Make sure student exists, then modify the Student array
	public void remove(Student removeMe) throws CourseException{
		if(findStudent(removeMe)){
			Student temp;
			int ind=getIndex(removeMe);
			int count=0;
			Student[] newList = new Student[35];

			//printStudents();//Prints current list for debugging purposes

			for(int i=0; i<students.length; i++){
				if(i==ind){
					System.out.println("Student "+removeMe.toString()+" removed.");
				}else{
					newList[count]=students[i];
					count++;
				}
			}
			this.students = newList;
		}else{
			throw new CourseException();
		}

		//printStudents();//Prints list after for removal debugging purposes
	}

	private int getIndex(Student x){
		int ind=0;
		for(int i=0; i<students.length;i++){
			if(students[i].equals(x)){
				ind=i;
				return ind;
			}
		}
		return 0;//return 0 if not found, in reality, the program will never execute this line.		
	}

	public void printStudents(){
		int i=0;
		while(students[i] != null && i<=35){
			System.out.println(students[i].toString());
			i++;
		}
	}
}
