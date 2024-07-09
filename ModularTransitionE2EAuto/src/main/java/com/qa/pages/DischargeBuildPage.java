package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.FakeDataProvider;
import com.qa.util.TestUtil;

import static com.qa.util.TestUtil.*;

import java.util.*;

public class DischargeBuildPage {

	@FindBy(xpath = "//button[@class='btn update-document ng-star-inserted']")
	WebElement updateDocumentsButton;
	@FindBy(xpath = "//mat-row[contains(@id,'document-row')]//label")
	List<WebElement> formCheckboxes;
	@FindBy(xpath = "//mat-row[contains(@id,'document-row')]//mat-cell[2]//span")
	List<WebElement> formNames;
	@FindBy(id = "e-signature-password")
	public WebElement eSignatureInput;
	@FindBy(xpath = "//nh-discharge-esign-password-input-modal//button[contains(text(),\"SIGN\")]")
	public WebElement esignModalSignButton;
	@FindBy(xpath = "//select[contains(@id,'select-a-service')]")
	WebElement selectServiceTypeForFirstCard;
	@FindBy(xpath = "//div[contains(@class,'service-card')]")
	List<WebElement> totalNoOfServiceCardEle;
	@FindBy(xpath = "//button[contains(.,'OKAY')]")
	WebElement okayUpdateDocument;
	@FindBy(xpath = "//nh-discharge-document-form-table[@header='Documents']//span[contains(.,'Patient Preferences - Providers')]")
	WebElement patientPrefDoc;
	@FindBy(xpath = "//nh-discharge-document-form-table[@header='Documents']//span[contains(.,'Provider Matching Report to Patient')]")
	WebElement pmrDoc;

	@FindBy(xpath = "//button[contains(text(),'SEND A FAX')]")
	WebElement sendAFaxButton;
	@FindBy(xpath = "//div[@class ='card service-card fax-manager']//button[contains(text(),'SEND A FAX')]")
	WebElement sendFaxAfterFillingDetail;
	@FindBy(id = "gobf-fax-number")
	WebElement faxNumber;
	@FindBy(id = "gobf-return-fax-number")
	WebElement returnFaxNumber;
	@FindBy(xpath = "//button[text()='FINISH SENDING ']")
	WebElement finishSending;
	@FindBy(xpath = "//button[text()=' FAX HISTORY ']")
	public WebElement faxHistory;
	@FindBy(id = "fax-history-close")
	public WebElement faxHistoryModalClose;
	@FindBy(xpath = "//mat-table[@id='fax-history-table']/mat-header-row/mat-header-cell")
	List<WebElement> FaxHistoryHeader;
	@FindBy(xpath = "//mat-table[@id='fax-history-table']/mat-row")
	List<WebElement> FaxHistoryAllRowElement;
	@FindBy(xpath = "//button[contains(text(),' UPDATE DOCUMENTS ')]")
	// Dynamic xpaths
	String formCheckBoxByName = "//mat-row[contains(.,'xxxxx')]//label";
	public String signButtonByFormName = "//span[contains(text(),'xxxxx')]/../following-sibling::mat-cell/button[contains(text(),'SIGN')]";
	public String editButtonByFormName = "//span[contains(text(),'xxxxx')]/../following-sibling::mat-cell/button[contains(text(),'EDIT')]";
	private String specificRowLoc = "//mat-table[@id='fax-history-table']/mat-row[xxxxx]/mat-cell";
	private String optionsInSelectDropDown = "//option[contains(text(),'xxxxx')]";
	private String lOCSelect = "//select[contains(@id,'selected-loc-xxxxx')]";

	// Initializing the Page Factory/Objects:
	public DischargeBuildPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	public void clickOnFormCheckbox(String formName) {
		clickElement(prepareWebElementWithDynamicXpath(formCheckBoxByName, formName), "form check box");
	}

	public void addRemoveFormToPacket(String formName) {
		// Select the check box for the document
		clickOnFormCheckbox(formName);
		// Click on Update document
		clickOnUpdateDocumentButton();		
		reportLog("Forms selected and Added/Removed to the referral");
	}
	
	public void clickOnUpdateDocumentButton () {
		clickElement(updateDocumentsButton, "Update Button");
		waitForLoaderToDisappear();
		try {
			clickElement(okayUpdateDocument, "okay button in update document confirmation modal");
		} catch (Exception e) {
			reportLog("no confirmation modal");
		}
	}

	// method to select multiple forms
	public void clickOnFormCheckboxByIndex(int formIndex) {
		clickElement(formCheckboxes.get(formIndex), "form check box");
	}

