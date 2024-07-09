package com.qa.testcases;

import static com.qa.util.TestUtil.*;
import static org.testng.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import com.qa.pages.*;
import com.qa.util.*;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.qa.base.TestBase;

import com.qa.pages.AddressBookPage;
import com.qa.pages.CommonPage;
import com.qa.pages.DischargeBuildPage;
import com.qa.pages.DischargeConnectPage;
import com.qa.pages.DischargeReferPage;
import com.qa.pages.DocumentManagerPage;
import com.qa.pages.IntakeArchivePage;
import com.qa.pages.IntakeDashboardPage;
import com.qa.pages.IntakeInternalNoteNotificationPage;
import com.qa.pages.IntakeProfilePage;
import com.qa.pages.IntakeReferralConnectTabPage;
import com.qa.pages.IntakeReferralDetailsPage;
import com.qa.pages.IntakeReferralDocumentTabPage;
import com.qa.pages.IntakeReferralInternalNoteTabPage;
import com.qa.pages.InternalNotesPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ManualReferralModalPage;
import com.qa.pages.OmePage;
import com.qa.pages.PatientDischargePage;
import com.qa.pages.PatientSummaryPage;
import com.qa.pages.QuickcasePage;
import com.qa.pages.WorkbookPage;

public class ModularRegression extends TestBase 
{
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
	LegacySearchPage legacySearchPageObj;

	// declare testdata
	String intakeCIUser;
	String intakeCIPassword;
	String intakeReadOnlyUser;
	String intakeReadOnlyPassword;
	String intakeReadOnlyArchiveUser;
	String intakeReadOnlyAechivePassword;
	List<String> legacyPatientData;
	String dischargeUser1;
	String dischargeUser1Password;
	String dischargeUser1FullName;
	String dischargeUser1NotificationEmail;
	String dischargeUser1NotificationPhone;
	String archiveProvider;
	String beddedPOS;
	String beddedLOC;
	String intakeUser1;
	String intakeUser1Password;
	String intakeUser1FullName;
	String intakeUser1NotificationEmail;
	String intakeUser1NotificationPhone;
	String qcUsername;
	String qcPassword;
	String unconnectedProvider;
	String caseHistoryUsername;
	String caseHistoryPassword;
	String docRenameFreeText;
	String emailRegUsername;
	String referralSource;
	String archiveProviderRefTest;
	String providerNameToSendReferral;
	String emailRegPassword;
	String emailRegUserId;
	String intakeTgInUser1;
	String intakeTgInUser1Pass;
	String intakeTgInUser2;
	String intakeTgInUser2Pass;
	String intakeTgInUsername1;
	String intakeTgInUsername2;
	String tginConnected;
	String dischargeTgInUser;
	String dischargeTgInUserPass;
	String intakeHelpUser;
	String intakeHelpUserPass;
	String dischHelpUser;
	String dischHelpUserPass;
	String pmlRegUser;
	String pmlRegPass;
	List<String> providerList;

	// shared testdata
	PatientInfoBean referralDetails;
	String referralName;
	String d2UserName;
	String d2PassWord;
	String legacySearchUsername;
	String legacySearchPassword;
	String escString, mirthURL;
	String tssHost;
	String homeHealthPOS,homeHealthLOC;
	String dischargeIntakeSmokeForm;
	List<String> homeHealthKeyServices;
	String provider,unConnectedProvider;

	String patientMRNNumber;
	String referralLastName;


