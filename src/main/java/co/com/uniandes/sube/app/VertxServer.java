package co.com.uniandes.sube.app;


import com.sube.utilities.hibernate.HibernateUtility;

import co.com.uniandes.sube.controller.AcademicOfferController;
import co.com.uniandes.sube.controller.HealthCheckServerController;
import co.com.uniandes.sube.controller.OfferStepController;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

/**
 * Class main of vertx server 
 * @author Javier Mesa
 *
 */
public class VertxServer extends AbstractVerticle {

	// Variables
	private AcademicOfferController academicOfferController = new AcademicOfferController();
	private OfferStepController offerStepController = new OfferStepController();
	private HealthCheckServerController healthCheckServerController = new HealthCheckServerController();
	public static boolean esTokenValido;
	static JsonObject authInfo;
	
	/**
	 * Start vertx server
	 */
	@Override
	public void start() {	
		Router servicesRouter = Router.router(vertx);
        setRoutes(servicesRouter);
		HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable().subscribe(servicesRouter::accept);
        server.listen(8085, "0.0.0.0", bindingResult -> {
            if (bindingResult.succeeded()) {
            	HibernateUtility.getSessionFactory().openSession();
            	System.out.println("Success");
            }
        });
	}
	
	
	/**
	 * Configurate server routes
	 * @param router Router of context
	 */
	private void setRoutes(Router router) {
		
		router.route().handler(io.vertx.rxjava.ext.web.handler.CorsHandler.create("*")
				.allowedMethod(io.vertx.core.http.HttpMethod.GET)
				.allowedMethod(io.vertx.core.http.HttpMethod.POST)
				.allowCredentials(true)
				.allowedHeader("Access-Control-Request-Method")
				.allowedHeader("Access-Control-Allow-Origin")
				.allowedHeader("Access-Control-Allow-Headers")
				.allowedHeader("X-Requested-With")
				.allowedHeader("Content-Type")
				.allowedHeader("TOKEN")
				.allowedHeader("ID"));
		router.route().handler(BodyHandler.create());
		
		router.route(HttpMethod.GET, "/status").handler(healthCheckServerController::getServerStatus);
		router.route(HttpMethod.POST, "/academicOffer").handler(academicOfferController::createOffer);
		router.route(HttpMethod.PUT, "/academicOffer").handler(academicOfferController::updateOffer);
		router.route(HttpMethod.GET, "/academicOffer").handler(academicOfferController::getOffers);
		router.route(HttpMethod.POST, "/offerStep").handler(offerStepController::createOfferStep);
		router.route(HttpMethod.PUT, "/offerStep").handler(offerStepController::updateOfferStep);
	}
	
}