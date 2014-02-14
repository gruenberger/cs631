package project1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;



public class Customer {

	private int customerID;
	private Name name;
	private String sex;
	private GregorianCalendar birthdate;
	private String seatPref;
	private EmergencyContact contact;
	private ArrayList<Booking> bookings;
	private Database data;
	
	public Customer(int customerID, Name name, String sex, GregorianCalendar birthdate,
			String seatPref, Database data) throws IllegalArgumentException{
		this.customerID = customerID;
		this.name = name;
		this.sex = sex;
		this.birthdate = birthdate;
		this.seatPref = seatPref;
		bookings = new ArrayList<Booking>();
		this.data = data;
		checkID(data);
		writeToDatabase();		
		checkSex(data);
		checkBirthdate(birthdate, data);
	}
	
	public int getCustomerID(){
		return customerID;
	}
	
	public Name getName(){
		return name;
	}
	
	public String getSex(){
		return sex;
	}
	
	public GregorianCalendar getBirthdate(){
		return birthdate;
	}
	
	public String getPref(){
		return seatPref;
	}
	
	public void setEmergencyContact(EmergencyContact c){
		contact = c;
	}
	
	public EmergencyContact getEmergencyContact(){
		return contact;
	}
	
	private void addToBookings(Booking booking){
		bookings.add(booking);
	}
	
	public void checkID(Database data){
		if(data.idCheck(customerID)){
			data.removeCustomer(this);		
			throw new IllegalArgumentException("Customer ID must be unique");
		}
	}
	
	public void checkSex(Database data){
		if(!(sex.equals("male") || sex.equals("female"))){
			data.removeCustomer(this);
			throw new IllegalArgumentException("Sex must be male or Female");
		}
	}
	
	public void checkBirthdate(GregorianCalendar birthday, Database data){
		GregorianCalendar now = new GregorianCalendar();
		if(birthday.after(now)){
			data.removeCustomer(this);
			throw new IllegalArgumentException("Birthday was set to a future date");
		}
	}	
	
	public void seatRequest(Flight flight, Booking specificSeat){
		flight.bookSpecificSeat(specificSeat);
	}
	
	
	public void makeBooking(Booking bookMe){
		bookMe.getFlight().reserveSeat(bookMe);
		addToBookings(bookMe);
	}
	
	public void cancelReservation(Booking cancelRequest){
		cancelRequest.getFlight().cancelBooking(cancelRequest);
		bookings.remove(cancelRequest);
	}
	
	public String toString(){
		return name.toString()+","+sex+","+birthdate.toString()+","+contact.toString();
	}
	
	
	public void writeToDatabase(){
		data.addCustomer(this);
	}
	
	public void displayBookings(){
		Iterator<Booking> i = bookings.iterator();
		
		System.out.println(name.toString());
		System.out.println(sex);
		System.out.println(birthdate.toString());
		System.out.println(seatPref);
		System.out.println("Emergency Contact: "+contact.toString());
		
		System.out.println("All Customer Bookings:");
		
		while(i.hasNext() && bookings != null){
			Booking temp = i.next();
			System.out.println("-----------------------------");
			System.out.println(temp.getFlight().toString());
			System.out.println(temp.getSeat().toString());
			System.out.println("-----------------------------");
		}
	}
}
