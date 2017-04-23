package connectionTest;

import java.net.UnknownHostException;
import java.util.Collection;

import com.mongodb.DBObject;

import util.DatabaseService;
import util.Queries_consumption;

public class ConnectionTest {

	public static void main(String[] args) throws UnknownHostException {
		Queries_consumption test = new Queries_consumption();
		DatabaseService.getCollection("consumption");
		Collection<DBObject> object = test.getAllId();
		System.out.println(object.toString());
	}
}
