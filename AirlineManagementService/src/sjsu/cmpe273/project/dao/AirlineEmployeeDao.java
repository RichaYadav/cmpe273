<<<<<<< HEAD
<<<<<<< HEAD
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
import sjsu.cmpe273.project.helper.ProjectHelper;

/*
 * By Frank;
 */

public class AirlineEmployeeDao {
	Connection connection=null;
	Statement st = null;
	ResultSet rs = null;
	
	public boolean storeEmployeeInfo(AirlineEmployeeBean employee){
		String sub_sql = "select id from common_values where id_type='person' and id_description = 'employee'";
		String sql_insertEmply = "insert into airline_employee(ssn , person_id , DESIGNATION)" +
		" value("+employee.getSsn()+", "+employee.getPerson_id()+" , ("+sub_sql+")) ";
		
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		connection = connectionJDBC.connectDatabase();
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_insertEmply);
			connectionJDBC.closeConnection(null, st, connection);
			System.out.println("Completing Storing! ");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Storing Employee failed!");
			return false;
		}	 
	}	
	
	public boolean deleteEmployeeInfo(Connection connection, int ssn){
		String sql_deleteEmply = "delete from airline_employee where ssn ="+ssn;
		
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_deleteEmply);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			ProjectHelper.closeStatement(st);
		}
	}
	
	//which method should be in personDao
	
	public UserBean[] selectAllEmployees(Connection connection){
		String sql_selectEmply = "select * from airline_employee emp  " +
				"inner join person p on emp.person_id = p.person_id ";
		//String sql_selectEmply1 = "select * from airline_employee";
		return formUserBeans( connection , sql_selectEmply);
	}
	
	
	public UserBean[] searchEmployee(Connection connection ,String searchType, UserBean user){
		String sql = "select * from airline_employee emply inner join person p on emply.person_id=p.person_id";
		if(searchType.equals("email")){
			sql = sql + " where email_addresss='"+user.getPerson().getEmail_address()+"'";
			System.out.println(sql);
		}else if(searchType.equals("name")){
			sql = sql + " where last_name='"+user.getPerson().getLast_name()+"' " +
					"and first_name='"+user.getPerson().getFirst_name()+"'";
		}else if(searchType.equals("ssn")){
			sql = sql + " where ssn =" + user.getEmployeeBean().getSsn();
		}else if(searchType.equals("passport_number")){
			sql = sql + " where passport_number='"+user.getPerson().getPassport_number()+"'";
		}else if(searchType.equals("person_id")){
			sql = sql + " where emply.person_id="+user.getEmployeeBean().getPerson_id();
		}
		System.out.println("searchEmployee().SQL----->"+sql);
		return formUserBeans(connection , sql);
	}
	
	
	public  UserBean[] formUserBeans(Connection connection , String sql){
		List<UserBean> employees = new ArrayList<UserBean>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ProjectHelper.closeResultSet(rs);
			ProjectHelper.closeStatement(st);
		}
		
		UserBean[] employeesArray = new UserBean[employees.size()];
		for(int i = 0; i < employees.size() ; i++ ){
			employeesArray[i] = (UserBean)employees.get(i);
		}

		return employeesArray;
	}
	
}
=======
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
import sjsu.cmpe273.project.helper.ProjectHelper;

/*
 * By Frank;
 */

public class AirlineEmployeeDao {
	Connection connection=null;
	Statement st = null;
	ResultSet rs = null;
	
