package utilites;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
	 /**
    * Read regular file
    */
   public List<String> getTestData(String fileName) throws IOException { 	
   	
   	//--- local variables ---
   	List<String> records = new ArrayList<String>();
   	String record;
   	
   	//--- open file for reading ---
   	BufferedReader file = new BufferedReader(new FileReader(fileName));
   	
   	//--- read each line and add it to the list ---
   	while((record = file.readLine()) != null) {
   		records.add(record);
   	}
   	
   	//--- close file ---
   	file.close();
   	
   	return records;
   }
}
