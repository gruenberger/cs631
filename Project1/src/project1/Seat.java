package project1;

public class Seat {
	private char label;
	private String type;
	private int row;
	private int column;
	private Customer customer;
	private boolean isExtraSpaceSeat = false;
	private boolean isOccupied = false;
	
	public Seat(char inLabel, String inType, int inRow, int inColumn){
		label = inLabel;
		type = inType;
		row = inRow;
		column = inColumn;		
	}
	
	
	public char getLabel() {
		return label;
	}	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
