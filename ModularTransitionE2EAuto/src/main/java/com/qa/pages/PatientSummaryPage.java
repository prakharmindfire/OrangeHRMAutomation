package com.qa.pages;

import static com.qa.util.TestUtil.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.qa.base.TestBase;
import com.qa.util.APIUtil;
import com.qa.util.FakeDataProvider;



public class PatientSummaryPage extends TestBase {

	//Patient Summary Locators 
	@FindBy(xpath = "//button[contains(text(),' Patient Summary ')]")
	WebElement patientSummaryOption;
	@FindBy(xpath = "//button[contains(text(),' IBF Cover Sheet ')]")
	WebElement IBFOption;
	@FindBy(xpath = "//div[@id='patient-summary-modal']//h1/strong | //div[@id='patient-summary']//h1/strong")
	WebElement patientSummaryModalTitle;
	@FindBy(xpath = "//div[@id='patient-summary']//h2/strong|//div[@id='patient-summary-modal']//h2/strong")
	WebElement patientSummaryModalSubTitle;
	@FindBy(xpath = "//mat-dialog-container[contains(@id,'mat-dialog')]|.//*[@id='patient-summary-modal']")
	WebElement patientSummaryModal;
	@FindBy(xpath = "//button[contains(@class,'cs-blue-btn pull-right')]")
	WebElement closeButtonOnPatientSummary;


	// Initializing the Page Factory/Objects:
	public PatientSummaryPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}


	public void verifyPatientSummary() {
		//Scroll to Top 
		scrollToTop();
		
		// Assert the modal title & modal subtitle in Patient Summary
		Assert.assertEquals(patientSummaryModalTitle.getText(),"Patient Detailed Summary");
		Assert.assertEquals(patientSummaryModalSubTitle.getText(),"Snapshot of Detailed Summary:");
		
		// Click on Outside of modal and assert the closing of patient summary
		clickElement(closeButtonOnPatientSummary, "Close Patient Summary Modal");
		waitForLoaderToDisappear();
		
		boolean result = isElementPresent(patientSummaryModal, "Patient Summary Modal");
		Assert.assertEquals(result, false);
		reportLog("Pass : Patient Summary modal is successfully closed");

	}

}