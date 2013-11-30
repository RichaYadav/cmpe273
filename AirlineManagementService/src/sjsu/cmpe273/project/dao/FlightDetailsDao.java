package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class FlightDetailsDao {
	public FlightDetailBean[] listAllFlights(Connection connection) throws Exception {

		int flightCount = 0;
		FlightDetailBean flightArray[] = null;

		try {
			flightCount = getFlightCount(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		if (flightCount > 0) {
			flightArray = new FlightDetailBean[flightCount];
			FlightDetailBean flightDetailBean = null;

			Statement statement = null;
			ResultSet resultSet = null;

			String GET_FLIGHTS = " SELECT FLIGHT_ID, AIRLINE_ID, FLIGHT_NAME, DATE_FORMAT(DATE_ADDED,'%b %d %Y %h:%i %p') DATE_ADDED, SEATS FROM FLIGHT_DETAILS ";

			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(GET_FLIGHTS);
				int arrayIndex = 0;
				while (resultSet.next()) {
					flightDetailBean = new FlightDetailBean();
					flightDetailBean.setAirline_id(resultSet.getInt("AIRLINE_ID"));
					flightDetailBean.setDate_added(resultSet.getString("DATE_ADDED"));
					flightDetailBean.setFlight_id(resultSet.getInt("FLIGHT_ID"));
					flightDetailBean.setFlight_name(resultSet.getString("FLIGHT_NAME"));
					flightDetailBean.setSeats(resultSet.getInt("SEATS"));
					flightArray[arrayIndex++] = flightDetailBean;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} finally {
				ProjectHelper.closeStatement(statement);
				ProjectHelper.closeResultSet(resultSet);
			}
		}

		return flightArray;
	}

	private int getFlightCount(Connection connection) throws Exception {

		Statement countStatement = null;
		ResultSet resultSet = null;

		int flightCount = 0;

		String GET_FLIGHT_COUNT = "SELECT COUNT(FLIGHT_ID) FLIGHT_COUNT FROM FLIGHT_DETAILS";

		try {
			countStatement = connection.createStatement();
			resultSet = countStatement.executeQuery(GET_FLIGHT_COUNT);

			resultSet.next();
			flightCount = resultSet.getInt("FLIGHT_COUNT");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(countStatement);
		}

		return flightCount;
	}
}
