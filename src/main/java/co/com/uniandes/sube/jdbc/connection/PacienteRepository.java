package co.com.uniandes.arquitectura.jdbc.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.uniandes.arquitectura.persistence.DiagnosticoDTO;

public class PacienteRepository {

	static JDBCConnection conn = JDBCConnection.getDb();

	public static int crearEpisodio(String nombre, String nivelDolor, int cedula) {
		int result = 0;
		PreparedStatement preparedStatement = null;
		String insert = "INSERT INTO EPISODIOS_X_PACIENTE (NOMBRE,NIVELDOLOR,CEDULA) VALUES (?,?,?)";
		try {
			preparedStatement = conn.conn.prepareStatement(insert);

			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, nivelDolor);
			preparedStatement.setInt(3, cedula);

			// execute insert SQL stetement
			result = preparedStatement.executeUpdate();
			System.out.println("sentencia ejecutada");
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static List<DiagnosticoDTO> consultarDiagnostico(int cedula) {
		List<DiagnosticoDTO> results = new ArrayList<>();
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
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

}