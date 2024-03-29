package project1;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Flight {
	
	private int flightNumber;
	private String departCity;
	private String arriveCity;
	private GregorianCalendar departureChrono;
	private GregorianCalendar arrivalChrono;
	private Seat[][] seats = new Seat[20][6];
	private Database data;
	
	/*
	 * The constructor takes in all the data.  There is only one way to make a flight.
	 * It then creates a 2d array of seat objects and checks the validity of the schedule
	 * and destination.
	 * 
	 * The methods are all pretty basic setter/getters except for seat assignments.
	 * 
	 * Essentially, the seats are either occupied or not, and the class maintains a list
	 * of unoccupied seats and uses that plus customer preference to assign seats.
	 */
	public Flight(int flightNumber, String departCity, String arriveCity,
			GregorianCalendar depart, GregorianCalendar arrive, Database data) 
					throws IllegalArgumentException {
		
		this.flightNumber = flightNumber;
		this.departCity = departCity;
		this.arriveCity = arriveCity;
		this.departureChrono = depart;
		this.arrivalChrono = arrive;
		this.data = data;
		instantiateSeats();
		writeToDatabase();
		checkCities();
		checkTimes();
		
	}
	
	private void instantiateSeats(){
		for(int i = 0; i <20; i++){
			for(int j =0; j < 6; j++){
				if(j==0){
					seats[i][j] = new Seat('a',"window",i);
				}else if(j==1){
					seats[i][j] = new Seat('b',"middle",i);
				}else if(j==2){
					seats[i][j] = new Seat('c',"aisle",i);
				}else if(j==3){
					seats[i][j] = new Seat('d',"aisle",i);
				}else if(j==4){
					seats[i][j] = new Seat('e',"middle",i);
				}else if(j==5){
					seats[i][j] = new Seat('f',"window",i);
				}
			}
		}
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
	
	public GregorianCalendar getDepartDate() {
		return departureChrono;
	}

	public GregorianCalendar getArrivalTime() {
		return arrivalChrono;
	}
	
	public Seat[][] getSeats() {
		return seats;
	}
	
	//These two check methods ensure that the times and locations are "valid"
	//they don't have to make sense, but they also can't break the laws of physics
	private void checkCities(){
		if(departCity.equals(arriveCity)){
			data.removeFlight(this);
			throw new IllegalArgumentException("The Departure and Arrival Cities must be different");
		}
		
	}
	
	private void checkTimes(){
		if(arrivalChrono.before(departureChrono)){
			data.removeFlight(this);
			throw new IllegalArgumentException("Arrival must be after departure!");
		}
	}
	
	private void checkFlightNum(){
		if(data.flightCheck(flightNumber)){
			data.removeFlight(this);		
			throw new IllegalArgumentException("Flight Number must be unique");
		}
	}
	
	//Here we gather all seats flagged as occupied.
	//Then, it sorts the list lexicographically by customer last name
	//After that, it uses the printWriter class to write to a file
	//then I close the streams!!
	public void writePassengerList() throws IOException{
		FileWriter outStream = new FileWriter("Flight_"+flightNumber+"_Manifest.dat");
		PrintWriter output = new PrintWriter(outStream);
		
		ArrayList<Seat> sortedSeatList = occupiedSeats();
		Collections.sort(sortedSeatList);
		
		Iterator<Seat> i = sortedSeatList.iterator();
		
		while(i.hasNext()){
			Seat temp = i.next();
			output.println((temp.getBooking().getCustomer().toString()+","+
			temp.toString()));
		}
		
		output.close();
		outStream.close();
	}
	
	private Seat suggestSeat(){
		return null;//My reservation Algorithm books based on preference already
	}
	
	public boolean isSeatOccupied(Seat checkMe){
		return seats[checkMe.getRow()][converter(checkMe.getSeat())].isOccupied();			
	}
	
	public ArrayList<Seat> occupiedSeats(){
		ArrayList<Seat> occupiedSeatList = new ArrayList<Seat>();
		for(int i =0; i<20; i++){
			for(int j =0; j<6; j++){				
				if(seats[i][j].isOccupied())
					occupiedSeatList.add(seats[i][j]);				
			}
		}
		return occupiedSeatList;
	}
	
	private void writeToDatabase(){
		data.addFlight(this);
	}
	
	@Override
	public String toString(){
		SimpleDateFormat flightFormat = new SimpleDateFormat("dd-MM-yy-hh:mm");
		return "Flight: "+flightNumber+"\n"+
				"Origin: "+departCity+"\n"+
				"Destination: "+arriveCity+"\n"+
				"Departure Time: "+flightFormat.format(departureChrono.getTime())+"\n"+
				"Scheduled Arrival Time: "+flightFormat.format(arrivalChrono.getTime())+"\n";
	}
	
	public void printSeats(){
		for(int i=0; i<20; i++){
			for(int j=0; j<6; j++){
				//System.out.print("Row: "+(i+1)+" Seat: "+seats[i][j].getSeat()+"is occupied? ");
				//System.out.println(seats[i][j].isOccupied());
			}
		}
	}
	
	//This is the backbone of reserving a seat.  It takes in a booking request
	//It creates a list of available seats
	//Then, it iterates through each seat to see if it is a good match
	//The matching goes based off of customer preference.  If there is no
	//preferred seat, it assigns the first available seat.
	public void reserveSeat(Booking request){
		ArrayList<Seat> availableSeats = getEmptySeats();
		if(availableSeats.isEmpty())
			throw new IllegalArgumentException("Flight is fully booked");
		else{
			Iterator<Seat> i = availableSeats.iterator();
			boolean seatFound = false;
			int row;
			int col;
			Seat temp;
			
			while(i.hasNext() && seatFound == false){
				temp = i.next();
				row = temp.getRow();
				col = converter(temp.getSeat());
				
				if(temp.getType().equals(request.getCustomer().getPref()) && !temp.isOccupied()){
					seats[row][col].bookSeat(request);
					seats[row][col].setOccupied(true);
					seatFound = true;
					request.setSeat(seats[row][col]);
					
					System.out.println("Booking Request Successfully processed");
					
				}
			}
			if(!seatFound){
				Iterator<Seat> j = availableSeats.iterator();
				
				while(j.hasNext() && seatFound == false){
					temp = j.next();
					row = temp.getRow();
					col = converter(temp.getSeat());
					
					if(!temp.isOccupied()){
						seats[row][col].bookSeat(request);
						seats[row][col].setOccupied(true);
						seatFound = true;
						request.setSeat(seats[row][col]);
						System.out.println("Booking Request Successfully processed, but not with preference");
					}
					
				}
			}
		}
	}
	
	//Collects all the seats that have the occupied flag set to false
	private ArrayList<Seat> getEmptySeats(){
		ArrayList<Seat> emptySeats = new ArrayList<Seat>();
		
		for(int i=0; i<20; i++){
			for(int j=0; j<6; j++){				
				if(seats[i][j].isOccupied()== false)					
					emptySeats.add(seats[i][j]);
			}
		}		
		return emptySeats;
	}
	
	//this was just a way to make it easier to work in a row/col style
	//as well as with "real life" airline seating systems.
	private int converter(char in){
		if(in == 'a')
			return 0;
		else if(in == 'b')
			return 1;
		else if(in =='c')
			return 2;
		else if(in == 'd')
			return 3;
		else if(in == 'e')
			return 4;
		else if(in == 'f')
			return 5;
		else
			throw new IllegalArgumentException("The seat converter messed up!");
	}
	
	//The book specific seat goes through and checks if it is available or not.
	//If it is, then it assigns the booking to that seat.
	public void bookSpecificSeat(Booking x){
		if(isSeatOccupied(x.getSeat()))
			throw new IllegalArgumentException("This seat is booked.");
		else{
			seats[x.getSeat().getRow()][converter(x.getSeat().getSeat())].bookSeat(x);
		}
	}
	
	public void cancelBooking(Booking booking){
		System.out.println("Cancelling booking now...");
		seats[booking.getSeat().getRow()][converter(booking.getSeat().getSeat())].cancellation();
		System.out.println("Cancellation completed.");
	}
	
}
