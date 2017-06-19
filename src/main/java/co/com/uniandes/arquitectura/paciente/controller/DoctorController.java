package co.com.uniandes.arquitectura.paciente.controller;

import java.util.List;

import co.com.uniandes.arquitectura.controller.Controller;
import co.com.uniandes.arquitectura.jdbc.connection.DoctorRepository;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import io.vertx.rxjava.ext.web.RoutingContext;

public class DoctorController implements Controller {

	public void consultarEpisodiosXPaciente(RoutingContext ctx) {
		
	    int cedula = Integer.parseInt(ctx.getBodyAsString());
	    List<EpisodioDTO> result = DoctorRepository.consultarEpisodiosXPaciente(cedula);
		respondWithJson(ctx, 200, result);
	}
}
