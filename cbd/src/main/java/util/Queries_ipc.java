package util;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import forms.IpcForm;

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
	public List<IpcForm> getAll_TotalNacionalVariacionMensual(Integer anyoAntesDe, Integer anyoDespuesDe) throws UnknownHostException{
		List<IpcForm> result = Lists.newArrayList();
		List<String[]> data = null;
		DBCursor cursor = this.getAll_TotalNacionalVariacionMensual();
		while(cursor.hasNext()){
			String[] array = ToolKit.fromStringToArray(cursor.next().toString());
			String[][] res = ToolKit.joinByNumberOfElement(array,3);
			data =  ToolKit.ipc_takeValidYear(res,anyoDespuesDe,anyoAntesDe);
		}
		
		for(String[] s: data){
			IpcForm ipcForm = new IpcForm();
			String[] month = s[0].split(":");
			String[] year = s[1].split(":");
			String[] value = s[2].split(":");
			
			ipcForm.setMonth(month[1]);
			ipcForm.setYear(year[1]);
			ipcForm.setValue(new Float(value[1]));
			
			result.add(ipcForm);
		}
		
		result = Lists.reverse(result);
		
		return result;
	}
	
	
	
	//Devuelve el objeto completo del indice por mes del IPC a nivel nacional (todos los años)
	public DBCursor getAll_TotalNacionalIndice() throws UnknownHostException{
		DBCollection collection = DatabaseService.getCollection("ipc");
		DBObject query = new BasicDBObject("Nombre","General, Total Nacional, Total, Base 2011, Índice");
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
	 * Devuelve una lista de arrays con el formato [Mes, año, valor] del índice del IPC 
	 * a nivel nacional entre los años pasados como parametros
	 */
	public List<IpcForm> getAll_TotalNacionalIndice(Integer anyoDespuesDe, Integer anyoAntesDe) throws UnknownHostException{
		DBCursor cursor = this.getAll_TotalNacionalIndice();
		List<IpcForm> result = Lists.newArrayList();
		List<String[]> data = Lists.newArrayList();
		while(cursor.hasNext()){
			String[] array = ToolKit.fromStringToArray(cursor.next().toString());
			String[][] res = ToolKit.joinByNumberOfElement(array,3);
			data = ToolKit.ipc_takeValidYear(res,anyoDespuesDe,anyoAntesDe);
		}
		
		for(String[] s: data){
			IpcForm ipcForm = new IpcForm();
			String[] month = s[0].split(":");
			String[] year = s[1].split(":");
			String[] value = s[2].split(":");
			
			ipcForm.setMonth(month[1]);
			ipcForm.setYear(year[1]);
			ipcForm.setValue(new Float(value[1]));
			
			result.add(ipcForm);
		}
		
		result = Lists.reverse(result);
		
		return result;
	}

	public static void main(String[] args) throws UnknownHostException {
		Queries_ipc ipc = new Queries_ipc();
		Collection<IpcForm> result = ipc.getAll_TotalNacionalIndice(2016, 2016);
		for(IpcForm ipc2: result){
			System.out.println(ipc2.toString());
		}
	}
}
