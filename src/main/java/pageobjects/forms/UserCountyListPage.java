package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageobjects.main.PageObject;

public class UserCountyListPage extends PageObject {

	// Constructor
	public UserCountyListPage(WebDriver webDriver, String baseUrl) {
		
		super(webDriver, baseUrl);
	}

	public UserDistrictListPage selectCounty(String countyName) {
		
		WebElement county = getWebElement("//a[text()= '" + countyName + "']");
		scrollToWebElement(county, 0, -60);
		clickLink(county);
		return new UserDistrictListPage(webDriver, baseUrl);
	}

	// Asserts
	public void assertCountyIsPresented(String countyName) {

		WebElement county = getWebElement("//*[text()= '" + countyName + "']");
		assertElementIsDisplayed(county, "County " + countyName + " is presented - ");
	}
}
