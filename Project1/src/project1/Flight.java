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
	
	public void checkCities(){
		if(departCity.equals(arriveCity)){
			data.removeFlight(this);
			throw new IllegalArgumentException("The Departure and Arrival Cities must be different");
		}
		
	}
	
	public void checkTimes(){
		if(arrivalChrono.before(departureChrono)){
			data.removeFlight(this);
			throw new IllegalArgumentException("Arrival must be after departure!");
		}
	}
	
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
	
	public Seat suggestSeat(){
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
				System.out.print("Row: "+(i+1)+" Seat: "+seats[i][j].getSeat()+"is occupied? ");
				System.out.println(seats[i][j].isOccupied());
			}
		}
	}
	
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
				//System.out.print(row);
				//System.out.println(temp.getSeat());
				if(temp.getType().equals(request.getCustomer().getPref()) && !temp.isOccupied()){
					seats[row][col].bookSeat(request);
					seats[row][col].setOccupied(true);
					seatFound = true;
					request.setSeat(seats[row][col]);
					System.out.println("Row: "+temp.getRow()+" Seat: "+temp.getSeat()+" is booked? "+temp.isOccupied());
					System.out.println("Booking Request Successfully processed");
					//printSeats();
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
	
	public ArrayList<Seat> getEmptySeats(){
		ArrayList<Seat> emptySeats = new ArrayList<Seat>();
		
		for(int i=0; i<20; i++){
			for(int j=0; j<6; j++){
				//System.out.print(seats[i][j].getRow());
				//System.out.println(seats[i][j].getSeat());
				if(seats[i][j].isOccupied()== false)					
					emptySeats.add(seats[i][j]);
			}
		}		
		return emptySeats;
	}
	
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
	
	public void bookSpecificSeat(Booking x){
		if(isSeatOccupied(x.getSeat()))
			throw new IllegalArgumentException("This seat is booked.");
		else{
			seats[x.getSeat().getRow()][converter(x.getSeat().getSeat())].bookSeat(x);
		}
	}
	
	public void cancelBooking(Booking booking){
		seats[booking.getSeat().getRow()][converter(booking.getSeat().getSeat())].cancellation();
	}
	
}
