package connectionTest;

import java.net.UnknownHostException;

import com.mongodb.DBObject;

import util.DatabaseService;

public class ConnectionTest {

	public static void main(String[] args) throws UnknownHostException {
		DatabaseService test = new DatabaseService();
		test.getConsumption();
		DBObject object = test.findConsumptionById("58ee3ef04dd417e657028afc");
		System.out.println(object.toString());
	}
}
