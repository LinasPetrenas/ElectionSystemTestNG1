package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import pageobjects.main.PageObject;

public class AdminAddRepresentativePage extends PageObject {

	// Fields
	@FindBy(id = "example-text-input1")
	@CacheLookup
	private WebElement inputRepresentativeName;
	
	@FindBy(id = "example-search-input1")
	@CacheLookup
	private WebElement inputRepresentativeSurname;
	
	@FindBy(id = "example-email")
	@CacheLookup
	private WebElement inputRepresentativeUsername;
	
	@FindBy(id = "example-email-input1")
	@CacheLookup
	private WebElement inputRepresentativePassword;
	
	@FindBy(css = "div[class='alert alert-success']")
	@CacheLookup
	private WebElement registerRepresentativeSuccessMessage;
	
	// Buttons
	
	@FindBy(css = "button[class='btn btn-primary']")
	@CacheLookup
	private WebElement buttonSubmit;
	
	

	// Constructor
	public AdminAddRepresentativePage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}
	
	// Page methods
	
	public void fillRepresentativeData(String username, String password, String name, String surname) {

		scrollToWebElement(inputRepresentativeName, 0, -80);
		setTextFieldValueWithClickClear(inputRepresentativeName, name);
		setTextFieldValueWithClickClear(inputRepresentativeSurname, surname);
		setTextFieldValueWithClickClear(inputRepresentativeUsername, username);
		setTextFieldValueWithClickClear(inputRepresentativePassword, password);
		clickButton(buttonSubmit);
	}
	
	// Asserts
	
	public void assertRepresentativeAddSuccessMessageIsDisplayed() {
		assertElementIsDisplayed(registerRepresentativeSuccessMessage, "Success message is displayed - ");
	}

	

}
