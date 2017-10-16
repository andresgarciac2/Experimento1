package co.com.uniandes.sube.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.com.uniandes.sube.dto.OfferTransitionDTO;

public class OfferTransitionRepository {
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static long createOfferTransition(OfferTransitionDTO stepTrans){
	    long idOfferTrans = 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "INSERT INTO OFFER_TRANSITION (OFFER_ID, SOURCE_STEP, TARGET_STEP, CONDITIONS) VALUES (?,?,?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insertOffer, new String[]{"ID"});

			preparedStatement.setLong(1, stepTrans.getOfferId());
			preparedStatement.setLong(2, stepTrans.getSourceStep());
			preparedStatement.setLong(3, stepTrans.getTargetStep());
			preparedStatement.setString(4, stepTrans.getConditions());
			
			// execute insert SQL statement
			preparedStatement.executeUpdate();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				idOfferTrans = generatedKeys.getLong(1);
            }
           
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return idOfferTrans;
	}
	
}
