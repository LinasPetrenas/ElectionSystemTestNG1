package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageobjects.main.PageObject;

public class UserMultiResultDistrictPage extends PageObject {

	@FindBy(id = "totalMultiVotes")
	@CacheLookup
	private WebElement totalMultiVotes;

	// Constructor
	public UserMultiResultDistrictPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	// Asserts
	public void comparePartyVotes(int voteSumForParties) {
		Assert.assertEquals(Integer.parseInt(getTextFieldValue(totalMultiVotes)), voteSumForParties,
				"The total votes are not the same in committee page and vote district page");
	}
}