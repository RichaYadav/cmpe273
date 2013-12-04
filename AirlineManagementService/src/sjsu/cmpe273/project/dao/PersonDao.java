package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class PersonDao {
	Connection connection = null;
	Statement st = null;
	ResultSet rs = null;

	public boolean createPerson(Connection connection, UserBean user) {
		boolean isSuccessful = true;
		String sql = "insert into person(PERSON_TYPE ,FIRST_NAME,LAST_NAME,EMAIL_ADDRESSS,"
				+ "PASSPORT_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,STATE,COUNTRY,ZIP_CODE) " + "values("
				+ user.getPerson().getPerson_type()
				+ ",'"
				+ user.getPerson().getFirst_name()
				+ "','"
				+ user.getPerson().getLast_name()
				+ "','"
				+ user.getPerson().getEmail_address()
				+ "','"
				+ user.getPerson().getPassport_number()
				+ "','"
				+ user.getPerson().getAddress_line1()
				+ "','"
				+ user.getPerson().getAddress_line2()
				+ "','"
				+ user.getPerson().getCity()
				+ "','"
				+ user.getPerson().getState()
				+ "','"
				+ user.getPerson().getCountry() + "','" + user.getPerson().getZip_code() + "')";
		System.out.println(sql);

		try {
			st = connection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccessful = false;
		} finally {
			ProjectHelper.closeConnection(null);
		}

		return isSuccessful;
	}

	public UserBean findUser(String email, String password) {
		String sql = "select * from person p " +
				"inner join user_account u on person_id = user_id" + " where p.email_addresss='" + email + "'";
		System.out.println("sql --->  " + sql);
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		try {
			connection = connectionJDBC.connectDatabase();
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				UserBean user = new UserBean();
				PersonBean person = new PersonBean();
				if (rs.getString("user_password").equals(password)) {
					person.setFirst_name(rs.getString("p.first_name"));
					person.setLast_name(rs.getString("p.last_name"));
					person.setPerson_id(rs.getInt("person_id"));
					person.setEmail_address(rs.getString("email_addresss"));
					person.setPerson_type(rs.getInt("person_type"));
					System.out.println("person_type = " + rs.getInt("person_type"));

					user.setPerson(person);

					return user;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionJDBC.closeConnection(rs, st, connection);
		}
		return null;
	}

	public PersonBean selectPerson(String passport_number) {

		ConnectJDBC connectionJDBC = new ConnectJDBC();
		String sql = "select * from person where passport_number='" + passport_number + "'";
		try {
			connection = connectionJDBC.connectDatabase();
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				PersonBean person = new PersonBean();
				person.setPerson_id(rs.getInt("person_id"));
				return person;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionJDBC.closeConnection(rs, st, null);
		}
		return null;
	}
	
	// shibai
	public boolean updatePerson (Connection connection, PersonBean person) {
		// update person table

		//PersonBean person = user.getPerson();
		String query = "update PERSON set first_name='" + person.getFirst_name() + "', last_name='" + person.getLast_name() 
				+ "', EMAIL_ADDRESSS='" + person.getEmail_address() + "', PASSPORT_NUMBER='" + person.getPassport_number()
				+ "', ADDRESS_LINE1='" + person.getAddress_line1() + "', ADDRESS_LINE2='" + person.getAddress_line2()
				+ "', CITY='" + person.getCity() + "', STATE='" + person.getState() + "', COUNTRY='" + person.getCountry()
				+ "', ZIP_CODE='" + person.getZip_code() + "', PERSON_DELETED=" + person.getPerson_deleted() + ", Date_of_birth='" + person.getDob()
				+ "' where person_id=" + person.getPerson_id();
		System.out.println(query);
		try{
			st = connection.createStatement();
			st.executeUpdate(query);
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			ProjectHelper.closeStatement(st);
		}
		return true;
	}


}
