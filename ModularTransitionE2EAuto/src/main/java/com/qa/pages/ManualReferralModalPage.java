package com.qa.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.PatientInfoBean;
import com.qa.util.TestUtil;

import static com.qa.util.TestUtil.*;

public class ManualReferralModalPage extends TestBase {

	@FindBy(xpath = "//*[@id='other-referral-source']//span[contains(text(),'Other Referral Source')]")
	WebElement otherRefSourceCheckBox;
	// Page Factory - OR:
	// locators
	@FindBy(xpath = "//button[contains(.,'Add New Referral')]")
	WebElement addNewReferralButton;
	@FindBy(xpath = "//label[contains(.,'Notify Date')]/..//button")
	WebElement notifyDate;
	@FindBy(id = "pai_firstName")
	WebElement firstNameInput;
	@FindBy(id = "pai_lastName")
	WebElement lastNameInput;
	@FindBy(xpath = "//label[contains(.,'* Date of Birth')]/following-sibling::div//input")
	WebElement dobInput;
	@FindBy(id = "rsi_refOrgSelect")
	WebElement referralSourceDropdown;
	@FindBy(id = "sourceOrg")
	WebElement referralSourceInput;
	@FindBy(id = "rsi_locSelect")
	WebElement requestedLOCDropdown;
	@FindBy(xpath = "//div[contains(@class,'new-referral')]//select[@name='refOrgSelect']//option")
	List<WebElement> referralSourcePredefinedOptions;
	@FindBy(id = "providersSearch")
	WebElement providersSearchInput;
	@FindBy(xpath = "//label[contains(.,'* Admit Date')]/following-sibling::nh-datepicker//input")
	WebElement admitDateInput;
	@FindBy(id = "pi_primaryPayerName")
	WebElement primaryPayerNameInput;
	@FindBy(id = "pi_primaryPayerPlanId")
	WebElement primaryPayerMemberIDInput;
	@FindBy(id = "pi_primaryPayerGroupNumber")
	WebElement primaryPayerGroupInput;
	@FindBy(id = "pi_primaryPayerSubscriberFirstName")
	WebElement primaryPayerSubscriberFirstNameInput;
	@FindBy(id = "pi_primaryPayerSubscriberLastName")
	WebElement primaryPayerSubscriberLastNameInput;
	@FindBy(id = "di_gender")
	WebElement genderDropdown;
	@FindBy(xpath = "//button[@angularticsaction='SaveManualReferral']")
	WebElement saveManualReferralButton;
	@FindBy(xpath = "//input[@name='ssn']")
	WebElement SSN;

	@FindBy(xpath = "//input[@name='address']")
	WebElement homeAddress;
	@FindBy(xpath = "//input[@name='city']")
	WebElement city;
	@FindBy(xpath = "//input[@name='state']")
	WebElement state;
	@FindBy(xpath = "//input[@name='zip']")
	WebElement zip;
	@FindBy(xpath = "//input[@name='phone1']")
	WebElement primaryPhoneNumber;
	@FindBy(xpath = "//input[@name='phone2']")
	WebElement alternativePhoneNumber;
	@FindBy(xpath = "//select[@name='patientClass']")
	WebElement patientClass;
	@FindBy(css = "[name='admitType']")
	WebElement admitType;
	@FindBy(css = "[name='patientLocation']")
	WebElement patientLocation;

	@FindBy(css = "[name='primaryFirstName']")
	WebElement primaryPhysicianFirstName;

	@FindBy(xpath = "//input[@name='primaryFirstName']/..//label")
	WebElement primaryPhysicianFirstNameLabel;

	@FindBy(css = "[name='primaryLastName']")
	WebElement primaryPhysicianLastName;

	@FindBy(xpath = "//input[@name='primaryLastName']/..//label")
	WebElement primaryPhysicianLastNameLabel;

	@FindBy(css = "[name='attendingFirstName']")
	WebElement attendingPhysicianFirstName;
	@FindBy(xpath = "//input[@name='attendingFirstName']/..//label")
	WebElement attendingPhysicianFirstNameLabel;

	@FindBy(css = "[name='attendingLastName']")
	WebElement attendingPhysicianLastName;

	@FindBy(css = "//input[@name='attendingLastName']/..//label")
	WebElement attendingPhysicianLastNameLabel;

