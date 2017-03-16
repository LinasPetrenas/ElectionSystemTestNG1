package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.forms.AdminCountyPage;
import pageobjects.forms.AdminPage;
import pageobjects.forms.HomePage;
import pageobjects.forms.LoginPage;
import pageobjects.forms.UserCountyListPage;
import pageobjects.forms.UserDistrictListPage;
import pageobjects.main.TestMain;
import pageobjects.main.TestMain.LoginAs;
import pageobjects.main.TestMain.LoginData;
import utilites.DataReader;
import utilites.GetTestPattern;
import utilites.Var;

public class AdminPageDistrictOperationsTest extends TestMain {

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

	@Test(priority = 5, dataProvider = "testPattern")
	public void districtRegistrationTest(String countyName, String districtName, String districtAddress,
			String districtElectors) throws IOException, InterruptedException {

		AdminCountyPage adminCountyPage = adminPage.selectCounty(countyName);
		adminCountyPage.fillDistrictData(districtName, districtAddress, districtElectors);
		adminCountyPage.assertSuccessMessageIsDisplayed();
		homePage.goHome();
		UserCountyListPage userCountyListPage = homePage.openUserCountyListPage();
		UserDistrictListPage userDistrictListPage = userCountyListPage.selectCounty(countyName);
		userDistrictListPage.assertDistrictData(districtName, districtAddress, districtElectors);
		homePage.goHome();
	}

	@Test(priority = 15, dataProvider = "testPattern")
	/**
	 * Check if administrator can delete a district. Assert by success message.
	 */
	public void districtDeletionTest(String countyName, String districtName) {
		adminPage.selectCounty(countyName);
		AdminCountyPage adminCountyPage = new AdminCountyPage(webDriver, districtName);
		adminCountyPage.deleteDistrict(districtName);
		adminCountyPage.assertDistrictIsDeleted();
}

	/**
	 * Provide data from file.
	 * 
	 * @return test pattern
	 * @throws IOException
	 */
	@DataProvider
	public Object[][] testPattern() throws IOException {

		GetTestPattern testPattern = new GetTestPattern();
		return testPattern.getTestPattern(Var.DISTRICT_REGISTRATION_TEST_DATA);
	}
}
