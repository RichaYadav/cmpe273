package sjsu.cmpe273.project.beans;

public class ReservationDetailBean {
JourneyDetailBean journeyDetailBean;
FlightDetailBean flightDetailBean;
PersonBean personBean;
TravelerBean travelerBean;
BookingDetailBean bookingDetailBean;
LocationsBean destination;
LocationsBean source;


public LocationsBean getDestination() {
	return destination;
}
public void setDestination(LocationsBean destination) {
	this.destination = destination;
}
public LocationsBean getSource() {
	return source;
}
public void setSource(LocationsBean source) {
	this.source = source;
}
public JourneyDetailBean getJourneyDetailBean() {
	return journeyDetailBean;
}
public void setJourneyDetailBean(JourneyDetailBean journeyDetailBean) {
	this.journeyDetailBean = journeyDetailBean;
}
public FlightDetailBean getFlightDetailBean() {
	return flightDetailBean;
}
public void setFlightDetailBean(FlightDetailBean flightDetailBean) {
	this.flightDetailBean = flightDetailBean;
}
public PersonBean getPersonBean() {
	return personBean;
}
public void setPersonBean(PersonBean personBean) {
	this.personBean = personBean;
}
public TravelerBean getTravelerBean() {
	return travelerBean;
}
public void setTravelerBean(TravelerBean travelerBean) {
	this.travelerBean = travelerBean;
}
public BookingDetailBean getBookingDetailBean() {
	return bookingDetailBean;
}
public void setBookingDetailBean(BookingDetailBean bookingDetailBean) {
	this.bookingDetailBean = bookingDetailBean;
}

}
