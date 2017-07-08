package co.com.uniandes.arquitectura.paciente.controller;

import java.util.List;

import co.com.uniandes.arquitectura.controller.Controller;
import co.com.uniandes.arquitectura.jdbc.connection.DoctorRepository;
import co.com.uniandes.arquitectura.jdbc.connection.PacienteRepository;
import co.com.uniandes.arquitectura.persistence.DiagnosticoDTO;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		
	    EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
	    int result = PacienteRepository.crearEpisodio(123, req.getNombre(), req.getNivelDolor(), req.getCedula());
		respondWithJson(ctx, 200, "Se inserto el registro " + result);
	}
	public void consultarDiagnostico(RoutingContext ctx) {
		
	    int cedula = Integer.parseInt(ctx.getBodyAsString());
	    List<DiagnosticoDTO> result = PacienteRepository.consultarDiagnostico(cedula);
		respondWithJson(ctx, 200, result);
	}
}

