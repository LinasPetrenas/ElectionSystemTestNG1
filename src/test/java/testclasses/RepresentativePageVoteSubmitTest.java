package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.forms.HomePage;
import pageobjects.forms.LoginPage;
import pageobjects.forms.RepresentativePage;
import pageobjects.forms.UserMultiResultCountyPage;
import pageobjects.forms.UserMultiResultDistrictPage;
import pageobjects.forms.UserMultiResultPage;
import pageobjects.forms.UserSingleResultCountyPage;
import pageobjects.forms.UserSingleResultDistrictPage;
import pageobjects.forms.UserSingleResultPage;
import pageobjects.main.TestMain;
import utilites.GetRandomVotes;
import utilites.GetTestPattern;
import utilites.Var;

public class RepresentativePageVoteSubmitTest extends TestMain {

	List<String> candidateList;
	List<String> partyList = new ArrayList<String>();
	HomePage homePage;
	RepresentativePage repPage;

	@BeforeMethod
	public void login() throws IOException {
		repPage = loginAsRepresentative();
	}

	@Test(priority = 12, dataProvider = "testPattern")
	/**
	 * Check if committee representative can submit votes for each candidate.
	 * Assert by success message and votes for candidates are asserted.
	 */

	public void submitVotesForCandidatesTest(String district, String candidateCount, String partyCount, String county)
			throws InterruptedException {
		repPage.assertRepresentativePageTitleIsPresented();
		repPage.assertDistrictIsPresented(district);
		GetRandomVotes randomVotes = new GetRandomVotes();
		candidateList = randomVotes.getRandomVotes(Integer.parseInt(candidateCount));
		repPage.fillVotesForCandidates(candidateList);
		homePage = new HomePage(webDriver, webSiteAddress);
		UserSingleResultPage singleResultPage = homePage.openUserSingleResultPage();
		UserSingleResultCountyPage singleResultCountyPage = singleResultPage.selectCounty(county);
		UserSingleResultDistrictPage singleResultDistrictPage = singleResultCountyPage.selectDistrict(district);
		int voteSumForCandidates = 0;
		for (String vote : candidateList) {
			voteSumForCandidates += Integer.parseInt(vote);
		}
		System.out.println(voteSumForCandidates);

		singleResultDistrictPage.compareCandidateVotes(voteSumForCandidates);
	}

	@Test(priority = 13, dataProvider = "testPattern")
	/**
	 * Check if committee representative can submit votes for each political
	 * group. Assert by success message and votes for political party are
	 * asserted.
	 */
	public void submitVotesForPartiesTest(String district, String candidateCount, String partyCount, String county) throws InterruptedException {
		repPage.assertRepresentativePageTitleIsPresented();
		repPage.assertDistrictIsPresented(district);
		GetRandomVotes randomVotes = new GetRandomVotes();
		partyList = randomVotes.getRandomVotes(Integer.parseInt(partyCount));
		repPage.fillVotesForParties(partyList);
		homePage = new HomePage(webDriver, webSiteAddress);
		UserMultiResultPage multiResultPage = homePage.openUserMultiResultPage();
		UserMultiResultCountyPage multiResultCountyPage = multiResultPage.selectCounty(county);
		UserMultiResultDistrictPage multiResultDistrictPage = multiResultCountyPage.selectDistrict(district);
		int voteSumForParties = 0;
		for (String vote : partyList) {
			voteSumForParties += Integer.parseInt(vote);
		}
		System.out.println(voteSumForParties);

		multiResultDistrictPage.comparePartyVotes(voteSumForParties);
	}

	@DataProvider
	public Object[][] testPattern() throws IOException {

		GetTestPattern testPattern = new GetTestPattern();
		return testPattern.getTestPattern(Var.REP_VOTE_REGISTRATION_TEST_DATA);
	}

}