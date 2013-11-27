package sjsu.cmpe273.project.process;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.AirlineEmployee;

/*
 * By Frank;
 */
public class EmployeeProcess {
	
	AirlineEmployee airlineEmply = new AirlineEmployee();
	
	public void createEmployeeProcess(AirlineEmployeeBean emply){
		airlineEmply.storeEmployeeInfo(emply);
	}
	
	public boolean deleteEmployeeProcess(int ssn){
		return airlineEmply.deleteEmployeeInfo(ssn);
	}
	
	public UserBean[] listAllEmployeesProcess(){
		return airlineEmply.selectAllEmployees();
	}
	
}
