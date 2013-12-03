package sjsu.cmpe273.project.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectJDBC {
	public Connection connectDatabase() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AIRLINE_DATABASE", "root", "Janataraja-383");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	

	public void closeConnection(ResultSet rs, Statement st, Connection con) {

		try {
			if (rs != null) {

				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * For testing for DB connection Do not forget to change your username and
	 * password.
	 */

	public static void main(String[] arg0) {
		Connection connection = new ConnectJDBC().connectDatabase();
		try {
			if (!connection.isClosed()) {
				System.out.println("connection successfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
