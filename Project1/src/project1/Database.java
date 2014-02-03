package project1;

import java.util.ArrayList;

public class Database {
	
	private ArrayList<Customer> customerList;
	private ArrayList<Flight> flightList;
	
	public Database(){
		customerList = new ArrayList<Customer>();
		flightList = new ArrayList<Flight>();
	}
	
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	
	public ArrayList<Flight> getFlightList(){
		return flightList;
	}
	
	public void addFlight(Flight newFlight) {
		flightList.add(newFlight);
	}
	
	public void addCustomer(Customer newCustomer) {
		customerList.add(newCustomer);
	}
	
	public void removeFlight(Flight remFlight) {
		flightList.remove(remFlight);
	}
	
	public void removeCustomer(Customer remCustomer) {
		customerList.remove(remCustomer);
	}

}
