package project1;

public class Seat implements Comparable {
	private char seat;
	private String type;
	private int row;	
	private Booking booking;
	private boolean isExtraSpaceSeat = false;
	private boolean isOccupied = false;
	
	public Seat(char inSeat, String inType, int inRow){
		seat = inSeat;
		type = inType;
		row = inRow;				
	}
	
	
	public char getSeat() {
		return seat;
	}
	
	public int getRow() {
		return row;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Booking getBooking() {
		return booking;
	}
	public void bookSeat(Booking newBooking) {
		booking = newBooking;
		isOccupied = true;
	}
	public boolean isExtraSpaceSeat() {
		return isExtraSpaceSeat;
	}
	public void setExtraSpaceSeat(boolean isExtraSpaceSeat) {
		this.isExtraSpaceSeat = isExtraSpaceSeat;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	
	public boolean equals(Seat check) {
		if(check.getRow() == row && check.getSeat() == seat && check.getType().equals(type))
			return true;
		else
			return false;
	}
	//The row+1 is there to create understandable output (so there is no row 0)
	public String toString(){
		return "Row: "+(row+1)+" Seat: "+seat+" Type: "+type;
	}
	
	//The cancel method removes references to the booking and flags it as
	//unoccupied
	public void cancellation(){
		System.out.println("Removing booking association and resetting seat to vacant");
		booking = null;
		isOccupied = false;
	}

	//In order to print out an alphabetically ordered list, I wanted to use an arrayList sort function
	//and that requires me to implement the comparable interface, which was not too difficult.
	//Basically it is a way to set the order that you want.  In this case it uses the string's
	//compareTo method so that I can sort it all out by customer last name.
	@Override
	public int compareTo(Object that) {
		if(booking.getCustomer().getName().getLast().compareTo(((Seat) that).getBooking().getCustomer().getName().getLast())
				<= -1)			
			return -1;
		else if(booking.getCustomer().getName().getLast().compareTo(((Seat) that).getBooking().getCustomer().getName().getLast())
				== 0)
			return 0;
		else if(booking.getCustomer().getName().getLast().compareTo(((Seat) that).getBooking().getCustomer().getName().getLast())
				>= 1)
		return 1;
		
		return 0;
	}

}
