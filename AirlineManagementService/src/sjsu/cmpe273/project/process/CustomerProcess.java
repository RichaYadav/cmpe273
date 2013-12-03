package sjsu.cmpe273.project.process;

import java.sql.Connection;
import java.util.ArrayList;

import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.beans.ReservationDetailBean;
import sjsu.cmpe273.project.beans.TravelerBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.CustomerDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.helper.ProjectHelper;

// Shibai
public class CustomerProcess {
	CustomerDao customerDao = new CustomerDao();
	
	public String cancelBooking(TravelerBean travelerBean) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		return customerDao.cancelBooking(connection , travelerBean);
	}
	
	public boolean issueTicket(UserBean userBean) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		boolean isIssued = true;
		try {
			isIssued = customerDao.issueTicket(connection , userBean);
		} catch (Exception e) {
			e.printStackTrace();
			isIssued = false;
		}finally{
			ProjectHelper.closeConnection(connection);		
		}
		return isIssued;
	}
	
	// not yet finished
	public String createBooking(ReservationDetailBean reserve) {
		// add one entry to booking_d
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		JourneyProcess jp = new JourneyProcess();
		if(jp.scheduleJourney(reserve.getJourneyDetailBean())){
			
		}
		return "";
	}

	public UserBean[] listAllCustomersProcess(){
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		//UserBean[] customers;
		try {
			UserBean[] customers = customerDao.listAllCustomers(connection);
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			ProjectHelper.closeConnection(connection);		
		}
	}
	
	/*
	public ReservationDetailBean[] listAllReservations(){
		
		
	}
	*/
}
