package co.com.uniandes.arquitectura.security;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.http.HttpClient;

public class SecurityServerClient extends AbstractVerticle{
	
	public static SecurityServerClient securityServer ;
	public static boolean esTokenValido;
	
	public static SecurityServerClient getSecurityServer() {
		if ( securityServer == null ) {
			securityServer = new SecurityServerClient();
        }
        return securityServer;
	}

	private SecurityServerClient(){
		
	}
	
	
	public boolean invocarVerificarToken(String token) {
		vertx = Vertx.vertx();
		HttpClient httpClient = vertx.createHttpClient();
		final String url = "http://localhost:8082/validarToken?token=" + token;
		httpClient.getAbs(url, response -> {
			if (response.statusCode() == 200) {
				esTokenValido = true;
			} else {
				esTokenValido = false;
			}
		}).end();
		System.out.println("esTokenValid: " + esTokenValido);
		return esTokenValido;
	}
}
