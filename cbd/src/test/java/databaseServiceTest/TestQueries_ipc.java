package databaseServiceTest;

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

	@Test
	public void getAll_TotalNacionalIndice() throws UnknownHostException{
		Queries_ipc ds = new Queries_ipc();
		ds.getAll_TotalNacionalIndice();
	}
	
	@Test
	public void getAll_TotalNacionalIndice2() throws UnknownHostException{
		Queries_ipc ds = new Queries_ipc();
		ds.getAll_TotalNacionalIndice(2016, 2014);
	}
}
