package testclasses;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.forms.AdminPage;
import pageobjects.forms.HomePage;
import pageobjects.forms.LoginPage;
import pageobjects.main.TestMain;

public class AdminPageLoginTest extends TestMain {

	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void openHomePage() {
		homePage = new HomePage(webDriver, webSiteAddress);
		loginPage = homePage.openLoginPage();
		loginPage.checkIsLoaded();
	}

	@Test(priority = 1)
	/**
	 * Check if administrator can login with correct user name and password.
	 * Assert by administrator page title.
	 */
	public void loginAdminWithCorrectDataTest() throws IOException {

		
	  AdminPage adminPage = (AdminPage) login(LoginAs.ADMIN, LoginData.CORRECT);
	  adminPage.assertTitleIsDisplayed(); }
	 
	
}
