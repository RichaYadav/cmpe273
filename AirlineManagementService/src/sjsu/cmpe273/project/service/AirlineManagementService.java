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
	public boolean createEmployee(UserBean userBean){
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
	
	public void editEmployee(){
		
	}
	
	public UserBean showEmployeeDetail(AirlineEmployeeBean airlineEmployeeBean) {
		UserBean userBean = new UserBean();
		return userBean;
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
		userBean.getPerson().setCounrty("USA");
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
	
	public static void main(String[] args) {
		AirlineManagementService airlineManagementService = new AirlineManagementService();
		airlineManagementService.createBooking(new FlightDetailBean(), new TravelerBean());
	}
}
