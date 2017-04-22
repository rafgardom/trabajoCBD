package util;

import java.net.UnknownHostException;
import java.util.List;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Queries_ipc {
	
	//Devuelve el objeto completo de las variaciones por mes del IPC a nivel nacional (todos los años)
	public DBCursor getAll_TotalNacionalVariacionMensual() throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("ipc");
		DBObject query = new BasicDBObject("Nombre","Total Nacional, General, Total, Variación mensual");
		DBObject projection = new BasicDBObject("_id",0);
		projection.put("COD", 0);
		projection.put("Nombre", 0);
		projection.put("T3_Unidad", 0);
		projection.put("T3_Escala", 0);
		projection.put("MetaData", 0);
		projection.put("Data.Fecha", 0);
		projection.put("Data.T3_TipoDato", 0);
		DBCursor result = collection.find(query,projection);
		return result;
	}
	
	/*
	 * Devuelve una lista de arrays con el formato [Mes, año, valor] de las variaciones mensuales del IPC 
	 * a nivel nacional entre los años pasados como parametros
	 */
	public List<String[]> getAll_TotalNacionalVariacionMensual(Integer anyoDespuesDe, Integer anyoAntesDe) throws UnknownHostException{
		DBCursor cursor = this.getAll_TotalNacionalVariacionMensual();
		String output = null;
		while(cursor.hasNext()){
			output = cursor.next().toString();
			String[] result = ToolKit.fromStringToArray(output);
			String[][] res = ToolKit.joinByNumberOfElement(result,3);
			return ToolKit.ipc_takeValidYear(res,anyoDespuesDe,anyoAntesDe);
		}
		return null;
	}

}
