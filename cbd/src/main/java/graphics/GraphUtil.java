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
	 * @param windowName(Nombre de la ventana)
	 * @param graphName (Título de la gráfica)
	 * @param xAxisName (Nombre Eje x)
	 * @param yAxisName (Nombre Eje y)
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
	
	
}
