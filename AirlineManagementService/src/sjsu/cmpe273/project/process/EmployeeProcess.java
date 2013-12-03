package sjsu.cmpe273.project.process;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.AirlineEmployee;

/*
 * By Frank;
 */
public class EmployeeProcess {
	
	AirlineEmployee airlineEmply = new AirlineEmployee();
	
<<<<<<< HEAD
	
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
=======
	public void createEmployeeProcess(AirlineEmployeeBean emply){
		airlineEmply.storeEmployeeInfo(emply);
>>>>>>> 2a3a9c4a6a90249c45478cd0ce53314602a568bb
	}
	
	public boolean deleteEmployeeProcess(int ssn){
		return airlineEmply.deleteEmployeeInfo(ssn);
	}
	
	public UserBean[] listAllEmployeesProcess(){
		return airlineEmply.selectAllEmployees();
	}
	
}
