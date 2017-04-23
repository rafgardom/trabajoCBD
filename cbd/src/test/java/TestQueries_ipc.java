

import java.net.UnknownHostException;
import org.junit.Test;
import util.Queries_ipc;

public class TestQueries_ipc {
	
	@Test
	public void getAll_TotalNacionalVariacionMensual() throws UnknownHostException{
		Queries_ipc ds = new Queries_ipc();
		ds.getAll_TotalNacionalVariacionMensual();
	}
	
	@Test
	public void getAll_TotalNacionalVariacionMensual2() throws UnknownHostException{
		Queries_ipc ds = new Queries_ipc();
		ds.getAll_TotalNacionalVariacionMensual(2016,2014);
	}

}
