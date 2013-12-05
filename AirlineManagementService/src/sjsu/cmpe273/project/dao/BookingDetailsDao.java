package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sjsu.cmpe273.project.beans.ReservationBean;
import sjsu.cmpe273.project.beans.ReservationLists;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class BookingDetailsDao {
	public int makeBooking(Connection connection, int seatsBooked, int paymentId, int journeyId) throws Exception {
		Statement statement = null;

		int bookingId = 0;
		int isInserted = 0;

		String MAKE_BOOKING = " INSERT INTO BOOKING_DETAILS( BOOKING_STATUS , PAYMENT_ID, JOURNEY_ID, SEATS_BOOKED) VALUES  "
				+ " ( ( SELECT ID FROM COMMON_VALUES WHERE ID_TYPE = 'BOOKING_STATUS' AND ID_DESCRIPTION = 'CONFIRMED' ), " + paymentId + "," + journeyId + ","
				+ seatsBooked + ") ";

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

		if (bookingId > 0) {

			String UPDATE_JOURNEY_DETAILS = "  UPDATE JOURNEY_DETAILS SET SEATS_BOOKED = ( SEATS_BOOKED + " + seatsBooked + " ) WHERE JOURNEY_ID =  "
					+ journeyId;

			/*
			 * " UPDATE JOURNEY_DETAILS SET SEATS_BOOKED = (( " + "  = " +
			 * journeyId +" ) + " + seatsBooked + ") WHERE JOURNEY_ID = " +
			 * journeyId;
			 */

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

	public boolean cancelBooking(Connection connection, int bookingId) throws Exception {
		Statement statement = null;
		boolean bookingCancelled = false;
		int cancelled = 0;

		String CANCEL_BOOKING = " UPDATE BOOKING_DETAILS SET BOOKING_CANCELLED = 1 , BOOKING_STATUS =  ( SELECT ID FROM COMMON_VALUES WHERE ID_TYPE = 'BOOKING_STATUS' AND"
				+ "  ID_DESCRIPTION = 'CANCELLED' ) WHERE BOOKING_ID = " + bookingId;

		try {
			statement = connection.createStatement();
			cancelled = statement.executeUpdate(CANCEL_BOOKING);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}

		int seatCount = 0;
		int journeyId = 0;

		String SEAT_NUMBER = " SELECT SEATS_BOOKED, JOURNEY_ID FROM BOOKING_DETAILS WHERE BOOKING_ID =  " + bookingId;
		Statement seatCountStatement = null;
		ResultSet seatCountResultSet = null;

		try {
			seatCountStatement = connection.createStatement();
			seatCountResultSet = seatCountStatement.executeQuery(SEAT_NUMBER);

			if (seatCountResultSet.next()) {
				seatCount = seatCountResultSet.getInt("SEATS_BOOKED");
				journeyId = seatCountResultSet.getInt("JOURNEY_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(seatCountStatement);
			ProjectHelper.closeResultSet(seatCountResultSet);
		}

		String UPDATE_JOURNEY_DETAILS = "  UPDATE JOURNEY_DETAILS SET SEATS_BOOKED = ( SEATS_BOOKED - " + seatCount + " ) WHERE JOURNEY_ID =  " + journeyId;

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

		if (cancelled > 0) {
			bookingCancelled = true;
		}

		return bookingCancelled;
	}

	public ReservationLists listAllReservations(Connection connection) throws Exception {
		Statement statement = null;
		ResultSet resultSet = null;

		List<ReservationBean> reservatons = new ArrayList<ReservationBean>();
		List<ReservationBean> cancelledReservatons = new ArrayList<ReservationBean>();
		ReservationBean reservationBean = null;

		String RESERVATION_LIST = " SELECT SOURCE_LOC.AIRPORT_NAME SOURCE_AIRPORT, JOURNEY_DETAILS.DEPARTURE_TIME, DESTI_LOC.AIRPORT_NAME DESTINATION_AIRPORT, JOURNEY_DETAILS.ARRIVAL_TIME , RES_STATUS.ID_DESCRIPTION BOOKING_STATUS, PAYMENT_STATUS.ID_DESCRIPTION PAYMENT_STATUS, CONCAT(PERSON.FIRST_NAME,' ', PERSON.LAST_NAME) NAME, PERSON.LAST_NAME, BOOKING_DETAILS.BOOKING_ID FROM BOOKING_DETAILS INNER JOIN JOURNEY_DETAILS ON BOOKING_DETAILS.JOURNEY_ID = JOURNEY_DETAILS.JOURNEY_ID INNER JOIN  LOCATIONS SOURCE_LOC ON JOURNEY_DETAILS.FLIGHT_SOURCE = SOURCE_LOC.LOCATION_ID INNER JOIN LOCATIONS DESTI_LOC ON JOURNEY_DETAILS.FLIGHT_SOURCE = DESTI_LOC.LOCATION_ID INNER JOIN COMMON_VALUES RES_STATUS ON BOOKING_DETAILS.BOOKING_STATUS = RES_STATUS.ID INNER JOIN	PAYMENT_DETAILS ON BOOKING_DETAILS.PAYMENT_ID = PAYMENT_DETAILS.PAYMENT_ID INNER JOIN 	TICKET_DETAILS ON TICKET_DETAILS.BOOKING_ID = BOOKING_DETAILS.BOOKING_ID INNER JOIN PERSON ON TICKET_DETAILS.PERSON_ID = PERSON.PERSON_ID INNER JOIN	COMMON_VALUES PAYMENT_STATUS ON PAYMENT_STATUS.ID = PAYMENT_DETAILS.PAYMENT_STATUS ";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(RESERVATION_LIST);

			while (resultSet.next()) {
				reservationBean = new ReservationBean();
				reservationBean.setSourceAirport(resultSet.getString("SOURCE_AIRPORT"));
				reservationBean.setDepartureTime(resultSet.getString("DEPARTURE_TIME"));
				reservationBean.setDestinationAirport(resultSet.getString("DESTINATION_AIRPORT"));
				reservationBean.setArrivalTime(resultSet.getString("ARRIVAL_TIME"));
				reservationBean.setBookingStatus(resultSet.getString("ARRIVAL_TIME"));
				reservationBean.setPaymentstatus(resultSet.getString("PAYMENT_STATUS"));
				reservationBean.setCustomerName(resultSet.getString("NAME"));
				reservationBean.setBookingId(resultSet.getInt("BOOKING_ID"));
				if (reservationBean.getPaymentstatus().equalsIgnoreCase("CANCELLED")) {
					cancelledReservatons.add(reservationBean);
				} else {
					reservatons.add(reservationBean);
				}
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
		ReservationLists reservationLists = new ReservationLists();
		
		ReservationBean[] activeReservations = new ReservationBean[reservatons.size()];
		ReservationBean[] cancelledArray = new ReservationBean[cancelledReservatons.size()];
		
		int arrayIndex = 0;
		for (ReservationBean cancel : cancelledReservatons) {
			cancelledArray[arrayIndex++] = cancel;
		}
		 arrayIndex = 0;
		for (ReservationBean booking : reservatons) {
			activeReservations[arrayIndex++] = booking;
		}
		reservationLists.setActiveReservations(activeReservations);
		reservationLists.setCancelledReservations(cancelledArray); 

		return reservationLists;
	}
}
