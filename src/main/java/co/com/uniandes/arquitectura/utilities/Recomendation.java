package co.com.uniandes.arquitectura.utilities;

public enum Recomendation {
	
	BAJO ("tomar Acetaminofem"),
    MEDIO ("tomar Ibuprofeno"),
    ALTO("correr donde el Médico");
    
    private final String recomendacion;

    Recomendation (String recomendacion) { 
        this.recomendacion = recomendacion;
    }

	public String getRecomendacion() {
		return recomendacion;
	} 
	
	public static String getRecomendacion(String nivelDolor){
		switch (nivelDolor) {
		case "BAJO":
			return Recomendation.BAJO.getRecomendacion();
			
		case "MEDIO":
			return Recomendation.MEDIO.getRecomendacion();
			
		default:
			return Recomendation.ALTO.getRecomendacion();
		}
	}
}
