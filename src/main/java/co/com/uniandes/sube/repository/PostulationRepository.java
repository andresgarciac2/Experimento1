package co.com.uniandes.sube.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import co.com.uniandes.sube.dto.PostulationDTO;
import co.com.uniandes.sube.dto.PostulationInfoDTO;
import co.com.uniandes.sube.utilities.entities.Postulation;

import com.sube.utilities.hibernate.HibernateUtility;

/**
 * Class to manage the postulations of table Postulation
 * @author Javier Mesa
 *
 */
public class PostulationRepository {

	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static PostulationDTO createPostulation(PostulationDTO postulation){
		Session session = HibernateUtility.getSessionFactory().openSession();

		// Create the postulation
		Postulation p = new Postulation();
		p.setCreationDate(postulation.getCreationDate());
		p.setOfferId(postulation.getOfferId());
		p.setState(postulation.getState());
		p.setUserId(postulation.getUserId());
		p.setCurrentStep(postulation.getCurrentStep());
		
		session.beginTransaction();		
		session.save(p);
		session.getTransaction().commit();
		Integer id = (Integer)session.getIdentifier(p);
		postulation.setId(id);
		System.out.println("Postulation successfully created with id " + postulation.getId() + ". Academic Offer id: " + postulation.getOfferId());


		return postulation;
	}
	
	public static void updatePostulation(PostulationDTO postulation){
		Session session = HibernateUtility.getSessionFactory().openSession();

		// Update the postulation
		Postulation p = new Postulation();
		p.setId(postulation.getId());
		p.setCreationDate(postulation.getCreationDate());
		p.setOfferId(postulation.getOfferId());
		p.setState(postulation.getState());
		p.setUserId(postulation.getUserId());
		p.setCurrentStep(postulation.getCurrentStep());
		
		session.beginTransaction();		
		session.merge(p);
		session.getTransaction().commit();
		System.out.println("Postulation successfully updated with id " + p.getId() + ". Academic Offer id: " + p.getOfferId());
		
		// Create or update Postulation Info
		
		List<PostulationInfoDTO> postulacionInfoDTOListTemp = new ArrayList<>();
		
		if(postulation.getPostulationInfoList() != null 
				&& !postulation.getPostulationInfoList().isEmpty()){
			
			for (PostulationInfoDTO pI : postulation.getPostulationInfoList()) {
				if(pI.getId()== 0){
					postulacionInfoDTOListTemp.add(PostulationInfoRepository.createPostulationInfo(pI));
				} else {
					postulacionInfoDTOListTemp.add(PostulationInfoRepository.updatePostulationInfo(pI));
				}
			}
		} 
		
		// If postulationInfo is not exist before
		if(!postulacionInfoDTOListTemp.isEmpty()){
			postulation.setPostulationInfoList(postulacionInfoDTOListTemp);
		}
		
	}
	
}
