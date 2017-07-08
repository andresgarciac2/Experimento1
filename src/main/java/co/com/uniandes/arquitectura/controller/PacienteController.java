package co.com.uniandes.arquitectura.controller;

import java.util.List;

import co.com.uniandes.arquitectura.jdbc.connection.PacienteRepository;
import co.com.uniandes.arquitectura.persistence.DiagnosticoDTO;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import co.com.uniandes.arquitectura.security.Session;
import co.com.uniandes.arquitectura.utilities.Recomendation;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
		    PacienteRepository.crearEpisodio(req.getNombre(), req.getNivelDolor(), req.getCedula());
		    respondWithJson(ctx, 200, "Querido usuario según su nivel de dolor " + req.getNivelDolor()
					+ " nuestra recomendación es que deberia " + Recomendation.getRecomendacion(req.getNivelDolor()));
		} else {
			String mensajeEstado = "Token no válido o nulo";
			respondWithJson(ctx, 403, mensajeEstado);
		}
	}
	
	public void consultarDiagnostico(RoutingContext ctx) {
	    int cedula = Integer.parseInt(ctx.getBodyAsString());
	    List<DiagnosticoDTO> result = PacienteRepository.consultarDiagnostico(cedula);
		respondWithJson(ctx, 200, result);
	}
}

