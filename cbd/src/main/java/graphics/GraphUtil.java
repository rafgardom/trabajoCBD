package graphics;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.google.common.collect.Lists;

import enumerados.LaboralType;
import forms.LaboralForm;
import util.Queries_laboral;

public class GraphUtil extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GraphUtil(){
		
	}
	
	/**
	 * Crea una gráfica de líneas
	 * 
	 * @param windowName (Nombre de la ventana)
	 * @param graphName (Título de la gráfica)
	 * @param xAxisName (Nombre Eje x)
	 * @param yAxisName (Nombre Eje y)
	 * @param data
	 */
	public void createLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, DefaultCategoryDataset data){
		JFreeChart graph = ChartFactory.createLineChart(graphName, xAxisName, yAxisName, data, PlotOrientation.VERTICAL,
				true, true, false);
		
		ChartPanel panel = new ChartPanel(graph);
		JFrame window = new JFrame(windowName);
		window.getContentPane().add(panel);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Crea una gráfica de barras
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param data
	 */
	public void createBarGraph(String windowName, String graphName, String xAxisName, String yAxisName, DefaultCategoryDataset data){
		JFreeChart graph = ChartFactory.createBarChart(graphName, xAxisName, yAxisName, data, PlotOrientation.VERTICAL,
				true, true, false);
		
		ChartPanel panel = new ChartPanel(graph);
		JFrame window = new JFrame(windowName);
		window.getContentPane().add(panel);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void generateLaboralRateLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			String year, LaboralType laboralType) throws UnknownHostException{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_laboral ql = new Queries_laboral();
		List<LaboralForm> laboralForms = ql.findYearFilterSummary(year, laboralType);
		
		for(LaboralForm lf: laboralForms){
			data.addValue(lf.getStatistics().getTasadeparo(), "Actividad laboral", lf.getDataName());
		}
		
		this.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public void generateLaboralRateMultipleLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			String year, Collection<LaboralType> laboralTypes) throws UnknownHostException{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_laboral ql = new Queries_laboral();
		List<List<LaboralForm>> laboralFormsLists = Lists.newArrayList();
//		List<LaboralForm> laboralForms = ql.findYearFilterSummary(year, laboralType);
		
		for(LaboralType laboralType: laboralTypes){
			List<LaboralForm> laboralForms = ql.findYearFilterSummary(year, laboralType);
			laboralFormsLists.add(laboralForms);
		}
		
		for(List<LaboralForm> lfs: laboralFormsLists){
			for(LaboralForm laboralForm: lfs){
				if(laboralForm.getDataName().contains("General")){
					if(laboralForm.getDataName().contains("T1")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "General", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "General", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "General", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "General", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Primariaoinferior")){
					if(laboralForm.getDataName().contains("T1")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Primariaoinferior", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Primariaoinferior", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Primariaoinferior", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Primariaoinferior", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Secundariaprimeraetapayformacionprofesional")){
					if(laboralForm.getDataName().contains("T1")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariaprimeraetapayformacionprofesional", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariaprimeraetapayformacionprofesional", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariaprimeraetapayformacionprofesional", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariaprimeraetapayformacionprofesional", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Secundariasegundaetapayformaciónprofesional")){
					if(laboralForm.getDataName().contains("T1")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariasegundaetapayformaciónprofesional", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariasegundaetapayformaciónprofesional", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariasegundaetapayformaciónprofesional", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Secundariasegundaetapayformaciónprofesional", "T4");
					}
					
				}else if(laboralForm.getDataName().contains("Educaciónsuperior")){
					if(laboralForm.getDataName().contains("T1")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Educaciónsuperior", "T1");
					}else if(laboralForm.getDataName().contains("T2")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Educaciónsuperior", "T2");
					}else if(laboralForm.getDataName().contains("T3")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Educaciónsuperior", "T3");
					}else if(laboralForm.getDataName().contains("T4")){
						data.addValue(laboralForm.getStatistics().getTasadeparo(), "Educaciónsuperior", "T4");
					}
					
				}
				
			}
			
		}
		
		this.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	
	public void generateLaboralRateBarGraph(String windowName,String graphName, String xAxisName, String yAxisName, String year, LaboralType laboralType) throws UnknownHostException{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_laboral ql = new Queries_laboral();
		List<LaboralForm> laboralForms = ql.findYearFilterSummary(year, laboralType);
		
		for(LaboralForm lf: laboralForms){
			data.addValue(lf.getStatistics().getTasadeparo(), "Actividad laboral", lf.getDataName());
		}
		
		this.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	public static void main(String[] args) throws UnknownHostException {
		
		List<LaboralType> laboralTypes = Lists.newArrayList();
		laboralTypes.add(LaboralType.GENERAL);
		laboralTypes.add(LaboralType.PRIMARIA_O_INFERIOR);
		GraphUtil gu = new GraphUtil();
		gu.generateLaboralRateLineGraph("Actividad laboral", "Paro", "Cuatrimestres", "Tasa de paro", "2013", LaboralType.PRIMARIA_O_INFERIOR);
		gu.generateLaboralRateBarGraph("Actividad laboral", "Paro", "Cuatrimestres", "Tasa de paro", "2013", LaboralType.PRIMARIA_O_INFERIOR);
		gu.generateLaboralRateMultipleLineGraph("Actividad laboral", "Paro", "Cuatrimestres", 
				"Tasa de paro", "2013",laboralTypes);
	}
}