	// method to add multiple forms to a packet
	public List<String> addRemoveFormsToPacket(int numberOfForms) {
		List<String> formNames = new ArrayList<String>();
		// Select form checkboxes and retrieve form names
		for (int i = 0; i < numberOfForms; i++) {
			clickOnFormCheckboxByIndex(i);
			formNames.add(retrieveText(this.formNames.get(i), "form name"));
		}
		// Click on Update document
		clickOnUpdateDocumentButton();
		reportLog("Forms selected and Added/Removed to the referral");
		return formNames;
	}

	public void sendAFax(String formName) {
		if (TestUtil.isElementPresent(sendAFaxButton, "Launch Type")) {
			TestUtil.clickElement(sendAFaxButton, "send a fax button click");
			waitForLoaderToDisappear();
		}
		TestUtil.clearAndInput(faxNumber, "Fax Number(s)", FakeDataProvider.getPhoneNumber());
		TestUtil.clearAndInput(returnFaxNumber, "Return Fax Number", FakeDataProvider.getPhoneNumber());
		waitForLoaderToDisappear();
		TestUtil.scrollPageToElementAndClick(
				TestUtil.prepareWebElementWithDynamicXpath(formCheckBoxByName, formName),
				"select a form");
		if (TestUtil.isEnabled(sendFaxAfterFillingDetail, "Fax button enable")) {
			TestUtil.clickElement(sendFaxAfterFillingDetail, "Send a fax button click");
			TestUtil.clickElement(finishSending, "Finish Sending a Fax");
			waitForLoaderToDisappear();
		}
	}

	public List<LinkedHashMap<String, String>> readFaxHistoryDetails(){
		//Let get header first
		List<String> allFaxHistoryHeaderName = new ArrayList<String>();
		for (WebElement header : FaxHistoryHeader){
			String headerName = header.getText();
			allFaxHistoryHeaderName.add(headerName);
		}
		// Each row will be a key value pair. So we will use LinkedHashMap so that order
		// can be retained.
		// All map will be added to a list.
		List<LinkedHashMap<String, String>> allTableData = new ArrayList<LinkedHashMap<String, String>>();
		// Get total rows count
		for (int i = 1; i <= FaxHistoryAllRowElement.size(); i++){
			// Getting specific row with each iteration and Locating only cells of specific row.
			List<WebElement> allColumnsEle = setFaxHistoryAllColumnElement(i);
			// Creating a map to store key-value pair data. It will be created for each
			// iteration of row
			LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
			// Iterating each cell
			for (int j = 0; j < allColumnsEle.size(); j++) {
				// Getting cell value
				String cellValue = allColumnsEle.get(j).getText();
				// We will put in to map with header name and value with iteration
				// Get jth index value from allHeaderNames and jth cell value of row
				eachRowData.put(allFaxHistoryHeaderName.get(j), cellValue);
			}
			// After iterating row completely, add in to list.
			allTableData.add(eachRowData);
		}
		reportLog(allTableData.toString());
		return allTableData;
	}

	public List<WebElement> setFaxHistoryAllColumnElement(int number){
		return TestUtil.prepareWebElementsWithDynamicXpath(specificRowLoc,Integer.toString(number));

	}

	public void selectServiceType(String serviceType) {
		TestUtil.clickElement(selectServiceTypeForFirstCard, "click on service Drop down");
		TestUtil.clickElement(TestUtil.prepareWebElementWithDynamicXpath(optionsInSelectDropDown, serviceType),
				"Select Service Type");
		waitForLoaderToDisappear();
	}


	public void selectLOCSNF(String LOCOptionVal) {
		int totalServiceCard = totalNoOfServiceCardEle.size();
		int index = totalServiceCard - 1;
		TestUtil.clickElement(TestUtil.prepareWebElementWithDynamicXpath(lOCSelect, Integer.toString(index)), "Click on LOC DropDown");
		TestUtil.clickElement(TestUtil.prepareWebElementWithDynamicXpath(optionsInSelectDropDown, LOCOptionVal), "Select LOC ");
		waitForPageToLoad();
		waitForLoaderToDisappear();
	}

	public void verifyPresenceOfPMLDocs(){
		Assert.assertTrue(isElementPresent(patientPrefDoc,"Patient Preference Document"));
		reportLog("Patient Preference Document is present in build page");
		retrieveText(patientPrefDoc,"PatPrefDoc Name");
		Assert.assertTrue(isElementPresent(pmrDoc,"PMR Document"));
		reportLog("PMR Document is present in build page");
		retrieveText(pmrDoc, "PMR Doc Name");
	}
}
