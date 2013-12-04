package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sjsu.cmpe273.project.beans.LocationsBean;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class LocationDetailsDao {
	public LocationsBean[] listAllLocations(Connection connection) throws Exception {

		int locationCount = 0;
		LocationsBean locationList[] = null;

		try {
			locationCount = getLocationsCount(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		if (locationCount > 0) {
			locationList = new LocationsBean[locationCount];
			LocationsBean locationsBean = null;

			Statement statement = null;
			ResultSet resultSet = null;

			String GET_LOCATIONS = " SELECT * FROM LOCATIONS ";
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(GET_LOCATIONS);

				int arrayIndex = 0;

				while (resultSet.next()) {
					locationsBean = new LocationsBean();
					locationsBean.setLocation_id(resultSet.getInt("LOCATION_ID"));
					locationsBean.setLocation_name(resultSet.getString("LOCATION_NAME"));
					locationsBean.setAirport_name(resultSet.getString("AIRPORT_NAME"));
					locationsBean.setAddress(resultSet.getString("ADDRESS"));
					locationsBean.setState(resultSet.getString("STATE"));
					locationsBean.setCountry(resultSet.getString("COUNTRY"));

					locationList[arrayIndex++] = locationsBean;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} finally {
				ProjectHelper.closeStatement(statement);
				ProjectHelper.closeResultSet(resultSet);
			}
		}

		return locationList;
	}

	private int getLocationsCount(Connection connection) throws Exception {

		Statement countStatement = null;
		ResultSet resultSet = null;

		int locationCount = 0;

		String GET_LOCATION_COUNT = " SELECT COUNT(LOCATION_ID) LOCATION_COUNT FROM LOCATIONS ";

		try {
			countStatement = connection.createStatement();
			resultSet = countStatement.executeQuery(GET_LOCATION_COUNT);

			resultSet.next();
			locationCount = resultSet.getInt("LOCATION_COUNT");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(countStatement);
		}

		return locationCount;
	}
	
	public LocationsBean searchLocation(Connection connection , String location){
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from locations where LOCATION_NAME = '"+location+"'";
		LocationsBean l = new LocationsBean();
		try {
			st = connection.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()){
				l.setLocation_id(Integer.parseInt(rs.getString("location_id")));
				l.setAirport_name(rs.getString("airport_name"));
				l.setAddress(rs.getString("address"));
				l.setCountry(rs.getString("country"));
				l.setLocation_name(rs.getString("location_name"));
				l.setState(rs.getString("state"));
				l.setZip_code(rs.getInt("zip_code"));
			}
			return l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ProjectHelper.closeResultSet(rs);
			ProjectHelper.closeStatement(st);
			
		}
	}
}
