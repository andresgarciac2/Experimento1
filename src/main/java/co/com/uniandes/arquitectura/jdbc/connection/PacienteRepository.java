package co.com.uniandes.arquitectura.jdbc.connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
