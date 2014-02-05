package project1;

public class Booking {
	
	private Flight flight;
	private Seat seat;
	private Customer customer;
	
	public Booking(Flight flight, Seat seat, Customer customer){
		this.flight = flight;
		this.seat = seat;
		this.customer = customer;
	}
	
	public Flight getFlight(){
		return flight;
	}

	public Seat getSeat(){
		return seat;
	}
	
	public Customer getCustomer(){
		return customer;
	}
}