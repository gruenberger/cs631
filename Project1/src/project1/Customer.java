package project1;

import java.util.ArrayList;
import java.*;


public class Customer {

	private String customerID;
	private Name name;
	private String sex;
	private int birthdate;
	private String seatPref;
	private EmergencyContact contact;
	private ArrayList<Booking> bookings;
	
	public Customer(String customerID, Name name, String sex, int birthdate,
			String seatPref) throws IllegalArgumentException{
		this.customerID = customerID;
		this.name = name;
		this.sex = sex;
		this.birthdate = birthdate;
		this.seatPref = seatPref;
		bookings = new ArrayList<Booking>();
	}
	
	public String getCustomerID(){
		return customerID;
	}
	
	public Name getName(){
		return name;
	}
	
	public String getSex(){
		return sex;
	}
	
	public int getBirthdate(){
		return birthdate;
	}
	
	public void setEmergencyContact(EmergencyContact c){
		contact = c;
	}
	
	public EmergencyContact getEmergencyContact(){
		return contact;
	}
	
	public void addToBookings(Booking booking){
		bookings.add(booking);
	}
	
	public void checkID(){
		
	}
	
	public void checkSex(){
		
	}
	
	public void checkBirthdate(){
		
	}
	
	public void setContact(){
		
	}
	
	@Override
	public String toString(){
		return null;
	}
	
	public void seatRequest(Seat request){
		
	}
	
	public void changeSeat(Seat changeRequest){
		
	}
	
	public void makeBooking(Flight bookRequest){
		
	}
	
	public void cancelReservation(Booking cancelRequest){
		
	}
	
	
	public void writeToDatabase(){
		
	}
}
