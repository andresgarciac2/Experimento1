package co.com.uniandes.sube.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.dto.AcademicOfferDTO;
import co.com.uniandes.sube.dto.OfferStepDTO;
import co.com.uniandes.sube.repository.AcademicOfferRepository;
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
	
	/**
	 * Method to get a or all offer steps from a offer
	 * @param ctx Request context
	 */
	public void getOfferSteps(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			
			AcademicOfferDTO req = new AcademicOfferDTO();
			if(ctx.request().getParam("id") != null){
				req.setId((Integer.valueOf(ctx.request().getParam("id"))));
			}
			respondWithJson(ctx, 200, OfferStepRepository.getOfferSteps(req));
			
		}  else {
			String stateMessage = "Invalid or null token";
			respondWithJson(ctx, 403, stateMessage);
		}
	}
	
}
