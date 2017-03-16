package pageobjects.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.main.PageObject;

public class UserSingleResultCountyPage extends PageObject {
	
	// Constructor
		public UserSingleResultCountyPage(WebDriver webDriver, String baseUrl) {
			super(webDriver, baseUrl);
		}

		//Page methods
		public UserSingleResultDistrictPage selectDistrict(String districtName) {
			WebElement district = getWebElement("//a[text()= '" + districtName + "']");
			scrollToWebElement(district, 0, -60);
			clickLink(district);
			return new UserSingleResultDistrictPage(webDriver, baseUrl);
		}

}
