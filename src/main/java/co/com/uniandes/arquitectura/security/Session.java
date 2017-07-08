package co.com.uniandes.arquitectura.security;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.http.HttpServerRequest;
import io.vertx.rxjava.ext.web.RoutingContext;

import com.google.common.net.HttpHeaders;

public class Session {

	public static Session session;
	static JsonObject authInfo;
	
	private Session(){
		authInfo = new JsonObject();
	}
	
	/**
    *
    * @return Session object
    */
   public static synchronized Session getSession() {
       if ( session == null ) {
    	   session = new Session();
       }
       return session;
   }
	
	public boolean verificarToken(RoutingContext ctx) {
		HttpServerRequest request = ctx.request();
		String authorization = request.headers().get(HttpHeaders.AUTHORIZATION);
		System.out.println("token: " + authorization);

		if (authorization != null) {
			if (getSession().authInfo.containsKey(authorization)) {
				System.out.println("el token : " + authorization + " existe");
				return true;
			} else {
				System.out.println("el token no existe en sesion");
				boolean esValido = SecurityServerClient.getSecurityServer()
						.invocarVerificarToken(authorization);

				System.out.println("esValido: " + esValido);

				if (esValido) {
					authInfo.put(authorization, authorization);
					System.out.println("Se agrego el token: " + authorization);
					return true;
				} else {
					System.out.println("Token no es valido");
					return false;
				}
			}
		} else {
			System.out.println("el token es nulo");
			return false;
		}
	}
	
}
