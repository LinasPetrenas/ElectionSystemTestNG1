package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.main.PageObject;

public class UserSingleResultPage extends PageObject {

	// Constructor
	public UserSingleResultPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}
	
	//Page methods
	public UserSingleResultCountyPage selectCounty(String countyName) {
		WebElement county = getWebElement("//a[text()= '" + countyName + "']");
		scrollToWebElement(county, 0, -60);
		clickLink(county);
		return new UserSingleResultCountyPage(webDriver, baseUrl);
	}

}
