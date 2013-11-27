package sjsu.cmpe273.project.service;

import javax.jws.WebService;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.beans.PassengerBean;
import sjsu.cmpe273.project.beans.PaymentDetailsBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.ReservationDetailBean;
import sjsu.cmpe273.project.beans.TravelerBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.process.EmployeeProcess;

@WebService
public class AirlineManagementService {
	EmployeeProcess employeeProcess = new EmployeeProcess();

	public UserBean login(String userName, String password) {
		UserBean userBean = new UserBean();
		return userBean;
	}

	public FlightDetailBean[] searchFlights(FlightDetailBean flightDetailBean) {
		FlightDetailBean flightArray[] = new FlightDetailBean[1];
		return flightArray;
	}
	
	public boolean logOff(String userSsn){
		boolean isSuccess = true;
		return isSuccess;
	}
	
	public JourneyDetailBean[] accountActivity(String userSsn){
		JourneyDetailBean  accountArray[] = new JourneyDetailBean[1];
		return accountArray;
	}

	
	public UserBean createUser(PersonBean personBean){
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
	public void createEmployee(UserBean userBean) {
		employeeProcess.createEmployeeProcess(userBean.getEmployeeBean());
		//return userBean;
	}
	
	public boolean deleteEmployee(int userSsn) {
		return employeeProcess.deleteEmployeeProcess(userSsn);
	}
	
	public UserBean[] listAllEmployees() {
		return employeeProcess.listAllEmployeesProcess();
	}
	/*********************************************************************/
	
	public String createBooking(FlightDetailBean flightDetailBean, TravelerBean travelerBean) {
		String bookingStatus = " ";
		return bookingStatus;
	}
	
	public String cancelBooking(TravelerBean travelerBean) {
		String bookingStatus = " ";
		return bookingStatus;
	}
	
	public boolean issueTicket(UserBean userBean) {
		boolean isIssued = true;
		return isIssued;
	}
	
	public PaymentDetailsBean makePayment(UserBean userBean, JourneyDetailBean journeyDetailBean) {
		PaymentDetailsBean paymentDetailsBean = new PaymentDetailsBean();
		return paymentDetailsBean;
	}
	
	public UserBean[] listAllCustomer() {
		UserBean userBean[] = new UserBean[1];
		return userBean;
	}
	
	public ReservationDetailBean[] listAllReservation() {
		ReservationDetailBean reservationDetailBean[] = new ReservationDetailBean[1];
		return reservationDetailBean;
	}
	
	public FlightDetailBean[] listAllFlights() {
		FlightDetailBean flightArray[] = new FlightDetailBean[1];
		return flightArray;
	}
	
	public UserBean editUser(UserBean userBean){
		return userBean;
	}
	
	public FlightDetailBean editFlight(FlightDetailBean flightDetailBean){
		return flightDetailBean;
	}
	
	public UserBean showEmployeeDetail(AirlineEmployeeBean airlineEmployeeBean){
		UserBean userBean = new UserBean();
		return userBean;
	}
	
	public PassengerBean[] passengerOnBoard(int journeyId, TravelerBean travelerBean) {
	PassengerBean passengerBean[] = new PassengerBean[1];
	return passengerBean;
	}
	
	/********************************** NEW METHODS *****************************************************/
	public boolean scheduleFlight(JourneyDetailBean journeyDetailBean){
		return true;
	}
}