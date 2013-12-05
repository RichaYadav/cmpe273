package sjsu.cmpe273.project.process;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.ReservationLists;
import sjsu.cmpe273.project.dao.BookingDetailsDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;

public class BookingProcess {
	public boolean cancelBooking(int bookingId) {
		
		BookingDetailsDao bookingDetailsDao = new BookingDetailsDao();
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		boolean isCancelled = true;
		try {
			isCancelled = bookingDetailsDao.cancelBooking(connection, bookingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isCancelled;
	}
	
	public ReservationLists listAllReservations() {
		ReservationLists reservationLists = new ReservationLists();
		BookingDetailsDao bookingDetailsDao = new BookingDetailsDao();
		
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		try {
			reservationLists = bookingDetailsDao.listAllReservations(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(reservationLists.getActiveReservations().length == 0 && reservationLists.getCancelledReservations().length == 0){
			reservationLists = null;
		}
		return reservationLists;
	}
}
