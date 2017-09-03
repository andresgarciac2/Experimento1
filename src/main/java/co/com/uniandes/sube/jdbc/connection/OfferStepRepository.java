package co.com.uniandes.sube.jdbc.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.com.uniandes.sube.persistence.OfferStepDTO;

public class OfferStepRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static OfferStepDTO createOfferStep(OfferStepDTO step){
	    long idStep = 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "INSERT INTO OFFER_STEP (CONFIGURATION_ID, OFFER_ID, NAME) VALUES (?,?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insertOffer, new String[]{"ID"});
			
			long idStepConfig = OfferStepConfigurationRepository.createOfferStepConfiguration(step.getOfferStepConfiguration());
			step.getOfferStepConfiguration().setId(idStepConfig);
			
			preparedStatement.setLong(1, idStepConfig);
			preparedStatement.setLong(2, step.getOfferId());
			preparedStatement.setString(3, step.getName());

			// execute insert SQL statement
			preparedStatement.executeUpdate();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				idStep = generatedKeys.getLong(1);
				step.setId(idStep);
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
	    return step;
	}
	
	public static int updateOfferStep(OfferStepDTO step){
		int result = 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "UPDATE ACADEMIC_OFFER SET CONFIGURATION_ID=?, OFFER_ID=?, NAME=? WHERE ID=?";
		try {
			preparedStatement = conn.conn.prepareStatement(insertOffer);

			preparedStatement.setLong(1, step.getOfferStepConfiguration().getId());
			preparedStatement.setLong(2, step.getOfferId());
			preparedStatement.setString(3, step.getName());

			// execute insert SQL stetement
			result = preparedStatement.executeUpdate();
			preparedStatement.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return result;
	}
}
