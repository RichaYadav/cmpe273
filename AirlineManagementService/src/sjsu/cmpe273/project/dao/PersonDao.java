package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.helper.ConnectJDBC;

public class PersonDao {
	Connection connection = null;
	Statement st = null;
	ResultSet rs = null;

	public void createPerson() {
	}

	public UserBean findUser(String email, String password) {
		String sql = "select * from person p inner join user_account u on person_id = user_id"
				+ " where p.email_addresss='" +email+ "'";
		System.out.println("sql --->  "+sql);
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		try {
			connection = connectionJDBC.connectDatabase();
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				UserBean user = new UserBean();
				PersonBean person = new PersonBean();
				if (rs.getString("user_password").equals(password)) {
					person.setFirst_name(rs.getString("p.first_name"));
					person.setLast_name(rs.getString("p.last_name"));
					person.setPerson_id(rs.getInt("person_id"));
					person.setEmail_address(rs.getString("email_addresss"));
					person.setPerson_type(rs.getInt("person_type"));
					System.out.println("person_type = "+rs.getInt("person_type"));

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
		PersonBean person = new PersonBean();
		ConnectJDBC connectionJDBC = new ConnectJDBC();
		String sql = "select * from person where passport_number='"
				+ passport_number + "'";
		try {
			connection = connectionJDBC.connectDatabase();
			st = connection.createStatement();
			st.executeQuery(sql);
			if (rs.next()) {
				person.setPerson_id(rs.getInt("person_id"));
				return person;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionJDBC.closeConnection(rs, st, connection);
		}
		return null;
	}

}
