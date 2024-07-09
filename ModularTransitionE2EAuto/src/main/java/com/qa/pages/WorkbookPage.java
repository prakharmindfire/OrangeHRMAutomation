package com.qa.pages;

import static com.qa.util.TestUtil.*;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.PatientInfoBean;

public class WorkbookPage extends TestBase {

	// Page Factory - OR:
	// Add patient locators
	@FindBy(xpath = "//button[contains(text(),'Create New Patient')]")
	WebElement createNewPatientButton;
	@FindBy(id = "patient-details-patient-first-name")
	WebElement patientDetailsFirstName;
	@FindBy(id = "patient-details-patient-middle-name")
	WebElement patientDetailsMiddleName;
	@FindBy(id = "patient-details-patient-last-name")
	WebElement patientDetailsLastName;
	@FindBy(id = "patient-details-mrn")
	WebElement patientDetailsMRN;
	@FindBy(id = "patient-details-account-number")
	WebElement patientDetailsACC;
	@FindBy(id = "patient-details-patient-address-1")
	WebElement patientDetailsAddressLine1;
	@FindBy(id = "patient-details-patient-address-2")
	WebElement patientDetailsAddressLine2;
	@FindBy(id = "patient-details-patient-city")
	WebElement patientDetailsCity;
	@FindBy(xpath = "//select[contains(@id,'patient-details-patient-state')]")
	WebElement patientDetailsState;
	@FindBy(id = "patient-details-patient-zip")
	WebElement patientDetailsZip;
	@FindBy(xpath = "//select[contains(@id,'workbook-add-patient-unit')]")
	WebElement patientDetailsUnit;
	@FindBy(id = "patient-details-room")
	WebElement patientDetailsRoom;
	@FindBy(id = "patient-details-bed")
	WebElement patientDetailsBed;
	@FindBy(xpath = "//nh-datepicker[@inputid='patient-details-dob']//button")
	WebElement patientDetailsDOBDatepicker;
	@FindBy(xpath = "//nh-datepicker[@inputid='patient-details-dob']//input")
	WebElement patientDetailsDOBInput;
	@FindBy(xpath = "//nh-datepicker[@inputid='workbook-add-patient-admitDate']//button")
	WebElement patientDetailsAdmitDateDatepicker;
	@FindBy(xpath = "//nh-datepicker[@inputid='workbook-add-patient-admitDate']//input")
	WebElement patientDetailsAdmitDateInput;
	@FindBy(xpath = "//nh-datepicker[@inputid='workbook-add-patient-estimatedDischargeDate']//button")
	WebElement patientDetailsEDDDatepicker;
	@FindBy(xpath = "//nh-datepicker[@inputid='workbook-add-patient-estimatedDischargeDate']//input")
	WebElement patientDetailsEDDInput;
	@FindBy(id = "patient-details-attending-phys-first-name")
	WebElement attendingPhysicianFirstName;
	@FindBy(id = "patient-details-attending-phys-last-name")
	WebElement attendingPhysicianLastName;
	@FindBy(id = "patient-details-primary-care-phys-first-name")
	WebElement primaryCarePhysicianFirstName;
	@FindBy(id = "patient-details-primary-care-phys-last-name")
	WebElement primaryCarePhysicianLastName;
	@FindBy(id = "patient-details-gender")
	WebElement patientDetailsGender;
	@FindBy(id = "workbook-add-primary-diagnosis")
	WebElement patientDetailsPrimaryDiagnosis;
	@FindBy(id = "workbook-add-secondary-diagnosis")
	WebElement patientDetailsSecondaryDiagnosis;
	@FindBy(id = "workbook-add-patient-admitType")
	WebElement patientDetailsAdmitType;
	@FindBy(id = "patient-details-other-primary-diagnosis")
	WebElement otherPrimaryDiagnosis;
	@FindBy(id = "patient-details-other-secondary-diagnosis")
	WebElement otherSecondaryDiagnosis;
	@FindBy(id = "patient-details-payer-0-name")
	WebElement patientDetailsPayer1name;
	@FindBy(id = "patient-details-plan-0-id")
	WebElement patientDetailsP1PlanID;
	@FindBy(xpath = "//button[(contains(text(),'SAVE'))]")
	WebElement saveButtonAddAPatient;
	@FindBy(id = "search-workbook")
	WebElement workbooksearchfield;
	@FindBy(xpath = "//button[@id='btn-search-workbook']")
	WebElement workbooksearchbutton;
	@FindBy(xpath = "//div[contains(@class,'mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin')]")
	WebElement searchedPatCheckbox;
	@FindBy(id="discharge-header-workbook-button")
	WebElement workBookButtoninHeader;
	@FindBy(xpath="//label[@for='patient-0-input']/div/input")
	WebElement checkboxforfirstpatientresult;
	@FindBy(id="workbook-action-dropdown")
	WebElement actiondropdown;
	@FindBy(xpath="//button[contains(.,' Change Primary Contact ')]")
	WebElement changePrimaryContactOnActionDropdown;
	@FindBy(xpath="//input[@id='search-case-manager']")
	WebElement searchFieldOnChangePrimaryContact;
	@FindBy(xpath="//button[@id='pc-save-btn']")
	WebElement saveButtonOnChangePrimaryContactModal;
	@FindBy(xpath="//button[contains(.,'NO')]")
	WebElement noSubscribeButtonOnChangePrimaryContact;
	@FindBy(xpath = "//button[contains(text(),'SEARCH ALL')]")
	WebElement searchAll;
	@FindBy(xpath = "//select[@formcontrolname='patientType']")
	WebElement searchDropdown;
	@FindBy(xpath = "//input[contains(@id,'search-patient')]")
	WebElement searchPatientFieldInWorkbook;
	@FindBy(xpath = "//div[contains(.,'Search & Assign Patients')]//button[text()='SEARCH']")
	WebElement searchPatientButtonInWorkbook;
	@FindBy(xpath="//label[@for='patient-csi-0-input']")
	WebElement searchedPatientCheckBox;
	@FindBy(xpath = "//button[contains(text(),'BECOME PRIMARY CONTACT')]")
	WebElement becomePrimaryContactButton;
	@FindBy(xpath = "//button[contains(text(),'CLOSE')]")
	WebElement closeButton;
	@FindBy(xpath = "//mat-dialog-container[contains(.,'notified about the case')]")
	WebElement notificationModal;
	@FindBy(xpath = "//button[contains(@id,'update-primary-confirm-no')]")
	WebElement notificationModalNoOption;
	@FindBy(xpath = "//nh-discharge-add-from-csi-modal//button[text()='SEARCH LEGACY CAREPORT DISCHARGE']")
	WebElement legacySearchBtn;
	@FindBy(xpath = "//*[contains(@id,'build')]")
	WebElement buildPageFromWorkbook;
	//email verification modal locators
	@FindBy(xpath = "//div[contains(@class,'change-email-modal-size')] | //mat-dialog-container//nh-change-email")
	WebElement emailChangeModal;
	@FindBy(xpath = "//div[contains(@class,'change-email-modal-size')]//div[@class='description'] | //nh-change-email//form")
	WebElement emailChangeModalBody;
	final String emailChangeModalText = "Please enter an account recovery email. Your recovery email should be an email address you use regularly.";
	@FindBy(xpath = "//input[@type='email'] | //input[@id='changeEmailForm_new_email']")
	WebElement inputChangeAccountRecoveryEmail;
	@FindBy(xpath = "//button[text()='VERIFY'] | //nh-change-email//button[text()='Verify']")
	WebElement emailChangeModalVerifyBtn;
	@FindBy(xpath = "//div[contains(@class,'change-email-modal-size')]//h2 | //nh-change-email//div/div")
	WebElement emailChangeModalTitle;
	@FindBy(xpath = "//div[contains(@class,'verification-code-modal-size')] | //mat-dialog-container//nh-verification-code")
	WebElement emailCodeVerificationModal;
	@FindBy(xpath = "//div[contains(@class,'validate-email-modal-size')] | //mat-dialog-container/nh-email-validation")
	WebElement emailVerificationModal;
	@FindBy(xpath = "//nh-email-validation//div[@class='nh-modal-title'] | //div[contains(@class,'validate-email-modal-size')]//div[@class='modal-header']/h2")
	WebElement emailVerificationModalTitle;
	@FindBy(xpath = "//nh-email-validation//mat-dialog-content | //div[contains(@class,'validate-email-modal-size')]//div[@class='modal-content']")
	WebElement emailVerificationModalBody;
	@FindBy(xpath = "//nh-email-validation//button[normalize-space()='Change email address'] | //button[text()='CHANGE EMAIL ADDRESS']")
	WebElement emailVerificationModalChangeEmailAddBtn;
	@FindBy(xpath = "//nh-email-validation//button[normalize-space()='Verify'] | //button[text()='VERIFY']")
	WebElement emailVerificationModalVerifyBtn;
	@FindBy(xpath = "//nh-verification-code//div[@class='nh-modal-title'] | //div[contains(@class,'verification-code-modal-size')]//h2")
	WebElement emailCodeVerificationModalTitle;
	@FindBy(xpath = "//nh-verification-code//button[normalize-space()='Send new verification code'] | //button[text()='SEND NEW VERIFICATION CODE']")
	WebElement emailCodeVerificationModalSendNewVerifyCodeBtn;
	@FindBy(xpath = "//nh-verification-code//button[normalize-space()='Verify'] | //button[text()='VERIFY']")
	WebElement emailCodeVerificationModalVerifyBtn;
	@FindBy(xpath = "//nh-verification-code//form | //div[contains(@class,'verification-code-modal')]//div[@class='modal-content']")
	WebElement emailCodeVerificationModalBody;
	@FindBy(xpath = "//input[@id='verification_code'] | //div[contains(@class,'verification-code-modal')]//input")
	WebElement inputVerificationCode;
	
