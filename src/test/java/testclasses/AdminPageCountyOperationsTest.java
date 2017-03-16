package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.forms.AdminPage;
import pageobjects.forms.HomePage;
import pageobjects.forms.LoginPage;
import pageobjects.forms.UserCountyListPage;
import pageobjects.main.TestMain;
import utilites.Var;
import utilites.DataReader;
import utilites.GetTestPattern;

/**
 * Administrator page. Check if administrator can register new county, add
 * candidate list from csv file, delete candidates from selected county and
 * delete county. Data provided by data provider from file
 * countyRegistrationTestData.txt.
 * @see AutoTestScenarijai.xlsx #
 */
public class AdminPageCountyOperationsTest extends TestMain {

	HomePage homePage;
	AdminPage adminPage;

	@BeforeMethod
	public void loginAsAdministrator() throws IOException {

		homePage = new HomePage(webDriver, webSiteAddress);
		homePage.checkIsLoaded();

		LoginPage loginPage = homePage.openLoginPage();
		loginPage.checkIsLoaded();

		adminPage = (AdminPage) login(LoginAs.ADMIN, LoginData.CORRECT);
		adminPage.checkIsLoaded();
	}

	@Test(priority = 3, dataProvider = "testPattern")
	/**
	 * Check if administrator can register new county. Assert by success message
	 * and new county is presented.
	 */
	public void countyRegistrationTest(String countyName) throws IOException, InterruptedException {

		adminPage.addNewCounty(countyName);
		adminPage.assertSuccessMessageIsDisplayed();

		UserCountyListPage userCountyListPage = homePage.openUserCountyListPage();
		userCountyListPage.assertCountyIsPresented(countyName);
	}

	@Test(priority = 4, dataProvider = "testPattern")
	/**
	 * Check if administrator wants to add a county which already exists in the
	 * system. Assert by error message.
	 */
	public void countyAddWhichAlreadyExistsTest(String countyName) throws InterruptedException {

		adminPage.addNewCounty(countyName);
		adminPage.assertErrorMessageIsDisplayed();
	}

	@Test(priority = 9, dataProvider = "testPattern")
	/**
	 * Check if administrator can add county candidate list. Assert by success
	 * message and user can find candidate in search page.
	 */

	public void countyCandidateListAddTest(String countyName) {
		adminPage.addCandidateList(countyName);
		adminPage.assertCountyListAddSuccessMessageIsDisplayed();
	}

	@Test(priority = 15, dataProvider = "testPattern")
	/**
	 * Check if administrator can delete single-member county candidate list.
	 * Assert by success message.
	 */
	public void countyDeleteCandidateListTest(String countyName) {

		adminPage.deleteCountyCandidateList(countyName);
		adminPage.assertCountyListDeleteSuccessMessageIsDisplayed();
	}

	@Test(priority = 16, dataProvider = "testPattern")
	/**
	 * Check if administrator can delete a county. Assert by success message and
	 * deleted county is not presented.
	 */
	public void countyDeleteTest(String countyName) throws InterruptedException {

		adminPage.deleteCounty(countyName);
		adminPage.assertCountyDeleteSuccessMessageIsDisplayed();
	}

	@DataProvider
	public Object[][] testPattern() throws IOException {

		GetTestPattern testPattern = new GetTestPattern();
		return testPattern.getTestPattern(Var.COUNTY_REGISTRATION_TEST_DATA);
	}
}
