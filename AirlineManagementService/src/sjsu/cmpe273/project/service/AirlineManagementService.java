package sjsu.cmpe273.project.service;

import javax.jws.WebService;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.beans.LocationsBean;
import sjsu.cmpe273.project.beans.PassengerBean;
import sjsu.cmpe273.project.beans.PaymentDetailsBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.ReservationDetailBean;
import sjsu.cmpe273.project.beans.TravelerBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.process.CustomerProcess;
import sjsu.cmpe273.project.process.EmployeeProcess;
import sjsu.cmpe273.project.process.FlightProcess;
import sjsu.cmpe273.project.process.JourneyProcess;
import sjsu.cmpe273.project.process.LocationsProcess;
import sjsu.cmpe273.project.process.PersonProcess;
@WebService
public class AirlineManagementService {
	EmployeeProcess employeeProcess = new EmployeeProcess();
	CustomerProcess custumorProcess = new CustomerProcess();
	PersonProcess personProcess = new PersonProcess();
	JourneyProcess journeyProcess = new JourneyProcess();
	//Frank 
	public UserBean login(String email, String password) {
		UserBean userBean = personProcess.loginProcess(email,password );
		return userBean;
	}

	public FlightDetailBean[] searchFlights(FlightDetailBean flightDetailBean) {
		FlightDetailBean flightArray[] = new FlightDetailBean[1];
		return flightArray;
	}

	public boolean logOff(String userSsn) {
		boolean isSuccess = true;
		return isSuccess;
	}

	public JourneyDetailBean[] accountActivity(String userSsn) {
		JourneyDetailBean accountArray[] = new JourneyDetailBean[1];
		return accountArray;
	}

	public UserBean createUser(PersonBean personBean) {
		UserBean userBean = new UserBean();
		return userBean;
	}

	public boolean deleteUser(String userSsn) {
		boolean isSuccess = true;
		return isSuccess;
	}
	
	/*********************************************************************/
	/*
	 * Completed by Frank. 
	 * Any problems, tell me.
	 */
	public int createEmployee(UserBean userBean){
		int status = employeeProcess.createEmployeeProcess(userBean);
		return status;
	}
	
	public boolean deleteEmployee(int userSsn) {
		return employeeProcess.deleteEmployeeProcess(userSsn);
	}
	
	public UserBean[] listAllEmployees() {
		return employeeProcess.listAllEmployeesProcess();
	}
	
	public UserBean[] searchEmployee(String searchType, UserBean employee ){
		return employeeProcess.searchEmployeeProcess(searchType, employee);
	}
	
	public void editEmployee(){
		
	}
	
	public UserBean showEmployeeDetail(AirlineEmployeeBean airlineEmployeeBean) {
		UserBean userBean = new UserBean();
		return userBean;
	}
	
	public JourneyDetailBean[] searchJourneys(String to , String from, String time){
		
		return journeyProcess.searchJourneys(to, from, time);
	}
	/*********************************************************************/
	
	// Shibai
	public String createBooking(FlightDetailBean flightDetailBean, TravelerBean travelerBean) {
		String bookingStatus = " ";
		return bookingStatus;
	}

	// Shibai
	public String cancelBooking(TravelerBean travelerBean) {
		String bookingStatus = " ";
		return bookingStatus;
	}
	
	public String createCustomer(UserBean customer){
		
		return "";
	}

	// Shibai
	public boolean issueTicket(UserBean userBean) {
		return custumorProcess.issueTicket(userBean);
	}

	public PaymentDetailsBean makePayment(UserBean userBean, JourneyDetailBean journeyDetailBean) {
		PaymentDetailsBean paymentDetailsBean = new PaymentDetailsBean();
		return paymentDetailsBean;
	}

	// Shibai
	public UserBean[] listAllCustomer() {
		return custumorProcess.listAllCustomersProcess();
	}
	
	public ReservationDetailBean[] listAllReservation() {
		ReservationDetailBean reservationDetailBean[] = new ReservationDetailBean[1];
		return reservationDetailBean;
	}

	public FlightDetailBean[] listAllFlights() {

		FlightDetailBean flightArray[] = null;

		FlightProcess flightProcess = new FlightProcess();
		flightArray = flightProcess.listAllFlights();

		return flightArray;
	}

	public UserBean editUser(UserBean userBean) {
		return userBean;
	}

	public FlightDetailBean editFlight(FlightDetailBean flightDetailBean) {
		return flightDetailBean;
	}



	public PassengerBean[] passengerOnBoard(int journeyId, TravelerBean travelerBean) {
		PassengerBean passengerBean[] = new PassengerBean[1];
		return passengerBean;
	}

	/********************************** NEW METHODS *****************************************************/
	public boolean scheduleFlight(JourneyDetailBean journeyDetailBean) {

		boolean isSuccess = true;

		JourneyProcess journeyProcess = new JourneyProcess();
		isSuccess = journeyProcess.scheduleJourney(journeyDetailBean);

		return isSuccess;
	}

	public LocationsBean[] listAllLocations() {

		LocationsBean locationList[] = null;

		LocationsProcess locationsProcess = new LocationsProcess();
		locationList = locationsProcess.listAllLocations();
		/*
		 * for (LocationsBean locationsBean : locationList) {
		 * System.out.println(locationsBean.getAirport_name() + "   " +
		 * locationsBean.getLocation_name()); }
		 */
		return locationList;
	}

	public boolean checkDuplicateFlight(JourneyDetailBean journeyDetailBean) {
		boolean isDuplicate = false;

		JourneyProcess journeyProcess = new JourneyProcess();
		isDuplicate = journeyProcess.checkDuplicateFlight(journeyDetailBean);

		return isDuplicate;
	}
}
