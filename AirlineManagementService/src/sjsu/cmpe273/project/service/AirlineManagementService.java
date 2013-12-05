package sjsu.cmpe273.project.service;

import javax.jws.WebService;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.beans.LocationsBean;
import sjsu.cmpe273.project.beans.PassengerBean;
import sjsu.cmpe273.project.beans.PaymentDetailsBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.ReservationBean;
import sjsu.cmpe273.project.beans.ReservationDetailBean;
import sjsu.cmpe273.project.beans.ReservationLists;
import sjsu.cmpe273.project.beans.TravelerBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.process.BookingProcess;
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
		return employeeProcess.createEmployeeProcess(userBean);
		//return userBean;
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
	
	public boolean  updateEmployee(PersonBean person){
		return updatePerson(person);
	}
	
	// shibai
	public boolean updatePerson(PersonBean person) {
		return personProcess.updatePerson(person);
	}
	
	// shibai
	public UserBean[] searchCustomer(String searchType, UserBean employee) {
		return custumorProcess.searchCustomerProcess(searchType, employee);
	}
	
	
	public UserBean showEmployeeDetail(AirlineEmployeeBean airlineEmployeeBean) {
		UserBean userBean = new UserBean();
		return userBean;
	}
	public JourneyDetailBean[] searchJourneys(String to, String from,
			String time) {
		
		return journeyProcess.searchJourneys(to, from, time);
	}
	/*********************************************************************/
	
	// Shibai
	public String createBooking(FlightDetailBean flightDetailBean, TravelerBean travelerBean) {
		String bookingStatus = " ";
		
		CustomerProcess customerProcess = new CustomerProcess();
		JourneyDetailBean journeyBean = new JourneyDetailBean();
		journeyBean.setJourney_id(6);
		
		UserBean[] userBeans = new UserBean[1];
		UserBean userBean = new UserBean();
		
		userBean.setPerson(new PersonBean());
		
		userBean.getPerson().setPerson_type(1);
		userBean.getPerson().setFirst_name("Amol");
		userBean.getPerson().setLast_name("Mane");
		userBean.getPerson().setPassport_number("H70409131");
		userBean.getPerson().setAddress_line1("190 Ryland Street");
		userBean.getPerson().setCity("San Jose");
		userBean.getPerson().setCountry("USA");
		userBean.getPerson().setState("CA");
		userBean.getPerson().setEmail_address("amol.mane@sjsu.edu");
		userBean.getPerson().setZip_code("95110");
		
		userBean.setTraveler(new TravelerBean());
		userBean.getTraveler().setSsn(123456789);
		userBeans[0] = userBean;
		
		PaymentDetailsBean paymentBean = new PaymentDetailsBean();
//		paymentBean.setCard_number(1234567890123456l);
		paymentBean.setCard_number(0);
		paymentBean.setAmount_paid(1500);
		paymentBean.setPayment_method(5);
//		paymentBean.setAccount_number(0);
		paymentBean.setAccount_number(1234567890123456l);
		
		customerProcess.createBooking(journeyBean, userBeans, paymentBean);
		
		return bookingStatus;
	}

	// Shibai
	public boolean cancelBooking(int bookingId) {
		BookingProcess bookingProcess = new BookingProcess();
		
		boolean isCancelled = false;
		
		isCancelled = bookingProcess.cancelBooking(bookingId);
		
		return isCancelled;
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
	
	public ReservationLists listAllReservation() {
		ReservationLists reservationLists = null;
		BookingProcess bookingProcess = new BookingProcess();
		reservationLists = bookingProcess.listAllReservations();
		return reservationLists;
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
		return locationList;
	}

	public boolean checkDuplicateFlight(JourneyDetailBean journeyDetailBean) {
		boolean isDuplicate = false;

		JourneyProcess journeyProcess = new JourneyProcess();
		isDuplicate = journeyProcess.checkDuplicateFlight(journeyDetailBean);

		return isDuplicate;
	}
	
	public JourneyDetailBean[] listAllJourneys(){
		JourneyDetailBean journeyList[] = null;
		
		JourneyProcess journeyProcess = new JourneyProcess();
		journeyList = journeyProcess.listAllJourneys();
		
		return journeyList;
	}
	
	public JourneyDetailBean journeyDetail(int journeyId){
		JourneyDetailBean journeyDetailBean = null;
		
		JourneyProcess journeyProcess = new JourneyProcess();
		journeyDetailBean = journeyProcess.getJourneyDetail(journeyId);
//		System.out.println(journeyDetailBean.getArrival_time());
		return journeyDetailBean;
	}
	
	public boolean updatejourney(JourneyDetailBean journeyDetailBean){
		boolean isInserted = true;
		
		JourneyProcess journeyProcess = new JourneyProcess();
		isInserted = journeyProcess.updateJourney(journeyDetailBean);
		
		return isInserted;
	}
	
	public boolean cancelJourney(int journeyId){
		boolean isCancelled = true;
		
		JourneyProcess journeyProcess = new JourneyProcess();
		isCancelled = journeyProcess.cancelJourney(journeyId);
		
		return isCancelled;
	}
	
	public static void main(String[] args) {
		AirlineManagementService airlineManagementService = new AirlineManagementService();
		ReservationLists reservationLists = null;
		reservationLists = airlineManagementService.listAllReservation();
		
		for (ReservationBean active : reservationLists.getActiveReservations()) {
			System.out.println(active.getBookingId() +" " + active.getSourceAirport() + " " + active.getBookingStatus());
		}
		
		for (ReservationBean active : reservationLists.getCancelledReservations()) {
			System.out.println(active.getBookingId() +" " + active.getSourceAirport() + " " + active.getBookingStatus());
		}
	}
}
