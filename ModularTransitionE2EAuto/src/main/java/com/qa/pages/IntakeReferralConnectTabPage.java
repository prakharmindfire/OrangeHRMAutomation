package com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class IntakeReferralConnectTabPage extends TestBase {

	// Page Factory - OR:
	// Set Status modal locators
	@FindBy(xpath = "//h5[contains(.,'Select Provider Status:')]/parent::div/select")
	WebElement statusModalSelectStatusDropdown;
	@FindBy(xpath = "//h5[contains(.,'* Select Level of Care')]/parent::div//li[1]//label/span")
	WebElement statusModalFirstLOC;
	@FindBy(xpath = "//h5[contains(.,'* Select Bed Type')]/parent::div//li[1]//label/span")
	WebElement statusModalFirstBedType;
	@FindBy(xpath = "(//label[@id='decline-reason-grouping-lbl']/parent::div[contains(.,'Patient Too Complex')])[1]//span[@class='circle']")
	WebElement statusModalDeclineReason;
	@FindBy(xpath = "//button[@class='btn-flat waves-effect waves-light blue'][contains(.,'Reopen Referral')]")
	WebElement statusModalReopenButton;
	@FindBy(xpath = "//div[@class='pull-right']/button[contains(.,'Send Response')]")
	WebElement statusModalSendResponseButton;
	@FindBy(xpath = "//span[contains(text(),'Decline all other providers')]//ancestor::label[@class='checkbox']")
	WebElement declineAllOtherProvidersCheckbox;
	@FindBy(xpath = "//button[@class='btn-flat waves-effect waves-light'][contains(.,'Save')]")
	WebElement recordProviderStatusSave;
	@FindBy(xpath = "//button[@class='btn-flat waves-effect waves-light blue'][contains(.,'Save')]")
	WebElement recordHospitalStatusSave;
	@FindBy(xpath = "//nh-datepicker[@formcontrolname='anticipatedSOCDate']//input")
	WebElement statusModalASOCInput;
	@FindBy(xpath = "//nh-intake-status-update-dialog//button[contains(.,'Close')]")
	WebElement statusModalCloseButton;
	@FindBy(xpath = "//div[@id='decline-reason-grouping']")
	List<WebElement> declineReasons;
	@FindBy(xpath = "//div[@class='patient-card cs-panel card']//h4")
	WebElement referralDetailsName;
	
	@FindBy(xpath = "//table[@id='providers-table']//thead")
    List<WebElement> patientDetailsConnectColumnHeaders;

	// Archive locators
	@FindBy(xpath = "//div[@class='actions']/button[@angularticsaction='ArchiveReferral']")
	WebElement archiveButtonInReferralDetailsPage;
	@FindBy(xpath = "//button[@angularticsaction='ArchiveReferrals']")
	WebElement removeButtonInArchiveReferralModal;
	@FindBy(xpath = "//form[@id='archive-form']/ul/li[3]//span")
	WebElement idontknowRadioButton;
	@FindBy(xpath = "//nh-datepicker[@formcontrolname='actualSOCDate']//input")
	WebElement archiveReferralModalActualSOCdatepickerInput;
	@FindBy(id = "actualSOCDateTime")
	WebElement archiveTimepickerInput;
	@FindBy(xpath = "//nh-intake-bulk-archive-dialog-accepted-booked")
	WebElement archiveModalAcceptedBooked;
	@FindBy(xpath = "//nh-datepicker[contains(@formcontrolname,'admissionDate')]//input")
	WebElement archiveReferralModalAdmissionDateDatepickerInput;
	@FindBy(xpath = "//div[contains(@class, 'diagnosis')]//select")
	WebElement otherPrimaryDiagnosisDropdown;
	@FindBy(id = "otherDiagnosisText")
	WebElement otherDiagnosisText;
	
	// Message Modal Locators
	@FindBy(xpath = "//textarea[@id='message']")
	WebElement messageTextArea;
	@FindBy(xpath = "//button[contains(.,'Send')]")
	WebElement sendMessageButton;
	@FindBy(xpath = "(//div[@class='pre-scrollable bordered bordered-bottom']//strong)[1]")
	WebElement LastmsgSentBy;
	@FindBy(xpath = "(//div[@class='pre-scrollable bordered bordered-bottom']//p)[1]")
	WebElement LastMsgContent;
	@FindBy(xpath = "//div[@class='hospital-message ng-star-inserted']//strong")
	List<WebElement> messagesSentByList;
	@FindBy(xpath = "//div[@class='hospital-message ng-star-inserted']//p")
	List<WebElement> messagesContentList;
	@FindBy(xpath = "//button[contains(.,'CLOSE')]")
	WebElement messageModalCloseButton;

	// Dynamic locators
	String setStatusButton = "//table[@id='providers-table']//td[contains(.,'xxxxx')]//parent::tr//button[contains(.,'Set Status')]";
	String providerCheckboxInReferralDetailsPage = "//td[contains(.,'xxxxx')]/ancestor::tr/td[1]//span[@class='checkbox-material header-checkbox cs-checkbox-label-margin']";
	String providerStatus = "//td[contains(.,'xxxxx')]//parent::tr//td[4]//span";
	String anticipatedStartOfCare = "//td[contains(.,'xxxxx')]//parent::tr//td[3]";
	String hospitalStatus = "//td[contains(.,'xxxxx')]//parent::tr//td[5]//span";
	String hospitalStatusRadioButton = "//label[contains(.,'xxxxx')]/span";
	// String messageButtonConTab =
	// "//span[contains(.,'xxxxx')]/ancestor::table//tbody//tr//td/button[contains(.,'
	// Messages ')]";
	String msgNewTag = "//td[contains(.,'xxxxx')]/ancestor::tr[@nh-intake-patient-detail-provider='']//button[contains(.,'Messages')]/span[contains(.,'New')]";
	String msgButtonConnectTab = "//td[contains(.,'xxxxx')]/ancestor::tr[@nh-intake-patient-detail-provider='']//button[contains(.,'Messages')]";
	String radioButtonsInArchiveModal = "//form[@id='archive-form']//span[contains(.,'xxxxx')]";
	String declineReasonRadioButton = "(//label[@id='decline-reason-grouping-lbl']/parent::div[contains(.,'xxxxx')])[1]//span[@class='circle']";

	// Initializing the Page Factory/Objects:
	public IntakeReferralConnectTabPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
														// be initialized with this driver
	}

	// click on set status button
	public void clickOnSetStatusButton(String providerName) {
		clickElement(prepareWebElementWithDynamicXpath(setStatusButton, providerName),
				"set status button for provider: " + providerName);
		waitForLoaderToDisappear();
	}

	// click on hospital status radio button
	public void clickOnHospitalStatusRadioButton(String status) {
		clickElement(prepareWebElementWithDynamicXpath(hospitalStatusRadioButton, status), status + " radiobutton");
	}

	// update status for bedded provider
	public void updateStatusForBeddedProvider(String providerName, String status) {
		// click on set status button for the given provider
		clickOnSetStatusButton(providerName);

		if (status != "Reopen Referral") {
			// select status from dropdown
			selectStatusInDropdwon(status);
			if (status != "Decline" && status != "Request More Information") {
				clickElement(statusModalFirstLOC, "first LOC in list");
				clickElement(statusModalFirstBedType, "first bed type in list");
			} else if (status == "Decline") {
				clickElement(statusModalDeclineReason, "decline reason");
			}

		}

		// reopen status condition
		if (status == "Reopen Referral") {
			clickElement(statusModalReopenButton, "reopen button in status modal for provider: " + providerName);
		} else {
			if (isElementPresent(statusModalSendResponseButton, "send response button")) {
				// uncheck decline all other providers checkbox
				if (isElementPresent(declineAllOtherProvidersCheckbox, "decline all other providers checkbox")) {
					clickElement(declineAllOtherProvidersCheckbox, "decline all other providers checkbox");
				}
				// send status response for connected referral
				clickStatusModalSendResponseButton();
			} else {
				// save provider status for manual referral
				scrollPageToElementAndClick(recordProviderStatusSave, "provider status save button");
			}
		}
		waitForLoaderToDisappear();
	}

	// update status for home health provider
	public String updateStatusForHomeHealthProvider(String providerName, String status) {
		String asoc = null;
		// click on set status button for the given provider
		clickOnSetStatusButton(providerName);

		if (status != "Reopen Referral") {
			// select status from dropdown
			selectStatusInDropdwon(status);
			if (status == "Decision Pending Authorization" || status == "Decision Pending Review") {
				clickElement(statusModalFirstLOC, "first LOC in list");
			} else if (status == "Decline") {
				clickElement(statusModalDeclineReason, "decline reason");
			} else if (status == "Accept") {
				clickElement(statusModalFirstLOC, "first LOC in list");
				asoc = input(statusModalASOCInput, "ASOC", getRandomDate(new Date(), new Date("11/31/2030")));
				reportLog("asoc is: " + asoc);
			}
		}

		// reopen status condition
		if (status == "Reopen Referral") {
			clickElement(statusModalReopenButton, "reopen button in status modal for provider: " + providerName);
		} else {
			if (isElementPresent(statusModalSendResponseButton, "send response button")) {
				// uncheck decline all other providers checkbox
				if (isElementPresent(declineAllOtherProvidersCheckbox, "decline all other providers checkbox")) {
					clickElement(declineAllOtherProvidersCheckbox, "decline all other providers checkbox");
				}
				// send status response for connected referral
				clickStatusModalSendResponseButton();
			} else {
				// save provider status for manual referral
				scrollPageToElementAndClick(recordProviderStatusSave, "provider status save button");
			}
		}
		waitForLoaderToDisappear();
		return asoc;
	}

	// update hospital status for Manual referral
	public void updateHospitalStatusForManualReferral(String providerName, String status) {

		// click on set status button for the given provider
		clickOnSetStatusButton(providerName);

		// click on status radiobutton
		clickOnHospitalStatusRadioButton(status);

		// save hospital status for manual referral
		clickElement(recordHospitalStatusSave, "hospital status save button");
		waitForLoaderToDisappear();
	}

	// archive referral
	public void archiveReferral() {

		// click on archive button
		clickOnArchiveButton();
		clickOnSaveAndRemoveButton();
		reportLog("archived the referral");
	}

	// archive accepted and booked bedded referral
	public void archiveAcceptedAndBookedBeddedReferral() {

		// click on archive button
		clickOnArchiveButton();
		// click on i don't know radio button
		clickElement(idontknowRadioButton, "i don't know radio button");
		clickOnSaveAndRemoveButton();
		reportLog("archived booked and accepted bedded referral");
	}

	// archive accepted and booked bedded referral
	public void archiveAcceptedAndBookedHHReferral() {

		// click on archive button
		clickOnArchiveButton();
		// select today's date in ASOC datepicker
		input(archiveReferralModalActualSOCdatepickerInput, "Actual SOC", getRandomDate(new Date(), new Date()));
		// enter time
		input(archiveTimepickerInput, "time picker", "01:00 AM" + Keys.ENTER);
		// click on save and remove button
		clickOnSaveAndRemoveButton();
		reportLog("archived booked and accepted home health referral");
	}

	// Send Message from Intake to D2
	public void sendMessage(String providerName, String message) {

		// click on Message button
		clickOnMsgButtonConnectTab(providerName);

		// Input the message in field
		clearAndInput(messageTextArea, "Message Text Area", message);

		// Send the message
		clickElement(sendMessageButton, "Message Send Button");
		waitForLoaderToDisappear();
		reportLog("Message sent from Intake to D2 ---> SUCCESS!");

	}

	public boolean isNewMsgTagPresentForProvidr(String provider) {

		return isElementPresent(prepareWebElementWithDynamicXpath(msgNewTag, provider),
				"New Tag for Message for " + provider);
	}

	// Click on message button on connectTab for specific Provider
	public void clickOnMsgButtonConnectTab(String provider) {

		clickElement(prepareWebElementWithDynamicXpath(msgButtonConnectTab, provider), "Message Button for: " + provider);
		waitForLoaderToDisappear();
	}

	public void clickOnCloseButtonInMessageModal() {
		clickElement(messageModalCloseButton, "close button in message modal");
	}

	// Get the Sender name form the last message sent from D2
	public String getTheSenderNameForLatestMessage() {

		return retrieveText(archiveButtonInReferralDetailsPage, "Name of Sender");
	}

	// Get The latest Messge content
	public String getTheContentForLatestMessage() {

		return retrieveText(LastmsgSentBy, "Massage content");
	}

	public String getTextReferralDetailsName() {
		return retrieveText(referralDetailsName, "text of referral name");
	}

	public ArrayList<String> getMessageSentByList() {

		ArrayList<String> messagesSentByList = new ArrayList<String>();
		;
		for (WebElement messageSentBy : this.messagesSentByList) {
			messagesSentByList.add(retrieveText(messageSentBy, "message sent by"));
		}
		return messagesSentByList;

	}
	public List<String> getpatientDetailsConnectColumnHeaders(){
		List<String> PatientDetailsConnectColumnHeadersValues = new ArrayList<String>();
		for(WebElement patientDetailsConnectColumnHeader : patientDetailsConnectColumnHeaders) {
			PatientDetailsConnectColumnHeadersValues.add(retrieveText(patientDetailsConnectColumnHeader, "Search results column header"));
		}
		 return PatientDetailsConnectColumnHeadersValues;
	}
	
	public void craeteMsgWith4000Letters(String prvderName, String Massage) {

		// click on Message button
		clickOnMsgButtonConnectTab(prvderName);

		// Input the message in field
				clearAndInput(messageTextArea, "Message Text Area", Massage);

	}

	public void craeteMsgWith4001Letters(String prvderName, String Massage) {

		// click on Message button
		clickOnMsgButtonConnectTab(prvderName);

		// Input the message in field
				clearAndInput(messageTextArea, "Message Text Area", Massage);

	}

