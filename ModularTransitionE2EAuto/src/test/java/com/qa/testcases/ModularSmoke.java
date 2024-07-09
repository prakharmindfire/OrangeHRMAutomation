package com.qa.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import com.qa.pages.*;

import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.DataProviders;
import com.qa.util.FakeDataProvider;
import com.qa.util.JsonReader;
import com.qa.util.PatientInfoBean;
import com.qa.util.TestUtil;

import static com.qa.util.TestUtil.*;

public class ModularSmoke extends TestBase {
	// Declare all pages to be used
	LoginPage loginPageObj;
	WorkbookPage workbookPageObj;
	DischargeBuildPage dischargeBuildPageObj;
	DischargeReferPage dischargeReferPageObj;
	DischargeConnectPage dischargeConnectPageObj;
	IntakeDashboardPage intakeDashboardPageObj;
	IntakeReferralDetailsPage intakeReferralDetailsPageObj;
	IntakeReferralConnectTabPage intakeReferralConnectTabPageObj;
	IntakeReferralDocumentTabPage intakeReferralDocumentTabPageObj;
	IntakeReferralInternalNoteTabPage intakeReferralInternalNoteTabPageObj;
	IntakeArchivePage intakeArchivePageObj;
	IntakeProfilePage intakeProfileObj;
	ManualReferralModalPage manualReferralModalPageObj;
	PatientSummaryPage patientSummaryPageobj;
	AddressBookPage addressBookPageobj;
	InternalNotesPage internalNotesPageobj;
	IntakeInternalNoteNotificationPage intakeInternalNoteNotificationPageObj;
	CommonPage commonPageObj;
	PatientDischargePage patientDischargePageobj;
	OmePage omePageObj;
	DocumentManagerPage documentManagerPageObj;
	QuickcasePage quickCasePageObj;

	// declare testdata
	String intakeUserName;
	String intakeUserPassword;
	String intakeUserFullName;
	String qcUsername;
	String qcPassword;
	String unconnectedProvider;
	String beddedLOC;
	String referralSource;
	String provider;
	String pmlUsername;
	String pmlPassword;
	String caseHistoryUsername;
	String caseHistoryPassword;
	String docRenameFreeText;
	String internalNoteText;
	String internalNoteModalTitle;
	String patientMRNToBePassedForCaseHistory;
	List<String> providerList;
	String d2UserName;
	String d2PassWord;
	String homeHealthPOS;
	String homeHealthLOC;
	String escString;
	String mirthURL;
	String dischargeIntakeSmokeDischargeUser;
	String dischargeIntakeSmokeDischargeUserPassword;
	String dischargeIntakeSmokeForm;
	List<String> homeHealthKeyServices;
	String dischargeIntakeSmokeDischargeUserEmail;
	String intakeUserEmail;
	String phonenumber;
	String profileprovider ;
	String addressline1text;
	String addressline2text;
	String citytext;
	String countytext;
	String organizationID;
	String metrotext;
	String PhysicianName ;
	String payerName ;
	String profileintakeUserName;
	String profileintakeUserPassword;
	String intakeDocUploadUser;
	String intakeDocUploadPass;
	String fileName;
	String oidcSsoLaunchUrl;
	String samlSsoLaunchUrl;
	String intakeSsoUserOktaUsername;
	String intakeSsoUserOktaPassword;
	String filePathToUpload;
	List<String> intakeSsoProviders;

	// shared testdata
	PatientInfoBean referralDetails;
	String referralName;

	@BeforeClass(alwaysRun = true)
	public void intialize() throws MalformedURLException {
		className = this.getClass().getSimpleName();
		// initialize browser
		initialization();
		// initialize testdata JSON object
		testDataFile = JsonReader.getTestDataJSON(className);
		//testDataEnv = testDataFile.getJSONObject(env);
		testData = testDataFile.getJSONObject(env);
		//testDataEnv.getJSONObject(browserName);

	}

