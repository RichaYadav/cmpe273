package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sjsu.cmpe273.project.helper.ProjectHelper;

public class BookingDetailsDao {
	public int makeBooking(Connection connection, int seatsBooked, int paymentId, int journeyId) throws Exception{
		Statement statement = null;
		
		int bookingId = 0;
		int isInserted = 0;
		
		String MAKE_BOOKING = " INSERT INTO BOOKING_DETAILS( BOOKING_STATUS , PAYMENT_ID, JOURNEY_ID, SEATS_BOOKED) VALUES  "
				+ " ( ( SELECT ID FROM COMMON_VALUES WHERE ID_TYPE = 'BOOKING_STATUS' AND ID_DESCRIPTION = 'CONFIRMED' ), " + paymentId + ","+ journeyId+ ","+ seatsBooked +") ";
		
		try {
			statement = connection.createStatement();
			isInserted = statement.executeUpdate(MAKE_BOOKING);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}
		
		if (isInserted > 0) {
			Statement bookingIdStatement = null;
			ResultSet bookingIdResultSet = null;
			
			String GET_BOOKING_ID = " SELECT BOOKING_ID FROM BOOKING_DETAILS WHERE PAYMENT_ID = " + paymentId;
			
			try { 
				bookingIdStatement = connection.createStatement();
				bookingIdResultSet = bookingIdStatement.executeQuery(GET_BOOKING_ID);
				if (bookingIdResultSet.next()) {
					bookingId = bookingIdResultSet.getInt("BOOKING_ID");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} finally {
				ProjectHelper.closeStatement(statement);
			}
		}
		
		if(bookingId > 0){
			
			String UPDATE_JOURNEY_DETAILS = "  UPDATE JOURNEY_DETAILS SET SEATS_BOOKED = ( SEATS_BOOKED + " + seatsBooked + " ) WHERE JOURNEY_ID =  " + journeyId;
					
					/*" UPDATE JOURNEY_DETAILS SET SEATS_BOOKED = (( "
					+ "  = " + journeyId +" ) + " + seatsBooked + ") WHERE JOURNEY_ID = " + journeyId;*/
			
			Statement updateStatement = null;

			try { 
				updateStatement = connection.createStatement();
				bookingId = updateStatement.executeUpdate(UPDATE_JOURNEY_DETAILS);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} finally {
				ProjectHelper.closeStatement(statement);
			}			
		}
		
		return bookingId;
	}
}
