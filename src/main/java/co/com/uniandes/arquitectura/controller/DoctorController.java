package co.com.uniandes.arquitectura.controller;

import java.util.List;

import co.com.uniandes.arquitectura.jdbc.connection.DoctorRepository;
import co.com.uniandes.arquitectura.persistence.DiagnosticoDTO;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import io.vertx.rxjava.ext.web.RoutingContext;

public class DoctorController implements Controller {

	public void consultarEpisodiosXPaciente(RoutingContext ctx) {
	    int cedula = Integer.parseInt(ctx.getBodyAsString());
	    List<EpisodioDTO> result = DoctorRepository.consultarEpisodiosXPaciente(cedula);
		respondWithJson(ctx, 200, result);
	}
	
	public void crearDiagnosticoXPaciente(RoutingContext ctx) {
		DiagnosticoDTO req = extractBodyAsJson(ctx, DiagnosticoDTO.class);
		int result = DoctorRepository.crearDiagnostico(req);
		respondWithJson(ctx, 200, "Diagnostico creado " + result);
	}
}
