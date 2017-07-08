package co.com.uniandes.arquitectura.controller;

import io.vertx.rxjava.ext.web.RoutingContext;

public class TokenController implements Controller {
	
	public void consultarTokenValido(RoutingContext ctx) {
		String tokenAValidar = ctx.request().getParam("token");
		System.out.println("token a consultar en el servidor de seguridad: " + tokenAValidar);
		
		if(tokenAValidar != null && tokenAValidar.contains("123")){
			ctx.response().setStatusCode(200).setStatusMessage("Token válido").end();
		} else {
			ctx.response().setStatusCode(500).setStatusMessage("Token no válido").end(); 
		}
	}
}
