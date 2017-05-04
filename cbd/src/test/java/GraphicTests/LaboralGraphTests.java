package GraphicTests;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import enumerados.LaboralType;
import graphics.GraphUtil;

public class LaboralGraphTests {

	@Test
	public void lineGraphTest() throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		gu.generateLaboralRateLineGraph("Actividad laboral", "Paro", "Cuatrimestres", "Tasa de paro", "2013", 
				LaboralType.PRIMARIA_O_INFERIOR);
		
	}
	
	@Test
	public void barGraphTest() throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		gu.generateLaboralRateBarGraph("Actividad laboral", "Paro", "Cuatrimestres", "Tasa de paro", "2013", 
				LaboralType.PRIMARIA_O_INFERIOR);
		
	}
	
	@Test
	public void multipleGraphTest() throws UnknownHostException{
		GraphUtil gu = new GraphUtil();
		
		List<LaboralType> laboralTypes = Lists.newArrayList();
		laboralTypes.add(LaboralType.GENERAL);
		laboralTypes.add(LaboralType.PRIMARIA_O_INFERIOR);
		
		gu.generateLaboralRateMultipleLineGraph("Actividad laboral", "Paro", "Cuatrimestres", 
				"Tasa de paro", "2013",laboralTypes);
		
	}
}
