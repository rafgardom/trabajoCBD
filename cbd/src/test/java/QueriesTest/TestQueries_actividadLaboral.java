package QueriesTest;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;

import enumerados.LaboralType;
import forms.LaboralForm;
import junit.framework.Assert;
import util.Queries_laboral;

public class TestQueries_actividadLaboral {

	@Test
	public void findOne() throws UnknownHostException{
		Queries_laboral finder = new Queries_laboral();
		LaboralForm laboralForm = finder.findSummary("2010", "T4", LaboralType.EDUCACION_SUPERIOR);
		Assert.assertNotNull(laboralForm);
	}
	
	@Test
	public void findFilterYear() throws UnknownHostException{
		Queries_laboral finder = new Queries_laboral();
		List<LaboralForm> laboralForms = finder.findYearFilterSummary("2015", LaboralType.PRIMARIA_O_INFERIOR);
		Assert.assertNotNull(laboralForms);
	}
}
