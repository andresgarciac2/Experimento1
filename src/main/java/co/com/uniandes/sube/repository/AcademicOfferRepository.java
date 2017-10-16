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
import co.com.uniandes.sube.utilities.entities.AcademicOffer;

/**
 * Class to manage the transactions of table Academic Offer
 * @author Javier Mesa
 *
 */
public class AcademicOfferRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static AcademicOfferDTO createAcademicOffer(AcademicOfferDTO offer){
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		AcademicOffer ao = new AcademicOffer();
		ao.setCreateBy(offer.getCreatedBy());
		ao.setCreationDate(new java.util.Date());
		ao.setStartDate(offer.getStartDate());
		ao.setEndDate(offer.getEndDate());
		ao.setName(offer.getName());
		ao.setDescription(offer.getDescription());
		ao.setType(offer.getType());
		ao.setState(offer.getState());
		
		session.beginTransaction();		
		session.save(ao);
		session.getTransaction().commit();
		Integer id = (Integer)session.getIdentifier(ao);
		System.out.println("Academic Offer successfully created with id: " + id);
		offer.setId(id);
		
		/*
	    long idOffer = 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "INSERT INTO ACADEMIC_OFFER (CREATE_BY, START_DATE, END_DATE, NAME, DESCRIPTION, TYPE, STATE) VALUES (?,?,?,?,?,?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insertOffer, new String[]{"ID"});

			preparedStatement.setInt(1, offer.getCreatedBy());
			preparedStatement.setDate(2, new Date(offer.getStartDate().getTime()));
			preparedStatement.setDate(3, new Date(offer.getEndDate().getTime()));
			preparedStatement.setString(4, offer.getName());
			preparedStatement.setString(5, offer.getDescription());
			preparedStatement.setInt(6, offer.getType());
			preparedStatement.setInt(7, offer.getState());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
            	idOffer = generatedKeys.getLong(1);
            	offer.setId(idOffer);
            }
           
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		*/
	    return offer;
	}
	
	public static int updateAcademicOffer(AcademicOfferDTO offer){
		int result = 0;
	    PreparedStatement preparedStatement = null;
	    String insertOffer = "UPDATE ACADEMIC_OFFER SET START_DATE=?, END_DATE=?, NAME=?, DESCRIPTION=?, TYPE=?, STATE=? WHERE ID=?";
		try {
			preparedStatement = conn.conn.prepareStatement(insertOffer);

			preparedStatement.setDate(1, new Date(offer.getStartDate().getTime()));
			preparedStatement.setDate(2, new Date(offer.getEndDate().getTime()));
			preparedStatement.setString(3, offer.getName());
			preparedStatement.setString(4, offer.getDescription());
			preparedStatement.setInt(5, offer.getType());
			preparedStatement.setInt(6, offer.getState());
			preparedStatement.setLong(7, offer.getId());

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
	
	
	public static List<AcademicOfferDTO> getAcademicOffers(AcademicOfferDTO offer){
		List<AcademicOfferDTO> offerList= new ArrayList<>();
		
		PreparedStatement preparedStatement = null;
	    String getOffers = "SELECT ID, CREATE_BY, CREATION_DATE, START_DATE, END_DATE, NAME, DESCRIPTION, TYPE, STATE FROM ACADEMIC_OFFER WHERE 1 = 1";
		
	    if(offer.getId()!= 0){
	    	getOffers += " AND ID=" +  offer.getId();
	    }
	    
	    if(offer.getCreatedBy()!= 0){
	    	getOffers += " AND CREATE_BY=" +  offer.getCreatedBy();
	    }
	    
	    if(offer.getCreationDate()!= null){
	    	getOffers += " AND CREATION_DATE=" +  new Date(offer.getCreationDate().getTime());
	    }
	    
	    if(offer.getType()!= 0){
	    	getOffers += " AND TYPE=" +  offer.getType();
	    }
	    
	    if(offer.getState()!= 0){
	    	getOffers += " AND STATE=" +  offer.getState();
	    }
	    System.out.println("query: " + getOffers );
	    
	    try {
			preparedStatement = conn.conn.prepareStatement(getOffers);
			
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				AcademicOfferDTO offerTemp = new AcademicOfferDTO();
				offerTemp.setId(rs.getInt(1));
				offerTemp.setCreatedBy(rs.getInt(2));
				offerTemp.setCreationDate(rs.getDate(3));
				offerTemp.setStartDate(rs.getDate(4));
				offerTemp.setEndDate(rs.getDate(5));
				offerTemp.setName(rs.getString(6));
				offerTemp.setDescription(rs.getString(7));
				offerTemp.setType(rs.getInt(8));
				offerTemp.setState(rs.getInt(9));
				offerList.add(offerTemp);
			}
           
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return offerList;
	}
}