	@FindBy(xpath = "///button[contains(.,'CANCEL')]")
	WebElement cancenButton;
	@FindBy(xpath = "//select[@formcontrolname='sourceOrg']")
	WebElement referralSourcePredefinedSource;
	@FindBy(css = "label[for='sourceOrg']")
	WebElement referralSourceLabel;
	@FindBy(xpath = "//nh-intake-nr-team-assignments//h3[contains(text(),'Team Assignments')]")
	WebElement teamsAssignmentHeader;
	@FindBy(id = "teamAssignmentsSearch")
	WebElement teamsAssignmentInput;

	@FindBy(css = "[name='spousePerson']")
	WebElement contact;

	@FindBy(xpath = "//input[@name='spousePerson']/..//label")
	WebElement contactLabel;

	@FindBy(css = "[name='contactPhone']")
	WebElement contactPhone;

	@FindBy(xpath = "//input[@name='contactPhone']/..//label")
	WebElement contactPhoneLabel;

	@FindBy(css = "[name='kinFirstName']")
	WebElement nextOfKinFirstName;

	@FindBy(xpath = "//input[@name='kinFirstName']/..//label")
	WebElement nextOfKinFirstNameLabel;

	@FindBy(css = "[name='kinLastName']")
	WebElement nextOfKinLastName;

	@FindBy(xpath = "//input[@name='kinLastName']/..//label")
	WebElement nextOfKinLastNameLabel;

	@FindBy(css = "[name='kinPhone']")
	WebElement nextOfKinPhone;

	@FindBy(xpath = "//input[@name='kinPhone']/..//label")
	WebElement nextOfKinPhoneLabel;

	@FindBy(xpath = "//input[@name='kinPhone']/following-sibling::div")
	WebElement nextOfKinPhoneRequiredLabel;
	// Clinical information
	@FindBy(xpath = "//h3[contains(.,'Clinical Information')]")
	WebElement clinicalInfoHeader;

	@FindBy(css = "[name='complaint']")
	WebElement chiefComplaint;

	@FindBy(xpath = "//input[@name='complaint']/..//label")
	WebElement chiefComplaintLabel;

	@FindBy(css = "[name='primaryDiagnoses']")
	WebElement primaryDiagnoses;

	@FindBy(css = "[name='secondaryDiagnoses']")
	WebElement secondaryDiagnoses;

	// Payer Information

	@FindBy(xpath = "//h3[contains(.,'* Payer Information')]")
	WebElement payerInfoHeader;

	@FindBy(id = "pi_primaryPayerName")
	WebElement primaryPayerName;

	@FindBy(xpath = "//input[@id='pi_primaryPayerName']/../label")
	WebElement primaryPayerNameLabel;

	@FindBy(xpath = "//input[@name='primaryPayerName']/following-sibling::div")
	WebElement primaryPayerNameRequiredLabel;

	@FindBy(id = "pi_primaryPayerPlanId")
	WebElement memberID;

	@FindBy(xpath = "//input[@id='pi_primaryPayerPlanId']/../label")
	WebElement memberIDLabel;

	@FindBy(xpath = ".//input[@id='pi_primaryPayerPlanId']/following-sibling::div")
	WebElement memberIDRequiredLabel;

	@FindBy(id = "pi_primaryPayerGroupNumber")
	WebElement group;

	@FindBy(xpath = "//input[@id='pi_primaryPayerGroupNumber']/../label")
	WebElement groupLabel;

	@FindBy(xpath = "//input[@id='pi_primaryPayerGroupNumber']/following-sibling::div")
	WebElement groupRequiredLabel;

	@FindBy(id = "pi_primaryPayerSubscriberFirstName")
	WebElement subscriberFName;

	@FindBy(xpath = "//input[@id='pi_primaryPayerSubscriberFirstName']/../label")
	WebElement subscriberFNameLabel;

	@FindBy(xpath = "//input[@id='pi_primaryPayerSubscriberFirstName']/following-sibling::div")
	WebElement subscriberFNameRequiredLabel;

	@FindBy(id = "pi_primaryPayerSubscriberLastName")
	WebElement subscriberLName;

	@FindBy(xpath = "//input[@id='pi_primaryPayerSubscriberLastName']/../label")
	WebElement subscriberLNameLabel;