	// Dynamic Locator
	String searchedPatConnectTabButton = "//span[contains(.,'xxxxx')]/ancestor::div/div//button[contains(.,'Connect')]";
	public String patientOnWorkBook = "//span[contains(.,'xxxxx')]";
	String radioButtonForsearchedPrimaryContact = "//div[@class='mat-radio-label-content'][contains(.,'xxxxx')]/ancestor::label/div/input";
	String patientPageLink = "//span[contains(.,'xxxxx')]/ancestor::nh-discharge-patient-card//button[contains(.,'xxxxx')]";

	// Initializing the Page Factory/Objects:
	public WorkbookPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/**
	 * Actions/Methods on Workbook page:
	 * 
	 * @throws InterruptedException
	 **/
	public PatientInfoBean addAPatientWithMandatoryFieldsOnly() throws InterruptedException, IOException {
		PatientInfoBean patientDetails = new PatientInfoBean();
		
		// click on Create New Patient button
		clickElement(createNewPatientButton, "Create New Patient button");
		waitForLoaderToDisappear();

		// enter details
		patientDetails.setFirstName(input(patientDetailsFirstName, "first name", "AutoF" + randomStringGenerator(5)));

		patientDetails.setLastName(input(patientDetailsLastName, "last name", "AutoL" + randomStringGenerator(5)));

		patientDetails.setReferralName(patientDetails.getLastName() + ",  " + patientDetails.getFirstName());

		patientDetails.setFullName(patientDetails.getFirstName() + " " + patientDetails.getLastName());

		patientDetails
		.setMRN(input(patientDetailsMRN, "MRN", Long.toString(randomNumberGenerator(10000000, 99999999))));

		patientDetails.setAccountNumber(
				input(patientDetailsACC, "account number", Long.toString(randomNumberGenerator(10000000, 99999999))));

		patientDetails.setDOB(input(patientDetailsDOBInput, "dob", getRandomDate(new Date("1/1/1970"), new Date())));
		patientDetails.setAge(calculateAge(patientDetails.getDOB()));

		patientDetails.setAdmitDate(input(patientDetailsAdmitDateInput, "admit date",
				getRandomDate(new Date(patientDetails.getDOB()), new Date())));

		patientDetails.setEstimatedDischargeDate(input(patientDetailsEDDInput, "estimated discharge date",
				getRandomDate(new Date(patientDetails.getAdmitDate()), new Date("3/31/2030"))));

		patientDetails.setGender(selectValueByVisibleText(patientDetailsGender, "Male", "gender dropdown"));

		patientDetails.setPrimaryDiagnosis(selectValueByVisibleText(patientDetailsPrimaryDiagnosis,
				"ALS", "primary diagnosis dropdown"));

		patientDetails.setSecondaryDiagnosis(
				selectValueByVisibleText(patientDetailsSecondaryDiagnosis, "ARDS", "secondary diagnosis dropdown"));

		patientDetails
		.setAdmitType(selectValueByVisibleText(patientDetailsAdmitType, "ATC", "admit type dropdown"));

		patientDetails.setPayer1Name(input(patientDetailsPayer1name, "payer1 name", randomStringGenerator(5)));

		patientDetails.setPayer1PlanID(input(patientDetailsP1PlanID, "payer1 plan ID", randomStringGenerator(5)));
		
		patientDetails.setNotifyDate(getFormattedCurrentTimestamp("MM/dd/yyyy", "UTC"));

		// wait for save button to be enabled/clickable
		savePatient();
		return patientDetails;

	}

