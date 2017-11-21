package co.com.uniandes.sube.controller;

import io.vertx.rxjava.ext.web.RoutingContext;
import co.com.uniandes.sube.dto.AcademicOfferDTO;
import co.com.uniandes.sube.dto.PostulationDTO;
import co.com.uniandes.sube.dto.UserDTO;
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
			PostulationDTO req = new PostulationDTO();
			
			if(ctx.request().getParam("offerId") != null){
				AcademicOfferDTO offer = new AcademicOfferDTO();
				offer.setId(Integer.valueOf(ctx.request().getParam("offerId")));
				req.setOffer(offer);
			}
			
			if(ctx.request().getParam("id") != null){
				req.setId((Integer.valueOf(ctx.request().getParam("id"))));
			}
			
			if(ctx.request().getParam("userId") != null){
				UserDTO user = new UserDTO();
				user.setDni((Integer.valueOf(ctx.request().getParam("userId"))));
				req.setUser(user);
			}
			
			respondWithJson(ctx, 200, PostulationRepository.getPostulations(req));
		//}  else {
		//	String stateMessage = "Invalid or null token";
		//	respondWithJson(ctx, 403, stateMessage);
		//}
	}
	
	
	/**
	 * Method to evaluate the postulation and set the current step of offer
	 * @param ctx Request context
	 */
	public void evaluatePostulation(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			PostulationDTO req = extractBodyAsJson(ctx, PostulationDTO.class);
			
			if(PostulationRepository.evaluatePostulation(req) != null){
				respondWithJson(ctx, 200, req);
			} else {
				respondWithJson(ctx, 500, "Error to evaluate step of postulation");
				//}
			}
			
			
		//}  else {
		//	String stateMessage = "Invalid or null token";
		//	respondWithJson(ctx, 403, stateMessage);
		//}
	}
	
}
