package co.com.uniandes.sube.repository;

import org.hibernate.Session;

import com.sube.utilities.hibernate.HibernateUtility;

import co.com.uniandes.sube.dto.OfferTransitionDTO;
import co.com.uniandes.sube.utilities.entities.OfferTransition;

public class OfferTransitionRepository {
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static OfferTransitionDTO createOfferTransition(OfferTransitionDTO offerTrans){
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		// Create the offer transition
		OfferTransition ot = new OfferTransition();
		ot.setOfferId((int) offerTrans.getOfferId());
		ot.setSourceStep((int)offerTrans.getSourceStep());
		ot.setTargetStep((int)offerTrans.getTargetStep());
		ot.setConditions(offerTrans.getConditions());
		
		session.beginTransaction();		
		session.save(ot);
		session.getTransaction().commit();
		Integer id = (Integer)session.getIdentifier(ot);
		System.out.println("Offer Transition successfully created for offer " + offerTrans.getOfferId());
		offerTrans.setId(id);
		
	    return offerTrans;
	}
	
	
	public static OfferTransitionDTO updateOfferTransition(OfferTransitionDTO offerTrans){
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		OfferTransition ot = (OfferTransition) session.get(OfferTransition.class, (int)offerTrans.getId());
		ot.setSourceStep((int)offerTrans.getSourceStep());
		ot.setTargetStep((int)offerTrans.getTargetStep());
		session.beginTransaction();		
		session.merge(ot);
		session.getTransaction().commit();
		System.out.println("Offer Transition successfully updated for offer " + offerTrans.getOfferId());
		
	    return offerTrans;
	}
	
	
	public static OfferTransition getOfferTransition(OfferTransitionDTO offerTrans){
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		OfferTransition ot = (OfferTransition) session.get(OfferTransition.class, (int)offerTrans.getId());
		
		return ot;
	}
}
