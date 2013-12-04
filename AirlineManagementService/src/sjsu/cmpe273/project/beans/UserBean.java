package sjsu.cmpe273.project.beans;

public class UserBean {
PersonBean person;
TravelerBean traveler;
AirlineEmployeeBean employeeBean;
public PersonBean getPerson() {
	return person;
}
public void setPerson(PersonBean person) {
	this.person = person;
}
public TravelerBean getTraveler() {
	return traveler;
}
public void setTraveler(TravelerBean traveler) {
	this.traveler = traveler;
}
public AirlineEmployeeBean getEmployeeBean() {
	return employeeBean;
}
public void setEmployeeBean(AirlineEmployeeBean employeeBean) {
	this.employeeBean = employeeBean;
}

}
