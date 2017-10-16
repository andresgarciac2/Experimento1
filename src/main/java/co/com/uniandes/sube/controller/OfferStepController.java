package co.com.uniandes.sube.controller;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.dto.OfferStepDTO;
import co.com.uniandes.sube.repository.OfferStepRepository;
import co.com.uniandes.sube.security.Session;

/**
 * Class to provide the services to manage offer step
 * @author Javier Mesa
 */
public class OfferStepController implements Controller{
	
	/**
	 * Method to create a offer step in the system
	 * @param ctx Request context
	 */
	public void createOfferStep(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			OfferStepDTO req = extractBodyAsJson(ctx, OfferStepDTO.class);
			OfferStepRepository.createOfferStep(req);
			respondWithJson(ctx, 200, req);
		}  else {
			String stateMessage = "Invalid or null token";
			respondWithJson(ctx, 403, stateMessage);
		}
	}
	
	/**
	 * Method to update a offer step in the system
	 * @param ctx Request context
	 */
	public void updateOfferStep(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			OfferStepDTO req = extractBodyAsJson(ctx, OfferStepDTO.class);
			OfferStepRepository.updateOfferStep(req);
			respondWithJson(ctx, 200, req);
		}  else {
			String stateMessage = "Invalid or null token";
			respondWithJson(ctx, 403, stateMessage);
		}
	}
}
