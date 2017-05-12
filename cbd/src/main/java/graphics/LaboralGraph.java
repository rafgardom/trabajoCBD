package graphics;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Lf2SpacesIndenter;
import com.google.common.collect.Lists;

import enumerados.LaboralRateType;
import enumerados.LaboralType;
import forms.LaboralForm;
import util.Queries_laboral;

public class LaboralGraph {

	/**
	 * Genera un gráfico de líneas de actividad laboral
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param year
	 * @param laboralType
	 * @throws UnknownHostException
	 */
	public void generateLaboralRateLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			String year, LaboralType laboralType, LaboralRateType laboralRateType) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_laboral ql = new Queries_laboral();
		List<LaboralForm> laboralForms = ql.findYearFilterSummary(year, laboralType);
		
		for(LaboralForm lf: laboralForms){
			
			if(laboralRateType.getValue().equals("Paro")){
				data.addValue(lf.getStatistics().getTasadeparo(), "Actividad laboral (Paro)", lf.getDataName());
				
			}else if(laboralRateType.getValue().equals("Empleo")){
				data.addValue(lf.getStatistics().getTasadeempleo(), "Actividad laboral (Empleo)", lf.getDataName());
				
			}else if(laboralRateType.getValue().equals("Actividad")){
				data.addValue(lf.getStatistics().getTasadeactividad(), "Actividad laboral (Actividad)", lf.getDataName());
			}
			
		}
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	/***
	 * Genera una gráfica de líneas multiples. Cada uno de los LaboralType se corresponde con una línea dentro de la gráfica
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param year
	 * @param laboralTypes
	 * @throws UnknownHostException
	 */
	public void generateLaboralRateMultipleLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			String year, Collection<LaboralType> laboralTypes, LaboralRateType laboralRateType) throws UnknownHostException{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_laboral ql = new Queries_laboral();
		List<List<LaboralForm>> laboralFormsLists = Lists.newArrayList();
		GraphUtil gu = new GraphUtil();
		
		for(LaboralType laboralType: laboralTypes){
			List<LaboralForm> laboralForms = ql.findYearFilterSummary(year, laboralType);
			laboralFormsLists.add(laboralForms);
		}
		
		for(List<LaboralForm> lfs: laboralFormsLists){
			for(LaboralForm laboralForm: lfs){
				if(laboralForm.getDataName().contains("General")){
					if(laboralForm.getDataName().contains("T1")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "General", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "General", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "General", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "General", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Primariaoinferior")){
					if(laboralForm.getDataName().contains("T1")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Primariaoinferior", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Primariaoinferior", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Primariaoinferior", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Primariaoinferior", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Secundariaprimeraetapayformacionprofesional")){
					if(laboralForm.getDataName().contains("T1")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariaprimeraetapayformacionprofesional", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariaprimeraetapayformacionprofesional", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariaprimeraetapayformacionprofesional", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariaprimeraetapayformacionprofesional", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Secundariasegundaetapayformaciónprofesional")){
					if(laboralForm.getDataName().contains("T1")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariasegundaetapayformaciónprofesional", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariasegundaetapayformaciónprofesional", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariasegundaetapayformaciónprofesional", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Secundariasegundaetapayformaciónprofesional", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Educaciónsuperior")){
					if(laboralForm.getDataName().contains("T1")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Educaciónsuperior", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Educaciónsuperior", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Educaciónsuperior", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						this.addDataSetValue(laboralForm, data, laboralRateType, "Educaciónsuperior", "T4");
					}
					
				}
				
			}
			
		}
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	/**
	 * Genera una gráfica de líneas multiples. Compara la actividad laboral en 2-3 años según el laboralType indicado
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param year1
	 * @param year2
	 * @param year3
	 * @param laboralType
	 * @param laboralRateType
	 * @throws UnknownHostException
	 */
	public void generateLaboralRateMultipleLineGraphByYear(String windowName, String graphName, String xAxisName, String yAxisName, 
			String year1, String year2, String year3, LaboralType laboralType, LaboralRateType laboralRateType) throws UnknownHostException{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_laboral ql = new Queries_laboral();
		GraphUtil gu = new GraphUtil();
		List<LaboralForm> laboralFormsList3 = Lists.newArrayList();
		
		List<LaboralForm> laboralFormsList1 = ql.findYearFilterSummary(year1, laboralType);
		List<LaboralForm> laboralFormsList2 = ql.findYearFilterSummary(year2, laboralType);
		if(year3 != null){
			laboralFormsList3 = ql.findYearFilterSummary(year3, laboralType);
		}
			for(LaboralForm laboralForm: laboralFormsList1){
				
				if(laboralForm.getDataName().contains("T1")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year1, "T1");
				}else if(laboralForm.getDataName().contains("T2")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year1, "T2");
				}else if(laboralForm.getDataName().contains("T3")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year1, "T3");
				}else if(laboralForm.getDataName().contains("T4")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year1, "T4");
				}
				
			}
			
			for(LaboralForm laboralForm: laboralFormsList2){
				
				if(laboralForm.getDataName().contains("T1")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year2, "T1");
				}else if(laboralForm.getDataName().contains("T2")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year2, "T2");
				}else if(laboralForm.getDataName().contains("T3")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year2, "T3");
				}else if(laboralForm.getDataName().contains("T4")){
					this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year2, "T4");
				}
			}
			
			if(laboralFormsList3 != null){
				for(LaboralForm laboralForm: laboralFormsList3){
					if(laboralForm.getDataName().contains("T1")){
						this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year3, "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year3, "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year3, "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						this.addDataSetValue(laboralForm, data, laboralRateType, laboralType.getValue()+year3, "T4");
					}
				}
			}
			
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
		
	}
	
	private void addDataSetValue(LaboralForm laboralForm, DefaultCategoryDataset data, LaboralRateType laboralRateType, String type, String period){
		if(laboralRateType.getValue().equals("Paro")){
			data.addValue(laboralForm.getStatistics().getTasadeparo(), type, period);
			
		}else if(laboralRateType.getValue().equals("Actividad")){
			data.addValue(laboralForm.getStatistics().getTasadeactividad(), type, period);
			
		}else if(laboralRateType.getValue().equals("Empleo")){
			data.addValue(laboralForm.getStatistics().getTasadeempleo(), type, period);
		}
	}
	
	/**
	 * Genera un gráfico de barras de actividad laboral
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param year
	 * @param laboralType
	 * @throws UnknownHostException
	 */
	public void generateLaboralRateBarGraph(String windowName,String graphName, String xAxisName, String yAxisName, 
			String year, LaboralType laboralType, LaboralRateType laboralRateType) throws UnknownHostException{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		GraphUtil gu = new GraphUtil();
		Queries_laboral ql = new Queries_laboral();
		List<LaboralForm> laboralForms = ql.findYearFilterSummary(year, laboralType);
		
		for(LaboralForm lf: laboralForms){

			if(laboralRateType.getValue().equals("Paro")){
				data.addValue(lf.getStatistics().getTasadeparo(), "Actividad laboral (Paro)", lf.getDataName());
				
			}else if(laboralRateType.getValue().equals("Empleo")){
				data.addValue(lf.getStatistics().getTasadeempleo(), "Actividad laboral (Empleo)", lf.getDataName());
				
			}else if(laboralRateType.getValue().equals("Actividad")){
				data.addValue(lf.getStatistics().getTasadeactividad(), "Actividad laboral (Actividad)", lf.getDataName());
			}
		}
		
		gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	
	public static void main(String[] args) throws UnknownHostException {
		
		List<LaboralType> laboralTypes = Lists.newArrayList();
		laboralTypes.add(LaboralType.GENERAL);
		laboralTypes.add(LaboralType.PRIMARIA_O_INFERIOR);
		LaboralGraph lg = new LaboralGraph();
		lg.generateLaboralRateLineGraph("Actividad laboral", "Paro", "Cuatrimestres", "Tasa de paro", "2013", 
				LaboralType.PRIMARIA_O_INFERIOR, LaboralRateType.PARO);
		lg.generateLaboralRateBarGraph("Actividad laboral", "Empleo", "Cuatrimestres", "Tasa de empleo", "2013", 
				LaboralType.PRIMARIA_O_INFERIOR, LaboralRateType.EMPLEO);
		lg.generateLaboralRateMultipleLineGraph("Actividad laboral", LaboralRateType.EMPLEO.getValue(), "Cuatrimestres", 
				"Tasa de empleo", "2013",laboralTypes, LaboralRateType.EMPLEO);
		
		lg.generateLaboralRateMultipleLineGraphByYear("Actividad laboral", LaboralRateType.PARO.getValue(), "Cuatrimestre",
				"Tasa paro", "2013", "2015", "2016",LaboralType.EDUCACION_SUPERIOR, LaboralRateType.PARO);
	}
}
