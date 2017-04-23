package util;

import java.net.UnknownHostException;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class DatabaseService {
	
	

	//Get table consumption
	public static DBCollection getCollection(String collectionName) throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		DBCollection table = db.getCollection(collectionName);
		return table;
	}
	
	//Drop collection
	public void dropCollection(String name) throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		try{
			DBCollection table = db.getCollection(name);
			table.drop();
		}catch(Throwable e){
			System.err.println(e.toString());
		}
	}
	
}
