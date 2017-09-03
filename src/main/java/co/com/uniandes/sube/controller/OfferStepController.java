package co.com.uniandes.sube.controller;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.jdbc.connection.OfferStepRepository;
import co.com.uniandes.sube.persistence.OfferStepDTO;
import co.com.uniandes.sube.security.Session;

public class OfferStepController implements Controller{
		
	public void createOfferStep(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			OfferStepDTO req = extractBodyAsJson(ctx, OfferStepDTO.class);
			OfferStepRepository.createOfferStep(req);
			respondWithJson(ctx, 200, req);
		//}  else {
		//	String mensajeEstado = "Token no válido o nulo";
		//	respondWithJson(ctx, 403, mensajeEstado);
		//}
	}
	
	public void updateOfferStep(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			OfferStepDTO req = extractBodyAsJson(ctx, OfferStepDTO.class);
			OfferStepRepository.updateOfferStep(req);
			respondWithJson(ctx, 200, req);
		//}  else {
		//	String mensajeEstado = "Token no válido o nulo";
		//	respondWithJson(ctx, 403, mensajeEstado);
		//}
	}
}
