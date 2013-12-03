package sjsu.cmpe273.project.process;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.AirlineEmployeeDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.helper.ProjectHelper;

/*
 * By Frank;
 */
public class EmployeeProcess {

	AirlineEmployeeDao airlineEmply = new AirlineEmployeeDao();

	public boolean createEmployeeProcess(UserBean user) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();

		PersonProcess pp = new PersonProcess();
		AirlineEmployeeBean employee = user.getEmployeeBean();
		// check if employee is already in the person table
		PersonBean p = pp.findPersonProcess(user);
		System.out.println("----------->" + p);
		if (p == null) {
			/*
			 * Here we need to improve with Statement.getGeneratedKey()
			 */
			// if not, create person , then create employee
			boolean isSuccess = pp.createPersonProcess(user);
			System.out.println("create user ----> " + isSuccess);
			if (isSuccess) {
				PersonBean p1 = pp.findPersonProcess(user);
				employee.setPerson_id(p1.getPerson_id());
				return airlineEmply.storeEmployeeInfo(employee);
			}else{
				return false;
			}
		} else {
			// else get person id , create employee
			employee.setPerson_id(p.getPerson_id());
			return airlineEmply.storeEmployeeInfo(employee);
		}
	}

	public boolean deleteEmployeeProcess(int ssn) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		boolean isSuccess = true;
		try {
			isSuccess = airlineEmply.deleteEmployeeInfo(connection ,ssn);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}finally{
			ProjectHelper.closeConnection(connection);
		}
		return isSuccess;
	}

	public UserBean[] listAllEmployeesProcess() {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		try {
			return airlineEmply.selectAllEmployees(connection);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			ProjectHelper.closeConnection(connection);	
		}
	}

	public UserBean[] searchEmployeeProcess(String searchType, UserBean user) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		try {
			return airlineEmply.searchEmployee(connection , searchType, user);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			ProjectHelper.closeConnection(connection);	
		}
		
	}
}
