package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.PatientInfoBean;
import com.qa.util.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class IntakeReferralDetailsPage extends TestBase {

	// Page Factory - OR:
	@FindBy(xpath = "//nh-intake-patient-detail-adt-info//span[contains(.,'Key Services Requested')]//following-sibling::span")
	WebElement keyServicesInDetailsPage;

	@FindBy(xpath = "//li[contains(text(),'Skilled Nursing Facility (SNF)')]")
	WebElement requiredLevelOfCareInDetailsPage;

	@FindBy(xpath = "//button[@angulartics2on='click'][contains(.,'Connect')]")
	WebElement connectTab;

	@FindBy(id = "edit-team-assignments-modal")
	WebElement editTeamAssignmentModal;
	
	@FindBy(xpath = "//div[@id='edit-team-assignments-modal']//button[contains(.,'Save')]")
	WebElement teamAssignmentSave;

	@FindBy(xpath = "//button[@angulartics2on='click'][contains(.,'Documents')]")
	WebElement documentTab;

	@FindBy(xpath = "//button[contains(.,'CLOSE')]")
	WebElement messageModalCloseButton;

	@FindBy(xpath = "//button[@angulartics2on='click'][contains(.,'Internal Notes')]")
	WebElement internalNotesTab;
	@FindBy(xpath = "//mat-dialog-container//button[contains(text(),'YES')]")
	WebElement yesButtonInModal;

	@FindBy(xpath = "//h5[contains(.,'Select Provider Status:')]/parent::div/select")
	WebElement statusModalSelectStatusDropdown;
	@FindBy(xpath = "//nh-datepicker[@formcontrolname='anticipatedSOCDate']//button[@aria-label='Open calendar']")
	public WebElement statusModalASOC;

	@FindBy(xpath = "//nh-intake-messages-dialog[@class='ng-star-inserted']")
	WebElement messageModal;
	@FindBy(xpath = "//div[@class='actions']/button[@angularticsaction='ArchiveReferral']")
	WebElement archiveButtonInReferralDetailsPage;
	@FindBy(xpath = "//button[contains(.,'Remove')]")
	WebElement removeButtonInArchiveReferralModal;

	@FindBy(xpath = "//div[@id='edit-team-assignments-modal']//button[contains(.,'Close')]")
	WebElement teamAssignmentClose;
	@FindBy(id = ".loader-overlay")
	WebElement loader;

	@FindBy(xpath = "//nh-svg-icon[contains(@mattooltip,'This is an unconnected referral')]")
	WebElement unconnectedReferralIcon;

	@FindBy(xpath = "//button[contains(.,'Edit')]")
	WebElement editButton;

	@FindBy(xpath = "//button[contains(.,'Edit Services')]")
	WebElement editServicesButton;
	
	@FindBy(xpath = "//nh-intake-edit-key-service-dialog//button[contains(.,'Update')]")
	WebElement editServicesUpdateButton;

	@FindBy(xpath = "//button[contains(.,'Broadcast Message')]")
	WebElement broadCastMessageButton;

	@FindBy(xpath = "//span[contains(text(),'Key Services Requested')]/..//span[contains(text(),'COVID-Ready')]")
	WebElement covidReadyKeyServiceInReferralDetailsPage;

	@FindBy(id = "team-assignments")
	WebElement teamAssignmentModal;

	@FindBy(xpath = "(//tr[contains(@ng-repeat,'teamAssignment in editTeamAssignmentsModel.teamAssignments')])[1]")
	WebElement teamNames1;

	@FindBy(xpath = "(//tr[contains(@ng-repeat,'teamAssignment in editTeamAssignmentsModel.teamAssignments')])[2]")
	WebElement teamNames2;

	@FindBy(xpath = "(//tr[contains(@ng-repeat,'teamAssignment in editTeamAssignmentsModel.teamAssignments')])[3]")
	WebElement teamNames3;

	@FindBy(id = "toggle-care-team")
	WebElement clicktoggleCare;

	@FindBy(xpath = "//button[@angularticsaction='OpenTeamAssignmentsModal']")
	WebElement teamAssignmentEdit;

	@FindBy(id = "team-assignments-header")
	WebElement teamAssignmentModalHeader;

	@FindBy(css = "nh-intake-add-provider-dialog h2")
	WebElement addProvidersHeader;

	@FindBy(css = "button[angularticsaction='AddProvider']")
	WebElement addProviders;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement saveAddProvider;
	@FindBy(xpath = "//div[@class='pull-right']/button[contains(.,'Send Response')]")
	WebElement statusModalSendResponseButton;
	@FindBy(xpath = "//h5[contains(.,'* Select Level of Care')]/parent::div//li[1]//label/span")
	WebElement statusModalFirstLOC;
	@FindBy(id = "providersSearch")
	WebElement providersSearchInput;
	@FindBy(css = ".mat-dialog-container")
	WebElement confirmationModal;
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement statusModalHospitalStatusReopen;

	@FindBy(xpath = "//button[@mattooltip='Clear hospital status selection']/parent::div/span/button[contains(.,'Save')]")
	WebElement hospitalStatusSave;

	@FindBy(xpath = "//nh-intake-patient-detail-adt-info/div/div/ul[1]/li[1]/span/strong")
	WebElement referralDetailsReferralSourceLabel;

	@FindBy(id = "toggle-care-team")
	WebElement toggleCare;

	@FindBy(id = "care-team")
	WebElement careTeamPanel;
	
	//referral details locators
	@FindBy(xpath = "//div[@class='patient-card cs-panel card']//h4")
	WebElement referralDetailsName;
	@FindBy(xpath = "//li[contains(.,'Required Level of Care:')]")
	WebElement referralDetailsLOC;
	@FindBy(xpath="//li[contains(.,'Gender:')]")
	WebElement referralDetailsGender;
	@FindBy(xpath="//li[contains(.,'DOB:')]")
	WebElement referralDetailsDOB;
	@FindBy(xpath="//li[contains(.,'Referral Source:')]")
	WebElement referralDetailsSource;
	@FindBy(xpath = "//li[contains(.,'Notify Date:')]")
	WebElement referralDetailsNotifyDate;
	@FindBy(xpath = "//li[contains(.,'Notify Time:')]")
	WebElement referralDetailsNotifyTime;
	@FindBy(xpath = "//li[contains(.,'Admit Date:')]")
	WebElement referralDetailsAdmitDate;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[1]/li[contains(.,'1:')]")
	WebElement referralDetailsPrimaryPayerName;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[1]/li[contains(.,'Plan ID:')]")
	WebElement referralDetailsPrimaryPayerPlanID;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[1]/li[contains(.,'Policy #:')]")
	WebElement referralDetailsPrimaryPayerPolicyNumber;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[1]/li[contains(.,'Group #:')]")
	WebElement referralDetailsPrimaryPayerGroupNumber;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[1]/li[contains(.,'Subscriber:')]")
	WebElement referralDetailsPrimaryPayerSubscriberName;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[2]/li[contains(.,'2:')]")
	WebElement referralDetailsSecondaryPayerName;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[2]/li[contains(.,'Plan ID:')]")
	WebElement referralDetailsSecondaryPayerPlanID;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[2]/li[contains(.,'Policy #:')]")
	WebElement referralDetailsSecondaryPayerPolicyNumber;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[2]/li[contains(.,'Group #:')]")
	WebElement referralDetailsSecondaryPayerGroupNumber;
	@FindBy(xpath = "//li[contains(.,'Payers')]/following-sibling::li/ul[2]/li[contains(.,'Subscriber:')]")
	WebElement referralDetailsSecondaryPayerSubscriberName;
	@FindBy(xpath = "//span[contains(.,'Primary:')]//span[@class='diagnosis-data']")
	WebElement referralDetailsPrimaryDiagnosis;
	@FindBy(xpath = "//span[contains(.,'Secondary:')]//span[@class='diagnosis-data']")
	WebElement referralDetailsSecondaryDiagnosis;
	
	
	@FindBy(xpath="//button[@angulartics2on='click'][contains(.,'Connect')]")
	WebElement connectTabButton;
	@FindBy(xpath="//button[@angulartics2on='click'][contains(.,'Documents')]")
	WebElement documentsTabButton;
	@FindBy(id="//div[@id='edit-key-services-modal']//button[contains(@class,'close')]")
	WebElement closeButtonKeyServiceModal;
	
	
	//Dynamic Locators
	String statusOption = "//span[contains(.,'xxxxx')]";
	String providerMatch = "//span[@class=\"mat-option-text\"][contains(text(),xxxxx)]";
	String setStatusButton = "//table[@id=\"providers-table\"]//td[contains(., xxxxx)]//parent::tr//button[contains(.,\"Set Status\")]";
	String providersChipClose = "//div[contains(@class,'badge')]//div[contains(text(),'xxxx')]/..//nh-svg-icon[@angularticsaction='RemoveProvider']";
	String referralDetailsProviderStatus = "//div/span[contains(.,'xxxx')]/ancestor::tr//td[4]//span";
	String referralDetailsHospitalStatus = "//div/span[contains(.,'xxxx')]/ancestor::tr//td[5]//span";
	String referralDetailsASOC = "//div/span[contains(.,'xxxx')]/ancestor::tr/td[3]";
	String messageButtonConnectTab = "//td[contains(.,'xxxx')]/ancestor::tr[@nh-intake-patient-detail-provider='']//button[contains(.,'Messages')]";
	String providerCheckboxInReferralDetailsPage = "//td[contains(.,'xxxx')]/ancestor::tr/td[1]//span[@class='checkbox-material header-checkbox cs-checkbox-label-margin']";
	String providersChip = "//div[contains(@class,'badge')]//div[contains(text(),'xxxx')]";
	String providersMatch = "//span[@class='mat-option-text'][contains(text(),'xxxx')]";
	String completeTeam = "//h3[contains(.,'xxxx')]//ancestor::ul//button[contains(.,'Complete')]";
	String completeTeamStatusDetails1 = "(//h3[contains(.,'xxxx')]//ancestor::ul//li//div//p)[1]";
	String completeTeamStatusDetails2 = "(//h3[contains(.,'xxxx')]//ancestor::ul//li//div//p)[2]";
	String completedTeamCheckbox = "//h3[@id='team-assignments-row--name'][contains(text(),'xxxx')]/parent::li//nh-svg-icon[@iconname='ic_check_box']";
	String teamCheckBox = "//tr[contains(.,'xxxxx')]//span[@class='checkbox-material header-checkbox cs-checkbox-label-margin']";
	String completeTeamAssignment = "//h3[contains(.,'xxxxx')]//ancestor::ul//button[contains(.,'Complete')]";
	String keyServiceCheckbox = "//label[contains(.,'xxxxx')]";

	// Initializing the Page Factory/Objects:
		public IntakeReferralDetailsPage() {
			PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
															// be initialized with this driver
		}
		
				// TBD - move to common page
		public void navigateTo(String pageName) {
			if (pageName == "connect") {
				clickElement(connectTab, "Connect Tab");
				waitForLoaderToDisappear();
			}else if(pageName == "document") {
				clickElement(documentTab, "dashboard page");
				waitForLoaderToDisappear();
			} else if(pageName == "internal note") {
				clickElement(internalNotesTab, "dashboard page");
				waitForLoaderToDisappear();
			}
		}
	public boolean isEditServiceButtonPresent() {
		return isElementPresent(editServicesButton, "Edit service Button");
	}

	public void clickEditButton() {
		clickElement(editButton, "editButton");
	}

	public void clickAddProver() {
		clickElement(addProviders, "addProviders Button");
	}

	public boolean isAddProvidersHeaderPresent() {
		return isElementPresent(addProvidersHeader, "addProvidersHeader");
	}

	public String addProviderText() {
		return retrieveText(addProvidersHeader, "addProvidersHeader");
	}

	public WebElement providersSearchInput() {
		return providersSearchInput;
	}

	public String referralDetailsLOCtext() {
		return retrieveText(referralDetailsLOC, "referralDetailsLOC");
	}
	public boolean isProvidersMatchPresent(String providerName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(providersMatch, providerName),
				" providerNameInAutoComplete");
	}
	public String referralDetailsNameText() {
		return retrieveText(referralDetailsName, "referralDetailsName");
	}
	public boolean isreferralDetailsNamePresent() {
		return isElementPresent(referralDetailsName, "referralDetailsName");
	}

	public boolean isSaveProvderButtonEnabled() {
		return isEnabled(saveAddProvider, "saveAddProvider button");
	}

	public void clickProviderMatch(String providerName) {
		clickElement(prepareWebElementWithDynamicXpath(providersMatch, providerName), "Provider amtch");
	}

	public boolean isProviderChipPresent(String providerName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(providersChip, providerName), "Provider Chip");
	}

	public void clickProviderChipClose(String Provider) {
		clickElement(prepareWebElementWithDynamicXpath(providersChipClose, Provider), "providersChipClose Button");
	}

	public void clickSaveProviders() {
		clickElement(saveAddProvider, "saveAddProvider Button");
	}

	public String referralDetailsProviderStatusText(String providerName) {
		return retrieveText(prepareWebElementWithDynamicXpath(referralDetailsProviderStatus, providerName),
				"referralDetailsProviderStatus");
	}

	public String referralDetailsHospitalStatusText(String providerName) {
		return retrieveText(prepareWebElementWithDynamicXpath(referralDetailsHospitalStatus, providerName),
				"referralDetailsHospitalStatus");
	}

	public void clickBroadCastMessageButton() {
		clickElement(broadCastMessageButton, "broadCastMessageButton");
	}

	public boolean isbroadCastMessageButtonEnabled() {
		return isEnabled(broadCastMessageButton, "broadCastMessageButton");
	}

	public boolean isteamAssignmentModalPresent() {
		return isElementPresent(teamAssignmentModal, "teamAssignmentModal");
	}

	public String teamAssignmentModalHeaderText() {
		return retrieveText(teamAssignmentModalHeader, "teamAssignmentModalHeader");
	}

	public void clicktoggleCare() {
		clickElement(toggleCare, "clicktoggleCare");
	}

	public boolean iscareTeamPanelPresent() {
		return isElementPresent(careTeamPanel, "careTeamPanel");
	}

	public void clickteamAssignmentEdit() {
		clickElement(teamAssignmentEdit, "teamAssignmentEdit");
	}
	public boolean isteamAssignmentEditPresent() {
		return isElementPresent(teamAssignmentEdit, "teamAssignmentEdit");
	}

	public void clickTeamAssignmentClose() {
		clickElement(teamAssignmentClose, "teamAssignmentClose");
	}

	public boolean isEditTeamAssignmentModalPresent() {
		return isElementPresent(editTeamAssignmentModal, "editTeamAssignmentModal");
	}

	public String teams1text() {
		return retrieveText(teamNames1, "teamNames1");
	}

	public String teams2text() {
		return retrieveText(teamNames2, "teamNames2");
	}

	public String teams3text() {
		return retrieveText(teamNames3, "teamNames3");
	}

	public boolean isCompleteTeamPresent(String team) {
		return isElementPresent(prepareWebElementWithDynamicXpath(completeTeam, team), "Complete Team" + team);
	}

	public void clickCompletTeams(String team) {
		clickAndWaitForPageLoad(prepareWebElementWithDynamicXpath(completeTeam, team), "Complete Team" + team);
	}

	public String completeTeamText(String team) {
		return retrieveText(prepareWebElementWithDynamicXpath(completeTeam, team), "Complte team" + team);
	}

	public String completeTeamStatusDetails1Text(String team) {
		return retrieveText(prepareWebElementWithDynamicXpath(completeTeamStatusDetails1, team),
				"completeTeamStatusDetails1");
	}

	public String completeTeamStatusDetails2Text(String team) {
		return retrieveText(prepareWebElementWithDynamicXpath(completeTeamStatusDetails2, team),
				"completeTeamStatusDetails2");
	}

	public boolean isconfirmationModalPresent() {
		return isElementPresent(confirmationModal, "confirmationModal");
	}

	public void clickyesButtonInModal() {
		clickElement(yesButtonInModal, "yesButtonInModal");
	}

	public WebElement setStatusButton(String providerName) {
		return prepareWebElementWithDynamicXpath(setStatusButton, providerName);
	}

	public void clicksetStatusButton(String providerName) {
		clickElement(prepareWebElementWithDynamicXpath(setStatusButton, providerName), "setStatusButton");
	}

	public void clickstatusModalFirstLOC() {
		clickElement(statusModalFirstLOC, "statusModalFirstLOC");
	}

	public void clickstatusModalSendResponseButton() {
		clickElement(statusModalSendResponseButton, "statusModalSendResponseButton");
	}

	public void clickcompletedTeamCheckbox(String team) {
		clickElement(prepareWebElementWithDynamicXpath(completedTeamCheckbox, team), team);
	}

	public WebElement referralDetailsASOC(String providerName) {
		return prepareWebElementWithDynamicXpath(referralDetailsASOC, providerName);
	}

	public boolean isstatusModalASOCPresent() {
		return isElementPresent(statusModalASOC, "status modal ASOC");
	}

	public String getTextReferralDetailsASOC(String referralName) {
		return retrieveText(referralDetailsASOC(referralName), "Referral Details ASOCC");
	}

	public WebElement messageButtonConnectTab(String providerName) {
		return prepareWebElementWithDynamicXpath(messageButtonConnectTab, providerName);
	}

	public void clickmessageButtonConnectTab(String providerName) {
		clickElement(prepareWebElementWithDynamicXpath(messageButtonConnectTab, providerName),
				"messageButtonConnectTab(String providerName)");
	}

	public String getTextmessageModal() {
		return retrieveText(messageModal, "Message Modal");
	}

	public void clickmessageModalCloseButton() {
		clickElement(messageModalCloseButton, "messageModalCloseButton");
	}

	public WebElement providerCheckboxInReferralDetailsPage(String providerName) {
		return prepareWebElementWithDynamicXpath(providerCheckboxInReferralDetailsPage, providerName);
	}

	public boolean isProviderCheckboxPresent(String providerName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(providerCheckboxInReferralDetailsPage, providerName),
				"providerCheckboxInReferralDetailsPage");
	}
	public void updateHospitalStatusForManualReferral(String status,String providerName) {
		//click on respond dropdown
		clickElement(prepareWebElementWithDynamicXpath(setStatusButton,providerName),"set status button");
		//select status from respond dropdown
		clickElement(prepareWebElementWithDynamicXpath(statusOption, status), status + " for hospital");
		waitForLoaderToDisappear();
		reportLog("updated hospital status to " + status);
	}
	public String getreferralDetailsReferralSourceLabel(){
		return TestUtil.retrieveText(referralDetailsReferralSourceLabel,"referral Details Referral SourceLabel");
	}
	// TBD - move to common page

	//page methods
	public String getKeyServices() {
		return retrieveText(keyServicesInDetailsPage, "key services");
	}

	//page methods
	public String getrequiredLevelOfCare() {
		return retrieveText(requiredLevelOfCareInDetailsPage, "Level Of Care");
	}

	public void clickAddProvider() {
		clickElement(addProviders, "Add provider button on referral details page");
	}

	public void clickSaveAddProvider() {
		clickElement(saveAddProvider, "Add provider button on referral details page");
	}

	public void clickToggleCare(){
		clickElement(toggleCare,"toggle-care-team");
	}

	public Boolean isDocumentTabPresent(){
		return TestUtil.isElementPresent(documentTab,"Documents tab on referral details page");
	}

	public Boolean isInterNoteTabPresent(){
		return  TestUtil.isElementPresent(internalNotesTab,"Internal Note tab on referral details page");
	}

	public Boolean isConnectTabPresent(){
		return  TestUtil.isElementPresent(connectTab,"Connect tab on referral details page");
	}

	public void clickSaveOnHospitalStatus() {
		clickElement(hospitalStatusSave, "Save button");
	}
	public void clickProviderMatch() {
		clickElement(prepareWebElementWithDynamicXpath(providerMatch, "provider name"), "Provider name");
	}

	public String getCareTeamPanelText(){
		return   retrieveText(careTeamPanel,"care team panel details");
	}

	public void scrollToDown() {
		TestUtil.scrollPageToLocation("0","1500");
	}

	public void moveMouseToBroadcastMessageButton() {
		TestUtil.hoverOver(broadCastMessageButton);
		waitForPageToLoad();

	}
	
	
	
	public void clickconnectTabButton() {
		clickAndWaitForPageLoad(connectTabButton, "connectTabButton");
	}

				
	public void clickcloseButtonKeyServiceModal() {
				clickAndWaitForPageLoad(closeButtonKeyServiceModal, "close button key service modal");
	}
	
	public void clickTeamCheckBox(String team) {
		clickElement(prepareWebElementWithDynamicXpath(teamCheckBox, team), team + "checkbox");
	}
	
	public void assignTeam(List<String> teams) throws Exception {
		clickElement(teamAssignmentEdit, "Edit team assignment button");
		waitForLoaderToDisappear();
	    for (String team : teams) {
	    	clickTeamCheckBox(team);
	    }
	    clickElement(teamAssignmentSave, "save team assignment button");
	    waitForLoaderToDisappear();
	}

	public boolean isTeamAssignmenetHeaderPresent() {
		return  isElementPresent(teamAssignmentModalHeader,"team assignment header");
	}
	
	public void clickCompleteTeam(String team) {
		clickElement(prepareWebElementWithDynamicXpath(completeTeam, team), team + "complete button");
	}
	
	public void completeTeamAssignment(List<String> teams) {		
		for (String team : teams) {
			clickCompleteTeam(team);
			waitForLoaderToDisappear();
	    }
	}
	
	public void verifyReferralDetailsForManualReferralSmoke(PatientInfoBean referralDetails) {
		assertEquals(retrieveText(referralDetailsName, "referral name"), referralDetails.getReferralName());
		assertEquals(retrieveText(referralDetailsGender, "gender"), "Gender: " + referralDetails.getGender());
		assertEquals(retrieveText(referralDetailsDOB, "DOB"), "DOB: " + referralDetails.getDOB() + " " + "(" + referralDetails.getAge() + " yr)");
		
		assertEquals(retrieveText(referralDetailsSource, "referral source"), "Referral Source:" + "\n" + referralDetails.getReferralSource());
		assertEquals(retrieveText(referralDetailsLOC, "required level of care"), "Required Level of Care:" + "\n" + referralDetails.getLevelOfcare());
		
		assertEquals(retrieveText(referralDetailsAdmitDate, "admit date"), "Admit Date: " + referralDetails.getAdmitDate());
		assertEquals(retrieveText(referralDetailsNotifyDate, "notify date"), "Notify Date: " + referralDetails.getNotifyDate());
		assertTrue(retrieveText(referralDetailsNotifyTime, "notify time").contains("Notify Time: " + referralDetails.getNotifyTime().substring(0, 5)));
		assertTrue(retrieveText(referralDetailsNotifyTime, "notify time").contains(referralDetails.getNotifyTime().substring(5, 8).toUpperCase()));
		
		assertEquals(retrieveText(referralDetailsPrimaryPayerName, "primary payer name"), "1: " + referralDetails.getPrimaryPayerName());
		assertEquals(retrieveText(referralDetailsPrimaryPayerPlanID, "primary payer plan id"), "Plan ID: " + referralDetails.getPrimaryPayerMemberID());
		assertEquals(retrieveText(referralDetailsPrimaryPayerGroupNumber, "primary payer group no."), "Group #: " + referralDetails.getPrimaryPayerGroupNumber());
		assertEquals(retrieveText(referralDetailsPrimaryPayerSubscriberName, "primary payer subscriber name"), "Subscriber: " + referralDetails.getPrimaryPayerSubscriberFirstName() + " " + referralDetails.getPrimaryPayerSubscriberLastName());
		
		assertEquals(retrieveText(referralDetailsSecondaryPayerName, "secondary payer name"), "2: " + referralDetails.getSecondaryPayerName());
		assertEquals(retrieveText(referralDetailsSecondaryPayerPlanID, "secondary payer plan id"), "Plan ID: " + referralDetails.getSecondaryPayerMemberID());
		assertEquals(retrieveText(referralDetailsSecondaryPayerGroupNumber, "secondary payer group no."), "Group #: " + referralDetails.getSecondaryPayerGroupNumber());
		assertEquals(retrieveText(referralDetailsSecondaryPayerSubscriberName, "secondary payer subscriber name"), "Subscriber: " + referralDetails.getSecondaryPayerSubscriberFirstName() + " " + referralDetails.getSecondaryPayerSubscriberLastName());
		
		assertTrue(retrieveText(referralDetailsPrimaryDiagnosis, "primary diagnoses").contains(referralDetails.getPrimaryDiagnosis()));
		assertTrue(retrieveText(referralDetailsSecondaryDiagnosis, "secondary diagnoses").contains(referralDetails.getSecondaryDiagnosis()));
	}
	
	public void clickKeyServiceCheckbox(String keyService) {
		clickElement(prepareWebElementWithDynamicXpath(keyServiceCheckbox, keyService), keyService + " checkbox");
	}
	
	public void addRemoveKeyServices(List<String> keyServices) {
		clickElement(editServicesButton, "edit services button");
		waitForLoaderToDisappear();
		for (String keyService : keyServices) {
			clickKeyServiceCheckbox(keyService);
	    }
		clickElement(editServicesUpdateButton, "edit services update button");
		waitForLoaderToDisappear();
	}
	
	public void verifyReferralDetailsForDischargeIntakeSmoke(PatientInfoBean referralDetails, String referralSource, String LOC) {
		assertEquals(retrieveText(referralDetailsName, "referral name"), referralDetails.getReferralName());
		assertEquals(retrieveText(referralDetailsGender, "gender").toLowerCase(), ("Gender: " + referralDetails.getGender()).toLowerCase());
		assertEquals(retrieveText(referralDetailsDOB, "DOB"), "DOB: " + referralDetails.getDOB() + " " + "(" + referralDetails.getAge() + " yr)");
		
		assertEquals(retrieveText(referralDetailsSource, "referral source"), "Referral Source:" + "\n" + referralSource);
		assertEquals(retrieveText(referralDetailsLOC, "required level of care"), "Required Level of Care:" + "\n" + LOC);
		
		assertEquals(retrieveText(referralDetailsAdmitDate, "admit date"), "Admit Date: " + referralDetails.getAdmitDate());
		assertEquals(retrieveText(referralDetailsNotifyDate, "notify date"), "Notify Date: " + referralDetails.getNotifyDate());
		
		assertTrue(retrieveText(referralDetailsPrimaryDiagnosis, "primary diagnoses").contains(referralDetails.getPrimaryDiagnosis()));
		assertTrue(retrieveText(referralDetailsSecondaryDiagnosis, "secondary diagnoses").contains(referralDetails.getSecondaryDiagnosis()));
	}

	public void verifyInternalNotesTabIsActive(){
		assertTrue(retrieveAttributeValue(internalNotesTab,"class","Internal Notes Button").contains("intake-blue-btn"));
	}
}