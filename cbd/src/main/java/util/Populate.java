package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Populate {

	public static void populateConsumption() throws IOException{
		String importCommand ="mongoimport.exe --db trabajoCBD --collection consumption "
				+ "c:\\data-output.json --jsonArray";
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
	}
}
