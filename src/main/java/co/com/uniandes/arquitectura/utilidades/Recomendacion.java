package co.com.uniandes.arquitectura.utilidades;

/*
 * Recomendaciones para los niveles de dolor
 */
public enum Recomendacion {
	
	BAJO ("tomar Acetaminofem"),
    MEDIO ("tomar Ibuprofeno"),
    ALTO("correr donde el Médico");
    
    private final String recomendacion;

    Recomendacion (String recomendacion) { 
        this.recomendacion = recomendacion;
    }

	public String getRecomendacion() {
		return recomendacion;
	} 
	
	public static String getRecomendacion(String nivelDolor){
		switch (nivelDolor) {
		case "BAJO":
			return Recomendacion.BAJO.getRecomendacion();
			
		case "MEDIO":
			return Recomendacion.MEDIO.getRecomendacion();
			
		default:
			return Recomendacion.ALTO.getRecomendacion();
		}
	}
}
