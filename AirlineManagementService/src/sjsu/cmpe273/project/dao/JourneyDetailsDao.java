package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.beans.LocationsBean;
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
	
	public JourneyDetailBean[] listAllJourneys(Connection connection) throws Exception{
		
		int journeyCount = 0;

		try {
			journeyCount = getJourneyCount(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		JourneyDetailBean journeyList[] = null;
		
		if (journeyCount > 0) {
			journeyList = new JourneyDetailBean[journeyCount];
			JourneyDetailBean journeyDetailBean = null;

			Statement statement = null;
			ResultSet resultSet = null;

			/*String GET_JOURNEYS = " SELECT JOURNEY_ID,FLIGHT_ID,FLIGHT_SOURCE,FLIGHT_DESTINATION,SEATS_AVAILABLE,SEATS_BOOKED,TICKET_PRICE, "
				+ " DATE_FORMAT(DEPARTURE_TIME,'%Y-%b-%d %h:%i %p') DEPARTURE_TIME, DATE_FORMAT(ARRIVAL_TIME,'%Y-%b-%d %h:%i %p') ARRIVAL_TIME, "
				+ " SOURCE_LOCATION.LOCATION_NAME SOURCE_LOCATION, SOURCE_LOCATION.AIRPORT_NAME SOURCE_AIRPORT, "
				+ " DESTINATION_LOCATION.LOCATION_NAME DESTINATION_LOCATION, DESTINATION_LOCATION.AIRPORT_NAME DESTINATION_AIRPORT FROM JOURNEY_DETAILS "
				+ " INNER JOIN LOCATIONS SOURCE_LOCATION ON SOURCE_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_SOURCE "
				+ " INNER JOIN LOCATIONS DESTINATION_LOCATION ON DESTINATION_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_DESTINATION ";*/
			
			String GET_JOURNEYS = " SELECT FLIGHT_NAME,JOURNEY_ID,FLIGHT_DETAILS.FLIGHT_ID,FLIGHT_SOURCE,FLIGHT_DESTINATION,SEATS_AVAILABLE,SEATS_BOOKED,TICKET_PRICE, DATE_FORMAT(DEPARTURE_TIME,'%Y-%b-%d %h:%i %p') DEPARTURE_TIME, DATE_FORMAT(ARRIVAL_TIME,'%Y-%b-%d %h:%i %p') ARRIVAL_TIME, SOURCE_LOCATION.LOCATION_NAME SOURCE_LOCATION, SOURCE_LOCATION.AIRPORT_NAME SOURCE_AIRPORT, DESTINATION_LOCATION.LOCATION_NAME DESTINATION_LOCATION, DESTINATION_LOCATION.AIRPORT_NAME DESTINATION_AIRPORT FROM JOURNEY_DETAILS INNER JOIN LOCATIONS SOURCE_LOCATION ON SOURCE_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_SOURCE INNER JOIN LOCATIONS DESTINATION_LOCATION ON DESTINATION_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_DESTINATION INNER JOIN  FLIGHT_DETAILS ON FLIGHT_DETAILS.FLIGHT_ID = JOURNEY_DETAILS.FLIGHT_ID ";
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(GET_JOURNEYS);
				
				LocationsBean sourceLocation = null;
				LocationsBean destinationLocation = null;
				
				int arrayIndex = 0;
				while (resultSet.next()) {
					journeyDetailBean = new JourneyDetailBean();
					
					sourceLocation = new LocationsBean();
					destinationLocation = new LocationsBean();
					
					journeyDetailBean.setJourney_id(resultSet.getInt("JOURNEY_ID"));
					journeyDetailBean.setFlight_id(resultSet.getInt("FLIGHT_ID"));
					journeyDetailBean.setFlight_source(resultSet.getInt("FLIGHT_SOURCE"));
					journeyDetailBean.setFlight_destination(resultSet.getInt("FLIGHT_DESTINATION"));
					journeyDetailBean.setSeats_available(resultSet.getInt("SEATS_AVAILABLE"));
					journeyDetailBean.setSeats_booked(resultSet.getInt("SEATS_BOOKED"));
					journeyDetailBean.setTicket_price(resultSet.getInt("TICKET_PRICE"));
					journeyDetailBean.setDeparture_time(resultSet.getString("DEPARTURE_TIME"));
					journeyDetailBean.setArrival_time(resultSet.getString("ARRIVAL_TIME"));
					journeyDetailBean.setFlight_name(resultSet.getString("FLIGHT_NAME"));
					
					sourceLocation.setLocation_name(resultSet.getString("SOURCE_LOCATION"));
					sourceLocation.setAirport_name(resultSet.getString("SOURCE_AIRPORT"));
					
					destinationLocation.setLocation_name(resultSet.getString("DESTINATION_LOCATION"));
					destinationLocation.setAirport_name(resultSet.getString("DESTINATION_AIRPORT"));
					
					journeyDetailBean.setSourceLocation(sourceLocation);
					journeyDetailBean.setDestinationLocation(destinationLocation);
					
					journeyList[arrayIndex++] = journeyDetailBean;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} finally {
				ProjectHelper.closeResultSet(resultSet);
				ProjectHelper.closeStatement(statement);
			}
		}
		return journeyList;
	}
	
	private int getJourneyCount(Connection connection) throws Exception{

		Statement countStatement = null;
		ResultSet resultSet = null;

		int locationCount = 0;

		String JOURNEY_COUNT = " SELECT COUNT(JOURNEY_ID) JOURNEY_COUNT FROM JOURNEY_DETAILS ";

		try {
			countStatement = connection.createStatement();
			resultSet = countStatement.executeQuery(JOURNEY_COUNT);

			resultSet.next();
			locationCount = resultSet.getInt("JOURNEY_COUNT");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeResultSet(resultSet);
			ProjectHelper.closeStatement(countStatement);
		}

		return locationCount;
	}
	
	public JourneyDetailBean getJourneyDetail(Connection connection, int journeyId) throws Exception{
		JourneyDetailBean journeyDetailBean = null;
		
//		JourneyDetailBean journeyList[] = null;
		
//		if (journeyCount > 0) {
//			JourneyDetailBean journeyDetailBean = null;

			Statement statement = null;
			ResultSet resultSet = null;

			/*String GET_JOURNEYS = " SELECT JOURNEY_ID,FLIGHT_ID,FLIGHT_SOURCE,FLIGHT_DESTINATION,SEATS_AVAILABLE,SEATS_BOOKED,TICKET_PRICE, "
				+ " DATE_FORMAT(DEPARTURE_TIME,'%Y-%b-%d %h:%i %p') DEPARTURE_TIME, DATE_FORMAT(ARRIVAL_TIME,'%Y-%b-%d %h:%i %p') ARRIVAL_TIME, "
				+ " SOURCE_LOCATION.LOCATION_NAME SOURCE_LOCATION, SOURCE_LOCATION.AIRPORT_NAME SOURCE_AIRPORT, "
				+ " DESTINATION_LOCATION.LOCATION_NAME DESTINATION_LOCATION, DESTINATION_LOCATION.AIRPORT_NAME DESTINATION_AIRPORT FROM JOURNEY_DETAILS "
				+ " INNER JOIN LOCATIONS SOURCE_LOCATION ON SOURCE_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_SOURCE "
				+ " INNER JOIN LOCATIONS DESTINATION_LOCATION ON DESTINATION_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_DESTINATION ";*/
			
			String GET_JOURNEYS = " SELECT FLIGHT_NAME,JOURNEY_ID,FLIGHT_DETAILS.FLIGHT_ID,FLIGHT_SOURCE,FLIGHT_DESTINATION,SEATS_AVAILABLE,SEATS_BOOKED,TICKET_PRICE, DATE_FORMAT(DEPARTURE_TIME,'%Y-%b-%d %h:%i %p') DEPARTURE_TIME, DATE_FORMAT(ARRIVAL_TIME,'%Y-%b-%d %h:%i %p') ARRIVAL_TIME, SOURCE_LOCATION.LOCATION_NAME SOURCE_LOCATION, SOURCE_LOCATION.AIRPORT_NAME SOURCE_AIRPORT, DESTINATION_LOCATION.LOCATION_NAME DESTINATION_LOCATION, DESTINATION_LOCATION.AIRPORT_NAME DESTINATION_AIRPORT FROM JOURNEY_DETAILS INNER JOIN LOCATIONS SOURCE_LOCATION ON SOURCE_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_SOURCE INNER JOIN LOCATIONS DESTINATION_LOCATION ON DESTINATION_LOCATION.LOCATION_ID = JOURNEY_DETAILS.FLIGHT_DESTINATION INNER JOIN  FLIGHT_DETAILS ON FLIGHT_DETAILS.FLIGHT_ID = JOURNEY_DETAILS.FLIGHT_ID WHERE JOURNEY_ID = " + journeyId;
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(GET_JOURNEYS);
				
				LocationsBean sourceLocation = null;
				LocationsBean destinationLocation = null;
				
//				int arrayIndex = 0;
				if (resultSet.next()) {
					journeyDetailBean = new JourneyDetailBean();
					
					sourceLocation = new LocationsBean();
					destinationLocation = new LocationsBean();
					
					journeyDetailBean.setJourney_id(resultSet.getInt("JOURNEY_ID"));
					journeyDetailBean.setFlight_id(resultSet.getInt("FLIGHT_ID"));
					journeyDetailBean.setFlight_source(resultSet.getInt("FLIGHT_SOURCE"));
					journeyDetailBean.setFlight_destination(resultSet.getInt("FLIGHT_DESTINATION"));
					journeyDetailBean.setSeats_available(resultSet.getInt("SEATS_AVAILABLE"));
					journeyDetailBean.setSeats_booked(resultSet.getInt("SEATS_BOOKED"));
					journeyDetailBean.setTicket_price(resultSet.getInt("TICKET_PRICE"));
					journeyDetailBean.setDeparture_time(resultSet.getString("DEPARTURE_TIME"));
					journeyDetailBean.setArrival_time(resultSet.getString("ARRIVAL_TIME"));
					journeyDetailBean.setFlight_name(resultSet.getString("FLIGHT_NAME"));
					
					sourceLocation.setLocation_name(resultSet.getString("SOURCE_LOCATION"));
					sourceLocation.setAirport_name(resultSet.getString("SOURCE_AIRPORT"));
					
					destinationLocation.setLocation_name(resultSet.getString("DESTINATION_LOCATION"));
					destinationLocation.setAirport_name(resultSet.getString("DESTINATION_AIRPORT"));
					
					journeyDetailBean.setSourceLocation(sourceLocation);
					journeyDetailBean.setDestinationLocation(destinationLocation);
					
//					journeyList[arrayIndex++] = journeyDetailBean;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} finally {
				ProjectHelper.closeResultSet(resultSet);
				ProjectHelper.closeStatement(statement);
			}
//		}
		return journeyDetailBean;
	}
	
	public boolean updateJourney(Connection connection, JourneyDetailBean journeyDetailBean) throws Exception{
		
		String UPDATE_JOURNEY = " UPDATE JOURNEY_DETAILS SET DEPARTURE_TIME = '"+ journeyDetailBean.getDeparture_time() +"' , ARRIVAL_TIME = '"+ journeyDetailBean.getArrival_time() +"' WHERE JOURNEY_ID = " + journeyDetailBean.getJourney_id();
		
		Statement statement = null;
		System.out.println(UPDATE_JOURNEY);
		boolean isInserted = true;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(UPDATE_JOURNEY);
		} catch  (SQLException e) {
			isInserted = false;
			e.printStackTrace();
			throw new Exception(e.getMessage());				
		} catch (Exception e) {
			isInserted = false;
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}
		return isInserted;
	}
	
	public boolean cancelJourney(Connection connection, int journeyId) throws Exception{
		
		String CANCEL_JOURNEY = " UPDATE JOURNEY_DETAILS SET FLIGHT_CANCELLED = 1 WHERE JOURNEY_ID =  " + journeyId;
		
		Statement statement = null;
		System.out.println(CANCEL_JOURNEY);
		boolean isCancelled = true;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(CANCEL_JOURNEY);
		} catch  (SQLException e) {
			isCancelled = false;
			e.printStackTrace();
			throw new Exception(e.getMessage());				
		} catch (Exception e) {
			isCancelled = false;
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}
		return isCancelled;
	}
	
	public JourneyDetailBean[] listJourneys(Connection connection,
			LocationsBean to, LocationsBean from, String time) {
		Statement statement = null;
		ResultSet resultSet = null;
		/*
		String sql = "select * from journey_details where DEPARTURE_TIME = '"
				+ time + "' " + "and FLIGHT_SOURCE = '" + from.getLocation_id()
				+ "' and FLIGHT_DESTINATION='" + to.getLocation_id() + "' ";
		*/
		String sql = "select * from journey_details jd " +
				"inner join airline_details ad on jd.AIRLINE_ID = ad.AIRLINE_ID" +
				" where  FLIGHT_SOURCE = " + from.getLocation_id()
			+ " and FLIGHT_DESTINATION=" + to.getLocation_id() + " and FLIGHT_CANCELLED = 0";
		ArrayList<JourneyDetailBean> journeys = new ArrayList<JourneyDetailBean>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				JourneyDetailBean j = new JourneyDetailBean();
				j.setDestinationLocation(to);
				j.setSourceLocation(from);
				j.setArrival_time(resultSet.getString("arrival_time"));
				j.setDeparture_time(resultSet.getString("departure_time"));
				j.setSeats_available(resultSet.getInt("SEATS_AVAILABLE"));
				j.setSeats_booked(resultSet.getInt("seats_booked"));
				j.setTicket_price(resultSet.getFloat("ticket_price"));
				j.setJourney_status(resultSet.getInt("FLIGHT_CANCELLED"));
				j.setFlight_id(resultSet.getInt("flight_id"));
				j.setArrival_time(resultSet.getString("airline_name"));
				journeys.add(j);

			}
			System.out.println(this.getClass().toString()+" class Success ---> method{listJourneys()}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(this.getClass().toString()+" class errot---> method{listJourneys()}");
			System.out.println("sQL----->"+sql);
		}
		JourneyDetailBean[] jd = new JourneyDetailBean[journeys.size()];
		for (int i = 0; i < journeys.size(); i++) {
			jd[i] = journeys.get(i);
		}
		return jd;
	}
}
