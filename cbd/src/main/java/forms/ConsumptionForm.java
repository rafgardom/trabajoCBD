package forms;

public class ConsumptionForm {

	private Integer energiaActivaP3;
	private String fechaActualLectura;
	private String anio;
	private Integer energiaReactivaP3;
	
	
	public ConsumptionForm() {
		super();
	}


	public ConsumptionForm(Integer energiaActivaP3, String fechaActualLectura, String anio, Integer energiaReactivaP3) {
		super();
		this.energiaActivaP3 = energiaActivaP3;
		this.fechaActualLectura = fechaActualLectura;
		this.anio = anio;
		this.energiaReactivaP3 = energiaReactivaP3;
	}



	public Integer getEnergiaActivaP3() {
		return energiaActivaP3;
	}



	public void setEnergiaActivaP3(Integer energiaActivaP3) {
		this.energiaActivaP3 = energiaActivaP3;
	}



	public String getFechaActualLectura() {
		return fechaActualLectura;
	}



	public void setFechaActualLectura(String fechaActualLectura) {
		this.fechaActualLectura = fechaActualLectura;
	}



	public String getAnio() {
		return anio;
	}



	public void setAnio(String anio) {
		this.anio = anio;
	}



	public Integer getEnergiaReactivaP3() {
		return energiaReactivaP3;
	}



	public void setEnergiaReactivaP3(Integer energiaReactivaP3) {
		this.energiaReactivaP3 = energiaReactivaP3;
	}



	@Override
	public String toString() {
		return "ConsumptionForm [energiaActivaP3=" + energiaActivaP3 + ", fechaActualLectura=" + fechaActualLectura
				+ ", anio=" + anio + ", energiaReactivaP3=" + energiaReactivaP3 + "]";
	}


	
}
