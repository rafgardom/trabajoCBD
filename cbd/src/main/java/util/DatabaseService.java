package util;

import java.net.UnknownHostException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.common.collect.Lists;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

import enumerados.PotenciaMax;
import enumerados.TipoEnergia;

public class DatabaseService {
	
	

	//Get table consumption
	public DBCollection getConsumption() throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		DBCollection table = db.getCollection("consumption");
		
			//Debug -- comentar si no es para depurar
//				System.out.println(table.findOne());
		return table;
	}
	
	//Get table consumption
	public DBCollection getLaboral() throws UnknownHostException{
		DB db = MongoConnection.connect("localhost", 27017);
		DBCollection table = db.getCollection("laboral");
		
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
	
	//Find one by id from laboral table
	public DBObject findLaboralById(String id) throws UnknownHostException{
		DBObject result;
		DBCollection table = this.getLaboral();
		DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
		result = table.findOne(searchById);
		
		return result;
	}
	
	
	//Drop all consumption collection
	public void dropConsumptionCollection() throws UnknownHostException{
		this.dropCollection("consumption");
	}
	
	//Drop all consumption collection
	public void dropLaboralCollection() throws UnknownHostException{
		this.dropCollection("laboral");
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
	
	// Encuentra un objeto dado una provincia
	public DBCursor findByLocationProvincia(String location) throws UnknownHostException{
		location = location.toUpperCase();
		DBCollection collection = this.getConsumption();
		DBObject query = new BasicDBObject("localizacion.provincia",java.util.regex.Pattern.compile(location));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado un Codigo Postal. String
	public DBCursor findByLocationCP(String number) throws UnknownHostException{
		DBCollection collection = this.getConsumption();
		DBObject query = new BasicDBObject("localizacion.codigo_postal",java.util.regex.Pattern.compile(number));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado un Codigo postal. Integer
	public DBCursor findByLocationCP(Integer number) throws UnknownHostException{
		DatabaseService ds = new DatabaseService();
		return ds.findByLocationCP(String.valueOf(number));
	}
	
	//Encuentra un objeto dada una ponblación
	public DBCursor findByLocationPoblacion(String location) throws UnknownHostException{
		location = location.toUpperCase();
		DBCollection collection = this.getConsumption();
		DBObject query = new BasicDBObject("localizacion.poblacion",java.util.regex.Pattern.compile(location));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado su CUPS (Código Universal de Punto de Suministro). Devuelve un cursor.
	public DBCursor findByLocationCups(String cups) throws UnknownHostException{
		DBCollection collection = this.getConsumption();
		DBObject query = new BasicDBObject("localizacion.cups",java.util.regex.Pattern.compile(cups));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado su CUPS (Código Universal de Punto de Suministro). Devuelve un array.
	public String[] findByLocationCups2(String cups) throws UnknownHostException{
		DBCollection collection = this.getConsumption();
		DBObject query = new BasicDBObject("localizacion.cups",java.util.regex.Pattern.compile(cups));
		DBCursor result = collection.find(query);
		String[] res = new String[result.size()];
		for(int i=0;i<result.size();i++){
			res[i] = result.next().toString();
		}
		return res;
	}
	
	// Obtiene la lista de consumos dado el ID de un objeto.
	public Collection<DBObject> getConsumosById(String id) throws UnknownHostException{
		Collection<DBObject> result;
		DBCollection collection = this.getConsumption();
		DBObject match = new BasicDBObject("$match", new BasicDBObject("_id",new ObjectId(id)));
		DBObject group = new BasicDBObject("$group", new BasicDBObject("_id","$consumos"));
		AggregationOutput agout = collection.aggregate(match, group);
		result = Lists.newArrayList(agout.results());
		return result;
	}
	
	// Devuelve todas las IDs de la Base de datos
	public Collection<DBObject> getAllId() throws UnknownHostException{
		Collection<DBObject> result;
		DBCollection collection = this.getConsumption();
		DBObject group = new BasicDBObject("$group", new BasicDBObject("_id","$_id"));
		AggregationOutput agout = collection.aggregate(group);
		result = Lists.newArrayList(agout.results());
		return result;
	}
	
	// Devuelve la media de energia consumida para un tipo de energia y una ID dada.
	public double getConsumoMediobyId(String tipoConsumo, String id) throws UnknownHostException{
		DBCollection collection = this.getConsumption();
		DBObject query = new BasicDBObject("_id", new ObjectId(id));
		DBObject projection = new BasicDBObject("_id",0);
		projection.put("datos_titular", 0);
		projection.put("localizacion", 0);
		projection.put("sustitucion_contadores", 0);
		projection.put("datos_contrato", 0);
		
		DBCursor cursor = collection.find(query,projection);
		String s = "";
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			s = obj.toString();
		}
		String[] values = ToolKit.fromStringToArray(s);
		Map<String, Double> average = ToolKit.averageValues(values);
		System.out.println(average.get(tipoConsumo));
		return average.get(tipoConsumo);
	}
	
	// Devuelve la media de un tipo de energia consumida por año
	public double getConsumoMedioByAnio(String tipoConsumo, Integer año) throws UnknownHostException{
		// check string. debe ser una fecha.
		DBCollection collection = this.getConsumption();
		DBObject query = new BasicDBObject("consumos.anio", año);
		
		DBObject projection = new BasicDBObject("_id",0);
		projection.put("datos_titular", 0);
		projection.put("localizacion", 0);
		projection.put("sustitucion_contadores", 0);
		projection.put("datos_contrato", 0);
		for(TipoEnergia e:TipoEnergia.values()){
			if(!(e.toString().equals(tipoConsumo)))
				projection.put("consumos."+e.toString(), 0);
		}
		for(PotenciaMax pm:PotenciaMax.values())
			projection.put("consumos."+pm.toString(), 0);			
		projection.put("consumos.fecha_lectura_actual", 0);
		projection.put("consumos.discriminacion_horaria", 0);
		projection.put("consumos.tip_facturacion", 0);
		projection.put("consumos.anio", 0);
		projection.put("consumos.tarifa", 0);
		projection.put("consumos.fecha_lectura_anterior", 0);
		
		DBCursor cursor = collection.find(query,projection);
		Double[] temp = {0.0,0.0};
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			List<String> ls = Arrays.asList(ToolKit.fromStringToArray(obj.toString()));
			temp[0] = temp[0] + ToolKit.sumEnergies(ls)[0];
			temp[1] = temp[1] + ToolKit.sumEnergies(ls)[1];
		}
		System.out.println(temp[0]/temp[1]);
		return temp[0]/temp[1];
	}
	
}