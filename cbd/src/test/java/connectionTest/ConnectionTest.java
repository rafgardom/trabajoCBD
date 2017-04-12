package connectionTest;

import java.net.UnknownHostException;

import com.mongodb.DBObject;

import util.DatabaseServices;

public class ConnectionTest {

	public static void main(String[] args) throws UnknownHostException {
		DatabaseServices test = new DatabaseServices();
		test.getConsumption();
		DBObject object = test.findConsumptionById("58ee110473bd4cb49d891a24");
		System.out.println(object.toString());
	}
}
