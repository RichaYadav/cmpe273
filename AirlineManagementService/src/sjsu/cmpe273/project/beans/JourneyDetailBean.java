package sjsu.cmpe273.project.beans;

// Journey Detail bean
public class JourneyDetailBean { 
	int journey_id;
	int flight_id;
	int flight_source;
	int flight_destination;
	int seats_available;
	int seats_booked;
	float ticket_price;
	String departure_time;
	String arrival_time;
	LocationsBean sourceLocation;
	LocationsBean destinationLocation;
	String flight_name;
	
	int journey_status;
	
	public int getJourney_status() {
		return journey_status;
	}
	public void setJourney_status(int journey_status) {
		this.journey_status = journey_status;
	}

	

	public String getFlight_name() {
		return flight_name;
	}

	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}

	public LocationsBean getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(LocationsBean sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public LocationsBean getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(LocationsBean destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public int getJourney_id() {
		return journey_id;
	}
	public void setJourney_id(int journey_id) {
		this.journey_id = journey_id;
	}
	public int getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	public int getFlight_source() {
		return flight_source;
	}
	public void setFlight_source(int flight_source) {
		this.flight_source = flight_source;
	}
	public int getFlight_destination() {
		return flight_destination;
	}
	public void setFlight_destination(int flight_destination) {
		this.flight_destination = flight_destination;
	}
	public int getSeats_available() {
		return seats_available;
	}
	public void setSeats_available(int seats_available) {
		this.seats_available = seats_available;
	}
	public int getSeats_booked() {
		return seats_booked;
	}
	public void setSeats_booked(int seats_booked) {
		this.seats_booked = seats_booked;
	}
	public float getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(float ticket_price) {
		this.ticket_price = ticket_price;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
}
