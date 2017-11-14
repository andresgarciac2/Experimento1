package co.com.uniandes.sube.repository;

import org.hibernate.Session;

import com.sube.utilities.hibernate.HibernateUtility;

import co.com.uniandes.sube.dto.OfferStepConfigurationDTO;
import co.com.uniandes.sube.utilities.entities.AcademicOffer;
import co.com.uniandes.sube.utilities.entities.OfferStepConfiguration;

public class OfferStepConfigurationRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static OfferStepConfigurationDTO createOfferStepConfiguration(OfferStepConfigurationDTO stepConfig){
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		AcademicOffer ao = new AcademicOffer();
		ao.setId((int) stepConfig.getOfferId());
		
		// Create the step configuration
		OfferStepConfiguration osc = new OfferStepConfiguration();
		osc.setOfferId((int) stepConfig.getOfferId());
		osc.setSerializeSettings(stepConfig.getSerializeSettings());
		session.beginTransaction();		
		session.save(osc);
		session.getTransaction().commit();
		Integer id = (Integer)session.getIdentifier(osc);
		System.out.println("Step configuration successfully created with id " + id);
		stepConfig.setId(id);
		
	    return stepConfig;
	}	
	
	
	public static OfferStepConfigurationDTO updateOfferStepConfiguration(OfferStepConfigurationDTO stepConfig){
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		// Update the step configuration
		OfferStepConfiguration osc = (OfferStepConfiguration) session.get(OfferStepConfiguration.class, (int)stepConfig.getId());
		osc.setSerializeSettings(stepConfig.getSerializeSettings());
		session.beginTransaction();		
		session.merge(osc);
		session.getTransaction().commit();
		System.out.println("Step configuration successfully updated  with id " + stepConfig.getId());
		
		return stepConfig;
		
	}
	
	
}
