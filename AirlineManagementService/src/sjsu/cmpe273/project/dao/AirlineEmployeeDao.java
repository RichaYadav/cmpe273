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
	
	
	public boolean deleteEmployeeInfo(Connection connection, int id){
		String sql_deleteEmply = "update person set person_deleted=1 where person_id="+id;
		
		try {
			st = connection.createStatement();
			st.executeUpdate(sql_deleteEmply);
			System.out.println(this.getClass().toString()+" method{deleteEmployeeInfo} successfully!");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(this.getClass().toString()+" method{deleteEmployeeInfo} error!");
			return false;
		}finally{
			ProjectHelper.closeStatement(st);
		}
	}
	
	public UserBean[] selectAllEmployees(Connection connection){
		String sql_selectEmply = "select * from airline_employee emp  " +
				"inner join person p on emp.person_id = p.person_id and p.person_deleted=0";
		//String sql_selectEmply1 = "select * from airline_employee";
		return formUserBeans( connection , sql_selectEmply);
	}
	
	
	public UserBean[] searchEmployee(Connection connection ,String searchType, UserBean user){
		String sql = "select * from airline_employee emp inner join person p on emp.person_id=p.person_id";
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
				person.setDob(rs.getString("birthday"));
				
				AirlineEmployeeBean employee = new AirlineEmployeeBean();
				employee.setPerson_id(rs.getInt("emp.person_id"));
				System.out.println("set id for empployee bean" +rs.getInt("emp.person_id"));
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
