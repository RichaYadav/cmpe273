package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sjsu.cmpe273.project.beans.PersonBean;
import sjsu.cmpe273.project.beans.ReservationDetailBean;
import sjsu.cmpe273.project.beans.TravelerBean;
import sjsu.cmpe273.project.beans.UserBean;
import sjsu.cmpe273.project.helper.ProjectHelper;

// Shibai
public class CustomerDao {


	public boolean createCustomer(UserBean customer) {
		boolean isCreated = false;
		String sql = "INSERT INTO COMMON_VALUES(ID_TYPE, ID_DESCRIPTION) VALUES ('PAYMENT_STATUS','FAILED'); ";

		return isCreated;
	}

	// cancel a booking
	public String cancelBooking(Connection connection, TravelerBean travelerBean) {
		Statement st = null;
		ResultSet rs = null;

		int booking_id = travelerBean.getBooking_id();
		String query = "update booking_details set booking_cancelled=1 where booking_id=" + booking_id;
		try {
			st = connection.createStatement();
			st.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		} finally {
			ProjectHelper.closeStatement(st);
			ProjectHelper.closeResultSet(rs);
		}
		return "cancelled";
	}

	// issue new ticket
	public boolean issueTicket(Connection connection, UserBean userBean) {

		Statement st = null;
		ResultSet rs = null;

		TravelerBean ticket = userBean.getTraveler();

		String query = "insert into ticket_details(ssn, person_id, booking_id) values(" + ticket.getSsn() + ", " + ticket.getPerson_id() + ", "
				+ ticket.getBooking_id() + ")";
		try {

			st = connection.createStatement();
			st.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			ProjectHelper.closeStatement(st);
			ProjectHelper.closeResultSet(rs);
		}
		return true;
	}

	// create booking
	public int createBooking(Connection connection, ReservationDetailBean reserve) {

		Statement st = null;
		ResultSet rs = null;

		String sql = "insert into booking_details(booking_status, payment_id ,JOURNEY_ID) " + "value(" + reserve.getBookingDetailBean().getBooking_status()
				+ "," + "" + reserve.getBookingDetailBean().getPayment_id() + "," + "" + reserve.getBookingDetailBean().getJournry_id() + ")";

		try {
			st = connection.createStatement();
			System.out.println("check executeupdate() return value--->" + st.executeUpdate(sql));
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			ProjectHelper.closeStatement(st);
			ProjectHelper.closeResultSet(rs);
		}

	}

	// list all customers known to system
	public UserBean[] listAllCustomers(Connection connection) {
		Statement st = null;
		ResultSet rs = null;

		String query = "select * from ticket_detail t  " + "inner join person p on t.person_id = p.person_id ";

		ArrayList<UserBean> users = new ArrayList<UserBean>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ProjectHelper.closeResultSet(rs);
			ProjectHelper.closeStatement(st);
		}
		UserBean[] ret = new UserBean[users.size()];
		for (int i = 0; i < users.size(); i++) {
			ret[i] = (UserBean) users.get(i);
		}
		return ret;
	}

	// shibai
	// copied and modified from searchEmployee()
	public UserBean[] searchCustomer (Connection connection ,String searchType, UserBean user) {
		
		String sql = "select * from ticket_details travl inner join person p on travl.person_id=p.person_id";
		if(searchType.equals("email")){
			sql = sql + " where email_addresss='"+user.getPerson().getEmail_address()+"'";
		}else if(searchType.equals("name")){
			sql = sql + " where last_name='"+user.getPerson().getLast_name()+"' " +
					"and first_name='"+user.getPerson().getFirst_name()+"'";
		}else if(searchType.equals("ssn")){
			sql = sql + " where ssn =" + user.getTraveler().getSsn();
		}else if(searchType.equals("passport_number")){
			sql = sql + " where passport_number='"+user.getPerson().getPassport_number()+"'";
		}
		System.out.println("searchCustomer().SQL----->"+sql);
		return formUserBeans(connection , sql);
		
		
	}
	
	
	public  UserBean[] formUserBeans(Connection connection , String sql){
		List<UserBean> employees = new ArrayList<UserBean>();
		Statement st = null;
		ResultSet rs = null;
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
				
				TravelerBean  traveler = new TravelerBean();
				traveler.setSsn(rs.getInt("ssn"));
				traveler.setTicket_id(rs.getInt("ticket_id"));
				traveler.setPerson_id(rs.getInt("person_id"));
				traveler.setBooking_id(rs.getInt("booking_id"));
				
				
				UserBean userBean = new UserBean();
				userBean.setPerson(person);
				userBean.setTraveler(traveler);
				
				employees.add(userBean);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ProjectHelper.closeResultSet(rs);
			ProjectHelper.closeStatement(st);
		}
		System.out.println(employees.size());
		UserBean[] employeesArray = new UserBean[employees.size()];
		for(int i = 0; i < employees.size() ; i++ ){
			employeesArray[i] = (UserBean)employees.get(i);
		}

		return employeesArray;
	}	
	
	
	/*
	 * public ReservationDetailBean[] selectAllReservations(Connection
	 * connection){ List<ReservationDetailBean> reservations = new
	 * ArrayList<ReservationDetailBean>(); String sql =
	 * "select * from journey_details jd " +
	 * "inner join flight_details fd on jd.FLIGHT_ID = fd.FLIGHT_ID " +
	 * "inner join booking_details bd on  jd.JOURNEY_ID = bd.JOURNEY_ID " +
	 * "inner join ticket_details td on td.BOOKING_ID = bd.BOOKING_ID " +
	 * "inner join person pe on td.PERSON_ID = pe.PERSON_ID " +
	 * "inner join location l on "; try { st = connection.createStatement(); rs
	 * = st.executeQuery(sql); while(rs.next()){ JourneyDetailBean journey = new
	 * JourneyDetailBean();
	 * journey.setFlight_id(Integer.parseInt(rs.getString("jd.flight_id")));
	 * journey.setArrival_time(rs.getString("arrival_time"));
	 * journey.setDeparture_time(rs.getString("departure_time"));
	 * //journey.setFlight_destination(rs.getString("flight_destination"));
	 * //journey.set
	 * 
	 * } } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * } /* public ReservationDetailBean[]
	 * selectALlReservationsByCustomer(Connection connection, UserBean customers
	 * ){ ArrayList<ReservationDetailBean> reservations = new
	 * ArrayList<ReservationDetailBean>();
	 * 
	 * 
	 * return reservations; }
	 */

}
