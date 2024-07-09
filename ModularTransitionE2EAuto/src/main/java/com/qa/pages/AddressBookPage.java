package com.qa.pages;

import static com.qa.util.TestUtil.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.APIUtil;
import com.qa.util.FakeDataProvider;
import com.qa.util.PatientInfoBean;
import com.qa.util.TestUtil;

public class AddressBookPage extends TestBase {

	// Address Book locators 
	@FindBy(xpath = "//select[contains(@id,'organizationType')]")
	WebElement orgType;
	@FindBy(xpath = "//input[contains(@id,'organizationName')]")
	WebElement orgName;
	@FindBy(xpath = "//input[contains(@id,'url')]")
	WebElement orgURL;
	@FindBy(xpath = "//input[contains(@id,'contactName')]")
	WebElement contactName;
	@FindBy(xpath = "//input[contains(@id,'faxNumber')]")
	WebElement faxNo;
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement saveButtonAddressBook;
	@FindBy(css = "mat-dialog-container.mat-dialog-container")
	WebElement deleteModal;
	@FindBy(xpath = "//button[contains(text(),'YES')]")
	WebElement yesOptionDeleteModal;
	@FindBy(xpath = "//button[contains(text(),'Address Book')]")
	WebElement addressBook;

	//Dynamic Locator for case history events  
	String OrganizationTypeToBeDeleted = "(//*[contains(text()[normalize-space()] ,'xxxxx')])[1]";
	String OrganizationNameToBeDeleted = "//div[contains(@class,'mat-expansion-panel-body')]//*[contains(text(),'xxxxx')]|//*[contains(text(),'xxxxx')]";
	String trashIcon = "//span[contains(text(),'xxxxx')]/../../mat-icon";
	public String organizationName;

	// Initializing the Page Factory/Objects:
	public AddressBookPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}


	public void clickOnAddressBookSettingsCog() {
		clickElement(addressBook, "Address Book link under settings Cog");
		waitForPageToLoad();
		waitForLoaderToDisappear();
		Assert.assertTrue(getURL().toString().contains("addressbookweb"));
	}

	public void verifyAddressBookSmoke(String organizationID) {
		// Enter organization type 
		clickElement(orgType, "Organization Type Click");
		reportLog("Clicked on dropdown");
		selectValueByVisibleText(orgType,organizationID,"Organization Type Selection"); 

		//Enter Organization Name ( random )
		organizationName = randomStringGenerator(5)+randomNumberGenerator(2,5);
		input(orgName, "Organization Name",organizationName);
		reportLog("Organization Name is entered");

		//Enter organization URL ( random )
		input(orgURL, "Organization URL", "www."+randomStringGenerator(5)+".com");
		reportLog("Organization URL is entered");

		//Enter Contact Name ( random )
		input(contactName, "Contact Name", "AutomationSmoke"+randomStringGenerator(5));
		reportLog("Contact Name is entered");

		// Enter 10 digit Fax no ( random )
		input(faxNo, "Fax No","3321234513");
		reportLog("Fax No is entered");

		//Click on Save button
		clickElement(saveButtonAddressBook, "Save Button On Address Book");
		waitForLoaderToDisappear();
		reportLog("Organization Details are Successfully Saved");
		
		reportLog("Now proceeding for the deletion of same contact details");
		
		// Click on organization type 
		clickElement(prepareWebElementWithDynamicXpath(OrganizationTypeToBeDeleted, organizationID), "Organization ID to be deleted");

		// Click on Organization name
		clickElement(prepareWebElementWithDynamicXpath(OrganizationNameToBeDeleted, organizationName), "Organization Name to be deleted"); 

		// Wait for the trash icon to be Clickable
		waitForElementToBeClickable(prepareWebElementWithDynamicXpath(trashIcon, organizationName), "waiting for the trash icon to be clickable");
		
		// Click on the trash icon 
		clickElement(prepareWebElementWithDynamicXpath(trashIcon, organizationName), "Click on the Trash Icon");

		// Check if delete modal is displayed and click on yes
		clickElement(yesOptionDeleteModal, "Click on the yes option in the delete modal");

		// Wait for the loader to disappear
		waitForLoaderToDisappear();

		reportLog("SUCCESS !! Address Book Smoke is Complete");
	}

}