	public PatientInfoBean addAPatient() throws InterruptedException, IOException {
		PatientInfoBean patientDetails = new PatientInfoBean();

		// click on Create New Patient button
		clickElement(createNewPatientButton, "Create New Patient button");
		waitForLoaderToDisappear();

		// enter details
		patientDetails.setFirstName(input(patientDetailsFirstName, "first name", "AutoF" + randomStringGenerator(5)));

		patientDetails.setMiddleName(input(patientDetailsMiddleName, "middle name", "AutoF" + randomStringGenerator(5)));

		patientDetails.setLastName(input(patientDetailsLastName, "last name", "AutoL" + randomStringGenerator(5)));

		patientDetails.setReferralName(patientDetails.getLastName() + ",  " + patientDetails.getFirstName());

		patientDetails.setFullName(patientDetails.getFirstName() + " " + patientDetails.getMiddleName() + " " + patientDetails.getLastName());

		patientDetails
		.setMRN(input(patientDetailsMRN, "MRN", Long.toString(randomNumberGenerator(10000000, 99999999))));

		patientDetails.setAccountNumber(
				input(patientDetailsACC, "account number", Long.toString(randomNumberGenerator(10000000, 99999999))));

		patientDetails.setAddressLine1(input(patientDetailsAddressLine1, "address line 1", "address line 1"));
		patientDetails.setAddressLine2(input(patientDetailsAddressLine2, "address line 2", "address line 2"));
		patientDetails.setCity(input(patientDetailsCity, "city", "Curaspan City"));
		patientDetails.setState(selectValueByVisibleText(patientDetailsState, "ZZ", "state"));
		patientDetails.setZip(input(patientDetailsZip, "zip", "99999"));
		patientDetails.setUnit(selectValueByVisibleText(patientDetailsUnit, "Emergency", "unit"));
		patientDetails.setRoom(input(patientDetailsRoom, randomStringGenerator(3), "room"));
		patientDetails.setBed(input(patientDetailsBed, randomNumberGenerator(1, 999)+"", "bed"));

		patientDetails.setAttendingPhysicianFirstName(input(attendingPhysicianFirstName, randomStringGenerator(5), "attending physician first name"));
		patientDetails.setAttendingPhysicianLastName(input(attendingPhysicianLastName, randomStringGenerator(5), "attending physician last name"));
		patientDetails.setPrimaryCarePhysicianFirstName(input(primaryCarePhysicianFirstName, randomStringGenerator(5), "primary care physician first name"));
		patientDetails.setPrimaryCarePhysicianLastName(input(primaryCarePhysicianLastName, randomStringGenerator(5), "primary care physician last name"));

		patientDetails.setOtherPrimary(input(otherPrimaryDiagnosis, randomStringGenerator(5), "other primary diagnosis"));
		patientDetails.setOtherSecondary(input(otherSecondaryDiagnosis, randomStringGenerator(5), "other secondary diagnosis"));

		patientDetails.setDOB(input(patientDetailsDOBInput, "dob", getRandomDate(new Date("1/1/1970"), new Date())));

		patientDetails.setAdmitDate(input(patientDetailsAdmitDateInput, "admit date",
				getRandomDate(new Date(patientDetails.getDOB()), new Date())));

		patientDetails.setEstimatedDischargeDate(input(patientDetailsEDDInput, "estimated discharge date",
				getRandomDate(new Date(patientDetails.getAdmitDate()), new Date("3/31/2030"))));

		patientDetails.setGender(selectValueByVisibleText(patientDetailsGender, "Male", "gender dropdown"));

		patientDetails.setPrimaryDiagnosis(selectValueByVisibleText(patientDetailsPrimaryDiagnosis,
				"Fracture, lower extremity", "primary diagnosis dropdown"));

		patientDetails.setSecondaryDiagnosis(
				selectValueByVisibleText(patientDetailsSecondaryDiagnosis, "Asthma", "secondary diagnosis dropdown"));

		patientDetails
		.setAdmitType(selectValueByVisibleText(patientDetailsAdmitType, "Emergency", "admit type dropdown"));

		patientDetails.setPayer1Name(input(patientDetailsPayer1name, "payer1 name", randomStringGenerator(5)));

		patientDetails.setPayer1PlanID(input(patientDetailsP1PlanID, "payer1 plan ID", randomStringGenerator(5)));
		
		savePatient();
		return patientDetails;

	}
	
