package sjsu.cmpe273.project.process;

import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.beans.TravelerBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.CustomerDao;

// Shibai
public class CustomerProcess {
	CustomerDao customerDao = new CustomerDao();
	
	public String cancelBooking(TravelerBean travelerBean) {
		return customerDao.cancelBooking(travelerBean);
	}
	
	public boolean issueTicket(UserBean userBean) {
		return customerDao.issueTicket(userBean);
	}
	
	// not yet finished
	public String createBooking (FlightDetailBean flightDetailBean, TravelerBean travelerBean) {
		// add one entry to booking_d
		// 
		
		return "";
	}

	public UserBean[] listAllEmployeesProcess(){
		return customerDao.listAllCustomers();
	}
	
}
