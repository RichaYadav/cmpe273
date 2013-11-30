package sjsu.cmpe273.project.beans;

public class PassengerBean {
	FlightDetailBean flightDetailBean;
	PersonBean[] passengers;
	public FlightDetailBean getFlightDetailBean() {
		return flightDetailBean;
	}
	public void setFlightDetailBean(FlightDetailBean flightDetailBean) {
		this.flightDetailBean = flightDetailBean;
	}
	public PersonBean[] getPassengers() {
		return passengers;
	}
	public void setPassengers(PersonBean[] passengers) {
		this.passengers = passengers;
	}

}
