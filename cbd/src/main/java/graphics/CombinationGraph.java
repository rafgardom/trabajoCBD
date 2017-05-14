package graphics;

import java.net.UnknownHostException;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import com.google.common.collect.Lists;

import enumerados.LaboralRateType;
import enumerados.LaboralType;
import forms.ConsumptionForm;
import forms.IpcForm;
import forms.LaboralForm;
import junit.framework.Assert;
import util.Queries_consumption;
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
	
	public void generateConsumptionIpcGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer year) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		Assert.assertTrue(year >= 2013);
		
		List<IpcForm> ipcForms;
		
		ipcForms = qIpc.getAll_TotalNacionalIndice(year, year);
		
		ipcForms = this.getIpcFormByQuarter(ipcForms);
		
		for(IpcForm ipcForm: ipcForms){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		//Consumption data
		
			Queries_consumption cq = new Queries_consumption();
			int contT1=0, contT2=0, contT3=0, contT4=0;
			List<ConsumptionForm> consumptionForms = cq.getActiveEnergyMaxPowerByYear(year.toString());
			Integer activeEnergyT1=0;
			Integer reactiveEnergyT1=0;
			
			Integer activeEnergyT2=0;
			Integer reactiveEnergyT2=0;
			
			Integer activeEnergyT3=0;
			Integer reactiveEnergyT3=0;
			
			Integer activeEnergyT4=0;
			Integer reactiveEnergyT4=0;
			
			for(int i=0; i<12;i++){
				if(i<3){
					if(consumptionForms.get(i) != null){
						activeEnergyT1 += consumptionForms.get(i).getEnergiaActivaP3();
						reactiveEnergyT1 += consumptionForms.get(i).getEnergiaReactivaP3();
					}else{
						contT1++;
					}
				}
				
				if(i>2 && i<6){
					if(consumptionForms.get(i) != null){
						activeEnergyT2 += consumptionForms.get(i).getEnergiaActivaP3();
						reactiveEnergyT2 += consumptionForms.get(i).getEnergiaReactivaP3();
					}else{
						contT2++;
					}
				}
				
				if(i>5 && i<9){
					if(consumptionForms.get(i) != null){
						activeEnergyT3 += consumptionForms.get(i).getEnergiaActivaP3();
						reactiveEnergyT3 += consumptionForms.get(i).getEnergiaReactivaP3();
					}else{
						contT3++;
					}
				}
				
				if(i>8){
					if(consumptionForms.get(i) != null){
						activeEnergyT4 += consumptionForms.get(i).getEnergiaActivaP3();
						reactiveEnergyT4 += consumptionForms.get(i).getEnergiaReactivaP3();
					}else{
						contT4++;
					}
				}
			}
			
			if(contT1 >= 3){
				activeEnergyT1 = 0;
				reactiveEnergyT1 = 0;
			}else{
				activeEnergyT1 = activeEnergyT1/3;
				reactiveEnergyT1 = reactiveEnergyT1/(3-contT1);
			}
			
			if(contT2 >= 3){
				activeEnergyT2 = 0;
				reactiveEnergyT2 = 0;
			}else{
				activeEnergyT2 = activeEnergyT2/3;
				reactiveEnergyT2 = reactiveEnergyT2/(3-contT2);
			}
			
			if(contT3 >= 3){
				activeEnergyT3 = 0;
				reactiveEnergyT3 = 0;
			}else{
				activeEnergyT3 = activeEnergyT3/3;
				reactiveEnergyT3 = reactiveEnergyT3/(3-contT3);
			}
			
			if(contT4 >= 3){
				activeEnergyT4 = 0;
				reactiveEnergyT4 = 0;
			}else{
				activeEnergyT4 = activeEnergyT4/3;
				reactiveEnergyT4 = reactiveEnergyT4/(3-contT4);
			}
			
			
			data.addValue(activeEnergyT1/10, "Energía activa", "T1");
			data.addValue(activeEnergyT2/10, "Energía activa", "T2");
			data.addValue(activeEnergyT3/10, "Energía activa", "T3");
			data.addValue(activeEnergyT4/10, "Energía activa", "T4");
			
			data.addValue(reactiveEnergyT1/10, "Energía reactiva", "T1");
			data.addValue(reactiveEnergyT2/10, "Energía reactiva", "T2");
			data.addValue(reactiveEnergyT3/10, "Energía reactiva", "T3");
			data.addValue(reactiveEnergyT4/10, "Energía reactiva", "T4");
			
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public void generateLaboralConsumptionGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer year, LaboralRateType laboralRateType) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_laboral ql = new Queries_laboral();
		Assert.assertTrue(year >= 2014);
		
		List<LaboralForm> laboralForms;
		
		//Consumption data
		
		Queries_consumption cq = new Queries_consumption();
		int contT1=0, contT2=0, contT3=0, contT4=0;
		List<ConsumptionForm> consumptionForms = cq.getActiveEnergyMaxPowerByYear(year.toString());
		Integer activeEnergyT1=0;
		Integer reactiveEnergyT1=0;
		
		Integer activeEnergyT2=0;
		Integer reactiveEnergyT2=0;
		
		Integer activeEnergyT3=0;
		Integer reactiveEnergyT3=0;
		
		Integer activeEnergyT4=0;
		Integer reactiveEnergyT4=0;
		
		for(int i=0; i<12;i++){
			if(i<3){
				if(consumptionForms.get(i) != null){
					activeEnergyT1 += consumptionForms.get(i).getEnergiaActivaP3();
					reactiveEnergyT1 += consumptionForms.get(i).getEnergiaReactivaP3();
				}else{
					contT1++;
				}
			}
			
			if(i>2 && i<6){
				if(consumptionForms.get(i) != null){
					activeEnergyT2 += consumptionForms.get(i).getEnergiaActivaP3();
					reactiveEnergyT2 += consumptionForms.get(i).getEnergiaReactivaP3();
				}else{
					contT2++;
				}
			}
			
			if(i>5 && i<9){
				if(consumptionForms.get(i) != null){
					activeEnergyT3 += consumptionForms.get(i).getEnergiaActivaP3();
					reactiveEnergyT3 += consumptionForms.get(i).getEnergiaReactivaP3();
				}else{
					contT3++;
				}
			}
			
			if(i>8){
				if(consumptionForms.get(i) != null){
					activeEnergyT4 += consumptionForms.get(i).getEnergiaActivaP3();
					reactiveEnergyT4 += consumptionForms.get(i).getEnergiaReactivaP3();
				}else{
					contT4++;
				}
			}
		}
		
		if(contT1 >= 3){
			activeEnergyT1 = 0;
			reactiveEnergyT1 = 0;
		}else{
			activeEnergyT1 = activeEnergyT1/3;
			reactiveEnergyT1 = reactiveEnergyT1/(3-contT1);
		}
		
		if(contT2 >= 3){
			activeEnergyT2 = 0;
			reactiveEnergyT2 = 0;
		}else{
			activeEnergyT2 = activeEnergyT2/3;
			reactiveEnergyT2 = reactiveEnergyT2/(3-contT2);
		}
		
		if(contT3 >= 3){
			activeEnergyT3 = 0;
			reactiveEnergyT3 = 0;
		}else{
			activeEnergyT3 = activeEnergyT3/3;
			reactiveEnergyT3 = reactiveEnergyT3/(3-contT3);
		}
		
		if(contT4 >= 3){
			activeEnergyT4 = 0;
			reactiveEnergyT4 = 0;
		}else{
			activeEnergyT4 = activeEnergyT4/3;
			reactiveEnergyT4 = reactiveEnergyT4/(3-contT4);
		}
		
		
		data.addValue(activeEnergyT1/10, "Energía activa", "T1");
		data.addValue(activeEnergyT2/10, "Energía activa", "T2");
		data.addValue(activeEnergyT3/10, "Energía activa", "T3");
		data.addValue(activeEnergyT4/10, "Energía activa", "T4");
		
		data.addValue(reactiveEnergyT1/10, "Energía reactiva", "T1");
		data.addValue(reactiveEnergyT2/10, "Energía reactiva", "T2");
		data.addValue(reactiveEnergyT3/10, "Energía reactiva", "T3");
		data.addValue(reactiveEnergyT4/10, "Energía reactiva", "T4");
		
		
		laboralForms = ql.findYearFilterSummary(year.toString(), LaboralType.GENERAL);
		
		
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
		
		cg.generateLaboralConsumptionGraph("Estadísticas", "Actividad Laboral vs Consumo energético", "Cuatrimestre",
				"Variación-Relación", 2015 , LaboralRateType.PARO);
		
		cg.generateConsumptionIpcGraph("Estadísticas", "Actividad Laboral vs Consumo energético", "Cuatrimestre",
				"Variación-Relación", 2015);
	}
}