	@BeforeClass(alwaysRun = true)
	public void intialize() throws MalformedURLException 
	{
		className = this.getClass().getSimpleName();
		// initialize browser
		initialization();
		// initialize testdata JSON object
		testDataFile = JsonReader.getTestDataJSON(className);
		testDataEnv = testDataFile.getJSONObject(env);
		testData = testDataEnv.getJSONObject(browserName);

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
		legacySearchPageObj = new LegacySearchPage();

		// initialize testdata variables
		intakeCIUser = testData.getString("intakeCI_User");
		intakeCIPassword = testData.getString("intakeCI_Password");
		intakeReadOnlyUser = testData.getString("intakeReadonly_User");
		intakeReadOnlyPassword = testData.getString("intakeReadonly_Password");
		intakeReadOnlyArchiveUser = testData.getString("intakeReadonlyWithArchive_User");
		intakeReadOnlyAechivePassword = testData.getString("intakeReadonlyWithArchive_Password");
		legacyPatientData = convertJsonArrayToList(testData.getJSONArray("legacyPatientData"));
		escString = testDataEnv.getString("escString");
		mirthURL = testDataEnv.getString("mirthURL");
		tssHost = testDataEnv.getString("TSS_HOST");
		caseHistoryUsername = testData.getString("caseHistoryUsername");
		caseHistoryPassword = testData.getString("caseHistoryPassword");
		docRenameFreeText = testDataEnv.getString("docRenameFreeText");
		providerNameToSendReferral =  testDataEnv.getString("providerNameToSendReferral");
		archiveProviderRefTest = testDataEnv.getString("archiveProviderRefTest");
		referralSource = testDataEnv.getString("referralSource");



		// initialize testdata variables
		d2UserName = testData.getString("d2UserName");
		d2PassWord = testData.getString("d2PassWord");
		legacySearchUsername = testData.getString("legacySearchUsername");
		legacySearchPassword = testData.getString("legacySearchPassword");
		dischargeUser1 = testData.getString("dischargeUser1");
		dischargeUser1Password = testData.getString("dischargeUser1Password");
		dischargeUser1FullName = testData.getString("dischargeUser1FullName");
		dischargeUser1NotificationEmail = testData.getString("dischargeUser1NotificationEmail");
		dischargeUser1NotificationPhone = testData.getString("dischargeUser1NotificationPhone");
		archiveProvider = testDataEnv.getString("archiveProvider");
		beddedPOS = testDataEnv.getString("beddedPOS");
		beddedLOC = testDataEnv.getString("beddedLOC");
		intakeUser1 = testData.getString("intakeUser1");
		intakeUser1Password = testData.getString("intakeUser1Password");
		intakeUser1FullName = testData.getString("intakeUser1FullName");
		intakeUser1NotificationEmail = testData.getString("intakeUser1NotificationEmail");
		intakeUser1NotificationPhone = testData.getString("intakeUser1NotificationPhone");
		qcUsername  = testData.getString("qcUsername");
		qcPassword = testData.getString("qcPassword");
		emailRegUsername = testData.getString("emailRegUsername");
		emailRegPassword = testData.getString("emailRegPassword");
		emailRegUserId = testData.getString("emailRegUserId");
		intakeTgInUser1 = testData.getString("intakeTgInUser1");
		intakeTgInUser1Pass =testData.getString("intakeTgInUser1Pass");
		intakeTgInUser2=testData.getString("intakeTgInUser2");
		intakeTgInUser2Pass=testData.getString("intakeTgInUser2Pass");
		intakeTgInUsername1=testData.getString("intakeTgInUsername1");
		intakeTgInUsername2=testData.getString("intakeTgInUsername2");
		tginConnected=testData.getString("tginConnected");
		dischargeTgInUser=testData.getString("dischargeTgInUser");
		dischargeTgInUserPass=testData.getString("dischargeTgInUserPass");
		intakeHelpUser=testData.getString("intakeHelpUser");
		intakeHelpUserPass=testData.getString("intakeHelpUserPass");
		dischHelpUser=testData.getString("dischHelpUser");
		dischHelpUserPass=testData.getString("dischHelpUserPass");
		pmlRegUser = testData.getString("pmlRegUser");
		pmlRegPass = testData.getString("pmlRegPass");
		providerList = convertJsonArrayToList(testData.getJSONArray("providerList"));

		escString = testDataEnv.getString("escString");
		mirthURL = testDataEnv.getString("mirthURL");
		unconnectedProvider = testDataEnv.getString("qcUnconnectedProvider");
		homeHealthPOS = testDataEnv.getString("homeHealthPOS");
		homeHealthLOC = testDataEnv.getString("homeHealthLOC");
		homeHealthKeyServices = convertJsonArrayToList(testDataEnv.getJSONArray("homeHealthKeyServices"));
		dischargeIntakeSmokeForm = testDataEnv.getString("dischargeIntakeSmokeForm");
		provider = testDataEnv.getString("provider");
		unConnectedProvider=testDataEnv.getString("unConnectedProvider");
		referralSource = testDataEnv.getString("referralSource");

	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshotInCaseOfFailure(ITestResult result) throws Exception 
	{
		// Check if the test case failed or was skipped and take screenshot
		if (result.getStatus() == result.FAILURE || result.getStatus() == result.SKIP) 
		{
			if (result.getStatus() == result.FAILURE)
				reportLog("TEST FAILED: " + result.getName());
			else
				reportLog("TEST SKIPPED: " + result.getName());
			String screenshotPath = getScreenshot(getdriver(), result.getName());
			result.setAttribute("screenshotPath", screenshotPath); // sets the value the variable/attribute
			// screenshotPath as the path of the screenshot
		} 
		else if (result.getStatus() == result.SUCCESS) 
		{
			reportLog("TEST PASSED: " + result.getName());
		}
	}

	@AfterClass(alwaysRun = true)
	public void cleanup() 
	{
		reportLog("quitting browser");
		getdriver().quit();
		reportLog("clearing threadlocal");
		unload();
	}

	@Test
	public void identityRegressionTestCase() throws Exception {

		// login with Intake CI user and verify the login Success
		loginPageObj.login(intakeCIUser, intakeCIPassword);
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent(), "Intake Dashboard presence");
		loginPageObj.logout();

		// login with intake Read only user
		loginPageObj.login(intakeReadOnlyUser, intakeReadOnlyPassword);
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent(), "Intake Dashboard presence");
		loginPageObj.logout();

