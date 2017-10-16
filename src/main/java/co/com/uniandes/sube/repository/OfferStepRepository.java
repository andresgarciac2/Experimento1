package co.com.uniandes.sube.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;

import com.sube.utilities.hibernate.HibernateUtility;

import co.com.uniandes.sube.dto.OfferStepDTO;
import co.com.uniandes.sube.utilities.entities.OfferStep;

/**
 * Class to manage the transactions of table Offer Step
 * @author Javier Mesa
 *
 */
public class OfferStepRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static OfferStepDTO createOfferStep(OfferStepDTO step){
		Session session = HibernateUtility.getSessionFactory().openSession();

		step.setOfferStepConfiguration(OfferStepConfigurationRepository.createOfferStepConfiguration(step.getOfferStepConfiguration()));
		OfferStep os = new OfferStep();
		os.setConfigurationId((int) step.getOfferStepConfiguration().getId());
		os.setOfferId((int) step.getOfferId());
		os.setName(step.getName());
				
		session.beginTransaction();		
		session.save(os);
		session.getTransaction().commit();
		Integer id = (Integer)session.getIdentifier(os);
		step.setId(id);
		System.out.println("Offer Step successfully created with id " + step.getId() + ". Academic Offer id: " + step.getOfferId());
		
		
		
	    /*long idStep = 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "INSERT INTO OFFER_STEP (CONFIGURATION_ID, OFFER_ID, NAME) VALUES (?,?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insertOffer, new String[]{"ID"});
			
			step.setOfferStepConfiguration(OfferStepConfigurationRepository.createOfferStepConfiguration(step.getOfferStepConfiguration()));
			
			preparedStatement.setLong(1, step.getOfferStepConfiguration().getId());
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
		}*/
	    return step;
	}
	
	public static int updateOfferStep(OfferStepDTO step){
		int result = 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "UPDATE OFFER_STEP SET CONFIGURATION_ID=?, OFFER_ID=?, NAME=? WHERE ID=?";
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
