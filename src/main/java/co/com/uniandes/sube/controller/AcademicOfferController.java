package co.com.uniandes.sube.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.dto.AcademicOfferDTO;
import co.com.uniandes.sube.repository.AcademicOfferRepository;
import co.com.uniandes.sube.security.Session;

/**
 * Class to provide the services to manage academic offer
 * @author Javier Mesa
 */
public class AcademicOfferController implements Controller{
	
	/**
	 * Method to create a academic offer in the system
	 * @param ctx Request context
	 */
	public void createOffer(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			AcademicOfferDTO req = extractBodyAsJson(ctx, AcademicOfferDTO.class);
			AcademicOfferRepository.createAcademicOffer(req);
			respondWithJson(ctx, 200, req);
			
		//}  else {
		//	String stateMessage = "Invalid or null token";
		//	respondWithJson(ctx, 403, stateMessage);
		//}
	}
	
	/**
	 * Method to update a academic offer in the system
	 * @param ctx Request context
	 */
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
	
	/**
	 * Method to get a or all academic offers in the system with filters
	 * @param ctx Request context
	 */
	public void getOffers(RoutingContext ctx) {
		Session session = Session.getSession();
		if (session.verificarToken(ctx)) {
			
			AcademicOfferDTO req = new AcademicOfferDTO();
			if(ctx.request().getParam("createdBy") != null){
				req.setCreatedBy((Integer.valueOf(ctx.request().getParam("createdBy"))));
			}
			
			if(ctx.request().getParam("id") != null){
				req.setId((Integer.valueOf(ctx.request().getParam("id"))));
			}
			
			if(ctx.request().getParam("creationDate") != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				try {
					Date date = sdf.parse(ctx.request().getParam("creationDate"));
					req.setCreationDate(date);
				} catch (ParseException e) {
					System.out.println("Formato de fecha incorrecto");
				}
				
			}
			
			if(ctx.request().getParam("type") != null){
				req.setType((Integer.valueOf(ctx.request().getParam("type"))));
			}
			
			if(ctx.request().getParam("state") != null){
				req.setState((Integer.valueOf(ctx.request().getParam("state"))));
			}
			
			respondWithJson(ctx, 200, AcademicOfferRepository.getAcademicOffers(req));
			
		}  else {
			String stateMessage = "Invalid or null token";
			respondWithJson(ctx, 403, stateMessage);
		}
	}
	
}
