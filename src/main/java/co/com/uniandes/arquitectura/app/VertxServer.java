package co.com.uniandes.arquitectura.app;

import co.com.uniandes.arquitectura.paciente.controller.PacienteController;
import io.vertx.core.http.HttpMethod;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

public class VertxServer extends AbstractVerticle {

	private PacienteController pacienteController = new PacienteController();

	@Override
	public void start() {	
		
		Router servicesRouter = Router.router(vertx);
        setRoutes(servicesRouter);
        
		HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable().subscribe(servicesRouter::accept);
        server.listen(8082, "0.0.0.0", bindingResult -> {
            if (bindingResult.succeeded()) {
            	System.out.println("Success");
            }
        });
	}
	
	private void setRoutes(Router router) {
		router.route().handler(BodyHandler.create());
		router.route(HttpMethod.POST, "/crearEpisodio").handler(pacienteController::crearEpisodio);
		router.route(HttpMethod.GET, "/status").handler(this::status);

	}
	
	private void status(RoutingContext ctx) {
		ctx.response().setStatusCode(200).end();
	}
}