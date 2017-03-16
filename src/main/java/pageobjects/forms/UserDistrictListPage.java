package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.main.PageObject;

public class UserDistrictListPage extends PageObject {

	// Constructor
	public UserDistrictListPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	public void assertDistrictData(String districtName, String districtAddress, String districtElectors) {

		WebElement district = getWebElement("//div[text()='" + districtName + "']");
		scrollToWebElement(district, 0, -60);
		assertElementIsDisplayed(district, "District " + districtName + " is displayed - ");

		WebElement address = getWebElement("//div[text()='" + districtAddress + "']");
		assertElementIsDisplayed(address, "District address " + districtAddress + " is displayed - ");

		WebElement electors = getWebElement("//div[text()='" + districtElectors + "']");
		assertElementIsDisplayed(electors, "District electors number " + districtElectors + " is displayed - ");
	}
}
