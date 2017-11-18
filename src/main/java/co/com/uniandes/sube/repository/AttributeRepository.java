package co.com.uniandes.sube.repository;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.uniandes.sube.dto.AttributeDTO;
import co.com.uniandes.sube.utilities.entities.Attribute;

import com.sube.utilities.hibernate.HibernateUtility;

public class AttributeRepository {

	
	public static AttributeDTO createAttribute(AttributeDTO attribute){
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		// Query to get if exist attribute
		Query qAttribute = session.getNamedQuery("Attribute.findByNameAndType");
		qAttribute.setParameter("name", attribute.getName());
		qAttribute.setParameter("type", attribute.getType());
		
		Attribute at= qAttribute.list().isEmpty()?null: (Attribute)qAttribute.list().get(0);
		
		if(at == null){
			// Create the attribute
			Attribute a = new Attribute();
			a.setName(attribute.getName());
			a.setType(attribute.getType());
			
			session.beginTransaction();		
			session.save(a);
			session.getTransaction().commit();
			Integer id = (Integer)session.getIdentifier(a);
			attribute.setId(id);
			System.out.println("Attribute successfully created with id " + attribute.getId());
		} else {
			attribute.setId(at.getId());
			System.out.println("Attribute already existed with id " + at.getId());
		}
		return attribute;
	}
	
	public static void updateAttribute(AttributeDTO attribute){
		Session session = HibernateUtility.getSessionFactory().openSession();

		// Update the attribute
		Attribute a = new Attribute();
		a.setName(attribute.getName());
		a.setType(attribute.getType());
		
		session.beginTransaction();		
		session.merge(a);
		session.getTransaction().commit();
		System.out.println("Attribute successfully updated with id " + attribute.getId());
	}
	
	
	public static AttributeDTO getAttribute(AttributeDTO attribute){
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		Query qAttribute = session.getNamedQuery("Attribute.findById");
		qAttribute.setParameter("id", attribute.getId());
		
		Attribute at= qAttribute.list().isEmpty()?null: (Attribute)qAttribute.list().get(0);
		
		if(at != null){
			// Set the attribute
			attribute.setName(at.getName());
			attribute.setType(at.getType());
		}
		
		return attribute;
	}
	
}
