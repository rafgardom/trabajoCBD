package graphics;

import java.net.UnknownHostException;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import forms.IpcForm;
import util.Queries_ipc;

public class IpcGraph {

	/**
	 * Genera una gráfica de líneas indicando la variación del IPC comprendida entre openPeriodYear y endPeriodYear
	 * (ambas cantidades en años)
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param openPeriodYear
	 * @param endPeriodYear
	 * @throws UnknownHostException
	 */
	public static void generateIpcVariationLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer openPeriodYear, Integer endPeriodYear) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		
		List<IpcForm> ipcForms = qIpc.getAll_TotalNacionalVariacionMensual(openPeriodYear, endPeriodYear);
		
		for(IpcForm ipcForm: ipcForms){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
			System.out.println(ipcForm);
		}
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	/**
	 * Genera una gráfica de líneas indicando el nivel del IPC. Dichos niveles entre openPeriodYear y endPeriodYear
	 * (ambas cantidades en años)
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param openPeriodYear
	 * @param endPeriodYear
	 * @throws UnknownHostException
	 */
	public static void generateIpcLevelLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer openPeriodYear, Integer endPeriodYear) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		
		List<IpcForm> ipcForms = qIpc.getAll_TotalNacionalIndice(openPeriodYear, endPeriodYear);
		
		for(IpcForm ipcForm: ipcForms){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	
	/**
	 * 	Genera una gráfica de barras indicando la variación del IPC comprendida entre openPeriodYear y endPeriodYear
	 * (ambas cantidades en años)
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param openPeriodYear
	 * @param endPeriodYear
	 * @throws UnknownHostException
	 */
	public static void generateIpcVariationBarGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer openPeriodYear, Integer endPeriodYear) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		
		List<IpcForm> ipcForms = qIpc.getAll_TotalNacionalVariacionMensual(openPeriodYear, endPeriodYear);
		
		for(IpcForm ipcForm: ipcForms){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	/**
	 * Genera una gráfica de barras indicando el nivel del IPC. Dichos niveles entre openPeriodYear y endPeriodYear
	 * (ambas cantidades en años)
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param openPeriodYear
	 * @param endPeriodYear
	 * @throws UnknownHostException
	 */
	public static void generateIpcLevelBarGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer openPeriodYear, Integer endPeriodYear) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		
		List<IpcForm> ipcForms = qIpc.getAll_TotalNacionalIndice(openPeriodYear, endPeriodYear);
		
		for(IpcForm ipcForm: ipcForms){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		gu.createBarGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	
	/**
	 * Genera una gráfica de líneas multiple con la variación del IPC. 
	 * 
	 * firstCompareYear, secondCompareYear, thirdCompareYear deben ser años diferentes. 
	 * 
	 * thirdCompareYear si se quiere hacer opcional basta con indicarlo como null.
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param firstCompareYear
	 * @param secondCompareYear
	 * @param thirdCompareYear (optional)
	 * @throws UnknownHostException
	 */
	public static void generateIpcVariationMultipleLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer firstCompareYear, Integer secondCompareYear, Integer thirdCompareYear) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		
		
		List<IpcForm> ipcFormsFirstYear = qIpc.getAll_TotalNacionalVariacionMensual(firstCompareYear, firstCompareYear);
		for(IpcForm ipcForm: ipcFormsFirstYear){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		List<IpcForm> ipcFormsSecondYear = qIpc.getAll_TotalNacionalVariacionMensual(secondCompareYear, secondCompareYear);
		for(IpcForm ipcForm: ipcFormsSecondYear){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		if(thirdCompareYear != null){
			List<IpcForm> ipcFormsThirdYear = qIpc.getAll_TotalNacionalVariacionMensual(thirdCompareYear, thirdCompareYear);
			for(IpcForm ipcForm: ipcFormsThirdYear){
				data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
			}
		}
		
		
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	/**
	 * Genera una gráfica de líneas multiple indicando los niveles del IPC. 
	 * 
	 * firstCompareYear, secondCompareYear, thirdCompareYear deben ser años diferentes. 
	 * 
	 * thirdCompareYear si se quiere hacer opcional basta con indicarlo como null.
	 * 
	 * @param windowName
	 * @param graphName
	 * @param xAxisName
	 * @param yAxisName
	 * @param firstCompareYear
	 * @param secondCompareYear
	 * @param thirdCompareYear
	 * @throws UnknownHostException
	 */
	public void generateIpcLevelMultipleLineGraph(String windowName, String graphName, String xAxisName, String yAxisName, 
			Integer firstCompareYear, Integer secondCompareYear, Integer thirdCompareYear) throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Queries_ipc qIpc = new Queries_ipc();
		
		
		List<IpcForm> ipcFormsFirstYear = qIpc.getAll_TotalNacionalIndice(firstCompareYear, firstCompareYear);
		for(IpcForm ipcForm: ipcFormsFirstYear){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		List<IpcForm> ipcFormsSecondYear = qIpc.getAll_TotalNacionalIndice(secondCompareYear, secondCompareYear);
		for(IpcForm ipcForm: ipcFormsSecondYear){
			data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
		}
		
		if(thirdCompareYear != null){
			List<IpcForm> ipcFormsThirdYear = qIpc.getAll_TotalNacionalIndice(thirdCompareYear, thirdCompareYear);
			for(IpcForm ipcForm: ipcFormsThirdYear){
				data.addValue(ipcForm.getValue(), "Evolución IPC (" + ipcForm.getYear() + ")", ipcForm.getMonth());
			}
		}
		
		
		
		gu.createLineGraph(windowName, graphName, xAxisName, yAxisName, data);
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws UnknownHostException {
		IpcGraph ipc = new IpcGraph();
		
		ipc.generateIpcVariationLineGraph("IPC", "IPC en 2016", "Mes", "Valor", 2016, 2016);
		ipc.generateIpcVariationBarGraph("IPC", "IPC en 2016", "Mes", "Valor", 2016, 2016);
		ipc.generateIpcVariationMultipleLineGraph("IPC", "Variación IPC", "Mes", "Valor", 2016, 2015, 2013);
		
		ipc.generateIpcLevelLineGraph("IPC", "IPC en 2016", "Mes", "Valor", 2016, 2016);
		ipc.generateIpcLevelBarGraph("IPC", "IPC en 2016", "Mes", "Valor", 2016, 2016);
		ipc.generateIpcLevelMultipleLineGraph("IPC", "Variación IPC", "Mes", "Valor", 2016, 2015, 2013);
	}
}
