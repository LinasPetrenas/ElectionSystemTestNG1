package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageobjects.main.PageObject;
import utilites.Var;

public class LoginPage extends PageObject {

	// --- fields
	// -------------------------------------------------------------------------------------------------------
	@FindBy(css = "input[placeholder='Prisijungimo vardas']")
	@CacheLookup
	private WebElement username;

	@FindBy(css = "input[placeholder='Slaptazodis']")
	@CacheLookup
	private WebElement password;

	@FindBy(id = "contained-modal-title-sm")
	@CacheLookup
	private WebElement loginErrorMessage;

	// --- buttons
	// ------------------------------------------------------------------------------------------------------
	@FindBy(css = "button[type='submit']")
	@CacheLookup
	private WebElement loginButton;

	@FindBy(css = "div > button")
	@CacheLookup
	private WebElement closeButton;

	// Constructor
	public LoginPage(WebDriver webDriver, String baseUrl) {

		super(webDriver, baseUrl);

	}

	// ==================================================================================================================
	// --- setters
	// ------------------------------------------------------------------------------------------------------
	// ==================================================================================================================

	/**
	 * Sets user name
	 * 
	 * @param user
	 */
	public void enterUsername(String user) {
		setTextFieldValue(username, user);
	}

	/**
	 * Sets password
	 * 
	 * @param psw
	 */
	public void enterPassword(String psw) {
		setTextFieldValue(password, psw);
	}

	/**
	 * Clicks Login button
	 * 
	 * @return
	 */
	public void clickLogin() {
		loginButton.click();
	}

	/**
	 * Click error message close button
	 */
	public void clickCloseErrorMessage() {
		closeButton.click();
	}

	// Asserts
	public void assertErrorMessageIsDisplayed() {
		assertElementIsDisplayed(loginErrorMessage, "Error message is displayed - ");
	}

}
