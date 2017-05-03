package util;

import java.net.UnknownHostException;
import java.util.List;

import javax.tools.Tool;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import enumerados.LaboralType;
import forms.LaboralForm;
import forms.LaboralStatisticsForm;

public class Queries_laboral {
	
	//Find one by id from laboral table
	public DBObject findLaboralById(String id) throws UnknownHostException{
		DBObject result;
		DBCollection table = DatabaseService.getCollection("laboral");
		DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
		result = table.findOne(searchById);
		return result;
	}
	
	//Devuelve la tasa de paro, tasa de actividad y tasa de empleo del cuatrimestre del año en cuestión
	private DBCursor getSummary(String year, String quarter, LaboralType type) throws UnknownHostException{
		
		String base = year + quarter+ type.getValue() + ".nombre";
		String value = year + quarter + type.getValue();
		DBCollection collection = DatabaseService.getCollection("laboral");
		DBObject query = new BasicDBObject(base, value);
		System.out.println(query.toString());
		DBObject fields = new BasicDBObject("_id",0);
		fields.put(base, 0);
//		fields.put(year + "." + year + "T1", 0);
//		fields.put(year + "." + year + "T2", 0);
//		fields.put(year + "." + year + "T3", 0);
//		fields.put(year + "." + year + quarter+".Primaria o inferior", 0);
//		fields.put(year + "." + year + quarter+".Primaria o inferior.nombre", 0);
//		fields.put(year + "." + year + quarter+".Secundaria primera etapa y formacion profesional", 0);
//		fields.put(year + "." + year + quarter+".Secundaria segunda etapa y formacion profesional", 0);
//		fields.put(year + "." + year + quarter+".Secundaria segunda etapa y formación laboral", 0);
//		fields.put(year + "." + year + quarter+".Educación superior", 0);
//		projection.put("T3_Unidad", 0);
//		projection.put("T3_Escala", 0);
//		projection.put("MetaData", 0);
//		projection.put("Data.Fecha", 0);
//		projection.put("Data.T3_TipoDato", 0);
		DBCursor result = collection.find(query, fields);
		return result;
	}
	
	public LaboralForm findSummary(String year, String quarter, LaboralType type) throws UnknownHostException{
		DBCursor cursor = this.getSummary(year, quarter, type);
		String cad = null;
		List<String> res = null;
		LaboralForm result = null;
		LaboralStatisticsForm statistics;
		
		while(cursor.hasNext()){
			cad = StringUtils.trimToEmpty(cursor.next().toString());
			res = ToolKit.fromStringToArrayLaboral(cad);
			if(res.size() == 7){
				statistics = new LaboralStatisticsForm(new Float(res.get(2)),new Float(res.get(4)),new Float(res.get(6)));
				result = new LaboralForm(res.get(0), statistics);
			}
			
		}
		return result;
		
	}
	
	public static void main(String[] args) throws UnknownHostException {
		Queries_laboral o = new Queries_laboral();
		LaboralForm pp = o.findSummary("2016", "T4", LaboralType.EDUCACION_SUPERIOR);
		System.out.println(pp.toString());
	}
}
