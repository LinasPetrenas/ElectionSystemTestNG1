package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.main.PageObject;
import utilites.Var;

public class UserMultiResultPage extends PageObject {

	// Constructor
	public UserMultiResultPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	// Page methods
	public UserMultiResultCountyPage selectCounty(String countyName) {
		WebElement county = getWebElement("//a[text()= '" + countyName + "']");
		scrollToWebElement(county, Var.scrollX, Var.scrollY1);
		clickLink(county);
		return new UserMultiResultCountyPage(webDriver, baseUrl);
	}
}