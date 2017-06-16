package co.com.uniandes.arquitectura.paciente.controller;

import co.com.uniandes.arquitectura.controller.Controller;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		String request = ctx.getBodyAsString();
	    respondWithJson(ctx, 200, request + "Episodio Creado");
	}
	
}
