package project1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;



public class Customer {

	private SimpleDateFormat birthFormat = new SimpleDateFormat("dd-MM-yy");
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
	//Bookings are maintained in an ArrayList
	private void addToBookings(Booking booking){
		bookings.add(booking);
	}
	//This checks the database of all customers created in the flight system
	public void checkID(Database data){
		if(data.idCheck(customerID)){
			data.removeCustomer(this);		
			throw new IllegalArgumentException("Customer ID must be unique");
		}
	}
	//This is an example of how the validity is checked when creating a customer
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
	//The change seat uses the original booking and makes a shallow copy of it
	//in case the change can't be processed.
	public void changeSeat(Booking y, Seat x){
		Booking change = bookings.get(bookings.indexOf(y));
		Booking temp = new Booking(change.getFlight(),change.getCustomer());
		
		cancelReservation(change);
		change.setSeat(x);
		
		try{
			change.getFlight().bookSpecificSeat(change);
		}catch(IllegalArgumentException e){
			System.out.println("Seat was occupied, resetting to original assignment");
			makeBooking(temp);
		}
	}
		
	
	public String toString(){
		return name.toString()+","+sex+","+birthFormat.format(birthdate.getTime())+","+contact.toString();
	}
	
	
	private void writeToDatabase(){
		data.addCustomer(this);
	}
	
	public void displayBookings(){
		
		Iterator<Booking> i = bookings.iterator();
		
		System.out.println(name.toString());
		System.out.println(sex);
		System.out.println(birthFormat.format(birthdate.getTime()));
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
