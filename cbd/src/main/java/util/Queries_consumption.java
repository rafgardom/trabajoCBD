package util;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.google.common.collect.Lists;
import com.mongodb.AggregationOptions;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;


import enumerados.PotenciaMax;
import enumerados.TipoEnergia;
import forms.ConsumptionForm;

public class Queries_consumption {
	
	//Find one by id from consumption table
	public DBObject findConsumptionById(String id) throws UnknownHostException{
		DBObject result;
		DBCollection table = DatabaseService.getCollection("consumption");
		DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
		result = table.findOne(searchById);
		
		return result;
	}
	
	// Encuentra un objeto dado una provincia
	public DBCursor findByLocationProvincia(String location) throws UnknownHostException{
		location = location.toUpperCase();
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("localizacion.provincia",java.util.regex.Pattern.compile(location));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado un Codigo Postal. String
	public DBCursor findByLocationCP(String number) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("localizacion.codigo_postal",java.util.regex.Pattern.compile(number));
		DBCursor result = collection.find(query);
		return result;
	}
		
	//Encuentra un objeto dado un Codigo postal. Integer
	public DBCursor findByLocationCP(Integer number) throws UnknownHostException{
		Queries_consumption ds = new Queries_consumption();
		return ds.findByLocationCP(String.valueOf(number));
	}
	

	//Encuentra un objeto dada una ponblación
	public DBCursor findByLocationPoblacion(String location) throws UnknownHostException{
		location = location.toUpperCase();
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("localizacion.poblacion",java.util.regex.Pattern.compile(location));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado su CUPS (Código Universal de Punto de Suministro). Devuelve un cursor.
	public DBCursor findByLocationCups(String cups) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("localizacion.cups",java.util.regex.Pattern.compile(cups));
		DBCursor result = collection.find(query);
		return result;
	}
	
