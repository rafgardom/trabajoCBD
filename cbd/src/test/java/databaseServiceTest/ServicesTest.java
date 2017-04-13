package databaseServiceTest;

import java.net.UnknownHostException;

import org.junit.Test;

import util.DatabaseService;

public class ServicesTest {

//	@Test
//	public void dropConsumptionTest() throws UnknownHostException{
//		DatabaseService util = new DatabaseService();
//		util.dropConsumptionCollection();
//	}
	
	@Test
	public void findByLocation() throws UnknownHostException{
		DatabaseService ds = new DatabaseService();
		ds.findByLocationProvincia("CUENCA");
	}
	
	@Test
	public void findByLocation2() throws UnknownHostException{
		DatabaseService ds = new DatabaseService();
		ds.findByLocationCP("16002");
		ds.findByLocationCP(16002);
	}
	
	@Test
	public void findByLocation3() throws UnknownHostException{
		DatabaseService ds = new DatabaseService();
		ds.findByLocationPoblacion("cuenca");
	}
	
	@Test
	public void findByLocation4() throws UnknownHostException{
		DatabaseService ds = new DatabaseService();
		ds.findByLocationCups("ES0032000002955582LS");
	}
	
	@Test
	public void findByLocation5() throws UnknownHostException{
		DatabaseService ds = new DatabaseService();
		ds.findByLocationCups2("ES0032000002955582LS");
	}
	
	
}
