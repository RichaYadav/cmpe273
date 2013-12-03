package sjsu.cmpe273.project.process;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.AirlineEmployeeDao;

/*
 * By Frank;
 */
public class EmployeeProcess {
	
	AirlineEmployeeDao airlineEmply = new AirlineEmployeeDao();
	
	
	public boolean createEmployeeProcess(UserBean user){
		PersonProcess pp = new PersonProcess();
		AirlineEmployeeBean employee = user.getEmployeeBean();
		//check if employee is already in the person table
		PersonBean p = pp.findPersonProcess(user);
		System.out.println("----------->"+p);
		if(p == null){
			/*
			 * Here we need to improve with Statement.getGeneratedKey()
			 */
			// if not, create person , then create employee 
			pp.createPersonProcess(user);
			PersonBean p1 = pp.findPersonProcess(user);
			employee.setPerson_id(p1.getPerson_id());
			return airlineEmply.storeEmployeeInfo(employee);
		}else{
			// else get person id , create employee
			employee.setPerson_id(p.getPerson_id());
			return airlineEmply.storeEmployeeInfo(employee);	
		}	
	}
	
	public boolean deleteEmployeeProcess(int ssn){
		return airlineEmply.deleteEmployeeInfo(ssn);
	}
	
	public UserBean[] listAllEmployeesProcess(){
		return airlineEmply.selectAllEmployees();
	}
	
	public UserBean[] searchEmployeeProcess(String searchType, UserBean user){
		return airlineEmply.searchEmployee(searchType, user);
	}
}
