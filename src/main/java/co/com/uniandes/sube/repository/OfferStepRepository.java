package co.com.uniandes.sube.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.sube.utilities.hibernate.HibernateUtility;

import co.com.uniandes.sube.dto.AcademicOfferDTO;
import co.com.uniandes.sube.dto.OfferStepConfigurationDTO;
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
		os.setPosition(step.getPosition());
		os.setType((int)step.getType());
		
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
	
	
	public static List<OfferStepDTO> getOfferSteps(AcademicOfferDTO offer){
		List<OfferStepDTO> offerStepList= new ArrayList<>();
		
		PreparedStatement preparedStatement = null;
		PreparedStatement ps = null;
	    String getOffers = "SELECT ID, NAME, TYPE, POSITION, OFFER_ID, CONFIGURATION_ID FROM OFFER_STEP WHERE 1 = 1";
		
	    if(offer.getId()!= 0){
	    	getOffers += " AND OFFER_ID = " +  offer.getId();
	    }
	    
	    System.out.println("query offer step: " + getOffers );
	    
	    try {
			preparedStatement = conn.conn.prepareStatement(getOffers);
			
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				OfferStepDTO offerStepTemp = new OfferStepDTO();
				offerStepTemp.setId(rs.getLong(1));
				offerStepTemp.setName(rs.getString(2));
				offerStepTemp.setType(rs.getLong(3));
				offerStepTemp.setPosition(rs.getInt(4));
				offerStepTemp.setOfferId(rs.getInt(5));
				int configurationId = rs.getInt(6);
				
				
				
			    String query = "SELECT ID, OFFER_ID, SERIALIZE_SETTINGS FROM OFFER_STEP_CONFIGURATION WHERE ID = " + configurationId;
			    
			    ps = conn.conn.prepareStatement(query);
			    
			    System.out.println("query configuration step: " + query );
				
			    // execute insert SQL stetement
				ResultSet rsQuery = ps.executeQuery();
				
				while(rsQuery.next()){
					OfferStepConfigurationDTO offerStepConfig = new OfferStepConfigurationDTO();
					offerStepConfig.setId(rsQuery.getInt(1));
					//offerStepConfig.setOfferId(rsQuery.getInt(2));
					offerStepConfig.setSerializeSettings(rsQuery.getString(3));
					
					offerStepTemp.setOfferStepConfiguration(offerStepConfig);
				}
				
				offerStepList.add(offerStepTemp);
			}
           
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return offerStepList;
	}
}
