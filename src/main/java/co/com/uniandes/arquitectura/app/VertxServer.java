package co.com.uniandes.arquitectura.app;

import co.com.uniandes.arquitectura.controller.DoctorController;
import co.com.uniandes.arquitectura.controller.HealthCheckServerController;
import co.com.uniandes.arquitectura.controller.PacienteController;
import co.com.uniandes.arquitectura.controller.TokenController;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

public class VertxServer extends AbstractVerticle {

	private PacienteController pacienteController = new PacienteController();
	private DoctorController doctorController = new DoctorController();
	private HealthCheckServerController healthCheckServerController = new HealthCheckServerController();
	private TokenController tokenController = new TokenController();
	
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
		
		router.route(HttpMethod.POST, "/crearEpisodio").handler(pacienteController::crearEpisodio);
		router.route(HttpMethod.POST, "/consultarEpisodiosXPaciente").handler(doctorController::consultarEpisodiosXPaciente);
		router.route(HttpMethod.POST, "/crearDiagnosticoXPaciente").handler(doctorController::crearDiagnosticoXPaciente);
		router.route(HttpMethod.POST, "/consultarDiagnostico").handler(pacienteController::consultarDiagnostico);
		router.route(HttpMethod.GET, "/").handler(healthCheckServerController::consultarEstadoServidor);
		router.route(HttpMethod.GET, "/validarToken").handler(tokenController::consultarTokenValido);
	}
	
}