package enumerados;

public enum LaboralType {
	GENERAL("General"), PRIMARIA_O_INFERIOR("Primariaoinferior"), 
	SECUNDARIA_PRIMERAETAPA("Secundariaprimeraetapayformacionprofesional"), 
	SECUNDARIA_SEGUNDAETAPA("Secundariasegundaetapayformaciónprofesional"),
	EDUCACION_SUPERIOR("Educaciónsuperior");

	private String type;

	LaboralType(String type){
		this.type = type;
	}
	
	public String getValue(){
		return type;
	}
}