	//save patient
	public void savePatient() throws InterruptedException {
		// wait for save button to be enabled/clickable
		waitForElementToBeClickable(saveButtonAddAPatient, "save button");
		Thread.sleep(1000);
		
		// click on save
		clickElement(saveButtonAddAPatient, "save button");
		waitForLoaderToDisappear();
	}
	
	//update patient name
	public void updatePatientFirstName(PatientInfoBean patientDetails) {
		patientDetails.setFirstName(clearAndInput(patientDetailsFirstName, "first name", "AutoF" + randomStringGenerator(5)));
		patientDetails.setReferralName(patientDetails.getLastName() + ",  " + patientDetails.getFirstName());
	}


	// Search Patient on Workbook and click
	public void searchPatientOnWorkbookAndGoToDetails(String patientName) {

		searchPatientOnWorkbookOnly(patientName);
		clickElement(prepareWebElementWithDynamicXpath(patientOnWorkBook, patientName), "Searched Patient Name link");
		waitForLoaderToDisappear();
		reportLog("The Patient is searched and Clicked ---> SUCCESS!");

	}
	
	

	// Only Search Patient on WorkBook
	public void searchPatientOnWorkbookOnly(String patientName) {

		clearAndInput(workbooksearchfield, "Search field onWorkbook", patientName);
		clickElement(workbooksearchbutton, "Search butotn on workbook search");
		waitForLoaderToDisappear();
		reportLog("The Patient is searched  ---> SUCCESS!");

	}
	
