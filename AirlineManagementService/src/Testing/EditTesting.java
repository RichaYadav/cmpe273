package Testing;

import java.sql.Connection;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.dao.AirlineEmployeeDao;
import sjsu.cmpe273.project.dao.CustomerDao;
import sjsu.cmpe273.project.helper.ConnectJDBC;

public class EditTesting {
	
	
	public static void main(String[] args) {
		EditTesting ed = new EditTesting();
		ed.testSearch();
		
	}
	
	public void testSearch () {
		CustomerDao customerDao = new CustomerDao();
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		
		UserBean user = new UserBean();
		PersonBean person = new PersonBean();
		
		person.setFirst_name("ff");
		person.setLast_name("ll");
		person.setEmail_address("ee");
		user.setPerson(person);
		
		
		UserBean[] result = customerDao.searchCustomer(connection, "email", user);
		//System.out.println(result[0].getPerson().getPerson_id());
		
	}
	
	
	public  void test(){
		AirlineEmployeeDao airlineEmply = new AirlineEmployeeDao();
		Connection connection = null;
		ConnectJDBC connectJDBC = new ConnectJDBC();
		connection = connectJDBC.connectDatabase();
		
		UserBean user = new UserBean();
		AirlineEmployeeBean employee = new AirlineEmployeeBean();
		PersonBean person = new PersonBean();
		
		person.setPerson_id(1);
		person.setFirst_name("ff");
		person.setLast_name("ll");
		person.setEmail_address("ee");
		person.setAddress_line1("adddd1");
		person.setAddress_line2("adddd2");
		person.setPassport_number("1111");
		person.setCity("c");
		person.setState("ss");
		person.setCountry("CC");
		person.setZip_code("95");
		person.setPerson_deleted(1);
		person.setDob("2000-02-01");
		
		
		//user.setEmployeeBean(employee);
		user.setPerson(person);
		
		
		//airlineEmply.updateEmployee(connection, user);
		
		
	}
	
}