	//Encuentra un objeto dado su CUPS (Código Universal de Punto de Suministro). Devuelve un array.
	public String[] findByLocationCups2(String cups) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("localizacion.cups",java.util.regex.Pattern.compile(cups));
		DBCursor result = collection.find(query);
		String[] res = new String[result.size()];
		for(int i=0;i<result.size();i++){
			res[i] = result.next().toString();
		}
		return res;
	}
	
	// Obtiene la lista de consumos dado el ID de un objeto.
	@SuppressWarnings("deprecation")
	public Collection<DBObject> getConsumosById(String id) throws UnknownHostException{
		Collection<DBObject> result;
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject match = new BasicDBObject("$match", new BasicDBObject("_id",new ObjectId(id)));
		DBObject group = new BasicDBObject("$group", new BasicDBObject("_id","$consumos"));
		AggregationOutput agout = collection.aggregate(match, group);
		result = Lists.newArrayList(agout.results());
		return result;
	}
	
	// Devuelve todas las IDs de la Base de datos
	@SuppressWarnings("deprecation")
	public Collection<DBObject> getAllId() throws UnknownHostException{
		Collection<DBObject> result;
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject group = new BasicDBObject("$group", new BasicDBObject("_id","$_id"));
		AggregationOutput agout = collection.aggregate(group);
		result = Lists.newArrayList(agout.results());
		return result;
	}
	
	// Devuelve la media de energia consumida para un tipo de energia y una ID dada.
	public double getConsumoMediobyId(String tipoConsumo, String id) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("_id", new ObjectId(id));
		DBObject projection = new BasicDBObject("_id",0);
		projection.put("datos_titular", 0);
		projection.put("localizacion", 0);
		projection.put("sustitucion_contadores", 0);
		projection.put("datos_contrato", 0);
		projection.put("consumos.discriminacion_horaria", 0);
		projection.put("consumos.fecha_lectura_actual", 0);
		projection.put("consumos.tip_facturacion", 0);
		projection.put("consumos.anio", 0);
		projection.put("consumos.tarifa", 0);
		projection.put("consumos.fecha_lectura_anterior", 0);
		for(PotenciaMax p:PotenciaMax.values())
			projection.put("consumos."+p.toString(), 0);
		for(TipoEnergia s:TipoEnergia.values()){
			if(!(s.toString().equals(tipoConsumo)))
				projection.put("consumos."+s.toString(), 0);
		}
		DBCursor cursor = collection.find(query,projection);
		String s = "";
		while(cursor.hasNext()){
			s += cursor.next().toString();
		}
		String[] values = ToolKit.fromStringToArray(s);
		Map<String, Double> average = ToolKit.averageValues(values);
		return average.get(tipoConsumo);
	}
	
	// Devuelve la media de un tipo de energia consumida por año
	public double getConsumoMedioByAnio(String tipoConsumo, Integer anio) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("consumos.anio", anio);
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
		return temp[0]/temp[1];
	}
	
	//Devuelve todos los objetos con impago
	public DBCursor getAllImpagos() throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject("datos_contrato.impago", new BasicDBObject("$ne","00000000,00"));
		DBCursor res = collection.find(query);
		return res;
	}
	
	//Devuelve todos los objetos con impago para un año determinado
	public DBCursor getAllImpagosByAnio(String anio) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		DBObject query = new BasicDBObject();
		query.put("datos_contrato.impago", new BasicDBObject("$ne","00000000,00"));
		query.put("datos_contrato.fecha_ultima_lectura", new BasicDBObject("$regex",anio));
		DBCursor res = collection.find(query);
		return res;
	}
	
	//Devuelve la energia activa_p3 y potencia máxima_p3 media por trimestre de un año dado
	public DBObject getActiveEnergyMaxPowerCursor(String date) throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("consumption");
		
		DBObject filterExpression = new BasicDBObject();
		filterExpression.put("input", "$consumos");
        filterExpression.put("as", "consumo");
        filterExpression.put("cond", new BasicDBObject("$eq", Arrays.<Object> asList("$$consumo.fecha_lectura_actual", date)));
        
		DBObject project = new BasicDBObject("$project",
				new BasicDBObject("items", 
						new BasicDBObject("$filter", filterExpression)));
		
		
		//Allow to return all objects
		AggregationOptions aggregationOptions = AggregationOptions.builder()
                .batchSize(100)
                .outputMode(AggregationOptions.OutputMode.CURSOR)
                .allowDiskUse(true)
                .build();
		
		List<DBObject> pipeline = Arrays.asList(project);
		
		Cursor output =  collection.aggregate(pipeline, aggregationOptions);
		
		return output.next();
	}
	
	public ConsumptionForm getActiveEnergyMaxPower(String date, int cont) throws UnknownHostException{
		String[] dateArray = date.split("-");
		String year = dateArray[0];
		String month = dateArray[1];
		Integer day = new Integer(dateArray[2]);
		ConsumptionForm result = null;
		
		DBObject data = this.getActiveEnergyMaxPowerCursor(date);
		List<String> ls = Arrays.asList(ToolKit.fromStringToArray(data.toString()));
		if(ls.size()<20){
			day++;
			if(day >31){
				day = 1;
			}
			String newDate = null;
			if(day <10){
				newDate = year+"-"+month+"-"+"0"+day;
			}else{
				newDate = year+"-"+month+"-"+day;
			}
			
			if(cont > 31){
				result = null;
			}else{
				cont++;
				result = this.getActiveEnergyMaxPower(newDate, cont);
			}
			
			
		}else{
			try{
				String[] activaArray = ls.get(6).split(":");
				String[] lecturaArray = ls.get(9).split(":");
				String[] anioArray = ls.get(11).split(":");
				String[] reactivaArray = ls.get(19).split(":");
				
				result = new ConsumptionForm(new Integer(activaArray[1]), lecturaArray[1], anioArray[1], 
						new Integer(reactivaArray[1]));
				
			}catch(Throwable oops){
				result = null;
				System.err.println("Error en la reconstruccion de consumptionForm");
			}
		}
		
		return result;
	}
	
	public List<ConsumptionForm> getActiveEnergyMaxPowerByYear(String year) throws UnknownHostException{
		List<ConsumptionForm> result = Lists.newArrayList();
		int month = 1;
		while(month <= 12){
			String date = year + "-0"+month +"-01";
			ConsumptionForm cf = this.getActiveEnergyMaxPower(date,0);
			
			result.add(cf);
			month++;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws UnknownHostException {
		Queries_consumption qc = new Queries_consumption();
		
//		ConsumptionForm p = qc.getActiveEnergyMaxPower("2016-05-24",0);
//		System.out.println(p);
		
		List<ConsumptionForm> consumptionForms = qc.getActiveEnergyMaxPowerByYear("2015");
		System.out.println(consumptionForms);
		
	}

}
