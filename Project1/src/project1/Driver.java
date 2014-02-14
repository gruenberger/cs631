package project1;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Driver {

	public static void main(String[] args) {
		Database database = new Database();
		EmergencyContact emergency = new EmergencyContact(new Name("Emer","Gency"),911);
		
		GregorianCalendar date1 = new GregorianCalendar(2000, Calendar.JULY, 4);
		GregorianCalendar date2 = new GregorianCalendar(1968, Calendar.JUNE, 8);
		
		Customer customer1 = new Customer(123, new Name("John","Doe"), "male", date2, "window", database);
		Customer customer2 = new Customer(456, new Name("Joan","Johnsdotter"), "female", date1, "aisle", database);
		
		GregorianCalendar flightDep = new GregorianCalendar(2014, Calendar.JULY, 4, 13, 30, 45);
		GregorianCalendar flightArr = new GregorianCalendar(2014, Calendar.JULY, 5, 13, 30, 45);
		
		Flight flight1 = new Flight(1, "New York", "Hong Kong", flightDep, flightArr, database);
		Flight flight2 = new Flight(2, "Hong Kong", "New York", flightDep, flightArr, database);
		
		customer1.setEmergencyContact(emergency);
		customer2.setEmergencyContact(emergency);
		
		Booking booking1 = new Booking(flight1, customer1);
		Booking booking2 = new Booking(flight1, customer2);
		Booking booking3 = new Booking(flight2, customer1);
		Booking booking4 = new Booking(flight2, customer2);
		
		customer1.makeBooking(booking1);
		customer1.makeBooking(booking3);
		customer2.makeBooking(booking2);
		customer2.makeBooking(booking4);
		
		customer1.displayBookings();
		customer2.displayBookings();

	}

}
