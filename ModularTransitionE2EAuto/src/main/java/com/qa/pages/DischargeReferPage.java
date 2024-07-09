/**
 * 
 */
package com.qa.pages;

import org.json.JSONArray;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

import static com.qa.util.TestUtil.*;

import java.util.List;

public class DischargeReferPage extends TestBase {

	// Xpaths on refer page.
	@FindBy(xpath = "//div[contains(@class,'service-card')]")
	WebElement serviceCardList;

	@FindBy(xpath = "//select[@id='discharge-view-matching-selected-service-0']")
	WebElement selectServiceDropdown;

	@FindBy(xpath = "//select[@id='discharge-view-matching-selected-loc-0']")
	WebElement selectLocDropdown;

	@FindBy(xpath = "//input[@id='matching-provider-name']")
	WebElement providerNameField;

	@FindBy(xpath = "//select[@id='state']")
	WebElement stateDropdownElement;

	@FindBy(xpath = "//button[contains(.,'Key ServicesNetworks')]")
	WebElement keyServiceNetworkButton;

	@FindBy(xpath = "//span[contains(.,'COVID-Ready')]")
	WebElement keyServicesSelectionInModal;

	@FindBy(xpath = "//button[contains(.,'UPDATE')]")
	WebElement keyServicesSelectionInModalUpdateButton;

	@FindBy(xpath = "//label[contains(.,'Provider Name')]//div[@class='mat-radio-inner-circle']")
	WebElement providerNameRadioButton;

	@FindBy(xpath = "//button[@id='matching-submit-search']")
	WebElement searchButton;

	@FindBy(xpath = "//button[@id='matching-reset']")
	WebElement clearAllButton;
	
	@FindBy(id = "discharge-header-matching-button")
	WebElement ReferPageButton;
	
	@FindBy(id = "matching-zip")
	WebElement ZIP;

	@FindBy(xpath = "//button[@id='discharge-matching-view-matching-send-referral-packet-button'][not(contains(@class,'disabled'))]")
	WebElement sendReferralPacketButton;
	
	//PML Specific Locators 
	
	@FindBy(id = "pml-button")
	WebElement ProviderListDropdown;

	@FindBy(xpath = "//button[contains(text(), 'Share List')]")
	WebElement ShareList;

	@FindBy(xpath = "//button[contains(text(), 'Open List')]")
	WebElement OpenList;

	@FindBy(xpath = "//input[@placeholder = 'Patient or Caregiver Email or Cell Number']")
	WebElement EmailfieldShareProviderListModal;

	@FindBy(id = "update-action-btn")
	WebElement SharePMLButton;

	@FindBy(id = "confirm-action-btn")
	WebElement Closepmlsharemodal;

	@FindBy(xpath = "//*[*[contains(text(),'SELECT ANOTHER')]]")
	WebElement selectAnotherBtn;

	@FindBy(xpath = "//span[contains(text(),'NEXT')]")
	WebElement nextBtn;

	@FindBy(xpath = "//div[@class='mat-form-field-flex']")
	WebElement pmlAppWhoMadeThisChoiceDropdown;

	@FindBy(xpath = "//span[contains(text(),'Patient/Self')]")
	WebElement pmlAppPatientValueFromDropdown;

	@FindBy(xpath = "//input[@name='name']")
	WebElement pmlAppNameInputTextBox;

	@FindBy(xpath = "//img[@class='ng-star-inserted']")
	WebElement pmlAppInputNameImage;

	@FindBy(xpath = "//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']")
	WebElement pmlCheckboxSubmit;

	@FindBy(xpath = "//button[*[*[contains(text(),'SIGN & SEND')]]]")
	WebElement pmlAppSubmitBtn;

	@FindBy(xpath = "//*[contains(text(),'Thank you for your selection')]")
	WebElement pmlTextPostSelection;

	@FindBy(xpath = "(//div[@class='mat-radio-container'])[2]")
	WebElement providerNameSearchRadioBtn;

	// Dynamic xpaths
	String providerCheckBox = "//mat-row[contains(.,'xxxxx')]//mat-checkbox";
	String keyServiceCheckbox = "//li[contains(.,'xxxxx')]//label";
	private String providerCheckboxByName = "//mat-cell[normalize-space()='xxxxx']/..//mat-checkbox[contains(@class,'mat-checkbox')]/label";
	private String providerSelectBtnOnPML = "//pml-provider-list-item['xxxxx']//button[@class='btn-select mat-flat-button ng-star-inserted']";
	private String pmlAppProviderOnSuccessPage = "//a[contains(text(),'xxxxx')]";

	// Initializing the Page Factory/Objects:
	public DischargeReferPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	// Actions/Methods on this page:
	public String verifyInboxPageTitle() {
		return getdriver().getCurrentUrl();

	}

	public void sendReferralPacket(String providerName,String stateCode) {
		// Select the Radio Button
		scrollPageToElementAndClick(providerNameRadioButton, "Radio button");
		// Input the Provider name
		clearAndInput(providerNameField, "Provider Name", providerName);
		// Select the State
		selectValueByVisibleText(stateDropdownElement, stateCode, "Select State");
		waitForLoaderToDisappear();
		// Click on Search Button
		clickElement(searchButton, "Search button");
		waitForLoaderToDisappear();
		// Select the check box for the provider
		clickElement(prepareWebElementWithDynamicXpath(providerCheckBox, providerName), "provider check box");
		// Click on Send referral Packet
		clickElement(sendReferralPacketButton, "Send referral Packet Button");
		waitForLoaderToDisappear();
		reportLog("Referral Created an sent to Intake");

	}

