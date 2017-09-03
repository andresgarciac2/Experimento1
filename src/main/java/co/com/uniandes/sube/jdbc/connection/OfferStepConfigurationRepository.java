package co.com.uniandes.sube.jdbc.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.com.uniandes.sube.persistence.OfferStepConfigurationDTO;

public class OfferStepConfigurationRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static long createOfferStepConfiguration(OfferStepConfigurationDTO stepConfig){
	    long idStepConfig= 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "INSERT INTO OFFER_STEP_CONFIGURATION (OFFER_ID, SERIALIZE_SETTINGS) VALUES (?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insertOffer, new String[]{"ID"});

			preparedStatement.setLong(1, stepConfig.getOfferId());
			preparedStatement.setString(2, stepConfig.getSerializeSettings());

			// execute insert SQL statement
			preparedStatement.executeUpdate();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				idStepConfig = generatedKeys.getLong(1);
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
	    return idStepConfig;
	}	
	
}