	public void navigateToBuildPageFromWorkbook() {
		clickElement(buildPageFromWorkbook, "Navigating to Build Page from workbook");
		waitForLoaderToDisappear();
		reportLog("Navigation to Build Page from Workbook  ---> SUCCESS!");

	}

	/* This function is to handle the pop up asking for notifying to old Primary contact 
	 * while we try to become primary contact for a searched patient in 
	 * patient census
	 */
	public void handleKeepPrimayContactNotifiedModal() {
		boolean result = isElementPresent(notificationModal, "Notification Modal");
		if(result ==true) {
			reportLog("Notify primary contact modal is displayed");
			reportLog("Clicking on Not to Notify Option from the modal");
			clickElement(notificationModalNoOption, "Clicked on Not to Notify Option");
			
		}else {
			reportLog("Notify primary contact modal is not displayed");
		}
	}
	
	/**
	 * 
	 * @param searchCriteria - Acc/MRN/Patient Name
	 * @throws InterruptedException 
	 */
	public void searchAllAndAddToWorkBook(String searchCriteria) throws InterruptedException {
		clickElement(searchAll, "Search ALL Button");
		selectValueByVisibleText(searchDropdown,"All Patients", "select a type of search from dropdown");
		clearAndInput(searchPatientFieldInWorkbook, "Providing Patient Detail in field",
				searchCriteria);
		Thread.sleep(4000); // sometimes searching quickly after creating patient does not return any result
		clickElement(searchPatientButtonInWorkbook, "Search Patients Button");
		waitForLoaderToDisappear();
		clickElement(searchedPatientCheckBox, "patient checkbox");
		clickElement(becomePrimaryContactButton, "Become Primary Contact");
		//Handle the notify modal
		handleKeepPrimayContactNotifiedModal();
		reportLog("Modal is successfully handled");
		waitForLoaderToDisappear();
		clickElement(closeButton, "Close Button");
		waitForLoaderToDisappear();
	}

//	// Click on Connect Tab for a Searched Patient on Workbook
//	public void navigateToConnectTabForSearchedPatient(String patientName) {
//
//		clickElement(prepareWebElementWithDynamicXpath(searchedPatConnectTabButton, patientName),
//				"Connect Tab for Patient on Wrokbook");
//		waitForLoaderToDisappear();
//		reportLog("The Connect Tab is clicked for " + patientName + " ---> SUCCESS!");
//
//	}

