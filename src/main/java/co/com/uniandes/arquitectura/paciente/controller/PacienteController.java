package co.com.uniandes.arquitectura.paciente.controller;

import java.sql.SQLException;

import co.com.uniandes.arquitectura.controller.Controller;
import co.com.uniandes.arquitectura.jdbc.connection.OracleJDBCConnection;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		String request = ctx.getBodyAsString();
		OracleJDBCConnection conn = OracleJDBCConnection.getDbCon();
		conn.insert("INSERT INTO EPISODIOS_X_PACIENTE (ID,NOMBRE,NIVELDOLOR) VALUES (11,\'Andres\',\'BAJO\')");
	    respondWithJson(ctx, 200, request + "Episodio Creado");
	}
	
}
