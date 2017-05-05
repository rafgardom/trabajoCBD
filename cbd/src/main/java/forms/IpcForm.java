package forms;

public class IpcForm {

	private String month;
	private String year;
	private float value;
	
	public IpcForm() {
		super();
	}

	public IpcForm(String month, String year, float value) {
		super();
		this.month = month;
		this.year = year;
		this.value = value;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IpcForm [month=" + month + ", year=" + year + ", value=" + value + "]";
	}
	
	
}