	//Click on workBook Button on header
	public void clickOnWorkBookButtonOnHeader() {

		clickElement(workBookButtoninHeader, "Workbook Button on Header");
	}

	//Search Patient in WorkBook and Update the Primary contact 
	public void searchPatientAndChangePrimaryContact(String referralName, String updatedPrimaryContact) {

		searchPatientOnWorkbookOnly(referralName);
		clickElement(checkboxforfirstpatientresult, "check box for first searched patient");
		clickElement(actiondropdown, "Action Button Dropdown");
		clickElement(changePrimaryContactOnActionDropdown, "change Primary contact option on action dropdown");
		clearAndInput(searchFieldOnChangePrimaryContact, "Updated Primary conact case mgr", updatedPrimaryContact);
		clickElement(prepareWebElementWithDynamicXpath(radioButtonForsearchedPrimaryContact, updatedPrimaryContact), "Radio button for searched primary contact");
		clickElement(saveButtonOnChangePrimaryContactModal, "save button on change primary contact modal");
		clickElement(noSubscribeButtonOnChangePrimaryContact, "NO for Subscriber on Change Primary contact");
		waitForLoaderToDisappear();



	}
	
	/**
	 * @param patientName
	 * @param page        - "Start Workflow","Build","Connect","Refer","Discharge"
	 */
	public void navigateToPatientPage(String patientName, String page) {
		String temp = patientPageLink.replaceFirst("xxxxx", patientName);
		clickElement(prepareWebElementWithDynamicXpath(temp, page), page + " for " + patientName);
		if (!page.contains("Workflow")) {
			waitForPageToLoad();
		}
		waitForLoaderToDisappear();
	}

	public void navigateToLegacySearchPage() {
		waitForElementToLoad(searchAll,"Search All Button");
		clickAndWaitForPageLoad(searchAll,"Search All Button");
		waitForElementToLoad(legacySearchBtn,"Legacy Search Button");
		clickAndWaitForPageLoad(legacySearchBtn,"Legacy Search Button");
		waitForLoaderToDisappear();
	}

