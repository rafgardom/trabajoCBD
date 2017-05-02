package enumerados;

public enum LaboralType {
	GENERAL("General"), PRIMARIA_O_INFERIOR("Primaria o inferior"), 
	SECUNDARIA_PRIMERAETAPA("Secundaria primera etapa y formacion profesional"), 
	SECUNDARIA_SEGUNDAETAPA("Secundaria segunda etapa y formación profesional"),
	EDUCACION_SUPERIOR("Educación superior");

	private String type;

	LaboralType(String type){
		this.type = type;
	}
	
	public String getValue(){
		return type;
	}
}
