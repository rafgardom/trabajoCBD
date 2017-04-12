package util;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class jsonReader {

	public static JSONArray reader(String urlFile){
		JSONArray result = null;
		JSONParser parser = new JSONParser();
		
		try{
			Object obj = parser.parse(new FileReader(urlFile));
			JSONObject jsonObj = (JSONObject) obj;
			System.out.println(jsonObj.keySet());
			
		}catch(Throwable e){
			result = null;
			System.err.println(e.toString());
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String urlFile = "c:\\data-output.json";
		JSONArray jsn = jsonReader.reader(urlFile);
	}
}
