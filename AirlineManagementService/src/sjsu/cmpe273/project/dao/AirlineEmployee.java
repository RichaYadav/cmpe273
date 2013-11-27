package sjsu.cmpe273.project.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sjsu.cmpe273.project.beans.AirlineEmployeeBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.helper.ConnectJDBC;

/*
 * By Frank;
 */

public class AirlineEmployee {
	Connection connection=null;
	Statement st = null;
	ResultSet rs = null;
	
	public void storeEmployeeInfo(AirlineEmployeeBean employee){

		String sql_insertEmply = "insert into airline_employee(ssn , person_id , DESIGNATION)" +
		" value("+employee.getSsn()+", "+employee.getPerson_id()+" , "+employee.getDesignation()+") ";
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		connection = connectionJDBC.connectDatabase();
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_insertEmply);
			connectionJDBC.closeConnection(null, st, connection);
			System.out.println("Completing Storing! ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Storing Employee failed!");
		}	 
	}	
	
	public boolean deleteEmployeeInfo(int ssn){
		String sql_deleteEmply = "delete from airline_employee where ssn ="+ssn;
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		connection = connectionJDBC.connectDatabase();
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_deleteEmply);
			connectionJDBC.closeConnection(null, st, connection);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	
	public UserBean[] selectAllEmployees(){
		String sql_selectEmply = "select * from airline_employee emp  " +
				"inner join person p on emp.person_id = p.person_id ";
		//String sql_selectEmply1 = "select * from airline_employee";
		List employees = new ArrayList();
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		connection = connectionJDBC.connectDatabase();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql_selectEmply);
			while(rs.next()){
				PersonBean person = new PersonBean();
				person.setPerson_id(rs.getInt("p.person_id"));
				person.setPerson_type(rs.getInt("person_type"));
				person.setFirst_name(rs.getString("first_name"));
				person.setLast_name(rs.getString("last_name"));
				person.setEmail_address(rs.getString("email_addresss"));
				person.setPassport_number(rs.getString("passport_number"));
				person.setAddress_line1(rs.getString("address_line1"));
				person.setAddress_line2(rs.getString("address_line2"));
				person.setCity(rs.getString("city"));
				person.setState(rs.getString("state"));
				person.setCounrty(rs.getString("country"));
				person.setZip_code(rs.getString("zip_code"));
				
				AirlineEmployeeBean employee = new AirlineEmployeeBean();
				employee.setSsn(rs.getInt("ssn"));
				employee.setDesignation(rs.getInt("designation"));
				employee.setJoining_date(rs.getDate("joining_date").toString());
				
				UserBean userBean = new UserBean();
				userBean.setPerson(person);
				userBean.setEmployeeBean(employee);
				
				employees.add(userBean);
			}
			connectionJDBC.closeConnection(rs, st, connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		UserBean[] employeesArray = new UserBean[employees.size()];
		for(int i = 0; i < employees.size() ; i++ ){
			employeesArray[i] = (UserBean)employees.get(i);
		}
		
		return employeesArray;
	}

}
