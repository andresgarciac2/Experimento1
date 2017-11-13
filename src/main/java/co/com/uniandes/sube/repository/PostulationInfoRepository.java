package co.com.uniandes.sube.repository;

import org.hibernate.Session;

import co.com.uniandes.sube.dto.PostulationInfoDTO;
import co.com.uniandes.sube.utilities.entities.PostulationInfo;

import com.sube.utilities.hibernate.HibernateUtility;

public class PostulationInfoRepository {

	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static PostulationInfoDTO createPostulationInfo(PostulationInfoDTO postulationInfo){
		Session session = HibernateUtility.getSessionFactory().openSession();

		// Create the postulation info
		PostulationInfo pI = new PostulationInfo();
		pI.setAttributeId(postulationInfo.getAttributeId());
		pI.setBoolValue(postulationInfo.getBoolValue());
		pI.setDateValue(postulationInfo.getDateValue());
		pI.setDecimalValue(postulationInfo.getDecimalValue());
		pI.setIntValue(postulationInfo.getIntValue());
		pI.setStringValue(postulationInfo.getStringValue());
		pI.setPostulationId(postulationInfo.getPostulationId());
		
		session.beginTransaction();		
		session.save(pI);
		session.getTransaction().commit();
		Integer id = (Integer)session.getIdentifier(pI);
		postulationInfo.setId(id);
		System.out.println("Postulation Info successfully created with id " + postulationInfo.getId() + ". Postulation id: " + postulationInfo.getPostulationId());

		return postulationInfo;
	}
	
	public static PostulationInfoDTO updatePostulationInfo(PostulationInfoDTO postulationInfo){
		Session session = HibernateUtility.getSessionFactory().openSession();

		// Update the postulation info
		PostulationInfo pI = new PostulationInfo();
		pI.setId(postulationInfo.getId());
		pI.setAttributeId(postulationInfo.getAttributeId());
		pI.setBoolValue(postulationInfo.getBoolValue());
		pI.setDateValue(postulationInfo.getDateValue());
		pI.setDecimalValue(postulationInfo.getDecimalValue());
		pI.setIntValue(postulationInfo.getIntValue());
		pI.setStringValue(postulationInfo.getStringValue());
		pI.setPostulationId(postulationInfo.getPostulationId());
		
		session.beginTransaction();		
		session.merge(pI);
		session.getTransaction().commit();
		System.out.println("Postulation Info successfully updated with id " + postulationInfo.getId() + ". Postulation id: " + postulationInfo.getPostulationId());
	
		return postulationInfo;
	}
	
}
