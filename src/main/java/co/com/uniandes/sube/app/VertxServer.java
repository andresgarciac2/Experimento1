package co.com.uniandes.sube.app;

import co.com.uniandes.sube.controller.AcademicOfferController;
import co.com.uniandes.sube.controller.HealthCheckServerController;
import co.com.uniandes.sube.controller.OfferStepController;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

public class VertxServer extends AbstractVerticle {

	private AcademicOfferController academicOfferController = new AcademicOfferController();
	private OfferStepController offerStepController = new OfferStepController();
	private HealthCheckServerController healthCheckServerController = new HealthCheckServerController();
	
	public static boolean esTokenValido;
	static JsonObject authInfo;
	
	@Override
	public void start() {	
		Router servicesRouter = Router.router(vertx);
        setRoutes(servicesRouter);
		HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable().subscribe(servicesRouter::accept);
        server.listen(8081, "0.0.0.0", bindingResult -> {
            if (bindingResult.succeeded()) {
            	System.out.println("Success");
            }
        });
	}
	
	private void setRoutes(Router router) {
		router.route().handler(BodyHandler.create());
		
		router.route(HttpMethod.GET, "/status").handler(healthCheckServerController::getServerStatus);
		router.route(HttpMethod.POST, "/academicOffer").handler(academicOfferController::createOffer);
		router.route(HttpMethod.PUT, "/academicOffer").handler(academicOfferController::updateOffer);
		router.route(HttpMethod.POST, "/offerStep").handler(offerStepController::createOfferStep);
		router.route(HttpMethod.PUT, "/offerStep").handler(offerStepController::updateOfferStep);
	}
	
}