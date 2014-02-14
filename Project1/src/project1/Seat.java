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
	
	public String toString(){
		return "Row: "+row+" Seat: "+seat+" Type: "+type;
	}
	
	public void cancellation(){
		booking = null;
		isOccupied = false;
	}


	@Override
	public int compareTo(Object that) {
		if(booking.getCustomer().getName().getLast().compareTo(((Seat) that).getBooking().getCustomer().getName().getLast())
				== -1)			
			return -1;
		else if(booking.getCustomer().getName().getLast().compareTo(((Seat) that).getBooking().getCustomer().getName().getLast())
				== 0)
			return 0;
		else if(booking.getCustomer().getName().getLast().compareTo(((Seat) that).getBooking().getCustomer().getName().getLast())
				== 0)
		return 0;
		
		return 0;
	}

}
