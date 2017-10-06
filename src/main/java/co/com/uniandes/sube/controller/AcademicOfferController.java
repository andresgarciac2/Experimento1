package co.com.uniandes.sube.controller;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.jdbc.connection.AcademicOfferRepository;
import co.com.uniandes.sube.persistence.AcademicOfferDTO;
import co.com.uniandes.sube.security.Session;

public class AcademicOfferController implements Controller{
	
	public void createOffer(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			AcademicOfferDTO req = extractBodyAsJson(ctx, AcademicOfferDTO.class);
			AcademicOfferRepository.createAcademicOffer(req);
			respondWithJson(ctx, 200, req);
			
		}  else {
			String stateMessage = "Invalid or null token";
			respondWithJson(ctx, 403, stateMessage);
		}
	}
	
	public void updateOffer(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			AcademicOfferDTO req = extractBodyAsJson(ctx, AcademicOfferDTO.class);
			AcademicOfferRepository.updateAcademicOffer(req);
			respondWithJson(ctx, 200, req);
		}  else {
			String stateMessage = "Invalid or null token";
			respondWithJson(ctx, 403, stateMessage);
		}
	}
	
}