	@BeforeMethod(alwaysRun = true)
	public void setup() {

		// Initilize page Objects
		loginPageObj = new LoginPage();
		workbookPageObj = new WorkbookPage();
		dischargeBuildPageObj = new DischargeBuildPage();
		dischargeReferPageObj = new DischargeReferPage();
		dischargeConnectPageObj = new DischargeConnectPage();
		intakeDashboardPageObj = new IntakeDashboardPage();
		intakeReferralDetailsPageObj = new IntakeReferralDetailsPage();
		intakeReferralConnectTabPageObj = new IntakeReferralConnectTabPage();
		intakeReferralDocumentTabPageObj = new IntakeReferralDocumentTabPage();
		intakeReferralInternalNoteTabPageObj = new IntakeReferralInternalNoteTabPage();
		intakeArchivePageObj = new IntakeArchivePage();
		manualReferralModalPageObj = new ManualReferralModalPage();
		intakeInternalNoteNotificationPageObj = new IntakeInternalNoteNotificationPage();
		commonPageObj = new CommonPage();
		addressBookPageobj = new AddressBookPage();
		patientSummaryPageobj = new PatientSummaryPage();
		internalNotesPageobj = new InternalNotesPage();
		patientDischargePageobj = new PatientDischargePage();
		omePageObj = new OmePage();
		intakeProfileObj = new IntakeProfilePage();
		documentManagerPageObj = new DocumentManagerPage();
		quickCasePageObj = new QuickcasePage();

		// initialize testdata variables
		intakeUserName = testData.getString("intakeUserName");
		intakeUserPassword = testData.getString("intakeUserPassword");
		intakeUserFullName = testData.getString("intakeUserFullName");
		caseHistoryUsername = testData.getString("caseHistoryUsername");
		organizationID = testData.getString("organizationID");
		caseHistoryPassword = testData.getString("caseHistoryPassword");
		beddedLOC = testData.getString("beddedLOC");
		referralSource = testData.getString("referralSource");
		pmlUsername = testData.getString("pmlUsername");
		pmlPassword = testData.getString("pmlPassword");
		provider = testData.getString("provider");
		providerList = convertJsonArrayToList(testData.getJSONArray("providerList"));
		patientMRNToBePassedForCaseHistory = testData.getString("patientMRNToBePassedForCaseHistory");
		d2UserName = testData.getString("d2UserName");
		d2PassWord = testData.getString("d2PassWord");
		docRenameFreeText = testData.getString("docRenameFreeText");
		internalNoteText = testData.getString("internalNoteText");
		internalNoteModalTitle = testData.getString("internalNoteModalTitle");
		homeHealthPOS = testData.getString("homeHealthPOS");
		homeHealthLOC = testData.getString("homeHealthLOC");
		escString = testData.getString("escString");
		mirthURL = testData.getString("mirthURL");
		dischargeIntakeSmokeDischargeUser = testData.getString("dischargeIntakeSmokeDischargeUser");
		dischargeIntakeSmokeDischargeUserPassword = testData.getString("dischargeIntakeSmokeDischargeUserPassword");
		dischargeIntakeSmokeForm = testData.getString("dischargeIntakeSmokeForm");
		homeHealthKeyServices = convertJsonArrayToList(testData.getJSONArray("homeHealthKeyServices"));
		dischargeIntakeSmokeDischargeUserEmail = testData.getString("dischargeIntakeSmokeDischargeUserEmail");
		intakeUserEmail = testData.getString("intakeUserEmail");
		phonenumber = FakeDataProvider.getPhoneNumber();
		profileprovider = testData.getString("profileProvider");
		addressline1text = testData.getString("addressLine1");
		addressline2text = testData.getString("addressLine2");
		citytext = testData.getString("city");
		countytext = testData.getString("county");
		metrotext = testData.getString("metro");
		PhysicianName = FakeDataProvider.getFirstName();
		payerName = FakeDataProvider.getFirstName();
		profileintakeUserName = testData.getString("intakeProfileusername");
		profileintakeUserPassword= testData.getString("intakeProfilePassword");
		intakeDocUploadUser= testData.getString("intakeDocUploadUsername");
		intakeDocUploadPass= testData.getString("intakeDocUploadPassword");
		fileName = "TestManual.pdf";
		oidcSsoLaunchUrl = testData.getString("oidcSsoLaunchUrl");
		samlSsoLaunchUrl = testData.getString("samlSsoLaunchUrl");
		intakeSsoUserOktaUsername = testData.getString("intakeSsoUserOktaUsername");
		intakeSsoUserOktaPassword = testData.getString("intakeSsoUserOktaPassword");
		intakeSsoProviders = convertJsonArrayToList(testData.getJSONArray("intakeSsoProviders"));
		qcUsername  = testData.getString("qcUsername");
		qcPassword = testData.getString("qcPassword");
		unconnectedProvider = testData.getString("unconnectedProvider");
		
		
		 
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshotInCaseOfFailure(ITestResult result) throws Exception {
		// Check if the test case failed or was skipped and take screenshot
		if (result.getStatus() == result.FAILURE || result.getStatus() == result.SKIP) {
			if (result.getStatus() == result.FAILURE)
				reportLog("TEST FAILED: " + result.getName());
			else
				reportLog("TEST SKIPPED: " + result.getName());
			String screenshotPath = getScreenshot(getdriver(), result.getName());
			result.setAttribute("screenshotPath", screenshotPath); // sets the value the variable/attribute
			// screenshotPath as the path of the screenshot
		} else if (result.getStatus() == result.SUCCESS) {
			reportLog("TEST PASSED: " + result.getName());
		}
	}

	@AfterClass(alwaysRun = true)
	public void cleanup() {
		reportLog("quitting browser");
		getdriver().quit();
		reportLog("clearing threadlocal");
		unload();
	}

	@Test(groups = { "D2PML", "patientStore", "eventing",
			"transitionCore" }, priority = 0, description = "PML smoke Basic flow check")
	public void d2PMLSmoke() throws Exception {

		// Close all existing Tabs if opened 
		closeAllSubTabs();
		
		// Login to Discharge
		loginPageObj.login(pmlUsername, pmlPassword);

		//Add a Patient
		workbookPageObj.addAPatientWithMandatoryFieldsOnly();

		// Navigate to Refer page
		commonPageObj.navigateTo("refer");

		// Search by location-zip
		dischargeReferPageObj.searchProvidersByLocationZip("99999");

		// Click on provider Names
		dischargeReferPageObj.clickOnCheckboxByProviderName(providerList);

		// Share pml list
		dischargeReferPageObj.sharePMLList();

		// Select providers and submit
		dischargeReferPageObj.makeProviderSelectionAndSubmitInPmlUI(providerList);

		// Navigate to the Discharge Page
		patientDischargePageobj.navigateToDischargePage();

		// Discharge the patient
		patientDischargePageobj.completeDischargeProcess();

		// Logout
		loginPageObj.logout();

	}

	@Test(groups = { "CaseHistory", "patientStore", "caseHistoryService", "transitionCore", "kafka",
			"caretransitionweb", "commonServices" }, priority = 2, description = "Check the case history")
	public void verifyCaseHistory() throws Exception {

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Search an existing patient
		workbookPageObj.searchAllAndAddToWorkBook(patientMRNToBePassedForCaseHistory);

		// Search the patient on workbook and move to the connect page
		workbookPageObj.navigateToPatientPage(patientMRNToBePassedForCaseHistory, "Connect");

		// Click on caret icon & open case history
		commonPageObj.clickOnCaretIconOpencaseHistory();

		// Verify the events
		commonPageObj.verifyCaseHistoryEventsAndCloseModal();

		// Logout
		loginPageObj.logout();

	}

	@Test(groups = { "Document Manager", "audit", "config", "transitionDocument", "transitionCore", "patientStore",
			"commonServices", "caretransitionweb" }, priority = 0, description = "Document Manager Smoke Check")
	public void verifyDocumentManager() throws Exception {

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Click on Settings Icon
		loginPageObj.clickSettingsCog();

		// Click on Generic document manager link
		loginPageObj.clickOnGenericDocMgrUnderSettingsCog();

		// Rename the doc in the unassigned section
		documentManagerPageObj.renameDocumentFromGenericDocMgrSmokeTest(docRenameFreeText);

		// Logout
		loginPageObj.logout();

	}

	@Test(groups = { "Internal Notes", "audit", "PDB", "transitionCore", "patientStore", "commonServices",
			"referenceData", "caretransitionweb" }, priority = 0, description = "Internal Notes Smoke Check")
	public void verifyInternalNotes() throws Exception {

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Add an existing patient to workbook
		workbookPageObj.searchAllAndAddToWorkBook(patientMRNToBePassedForCaseHistory);

		// Search the same patient from workbook
		workbookPageObj.searchPatientOnWorkbookOnly(patientMRNToBePassedForCaseHistory);

		// Open internal notes,Add a note & close
		internalNotesPageobj.addInternalNotes(internalNoteText, internalNoteModalTitle);

		// Navigate to Build page from Workbook Page
		workbookPageObj.navigateToBuildPageFromWorkbook();

		// Open the internal note,check the previous note & close
		internalNotesPageobj.checkPreviousInternalNote(internalNoteText, internalNoteModalTitle);

		// Logout
		loginPageObj.logout();

	}
	
	@Test(groups = { "Patient Summary", "transitionCore", 
			"commonServices","caretransitionweb" }, priority = 0, description = "Patient Summary Smoke Flow")
	public void verifyPatientSummary() throws Exception {

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Add an existing patient to workbook
		workbookPageObj.searchAllAndAddToWorkBook(patientMRNToBePassedForCaseHistory);

		// Search the same patient from workbook
		workbookPageObj.searchPatientOnWorkbookOnly(patientMRNToBePassedForCaseHistory);
		
		//Open Patient Summary 
		commonPageObj.openModalsViaDownwardArrowIconBesidePatientName("Patient Summary");
		
		//Verify patient Summary
		patientSummaryPageobj.verifyPatientSummary();

		// Logout
		loginPageObj.logout();

	}
	
	@Test(groups = { "Address Book", "audit", 
			"addressBookWeb","config","eventing","identity",
			"patientStore","PDB","transitionCore","transitionSecurity","CAS","commonServices"}, priority = 0, description = "Address Book basic Smoke Flow")
	public void verifyAddressBookSmoke() throws Exception {

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Click on Settings Icon
		loginPageObj.clickSettingsCog();

		// Navigate To The Address Book Page
		addressBookPageobj.clickOnAddressBookSettingsCog();

		// Verify the Address Book Smoke 
		addressBookPageobj.verifyAddressBookSmoke(organizationID);

		// Logout
		loginPageObj.logout();

	}

	@Test(groups = { "manualReferral", "patientStore", "PDB",
			"coordinatedCare" }, priority = 0, description = "create Manual Referral and assert referral details")
	public void manualReferralSmoke() throws Exception {

		// login to intake
		loginPageObj.login(intakeUserName, intakeUserPassword);

		// create manual referral
		manualReferralModalPageObj.clickOnAddNewReferralButton();
		PatientInfoBean referralDetails = manualReferralModalPageObj
				.enterMandatoryFieldsInManualReferral(referralSource, beddedLOC, provider);
		manualReferralModalPageObj.enterAdditionalFieldsForSmoke(referralDetails);
		referralName = referralDetails.getReferralName();
		manualReferralModalPageObj.clickOnSaveManualReferralButton(referralDetails);

		// go to created manual referral details page
		intakeDashboardPageObj.clickReferralOnDashboard(referralName);

		// verify details
		intakeReferralDetailsPageObj.verifyReferralDetailsForManualReferralSmoke(referralDetails);

		// logout
		loginPageObj.logout();

	}


	@Test(groups = { "eventing", "patientStore", "PDB", "coordinatedCare", "caretransitionweb",
			"transitionPrintService" }, priority = 0, description = "D2<->Intake comm,Email notification,Arhcive-unarchive")
	public void d2IntakeSmoke() throws Exception {
		
		//closing all other tabs to avoid errors while switching tabs for doc print and OME
		closeAllSubTabs();

		String messageFromIntakeToDischarge = randomStringGenerator(20);
		String messageFromDischargeToIntake = randomStringGenerator(20);
		String startTime;
		int notificationCount = 0;

		// Login to discharge
		loginPageObj.login(dischargeIntakeSmokeDischargeUser, dischargeIntakeSmokeDischargeUserPassword);

		// add patient to workbook
		referralDetails = commonPageObj.createPatientForSmoke(escString, mirthURL);
		referralName = referralDetails.getReferralName();
		String referralFullName = referralDetails.getFullName();

		// navigate to build page
		commonPageObj.navigateTo("build");

		// update packet to home health loc
		commonPageObj.updateReferralPacket(homeHealthPOS, homeHealthLOC);

		// attach form
		dischargeBuildPageObj.addRemoveFormToPacket(dischargeIntakeSmokeForm);

		// navigate to refer page
		commonPageObj.navigateTo("refer");

		// select keyservice and send referral to provider
		dischargeReferPageObj.selectKeyService(homeHealthKeyServices.get(0));
		dischargeReferPageObj.sendReferralPacket(provider, "ZZ");

		// logout
		loginPageObj.logout();

		// login to intake
		loginPageObj.login(intakeUserName, intakeUserPassword);

		// go to referral details page
		intakeDashboardPageObj.searchAndClickOnReferral(referralDetails);

		// remove old keyservice and add new keyservice
		intakeReferralDetailsPageObj.addRemoveKeyServices(homeHealthKeyServices);

		// assert updated keyservice in referral details
		assertEquals(intakeReferralDetailsPageObj.getKeyServices(), homeHealthKeyServices.get(1));

		// verify referral details
		intakeReferralDetailsPageObj.verifyReferralDetailsForDischargeIntakeSmoke(referralDetails, referralSource,
				homeHealthLOC);

		// verify doc view and print
		commonPageObj.navigateTo("referralDocuments");

		intakeReferralDocumentTabPageObj.clickDocumentName(dischargeIntakeSmokeForm);
		intakeReferralDocumentTabPageObj.verifyViewDocument(referralName);
		intakeReferralDocumentTabPageObj.selectDocumentCheckBox(dischargeIntakeSmokeForm);
		intakeReferralDocumentTabPageObj.printPreviewDocument(referralName);

		// navigate back to connect tab
		commonPageObj.navigateTo("referralConnect");
		
		startTime = getFormattedCurrentTimestamp("MM/dd/YYYY HH:mm", "UTC");

		// update status from intake and assert
		intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(provider,
				Constants.DECISION_PENDING_AUTHORIZATION);
		assertEquals(intakeReferralConnectTabPageObj.getProviderStatus(provider),
				Constants.DECISION_PENDING_AUTHORIZATION);
		notificationCount++;
		intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(provider, Constants.DECISION_PENDING_REVIEW);
		assertEquals(intakeReferralConnectTabPageObj.getProviderStatus(provider), Constants.DECISION_PENDING_REVIEW);
		notificationCount++;
		intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(provider, Constants.REQUEST_MORE_INFORMATION);
		assertEquals(intakeReferralConnectTabPageObj.getProviderStatus(provider), Constants.REQUEST_MORE_INFORMATION);
		notificationCount++;
		String anticipatedStartOfCare = intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(provider,
				Constants.ACCEPT);
		assertEquals(intakeReferralConnectTabPageObj.getProviderStatus(provider), Constants.ACCEPT);
		assertEquals(intakeReferralConnectTabPageObj.getAnticipatedStartOfCare(provider), anticipatedStartOfCare);
		notificationCount++;
		intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(provider, Constants.DECLINE);
		assertEquals(intakeReferralConnectTabPageObj.getProviderStatus(provider), Constants.DECLINE);
		notificationCount++;
		intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(provider, Constants.REOPEN);
		assertEquals(intakeReferralConnectTabPageObj.getProviderStatus(provider), Constants.REOPEN);
		// discharge user does not get notification for referral reopen
		// notificationCount++;

		// send message
		intakeReferralConnectTabPageObj.sendMessage(provider, messageFromIntakeToDischarge);
		notificationCount++;

		// logout
		loginPageObj.logout();

		// navigate to OME
		omePageObj.navigateToOme();
		omePageObj.setOmeColumns();

		// set destination
		omePageObj.setDestination(dischargeIntakeSmokeDischargeUserEmail);

		// set created after
		omePageObj.setCreatedAfter(startTime);

		// assert number of messages
		assertEquals(omePageObj.getMessagesCount(), notificationCount);

		// assert notifications status completed for all notifications
		for (int i = 1; i <= notificationCount; i++) {
			assertEquals(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_STATUS), "COMPLETED");
		}
		
		// get templates
		List<String> templateNames = new ArrayList<String>();
		for (int i = 1; i <= notificationCount; i++) {
			templateNames.add(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_TEMPLATE));			
		}