	public void changeUserRecoveryEmailAndVerify(String newEmail) {
		clickOnChangeEmailAddressButton();
		verifyEmailChangeModalUI();
		clearAndInput(inputChangeAccountRecoveryEmail, "Account recover email input field", newEmail);
		clickAndWaitForPageLoad(emailChangeModalVerifyBtn, "Verify Button");
		waitForLoaderToDisappear();
		waitForElementToLoad(emailCodeVerificationModal, "Email Code Verification Modal");
	}

	public void verifyEmailChangeModalUI() {
		reportLog("Verifying the Email Address Change Modal UI");
		waitForElementToLoad(emailChangeModal, "Email Change Modal");
		assertTrue(retrieveText(emailChangeModalTitle, "Email Change Modal Title").contains("Email Verification"));
		assertTrue(retrieveText(emailChangeModalBody, "Email Change Modal Body").contains(emailChangeModalText));
		assertTrue(isElementPresent(inputChangeAccountRecoveryEmail, "Account recovery email input Field"));
	}

	public void verifyEmailVerificationCode(String code) {
		reportLog("Inputting the verification code to verify email address");
		clearAndInput(inputVerificationCode, "Verification Code Input", code);
		clickElement(emailCodeVerificationModalVerifyBtn, "Verify Button");
		waitForElementToDisappear(emailCodeVerificationModal, "Email Code Verification Modal");
		waitForElementToLoad(searchAll, "SEARCH ALL button on workbook");
		assertTrue(isElementPresent(searchAll, "Search All button- workbook"));
		reportLog("Successfully verified the email. [PASS] ");
	}

	public void verifyEmailVerificationModalUI(String userEmail) {
		reportLog("Verifying the Email Verification modal UI");
		final String emailVerificationModalBodyText = "Your email address " + userEmail;
		final String emailVerificationModalBodyText2 = "has not been verified. For your protection, your account must have a verified email address.";
		waitForElementToLoad(emailVerificationModal, "Email Verification Modal");
		assertTrue(retrieveText(emailVerificationModalTitle, "Email Verification Modal Title").contains("Email Verification"));
		assertTrue(retrieveText(emailVerificationModalBody, "Email Verification Modal Body").contains(emailVerificationModalBodyText));
		assertTrue(retrieveText(emailVerificationModalBody, "Email Verification Modal Body").contains(emailVerificationModalBodyText2));
		assertTrue(isElementPresent(emailVerificationModalChangeEmailAddBtn, "Change Email Address Button"));
	}

	public void clickOnVerifyButtonForEmailVerification() {
		reportLog("Verifying the Email Address using OME");
		if (isElementPresent(emailVerificationModal, "Email Verification Modal")) {
			clickAndWaitForPageLoad(emailVerificationModalVerifyBtn, "Verify Button");
		} else reportLog("Email Verification Modal Not displayed");
	}


	public void clickOnChangeEmailAddressButton() {
		reportLog("Verifying the Email Address using OME");
		waitForElementToLoad(emailVerificationModal, "Email Verification Modal");
		if (isElementPresent(emailVerificationModalChangeEmailAddBtn, "Change Email Address Btn")) {
			clickAndWaitForPageLoad(emailVerificationModalChangeEmailAddBtn, "Change Email Address Btn");
		} else reportLog("Email Verification Modal Not displayed");
	}

	public void verifyEmailCodeVerificationModalUI(String userEmail) {
		reportLog("Verifying the Email Code Verification Modal UI");
		final String emailCodeVerificationModalBodyText = "We sent a verification code to " + userEmail ;
		waitForElementToLoad(emailCodeVerificationModal, "Email Code Verification Modal");
		assertTrue(retrieveText(emailCodeVerificationModalBody, "Email Code Verification Modal Body").contains(emailCodeVerificationModalBodyText));
		assertTrue(retrieveText(emailCodeVerificationModalTitle, "Email Code Verification Modal Title").contains("Email Verification"));
		assertTrue(isElementPresent(emailCodeVerificationModalSendNewVerifyCodeBtn, "Send New Verification Code Btn"));
		assertTrue(isElementPresent(emailCodeVerificationModalVerifyBtn, "Verify Button"));
	}

}