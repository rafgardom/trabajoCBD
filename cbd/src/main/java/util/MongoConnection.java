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
	private MongoClient connect(String address, int port) throws UnknownHostException{
		MongoClient mongo = new MongoClient(address, port);
		
		return mongo;
	}
	
	//Get table consumption
	public DBCollection getConsumption() throws UnknownHostException{
		MongoClient mongo = this.connect("localhost", 27017);
		DB db = mongo.getDB("trabajoCBD");
		DBCollection table = db.getCollection("consumption");
		
			//Debug -- comentar si no es para depurar
//			System.out.println(table.findOne());
		return table;
	}
	
	//Find one by id from consumption table
	public DBObject findConsumptionById(String id) throws UnknownHostException{
		DBObject result;
		DBCollection table = this.getConsumption();
		DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
		result = table.findOne(searchById);
		
		return result;
	}
	
	//Due to DB size this method will never be used
	@Deprecated
	public Set<DBCollection> getAllDatabase() throws UnknownHostException{
		Set<DBCollection> result = new HashSet<DBCollection>();
		MongoClient mongo = this.connect("localhost", 27017);
		
		DB db = mongo.getDB("trabajoCBD");
		Set<String> tables = db.getCollectionNames();
		for(String table: tables){
			DBCollection row = db.getCollection(table);
			result.add(row);
		}
		
		return result;
	}
	
}
