package Testing;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.service.AirlineManagementService;

public class TestingOperationOnEmployee {

	/**
	 * @param args
	 */
	AirlineManagementService service = new AirlineManagementService();
	AirlineEmployeeBean employee = new AirlineEmployeeBean();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestingOperationOnEmployee test = new TestingOperationOnEmployee();
		
//		test.testCreatEmployee(1);
//		test.testDeleteEmployee();
		
//		test.testCreatEmployee(2);
//		test.testCreatEmployee(3);
//		test.testCreatEmployee(4);
//		test.testCreatEmployee(5);
//		test.testCreatEmployee(6);
//		test.testCreatEmployee(7);
//		test.testCreatEmployee(8);
		test.testListAllEmply();
//		UserBean u = test.testLogin("admin@admin.com","68018500");
//		System.out.println(u.getPerson().getFirst_name());
	}
	
	public UserBean testLogin(String email, String password){
		return service.login(email, password);
	}
	
	public void testCreatEmployee(int i){
		
		employee.setSsn(12345+i);
		employee.setPerson_id(1111+i);
		employee.setDesignation(111);
		UserBean user = new UserBean();
		user.setEmployeeBean(employee);
//		service.createEmployee(user);
	}
	
	public void testDeleteEmployee(){/*
		if(service.deleteEmployee(employee.getSsn())){
			System.out.println("delete successfully");
		}else{
			System.out.println("delete UN_successfully!");
		}
	*/}
	
	public void testListAllEmply(){/*
		UserBean[] emplys = service.listAllEmployees();
		for(int i = 0 ; i < emplys.length ; i++){
			System.out.println("Employee["+i+"]--->SSn :" + emplys[i].getEmployeeBean().getSsn() );
		}
	*/}

}
