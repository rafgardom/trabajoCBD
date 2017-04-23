package util;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class Queries_laboral {
	
	//Find one by id from laboral table
	public DBObject findLaboralById(String id) throws UnknownHostException{
		DBObject result;
		DBCollection table = DatabaseService.getCollection("laboral");
		DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
		result = table.findOne(searchById);
		return result;
	}

}
