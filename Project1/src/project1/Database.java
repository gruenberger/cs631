package project1;

import java.util.ArrayList;
import java.util.Iterator;


//The database here just keeps a list of all flights and customers
//created while the program executes
public class Database {
	
	private ArrayList<Customer> customerList;
	private ArrayList<Flight> flightList;
	
	public Database(){
		customerList = new ArrayList<Customer>();
		//System.out.println(customerList.isEmpty());
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
	
	public boolean idCheck(int id){
		Iterator<Customer> i = customerList.iterator();
		Customer temp;		
				
		while(i.hasNext()){
			System.out.println("in id Check");
			temp = i.next();
			if(temp.getCustomerID()== id)
				return true;
		}
		return false;
	}
	
	public boolean flightCheck(int id){
		Iterator<Flight> i = flightList.iterator();
		Flight temp;		
				
		while(i.hasNext()){
			System.out.println("in id Check");
			temp = i.next();
			if(temp.getFlightNumber()== id)
				return true;
		}
		return false;
		
	}

}
