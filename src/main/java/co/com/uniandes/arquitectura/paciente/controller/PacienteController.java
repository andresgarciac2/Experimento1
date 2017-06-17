package co.com.uniandes.arquitectura.paciente.controller;

import co.com.uniandes.arquitectura.controller.Controller;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		//String request = ctx.getBodyAsString();
		EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
		
	    respondWithJson(ctx, 200, "El nombre del paciente es: " + req.getNombre());
	}
	
}
