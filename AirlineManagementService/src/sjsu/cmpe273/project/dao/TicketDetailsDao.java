package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class TicketDetailsDao {
	public boolean makeTicketEntries(Connection connection,int bookingId, UserBean[] users) throws Exception{
		PersonDao personDao = new PersonDao();
		PersonBean personBean = null;
		Statement statement = null;
		int inserted = 0;
		boolean isSuccess = true;
		try {
			
			for (UserBean userBean : users) {
				personBean = personDao.selectPerson(userBean.getPerson().getPassport_number());
				if(personBean == null ){
					personDao.createPerson(connection, userBean);
					personBean = personDao.selectPerson(userBean.getPerson().getPassport_number());
				}
				String INSERT_TICKET = " INSERT INTO TICKET_DETAILS(SSN,PERSON_ID,BOOKING_ID) VALUES (" + userBean.getTraveler().getSsn() +","
						+ personBean.getPerson_id() + "," + bookingId +") ";
				statement = connection.createStatement();
				inserted = statement.executeUpdate(INSERT_TICKET);
				if(inserted > 0){
					isSuccess = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}
		
		return isSuccess;
	}
}
