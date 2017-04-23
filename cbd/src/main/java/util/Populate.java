package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Populate {

	public static void populateConsumption() throws IOException{
	
		//Erase collection if any
		DatabaseService dbs = new DatabaseService();
		dbs.dropCollection("consumption");
		
		//Populate consumption collection
		String importCommand ="mongoimport.exe --db trabajoCBD --collection consumption "
				+ "c:\\data\\data-output.json --jsonArray";
		String commandPath ="cd C:\\Program Files\\MongoDB\\Server\\3.4\\bin";
		ProcessBuilder builder = new ProcessBuilder(
		      "cmd.exe", "/c", commandPath +"&&" + importCommand);
		builder.redirectErrorStream(true);
		
	    Process p = builder.start();
	    
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { 
            	break; 
            }
            
            System.out.println(line);
        }
	}
	
	public static void populateLaboral() throws IOException{
		
		//Erase collection if any
		DatabaseService dbs = new DatabaseService();
		dbs.dropCollection("laboral");
		
		//Populate laboral collection
		String importCommand ="mongoimport.exe --db trabajoCBD --collection laboral "
				+ "c:\\data\\actividad-laboral-refact.json --jsonArray";
		String commandPath ="cd C:\\Program Files\\MongoDB\\Server\\3.4\\bin";
		ProcessBuilder builder = new ProcessBuilder(
		      "cmd.exe", "/c", commandPath +"&&" + importCommand);
		builder.redirectErrorStream(true);
		
	    Process p = builder.start();
	    
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { 
            	break; 
            }
            
            System.out.println(line);
        }
	}
	
	public static void populateIPC() throws IOException{
		
		//Erase collection if any
		DatabaseService dbs = new DatabaseService();
		dbs.dropCollection("ipc");
		
		//Populate IPC collection
		String importCommand ="mongoimport.exe --db trabajoCBD --collection ipc "
				+ "c:\\data\\ipc_json.json --jsonArray";
		String commandPath ="cd C:\\Program Files\\MongoDB\\Server\\3.4\\bin";
		ProcessBuilder builder = new ProcessBuilder(
		      "cmd.exe", "/c", commandPath +"&&" + importCommand);
		builder.redirectErrorStream(true);
		
	    Process p = builder.start();
	    
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { 
            	break; 
            }
            
            System.out.println(line);
        }
	}
	
	public static void main(String[] args) throws IOException {
		 Populate.populateConsumption();
		 Populate.populateLaboral();
		 Populate.populateIPC();
	}
}
