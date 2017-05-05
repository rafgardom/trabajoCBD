package enumerados;

public enum LaboralRateType {
	PARO("Paro"), ACTIVIDAD("Actividad"), 
	EMPLEO("Empleo");

	private String type;

	LaboralRateType(String type){
		this.type = type;
	}
	
	public String getValue(){
		return type;
	}
}
