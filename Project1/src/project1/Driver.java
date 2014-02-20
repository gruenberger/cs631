/*
 * Andrew Gruenberger
 * CS631
 * 
 * This still needs a bit more work.  There are a few tweaks I need to make.
 * Along with some commenting.  I don't think it's terribly tricky to follow along.
 */
package project1;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Driver {

	public static void main(String[] args) {
		Database database = new Database();
		EmergencyContact emergency = new EmergencyContact(new Name("Emer","Gency"),911);
		
		GregorianCalendar date1 = new GregorianCalendar(2000, Calendar.JULY, 4);
		GregorianCalendar date2 = new GregorianCalendar(1968, Calendar.JUNE, 8);
		GregorianCalendar date3 = new GregorianCalendar(1962, Calendar.APRIL, 5);
		
		Customer customer1 = new Customer(123, new Name("John","Doe"), "male", date2, "window", database);
		Customer customer2 = new Customer(456, new Name("Joan","Johnsdotter"), "female", date1, "aisle", database);
		Customer customer3 = new Customer(789, new Name("Joanne","Aardvark"), "female", date3, "middle", database);
		Customer customer4 = new Customer(987, new Name("Jackson","Bramblewood"), "male", date1, "none", database);
		
		GregorianCalendar flightDep = new GregorianCalendar(2014, Calendar.JULY, 4, 13, 30, 45);
		GregorianCalendar flightArr = new GregorianCalendar(2014, Calendar.JULY, 5, 13, 30, 45);
		
		Flight flight1 = new Flight(1, "New York", "Hong Kong", flightDep, flightArr, database);
		Flight flight2 = new Flight(2, "Hong Kong", "New York", flightDep, flightArr, database);
		
		
		customer1.setEmergencyContact(emergency);
		customer2.setEmergencyContact(emergency);
		customer3.setEmergencyContact(emergency);
		customer4.setEmergencyContact(emergency);
		
		Booking booking1 = new Booking(flight1, customer1);
		Booking booking2 = new Booking(flight1, customer2);
		Booking booking3 = new Booking(flight2, customer1);
		Booking booking4 = new Booking(flight2, customer2);
		Booking booking5 = new Booking(flight1, customer3);
		Booking booking6 = new Booking(flight2, customer3);
		Booking booking7 = new Booking(flight1, customer4);
		Booking booking8 = new Booking(flight2, customer4);
		
		customer1.makeBooking(booking1);
		customer1.makeBooking(booking3);
		customer2.makeBooking(booking2);
		customer2.makeBooking(booking4);
		customer3.makeBooking(booking5);
		customer3.makeBooking(booking6);
		customer4.makeBooking(booking7);
		customer4.makeBooking(booking8);
		
		customer1.displayBookings();
		customer2.displayBookings();
		customer3.displayBookings();
		customer4.displayBookings();
		
		try {
			flight1.writePassengerList();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		customer4.cancelReservation(booking8);
		
		customer4.displayBookings();
		
		try{
			flight2.writePassengerList();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try{
			Flight flight3 = new Flight(1, "New York", "New York", flightDep, flightArr, database);
		} catch(IllegalArgumentException e){
			System.out.println("caught the same city constructor error");
		}
		
		try{
		Flight flight4 = new Flight(1, "New York", "Bombay",  flightArr, flightDep, database);
		} catch(IllegalArgumentException e){
			System.out.println("Caught the incorrect time constructor error");
		}
		
		try{
			Seat x = new Seat('a',"window",0);
			customer2.changeSeat(booking2, x);
		} catch (IllegalArgumentException a){
			System.out.println("The seat was occupied.  Change worked as expected.");
		}
	}

}
