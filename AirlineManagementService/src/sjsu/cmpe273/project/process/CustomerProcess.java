package sjsu.cmpe273.project.process;

import java.sql.Connection;
import java.util.List;

import sjsu.cmpe273.project.beans.CommonValuesBean;
import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.beans.PaymentDetailsBean;
import sjsu.cmpe273.project.beans.TravelerBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.BookingDetailsDao;
import sjsu.cmpe273.project.dao.CustomerDao;
import sjsu.cmpe273.project.dao.PaymentDetailsDao;
import sjsu.cmpe273.project.dao.TicketDetailsDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.helper.ProjectHelper;

// Shibai
public class CustomerProcess {
	CustomerDao customerDao = new CustomerDao();

	public String cancelBooking(TravelerBean travelerBean) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		return customerDao.cancelBooking(connection, travelerBean);
	}

	public boolean issueTicket(UserBean userBean) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		boolean isIssued = true;
		try {
			isIssued = customerDao.issueTicket(connection, userBean);
		} catch (Exception e) {
			e.printStackTrace();
			isIssued = false;
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		return isIssued;
	}

	// not yet finished
	public String createBooking(JourneyDetailBean journeyBean, UserBean[] userBeans, PaymentDetailsBean paymentBean) {
		// add one entry to booking_d
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();

		boolean isPaymentMethod = true;

		PaymentDetailsDao paymentDetailsDao = new PaymentDetailsDao();
		if (journeyBean != null && userBeans != null && paymentBean != null && journeyBean.getJourney_id() != 0) {

			List<CommonValuesBean> paymentMethods = null;
			try {
				paymentMethods = paymentDetailsDao.listPaymentMethods(connection);

				for (CommonValuesBean paymentMethod : paymentMethods) {
					if (paymentMethod.getId() == paymentBean.getPayment_method()) {
						if (paymentMethod.getId_description().trim().equalsIgnoreCase("CARD")) {
							if (paymentBean.getCard_number() == 0) {
								isPaymentMethod = false;
							}
						} else if (paymentMethod.getId_description().trim().equalsIgnoreCase("ACCOUNT")) {
							if (paymentBean.getAccount_number() == 0) {
								isPaymentMethod = false;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				isPaymentMethod = false;
			}
		}
		
		int paymentId = 0;
		if (isPaymentMethod) {
			try {
				paymentId = paymentDetailsDao.makePayment(connection, paymentBean);
				
				List<CommonValuesBean> paymentStatusList = null;
				
				paymentStatusList = paymentDetailsDao.listPaymentStatus(connection);
				for (CommonValuesBean paymentStatus : paymentStatusList) {
					if(paymentStatus.getId_description().equalsIgnoreCase("SUCCESS") && paymentId > 0){
						paymentDetailsDao.updatePaymentStatus(connection, paymentStatus.getId(), paymentId);
					}
					if(paymentStatus.getId_description().equalsIgnoreCase("FAILED") && paymentId <= 0){
						paymentDetailsDao.updatePaymentStatus(connection, paymentStatus.getId(), paymentId);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int bookingId = 0;
		if (paymentId > 0) {
			BookingDetailsDao bookingDetailsDao = new BookingDetailsDao();
			try {
				bookingId = bookingDetailsDao.makeBooking(connection, userBeans.length, paymentId, journeyBean.getJourney_id());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		boolean isSuccess = false;
		if(bookingId > 0){
			TicketDetailsDao ticketDetailsDao = new TicketDetailsDao();
			try {
				isSuccess = ticketDetailsDao.makeTicketEntries(connection, bookingId, userBeans);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String BookingIdString = null;
		if (isSuccess) {
			BookingIdString = bookingId + "";
		}
		
		return BookingIdString;
	}
	
	// shibai
	public UserBean[] listAllCustomersProcess() {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		// UserBean[] customers;
		try {
			UserBean[] customers = customerDao.listAllCustomers(connection);
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ProjectHelper.closeConnection(connection);
		}
	}
	
	// shibai
	public UserBean[] searchCustomerProcess(String searchType, UserBean user) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		try {
			return customerDao.searchCustomer(connection , searchType, user);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			ProjectHelper.closeConnection(connection);	
		}
	}
	

	/*
	 * public ReservationDetailBean[] listAllReservations(){
	 * 
	 * 
	 * }
	 */
}