public boolean isArchiveButtonPresent() {
	        return isElementPresent(archiveButtonInReferralDetailsPage, "Archive button");
	    }
	 
	 public boolean isSendButtonEnabled() {
	        return isElementPresent(sendMessageButton, "Send button");
	    }

	public ArrayList<String> getMessageContentList() {

		ArrayList<String> messagesContentList = new ArrayList<String>();
		for (WebElement messageContent : this.messagesContentList) {
			messagesContentList.add(retrieveText(messageContent, "message content"));
		}
		return messagesContentList;

	}

	public void selectProvider(String providerName) throws InterruptedException {
		Thread.sleep(3000);
		waitForElementToBeClickable(prepareWebElementWithDynamicXpath(providerCheckboxInReferralDetailsPage, providerName), providerName + " checkbox");
		clickElement(prepareWebElementWithDynamicXpath(providerCheckboxInReferralDetailsPage, providerName),
				"checkbox for provider: " + providerName);
	}

	public void selectProviders(List<String> providerNames) throws InterruptedException {
		for (String providerName : providerNames) {
			// click on provider checkboxes
			selectProvider(providerName);
		}
	}
	
	public String getHospitalStatus(String providerName) {
		return retrieveText(prepareWebElementWithDynamicXpath(hospitalStatus, providerName), "hospital status");
	}
	
	public String getProviderStatus(String providerName) {
		return retrieveText(prepareWebElementWithDynamicXpath(providerStatus, providerName), "provider status");
	}
	
	public String getAnticipatedStartOfCare(String providerName) {
		return retrieveText(prepareWebElementWithDynamicXpath(anticipatedStartOfCare, providerName), "ASOC");
	}
	
	public boolean isSetStatusButtonPresent(String providerName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(setStatusButton, providerName), "set status button for provider: " + providerName);
	}
	
	public void clickOnArchiveButton() {
		// click on archive button
		clickElement(archiveButtonInReferralDetailsPage, "archive button in referral details page");
	}
	
	public void clickOnSaveAndRemoveButton() {
		clickElement(removeButtonInArchiveReferralModal, "save and remove button");
		waitForLoaderToDisappear();
	}
	
	public String assertAcceptedAndBookedBeddedArchiveReferralModal(List<String> bookedProvider) {
		
		// click on archive button
		clickOnArchiveButton();
		//assert contents of modal
		String contentsOfArchiveModal = retrieveText(archiveModalAcceptedBooked, "archive modal for accepted and booked referral");
		assertTrue(contentsOfArchiveModal.contains("Archive Referral"));
		assertTrue(contentsOfArchiveModal.contains("Note: Archived referrals may reappear in your dashboard if the case manager reactivates the referral with your facility."));
		if(bookedProvider.size() == 1) {
			assertTrue(contentsOfArchiveModal.contains("You are removing 1 booked provider"));
			assertTrue(contentsOfArchiveModal.contains("For the Booked Provider "+ bookedProvider.get(0) +", Please answer the following questions:"));
		} else {
			assertTrue(contentsOfArchiveModal.contains("You are removing "+ bookedProvider.size() +" booked providers"));
			assertTrue(contentsOfArchiveModal.contains("For the Booked Provider(s), Please answer the following questions:"));
		}
		//assert radio button options.
		assertTrue(contentsOfArchiveModal.contains("This patient was admitted on..."));
		assertTrue(contentsOfArchiveModal.contains("This patient was not admitted."));
		assertTrue(contentsOfArchiveModal.contains("I don't know."));
		
		//assert datepicker is hidden when "This patient was admitted on..." option is not selected
		assertFalse(isElementPresent(archiveReferralModalAdmissionDateDatepickerInput, "admission date datepicker"));
		clickElement(prepareWebElementWithDynamicXpath(radioButtonsInArchiveModal, "This patient was not admitted."), "radio button: This patient was not admitted.");
		assertFalse(isElementPresent(archiveReferralModalAdmissionDateDatepickerInput, "admission date datepicker"));
		clickElement(prepareWebElementWithDynamicXpath(radioButtonsInArchiveModal, "I don't know."), "radio button: I don't know.");
		assertFalse(isElementPresent(archiveReferralModalAdmissionDateDatepickerInput, "admission date datepicker"));
		clickElement(prepareWebElementWithDynamicXpath(radioButtonsInArchiveModal, "This patient was admitted on..."), "radio button: This patient was admitted on...");
		assertTrue(isElementPresent(archiveReferralModalAdmissionDateDatepickerInput, "admission date datepicker"));
		
		String admitDate = getFormattedCurrentTimestamp("MM/dd/yyyy", "EST");
		input(archiveReferralModalAdmissionDateDatepickerInput, "admission date datepicker", admitDate);
		
		//save and remove
		clickOnSaveAndRemoveButton();
		
		return admitDate;
						
	}
	
	public String assertAcceptedAndBookedHomeHealthArchiveReferralModal(List<String> bookedProvider,
			String primaryDiagnosis, String anticipatedSOC) {

		String actualSOC = getFormattedCurrentTimestamp("MM/dd/yyyy", "EST");
		// click on archive button
		clickOnArchiveButton();
		// assert contents of modal
		String contentsOfArchiveModal = retrieveText(archiveModalAcceptedBooked,
				"archive modal for accepted and booked referral");
		assertTrue(contentsOfArchiveModal.contains("Archive Referral"));
		assertTrue(contentsOfArchiveModal.contains(
				"Note: Archived referrals may reappear in your dashboard if the case manager reactivates the referral with your facility."));
		if (bookedProvider.size() == 1) {
			assertTrue(contentsOfArchiveModal.contains("You are removing 1 booked provider"));
			assertTrue(contentsOfArchiveModal.contains(
					"For the Booked Provider " + bookedProvider.get(0) + ", Please answer the following questions:"));
		} else {
			assertTrue(
					contentsOfArchiveModal.contains("You are removing " + bookedProvider.size() + " booked providers"));
			assertTrue(contentsOfArchiveModal
					.contains("For the Booked Provider(s), Please answer the following questions:"));
		}

		// assert Anticipated Start of care
		assertTrue(contentsOfArchiveModal.contains("Anticipated Start of Care Date on record"));
		assertTrue(contentsOfArchiveModal.contains(anticipatedSOC));

		// assert radio button options.
		assertTrue(contentsOfArchiveModal.contains("Hospital's Primary Diagnosis: " + primaryDiagnosis));
		assertTrue(contentsOfArchiveModal.contains("Other Primary Diagnosis: "));

		assertTrue(contentsOfArchiveModal.contains("Select Diagnosis Related to Admission"));

		// assert save and remove button is disabled by default
		assertFalse(isEnabled(removeButtonInArchiveReferralModal, "save and remove button"));
		// add actual start of care and assert save and remove is still disabled
		input(archiveReferralModalActualSOCdatepickerInput, "Actual SOC", actualSOC);
		assertFalse(isEnabled(removeButtonInArchiveReferralModal, "save and remove button"));
		// enter time and assert save and remove button is enabled now.
		input(archiveTimepickerInput, "time picker", "01:00 AM" + Keys.ENTER);
		assertTrue(isEnabled(removeButtonInArchiveReferralModal, "save and remove button"));

		// select other primary diagnosis and assert dropdown and assert save and Remove
		// button is disabled
		clickElement(prepareWebElementWithDynamicXpath(radioButtonsInArchiveModal, "Other Primary Diagnosis:"),
				"radio button: Other Primary Diagnosis:");
		assertTrue(isElementPresent(otherPrimaryDiagnosisDropdown, "other primary diagnosis dropdown"));
		assertFalse(isEnabled(removeButtonInArchiveReferralModal, "save and remove button"));

		// select and value except "other" from dropdown and assert save and Remove
		// button is enabled
		selectValueByVisibleText(otherPrimaryDiagnosisDropdown, primaryDiagnosis, "other primary diagnosis dropdown");
		assertTrue(isEnabled(removeButtonInArchiveReferralModal, "save and remove button"));

		// select other from dropdown and assert other diagnosis text field is displayed
		// and save and Remove button is disabled
		selectValueByVisibleText(otherPrimaryDiagnosisDropdown, "other", "other primary diagnosis dropdown");
		assertTrue(isElementPresent(otherDiagnosisText, "other diagnosis text field"));
		assertFalse(isEnabled(removeButtonInArchiveReferralModal, "save and remove button"));

		// enter any text in other diagnosis field and assert save and Remove button is
		// enabled
		input(otherDiagnosisText, "other diagnosis text field", primaryDiagnosis + " updated");

		// save and remove
		clickOnSaveAndRemoveButton();

		return actualSOC;

	}
	
	public List<String> getStatusOptionsPresentInDropdown() {
		return getOptionsFromDropdown(statusModalSelectStatusDropdown, "status dropdown");
	}
	
	public void clickOnCloseButtonInStatusModal() {
		clickElement(statusModalCloseButton, "close button in status modal");
	}
	
	public void selectStatusInDropdwon(String status) {
		selectValueByVisibleText(statusModalSelectStatusDropdown, status, "status dropdown");
	}
	
	public List<String> getDeclineReasons() {
		List<String> declineReasons = new LinkedList<String>();
		for(WebElement declineReason: this.declineReasons) {
			declineReasons.add(retrieveText(declineReason, "decline reason").trim());
		}
		return declineReasons;
	}
	
	public void selectDeclineReason(String declineReason) {
		clickElement(prepareWebElementWithDynamicXpath(declineReasonRadioButton, declineReason), "decline reason radio button");
	}
	
	public void clickStatusModalSendResponseButton() {
		clickElement(statusModalSendResponseButton, "status modal send response button");
		waitForLoaderToDisappear();
	}

}
