package co.com.uniandes.arquitectura.paciente.controller;

import co.com.uniandes.arquitectura.controller.Controller;
import co.com.uniandes.arquitectura.jdbc.connection.JDBCConnection;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import co.com.uniandes.arquitectura.utilidades.Recomendacion;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
		new Thread(() -> {
			JDBCConnection conn = JDBCConnection.getDbCon();
			String query = "INSERT INTO EPISODIOS_X_PACIENTE (NOMBRE, NIVELDOLOR, CEDULA) VALUES ('"
					+ req.getNombre() + "', '" 
					+ req.getNivelDolor() + "', "
					+ req.getCedula()+ ")";
			conn.insert(query);
			System.out.println("Ingreso en el 83");
			System.out.println(query);
			
		}).start();
		respondWithJson(ctx, 200, "Querido usuario seg�n su nivel de dolor " + req.getNivelDolor()
				+ " nuestra recomendaci�n es que deberia " + Recomendacion.getRecomendacion(req.getNivelDolor()));
	}
}
