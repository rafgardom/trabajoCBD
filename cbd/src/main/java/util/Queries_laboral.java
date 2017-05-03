package util;

import java.net.UnknownHostException;
import java.util.List;

import javax.tools.Tool;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Lf2SpacesIndenter;
import com.google.common.collect.Lists;
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
		DBCursor result = collection.find(query, fields);
		return result;
	}
	
	//Devuelve la tasa de paro, tasa de actividad y tasa de empleo del cuatrimestre del año en cuestión
	private List<DBCursor> getYearFilterSummary(String year, LaboralType type) throws UnknownHostException{
		List<String> quarters = Lists.newArrayList();
		List<DBCursor> result = Lists.newArrayList();
		quarters.add("T1");
		quarters.add("T2");
		quarters.add("T3");
		quarters.add("T4");
		
		for(String quarter: quarters){
			result.add(this.getSummary(year, quarter, type));
		}
		
		return result;
	}
		
	//Devuelve la tasa de paro, tasa de actividad y tasa de empleo del cuatrimestre del año en cuestión
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
	
	//Devuelve la tasa de paro, tasa de actividad y tasa de empleo del cuatrimestre del año en cuestión
	public List<LaboralForm> findYearFilterSummary(String year, LaboralType type) throws UnknownHostException{
		List<DBCursor> cursors = this.getYearFilterSummary(year, type);
		String cad = null;
		List<String> res = null;
		LaboralForm laboralForm = null;
		List<LaboralForm> result = Lists.newArrayList();
		LaboralStatisticsForm statistics;
		for(DBCursor cursor: cursors){
			while(cursor.hasNext()){
				cad = StringUtils.trimToEmpty(cursor.next().toString());
				res = ToolKit.fromStringToArrayLaboral(cad);
				if(res.size() == 7){
					statistics = new LaboralStatisticsForm(new Float(res.get(2)),new Float(res.get(4)),new Float(res.get(6)));
					laboralForm = new LaboralForm(res.get(0), statistics);
				}
				if(laboralForm != null){
					result.add(laboralForm);
				}
			}
		}
		
		return result;
		
	}
	
	public static void main(String[] args) throws UnknownHostException {
		Queries_laboral o = new Queries_laboral();
//		LaboralForm pp = o.findSummary("2010", "T4", LaboralType.EDUCACION_SUPERIOR);
//		System.out.println(pp.toString());
		
		List<LaboralForm> pp2 = o.findYearFilterSummary("2016", LaboralType.GENERAL);
		for(LaboralForm as:pp2){
			System.out.println(as.toString());
		}
	}
}