		// assert new message notification count
		assertEquals(Collections.frequency(templateNames, Constants.DISCHARGE_NEW_MESSAGE_NOTIFICATION_TEMPLATE), 1);

		// assert status update notification count
		assertEquals(
				Collections.frequency(templateNames, Constants.DISCHARGE_ACCEPT_STATUS_UPDATE_NOTIFICATION_TEMPLATE),
				1);
		assertEquals(
				Collections.frequency(templateNames, Constants.DISCHARGE_DECLINE_STATUS_UPDATE_NOTIFICATION_TEMPLATE),
				1);
		assertEquals(Collections.frequency(templateNames,
				Constants.DISCHARGE_PENDING_AUTH_STATUS_UPDATE_NOTIFICATION_TEMPLATE), 1);
		assertEquals(Collections.frequency(templateNames,
				Constants.DISCHARGE_PENDING_REVIEW_STATUS_UPDATE_NOTIFICATION_TEMPLATE), 1);
		assertEquals(Collections.frequency(templateNames,
				Constants.DISCHARGE_REQUEST_INFO_STATUS_UPDATE_NOTIFICATION_TEMPLATE), 1);
		
		//closing all other tabs to avoid errors while switching tabs for doc print and OME
		closeAllSubTabs();

		// login to discharge
		loginPageObj.login(dischargeIntakeSmokeDischargeUser, dischargeIntakeSmokeDischargeUserPassword);

