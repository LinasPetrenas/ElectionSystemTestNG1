package pageobjects.forms;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import pageobjects.main.EmptyPage;
import pageobjects.main.PageObject;
import utilites.Var;

public class RepresentativePage extends PageObject implements EmptyPage {

	@FindBy(xpath = "//*[text()= '" + Var.Atstovo_puslapis + "']")
	@CacheLookup
	private WebElement repPageTitle;

	@FindBy(id = "corruptCandidate")
	@CacheLookup
	private WebElement corruptCandidate;

	@FindBy(id = "corruptParty")
	@CacheLookup
	private WebElement corruptParty;

	@FindBy(id = "submitCandidate")
	@CacheLookup
	private WebElement submitCandidate;

	@FindBy(id = "submitParty")
	@CacheLookup
	private WebElement submitParty;

	// Constructor
	public RepresentativePage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	// Page methods
	public void fillVotesForCandidates(List<String> candidateList) {

		for (int i = 0; i < candidateList.size() - 1; i++) {
			String candidateVotes = candidateList.get(i);
			WebElement candidates = getWebElement("//input[@id='candidate" + i + "']");
			scrollToWebElement(candidates, 0, -60);
			setTextFieldValueWithClickClear(candidates, candidateVotes);
		}
		setTextFieldValueWithClickClear(corruptCandidate, candidateList.get(candidateList.size() - 1));
		clickButton(submitCandidate);
	}
	
	public void fillVotesForParties(List<String> partyList) {

		for (int i = 0; i < partyList.size() - 1; i++) {
			String partyVotes = partyList.get(i);
			WebElement parties = getWebElement("//input[@id='party" + i + "']");
			scrollToWebElement(parties, 0, -60);
			setTextFieldValueWithClickClear(parties, partyVotes);
		}
		setTextFieldValueWithClickClear(corruptCandidate, partyList.get(partyList.size() - 1));
		clickButton(submitCandidate);
	}

	// Asserts
	public void assertRepresentativePageTitleIsPresented() {
		assertElementIsDisplayed(repPageTitle, "Representative page is opened - ");
	}

	public void assertDistrictIsPresented(String districtName) {
		WebElement district = getWebElement("//*[text()= '" + districtName + "']");
		assertElementIsDisplayed(district, "District " + districtName + " is presented - ");
	}

}
