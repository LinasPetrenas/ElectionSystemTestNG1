package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import pageobjects.main.PageObject;
import utilites.Var;

public class HomePage extends PageObject {

	// Buttons
	@FindBy(css = "a[href='#/']")
	@CacheLookup
	private WebElement homeIcon;

	@FindBy(css = "a[href='#/login']")
	@CacheLookup
	private WebElement loginIcon;

	@FindBy(xpath = "//h3[text()='" + Var.Apygardu_Sarasas + "']")
	@CacheLookup
	private WebElement countyListLink;
	
	@FindBy(css = "a[href='#/search-result'")
	@CacheLookup
	private WebElement searchIcon;
	
	@FindBy(xpath = "//h3[text()='" + Var.Vienmandates_Rezultatai + "']")
	@CacheLookup
	private WebElement singleResultLink;
	
	@FindBy(xpath = "//h3[text()='" + Var.Daugiamandates_Rezultatai + "']")
	@CacheLookup
	private WebElement multiResultLink;

	// Constructor
	public HomePage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	public LoginPage openLoginPage() {
		waitForElementToBeInDOM(loginIcon);
		loginIcon.click();
		return new LoginPage(webDriver, baseUrl);
	}

	public UserCountyListPage openUserCountyListPage() throws InterruptedException {
		goHome();
		waitForElementToBeInDOM(countyListLink);
		countyListLink.click();
		return new UserCountyListPage(webDriver, baseUrl);
	}

	public void goHome() {
		waitForElementToBeInDOM(homeIcon);
		homeIcon.click();
	}
	
	public SearchPage openSearchPage() {
		waitForElementToBeInDOM(searchIcon);
		searchIcon.click();
		return new SearchPage(webDriver, baseUrl);
	}
	
	public UserSingleResultPage openUserSingleResultPage() throws InterruptedException {
		goHome();
		waitForElementToBeInDOM(singleResultLink);
		singleResultLink.click();
		return new UserSingleResultPage(webDriver, baseUrl);
	}
	
	public UserMultiResultPage openUserMultiResultPage() throws InterruptedException {
		goHome();
		waitForElementToBeInDOM(multiResultLink);
		multiResultLink.click();
		return new UserMultiResultPage(webDriver, baseUrl);
	}
}