	public boolean storeEmployeeInfo(AirlineEmployeeBean employee){
		String sub_sql = "select id from common_values where id_type='person' and id_description = 'employee'";
		String sql_insertEmply = "insert into airline_employee(ssn , person_id , DESIGNATION)" +
		" value("+employee.getSsn()+", "+employee.getPerson_id()+" , ("+sub_sql+")) ";
		
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		connection = connectionJDBC.connectDatabase();
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_insertEmply);
			connectionJDBC.closeConnection(null, st, connection);
			System.out.println("Completing Storing! ");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Storing Employee failed!");
			return false;
		}	 
	}	
	
	public boolean deleteEmployeeInfo(Connection connection, int ssn){
		String sql_deleteEmply = "delete from airline_employee where ssn ="+ssn;
		
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_deleteEmply);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			ProjectHelper.closeStatement(st);
		}
	}
	
	//which method should be in personDao
	
	
	public UserBean[] selectAllEmployees(Connection connection){
		String sql_selectEmply = "select * from airline_employee emp  " +
				"inner join person p on emp.person_id = p.person_id ";
		//String sql_selectEmply1 = "select * from airline_employee";
		return formUserBeans( connection , sql_selectEmply);
	}
	
	
	public UserBean[] searchEmployee(Connection connection ,String searchType, UserBean user){
		String sql = "select * from airline_employee emply inner join person p on emply.person_id=p.person_id";
		if(searchType.equals("email")){
			sql = sql + " where email_addresss='"+user.getPerson().getEmail_address()+"'";
			System.out.println(sql);
		}else if(searchType.equals("name")){
			sql = sql + " where last_name='"+user.getPerson().getLast_name()+"' " +
					"and first_name='"+user.getPerson().getFirst_name()+"'";
		}else if(searchType.equals("ssn")){
			sql = sql + " where ssn =" + user.getEmployeeBean().getSsn();
		}else if(searchType.equals("passport_number")){
			sql = sql + " where passport_number='"+user.getPerson().getPassport_number()+"'";
		}
		System.out.println("searchEmployee().SQL----->"+sql);
		return formUserBeans(connection , sql);
	}
	
	
	public  UserBean[] formUserBeans(Connection connection , String sql){
		List<UserBean> employees = new ArrayList<UserBean>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
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
				person.setCountry(rs.getString("country"));
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ProjectHelper.closeResultSet(rs);
			ProjectHelper.closeStatement(st);
		}
		
		UserBean[] employeesArray = new UserBean[employees.size()];
		for(int i = 0; i < employees.size() ; i++ ){
			employeesArray[i] = (UserBean)employees.get(i);
		}

		return employeesArray;
	}
	
	// shibai
	public boolean updateEmployee (Connection connection, UserBean user) {
		// update person table
		AirlineEmployeeBean employee = user.getEmployeeBean();
		
		String query1 = "update AIRLINE_EMPLOYEE set DESIGNATION={select id from COMMON_VALUES where ID_TYPE='person' and ID_DESCRIPTION='employee'}"
		+ " where person_id=" + employee.getPerson_id(); 

		// update employee table
		PersonBean person = user.getPerson();
		String query2 = "update PERSON set first_name=" + person.getFirst_name() + ", last_name=" + person.getLast_name() 
				+ ", EMAIL_ADDRESSS=" + person.getEmail_address() + ", PASSPORT_NUMBER=" + person.getPassport_number()
				+ ", ADDRESS_LINE1=" + person.getAddress_line1() + ", ADDRESS_LINE2=" + person.getAddress_line2()
				+ ", CITY=" + person.getCity() + ", STATE=" + person.getState() + ", COUNTRY=" + person.getCountry()
				+ ", ZIP_CODE=" + person.getZip_code() + ", PERSON_DELETED=" + person.getPerson_deleted() + ", DOB=" + person.getDob()
				+ " where person_id=" + person.getPerson_id();
		try{
			st = connection.createStatement();
			st.executeUpdate(query1);
			st.executeUpdate(query2);
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ProjectHelper.closeStatement(st);
		}
		return true;
	}
	
	
}
>>>>>>> 1ea5f3739238d02587fa88b242637578ebf04d5d
=======
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
import sjsu.cmpe273.project.helper.ProjectHelper;

/*
 * By Frank;
 */

public class AirlineEmployeeDao {
	Connection connection=null;
	Statement st = null;
	ResultSet rs = null;
	
	public boolean storeEmployeeInfo(AirlineEmployeeBean employee){
		String sub_sql = "select id from common_values where id_type='person' and id_description = 'employee'";
		String sql_insertEmply = "insert into airline_employee(ssn , person_id , DESIGNATION)" +
		" value("+employee.getSsn()+", "+employee.getPerson_id()+" , ("+sub_sql+")) ";
		
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		connection = connectionJDBC.connectDatabase();
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_insertEmply);
			connectionJDBC.closeConnection(null, st, connection);
			System.out.println("Completing Storing! ");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Storing Employee failed!");
			return false;
		}	 
	}	
	
	public boolean deleteEmployeeInfo(Connection connection, int ssn){
		String sql_deleteEmply = "delete from airline_employee where ssn ="+ssn;
		
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_deleteEmply);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			ProjectHelper.closeStatement(st);
		}
	}
	
	//which method should be in personDao
	
	
	public UserBean[] selectAllEmployees(Connection connection){
		String sql_selectEmply = "select * from airline_employee emp  " +
				"inner join person p on emp.person_id = p.person_id ";
		//String sql_selectEmply1 = "select * from airline_employee";
		return formUserBeans( connection , sql_selectEmply);
	}
	
	
	public UserBean[] searchEmployee(Connection connection ,String searchType, UserBean user){
		String sql = "select * from airline_employee emply inner join person p on emply.person_id=p.person_id";
		if(searchType.equals("email")){
			sql = sql + " where email_addresss='"+user.getPerson().getEmail_address()+"'";
			System.out.println(sql);
		}else if(searchType.equals("name")){
			sql = sql + " where last_name='"+user.getPerson().getLast_name()+"' " +
					"and first_name='"+user.getPerson().getFirst_name()+"'";
		}else if(searchType.equals("ssn")){
			sql = sql + " where ssn =" + user.getEmployeeBean().getSsn();
		}else if(searchType.equals("passport_number")){
			sql = sql + " where passport_number='"+user.getPerson().getPassport_number()+"'";
		}
		System.out.println("searchEmployee().SQL----->"+sql);
		return formUserBeans(connection , sql);
	}
	
	
	public  UserBean[] formUserBeans(Connection connection , String sql){
		List<UserBean> employees = new ArrayList<UserBean>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
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
				person.setCountry(rs.getString("country"));
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ProjectHelper.closeResultSet(rs);
			ProjectHelper.closeStatement(st);
		}
		
		UserBean[] employeesArray = new UserBean[employees.size()];
		for(int i = 0; i < employees.size() ; i++ ){
			employeesArray[i] = (UserBean)employees.get(i);
		}

		return employeesArray;
	}	
	
}
>>>>>>> 3eb31645174a318b7026c69098181be7b12b1fd1
