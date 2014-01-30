package registrar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
	
	private static int currChoice=1;//set initial value
	private static Course currCourse;
	

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(currChoice != 5 && currChoice > 0 && 
				currChoice < 6){
			printMain();
			System.out.println("Enter Selection: ");
			currChoice = reader.nextInt();
			evalChoice(currChoice);
		}

	}
	//This is the main menu print out
	private static void printMain(){
		System.out.println("\n\n\n---Main Menu---");
		System.out.println("1. Create Course");
		System.out.println("2. Add Student to the Course");
		System.out.println("3. Check for a Student");
		System.out.println("4. Remove a Student");
		System.out.println("5. Exit");
	}
	
	//gathers fields for constructors of a course
	private static void createCourse(){
		Scanner reader = new Scanner(System.in);
		String courseName = "";
		int regCode=0;
		Instructor inst;
		
		System.out.println("---Create New Course---");
		System.out.println("Enter the Course name: ");
		courseName = reader.nextLine();
		System.out.println("Enter the registration Code: ");
		regCode = reader.nextInt();
		
		Course crs = new Course(courseName,regCode);
		
		crs.setInstructor(setInst());
		currCourse = crs;
		System.out.println("Course created!");
	}
	
	//This method prompts for all parts of student constructor
	//Then it adds it to the student[] of the course
	private static void addStudent(){
		Scanner reader = new Scanner(System.in);
		String name = "";
		int credits,gpts,id;
		
		System.out.println("Please enter the Student's name: ");
		name = reader.nextLine();
		System.out.println("Please enter student id: ");
		id = reader.nextInt();
		System.out.println("Please enter student credits: ");
		credits = reader.nextInt();
		System.out.println("Please enter student grade points: ");
		gpts = reader.nextInt();
		
		Student newStud = new Student(name,id,credits,gpts);
		try {
			currCourse.addStudent(newStud);
			System.out.println("Student Added!");
		} catch (CourseException e) {
			e.printStackTrace();
		}
	}
	
	//Uses the fields to check for a student
	//No specifications were given on how to find the student
	//So my method uses the student id, which is more precise
	//in a real world situation-- I think.
	private static void studentCheck(){
		Scanner reader = new Scanner(System.in);
		int idNum;
		boolean check;
		
		System.out.println("Please enter student ID number: ");
		idNum = reader.nextInt();
		check = currCourse.findStudent(currCourse.findStudentByID(idNum));
		
		if(check)
			System.out.println("Student is enrolled.");
		else
			System.out.println("Student not enrolled.");
	}
	
	//Gets rid of the studs...students.. one by one
	//based on the given student id number
	private static void removeStud(){
		Scanner reader = new Scanner(System.in);
		int idNum;
		boolean check;
		
		System.out.println("Please enter student ID number: ");
		idNum = reader.nextInt();
		try {
			currCourse.remove(currCourse.findStudentByID(idNum));
		} catch (CourseException e) {			
			e.printStackTrace();
		}
		
		
	}
	//This gets the instructor for the course
	//it's inside of the course builder method
	private static Instructor setInst(){
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Enter the name of the instructor: ");
		String instName="";
		instName = reader.nextLine();
		Instructor inst = new Instructor(instName);
		
		return inst;
	}
	
	//I thought it would be novel to use a switch statement
	//This is kind of like the operator or real driver
	private static void evalChoice(int choice){
		switch (choice) {
			case 1:		createCourse();
						break;
			case 2:		addStudent();
						break;
			case 3:		studentCheck();
						break;
			case 4:		removeStud();
						break;
			case 5:		System.out.println("Exiting now...");
		}
		
	}

}
