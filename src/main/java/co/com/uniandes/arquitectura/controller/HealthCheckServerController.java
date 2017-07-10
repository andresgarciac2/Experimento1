package co.com.uniandes.arquitectura.controller;

import co.com.uniandes.arquitectura.security.Session;

import io.vertx.rxjava.ext.web.RoutingContext;

public class HealthCheckServerController implements Controller{
	
	public void consultarEstadoServidor(RoutingContext ctx) {
		Session session = Session.getSession();
		//if (session.verificarToken(ctx)) {
			System.out.println("Ingreso al 81 a consultar el estado del servidor");
			String mensajeEstado = "El servidor se encuentra arriba";
			respondWithJson(ctx, 200, mensajeEstado);
		//} else {
		//	String mensajeEstado = "Token no válido o nulo";
		//	respondWithJson(ctx, 403, mensajeEstado);
		//}
	}
}