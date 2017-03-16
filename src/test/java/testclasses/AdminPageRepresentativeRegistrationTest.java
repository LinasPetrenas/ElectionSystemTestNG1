package testclasses;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.forms.AdminCountyPage;
import pageobjects.forms.AdminAddRepresentativePage;
import pageobjects.forms.AdminPage;
import pageobjects.forms.HomePage;
import pageobjects.forms.LoginPage;
import pageobjects.forms.RepresentativePage;
import pageobjects.main.TestMain;
import pageobjects.main.TestMain.LoginData;
import utilites.GetTestPattern;
import utilites.Var;

public class AdminPageRepresentativeRegistrationTest extends TestMain {

	HomePage homePage;
	AdminPage adminPage;
	AdminCountyPage adminCountyPage;
	
	@BeforeMethod
	public void loginAsAdministrator() throws IOException {

		homePage = new HomePage(webDriver, webSiteAddress);
		homePage.checkIsLoaded();

		LoginPage loginPage = homePage.openLoginPage();
		loginPage.checkIsLoaded();

		adminPage = (AdminPage) login(LoginAs.ADMIN, LoginData.CORRECT);
		adminPage.checkIsLoaded();
	}

	@Test(priority = 6, dataProvider = "testPattern")
	/**
	 * Check if administrator can register new committee representatives. Assert
	 * by success message and try to login with representatives' username and
	 * password.
	 */
	public void representativeRegistrationTest(String username, String password, String countyName, String districtName, String name, String surname) throws IOException, InterruptedException {
		AdminCountyPage adminCountyPage = adminPage.selectCounty(countyName);
		AdminAddRepresentativePage adminAddRepresentativePage = adminCountyPage.clickAddRepresentativeBtn(districtName);
		adminAddRepresentativePage.fillRepresentativeData(username, password, name, surname);
		adminAddRepresentativePage.assertRepresentativeAddSuccessMessageIsDisplayed();
		RepresentativePage repPage = loginAsRepresentative();
		repPage.assertDistrictIsPresented(districtName);
		repPage.assertRepresentativePageTitleIsPresented();
	}
	
	@DataProvider
	public Object[][] testPattern() throws IOException {

		GetTestPattern testPattern = new GetTestPattern();
		return testPattern.getTestPattern(Var.REPRESENTATIVE_REGISTRATION_TEST_DATA);
	}
}
