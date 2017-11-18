package co.com.uniandes.sube.repository;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.uniandes.sube.dto.UserDTO;
import co.com.uniandes.sube.utilities.entities.Users;

import com.sube.utilities.hibernate.HibernateUtility;

public class UserRepository {
	
	public static UserDTO getUser(UserDTO user){
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		Query qUser = session.getNamedQuery("Users.findById");
		qUser.setParameter("id", user.getDni());
		
		Users u= qUser.list().isEmpty()?null: (Users)qUser.list().get(0);
		
		if(u != null){
			// Set the user
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
		}
		
		return user;
	}

}
