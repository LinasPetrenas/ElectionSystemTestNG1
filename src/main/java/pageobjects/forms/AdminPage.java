package pageobjects.forms;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import pageobjects.main.EmptyPage;
import pageobjects.main.PageObject;
import utilites.Var;

public class AdminPage extends PageObject implements EmptyPage {

	// Fields
	@FindBy(xpath = "//*[text()= '" + Var.Administratoriaus_puslapis + "']")
	@CacheLookup
	private WebElement adminPageTitle;

	@FindBy(id = "exampleInput1")
	@CacheLookup
	private WebElement inputNewParty;

	@FindBy(id = "dropdownOne")
	@CacheLookup
	private WebElement selectPartyList;

	@FindBy(id = "exampleInputNumber")
	@CacheLookup
	private WebElement inputNewPartyNumber;

	@FindBy(css = "input[name='multiPartyListFile']")
	@CacheLookup
	private WebElement browsePartyListFile;

	@FindBy(id = "partyListAddFormMsg")
	@CacheLookup
	private WebElement partyListAddMessage;

	@FindBy(css = "div[id = 'countyListMsg']>div[class='alert alert-success']")
	@CacheLookup
	private WebElement countyListAddMessage;
	
	@FindBy(css = "div[id = 'countyListDeletionMsg']>div[class='alert alert-success']")
	@CacheLookup
	private WebElement countyListDeleteMessage;

	@FindBy(id = "exampleInput")
	@CacheLookup
	private WebElement inputNewCounty;

	@FindBy(css = "input[name='singlePartyListFile']")
	@CacheLookup
	private WebElement browseSingleListFile;
	
	@FindBy(id = "countyDeletionRequest")
	@CacheLookup
	private WebElement countyDeleteRequest;
	
	@FindBy(id = "countyDeletionMessage")
	@CacheLookup
	private WebElement countyDeletedMessage;

	@FindBy(css = "div[class='alert alert-success']")
	@CacheLookup
	private WebElement successMessage;

	@FindBy(css = "div[class='alert alert-danger']")
	@CacheLookup
	private WebElement errorMessage;

	// Lists
	@FindBy(css = "select[name='partyId']")
	@CacheLookup
	private WebElement partyList;

	// Links
	@FindBy(css = "a[href='#partyRegistration']")
	@CacheLookup
	private WebElement addNewParty;

	@FindBy(css = "a[href='#partyListRegistration']")
	@CacheLookup
	private WebElement addPartyList;

	@FindBy(css = "a[href='#partyListDeletion']")
	@CacheLookup
	private WebElement deleteParty;

	@FindBy(id = "dropdownTwo")
	@CacheLookup
	private WebElement selectPartyForDeletion;

	//@FindBy(xpath = "//a[text()= '" + Var.Naujos_apygardos_registravimas + "']")
	@FindBy(css = "a[href='#countyRegistration']")
	@CacheLookup
	private WebElement addNewCounty;

	@FindBy(css = "a[href='#countyListRegistration']")
	@CacheLookup
	private WebElement addCountyList;

	@FindBy(id = "dropdownThree")
	@CacheLookup
	private WebElement selectCountyList;

	@FindBy(css = "a[href='#countyListDeletion']")
	@CacheLookup
	private WebElement deleteCountyList;
	
	@FindBy(id = "dropdownFour")
	@CacheLookup
	private WebElement selectCountyForCountyListDeletion;

	// Buttons
	@FindBy(css = "form[id='partyAddForm'] > button")
	@CacheLookup
	private WebElement buttonConfirmParty;

	@FindBy(css = "form[id='partyCandidateListAddForm']>button")
	@CacheLookup
	private WebElement buttonConfirmPartyList;

	@FindBy(xpath = "//form[@id='partyCandidateListDeleteForm']//button")
	@CacheLookup
	private WebElement buttonDeletePartyList;

	@FindBy(css = "form[id='countyAddForm'] > button")
	@CacheLookup
	private WebElement buttonConfirmCounty;

	@FindBy(css = "form[id='countyCandidateListAddForm']>input")
	@CacheLookup
	private WebElement buttonConfirmCountyList;

	@FindBy(xpath = "//*[@id='countyListDeletion']//button")
	@CacheLookup
	private WebElement buttonDeleteCountyList;

	@FindBy(id = "countyDeletionConfirm")
	@CacheLookup
	private WebElement buttonConfirmDeleteCounty;
	
