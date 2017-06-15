package co.com.uniandes.arquitectura.app;

import com.google.inject.Inject;

import io.vertx.core.http.HttpMethod;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

public class VertxServer extends AbstractVerticle {
//
//	@Inject
//	private Router router;

	@Override
	public void start() {	
		
		Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/status").handler(this::status);
        
        Router servicesRouter = Router.router(vertx);
        setRoutes(servicesRouter);
        
		HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable().subscribe(router::accept);
        server.listen(8082, "0.0.0.0", bindingResult -> {
            if (bindingResult.succeeded()) {
            	System.out.println("Success");
            }
        });
	}
	
    private void setRoutes(Router router) {

//      router.route(HttpMethod.POST, "/user").handler(userCtrl::user);
  	router.route(HttpMethod.POST, "/temporal").handler(this::temporal);

  }
	private void status(RoutingContext ctx) {
		ctx.response().setStatusCode(200).end();
	}

	private void temporal(RoutingContext ctx) {
		ctx.response().setStatusCode(201).end();
	}
}