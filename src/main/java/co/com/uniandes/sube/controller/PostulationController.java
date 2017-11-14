package co.com.uniandes.sube.controller;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.dto.AcademicOfferDTO;
import co.com.uniandes.sube.dto.PostulationDTO;
import co.com.uniandes.sube.repository.PostulationRepository;
import co.com.uniandes.sube.security.Session;

/**
 * Class to provide the services to manage postulations
 * @author Javier Mesa
 */
public class PostulationController implements Controller{

	/**
	 * Method to create a postulation in the system
	 * @param ctx Request context
	 */
	public void createPostulation(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			PostulationDTO req = extractBodyAsJson(ctx, PostulationDTO.class);
			PostulationRepository.createPostulation(req);
			respondWithJson(ctx, 200, req);
		//}  else {
		//	String stateMessage = "Invalid or null token";
		//	respondWithJson(ctx, 403, stateMessage);
		//}
	}
	
	/**
	 * Method to update a postulation in the system
	 * @param ctx Request context
	 */
	public void updatePostulation(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			PostulationDTO req = extractBodyAsJson(ctx, PostulationDTO.class);
			PostulationRepository.updatePostulation(req);
			respondWithJson(ctx, 200, req);
		//}  else {
		//	String stateMessage = "Invalid or null token";
		//	respondWithJson(ctx, 403, stateMessage);
		//}
	}
	
	/**
	 * Method to get the postulations a offer
	 * @param ctx Request context
	 */
	public void getPostulationsOffer(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			AcademicOfferDTO req = new AcademicOfferDTO();

			if(ctx.request().getParam("offerId") != null){
				req.setId((Integer.valueOf(ctx.request().getParam("offerId"))));
			}
			
			respondWithJson(ctx, 200, PostulationRepository.getPostulationsOffer(req));
		//}  else {
		//	String stateMessage = "Invalid or null token";
		//	respondWithJson(ctx, 403, stateMessage);
		//}
	}
}
