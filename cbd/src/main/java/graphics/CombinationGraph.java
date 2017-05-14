package graphics;

import java.net.UnknownHostException;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import com.google.common.collect.Lists;

import enumerados.LaboralRateType;
import enumerados.LaboralType;
import forms.IpcForm;
import forms.LaboralForm;
import junit.framework.Assert;
import util.Queries_ipc;
import util.Queries_laboral;

public class CombinationGraph {

	private void addDataSetValue(LaboralForm laboralForm, DefaultCategoryDataset data, LaboralRateType laboralRateType, String type, String period){
		if(laboralRateType.getValue().equals("Paro")){
			data.addValue(laboralForm.getStatistics().getTasadeparo(), type, period);
			
		}else if(laboralRateType.getValue().equals("Actividad")){
			data.addValue(laboralForm.getStatistics().getTasadeactividad(), type, period);
			
		}else if(laboralRateType.getValue().equals("Empleo")){
			data.addValue(laboralForm.getStatistics().getTasadeempleo(), type, period);
		}
	}
	
	private List<IpcForm> getIpcFormByQuarter(List<IpcForm> ipcForms){
		List<IpcForm> result = Lists.newArrayList();
		Assert.assertTrue(!ipcForms.isEmpty());
		Float mediumT1 = null;
		Float mediumT2 = null;
		Float mediumT3 = null;
		Float mediumT4 = null;
		
		for(int i = 0; i<= 2; i++){
			mediumT1 = ipcForms.get(i).getValue();
		}
		
		for(int i=3; i<= 5; i++){
			mediumT2 = ipcForms.get(i).getValue();
		}
		
		for(int i=6; i<= 8; i++){
			mediumT3 = ipcForms.get(i).getValue();
		}
		
		for(int i=9; i<= 11; i++){
			mediumT4 = ipcForms.get(i).getValue();
		}
		
		IpcForm ipc1 = new IpcForm("T1", ipcForms.get(0).getYear(), mediumT1/3);
		IpcForm ipc2 = new IpcForm("T2", ipcForms.get(0).getYear(), mediumT2/3);
		IpcForm ipc3 = new IpcForm("T3", ipcForms.get(0).getYear(), mediumT3/3);
		IpcForm ipc4 = new IpcForm("T4", ipcForms.get(0).getYear(), mediumT4/3);
		
		result.add(ipc1);
		result.add(ipc2);
		result.add(ipc3);
		result.add(ipc4);
		
		return result;
	}
	
	
	public void generateLaboralIpcGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer year, LaboralRateType laboralRateType) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		Queries_laboral ql = new Queries_laboral();
		Assert.assertTrue(year >= 2013);
		
		List<IpcForm> ipcForms;
		List<LaboralForm> laboralForms;
		
		ipcForms = qIpc.getAll_TotalNacionalIndice(year, year);
		
		laboralForms = ql.findYearFilterSummary(year.toString(), LaboralType.GENERAL);
		ipcForms = this.getIpcFormByQuarter(ipcForms);
		
		for(IpcForm ipcForm: ipcForms){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		for(LaboralForm laboralForm: laboralForms){
			if(laboralForm.getDataName().contains("T1")){
				this.addDataSetValue(laboralForm, data, laboralRateType, laboralRateType.getValue(), "T1");
			}else if(laboralForm.getDataName().contains("T2")){
				this.addDataSetValue(laboralForm, data, laboralRateType,laboralRateType.getValue(), "T2");
			}else if(laboralForm.getDataName().contains("T3")){
				this.addDataSetValue(laboralForm, data, laboralRateType,laboralRateType.getValue(), "T3");
			}else if(laboralForm.getDataName().contains("T4")){
				this.addDataSetValue(laboralForm, data, laboralRateType, laboralRateType.getValue(), "T4");
			}
			
		}
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void main(String[] args) throws UnknownHostException {
		CombinationGraph cg = new CombinationGraph();
		cg.generateLaboralIpcGraph("Estadísticas", "Actividad Laboral vs IPC", "Cuatrimestre",
				"Variación", 2013 , LaboralRateType.PARO);
	}
}
