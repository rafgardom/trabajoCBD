package util;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoConnection {

	//Uses to connect to DB
	@SuppressWarnings("deprecation")
	public static DB connect(String address, int port) throws UnknownHostException{
		MongoClient mongo = new MongoClient(address, port);
		DB db = mongo.getDB("trabajoCBD");
		return db;
	}
	
	
}