		// go to connect page
		workbookPageObj.navigateToPatientPage(referralName, "Connect");

		// assert hospital status is Notified.
		assertEquals(dischargeConnectPageObj.getHospitalStatus(provider), Constants.DISCHARGE_HOSPITAL_STATUS_NOTIFIED);

		// assert provider status is Reopened.
		assertEquals(dischargeConnectPageObj.getProviderStatus(provider), Constants.REOPEN);

		// assert message from intake
		List<String> messages = dischargeConnectPageObj.getProviderMessagesFromGlobalMessagingModal(provider);
		assertTrue(messages.contains("Decline reason: Patient Too Complex"));
		assertTrue(messages.contains(messageFromIntakeToDischarge));
		assertTrue(messages.contains("--Anticipated Start of Care " + anticipatedStartOfCare.replaceAll("/", "-")));
		
		// reset start date and notification counter
		startTime = getFormattedCurrentTimestamp("MM/dd/YYYY HH:mm", "UTC");
		notificationCount = 0;
		templateNames.clear();

		// assert status updates from intake in referral history
		List<String> referralHistory = dischargeConnectPageObj.getReferralHistory(provider);
		assertTrue(referralHistory.contains("Provider referral status changed to " + Constants.RECEIVED));
		assertTrue(referralHistory
				.contains("Provider referral status changed to " + Constants.DECISION_PENDING_AUTHORIZATION));
		assertTrue(
				referralHistory.contains("Provider referral status changed to " + Constants.DECISION_PENDING_REVIEW));
		assertTrue(
				referralHistory.contains("Provider referral status changed to " + Constants.REQUEST_MORE_INFORMATION));
		assertTrue(referralHistory.contains("Provider referral status changed to " + Constants.ACCEPT));
		assertTrue(referralHistory.contains("Provider referral status changed to " + Constants.DECLINE));
		assertTrue(referralHistory.contains("Provider referral status changed to " + Constants.REOPEN));

