package Testing;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.AirlineEmployeeDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;

public class EditTesting {
	
	
	public static void main(String[] args) {
		EditTesting ed = new EditTesting();
		ed.test();
		
	}
	
	public  void test(){
		AirlineEmployeeDao airlineEmply = new AirlineEmployeeDao();
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		UserBean user = new UserBean();
		AirlineEmployeeBean employee = new AirlineEmployeeBean();
		PersonBean person = new PersonBean();
		
		
		
		
		
		user.setEmployeeBean(employee);
		user.setPerson(person);
		
		
		airlineEmply.updateEmployee(connection, user);
		
		
	}
	
}