		// Login with intake Read only with Archive User
		loginPageObj.login(intakeReadOnlyUser, intakeReadOnlyPassword);
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent(), "Intake Dashboard presence");
		loginPageObj.logout();

		// Login with intake Community Service User
		loginPageObj.login(intakeReadOnlyUser, intakeReadOnlyPassword);
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent(), "Intake Dashboard presence");
		loginPageObj.logout();

		// Login with Intake Lite User
		loginPageObj.login(intakeReadOnlyUser, intakeReadOnlyPassword);
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent(), "Intake Dashboard presence");
		loginPageObj.logout();

		// Login with D2 User
		loginPageObj.login(intakeReadOnlyUser, intakeReadOnlyPassword);
		// TBD workbook assertion
		loginPageObj.logout();

		// Login with recast User
		loginPageObj.login(intakeReadOnlyUser, intakeReadOnlyPassword);
		// TBD clicking on Chicklet
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent(), "Intake Dashboard presence");

		loginPageObj.logout();

		// Login with insight User
		loginPageObj.login(intakeReadOnlyUser, intakeReadOnlyPassword);
		// TBD clicking on Chiklet
		assertTrue(intakeDashboardPageObj.isCentralizedIntakeDashboardPresent(), "Intake Dashboard presence");
		loginPageObj.logout();

		//

		// Login with Intake SSO user
		// TBD

		// Login with D2 SSO
		// TBD

		// Login with Dispo Code user
		// TBD

	}

	@Test(groups = { "Password Change", "transitionSecurityService","config",
			"PDB", "eventing" }, priority = 0, description = "Document Manager Smoke Check")
	public void passwordChange() throws Exception {

		// Close all existing Tabs if opened
		closeAllSubTabs();

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Click on Settings Icon
		loginPageObj.clickSettingsCog();

		// Click on Change Password Option
		loginPageObj.clickOnChangePasswordOption();

		// Change the password now
		String changedPassword = loginPageObj.changePasswordNow(caseHistoryPassword);

		// Ensure that user is logged out immediately after changing the password
		loginPageObj.checkIfUserLoggedOutAfterPasswordChange();

		// Login Again
		loginPageObj.login(caseHistoryUsername, changedPassword);

		// Click on Settings Icon Again
		loginPageObj.clickSettingsCog();

		// Click on Change Password Option Again
		loginPageObj.clickOnChangePasswordOption();

		// Revert back to the Original Password
		loginPageObj.revertBackTheOriginalPassword(changedPassword, caseHistoryPassword);

		// Login back with the original credentials to confirm
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Now logout after successful login
		loginPageObj.logout();

	}


	@Test(groups = { "Document Manager", "audit", "config", "transitionDocument", "transitionCore", "patientStore",
			"commonServices", "careTransitionWeb" }, priority = 0, description = "Document Manager Smoke Check")
	public void verifyDocumentManager() throws Exception {

		// Close all existing Tabs if opened
		closeAllSubTabs();

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Click on Settings Icon
		loginPageObj.clickSettingsCog();

		// Click on Generic document manager link
		loginPageObj.clickOnGenericDocMgrUnderSettingsCog();

		// Rename the doc in the unassigned section
		documentManagerPageObj.renameDocumentFromGenericDocMgrSmokeTest(docRenameFreeText);

		//Navigate back to the workbook page
		documentManagerPageObj.clickOnWorkBookButtonOnHeader();

		//Create a Patient
		referralDetails = commonPageObj.createPatientForSmoke(escString, mirthURL);
		referralLastName = referralDetails.getLastName();
		patientMRNNumber = referralDetails.getMRN();

		//Search the Patient And Navigate to Build page
		workbookPageObj.searchAllAndAddToWorkBook(patientMRNNumber);
		workbookPageObj.searchPatientOnWorkbookOnly(referralLastName);
		workbookPageObj.navigateToBuildPageFromWorkbook();

		//Navigate to Patient Specific Document Manager from Build Page
		documentManagerPageObj.clickPatspecficDocMgrButton();

		//Check whether in unassigned tab or not by default
		documentManagerPageObj.verifyTheTabNameSelectedByDefault();

		//check Rename And Assign a document in unassigned tab
		documentManagerPageObj.checkRenameAndAssignADocumentFromUnAssignedTab(docRenameFreeText);

		// Check Rename And UnAssign a document from Assigned tab
		documentManagerPageObj.checkRenameAndUnAssigningDocumentFromAssignedTab(docRenameFreeText);

		// Delete a Document from Un Assigned tab
		documentManagerPageObj.deleteAAssignedDocument();

		// Delete a Document from Assigned tab
		documentManagerPageObj.deleteunAAssignedDocument();

		// Logout
		loginPageObj.logout();

	}

	@Test(groups = { "Referral Count & Archive search", "patientStore","eventingservice",
			"PDB", "transitionCore", "PAT", "config", "coordinatedcare",
			"userEventService", "audit"}, priority = 0, description = "Referral Count & Archive search from Intake")
	public void referralCountArchiveSearchIntake() throws Exception {

		// Close all existing Tabs if opened
		closeAllSubTabs();

		// Login to Intake
		loginPageObj.login(intakeUser1, intakeUser1Password);

		// Check the referral count in new tab
		int originalCount = intakeDashboardPageObj.getTotalReferralCountInNewTab();

		// logout from Intake
		loginPageObj.logout();

		// Login to Discharge
		loginPageObj.login(caseHistoryUsername, caseHistoryPassword);

		// Create a patient
		waitForLoaderToDisappear();
		commonPageObj.createPatientForRegressionWithRandomData(escString, mirthURL);

		// Navigate to the Refer page
		commonPageObj.navigateTo("build");
		commonPageObj.updateReferralPacket(homeHealthPOS, homeHealthLOC);
		commonPageObj.navigateTo("refer");

		// Send a referral to the same Intake provider
		dischargeReferPageObj.sendReferralPacket(providerNameToSendReferral, "ZZ");

		// Logout from Discharge
		loginPageObj.logout();

		// Login to Intake Again
		loginPageObj.login(intakeUser1, intakeUser1Password);

		// Check the new tab count
		int countAfterSendingReferral = intakeDashboardPageObj.getTotalReferralCountInNewTab();
		reportLog("Original count in new tab before referrals sent = "+originalCount);
		reportLog("Count in new tab after referrals sent = "+countAfterSendingReferral);
		Assert.assertEquals(countAfterSendingReferral, originalCount+1);

		// Add a manual referral
		manualReferralModalPageObj.clickOnAddNewReferralButton();
		PatientInfoBean referralDetails = manualReferralModalPageObj
				.enterMandatoryFieldsInManualReferral(referralSource, beddedLOC, provider);
		manualReferralModalPageObj.enterAdditionalFieldsForSmoke(referralDetails);
		referralName = referralDetails.getReferralName();
		manualReferralModalPageObj.clickOnSaveManualReferralButton(referralDetails);

		// Come back to the Dashboard
		commonPageObj.navigateTo("dashboard");

		// Check the new tab count
		int countAfterCreatingManualReferral = intakeDashboardPageObj.getTotalReferralCountInNewTab();
		reportLog("Original count in new tab before creation of manual referrals = "+countAfterSendingReferral);
		reportLog("Count in new tab after creation of manual referrals = "+countAfterCreatingManualReferral);
		Assert.assertEquals(countAfterCreatingManualReferral, countAfterSendingReferral+1);

		// Click on Referral to view details
		intakeDashboardPageObj.clickReferralOnDashboard(referralName);

		// Archive the referral
		intakeReferralConnectTabPageObj.selectProvider(archiveProviderRefTest);
		intakeReferralConnectTabPageObj.archiveReferral();

		// Come back to the Dashboard
		commonPageObj.navigateTo("dashboard");

		// Check the new tab count
		int countAfterArchivingReferral = intakeDashboardPageObj.getTotalReferralCountInNewTab();
		reportLog("Original count in new tab before archiving referrals = "+countAfterCreatingManualReferral);
		reportLog("Count in new tab after archiving referrals = "+countAfterArchivingReferral);
		Assert.assertEquals(countAfterArchivingReferral, countAfterCreatingManualReferral-1);

		// Navigate to the Archive tab
		commonPageObj.navigateTo("archive");

		// Search for the referral in Archive tab
		Assert.assertTrue(intakeArchivePageObj.referralSearch(referralDetails));

		// Logout from the application
		loginPageObj.logout();

	}


	@Test()
	public void verifyAccountSettingsUpdate() throws JSONException, InterruptedException, IOException {
		// login to D2
		loginPageObj.login(d2UserName, d2PassWord);
		waitForLoaderToDisappear();
		loginPageObj.clickSettingsCog();
		loginPageObj.clickAccountSettings();
		String faxNumber = FakeDataProvider.getPhoneNumber();
		loginPageObj.inputFaxNumberOnAccountSettingsModal(faxNumber);
		loginPageObj.clickSaveOnAccountSettings();
		loginPageObj.clickSettingsCog();
		loginPageObj.clickAccountSettings();
		String retrievedFax = loginPageObj.getFaxNumber().replaceAll("[^A-Za-z0-9]", "");
		assertEquals(faxNumber, retrievedFax);

	}

	@Test()
	public void verifyReferralHistory() throws JSONException, InterruptedException, IOException {
		// login to D2
		loginPageObj.login(d2UserName, d2PassWord);
		waitForLoaderToDisappear();
		commonPageObj.createPatientForRegressionWithRandomData(escString, mirthURL);
		// navigate to build page
		commonPageObj.navigateTo("build");

		// update packet to home health loc
		commonPageObj.updateReferralPacket(homeHealthPOS, homeHealthLOC);

		// attach form
		dischargeBuildPageObj.addRemoveFormToPacket(dischargeIntakeSmokeForm);

		// navigate to refer page
		commonPageObj.navigateTo("refer");

		// select keyservice and send referral to provider
		//dischargeReferPageObj.selectKeyService(homeHealthKeyServices.get(0));
		dischargeReferPageObj.sendReferralPacket(provider, "ZZ");
		dischargeReferPageObj.sendReferralPacket(unConnectedProvider, "ZZ");
		commonPageObj.navigateTo("connect");
		List<String> connectedPrvdrRefHistory = dischargeConnectPageObj.getReferralHistory(provider);
		assertTrue(connectedPrvdrRefHistory.toString().contains("Referral packet sent"));
		assertTrue(connectedPrvdrRefHistory.toString().contains("Notified"));
		List<String> QCPrvdrRefHistory = dischargeConnectPageObj.getReferralHistory(unConnectedProvider);
		assertTrue(QCPrvdrRefHistory.toString().contains("QuickCase Pending"));
		assertTrue(QCPrvdrRefHistory.toString().contains("Notified"));
	}

	@Test
	public void legacySearchRegression(){
		// login to D2
		loginPageObj.login(legacySearchUsername, legacySearchPassword);
		workbookPageObj.navigateToLegacySearchPage();
		legacySearchPageObj.assertLegacySearchLandingPage();
		legacySearchPageObj.searchWithInvalidData();
		//search with accnumber and validate
		legacySearchPageObj.searchWithValidACC(legacyPatientData.get(3));
		legacySearchPageObj.assertSearchResultStaticData(legacyPatientData);
		//search with mrn and validate
		legacySearchPageObj.searchWithValidMRN(legacyPatientData.get(2));
		legacySearchPageObj.assertSearchResultStaticData(legacyPatientData);
		//search with lname and validate
		String[] patName= legacyPatientData.get(0).split(",");
		legacySearchPageObj.searchWithValidLname(patName[0].trim());
		legacySearchPageObj.assertSearchResultStaticData(legacyPatientData);
		//search with fname and lname
		legacySearchPageObj.searchWithValidFnameLname(patName[1].trim(),patName[0].trim());
		legacySearchPageObj.assertSearchResultStaticData(legacyPatientData);
		//search with ssn
		legacySearchPageObj.searchWithValidSSN(legacyPatientData.get(4));
		legacySearchPageObj.assertSearchResultStaticData(legacyPatientData);
		//case history assert
		legacySearchPageObj.openPatientDropdownAndNavigateTo("ch");
		legacySearchPageObj.assertCaseHistoryModal(legacyPatientData.get(0));
		//pat summary assert
		legacySearchPageObj.openPatientDropdownAndNavigateTo("patSum");
		legacySearchPageObj.assertPatientSummaryModal(legacyPatientData.get(0),legacyPatientData.get(2),legacyPatientData.get(3));

		//logout
		loginPageObj.logout();
	}

	@Test()
	public void verifyArchiveAndReactivateBeddedWorkflow() throws JSONException, InterruptedException, IOException {

		// login to D2
		loginPageObj.login(dischargeUser1, dischargeUser1Password);

		// create patient and send a referral
		referralDetails = commonPageObj.addNewPatientAndSendReferralForRegression(escString, mirthURL, archiveProvider,
				beddedPOS, beddedLOC);
		referralName = referralDetails.getReferralName();

		// navigate to connect page
		commonPageObj.navigateTo("connect");

		// update status to booked
		dischargeConnectPageObj.updateStatus(Constants.BOOK);

		// logout
		loginPageObj.logout();

		// login to Intake
		loginPageObj.login(intakeUser1, intakeUser1Password);

		Map<String, String> statusDetails = new LinkedHashMap<String, String>() {
			{
				put(Constants.RECEIVED, "in progress");
				put(Constants.ACCEPT, "accepted");
				put(Constants.DECLINE, "declined");
			}
		};

		for (Map.Entry<String, String> entry : statusDetails.entrySet()) {

			String status = entry.getKey();
			String statusTab = entry.getValue();

			// go to referral details
			intakeDashboardPageObj.clickReferralOnDashboard(referralName);

			// update status
			if (!status.equals(Constants.RECEIVED))
				intakeReferralConnectTabPageObj.updateStatusForBeddedProvider(archiveProvider, status);

			// archive referral
			intakeReferralConnectTabPageObj.selectProvider(archiveProvider);
			if (status.equals(Constants.ACCEPT)) {
				// assert booked and accepetd referral archive modal
				List<String> bookedProviderList = new ArrayList<>();
				bookedProviderList.add(archiveProvider);
				intakeReferralConnectTabPageObj.assertAcceptedAndBookedBeddedArchiveReferralModal(bookedProviderList);
			} else
				intakeReferralConnectTabPageObj.archiveReferral();

			// assert referral is not present on dashboard
			assertFalse(intakeDashboardPageObj.referralSearch(referralDetails));

			// assert referral is not present on dashboard - under status tab
			commonPageObj.navigateTo(statusTab);
			assertFalse(intakeDashboardPageObj.isReferralPresent(referralName));

			// go to archive page
			commonPageObj.navigateTo("archive");

			// reactivate referral
			intakeArchivePageObj.reactivateReferral(referralName);

			// go back to dashboard
			commonPageObj.navigateTo("dashboard");

			// assert referral is present on dashboard - under status tab
			commonPageObj.navigateTo(statusTab);
			assertTrue(intakeDashboardPageObj.isReferralPresent(referralName));

			// assert reactivated badge
			assertTrue(intakeDashboardPageObj.isReactivateBadgePresent(referralName));
			assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName)
					.contains("Reactivated: " + getFormattedCurrentTimestamp("MM/dd/yy hh:", "EST")));
			assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName)
					.contains("by " + intakeUser1FullName));

		}

		// logout
		loginPageObj.logout();
	}

	@Test(priority=2, groups = {"quickcase","coordinatedcare","PDB"},description = "Verify quickcase - Regression")
	public void QCRegression() throws Exception {
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
		closeAllSubTabs();
		//navigate to connect page
		commonPageObj.navigateTo("connect");
		String status = dischargeConnectPageObj.getProviderStatus(unconnectedProvider);
		assertText("Quickcase Accepted", status);
		quickCasePageObj.loginToQuickcase(referralAccessDetails);
		quickCasePageObj.submitDetailsOnVerifyPage(unconnectedProvider);
		quickCasePageObj.navigateToCoordinatorTab();
		quickCasePageObj.deleteAllCoordinator();
		quickCasePageObj.addACoordinator();
		String coordinatorEmail= "testmeauto1@testnow.com";
		quickCasePageObj.editCoordinator(coordinatorEmail);
		switchToSpecificTab(0);
		closeAllSubTabs();
		//assert OME notification for the coordinator
		//cancel referral and assert ome notification for coordinator
		dischargeConnectPageObj.updateStatus("Cancel");
		//reopen referral and assert ome notification for coordinator
		dischargeConnectPageObj.updateStatus("Re-open Referral");
		//book referral and assert ome notification for coordinator
		dischargeConnectPageObj.updateStatus("Book");
		omePageObj.navigateToOme();
		omePageObj.filterRecordByDischargeId(did);
		//asserting notifications for coordinator
		omePageObj.assertNotificationForDestinationEmail(coordinatorEmail,"QCKCS.Cancel");
		omePageObj.assertNotificationForDestinationEmail(coordinatorEmail,"QCKCS.Notify");
		omePageObj.assertNotificationForDestinationEmail(coordinatorEmail,"QCKCS.Booked");
		quickCasePageObj.loginToQuickcase(referralAccessDetails);
		quickCasePageObj.submitDetailsOnVerifyPage(unconnectedProvider);
		quickCasePageObj.declineReferralOnReferralPage();
		switchToSpecificTab(0);
		closeAllSubTabs();
		refreshPage();
		String newStatus = dischargeConnectPageObj.getProviderStatus(unconnectedProvider);
		assertText("Quickcase Declined", newStatus);
		// Discharge the patient
		patientDischargePageobj.navigateToDischargePage();
		patientDischargePageobj.completeDischargeProcess();
		// Logout
		loginPageObj.logout();
	}

	@Test(priority=3, groups = {"caretransitionweb", "commonServices"},description = "Verify Email Verification Flow - Regression")
	public void emailVerification() throws IOException, InterruptedException {
		final String userEmail="testtest@autotest.com";
		final String newEmail="newemail@test.com";
		//invalidate email for user
		APIUtil.updateAndInvalidateEmail(tssHost,userEmail,emailRegUserId);
		// Login to Discharge
		loginPageObj.login(emailRegUsername, emailRegPassword);
		//email verification modal UI test
		workbookPageObj.verifyEmailVerificationModalUI(userEmail);
		workbookPageObj.clickOnVerifyButtonForEmailVerification();
		//email code verification modal UI test
		workbookPageObj.verifyEmailCodeVerificationModalUI(userEmail);
		//actual verification of email using code from OME
		workbookPageObj.verifyEmailVerificationCode(omePageObj.retrieveEmailVerificationCode(userEmail));
		loginPageObj.logout();
		//invalidate email for user
		APIUtil.updateAndInvalidateEmail(tssHost,userEmail,emailRegUserId);
		loginPageObj.login(emailRegUsername, emailRegPassword);
		//change user recovery email and test its UI
		workbookPageObj.changeUserRecoveryEmailAndVerify(newEmail);
		//verify email code verification modal UI with the changed email
		workbookPageObj.verifyEmailCodeVerificationModalUI(newEmail);
		//actual verification of email using code from OME
		workbookPageObj.verifyEmailVerificationCode(omePageObj.retrieveEmailVerificationCode(newEmail));
		loginPageObj.logout();
	}

	@Test(priority=0, groups = {"eventing","coordinatedcare","patientstore"},description = "archive and reactivate - home health referral")
	public void verifyArchiveAndReactivateHomeHealthWorkflow() throws JSONException, InterruptedException, IOException {

		String anticipatedSOC = null;

		// login to D2
		loginPageObj.login(dischargeUser1, dischargeUser1Password);

		// create patient and send a referral
		referralDetails = commonPageObj.addNewPatientAndSendReferralForRegression(escString, mirthURL, archiveProvider,
				homeHealthPOS, homeHealthLOC);
		referralName = referralDetails.getReferralName();

		// navigate to connect page
		commonPageObj.navigateTo("connect");

		// update status to booked
		dischargeConnectPageObj.updateStatus(Constants.BOOK);

		// logout
		loginPageObj.logout();

		// login to Intake
		loginPageObj.login(intakeUser1, intakeUser1Password);

		Map<String, String> statusDetails = new LinkedHashMap<String, String>() {
			{
				put(Constants.RECEIVED, "in progress");
				put(Constants.ACCEPT, "accepted");
				put(Constants.DECLINE, "declined");
			}
		};

		for (Map.Entry<String, String> entry : statusDetails.entrySet()) {

			String status = entry.getKey();
			String statusTab = entry.getValue();

			// go to referral details
			intakeDashboardPageObj.clickReferralOnDashboard(referralName);

			// update status
			if (!status.equals(Constants.RECEIVED))
				anticipatedSOC = intakeReferralConnectTabPageObj.updateStatusForHomeHealthProvider(status, statusTab);

			// archive referral
			intakeReferralConnectTabPageObj.selectProvider(archiveProvider);
			if (status.equals(Constants.ACCEPT)) {
				// assert anticipated SOC in details page
				assertEquals(intakeReferralConnectTabPageObj.getAnticipatedStartOfCare(archiveProvider),
						anticipatedSOC);
				// assert booked and accepted referral archive modal
				List<String> bookedProviderList = new ArrayList<>();
				bookedProviderList.add(archiveProvider);
				intakeReferralConnectTabPageObj.assertAcceptedAndBookedHomeHealthArchiveReferralModal(
						bookedProviderList, referralDetails.getPrimaryDiagnosis(), anticipatedSOC);
			} else
				intakeReferralConnectTabPageObj.archiveReferral();

			// assert referral is not present on dashboard
			assertFalse(intakeDashboardPageObj.referralSearch(referralDetails));

			// assert referral is not present on dashboard - under status tab
			commonPageObj.navigateTo(statusTab);
			assertFalse(intakeDashboardPageObj.isReferralPresent(referralName));

			// go to archive page
			commonPageObj.navigateTo("archive");

			// reactivate referral
			intakeArchivePageObj.reactivateReferral(referralName);

			// go back to dashboard
			commonPageObj.navigateTo("dashboard");

			// assert referral is present on dashboard - under status tab
			commonPageObj.navigateTo(statusTab);
			assertTrue(intakeDashboardPageObj.isReferralPresent(referralName));

			// assert reactivated badge
			assertTrue(intakeDashboardPageObj.isReactivateBadgePresent(referralName));
			assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName)
					.contains("Reactivated: " + getFormattedCurrentTimestamp("MM/dd/yy hh:", "EST")));
			assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName)
					.contains("by " + intakeUser1FullName));

		}

		// logout
		loginPageObj.logout();
	}

	@Test(priority=0, groups = {"PDB","transitionSecurity"},description = "Intake Tagged Internal Notes")
	public void taggedInternalNotesReg() throws IOException, InterruptedException {
		String note=" Automated Tagged Internal Note";
		// login to D2
		loginPageObj.login(dischargeTgInUser, dischargeTgInUserPass);
		// create patient and send a referral
		referralDetails = commonPageObj.addNewPatientAndSendReferralForRegression(escString, mirthURL, tginConnected,
				homeHealthPOS, homeHealthLOC);
		referralName = referralDetails.getReferralName();
		// logout
		loginPageObj.logout();

		// login to Intake with user1 and add internal notes tagging user2
		loginPageObj.login(intakeTgInUser1, intakeTgInUser1Pass);
		intakeDashboardPageObj.searchAndClickOnReferral(referralDetails);
		intakeReferralDetailsPageObj.navigateTo("internal note");
		intakeReferralInternalNoteTabPageObj.addTaggedInternalNotes(intakeTgInUser2,note);
		String noteCreationTimestamp= intakeReferralInternalNoteTabPageObj.verifyTheAddedInternalNote(intakeTgInUsername1, intakeTgInUser2,note);
		// logout
		loginPageObj.logout();

		// login to Intake with user2 and verify tagged internal notes
		loginPageObj.login(intakeTgInUser2, intakeTgInUser2Pass);
		intakeInternalNoteNotificationPageObj.verifyTaggedInternalNoteNotificationAndGoToReferralDetails(referralName,homeHealthLOC,note,intakeTgInUsername1,intakeTgInUser2,noteCreationTimestamp);
		intakeReferralDetailsPageObj.verifyInternalNotesTabIsActive();
		intakeReferralInternalNoteTabPageObj.verifyTheAddedInternalNoteWithTimestamp(intakeTgInUsername1, intakeTgInUser2, note, noteCreationTimestamp);
		// logout
		loginPageObj.logout();

		// login to D2 and discharge the patient
		loginPageObj.login(dischargeTgInUser, dischargeTgInUserPass);
		workbookPageObj.searchPatientOnWorkbookOnly(referralName);
		patientDischargePageobj.openDischargeModalFromWorkbook();
		patientDischargePageobj.completeDischargeProcess();
		// logout
		loginPageObj.logout();
		softAssert.assertAll();
	}

	@Test(priority=0, groups = {"eventing","coordinatedcare","patientstore"},description = "reactivate referral at Intake - from discharge events")
	public void verifyReferralReactivateAtIntakeFromDischargeEvents()
			throws JSONException, InterruptedException, IOException {

		// login to D2
		loginPageObj.login(dischargeUser1, dischargeUser1Password);

		// create patient and send a referral
		referralDetails = commonPageObj.addNewPatientAndSendReferralForRegression(escString, mirthURL, archiveProvider,
				beddedPOS, beddedLOC);
		referralName = referralDetails.getReferralName();

		// logout
		loginPageObj.logout();

		// login to Intake
		loginPageObj.login(intakeUser1, intakeUser1Password);

		List<String> dischargeEvents = new ArrayList<String>();
		dischargeEvents.add("patient details update");
		dischargeEvents.add("send message");
		dischargeEvents.add("document update");
		dischargeEvents.add("status." + Constants.DELAY_EDD);
		dischargeEvents.add("status." + Constants.REOPEN_REFERRAL);
		dischargeEvents.add("status." + Constants.BOOK);
		dischargeEvents.add("status." + Constants.SUSPEND);
		dischargeEvents.add("status." + Constants.CANCEL);

		for (String dischargeEvent : dischargeEvents) {

			// go to referral details and archive the referral
			intakeDashboardPageObj.searchAndClickOnReferral(referralDetails);
			intakeReferralConnectTabPageObj.selectProvider(archiveProvider);
			intakeReferralConnectTabPageObj.archiveReferral();

			// logout
			loginPageObj.logout();

			// login to D2
			loginPageObj.login(dischargeUser1, dischargeUser1Password);

			if (dischargeEvent.equals("patient details update")) {
				// go to patient details page
				workbookPageObj.searchPatientOnWorkbookAndGoToDetails(referralName);
				// update patient first name
				workbookPageObj.updatePatientFirstName(referralDetails);
				workbookPageObj.savePatient();
				referralName = referralDetails.getReferralName();
			} else if (dischargeEvent.equals("send message")) {
				// navigate to connect page
				commonPageObj.navigateTo("connect");
				// send message
				dischargeConnectPageObj.sendMsgFromDischarge("message");
			} else if (dischargeEvent.equals("document update")) {
				// navigate to connect page
				commonPageObj.navigateTo("build");
				// attach form
				dischargeBuildPageObj.addRemoveFormToPacket(dischargeIntakeSmokeForm);
			} else if (dischargeEvent.equals("document update")) {
				// navigate to connect page
				commonPageObj.navigateTo("build");
				// attach form
				dischargeBuildPageObj.addRemoveFormToPacket(dischargeIntakeSmokeForm);
			} else if (dischargeEvent.contains("status")) {
				// navigate to connect page
				commonPageObj.navigateTo("connect");
				// update status
				dischargeConnectPageObj.updateStatus(dischargeEvent.split(".")[1]);
			}

			// logout
			loginPageObj.logout();

			// login to Intake
			loginPageObj.login(intakeUser1, intakeUser1Password);

			if (!(dischargeEvent.contains(Constants.CANCEL) || dischargeEvent.contains(Constants.SUSPEND))) {
				// assert referral reactivated and is present on in progress tab.
				commonPageObj.navigateTo("in progress");
				assertTrue(intakeDashboardPageObj.isReferralPresent(referralName));

				// assert reactivated badge
				assertTrue(intakeDashboardPageObj.isReactivateBadgePresent(referralName));
				assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName)
						.contains("Reactivated: " + getFormattedCurrentTimestamp("MM/dd/yy hh:", "EST")));
				assertTrue(intakeDashboardPageObj.getReactivateBadgeTooltip(referralName)
						.contains("by " + dischargeUser1FullName));
			} else {
				// assert referral is not reactivated and is still present in archive page
				commonPageObj.navigateTo("in progress");
				assertFalse(intakeDashboardPageObj.isReferralPresent(referralName));
				commonPageObj.navigateTo("archive");
				assertFalse(intakeArchivePageObj.referralSearch(referralDetails));
			}

		}

		// logout
		loginPageObj.logout();

	}
	@Test(priority = 0,groups={"careTransitionWeb"},description = "Verify Help Pages")
	public void helpPagesReg() throws IOException, InterruptedException {
		// login to Intake for help pages verification
		loginPageObj.login(intakeHelpUser, intakeHelpUserPass);
		commonPageObj.checkHelpPage(Constants.HELPPAGES.INTAKE_DASHBOARD);
		commonPageObj.checkHelpPage(Constants.HELPPAGES.INTAKE_DOCS);
		commonPageObj.checkHelpPage(Constants.HELPPAGES.INTAKE_ARCHIVE);
		// logout
		loginPageObj.logout();

		// login to Discharge for help pages verification
		loginPageObj.login(dischHelpUser, dischHelpUserPass);
		commonPageObj.checkHelpPage(Constants.HELPPAGES.DISCHARGE_WORKBOOK);
		// logout
		loginPageObj.logout();
	}

	@Test(priority = 0, groups={"eventing", "coordinatedcare"},description = "verify discharge-intake communication and notifications for bedded LOC")
	public void verifyDischargeIntakeCommunicationForBeddedLOC() throws IOException, InterruptedException {
		int notificationCountIntake = 0, notificationCountDischarge = 0;
		String startTime = getFormattedCurrentTimestamp("MM/dd/YYYY HH:mm", "UTC");
		List<String> intakeNotifications = new ArrayList<String>();
		List<String> dischargeNotifications = new ArrayList<String>();
		// login to D2
		loginPageObj.login(dischargeUser1, dischargeUser1Password);

		// create patient and send a referral
		referralDetails = commonPageObj.addNewPatientAndSendReferralForRegression(escString, mirthURL, archiveProvider,
				beddedPOS, beddedLOC);
		notificationCountIntake++;
		intakeNotifications.add(Constants.INTAKE_NEW_REFERRAL_NOTIFICATION_TEMPLATE);
		referralName = referralDetails.getReferralName();

		// go to connect tab and update status to Cancel
		commonPageObj.navigateTo("connect");
		dischargeConnectPageObj.updateStatus(Constants.CANCEL);
		notificationCountIntake++;
		intakeNotifications.add(Constants.INTAKE_STATUS_UPDATE_NOTIFICATION_TEMPLATE);

		// logout
		loginPageObj.logout();

		// login to Intake
		loginPageObj.login(intakeUser1, intakeUser1Password);

		// go to referral details page
		intakeDashboardPageObj.clickReferralOnDashboard(referralName);

		// assert Cancel status
		assertEquals(intakeReferralConnectTabPageObj.getHospitalStatus(archiveProvider),
				Constants.CANCEL_DISPLAY_VALUE_AT_INTAKE);

		// assert only decline option is available in status drop-down
		intakeReferralConnectTabPageObj.clickOnSetStatusButton(archiveProvider);
		List<String> statusOptionsInDropdown = new LinkedList<String>();
		statusOptionsInDropdown = intakeReferralConnectTabPageObj.getStatusOptionsPresentInDropdown();
		assertEquals(statusOptionsInDropdown.size(), 2);
		assertTrue(statusOptionsInDropdown.contains("Select a status"));
		assertTrue(statusOptionsInDropdown.contains(Constants.DECLINE));
		intakeReferralConnectTabPageObj.selectStatusInDropdwon(Constants.DECLINE);

		// assert only bedded decline reasons are displayed
		List<String> actualDeclineReasons = intakeReferralConnectTabPageObj.getDeclineReasons();
		assertTrue(actualDeclineReasons.containsAll(Constants.COMMON_DECLINE_REASONS));
		assertTrue(actualDeclineReasons.containsAll(Constants.BEDDED_ONLY_DECLINE_REASONS));
		assertFalse(actualDeclineReasons.containsAll(Constants.HOMEHEALTH_ONLY_DECLINE_REASONS));

		// select reason and decline the referral - no notification will be trigerred for discharge user as hospital status is cancelled.
		intakeReferralConnectTabPageObj.selectDeclineReason(Constants.COMMON_DECLINE_REASONS.get(0));
		intakeReferralConnectTabPageObj.clickStatusModalSendResponseButton();

		// logout
		loginPageObj.logout();

		// login to D2
		loginPageObj.login(dischargeUser1, dischargeUser1Password);

		// go to connect page
		workbookPageObj.navigateToPatientPage(referralName, "Connect");

		// update status to reopen
		dischargeConnectPageObj.updateStatus(Constants.REOPEN_REFERRAL);
		notificationCountIntake++;
		intakeNotifications.add(Constants.INTAKE_REOPEN_AFTER_DECLINE_NOTIFICATION_TEMPLATE);

		// logout
		loginPageObj.logout();

		// login to Intake
		loginPageObj.login(intakeUser1, intakeUser1Password);

		// go to referral details page
		intakeDashboardPageObj.searchAndClickOnReferral(referralDetails);

		// assert hospital status
		assertEquals(intakeReferralConnectTabPageObj.getHospitalStatus(archiveProvider),
				Constants.REOPEN_REFERRAL_DISPLAY_VALUE_AT_INTAKE);

		//update intake status to reopen and then to decline again
		intakeReferralConnectTabPageObj.updateStatusForBeddedProvider(archiveProvider, Constants.REOPEN);
		intakeReferralConnectTabPageObj.updateStatusForBeddedProvider(archiveProvider, Constants.DECLINE);
		notificationCountDischarge++;
		dischargeNotifications.add(Constants.DISCHARGE_DECLINE_STATUS_UPDATE_NOTIFICATION_TEMPLATE);

		// logout
		loginPageObj.logout();

		//navigate to OME
		omePageObj.navigateToOme();
		omePageObj.setOmeColumns();

		//assert intake notifications
		Collections.reverse(intakeNotifications);
		List<String> intakeDestinations = Arrays.asList(intakeUser1NotificationEmail, intakeUser1NotificationPhone);
		for (String destination : intakeDestinations) {

			// set destination
			omePageObj.setDestination(destination);

			// set created after
			omePageObj.setCreatedAfter(startTime);

			// assert number of messages
			assertEquals(omePageObj.getMessagesCount(), notificationCountIntake);

			// get templates and assert status and destination
			List<String> templateNames = new ArrayList<String>();
			for (int i = 1; i <= notificationCountIntake; i++) {
				templateNames.add(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_TEMPLATE));
				softAssert.assertEquals(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_STATUS), "COMPLETED");
				assertEquals(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_DESTINATION), destination);
			}
			assertTrue(templateNames.equals(intakeNotifications));
		}

		// assert notifications for discharge
		Collections.reverse(dischargeNotifications);
		List<String> dischargeDestinations = Arrays.asList(dischargeUser1NotificationEmail, dischargeUser1NotificationPhone);
		for (String destination : dischargeDestinations) {

			// set destination
			omePageObj.setDestination(destination);

			// set created after
			omePageObj.setCreatedAfter(startTime);

			// assert number of messages
			assertEquals(omePageObj.getMessagesCount(), notificationCountDischarge);

			// get templates and assert status and destination
			List<String> templateNames = new ArrayList<String>();
			for (int i = 1; i <= notificationCountDischarge; i++) {
				templateNames.add(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_TEMPLATE));
				softAssert.assertEquals(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_STATUS), "COMPLETED");
				assertEquals(omePageObj.getOmeColumnValue(i, Constants.OME_COLUMN_DESTINATION), destination);
			}
			assertTrue(templateNames.equals(dischargeNotifications));
		}

		closeAllSubTabs();
	}

	@Test(groups = { "D2PML", "patientStore", "eventing",
	"transitionCore" }, priority = 0, description = "PML Regression")
	public void d2PMLReg() throws Exception {

		// Close all existing Tabs if opened
		closeAllSubTabs();

		// Login to Discharge
		loginPageObj.login(pmlRegUser, pmlRegPass);

		//Add a Patient
		workbookPageObj.addAPatientWithMandatoryFieldsOnly();

		// Navigate to Refer page
		commonPageObj.navigateTo("refer");
		//Select POS
		dischargeBuildPageObj.selectServiceType("Home Services");
		//select LOC
		dischargeBuildPageObj.selectLOCSNF("Home Health Agency");

		// Search by location-zip
		dischargeReferPageObj.searchProvidersByLocationZip("99999");

		// Click on provider Names
		dischargeReferPageObj.clickOnCheckboxByProviderName(providerList);

		// Share pml list
		dischargeReferPageObj.sharePMLList();

		// Select providers and submit
		dischargeReferPageObj.makeProviderSelectionAndSubmitInPmlUI(providerList);

		//navigate to build page to assert the presence of pml docs
		commonPageObj.navigateTo("build");

		//verify presence of pml docs
		dischargeBuildPageObj.verifyPresenceOfPMLDocs();

		// Navigate to the Discharge Page
		patientDischargePageobj.navigateToDischargePage();

		// Discharge the patient
		patientDischargePageobj.completeDischargeProcess();

		// Logout
		loginPageObj.logout();

	}

	@Test(groups = {"D2PML", "patientStore", "eventing",
	"transitionCore"}, priority = 0, description = "Workbook Regression")
	public void workbookReg() throws Exception {

		// Close all existing Tabs if opened
		closeAllSubTabs();

		// Login to Discharge
		loginPageObj.login(pmlRegUser, pmlRegPass);
		// create ADT Patient
		PatientInfoBean patientDetails = commonPageObj.createPatientForSmoke(testData.getString("escString"),
				testData.getString("mirthURL"));

		//verify changing primary contact from cm workbook without keeping cm as subscriber
		//verify change in pc/subscribedto count

		//verify changing pc from the new cm workbook with keeping that cm as subscriber
		//verify change in pc/subscribedto count

		//verify changing pc from the pc option on patient card

		//verify adding removing subscribers from patient card- view edit subscribers

		//verify subscriber adding from actions menu

		//verify unsubscribe from actions menu

		//


	}
}
