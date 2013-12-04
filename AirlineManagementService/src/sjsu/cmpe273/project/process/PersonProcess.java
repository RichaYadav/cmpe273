package sjsu.cmpe273.project.process;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.PersonDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;

public class PersonProcess {
	PersonDao personDao = new PersonDao();
	
	
	public boolean createPersonProcess(UserBean user){
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		boolean isSuccess = true;
		try {
			isSuccess = personDao.createPerson(connection,user);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public PersonBean findPersonProcess(UserBean user){
		return personDao.selectPerson(user.getPerson().getPassport_number());
	}
	
	public UserBean loginProcess(String email , String password){
		return personDao.findUser(email, password);
	}
}
