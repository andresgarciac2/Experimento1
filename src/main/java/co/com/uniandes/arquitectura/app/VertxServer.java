package co.com.uniandes.arquitectura.app;

import java.util.ArrayList;
import java.util.List;


import co.com.uniandes.arquitectura.paciente.controller.PacienteController;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

public class VertxServer extends AbstractVerticle {

	private PacienteController pacienteController = new PacienteController();

	private final static Logger log = LoggerFactory.getLogger(LoadBalancerVerticle.class);
	private final static String DEFAULT_HOST = "localhost";
	private final static Integer DEFAULT_PORT = 8080;

	private HttpServer server;
	private HttpServerOptions options;
	private List<ProxyHandler> slaves;

	
	@Override
	public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
		slaves = new ArrayList<>();
	}
	
	@Override
	public void start(Future<Void> future) {	
		
		Router servicesRouter = Router.router(vertx);
        setRoutes(servicesRouter);
        
		HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable().subscribe(servicesRouter::accept);
        server.listen(8083, "0.0.0.0", bindingResult -> {
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