	@FindBy(xpath = "//input[@id='pi_primaryPayerSubscriberLastName']/following-sibling::div")
	WebElement subscriberLNameRequiredLabel;

	@FindBy(xpath = "//button[contains(@class,'secondary-payer-btn')]//*[@iconname='ic_add']")
	WebElement secondaryPayerInfoPlusIcon;

	@FindBy(css = "[name='secondaryPayerSubscriberFirstName']")
	WebElement secondaryPayerSubscriberFirstName;

	@FindBy(css = "[name='secondaryPayerSubscriberLastName']")
	WebElement secondaryPayerSubscriberLastName;

	@FindBy(css = "[name='secondaryPayerName']")
	WebElement secondaryPayerName;

	@FindBy(css = "[name='secondaryPayerPlanId']")
	WebElement secondaryPayerMemberID;

	@FindBy(css = "[name='secondaryPayerGroupNumber']")
	WebElement secondaryPayerGroup;

	@FindBy(xpath = "//button[contains(@class,'secondary-payer-btn')]//*[@iconname='ic_arrow_back']")
	WebElement secondaryPayerInfoMinusIcon;

	// Detailed Information

	@FindBy(xpath = "//h3[contains(.,'Detailed Information')]")
	WebElement detailedInfoHeader;

	@FindBy(css = "[name='email']")
	WebElement email;

	@FindBy(css = "//input[@name='email']/../label")
	WebElement emailLabel;

	@FindBy(xpath = "//input[@id='di_email']/following-sibling::div")
	WebElement emailRequiredLabel;

	@FindBy(css = "[name='height']")
	WebElement height;

	@FindBy(css = "[name='weight']")
	WebElement weight;

	@FindBy(xpath = "//input[@name='email']/../label")
	WebElement weightLabel;
	@FindBy(id = "di_gender")
	WebElement gender;

	@FindBy(xpath = "//select[@id='di_gender']/following-sibling::div")
	WebElement genderRequiredLabel;

	@FindBy(xpath = "[name='maritalStatus']")
	WebElement martialStatus;

	@FindBy(css = "[name='race']")
	WebElement race;

	@FindBy(css = "[name='religion']")
	WebElement religion;

	@FindBy(css = "[name='primaryLanguage']")
	WebElement primaryLanguage;

	@FindBy(css = "[name='employer']")
	WebElement employer;

	@FindBy(xpath = "//input[@name='email']/../label")
	WebElement employerLabel;

	@FindBy(xpath = "//div[@class='duplicate-manual-referral-alert ng-star-inserted']")
	WebElement duplicateReferralAlert;

	@FindBy(xpath = "//div[contains(@class,'duplicate-manual-referral-message pull-left')]")
	WebElement duplicateMessage;

	@FindBy(xpath = "//a[contains(.,'Use this Patient and LOC Information')]")
	WebElement locLinkUnderActionHeader;

	@FindBy(xpath = "//nh-svg-icon[contains(@iconname,'ic_launch')]")
	WebElement duplicateReferralExpandIcon;

	@FindBy(xpath = "//a[contains(.,'X')]")
	WebElement duplicateReferralAlertClose;

	@FindBy(xpath = "(//button[@id='save-new-referral'])[2]")
	WebElement saveButtonOnInfoModal;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastName;
	// dynamic locators
	String providerNameInAutoComplete = "//div[contains(@class,'mat-autocomplete-visible')]//mat-option[contains(.,'xxxxx')]";
	String referralSourceNameInAutoComplete = "//div[contains(@class,'mat-autocomplete-visible')]//span[text()='xxxxx']/ancestor::mat-option";
	String providersMatch = "//span[@class='mat-option-text'][contains(text(),'xxxx')]";
	String teamAssignmentMatch = "//span[@class='mat-option-text'][contains(text(),'xxxxx')]//parent::mat-option";
	String keyService = "//label[contains(.,'xxxx')]";

