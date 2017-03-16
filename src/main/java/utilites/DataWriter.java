package utilites;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {

	public boolean dataWriter(String fileName, List<String> items) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			for (String item : items) {
				fw.write(item);
			}
		} catch (IOException ex) {
			fw.close();
			System.out.println("Error " + ex);
			return false;
		}
		fw.close();
		return true;
	}
}