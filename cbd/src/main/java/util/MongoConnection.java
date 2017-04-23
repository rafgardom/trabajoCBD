package util;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnection {

	//Uses to connect to DB
	@SuppressWarnings({ "deprecation" })
	public static DB connect(String address, int port) throws UnknownHostException{
		MongoClient mongo = new MongoClient(address, port);
		DB db = mongo.getDB("trabajoCBD");
		return db;
	}
	
	
}
