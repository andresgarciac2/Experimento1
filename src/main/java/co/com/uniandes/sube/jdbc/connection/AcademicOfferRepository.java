package co.com.uniandes.sube.jdbc.connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.com.uniandes.sube.persistence.AcademicOfferDTO;


public class AcademicOfferRepository {
	
	
	static JDBCConnection conn = JDBCConnection.getDb();
	
	public static AcademicOfferDTO createAcademicOffer(AcademicOfferDTO offer){
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
}
