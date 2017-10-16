package co.com.uniandes.sube.controller;

import io.vertx.rxjava.ext.web.RoutingContext;

/**
 * Class to verify of server status
 * @author Javier Mesa
 *
 */
public class HealthCheckServerController implements Controller{
	
	/**
	 * Method to get current server status
	 * @param ctx Request context
	 */
	public void getServerStatus(RoutingContext ctx) {
		String mensajeEstado = "Server running";
		System.out.println(mensajeEstado);
		respondWithJson(ctx, 200, mensajeEstado);
	}
}
