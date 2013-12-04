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
	
	public JourneyDetailBean[] listAllJourneys(){
		
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
//		JdbcPool pool = JdbcPool.getInstance();
//		connection = pool.getConnection();
//		
		System.out.println(connection + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(connection);
		
		JourneyDetailBean journeyList[] = null;
		JourneyDetailsDao journeyDetailsDao = new JourneyDetailsDao();
		
		try {
			journeyList = journeyDetailsDao.listAllJourneys(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		
		return journeyList;
	}
	
	public JourneyDetailBean getJourneyDetail(int journeyId) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		JourneyDetailsDao journeyDetailsDao = new JourneyDetailsDao();
		JourneyDetailBean journeyDetailBean = null;
		
		try {
			journeyDetailBean = journeyDetailsDao.getJourneyDetail(connection, journeyId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		
		return journeyDetailBean;
	}
	
	public boolean updateJourney(JourneyDetailBean journeyDetailBean){
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		JourneyDetailsDao journeyDetailsDao = new JourneyDetailsDao();
		
		boolean isInserted = true;
		
		try {
			isInserted = journeyDetailsDao.updateJourney(connection, journeyDetailBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		return isInserted;
	}
	
	public boolean cancelJourney(int journeyId){
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		boolean isCancelled = true;
		
		JourneyDetailsDao journeyDetailsDao = new JourneyDetailsDao();
		
		try {
			isCancelled = journeyDetailsDao.cancelJourney(connection, journeyId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ProjectHelper.closeConnection(connection);
		}
		return isCancelled;
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
}
