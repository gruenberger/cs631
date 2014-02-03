package project1;

public class Flight {
	
	private int flightNumber;
	private String departCity;
	private String arriveCity;
	private int departDate;
	private int departTime;
	private int arriveDate;
	private int arriveTime;
	private Seat[][] seats = new Seat[20][6];
	
	
	
	public int getFlightNumber() {
		return flightNumber;
	}
	
	public String getDepartCity() {
		return departCity;
	}
	
	public String getArriveCity() {
		return arriveCity;
	}
	
	public int getDepartDate() {
		return departDate;
	}

	public int getDepartTime() {
		return departTime;
	}
	
	public int getArriveTime() {
		return arriveTime;
	}
	
	public Seat[][] getSeats() {
		return seats;
	}
}
