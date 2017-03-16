package pageobjects.forms;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import pageobjects.main.PageObject;
import utilites.Var;

public class AdminCountyPage extends PageObject {

	// Fields
	@FindBy(xpath = "//h3[text()='" + Var.Apylinkes_registravimas + "']")
	@CacheLookup
	private WebElement districtRegistration;

	@FindBy(id = "districtCreatedMsg")
	@CacheLookup
	private WebElement createDistrictSuccessMessage;

	@FindBy(id = "example-text-input")
	@CacheLookup
	private WebElement inputDistrictName;

	@FindBy(id = "example-search-input")
	@CacheLookup
	private WebElement inputDistrictAddress;

	@FindBy(id = "example-email-input")
	@CacheLookup
	private WebElement inputDistrictElectors;

	@FindBy(id = "btn-danger btn btn-default")
	@CacheLookup
	private WebElement confirmDeletionBtn;

	@FindBy(id = "contained-modal-title-sm")
	@CacheLookup
	private WebElement districtDeletedSuccessMessage;

	// Buttons
	@FindBy(css = "button[class='btn btn-primary']")
	@CacheLookup
	private WebElement buttonSubmit;

	// Constructor
	public AdminCountyPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	public void fillDistrictData(String districtName, String districtAddress, String districtElectors) {

		scrollToWebElement(inputDistrictName, 0, -80);
		setTextFieldValueWithClickClear(inputDistrictName, districtName);
		setTextFieldValueWithClickClear(inputDistrictAddress, districtAddress);
		setTextFieldValueWithClickClear(inputDistrictElectors, districtElectors);
		clickButton(buttonSubmit);
	}

	public AdminAddRepresentativePage selectDistrict(String districtName) {

		WebElement district = getWebElement("//a[text()= '" + districtName + "']");
		scrollToWebElement(district, 0, -60);
		clickLink(district);
		return new AdminAddRepresentativePage(webDriver, baseUrl);
	}

	public AdminAddRepresentativePage clickAddRepresentativeBtn(String districtName) {
		System.out.println(districtName);
		WebElement addRepresentativeBtn = getWebElement("//div[text()='" + districtName + "']/../../td[4]//button");
		scrollToWebElement(addRepresentativeBtn, 0, -60);
		clickButton(addRepresentativeBtn);
		return new AdminAddRepresentativePage(webDriver, baseUrl);
	}

	public void deleteDistrict(String districtName) {
		WebElement deleteDistrictBtn = getWebElement("//*[text()='"+ districtName +"']/../../td[6]//button");
		scrollToWebElement(deleteDistrictBtn, 0, -80);
		clickButton(deleteDistrictBtn);
		clickButton(confirmDeletionBtn);
	}

	// Asserts
	public void assertSuccessMessageIsDisplayed() {
		assertElementIsDisplayed(createDistrictSuccessMessage, "Success message is displayed - ");
	}
	
	public void assertDistrictIsDeleted() {
		assertElementIsDisplayed(districtDeletedSuccessMessage, "Success message is displayed - ");
	}

}
