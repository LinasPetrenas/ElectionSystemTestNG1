package utilites;

import java.io.IOException;
import java.sql.Connection;

public class TestApp {

	public static void main(String[] args) {

		// GetTestPattern gp = new GetTestPattern();
		// gp.getTestPattern("src/test/resources/districtRegistrationTestData.txt");

		GetDataFromDataBase dataBase = new GetDataFromDataBase();
		try {
			Connection conn = dataBase.getConnectionToDB();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
