import java.net.UnknownHostException;
import org.junit.Test;
import enumerados.TipoEnergia;
import util.Queries_consumption;

public class TestQueries_consumption {
	
	@Test
	public void findByLocation() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.findByLocationProvincia("CUENCA");
	}
	
	@Test
	public void findByLocation2() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.findByLocationCP("16002");
		ds.findByLocationCP(16002);
	}
	
	@Test
	public void findByLocation3() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.findByLocationPoblacion("cuenca");
	}
	
	@Test
	public void findByLocation4() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.findByLocationCups("ES0032000002955582LS");
	}
	
	@Test
	public void findByLocation5() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.findByLocationCups2("ES0032000002955582LS");
	}
	
	@Test
	public void getConsumosById() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.getConsumosById("58fbaeb3d03ec399b136215a");
	}
	
	@Test
	public void getAllId() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.getAllId();
	}
	
	@Test
	public void getConsumoMediobyId() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.getConsumoMediobyId(TipoEnergia.energia_activa_p1.toString(), "58fbaeb3d03ec399b136215a");
	}
	
	@Test
	public void getConsumoMediobyAnio() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.getConsumoMedioByAnio("energia_activa_p1",2015);
	}
	
	@Test
	public void getAllImpagos() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.getAllImpagos();
	}
	
	@Test
	public void getAllImpagosByAnio() throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		ds.getAllImpagosByAnio("2016").next();
	}

}
