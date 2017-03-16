package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageobjects.main.PageObject;

public class UserSingleResultDistrictPage extends PageObject {

	@FindBy(id = "totalSingleVotes")
	@CacheLookup
	private WebElement totalSingleVotes;

	// Constructor
	public UserSingleResultDistrictPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	// Page methods

	// Asserts
	public void compareCandidateVotes(int voteSumForCandidates) {
		Assert.assertEquals(Integer.parseInt(getTextFieldValue(totalSingleVotes)), voteSumForCandidates,
				"The total votes are not the same in committee page and vote district page");
	}
}
