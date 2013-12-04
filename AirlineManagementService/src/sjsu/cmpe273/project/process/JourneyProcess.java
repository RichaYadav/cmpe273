package sjsu.cmpe273.project.process;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.JourneyDetailBean;
import sjsu.cmpe273.project.beans.LocationsBean;
import sjsu.cmpe273.project.dao.JourneyDetailsDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class JourneyProcess {
	public boolean scheduleJourney(JourneyDetailBean journeyDetailBean) {

		boolean isSuccess = true;

		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();

		JourneyDetailsDao journeyDetailsDao = new JourneyDetailsDao();
		try {
			isSuccess = journeyDetailsDao.scheduleJourney(connection, journeyDetailBean);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		return isSuccess;
	}
	
	public JourneyDetailBean[] searchJourneys(String to, String from, String time){
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		// search location to and from 
		LocationsProcess l = new LocationsProcess();
		LocationsBean source = l.searchLocation(from);
		LocationsBean destination = l.searchLocation(to);
		if(source == null || destination == null){
			return null;
		}else{
			JourneyDetailsDao jdd = new JourneyDetailsDao();
			return jdd.listJourneys(connection, destination, source, time);
		}
	}

	public boolean checkDuplicateFlight(JourneyDetailBean journeyDetailBean) {

		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();

		boolean isDuplicate = false;

		JourneyDetailsDao journeyDetailsDao = new JourneyDetailsDao();

		try {
			isDuplicate = journeyDetailsDao.checkDuplicateFlight(connection, journeyDetailBean);
		} catch (Exception e) {
			e.printStackTrace();
			isDuplicate = true;
		} finally {
			ProjectHelper.closeConnection(connection);
		}

		return isDuplicate;
	}
}
