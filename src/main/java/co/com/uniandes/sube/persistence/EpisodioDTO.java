package co.com.uniandes.arquitectura.persistence;

/**
 * 
 * @author jaher
 * DTO para el manejo transaccional de los episodios
 *
 */
public class EpisodioDTO {

	int cedula;
	String nombre;
	String nivelDolor;
	
	public int getCedula() {
		return cedula;
	}
	
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNivelDolor() {
		return nivelDolor;
	}
	
	public void setNivelDolor(String nivelDolor) {
		this.nivelDolor = nivelDolor;
	}
}
