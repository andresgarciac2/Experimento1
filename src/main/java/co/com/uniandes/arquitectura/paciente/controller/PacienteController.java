package co.com.uniandes.arquitectura.paciente.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import co.com.uniandes.arquitectura.controller.Controller;
import co.com.uniandes.arquitectura.jdbc.connection.OracleJDBCConnection;
import co.com.uniandes.arquitectura.persistence.EpisodioDTO;
import io.vertx.rxjava.ext.web.RoutingContext;

public class PacienteController implements Controller {

	public void crearEpisodio(RoutingContext ctx) {
		
	    EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
		OracleJDBCConnection conn = OracleJDBCConnection.getDbCon();
		String query = "INSERT INTO EPISODIOS_X_PACIENTE (ID,NOMBRE,NIVELDOLOR,CEDULA) VALUES ("
				+ System.currentTimeMillis() + ",'"
				+ req.getNombre() + "','" 
				+ req.getNivelDolor() + "',"
				+ req.getCedula()+ ")";
		int result = conn.insert(query);
		System.out.println(query);
		respondWithJson(ctx, 200, "Se inserto el registro " + result);
		
//		
//		Callable<Integer> task = () -> {
//		    EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
//			OracleJDBCConnection conn = OracleJDBCConnection.getDbCon();
//			String query = "INSERT INTO EPISODIOS_X_PACIENTE (ID,NOMBRE,NIVELDOLOR,CEDULA) VALUES ("
//					+ System.currentTimeMillis() + ",'"
//					+ req.getNombre() + "','" 
//					+ req.getNivelDolor() + "',"
//					+ req.getCedula()+ ")";
//			int result = conn.insert(query);
//			System.out.println(query);
//			return result;
//		};
//		
//		ExecutorService executor = Executors.newFixedThreadPool(1);
//		Future<Integer> future = executor.submit(task);
//
//		try {
//			respondWithJson(ctx, 200, "Se inserto el registro " + future.get());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//		new Thread(() -> {
//			EpisodioDTO req = extractBodyAsJson(ctx, EpisodioDTO.class);
//			OracleJDBCConnection conn = OracleJDBCConnection.getDbCon();
//			String query = "INSERT INTO EPISODIOS_X_PACIENTE (ID,NOMBRE,NIVELDOLOR,CEDULA) VALUES ("
//					+ System.currentTimeMillis() + ",'"
//					+ req.getNombre() + "','" 
//					+ req.getNivelDolor() + "',"
//					+ req.getCedula()+ ")";
//			int result = conn.insert(query);
//			System.out.println(query);
//			
//		}).start();
//		respondWithJson(ctx, 200, "Se inserto el registro ");
	}
}
