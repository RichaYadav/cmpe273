package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class JourneyDetailsDao {
	public boolean scheduleJourney(Connection connection, JourneyDetailBean journeyDetailBean) throws Exception {

		int returnValue = 0;
		boolean isInserted = true;
		Statement statement = null;

		String JOURNEY_DETAILS_INSERT = "INSERT INTO JOURNEY_DETAILS(FLIGHT_ID, FLIGHT_SOURCE, FLIGHT_DESTINATION, SEATS_AVAILABLE, TICKET_PRICE,DEPARTURE_TIME,ARRIVAL_TIME) VALUES ("
				+ journeyDetailBean.getFlight_id() + "," + journeyDetailBean.getFlight_source() + "," + journeyDetailBean.getFlight_destination() + ","
				+ journeyDetailBean.getSeats_available() + "," + journeyDetailBean.getTicket_price() + ",'" + journeyDetailBean.getDeparture_time() + "','" 
				+ journeyDetailBean.getArrival_time() + "')";
//		System.out.println(JOURNEY_DETAILS_INSERT);
		try {
			statement = connection.createStatement();
			returnValue = statement.executeUpdate(JOURNEY_DETAILS_INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}

		if (returnValue <= 0) {
			isInserted = false;
		}

		return isInserted;
	}
	
	public boolean checkDuplicateFlight(Connection connection, JourneyDetailBean journeyDetailBean) throws Exception {
		boolean isDuplicate = false;
		
		String deptDate[] = journeyDetailBean.getDeparture_time().split(" ");
		String departureDate = deptDate[0];
		System.out.println(departureDate);
		String JOURNEY_DUPLICATE = " SELECT * FROM JOURNEY_DETAILS WHERE FLIGHT_ID = "+ journeyDetailBean.getFlight_id()
				+ " AND FLIGHT_SOURCE = "+ journeyDetailBean.getFlight_source() +" AND FLIGHT_DESTINATION = " + journeyDetailBean.getFlight_destination()  
				+ " AND ( (DEPARTURE_TIME   > '"+ journeyDetailBean.getDeparture_time() +"'  AND  '"+ journeyDetailBean.getDeparture_time() +"' < ARRIVAL_TIME )  "
				+ " OR    (DEPARTURE_TIME   > '"+ journeyDetailBean.getArrival_time() +"'  AND  '"+ journeyDetailBean.getArrival_time() +"' < ARRIVAL_TIME )  )";
				//				+ " AND DATE_FORMAT(DEPARTURE_TIME,'%Y-%m-%d')  LIKE '"+departureDate+"' ";
		//((DEPARTURE_TIME   > '2013-11-29 22:46:00'  AND  '2013-11-29 22:46:00' < ARRIVAL_TIME ) OR (DEPARTURE_TIME   > '2013-11-29 22:46:00'  AND  '2013-11-29 22:46:00' < ARRIVAL_TIME))
		System.out.println(JOURNEY_DUPLICATE);
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(JOURNEY_DUPLICATE);
			
			if (resultSet.next()) {
				isDuplicate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
			ProjectHelper.closeResultSet(resultSet); 
		}
		return isDuplicate;
	}
}
