package databaseServiceTest;

import java.net.UnknownHostException;

import org.junit.Test;

import util.DatabaseService;

public class ServicesTest {

	@Test
	public void dropConsumptionTest() throws UnknownHostException{
		DatabaseService util = new DatabaseService();
		util.dropConsumptionCollection();
	}
}
