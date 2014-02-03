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
	
	
	
	public Flight(int flightNumber, String departCity, String arriveCity,
			int departDate, int departTime, int arriveDate, int arriveTime) 
					throws IllegalArgumentException {
		
		this.flightNumber = flightNumber;
		this.departCity = departCity;
		this.arriveCity = arriveCity;
		this.departDate = departDate;
		this.departTime = departTime;
		this.arriveDate = arriveDate;
		this.arriveTime = arriveTime;
	}

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
	
	public void checkCities(){
		
	}
	
	public void checkTimes(){
	
	}
	
	public void checkDates(){
	
	}
	
	public void checkPassengers(){
		
	}
	
	public void assignSeat(Customer customer){
		
	}
	
	public void writePassengerList(){
		
	}
	
	public Seat suggestSeat(){
		return null;
	}
	
	public void writeToDatabase(){
		
	}
	
	@Override
	public String toString(){
		return null;
	}
	
	public void reserveSeat(Booking request){
		
	}
	
	public Seat[] getEmptySeats(){
		return null;
	}
	
}
