<<<<<<< HEAD
<<<<<<< HEAD
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

	public int createEmployeeProcess(UserBean user) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		boolean isSuccess = true;
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
			isSuccess = pp.createPersonProcess(user);
			System.out.println("create user ----> " + isSuccess);
			if (isSuccess) {
				PersonBean p1 = pp.findPersonProcess(user);
				employee.setPerson_id(p1.getPerson_id());
				isSuccess = airlineEmply.storeEmployeeInfo(employee);
				if(isSuccess){
					return 1; // 1 = store successfully
				}else {
					return 0; // 0 = store unsuccessfully
				}
			}else{
				return 2; // 2 = creat person unsuccessfully
			}
		} else {
			// else get person id , create employee
			employee.setPerson_id(p.getPerson_id());
			UserBean[] users = airlineEmply.searchEmployee(connection, "person_id", user);
			if(users.length != 0){
				System.out.println("person_id ----> " + user.getEmployeeBean().getPerson_id());
				isSuccess = airlineEmply.storeEmployeeInfo(employee);
				if(isSuccess){
					return 3; // person exist previously, but not a employee. Now is employee
				}else{
					return 0;  
				}
			}else{
				return 4; // 4 = employee exist 
			}
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
=======
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
	
	// shibai
	public void editEmployee(UserBean employee) {
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		try{
			boolean flag = airlineEmply.updateEmployee(connection,employee);
			if (!flag) {
				System.out.println("update employee failed");
			}
			
		}catch (Exception e) {
			
		}
		
	}
}
>>>>>>> 1ea5f3739238d02587fa88b242637578ebf04d5d
=======
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
>>>>>>> 3eb31645174a318b7026c69098181be7b12b1fd1
