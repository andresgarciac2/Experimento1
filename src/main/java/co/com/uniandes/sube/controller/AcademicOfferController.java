package co.com.uniandes.sube.controller;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.persistence.AcademicOfferDTO;
import co.com.uniandes.sube.security.Session;

public class AcademicOfferController implements Controller{
	
	public void createOffer(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			AcademicOfferDTO req = extractBodyAsJson(ctx, AcademicOfferDTO.class);
			req.setId(12345);
			//int result = AcademicOfferRepository.crearDiagnostico(req);
			respondWithJson(ctx, 200, req);
		}  else {
			String mensajeEstado = "Token no válido o nulo";
			respondWithJson(ctx, 403, mensajeEstado);
		}
	}
	
	public void updateOffer(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			AcademicOfferDTO req = extractBodyAsJson(ctx, AcademicOfferDTO.class);
			req.setId(12345);
			//int result = AcademicOfferRepository.crearDiagnostico(req);
			respondWithJson(ctx, 200, req);
		}  else {
			String mensajeEstado = "Token no válido o nulo";
			respondWithJson(ctx, 403, mensajeEstado);
		}
	}
	
}
