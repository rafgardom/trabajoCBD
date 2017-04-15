package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolKit {
	
	/*
	 * Formatea una cadena devuelta por la BD. Elimina los caracteres
	 *  {,},[,]," y espacios en blanco, y devuelve un array de string 
	 *  con el formato: "Key:Value"
	 */
	public static String[] fromStringToArray(String s){
		String[] res = s.split("\\[");
		res = res[1].split(" , ");
		for(int i=0;i<res.length;i++){
			StringBuilder sb = new StringBuilder(res[i]);
			while(res[i].contains("{")) res[i] = sb.deleteCharAt(res[i].indexOf('{')).toString();
			while(res[i].contains("}")) res[i] = sb.deleteCharAt(res[i].indexOf('}')).toString();
			while(res[i].contains("\"")) res[i] = sb.deleteCharAt(res[i].indexOf('"')).toString();	
			while(res[i].contains(" ")) res[i] = sb.deleteCharAt(res[i].indexOf(' ')).toString();
			while(res[i].contains("]")) res[i] = sb.deleteCharAt(res[i].indexOf(']')).toString();			
		}
		
		return res;
	}
	
	/*
	 * Recibe un Array de valores con el formato "key:value" y 
	 * devuelve un Map con la media de las energias recibidas. La Key 
	 * del Map es el tipo de energia y el Value es la media.
	 */
	public static Map<String,Double> averageValues(String[] values){
		Map<String,Double> res = new HashMap<String,Double>();
		List<String> noImportantValues = Arrays.asList("discriminacion_horaria","tip_facturacion","fecha_lectura_actual","tarifa","fecha_lectura_anterior","anio");
		String[] t = new String[2];
		Map<String,Integer> times = new HashMap<String,Integer>();
		for(String s:values){
			t = s.split(":");
			if(!noImportantValues.contains(t[0]))
				if(res.containsKey(t[0])){	
					times.put(t[0], times.get(t[0])+1);
					res.put(t[0], Double.valueOf(res.get(t[0]) + Double.valueOf(t[1])));
				}
				else{
					times.put(t[0], 1);
					res.put(t[0], Double.valueOf(t[1]));
				}
		}
		
		for(String s:res.keySet()){
			res.put(s, Double.valueOf(res.get(s))/(times.get(s)*1.0));
		}
		return res;
	}

}
