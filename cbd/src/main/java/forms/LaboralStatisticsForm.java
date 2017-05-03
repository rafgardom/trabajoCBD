package forms;

public class LaboralStatisticsForm {

	private Float Tasadeparo;
	private Float Tasadeactividad;
	private Float Tasadeempleo;
	
	public LaboralStatisticsForm(){
		
	}
	
	

	public LaboralStatisticsForm(Float tasadeparo, Float tasadeactividad, Float tasadeempleo) {
		super();
		Tasadeparo = tasadeparo;
		Tasadeactividad = tasadeactividad;
		Tasadeempleo = tasadeempleo;
	}



	public Float getTasadeparo() {
		return Tasadeparo;
	}

	public void setTasadeparo(Float tasadeparo) {
		Tasadeparo = tasadeparo;
	}

	public Float getTasadeactividad() {
		return Tasadeactividad;
	}

	public void setTasadeactividad(Float tasadeactividad) {
		Tasadeactividad = tasadeactividad;
	}

	public Float getTasadeempleo() {
		return Tasadeempleo;
	}

	public void setTasadeempleo(Float tasadeempleo) {
		Tasadeempleo = tasadeempleo;
	}

	@Override
	public String toString() {
		return "LaboralStatisticsForm [Tasadeparo=" + Tasadeparo + ", Tasadeactividad=" + Tasadeactividad
				+ ", Tasadeempleo=" + Tasadeempleo + "]";
	}
	
	
}
