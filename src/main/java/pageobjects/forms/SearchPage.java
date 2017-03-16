package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageobjects.main.PageObject;
import utilites.Var;

public class SearchPage extends PageObject {

	@FindBy(id = "candidateSearch")
	@CacheLookup
	private WebElement candidateSearchField;

	@FindBy(xpath = "//a[text()='" + Var.Kandidato_Info + "']")
	@CacheLookup
	private WebElement candidateInfoButton;

	@FindBy(css = "div[class='title']")
	@CacheLookup
	private WebElement candidateNameTitle;

	@FindBy(xpath = "//tbody/tr")
	@CacheLookup
	private WebElement noCandidate;

	// Constructor
	public SearchPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	// Page methods
	public void searchForCandidate(String candidateName) {
		setTextFieldValueWithClickClear(candidateSearchField, candidateName);
		String[] arrayName = candidateName.split(" ");
		WebElement firstName = getWebElement("//tbody//td[text()='" + arrayName[0] + "']");
		if (firstName != null) {
			if (arrayName.length > 1) {
				WebElement lastName = getWebElement("//tbody//td[text()='" + arrayName[1] + "']");
				if (lastName != null) {
					clickButton(candidateInfoButton);
				}
			}
		}

	}

	// Asserts
	public void assertNameIsPresent(String candidateName) {
		Assert.assertEquals(getTextFieldValue(candidateNameTitle), candidateName,
				"The candidate name from the file does not match the one showing on page");
	}

	public void assertInvalidNameIsNotPresent(String invalidCandidateName) {
		Assert.assertEquals(!isElementDisplayed(candidateNameTitle), true,
				"Candidate name " + invalidCandidateName + " is not displayed");
	}

}
