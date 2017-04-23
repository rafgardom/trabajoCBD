package util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiServices {
	
	private static String ApiKey = "key=049dffb0180a46399cc144853171204";
	private static String UrlBase = "http://api.apixu.com/v1";
	
	//Tiempo actual 
	public static String tiempoActual(String zona){
		String url = UrlBase+"/current.json"+"?"+ApiKey+"&q="+zona+"&lang=es";
		return callURL(url);
	}
	
	// Previsión para el día actual completo (por horas)
	public static String prevision(String zona){
		String url = UrlBase+"/forecast.json"+"?"+ApiKey+"&q="+zona+"&lang=es";
		return callURL(url);
	}
	
	// Prevision para el dia actual y los N siguientes (N<10)
	public static String prevision(String zona, int dias){
		if(dias>=1 && dias<=10){
			String url = UrlBase+"/forecast.json"+"?"+ApiKey+"&q="+zona+"&days="+String.valueOf(dias)+"&lang=es";
			return callURL(url);
		}else
			return "ERROR. Parametros incorrectos, el número de dias debe estar comprendido entre 1 y 10";
	}
	
	// Prevision para N días pasados, N debe estar comprendido entre 1 y 4
	public static String tiempoNDiasPasados(String zona, int dias){
		if(dias>=1 && dias<=4){
			Calendar d = Calendar.getInstance();
			Calendar res = Calendar.getInstance();
			int dia = d.get(Calendar.DAY_OF_MONTH) - dias;
			
			if(dia<=0){
				res.set(Calendar.DAY_OF_MONTH, res.getMaximum(Calendar.DAY_OF_MONTH)+(dia+1));
				if(d.get(Calendar.MONTH) != 0)
					res.set(Calendar.MONTH, d.get(Calendar.MONTH)-1);
				else{
					res.set(Calendar.YEAR, d.get(Calendar.YEAR)-1);
					res.set(Calendar.MONTH, 11);
				}
			}else
				res.set(Calendar.DAY_OF_MONTH, dia);
			
			String url = null;
			String month = String.valueOf(res.get(Calendar.MONTH)+1);
			String dayOfMonth = String.valueOf(res.get(Calendar.DAY_OF_MONTH));
			
			if(res.get(Calendar.MONTH)<10)
				month = "0"+String.valueOf(res.get(Calendar.MONTH)+1);
			if(res.get(Calendar.DAY_OF_MONTH)<10)
				dayOfMonth = "0"+String.valueOf(res.get(Calendar.DAY_OF_MONTH));
			
			url = UrlBase+"/forecast.json"+"?"+ApiKey+"&q="+zona+"&dt="+String.valueOf(res.get(Calendar.YEAR))+"-"+month+"-"+dayOfMonth+"&lang=es";
			return callURL(url);
		}else
			return "ERROR. Parametros incorrectos, el número de dias debe estar comprendido entre 1 y 4";
	}
	
	// Datos de una fecha determinada de entre 0 y 30 días pasados. Formato: YYYY-MM-DD
	public static String fechaPasadaExacta(String zona, String fecha){
		if(!(Pattern.matches("\\d{4}-\\d{2}-\\d{2}", fecha)))
			return "Error. Parametros incorrectos, debe introducir una fecha valida. YYYY-MM-DD";
		if(restarFechas(fecha) >= 30 || restarFechas(fecha) < 0)
			return "Error. Parametros incorrectos, la fecha no debe ser posterior o igual a 30 dias";
		String url = UrlBase+"/history.json"+"?"+ApiKey+"&q="+zona+"&dt="+fecha+"&lang=es";
		return callURL(url);
			
	}
	
	// Escribe en un formato JSON el String que se le pase como parametro.
	public static void guardarEnJson(String path, String nombreFichero, String info){
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(info);
			FileWriter file = new FileWriter(path+nombreFichero);
			try {
				file.write(json.toJSONString());
			} catch (IOException e) {
				System.out.println("Ups! some wrong happend");
//				e.printStackTrace();
			}finally{
				file.flush();
				file.close();
			}
		} catch (org.json.simple.parser.ParseException e) {
			System.out.println("Ups! some wrong happend");
//			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Ups! some wrong happend");
			e1.printStackTrace();
		}

			
	}
	
	// Realiza la petición a la API
	private static String callURL(String myUrl){
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try{
			URL url = new URL(myUrl);
			urlConn = url.openConnection();
			if(urlConn != null)
				urlConn.setReadTimeout(60*1000);
			if(urlConn != null && urlConn.getInputStream() != null){
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if(bufferedReader != null){
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
				}
				bufferedReader.close();
			}
		}
		in.close();
		}catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myUrl, e);
		}
		return sb.toString();
	}
	
	//Resta dos fechas y devuelve el numero de días que las separan
	private static Integer restarFechas(String sfecha){
		Calendar fecha = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			fecha.setTime(sdf.parse(sfecha));
		} catch (ParseException e) {
			System.out.println("Ups! some wrong happend");
//			e.printStackTrace();
		}
		Calendar fechaActual = Calendar.getInstance();
		Integer res = 0;
		int temp1,temp2;
		temp1 = fechaActual.get(Calendar.YEAR);
		temp2 = fecha.get(Calendar.YEAR);
		if(temp1-temp2 == 0){
			res = fechaActual.get(Calendar.DAY_OF_YEAR) - fecha.get(Calendar.DAY_OF_YEAR);}
		else{
			if(Math.abs(fechaActual.get(Calendar.YEAR)-fecha.get(Calendar.YEAR))>1)
				return (int) Double.POSITIVE_INFINITY;
			else{
				if(fechaActual.get(Calendar.MONTH) != 11 || fecha.get(Calendar.MONTH) != 0)
					return (int) Double.POSITIVE_INFINITY;
				else{
					res = (fechaActual.getMaximum(Calendar.DAY_OF_MONTH)-fechaActual.get(Calendar.DAY_OF_MONTH))+fecha.get(Calendar.DAY_OF_MONTH); 
				}
			}
		}
		return res;
	}
	
}
