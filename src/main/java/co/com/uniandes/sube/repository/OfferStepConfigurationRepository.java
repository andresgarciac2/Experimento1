package co.com.uniandes.sube.repository;

import org.hibernate.Session;

import com.sube.utilities.hibernate.HibernateUtility;

import co.com.uniandes.sube.dto.OfferStepConfigurationDTO;
import co.com.uniandes.sube.utilities.entities.OfferStepConfiguration;

public class OfferStepConfigurationRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static OfferStepConfigurationDTO createOfferStepConfiguration(OfferStepConfigurationDTO stepConfig){
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		OfferStepConfiguration osc = new OfferStepConfiguration();
		osc.setOfferId((int) stepConfig.getOfferId());
		osc.setSerializeSettings(stepConfig.getSerializeSettings());
		session.beginTransaction();		
		session.save(osc);
		session.getTransaction().commit();
		Integer id = (Integer)session.getIdentifier(osc);
		System.out.println("Se ha insertado la configuración del paso " + id);
		stepConfig.setId(id);
		
		
		
	    /*long idStepConfig= 0;
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
		}*/
	    return stepConfig;
	}	
	
}