		// update status from discharge
		dischargeConnectPageObj.updateStatus(Constants.SUSPEND);
		notificationCount++;
		dischargeConnectPageObj.updateStatus(Constants.DELAY_EDD);
		notificationCount++;
		dischargeConnectPageObj.updateStatus(Constants.CANCEL);
		notificationCount++;
		dischargeConnectPageObj.updateStatus(Constants.REOPEN_REFERRAL);
		notificationCount++;
		dischargeConnectPageObj.updateStatus(Constants.BOOK);
		notificationCount++;

		// send message from discharge
		dischargeConnectPageObj.clickOnMessageIconConnectTab(provider);
		dischargeConnectPageObj.sendMsgFromDischarge(messageFromDischargeToIntake);
		dischargeConnectPageObj.closeMessageModal();
		notificationCount++;

		// logout
		loginPageObj.logout();

		// assert notifications for intake user for status and new message from
		// discharge
		// navigate to OME
		omePageObj.navigateToOme();

		// set destination
		omePageObj.setDestination(intakeUserEmail);

		// set created after
		omePageObj.setCreatedAfter(startTime);

		// assert number of messages
		assertEquals(omePageObj.getMessagesCount(), notificationCount);

		// assert notifications status completed for all notifications
		for (int i = 1; i <= notificationCount; i++) {
			assertEquals(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_STATUS), "COMPLETED");
		}

		// assert templates
		for (int i = 1; i <= notificationCount; i++) {
			templateNames.add(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_TEMPLATE));
		}

		// assert new message notification count
		assertEquals(Collections.frequency(templateNames, Constants.INTAKE_NEW_MESSAGE_NOTIFICATION_TEMPLATE), 1);

		// assert status update notification count
		assertEquals(Collections.frequency(templateNames, Constants.INTAKE_STATUS_UPDATE_NOTIFICATION_TEMPLATE), 5);
		
		//closing all other tabs to avoid errors while switching tabs for doc print and OME
		closeAllSubTabs();

		// login to intake
		loginPageObj.login(intakeUserName, intakeUserPassword);

		// go to referral details page
		intakeDashboardPageObj.searchAndClickOnReferral(referralDetails);

		// assert status at Intake
		assertEquals(intakeReferralConnectTabPageObj.getHospitalStatus(provider),
				Constants.BOOK_DISPLAY_VALUE_AT_INTAKE);
		assertEquals(intakeReferralConnectTabPageObj.getProviderStatus(provider), Constants.REOPEN);

		// update intake status to Accepted
		anticipatedStartOfCare = intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(provider,
				Constants.ACCEPT);
		assertEquals(intakeReferralConnectTabPageObj.getAnticipatedStartOfCare(provider), anticipatedStartOfCare);

		// archive accepted and booked home health referral
		intakeReferralConnectTabPageObj.selectProvider(provider);
		intakeReferralConnectTabPageObj.archiveAcceptedAndBookedHHReferral();

		// assert referral is not present on dashboard - from dashboard search
		assertFalse(intakeDashboardPageObj.referralSearch(referralDetails));

		// go to archive page
		commonPageObj.navigateTo("archive");

		// reactivate referral
		intakeArchivePageObj.reactivateReferral(referralName);

		// go back to dashboard
		commonPageObj.navigateTo("dashboard");

		// assert referral is present on dashboard - under accepted tab
		commonPageObj.navigateTo("accepted");
		assertTrue(intakeDashboardPageObj.isReferralPresent(referralName));

		// assert reactivated badge
		assertTrue(intakeDashboardPageObj.isReactivateBadgePresent(referralName));
		assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName).contains("Reactivated: " + getFormattedCurrentTimestamp("MM/dd/yy hh:", "EST")));
		assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName).contains("by " + intakeUserFullName));

		// logout
		loginPageObj.logout();

	}

	@Test(groups = { "forms", "TDS",
			"coordinatedCare" }, priority = 1, description = "Verify Send GOBF after attaching a form to the referral packet and fax history")
	public void faxHistoryGOBFSmoke() throws Exception {
// login to D2
		loginPageObj.login(d2UserName, d2PassWord);
// create ADT Patient
		PatientInfoBean patientDetails = commonPageObj.createPatientForSmoke(testData.getString("escString"),
				testData.getString("mirthURL"));		
		commonPageObj.navigateTo("build");
		waitForLoaderToDisappear();
		// Prerequisites for GOBF
		commonPageObj.updateReferralPacket(homeHealthPOS, homeHealthLOC);
		dischargeBuildPageObj.addRemoveFormToPacket(dischargeIntakeSmokeForm);
		commonPageObj.navigateTo("refer");
		dischargeReferPageObj.sendReferralPacket(provider, "ZZ");
		commonPageObj.navigateTo("build");

		// GOBF and Fax History Smoke
		dischargeBuildPageObj.sendAFax(dischargeIntakeSmokeForm);
		scrollToTop();
		// Forms Smoke Validation
		clickElement(prepareWebElementWithDynamicXpath(dischargeBuildPageObj.editButtonByFormName, dischargeIntakeSmokeForm),
				"Edit Button for Form");
		waitForNewWindow(1);
		switchToNewWindow();
		Assert.assertTrue(getURL().contains("forms"));
		switchToNewWindow();
		closeAllSubTabs();
		clickElement(prepareWebElementWithDynamicXpath(dischargeBuildPageObj.signButtonByFormName, dischargeIntakeSmokeForm),
				"Sign Button for Form");
		clearAndInput(dischargeBuildPageObj.eSignatureInput, "Form Password", d2PassWord);
		clickElement(dischargeBuildPageObj.esignModalSignButton, "Sign Button");
		waitForLoaderToDisappear();
		Assert.assertFalse(isElementPresent(
				prepareWebElementWithDynamicXpath(dischargeBuildPageObj.signButtonByFormName, dischargeIntakeSmokeForm),
				"Sign Button for Form"));
		//Fax History Validation- Since fax takes time to process forms testing is done in between
		clickElement(dischargeBuildPageObj.faxHistory, "Fax History");
		List<LinkedHashMap<String, String>> faxHistoryTable = dischargeBuildPageObj.readFaxHistoryDetails();
		reportLog("Fax history:   " + faxHistoryTable);
		assertTrue(faxHistoryTable.toString().contains("In Progress")||faxHistoryTable.toString().contains("Sent OK"));	
		clickElement(dischargeBuildPageObj.faxHistoryModalClose, "Fax History Modal close");
		// logout
		loginPageObj.logout();

	}
	
	@Test(priority=2, groups = {"providerprofile","coordinatedcare","PDB","PML"})
	public void intakeProviderProfileSmoke() throws Exception {
		
		// login to intake
		loginPageObj.login(profileintakeUserName, profileintakeUserPassword);
		
		//Navigate to Provider Profile page
		commonPageObj.navigateTo("profile");
		
		//Select the Provider 
		intakeProfileObj.clickOnProviderforProfile(profileprovider);
		
		//Edit the Phone number field and verify saved correctly
		intakeProfileObj.clickOnEditProfileButton();
		waitForLoaderToDisappear();
		intakeProfileObj.inputPhoneNumber(phonenumber);
		String fomattedPhoneNumber = "(" + phonenumber.substring(0, 3) + ") " + phonenumber.substring(3, 6) + "-" + phonenumber.substring(6);
		clickElement(intakeProfileObj.getEditProfileSaveButtonxpath(), "Edit Profile Save Button");
		waitForLoaderToDisappear();
		assertEquals(intakeProfileObj.getPhoneNumberValue(), fomattedPhoneNumber);
		//Assert address is present 
        assertEquals(intakeProfileObj.getAdressLine1Text().trim(), addressline1text);
        assertEquals(intakeProfileObj.getAdressLine2Text().trim(), addressline2text);
        assertEquals(intakeProfileObj.getCityText().trim(), citytext);
        assertEquals(intakeProfileObj.getCountyText().trim(), countytext);
        assertEquals(intakeProfileObj.getMetroText().trim(), metrotext);
        //Edit Clinical Service and verify
        intakeProfileObj.clickOnServiceTab();
        intakeProfileObj.clickOnEditClinicalService();
        waitForLoaderToDisappear();
        intakeProfileObj.clickOnSaveforClicnicalService();
        waitForLoaderToDisappear();
        //Edit Physician details and verify
        intakeProfileObj.clickOnPhysicianTab();
        intakeProfileObj.clickEditButtonOnPhysicianTab();
        waitForLoaderToDisappear();
        intakeProfileObj.inputPhysicianNameField(PhysicianName);
        intakeProfileObj.clickOnSaveforPhysician();
        waitForLoaderToDisappear();
        assertEquals(intakeProfileObj.getPhysicianNameText(), PhysicianName);
        
        //Edit Payer information and Verify
        intakeProfileObj.clickOnPayersTab();
        intakeProfileObj.clickOnEditPayersButton();
        waitForLoaderToDisappear();
        intakeProfileObj.inputPayersName(payerName);
        intakeProfileObj.clickOnSaveButtonEditPayerModal();
        waitForLoaderToDisappear();
        assertEquals(intakeProfileObj.getPayerNameText(), payerName);
        
        // logout
     	loginPageObj.logout();
        
		
	}

	@Test(priority=6, groups = {"intake document manager","uploadservice", "coordinatedcare","eventing"})
	public void intakeUploadDocumentSmoke() throws Exception{
		
		// login to intake
		loginPageObj.login(intakeDocUploadUser, intakeDocUploadPass);
		
		//Navigate to Document Manager Tab
		//upload the doc
		commonPageObj.navigateTo("documents");
		reportLog("directory: " + System.getProperty("user.dir"));
		
		filePathToUpload = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "com" + File.separator + "qa" + File.separator + "testdata"
				+ File.separator + "filesToUpload" + File.separator + fileName;
		
		documentManagerPageObj.uploadWithSendKeys(filePathToUpload);
		
		//Verify the upload successful Toast Message
		//assertEquals(documentManagerPageObj.getSuccessfulToastMessagetext().trim(), "File: "+fileName+" successfully uploaded.");
		
		//Verify the document is displayed on top of the Unassigned list
		assertTrue(documentManagerPageObj.getDocNameFromList(0).contains(fileName));
		
		loginPageObj.logout();
	}
		
		
	@Test(priority=3, groups = {"intakeSSO"},  dataProvider = "intake-sso-authentication-protocols", dataProviderClass = DataProviders.class)
	public void intakeSsoSmoke(String authProtocol) {
		
		String ssoLaunchUrl = null;
		if(authProtocol.equals("OIDC"))
			ssoLaunchUrl = oidcSsoLaunchUrl;
		else if(authProtocol.equals("SAML"))
			ssoLaunchUrl = samlSsoLaunchUrl;
		
		//launch intake sso for specified authentication method via Okta
		loginPageObj.intakeSsoLaunchViaOkta(ssoLaunchUrl, intakeSsoUserOktaUsername, intakeSsoUserOktaPassword);
		waitForLoaderToDisappear();
		
		//assert user landed on intake dashboard page
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent());
		
		//assert provider
		intakeDashboardPageObj.clickOnFilterButton("All Providers");
		assertTrue(intakeSsoProviders.containsAll(intakeDashboardPageObj.getAllProvidersFromProviderFilter())
				&& intakeDashboardPageObj.getAllProvidersFromProviderFilter().containsAll(intakeSsoProviders));
		intakeDashboardPageObj.clickCancelButton();
		
		//assert archive tab is loading
		commonPageObj.navigateTo("archive");
		assertTrue(intakeArchivePageObj.isArchivePagePresent());
		
		//logout from Intake and Okta
		loginPageObj.logout();
		loginPageObj.navigateToOkta();
		loginPageObj.logoutFromOkta();
		
	}

	@Test(priority=0, groups = {"quickcase","coordinatedcare","PDB"},description = "Verify quickcase - send unconnected referral and accept the referral from quickcase")
	public void QCSmoke() throws Exception {
		List<String> unconnectedProviderList = new ArrayList<>();
		unconnectedProviderList.add(unconnectedProvider);
		// Login to Discharge
		loginPageObj.login(qcUsername, qcPassword);
		//Add a Patient
		workbookPageObj.addAPatientWithMandatoryFieldsOnly();
		// Navigate to Refer page
		commonPageObj.navigateTo("refer");
		String did= commonPageObj.extractDischargeId(getURL());
		reportLog("test did: "+did);
		//Select POS
		dischargeBuildPageObj.selectServiceType("Home Services");
		//select LOC
		dischargeBuildPageObj.selectLOCSNF("Home Health Agency");

		dischargeReferPageObj.sendReferralByNameProviderName(unconnectedProviderList);
		//Navigate to OME
		omePageObj.navigateToOme();
		//Filter record using dischargeId
		omePageObj.filterRecordByDischargeId(did);
		String[] referralAccessDetails=omePageObj.getReferralCodeAndPinAndSwitchToDischargeTab();
		quickCasePageObj.loginToQuickcase(referralAccessDetails);
		quickCasePageObj.submitDetailsOnVerifyPage(unconnectedProvider);
		quickCasePageObj.acceptReferralOnReferralPage();
		switchToSpecificTab(0);
		// Discharge the patient
		patientDischargePageobj.navigateToDischargePage();
		patientDischargePageobj.completeDischargeProcess();
		// Logout
		loginPageObj.logout();
	}

}
