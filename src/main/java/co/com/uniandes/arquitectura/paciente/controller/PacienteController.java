package co.com.uniandes.arquitectura.paciente.controller;

import co.com.uniandes.arquitectura.controller.Controller;
import co.com.uniandes.arquitectura.jdbc.connection.OracleJDBCConnection;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		new Thread(() -> {
			EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
			OracleJDBCConnection conn = OracleJDBCConnection.getDbCon();
			String query = "INSERT INTO EPISODIOS_X_PACIENTE (ID,NOMBRE,NIVELDOLOR,CEDULA) VALUES ("
					+ System.currentTimeMillis() + ",'"
					+ req.getNombre() + "','" 
					+ req.getNivelDolor() + "',"
					+ req.getCedula()+ ")";
			int result = conn.insert(query);
			System.out.println(query);
			respondWithJson(ctx, 200, "Se inserto el registro " + result);
		}).start();
	}
}
