package testclasses;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.forms.AdminPage;
import pageobjects.forms.HomePage;
import pageobjects.forms.LoginPage;
import pageobjects.forms.SearchPage;
import pageobjects.main.TestMain;
import pageobjects.main.TestMain.LoginData;
import utilites.DataReader;
import utilites.GetTestPattern;
import utilites.Var;

/**
 * Administrator page. Check if administrator can register new party, add their
 * candidate list from csv file and delete party together with their candidate list. Data
 * provided by data provider from file partyRegistrationTestData.txt.
 * @see AutoTestScenarijai.xlsx #4, #5.
 */
public class AdminPagePartyOperationsTest extends TestMain {
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

	@Test(priority = 7, dataProvider = "testPattern")
	/**
	 * Check if administrator can register new party. Assert by success message.
	 */
	public void partyRegistrationTest(String partyName, String partyOrdinalNumber) {

		adminPage.addNewParty(partyName, partyOrdinalNumber);
		adminPage.assertSuccessMessageIsDisplayed();
	}

	@Test(priority = 8, dataProvider = "testPattern")
	/**
	 * Check if administrator can add a party list. Assert by success message
	 * and user can find candidate in search page.
	 */
	public void partyListRegistrationTest(String partyName, String partyOrdinalNumber) {

		adminPage.addPartyList(partyOrdinalNumber);
		adminPage.assertPartyListAddSuccessMessageIsDisplayed();
		String candidateName = getCandidateFromCsv(partyOrdinalNumber);
		if (candidateName != null) {
			SearchPage searchPage = homePage.openSearchPage();
			searchPage.searchForCandidate(candidateName);
			searchPage.assertNameIsPresent(candidateName);
		}
	}

	@Test(priority = 14, dataProvider = "testPattern")
	/**
	 * Check if administrator can delete a party together with their candidate
	 * list. Assert by success message.
	 */
	
	public void deletePartyWithCandidateListTest(String partyName, String partyNumber) {
		adminPage.deleteParty(partyNumber);
		adminPage.assertSuccessMessageIsDisplayed();
	}

	@DataProvider
	public Object[][] testPattern() throws IOException {

		GetTestPattern testPattern = new GetTestPattern();
		return testPattern.getTestPattern(Var.PARTY_REGISTRATION_TEST_DATA);
	}

	private String getCandidateFromCsv(String partyOrdinalNumber) {
		String filePath = Var.multiPath + partyOrdinalNumber + ".csv";
		DataReader reader = new DataReader();
		try {
			List<String> candidates = reader.getTestData(filePath);
			String[] array = candidates.get(0).split(",");
			return array[0].concat(" ").concat(array[1]);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
}
