package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.helper.ConnectJDBC;
import sjsu.cmpe273.project.beans.FlightDetailBean;
import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.TravelerBean;

// Shibai
public class CustomerDao {
	Connection connection=null;
	Statement st = null;
	ResultSet rs = null;
	
	// cancel a booking
	public String cancelBooking(TravelerBean travelerBean) {
		int booking_id = travelerBean.getBooking_id();
		String query = "update booking_details set booking_cancelled=1 where booking_id=" + booking_id;
		try{
			ConnectJDBC connectionJDBC = new ConnectJDBC();
			connection = connectionJDBC.connectDatabase();
			st = connection.createStatement();
			st.executeUpdate(query);
			connectionJDBC.closeConnection(null, st, connection);
			
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "cancelled";
	}
	
	// issue new ticket
	public boolean issueTicket(UserBean userBean) {
		TravelerBean ticket = userBean.getTraveler();
		
		String query = "insert into ticket_details(ssn, person_id, booking_id) values(" + ticket.getSsn() + ", "
				+ ticket.getPerson_id() + ", " + ticket.getBooking_id() + ")";
		try {
			ConnectJDBC connectionJDBC = new ConnectJDBC();
			connection = connectionJDBC.connectDatabase();
			st = connection.createStatement();
			st.executeUpdate(query);
			connectionJDBC.closeConnection(null, st, connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		return true;
	}
	
	// create booking
	public String createBooking (FlightDetailBean flightDetailBean, TravelerBean travelerBean) {
		String query = "insert into booking_details(booking_status, payment_id)";
		ConnectJDBC connectionJDBC = new ConnectJDBC(); // current password is set to "root"
		connection = connectionJDBC.connectDatabase();
		
		try{
			
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "";
	}
	
	
	
	
	// list all customers known to system
	public UserBean[] listAllCustomers () {
		String query = "select * from ticket_detail t  " +
				"inner join person p on t.person_id = p.person_id ";
		ConnectJDBC connectionJDBC = new ConnectJDBC(); // current password is set to "root"
		connection = connectionJDBC.connectDatabase();

		ArrayList<UserBean> users = new ArrayList<UserBean>();
		try{
			st = connection.createStatement();
			rs = st.executeQuery(query);
			
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
				
				TravelerBean traveler = new TravelerBean();
				traveler.setTicket_id(rs.getInt("ticket_id"));
				traveler.setSsn(rs.getInt("ssn"));
				traveler.setBooking_id(rs.getInt("booking_id"));
				traveler.setPerson_id(rs.getInt("p.person_id"));
				
				UserBean userBean = new UserBean();
				userBean.setPerson(person);
				userBean.setTraveler(traveler);
				
				users.add(userBean);
			}
			connectionJDBC.closeConnection(rs, st, connection);
		}catch (Exception e) {
			e.printStackTrace();
		}
		UserBean[] ret = new UserBean[users.size()];
		for(int i = 0; i < users.size() ; i++ ){
			ret[i] = (UserBean)users.get(i);
		}
		return ret;
	}
}
