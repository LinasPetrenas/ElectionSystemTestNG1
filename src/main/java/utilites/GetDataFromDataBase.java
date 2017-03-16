package utilites;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class GetDataFromDataBase {

	private static final String URL_FILE_NAME = "/Desktop/url.txt";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	public Connection getConnectionToDB() {

		String urlFile = System.getProperty("user.home") + URL_FILE_NAME;
		String dbUrl = "";
		try {
			DataReader dataReader = new DataReader();
			List<String> items = dataReader.getTestData(urlFile);
			dbUrl = items.get(1);
		} catch (Exception e) {
			System.out.println("Error reading url data from file " + URL_FILE_NAME);
		}

		try (Connection conn = DriverManager.getConnection(dbUrl, DB_USERNAME, DB_PASSWORD)) {
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
