package project1;

public class Seat {
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
		this.booking = newBooking;
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
	

}
