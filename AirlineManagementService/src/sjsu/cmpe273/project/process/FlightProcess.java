package sjsu.cmpe273.project.process;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.dao.FlightDetailsDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class FlightProcess {
	public FlightDetailBean[] listAllFlights() {
		
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();

		FlightDetailBean flightArray[] = null;
		
		FlightDetailsDao flightDetailsDao = new FlightDetailsDao();
		try {
			flightArray = flightDetailsDao.listAllFlights(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		 
		return flightArray;
	}
}
