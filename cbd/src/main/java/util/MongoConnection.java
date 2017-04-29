package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnection {

	//Uses to connect to DB
	@SuppressWarnings({ "deprecation" })
	public static DB connect(String address, int port) throws UnknownHostException{
		MongoClient mongo = new MongoClient(address, port);
		DB db = mongo.getDB("trabajoCBD");
		return db;
	}
	
	public static void runServer(String path){
		System.out.println("Abriendo consola de comandos...");
		
		try{
			File file = new File(path);
			Process process = Runtime.getRuntime().exec(file.getAbsolutePath());
			
			System.out.println(file.getAbsolutePath());
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String path = "C:\\Program Files\\MongoDB\\Server\\3.4\\bin\\mongod.exe";
		runServer(path);
	}
}
