package sjsu.cmpe273.project.process;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.beans.LocationsBean;
import sjsu.cmpe273.project.dao.LocationDetailsDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class LocationsProcess {
	public LocationsBean[] listAllLocations() {

		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();

		LocationsBean locationList[] = null;

		LocationDetailsDao locationDetailsDao = new LocationDetailsDao();
		try {
			locationList = locationDetailsDao.listAllLocations(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		return locationList;
	}

	public LocationsBean searchLocation(String location) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		LocationDetailsDao locationDetailsDao = new LocationDetailsDao();
		return locationDetailsDao.searchLocation(connection, location);
	}
}
