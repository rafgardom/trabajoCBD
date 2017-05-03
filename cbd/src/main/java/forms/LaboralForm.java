package forms;

public class LaboralForm {

	private String dataName;
	private LaboralStatisticsForm statistics;
	
	public LaboralForm(){
		
	}

	
	public LaboralForm(String dataName, LaboralStatisticsForm statistics) {
		super();
		this.dataName = dataName;
		this.statistics = statistics;
	}


	public String getDataName() {
		return dataName;
	}


	public void setDataName(String dataName) {
		this.dataName = dataName;
	}


	public LaboralStatisticsForm getStatistics() {
		return statistics;
	}

	public void setStatistics(LaboralStatisticsForm statistics) {
		this.statistics = statistics;
	}

	@Override
	public String toString() {
		return "LaboralForm [statistics=" + statistics + "]";
	}
	
	
	
}