	@FindBy(css = "button[class='close']")
	@CacheLookup
	private WebElement buttonCloseSuccessMessage;

	@FindBy(css = "button[class='btn-danger btn btn-default']")
	@CacheLookup
	private WebElement buttonConfirmDeletion;

	// Constructor
	public AdminPage(WebDriver webDriver, String baseUrl) {
		super(webDriver, baseUrl);
	}

	public AdminCountyPage selectCounty(String countyName) {

		WebElement county = getWebElement("//a[text()= '" + countyName + "']");
		scrollToWebElement(county, Var.scrollX, Var.scrollY);
		clickLink(county);
		return new AdminCountyPage(webDriver, baseUrl);
	}

	// Page test methods
	public void addNewParty(String partyName, String partyOrdinalNumber) {

		setDropDownMenu(addNewParty, inputNewParty, partyName);
		setTextFieldValueWithClickClear(inputNewPartyNumber, partyOrdinalNumber);
		clickButton(buttonConfirmParty);
	}

	public void addPartyList(String partyOrdinalNumber) {

		setDropDownList(addPartyList, selectPartyList, partyOrdinalNumber);
		scrollToWebElement(addPartyList, Var.scrollX, Var.scrollY);
		String filePath = Var.multiPath + partyOrdinalNumber + ".csv";
		attachFile(filePath, browsePartyListFile);
		clickButton(buttonConfirmPartyList);
	}

	public void deleteParty(String partyNumber) {

		setDropDownList(deleteParty, selectPartyForDeletion, partyNumber);
		scrollToWebElement(selectPartyForDeletion, Var.scrollX, Var.scrollY);
		clickButton(buttonDeletePartyList);
		clickButton(buttonConfirmDeletion);
	}

	public void addNewCounty(String countyName) throws InterruptedException {

		setDropDownMenu(addNewCounty, inputNewCounty, countyName);
		clickButton(buttonConfirmCounty);
	}

	// *****
	public void addCandidateList(String countyName) {
		setDropDownListByVisibleText(addCountyList, selectCountyList, countyName);
		scrollToWebElement(selectCountyList, Var.scrollX, Var.scrollY);
		String filePath = Var.singlePath + countyName + ".csv";
		attachFile(filePath, browseSingleListFile);
		clickButton(buttonConfirmCountyList);

	}

	public void deleteCounty(String countyName) throws InterruptedException {

		WebElement county = getWebElement("//a[text()= '" + countyName + "']/../../..//button");
		scrollToWebElement(county, Var.scrollX, Var.scrollY);
		clickButton(county);
		clickButton(buttonConfirmDeleteCounty);

	}

	public void deleteCountyCandidateList(String countyName) {
		setDropDownListByVisibleText(deleteCountyList, selectCountyForCountyListDeletion, countyName);
		scrollToWebElement(selectCountyForCountyListDeletion, Var.scrollX, Var.scrollY);
		clickButton(buttonDeleteCountyList);
		clickButton(buttonConfirmDeletion);
	}

	// Asserts
	public void assertTitleIsDisplayed() {

		assertElementIsDisplayed(adminPageTitle, "Admin page is opened - ");
	}

	public void assertPartyListAddSuccessMessageIsDisplayed() {

		assertElementIsDisplayed(partyListAddMessage, "Success message is displayed - ");
	}

	public void assertCountyListAddSuccessMessageIsDisplayed() {

		assertElementIsDisplayed(countyListAddMessage, "Success message is displayed - ");
	}
	
	public void assertCountyListDeleteSuccessMessageIsDisplayed() {
		
		assertElementIsDisplayed(countyListDeleteMessage, "Success message is displayed - ");
	}
	
	public void assertCountyDeleteSuccessMessageIsDisplayed() {
		assertElementIsDisplayed(countyDeletedMessage, "Success message is displayed - ");
		clickButton(buttonCloseSuccessMessage);
	}

	public void assertCountyIsPresented(String countyName) {

		WebElement county = getWebElement("//*[text()= '" + countyName + "']");
		assertElementIsDisplayed(county, "County " + countyName + " is presented - ");
	}

	public void assertSuccessMessageIsDisplayed() {

		assertElementIsDisplayed(successMessage, "Success message is displayed - ");
	}

	public void assertErrorMessageIsDisplayed() {

		assertElementIsDisplayed(errorMessage, "Error message is displayed - ");
	}

	

	
}
