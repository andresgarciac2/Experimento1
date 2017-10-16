package co.com.uniandes.sube.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.json.DecodeException;
import io.vertx.rxjava.ext.web.RoutingContext;

/**
 * Class to controller the response 
 * @author Javier Mesa
 *
 */
public interface Controller {

	
    public static final ObjectMapper mapper = new ObjectMapper();
	
	 /**
     * Method to get body request like JSON object
     * @param ctx Request context
     * @param clazz target object
     * @return a target instance
     */
    default <T> T extractBodyAsJson(RoutingContext ctx, Class<T> clazz) {
        try {
            String bodyAsString = ctx.getBodyAsString();
            return mapper.readValue(bodyAsString, clazz);
        } catch (DecodeException e) {
            throw new DecodeException(e.getMessage());
        } catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }

    /**
     * Method to get a response without content
     * @param ctx Request context
     */
    default void respondNoContent(RoutingContext ctx) {
        ctx.response()
                .setStatusCode(403)
                .end();
    }

    /**
     * Method to response a request http like a JSON Object
     * @param ctx  Request context
     * @param body Object to convert to JSON Object
     */
    default void respondWithJson(RoutingContext ctx, Object body) {
    	respondWithJson(ctx, 200, body);
    }

    /**
     * Method to response a http resquest like JSON Object
     * @param ctx Request context
     * @param statusCode status code of response
     * @param body Object to convert to JSON Object
     */
    default void respondWithJson(RoutingContext ctx, int statusCode, Object body) {
        String encoding = "UTF-8";
        try {
			ctx.response()
			        .setStatusCode(statusCode)
			        .putHeader("Content-Type", "application/json;charset=UTF-8" + encoding)
			        .end(mapper.writeValueAsString(body), encoding);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
	
}
