package pageobjects.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import pageobjects.forms.AdminPage;
import pageobjects.forms.HomePage;
import pageobjects.forms.LoginPage;
import pageobjects.forms.RepresentativePage;
import pageobjects.main.TestMain.LoginAs;
import pageobjects.main.TestMain.LoginData;
import utilites.Var;
import utilites.DataReader;
import utilites.GetTestPattern;

public abstract class TestMain {
	public WebDriver webDriver;
	public String webSiteAddress;
	public int step = 0;
	HomePage homePage;
	RepresentativePage repPage;

	public enum LoginData {
		CORRECT, INVALID
	};

	public enum LoginAs {
		ADMIN, REPRESENTATIVE
	};

	/**
	 * Initialize webDriver
	 * 
	 * @throws Exception
	 */

	@BeforeClass
	@Parameters("webSiteAddress") // use web site address as parameter from
									// suite testng.xml
	public void initialize(String webSiteAddress) throws Exception {
		this.webSiteAddress = webSiteAddress;
		webDriver = initWebDriver(webDriver, webSiteAddress);
	}

	/**
	 * Closes webDriver
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public void shutDown() throws Exception {
		webDriver.close();
		webDriver.quit();
	}

	/**
	 * Initialize Firefox browser
	 * 
	 * @param webDriver
	 * @return
	 */
	public static WebDriver initWebDriver(WebDriver webDriver, String baseUrl) {

		webDriver = new FirefoxDriver();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(baseUrl);

		return webDriver;
	}

	public EmptyPage login(LoginAs user, LoginData loginData) throws IOException {

		String testPatternFileName;
		if (loginData == LoginData.CORRECT) {
			if (user == LoginAs.ADMIN) {
				testPatternFileName = Var.LOGIN_ADMIN_TEST_DATA;
			} else {
				testPatternFileName = Var.LOGIN_REPRESENTATIVE_TEST_DATA;
			}
		} else {
			testPatternFileName = Var.LOGIN_INVALID_TEST_DATA;
		}
		GetTestPattern testPattern = new GetTestPattern();
		testPattern.getTestPattern(testPatternFileName);
		String[][] login = (String[][]) testPattern.getTestPattern(testPatternFileName);
		LoginPage loginPage = new LoginPage(webDriver, webSiteAddress);
		String username = login[0][0];
		String password = login[0][1];

		System.out.println("Login with user name <" + username + ">");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		if (user == LoginAs.ADMIN) {
			return new AdminPage(webDriver, webSiteAddress);
		} else {
			return new RepresentativePage(webDriver, webSiteAddress);
		}
	}

	public EmptyPage login1(LoginAs user, LoginData loginData, int step) throws IOException {

		String testPatternFileName;
		if (loginData == LoginData.CORRECT) {
			if (user == LoginAs.ADMIN) {
				testPatternFileName = Var.LOGIN_ADMIN_TEST_DATA;
				// step = 0;
			} else {
				testPatternFileName = Var.LOGIN_REPRESENTATIVE_TEST_DATA;
			}
		} else {
			testPatternFileName = Var.LOGIN_INVALID_TEST_DATA;
			// step = 0;
		}
		GetTestPattern testPattern = new GetTestPattern();
		testPattern.getTestPattern(testPatternFileName);
		String[][] login = (String[][]) testPattern.getTestPattern(testPatternFileName);
		LoginPage loginPage = new LoginPage(webDriver, webSiteAddress);
		String username = login[step][0];
		String password = login[step][1];

		System.out.println("Login with user name <" + username + ">, password <" + password + ">");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		if (user == LoginAs.ADMIN) {
			return new AdminPage(webDriver, webSiteAddress);
		} else {
			step++;
			return new RepresentativePage(webDriver, webSiteAddress);
		}
	}

	public RepresentativePage loginAsRepresentative() throws IOException {

		homePage = new HomePage(webDriver, webSiteAddress);
		homePage.checkIsLoaded();

		LoginPage loginPage = homePage.openLoginPage();
		loginPage.checkIsLoaded();

		repPage = (RepresentativePage) login1(LoginAs.REPRESENTATIVE, LoginData.CORRECT, step);
		step++;
		repPage.checkIsLoaded();

		return repPage;
	}
}
