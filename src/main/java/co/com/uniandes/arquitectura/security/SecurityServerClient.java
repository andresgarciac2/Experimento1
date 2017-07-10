package co.com.uniandes.arquitectura.security;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.common.net.HttpHeaders;

import io.vertx.rxjava.core.AbstractVerticle;

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
	
	public boolean invocarVerificarToken(String id, String token) {
		try {
			String url = "http://localhost:8480/validate";
			URL obj;
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("TOKEN", token);
			con.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
			con.setRequestProperty(HttpHeaders.CONTENT_LENGTH, String.valueOf(id.length()));

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(id);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			
			
			if(responseCode == 200){
				esTokenValido = true;
			} else {
				esTokenValido = false;
			}
			
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("esTokenValid: "  + esTokenValido);
		return esTokenValido; 
		
		/*
		Context contex = Vertx.currentContext();
		vertx = contex.owner();
		HttpClient httpClient = vertx.createHttpClient();
		Buffer buffer = Buffer.buffer(id);
		final String url = "http://localhost:8480/validate";
		httpClient.postAbs(url, response -> {
			if (response.statusCode() == 200) {
				System.out.println("true");
				esTokenValido = true;
			} else {
				System.out.println("false");
				esTokenValido = false;
			}
		})
	    .putHeader("TOKEN" , token)
	    .putHeader(HttpHeaders.CONTENT_TYPE, "aplication/json")
	    .putHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(id.length()))
	    .write(buffer)
	    .end();
		try {
			Thread.sleep(1500);
			System.out.println("esTokenValido: " + esTokenValido);
			return esTokenValido;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return esTokenValido;
		}*/
		
	}
}