	public void sendReferralByNameProviderName(List<String> providerNames){
		clickElement(providerNameSearchRadioBtn,"Search By Provider Name Radio Button");
		reportLog(providerNames.get(0));
		input(providerNameField,"provider name field",providerNames.get(0));
		scrollPageToElementAndClick(searchButton,"Search Provider Button");
		scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(providerCheckboxByName,providerNames.get(0)),"Provider Checkbox");
		// Click on Send referral Packet
		scrollPageToElementAndClick(sendReferralPacketButton, "Send referral Packet Button");
		waitForLoaderToDisappear();
	}

	//Search providers by zip in refer page
	public void searchProvidersByLocationZip(String zip) {
		clearAndInput(this.ZIP, "Zip Field", zip);
		clickElement(searchButton, "Search button on refer Page");
		waitForLoaderToDisappear();
	}

	//Click on the checkboxes of multiple providers in refer page 
	public void clickOnCheckboxByProviderName(List<String> providerNames ) {
		for (String providerName : providerNames) {
			clickElement(prepareWebElementWithDynamicXpath(providerCheckboxByName,providerName),"Provider Checkbox");
		}
	}

	//Share Patient Matching List 
	public void sharePMLList() {
		waitForElementToLoad(ProviderListDropdown, "Provider List Dropdown to load");
		clickElement(ProviderListDropdown, "Clicked on Provider List Dropdown button");
		clickElement(ShareList, "Clicked on Share List Dropdown button");
		waitForElementToLoad(EmailfieldShareProviderListModal, "Wait for Email Field");
		clearAndInput(this.EmailfieldShareProviderListModal, "Email Field", "d2migrationemailuser@navihealth.com");
		clickElement(SharePMLButton, "Share Patient Matching List");
		//get the current date time here
		waitForLoaderToDisappear();
		clickElement(Closepmlsharemodal, "closing the PML Share Modal");
	}

	//Open PML List	
	public void openPMLList() {
		waitForPageToLoad();
		clickElement(ProviderListDropdown, "Clicked on Provider List Dropdown button");
		waitForElementToBeClickable(OpenList, "Open List is clickable");
		clickElement(OpenList, "Clicked on Open List option");
		waitForLoaderToDisappear();

	}

	//Provider selection & submission  
	public void makeProviderSelectionAndSubmitInPmlUI(List<String> providerList) throws InterruptedException {
		openPMLList();
		switchToNewWindow();
		reportLog("CURRENT URL: " + getURL());
		Assert.assertTrue((getURL()).contains("https://pml"));
		reportLog("SWITCHED TO PML APPLICATION TAB");
		clickElement(prepareWebElementWithDynamicXpath(providerSelectBtnOnPML, "1"), "First Provider on PML APP");
		clickElement(selectAnotherBtn, "Select Another Button On PML APP");
		clickElement(prepareWebElementWithDynamicXpath(providerSelectBtnOnPML, "2"), "Second Provider on PML APP");
		clickElement(nextBtn, "Next Button On PML APP");
		clickElement(pmlAppWhoMadeThisChoiceDropdown, "Who Made This Choice Dropdown");
		clickElement(pmlAppPatientValueFromDropdown, "Patient Value-From Who Made This Choice Dropdown");
		clickElement(pmlAppNameInputTextBox, "Enter Your Name Textbox");
		input(pmlAppNameInputTextBox, "Enter Your Name Textbox", "Selenium Autouser");
		waitForElementToLoad(pmlAppInputNameImage, "Input Name- Image");
		if (isElementPresent(pmlAppInputNameImage, "Input Name- Image")) {
			reportLog("Image equivalent of Name is present.");
		} else reportLog("Image equivalent of Name is not present");
		clickElement(pmlCheckboxSubmit, "Submit Checkbox");
		clickElement(pmlAppSubmitBtn, "SIGN AND SEND BUTTON");
		String successText = retrieveText(pmlTextPostSelection, "PML Successful selection text");
		Assert.assertTrue(successText.contains("Thank you for your selection"));
		Assert.assertTrue((retrieveText(prepareWebElementWithDynamicXpath(pmlAppProviderOnSuccessPage,providerList.get(0)),"Provider Name 1")).contains(providerList.get(0)));
		Assert.assertTrue((retrieveText(prepareWebElementWithDynamicXpath(pmlAppProviderOnSuccessPage,providerList.get(1)),"Provider Name 1")).contains(providerList.get(1)));
		switchToNewWindow();
		reportLog("Title of the current page = "+getdriver().getTitle());
		reportLog("CURRENT URL: " + getURL());
		closeAllSubTabs();
	}

	public void selectKeyService(String keyService) {
		// Click on Key Service Network
		clickElement(keyServiceNetworkButton, "Key Service Network button");
		waitForLoaderToDisappear();
		// Select the check box for Key service Covid-Ready
		clickElement(prepareWebElementWithDynamicXpath(keyServiceCheckbox, keyService), "Key service check box");
		// Click on Update button in the key service Modal
		clickElement(keyServicesSelectionInModalUpdateButton, "Update Button");
		waitForLoaderToDisappear();

	}

}
