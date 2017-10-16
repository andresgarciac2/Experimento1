package co.com.uniandes.sube.app;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.vertx.core.http.HttpMethod;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

/**
 * Class to server routing 
 * @author Javier 
 */
public class RouterProvider implements Provider<Router> {

    // ----------------------------------------
    // instances
    // ----------------------------------------

    @Inject
    private Vertx vertx;

    // ----------------------------------------
    // ----------------------------------------
    // public methods
    // ----------------------------------------

    @Override
    public Router get() {

        // configure main router
        Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/status").handler(this::status);

        // configure services router
        Router servicesRouter = Router.router(vertx);
        setRoutes(servicesRouter);

        // return main router
        return router;
    }

    // ----------------------------------------
    // private methods
    // ----------------------------------------
   
    private void setRoutes(Router router) {
    	router.route(HttpMethod.POST, "/temporal").handler(this::temporal);
    }

    private void status(RoutingContext ctx) {
        ctx.response().setStatusCode(200).end();
    }
    
    private void temporal(RoutingContext ctx) {
    	ctx.response().setStatusCode(201).end();
    }
    
}
