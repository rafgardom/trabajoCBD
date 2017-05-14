package graphics;

import java.net.UnknownHostException;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import forms.ConsumptionForm;
import util.Queries_consumption;

public class ConsumptionGraph {

	public static void generateConsumptionLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			String year) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_consumption cq = new Queries_consumption();
		int contT1=0, contT2=0, contT3=0, contT4=0;
		List<ConsumptionForm> consumptionForms = cq.getActiveEnergyMaxPowerByYear(year);
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
		
		
		data.addValue(activeEnergyT1, "Energía activa", "T1");
		data.addValue(activeEnergyT2, "Energía activa", "T2");
		data.addValue(activeEnergyT3, "Energía activa", "T3");
		data.addValue(activeEnergyT4, "Energía activa", "T4");
		
		data.addValue(reactiveEnergyT1, "Energía reactiva", "T1");
		data.addValue(reactiveEnergyT2, "Energía reactiva", "T2");
		data.addValue(reactiveEnergyT3, "Energía reactiva", "T3");
		data.addValue(reactiveEnergyT4, "Energía reactiva", "T4");
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void generateConsumptionBarGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			String year) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_consumption cq = new Queries_consumption();
		int contT1=0, contT2=0, contT3=0, contT4=0;
		List<ConsumptionForm> consumptionForms = cq.getActiveEnergyMaxPowerByYear(year);
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
		
		
		data.addValue(activeEnergyT1, "Energía activa", "T1");
		data.addValue(activeEnergyT2, "Energía activa", "T2");
		data.addValue(activeEnergyT3, "Energía activa", "T3");
		data.addValue(activeEnergyT4, "Energía activa", "T4");
		
		data.addValue(reactiveEnergyT1, "Energía reactiva", "T1");
		data.addValue(reactiveEnergyT2, "Energía reactiva", "T2");
		data.addValue(reactiveEnergyT3, "Energía reactiva", "T3");
		data.addValue(reactiveEnergyT4, "Energía reactiva", "T4");
		
		gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void main(String[] args) throws UnknownHostException {
		ConsumptionGraph.generateConsumptionLineGraph("Gráfica", "Consumo de energía (P3)", "Trimestre", "Consumo", "2014");
		ConsumptionGraph.generateConsumptionBarGraph("Gráfica", "Consumo de energía (P3)", "Trimestre", "Consumo", "2014");
	}
}
