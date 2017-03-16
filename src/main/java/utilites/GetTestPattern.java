package utilites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetTestPattern {

	public Object[][] getTestPattern(String fileName) throws IOException {

		List<String> testData = new ArrayList<String>();
		DataReader reader = new DataReader();
		testData = reader.getTestData(fileName);
		testData.remove(0);
		
		int rows = testData.size();
		int columns = testData.get(0).split(";").length;
		TwoDArray twoDArray = new TwoDArray(rows, columns);
		
		String[][] testPattern = twoDArray.createArray();
		for (int i = 0; i < rows; i++) {
			testPattern[i] = testData.get(i).split(";");
		}
		
		return (Object[][]) testPattern;
	}
}
