package co.com.uniandes.sube.repository;

import org.hibernate.Session;

import co.com.uniandes.sube.dto.AttributeDTO;
import co.com.uniandes.sube.utilities.entities.Attribute;

import com.sube.utilities.hibernate.HibernateUtility;

public class AttributeRepository {

	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static AttributeDTO createAttribute(AttributeDTO attribute){
		Session session = HibernateUtility.getSessionFactory().openSession();

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
	
}
