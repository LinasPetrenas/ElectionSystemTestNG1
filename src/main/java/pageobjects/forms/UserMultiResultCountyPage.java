package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.main.PageObject;

public class UserMultiResultCountyPage extends PageObject {

	public UserMultiResultCountyPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	// Page methods
	public UserMultiResultDistrictPage selectDistrict(String districtName) {
		WebElement district = getWebElement("//a[text()= '" + districtName + "']");
		scrollToWebElement(district, 0, -60);
		clickLink(district);
		return new UserMultiResultDistrictPage(webDriver, baseUrl);
	}

}