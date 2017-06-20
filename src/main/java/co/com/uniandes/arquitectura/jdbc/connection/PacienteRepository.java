package co.com.uniandes.arquitectura.jdbc.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.uniandes.arquitectura.persistence.DiagnosticoDTO;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;

public class PacienteRepository {

	static OracleJDBCConnection conn = OracleJDBCConnection.getDbCon();
    
	public static int crearEpisodio(int id, String nombre, String nivelDolor, int cedula){
        int result = 0;
        PreparedStatement preparedStatement = null;
        String insert = "INSERT INTO EPISODIOS_X_PACIENTE (ID,NOMBRE,NIVELDOLOR,CEDULA) VALUES (?,?,?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insert);

			preparedStatement.setLong(1, System.currentTimeMillis());
			preparedStatement.setString(2, nombre);
			preparedStatement.setString(3, nivelDolor);
			preparedStatement.setInt(4, cedula);

			// execute insert SQL stetement
			result = preparedStatement.executeUpdate();
			System.out.println("sentencia ejecutada");
			preparedStatement.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return result;
	}
	
	public static List<DiagnosticoDTO> consultarDiagnostico(int cedula){
		List<DiagnosticoDTO> results= new ArrayList<>(); 
		ResultSet result = null;
        PreparedStatement preparedStatement = null;
        System.out.println(preparedStatement);
        String select = "SELECT * FROM DIAGNOSTICOS_X_PACIENTE WHERE PACIENTE_ID = ?";
		try {
			preparedStatement = conn.conn.prepareStatement(select);
			preparedStatement.setInt(1, cedula);

			// execute insert SQL stetement
			result = preparedStatement.executeQuery();
			 while (result.next()) {
				 DiagnosticoDTO e = new DiagnosticoDTO();
				 e.setDescripcion(result.getString(1));
				 e.setIdDoctor(result.getInt(2));
				 e.setIdPaciente(result.getInt(3));
				 results.add(e);
			    }
			System.out.println("sentencia ejecutada");
			preparedStatement.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return results;
}

}
