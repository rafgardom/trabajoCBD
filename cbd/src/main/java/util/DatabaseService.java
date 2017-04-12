package util;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class DatabaseService {

	//Get table consumption
	public DBCollection getConsumption() throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		DBCollection table = db.getCollection("consumption");
		
			//Debug -- comentar si no es para depurar
//				System.out.println(table.findOne());
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
	
	
	//Drop all consumption collection
	public void dropConsumptionCollection() throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		
		DBCollection table = db.getCollection("consumption");
		table.drop();
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
