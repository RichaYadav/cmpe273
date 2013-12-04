package sjsu.cmpe273.project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sjsu.cmpe273.project.beans.CommonValuesBean;
import sjsu.cmpe273.project.beans.PaymentDetailsBean;
import sjsu.cmpe273.project.helper.ProjectHelper;

public class PaymentDetailsDao {
	
	public List<CommonValuesBean> listPaymentMethods(Connection connection) throws Exception{
		Statement statement = null;
		ResultSet resultSet = null;

		List<CommonValuesBean> paymentMethodsList = new ArrayList<CommonValuesBean>();
		String PAYMENT_METHODS = " SELECT * FROM COMMON_VALUES WHERE ID_TYPE LIKE 'PAYMENT_METHOD' ";

		CommonValuesBean commonValuesBean = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(PAYMENT_METHODS);
			while (resultSet.next()) {
				commonValuesBean = new CommonValuesBean();
				commonValuesBean.setId(resultSet.getInt("ID"));
				commonValuesBean.setId_type(resultSet.getString("ID_TYPE"));
				commonValuesBean.setId_description(resultSet.getString("ID_DESCRIPTION"));
				paymentMethodsList.add(commonValuesBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeResultSet(resultSet);
			ProjectHelper.closeStatement(statement);
		}
		return paymentMethodsList;
	}
	
	public List<CommonValuesBean> listPaymentStatus(Connection connection) throws Exception{
		Statement statement = null;
		ResultSet resultSet = null;

		List<CommonValuesBean> paymentStatusList = new ArrayList<CommonValuesBean>();
		String PAYMENT_METHODS = " SELECT * FROM COMMON_VALUES WHERE ID_TYPE LIKE 'PAYMENT_STATUS' ";

		CommonValuesBean commonValuesBean = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(PAYMENT_METHODS);
			while (resultSet.next()) {
				commonValuesBean = new CommonValuesBean();
				commonValuesBean.setId(resultSet.getInt("ID"));
				commonValuesBean.setId_type(resultSet.getString("ID_TYPE"));
				commonValuesBean.setId_description(resultSet.getString("ID_DESCRIPTION"));
				paymentStatusList.add(commonValuesBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeResultSet(resultSet);
			ProjectHelper.closeStatement(statement);
		}
		return paymentStatusList;
	}
	
	public int updatePaymentStatus(Connection connection, int status, int paymentId) throws Exception {
		int paymentStatus = 0;
		String UPDATE_PAYMENT_STATUS = " UPDATE PAYMENT_DETAILS SET PAYMENT_STATUS = "+ status +" WHERE PAYMENT_ID = "+ paymentId;
		
		Statement statement = null;
		 
		
		try {
			statement = connection.createStatement();
			paymentStatus = statement.executeUpdate(UPDATE_PAYMENT_STATUS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}		
		return paymentStatus;
	}
	public int makePayment(Connection connection, PaymentDetailsBean paymentBean) throws Exception{
		int paymentId = 0; 
		int insertStatus = 0;
		Statement statement = null;
		
		String PAYMENT_QUERY = " INSERT INTO PAYMENT_DEATAILS(PAYMENT_METHOD, CARD_NUMBER, ACCOUNT_NUMBER, AMOUNT_PAID ) VALUES ("
				+ paymentBean.getPayment_method() + ","+ paymentBean.getCard_number() + "," + paymentBean.getAccount_number() 
				+","+ paymentBean.getAmount_paid() +  "  ) ";
		
		try {
			statement = connection.createStatement();
			insertStatus = statement.executeUpdate(PAYMENT_QUERY);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ProjectHelper.closeStatement(statement);
		}
		
		if(insertStatus <= 0){
			Statement paymentIdStatement = null;
			ResultSet paymentIdResultSet = null;
			
			String GET_PAYMENT_ID = " SELECT PAYMENT_ID,MAX(PAYMENT_TIME) FROM payment_details WHERE CARD_NUMBER = "+ paymentBean.getCard_number()+ 
					" OR ACCOUNT_NUMBER = " + paymentBean.getAccount_number();
			
			try {
				paymentIdStatement = connection.createStatement();
				paymentIdResultSet = statement.executeQuery(GET_PAYMENT_ID);
				
				if (paymentIdResultSet.next()) {
					paymentId = paymentIdResultSet.getInt("PAYMENT_ID");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			} finally {
				ProjectHelper.closeResultSet(paymentIdResultSet);
				ProjectHelper.closeStatement(paymentIdStatement);
			}
		}
		return paymentId;
	}
	
	
}