	// Initializing the Page Factory/Objects:
	public ManualReferralModalPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
														// be initialized with this driver
	}

	/**
	 * Actions/Methods on Manual Referral Modal page:
	 * 
	 * @throws InterruptedException
	 **/

	public void clickOnAddNewReferralButton() {
		clickElement(addNewReferralButton, "add new referral button");
		waitForLoaderToDisappear();
	}

	public void selectProviderInManualReferral(String providerName) {
		input(providersSearchInput, "provider search textbox", providerName);
		clickElement(prepareWebElementWithDynamicXpath(providerNameInAutoComplete, providerName),
				"provider name in auto complete");
		waitForLoaderToDisappear();
	}

	public void selectReferralSourceInManualReferral(String referralSource) {
		input(referralSourceInput, "referral source input", referralSource);
		waitForLoaderToDisappear();
		clickElement(prepareWebElementWithDynamicXpath(referralSourceNameInAutoComplete, referralSource),
				"referral source name in auto complete");
	}

	public void clickOnsaveManualReferralButton() {
		clickElement(saveManualReferralButton, "add new referral save button");
		waitForLoaderToDisappear();
	}
	
	public void clickOnSaveManualReferralButton(PatientInfoBean referralDetails) {
		referralDetails.setNotifyDate(getFormattedCurrentTimestamp("MM/dd/YYYY", "EST"));
		referralDetails.setNotifyTime(getFormattedCurrentTimestamp("hh:mm a", "EST"));
		clickElement(saveManualReferralButton, "add new referral save button");
		waitForLoaderToDisappear();
	}

	public void clickKeyService(String service) {
		scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(keyService, service), "covidReadyKeyService");
	}

	public PatientInfoBean addManualReferralWithMandatoryfieldsOnly(String referralSource, String LOC,
			String providerName) {

		PatientInfoBean patientDetails = new PatientInfoBean();

		// click on Add new Referral button
		clickOnAddNewReferralButton();
		patientDetails = enterMandatoryFieldsInManualReferral(referralSource, LOC, providerName);
		clickOnsaveManualReferralButton();
		waitForLoaderToDisappear();

		return patientDetails;

	}

	public PatientInfoBean addManualReferralWithMandatoryfieldsOnlyWithService(String referralSource, String LOC,
			String providerName, String service) {

		PatientInfoBean patientDetails = new PatientInfoBean();

		// click on Add new Referral button
		clickOnAddNewReferralButton();

		patientDetails = enterMandatoryFieldsInManualReferral(referralSource, LOC, providerName);
		clickKeyService(service);
		clickOnsaveManualReferralButton();
		waitForLoaderToDisappear();

		return patientDetails;

	}

	public PatientInfoBean enterMandatoryFieldsInManualReferral(String referralSource, String LOC,
			String providerName) {

		PatientInfoBean patientDetails = new PatientInfoBean();

		// enter details
		patientDetails.setFirstName(input(firstNameInput, "first name", "AutoF" + randomStringGenerator(5)));
		patientDetails.setLastName(input(lastNameInput, "last name", "AutoL" + randomStringGenerator(5)));
		patientDetails.setFullName(patientDetails.getFirstName() + " " + patientDetails.getLastName());
		patientDetails.setDOB(input(dobInput, "dob", getRandomDate(new Date("1/1/1970"), new Date())));
		patientDetails.setAge(calculateAge(patientDetails.getDOB()));
		selectReferralSourceInManualReferral(referralSource);
		patientDetails.setReferralSource(referralSource);
		selectValueByVisibleText(requestedLOCDropdown, LOC, "loc dropdown");
		patientDetails.setLevelOfcare(LOC);
		selectProviderInManualReferral(providerName);
		patientDetails.setAdmitDate(
				input(admitDateInput, "admit date", getRandomDate(new Date(patientDetails.getDOB()), new Date())));
		patientDetails.setPrimaryPayerName(
				input(primaryPayerNameInput, "primary payer name", "AutoF" + randomStringGenerator(5)));
		patientDetails.setPrimaryPayerMemberID(input(primaryPayerMemberIDInput, "primary payer member id",
				randomStringGenerator(3) + randomNumberGenerator(100, 99999)));
		patientDetails.setPrimaryPayerGroupNumber(input(primaryPayerGroupInput, "primary payer group number",
				randomStringGenerator(3) + randomNumberGenerator(100, 99999)));
		patientDetails.setPrimaryPayerSubscriberFirstName(input(primaryPayerSubscriberFirstNameInput,
				"primary payer subscriber first name", "AutoF" + randomStringGenerator(5)));
		patientDetails.setPrimaryPayerSubscriberLastName(input(primaryPayerSubscriberLastNameInput,
				"primary payer subscriber last name", "AutoF" + randomStringGenerator(5)));
		patientDetails
				.setGender(selectValueByIndex(genderDropdown, (int) (randomNumberGenerator(1, 4)), "gender dropdown"));
 
		return patientDetails;

	}
	
	public void enterAdditionalFieldsForSmoke(PatientInfoBean patientDetails) {
		
		selectValueByVisibleText(primaryDiagnoses, "Fracture, lower extremity", "primary diagnoses dropdown");
		patientDetails.setPrimaryDiagnosis("Fracture, lower extremity");
		selectValueByVisibleText(secondaryDiagnoses, "Dehydration", "secondary diagnoses dropdown");
		patientDetails.setSecondaryDiagnosis("Dehydration");
		clickElement(secondaryPayerInfoPlusIcon, "secondary payer plus icon");
		patientDetails.setSecondaryPayerName(
				input(secondaryPayerName, "secondary payer name", "AutoF" + randomStringGenerator(5)));
		patientDetails.setSecondaryPayerMemberID(input(secondaryPayerMemberID, "secondary payer member id",
				randomStringGenerator(3) + randomNumberGenerator(100, 99999)));
		patientDetails.setSecondaryPayerGroupNumber(input(secondaryPayerGroup, "secondary payer group number",
				randomStringGenerator(3) + randomNumberGenerator(100, 99999)));
		patientDetails.setSecondaryPayerSubscriberFirstName(input(secondaryPayerSubscriberFirstName,
				"secondary payer subscriber first name", "AutoF" + randomStringGenerator(5)));
		patientDetails.setSecondaryPayerSubscriberLastName(input(secondaryPayerSubscriberLastName,
				"secondary payer subscriber last name", "AutoF" + randomStringGenerator(5)));
		
	}

	public List<WebElement> referralSourcePredefinedOptions() {
		return referralSourcePredefinedOptions;
	}

	public List<String> getreferralSourcePredefinedOptionsText() {
		List<String> referralSourcePredefinedOptionsText = new ArrayList<>();
		for (int i = 0; i < referralSourcePredefinedOptions.size(); i++) {
			referralSourcePredefinedOptionsText
					.add(retrieveText(referralSourcePredefinedOptions.get(i), "referralSourcePredefinedOptions"));
		}
		return referralSourcePredefinedOptionsText;
	}

	public void clickCancelButton() {
		clickElement(cancenButton, "cancel Button");
	}

	public Boolean isReferralSourceInputPresent() {

		return isElementPresent(referralSourceInput, "referralSourceInput");
	}

	public Boolean isReferralSourcePredefinedSource() {
		return isElementPresent(referralSourcePredefinedSource, "referralSourcePredefinedSource");
	}

	

	public boolean isReferralSourceLabel() {
		return isElementPresent(referralSourceLabel, "referralSourceLabel");
	}

	public void clickFirstNameInput() {
		clickElement(firstNameInput, "firstNameInput");
	}

	public boolean isTeamsAssignmentHeaderPresent() {
		return isElementPresent(teamsAssignmentHeader, "teamsAssignmentHeader");
	}

	public void setTeamValue(String values) {
		clearAndInput(teamsAssignmentInput, "teamsAssignmentInput", values);
	}

	

	public boolean isTeamAssignmentMatchPresent(String team) {
		return isElementPresent(prepareWebElementWithDynamicXpath(teamAssignmentMatch, team), "teamAssignmentMatch");
	}

	public void clickTeamAssignmentMatch(String team) {
		clickElement(prepareWebElementWithDynamicXpath(teamAssignmentMatch, team), "teamAssignmentMatch" + team);
	}

	public boolean isPatientLocationEnabled() {
		return isEnabled(patientLocation, "patientLocation");
	}

	public boolean ispatientClassEnabled() {
		return isEnabled(patientClass, "patientClass");
	}
	public void scrollToReferralSourceLabel() {
		scrollPageToElement(referralSourceLabel);

	}
	public boolean isNotifyDateEnabled() {
		return isEnabled(notifyDate, "patientClass");
	}

	}
