package co.com.uniandes.sube.repository;

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
import co.com.uniandes.sube.utilities.entities.AcademicOffer;
import co.com.uniandes.sube.utilities.entities.OfferStep;
import co.com.uniandes.sube.utilities.entities.OfferStepConfiguration;

/**
 * Class to manage the transactions of table Offer Step
 * @author Javier Mesa
 *
 */
public class OfferStepRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static OfferStepDTO createOfferStep(OfferStepDTO step){
		Session session = HibernateUtility.getSessionFactory().openSession();

		// Create the step configuration
		step.setOfferStepConfiguration(OfferStepConfigurationRepository.createOfferStepConfiguration(step.getOfferStepConfiguration()));
		
		OfferStepConfiguration osc = new OfferStepConfiguration();
		osc.setId((int) step.getOfferStepConfiguration().getId());
		
		AcademicOffer ao = new AcademicOffer();
		ao.setId((int) step.getOfferId());
		
		// Create the step
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

		return step;
	}
	
	public static void updateOfferStep(OfferStepDTO step){
		Session session = HibernateUtility.getSessionFactory().openSession();

		// Update the step configuration
		step.setOfferStepConfiguration(OfferStepConfigurationRepository.updateOfferStepConfiguration(step.getOfferStepConfiguration()));
		
		// Create or update Offer transition
		if(step.getOfferTransition() != null && step.getOfferTransition().getId() ==0){
			step.setOfferTransition(OfferTransitionRepository.createOfferTransition(step.getOfferTransition()));
		} else if(step.getOfferTransition() != null){
			step.setOfferTransition(OfferTransitionRepository.updateOfferTransition(step.getOfferTransition()));
			
		}
		
		// Update the step
		OfferStep os = (OfferStep) session.get(OfferStep.class, (int)step.getId());
		os.setOfferId((int) step.getOfferId());
		os.setName(step.getName());
		os.setPosition(step.getPosition());
		os.setType((int)step.getType());
		
		session.beginTransaction();		
		session.merge(os);
		session.getTransaction().commit();
		System.out.println("Offer Step successfully updated with id " + step.getId() + ". Academic Offer id: " + step.getOfferId());
	
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
			
			// execute insert SQL statement
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
				
			    // execute insert SQL statement
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
