package testclasses;



import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.forms.HomePage;
import pageobjects.forms.SearchPage;
import pageobjects.main.TestMain;
import utilites.GetTestPattern;
import utilites.Var;

public class SearchForCandidatesTest extends TestMain {

	HomePage homePage;
	SearchPage searchPage;

	@BeforeMethod
	public void openSearchPage() {
		homePage = new HomePage(webDriver, webSiteAddress);
		searchPage = homePage.openSearchPage();
		searchPage.checkIsLoaded();
		}
	
	@Test(priority = 10, dataProvider = "testPattern")
	/**
	 * Check if a user can search for candidates.
	 * Assert by candidates name and surname in "Kandidato Info" section.
	 */
	public void searchForCandidateTest(String candidateName) {
		searchPage.searchForCandidate(candidateName);
		searchPage.assertNameIsPresent(candidateName);
	}
	
	//@Test(priority = 11, dataProvider = "invalidTestPattern")
	/**
	 * Check what happens when user tries to search for candidate which does not exist. 
	 * Assert that candidate is not presented.
	 */
	public void searchForInvalidCandidateTest(String invalidCandidateName) {
		searchPage.searchForCandidate(invalidCandidateName);
		searchPage.assertInvalidNameIsNotPresent(invalidCandidateName);
		
	}

	@DataProvider
	public Object[][] testPattern() throws IOException {

		GetTestPattern testPattern = new GetTestPattern();
		return testPattern.getTestPattern(Var.SEARCH_TEST_DATA);
	}
	
	@DataProvider
	public Object[][] invalidTestPattern() throws IOException {

		GetTestPattern testPattern = new GetTestPattern();
		return testPattern.getTestPattern(Var.INVALID_SEARCH_TEST_DATA);
	}

}
