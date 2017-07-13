package co.com.uniandes.arquitectura.controller;

import io.vertx.rxjava.ext.web.RoutingContext;

public class HealthCheckServerController implements Controller{
	
	public void consultarEstadoServidor(RoutingContext ctx) {
		String mensajeEstado = "El servidor se encuentra arriba";
		System.out.println("Ingreso a consultar el estado del servidor: " +  mensajeEstado);
		respondWithJson(ctx, 200, mensajeEstado);
	}
}