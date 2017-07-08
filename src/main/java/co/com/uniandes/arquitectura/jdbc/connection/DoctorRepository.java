package co.com.uniandes.arquitectura.jdbc.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.uniandes.arquitectura.persistence.DiagnosticoDTO;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;

public class DoctorRepository {

static JDBCConnection conn = JDBCConnection.getDb();
    
	public static List<EpisodioDTO> consultarEpisodiosXPaciente(int cedula){
			List<EpisodioDTO> results= new ArrayList<>(); 
			ResultSet result = null;
	        PreparedStatement preparedStatement = null;
	        String select = "SELECT * FROM EPISODIOS_X_PACIENTE WHERE CEDULA = ?";
			try {
				preparedStatement = conn.conn.prepareStatement(select);
				preparedStatement.setInt(1, cedula);

				// execute insert SQL stetement
				result = preparedStatement.executeQuery();
				 while (result.next()) {
					 EpisodioDTO e = new EpisodioDTO();
					 e.setNombre(result.getString(2));
					 e.setNivelDolor(result.getString(3));
					 e.setCedula(result.getInt(4));
					 results.add(e);
				    }
				System.out.println("sentencia ejecutada");
				preparedStatement.close();
				return results;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	        return results;
	}
	
	public static int crearDiagnostico(DiagnosticoDTO diagnostico){
        int result = 0;
        PreparedStatement preparedStatement = null;
        String insert = "INSERT INTO DIAGNOSTICOS_X_PACIENTE (DESCRIPCION,DOCTOR_ID,PACIENTE_ID) VALUES (?,?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insert);

			preparedStatement.setString(1, diagnostico.getDescripcion());
			preparedStatement.setInt(2, diagnostico.getIdDoctor());
			preparedStatement.setInt(3, diagnostico.getIdPaciente());

			// execute insert SQL stetement
			result = preparedStatement.executeUpdate();
			System.out.println("sentencia ejecutada");
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
