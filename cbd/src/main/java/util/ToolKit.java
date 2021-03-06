package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import enumerados.LaboralRateType;
import enumerados.LaboralType;

public class ToolKit {
	
	/*
	 * Formatea una cadena devuelta por la BD. Elimina los caracteres
	 *  {,},[,]," y espacios en blanco, y devuelve un array de string 
	 *  con el formato: "Key:Value"
	 */
	public static String[] fromStringToArray(String s){
		String[] res = s.split("\\[");
		res = res[1].split(",");
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
	
	public static List<String> fromStringToArrayLaboral(String s){
		String[] res = s.split(":");
		String[] subString = null;
		List<String> result = Lists.newArrayList();
		for(int i=0;i<res.length;i++){
			StringBuilder sb = new StringBuilder(res[i]);
			while(res[i].contains("{")) res[i] = sb.deleteCharAt(res[i].indexOf('{')).toString();
			while(res[i].contains("}")) res[i] = sb.deleteCharAt(res[i].indexOf('}')).toString();
			while(res[i].contains("\"")) res[i] = sb.deleteCharAt(res[i].indexOf('"')).toString();	
			while(res[i].contains(" ")) res[i] = sb.deleteCharAt(res[i].indexOf(' ')).toString();
			while(res[i].contains("]")) res[i] = sb.deleteCharAt(res[i].indexOf(']')).toString();		
			if(res[i].contains(",")){
				subString = res[i].split(",");
				result.addAll(Arrays.asList(subString));
			}else{
				result.add(res[i]);
			}
			
		}
		return result;
	}
	
	/*
	 * Recibe un Array de valores con el formato "key:value" y 
	 * devuelve un Map con la media de las energias recibidas. La Key 
	 * del Map es el tipo de energia y el Value es la media.
	 */
	public static Map<String,Double> averageValues(String[] values){
		Map<String,Double> res = new HashMap<String,Double>();
		String[] t = new String[2];
		Map<String,Integer> times = new HashMap<String,Integer>();
		for(String s:values){
			t = s.split(":");
			if(res.containsKey(t[0])){	
				times.put(t[0], times.get(t[0])+1);
				res.put(t[0], Double.valueOf(res.get(t[0]) + Double.valueOf(t[1])));
			}
			else{
				times.put(t[0], 1);
				res.put(t[0], Double.valueOf(t[1]));
			}
		}
		for(String s:res.keySet())
			res.put(s, Double.valueOf(res.get(s))/(times.get(s)*1.0));
		
		return res;
	}
	
	/*
	 * Recibe una lista de String con el formato "Key:valor"
	 * y devuelve un Array de double con dos valores: 
	 * [ suma acumulada de los valores, numero de elementos sumados].
	 */
	public static Double[] sumEnergies(List<String> ls){
		Double[] res = {0.0,0.0};
		for(String s:ls){
			res[0] = res[0] + Double.valueOf(s.split(":")[1]);
			res[1] = res[1] + 1.0;
		}
		return res;
	}

	/*
	 * Une un Array de elementos de n en n (donde n se pasa como parámetro).
	 * Devuelve un Array de Arrays de esos elementos.
	 */
	public static String[][] joinByNumberOfElement(String[] input, int n){
		String[][] res = new String[input.length/n][n];
		String[] temp = new String[n];

		for(int i=0; i<input.length+1; i++){
			if(!(i%n==0) || i==0)
				temp[i%n] = input[i];
			else{
				res[(i/n)-1] = temp.clone();
				if(i<input.length)
					temp[0] = input[i];
			}
		}
		return res;
	}

	/*
	 * Coge un Array de Arrays de valores del IPC y elimina aquellos
	 * que no pertenecen al rango de los años pasados como parametro.
	 */
	public static List<String[]> ipc_takeValidYear(String[][] input, Integer anyoDespuesDe, Integer anyoAntesDe) {
		List<String[]> res = new ArrayList<String[]>();
		for(String[] a:input){
			if( (Integer.valueOf(a[1].split(":")[1]) <= anyoDespuesDe) && (Integer.valueOf(a[1].split(":")[1]) >= anyoAntesDe) )
				res.add(a);
		}
		return res;
	}
	
	/*
	 * Transforma el string del tipo de educación seleccionada en la interfaz
	 * en su correspondiente enumerado.
	 */
	public static LaboralType getRateType(String input){
		if(input.equals("General")) return LaboralType.GENERAL;
		if(input.equals("Primaria")) return LaboralType.PRIMARIA_O_INFERIOR;
		if(input.equals("Secundaria-1 FP")) return LaboralType.SECUNDARIA_PRIMERAETAPA;
		if(input.equals("Secundaria-2 FP")) return LaboralType.SECUNDARIA_SEGUNDAETAPA;
		if(input.equals("Educ. Superior")) return LaboralType.EDUCACION_SUPERIOR;
		return null;
		
	}

	/*
	 * Transforma el string del tipo de ocupación laboral seleccionada en la interfaz
	 * en su correspondiente enumerado.
	 */
	public static LaboralRateType getType(String input) {
		if(input.equals("Paro")) return LaboralRateType.PARO;
		if(input.equals("Empleo")) return LaboralRateType.EMPLEO;
		if(input.equals("Actividad")) return LaboralRateType.ACTIVIDAD;
		return null;
	}

	/*
	 * Devuelve el número de elementos (checkBoxs) de la interfaz  han
	 * sido seleccionados.
	 */
	public static int numSelectedTypes(boolean isGeneral, boolean isPrimaria, boolean isSecundaria1,
			boolean isSecundaria2, boolean isEducSuperior) {
		int res = 0;
		if(isGeneral) res++;
		if(isPrimaria) res++;
		if(isSecundaria1) res++;
		if(isSecundaria2) res++;
		if(isEducSuperior) res++;
		return res;
	}

	/*
	 * Recibe una serie de booleanos que corresponden a los checkbox de
	 * la interfaz gráfica y devuelve el string cuya casilla ha sido marcada (true).
	 */
	public static String selectedCheckBox(boolean isGeneral, boolean isPrimaria, boolean isSecundaria1,
			boolean isSecundaria2, boolean isEducSuperior) {
		if(isGeneral) return "General";
		if(isPrimaria) return "Primaria";
		if(isSecundaria1) return "Secundaria-1 FP";
		if(isSecundaria2) return "Secundaria-2 FP";
		if(isEducSuperior) return "Educ. Superior";
		return null;
	}

	/*
	 * Recibe una serie de booleanos que corresponden a los checkbox de 
	 * la interfaz gráfica y devuelve una colección de String de las
	 * casillas que han sido marcadas (true).
	 */
	public static Collection<LaboralType> getAllSelected(boolean isGeneral, boolean isPrimaria, boolean isSecundaria1,
			boolean isSecundaria2, boolean isEducSuperior) {
		Collection<LaboralType> res = new ArrayList<LaboralType>();
		if(isGeneral) res.add(getRateType("General"));
		if(isPrimaria) res.add(getRateType("Primaria"));
		if(isSecundaria1) res.add(getRateType("Secundaria-1 FP"));		
		if(isSecundaria2) res.add(getRateType("Secundaria-2 FP"));
		if(isEducSuperior) res.add(getRateType("Educ. Superior"));
		return res;
	}

}
