package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qa.util.Constants;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.APIUtil;
import com.qa.util.FakeDataProvider;
import com.qa.util.PatientInfoBean;

import static com.qa.util.TestUtil.*;

public class CommonPage extends TestBase {

	WorkbookPage workbookPageObj = new WorkbookPage();
	DischargeReferPage dischargeReferPageObj = new DischargeReferPage();
	DischargeBuildPage dischargeBuildPageObj = new DischargeBuildPage();
	IntakeDashboardPage intakeDashboardPageObj = new IntakeDashboardPage();

	APIUtil apiUtil = new APIUtil();

	// Locators
	@FindBy(id = "discharge-header-matching-button")
	WebElement referPage;
	@FindBy(id = "discharge-header-implementation-button")
	WebElement connectPage;
	@FindBy(xpath = "//nh-intake-patient-detail-tabs//button[contains(.,'Documents')]")
	WebElement referralDocuments;
	@FindBy(xpath = "//nh-intake-patient-detail-tabs//button[contains(.,'Connect')]")
	WebElement referralConnect;
	@FindBy(xpath = "//button[contains(.,'Documents')]")
	WebElement documentsTab;
	@FindBy(xpath = "//button[@id='discharge-header-assessment-button']")
	WebElement buildPage;
	@FindBy(xpath = "//div[@class='cs-container']//button[text()='Archive']")
	WebElement archivePage;
	@FindBy(xpath = "//div[@class='cs-container']//button[text()='Referrals']")
	WebElement dashboardPage;
	@FindBy(xpath = "//nh-intake-dashboard-status-tabs//button[contains(.,'New')]")
	WebElement newStatus;
	@FindBy(xpath = "//nh-intake-dashboard-status-tabs//button[contains(.,'In Progress')]")
	WebElement inProgressStatus;
	@FindBy(xpath = "//nh-intake-dashboard-status-tabs//button[contains(.,'Accepted')]")
	WebElement acceptedStatus;
	@FindBy(xpath = "//nh-intake-dashboard-status-tabs//button[contains(.,'Booked')]")
	WebElement bookedStatus;
	@FindBy(xpath = "//nh-intake-dashboard-status-tabs//button[contains(.,'Declined')]")
	WebElement declinedStatus;
	@FindBy(xpath = "//nh-intake-dashboard-status-tabs//button[contains(.,'Reopened')]")
	WebElement reopenedStatus;
	@FindBy(xpath = "//button[contains(.,'Profile')]")
	WebElement profilePage;
	@FindBy(xpath = "//mat-tooltip-component")
	WebElement tooltipComponent;
	@FindBy(xpath = "//select[contains(@id, 'select-a-service')]")
	WebElement selectServiceDropdown;
	@FindBy(xpath = "//select[contains(@id, 'selected-loc')]")
	WebElement selectLocDropdown;
	@FindBy(xpath = "//button[contains(text(),' Patient Summary ')]")
	WebElement patientSummaryOption;

	// Case History locators
	@FindBy(xpath = "//span[contains(@class,'mat-button-wrapper')]//nh-svg-icon[@iconname='ic_arrow_drop_down']|//nh-svg-icon[contains(@class,'ic-caret-icon')]")
	WebElement caretIcon;
	@FindBy(xpath = "//button[contains(text(),' Case History ')]")
	WebElement caseHistoryOption;
	@FindBy(xpath = "//mat-dialog-container[contains(@id,'mat-dialog')]")
	WebElement caseHistoryModal;
	@FindBy(xpath = "//button[@id='close-action-btn']")
	WebElement caseHistoryCancelButton;

	// Help pages locators
	@FindBy(css= "button.help-trigger")
	WebElement helpBtnOnIntakeDashboard;
	@FindBy(xpath = "//nh-intake-documents-list//button[@class='help-trigger']")
	WebElement helpBtnOnIntakeDocs;
	@FindBy(xpath = "//nh-intake-archive-search-form//button[@class='help-trigger']")
	WebElement helpBtnOnIntakeArchive;
	@FindBy(xpath = "//nh-intake-search-patients//button[@class='help-trigger']")
	WebElement helpBtnOnIntakePatientSearch;

	// Dynamic Locator for case history events
	String eventsInCaseHistory = "//*[contains(text(), 'xxxxx')]";

	// Initializing the Page Factory/Objects:
	public CommonPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/**
	 * @description: add patient at discharge and send referral to intake provider
	 * 
	 * @param: providerName - intake providerName, POS - Point of service for
	 *                      referral packet, LOC - Level of care for referral packet
	 * 
	 * @return: referralDetails - PatinetInfoBean object containing referral
	 *          details.
	 */
	public PatientInfoBean addNewPatientAndSendReferralForRegression(String escString, String mirthURL, String providerName, String POS, String LOC)
			throws InterruptedException, IOException {
		
		PatientInfoBean referralDetails = createPatientForRegressionWithRandomData(escString, mirthURL);
		navigateTo("refer");
		updateReferralPacket(POS, LOC);
		dischargeReferPageObj.sendReferralPacket(providerName, "ZZ");
		return referralDetails;
	}

	/**
	 * @description: add patient at discharge and send referral with keyservice to
	 *               intake provider
	 * 
	 * @param: providerName - intake providerName, POS - Point of service for the
	 *                      referral, LOC - Level of care for referral packet,
	 *                      keyService - key service for referral packet
	 * 
	 * @return: referralDetails - PatinetInfoBean object containing referral
	 *          details.
	 */
	public PatientInfoBean addNewPatientAndSendReferralWithKeyService(String providerName, String POS, String LOC,
			String keyService) throws InterruptedException, IOException {

		PatientInfoBean referralDetails = workbookPageObj.addAPatientWithMandatoryFieldsOnly();
		navigateTo("refer");
		updateReferralPacket(POS, LOC);
		dischargeReferPageObj.selectKeyService(keyService);
		dischargeReferPageObj.sendReferralPacket(providerName, "ZZ");
		return referralDetails;

	}

	/**
	 * @description: add patient at discharge and send referral with keyservice to
	 *               intake provider
	 * 
	 * @param: providerName - intake providerName, POS - Point of service for
	 *                      referral, LOC - Level of care for referral packet,
	 *                      formName - name of the form to be attached to referral
	 *                      packet
	 * 
	 * @return: referralDetails - PatinetInfoBean object containing referral
	 *          details.
	 */
	public PatientInfoBean addNewPatientAndSendReferralWithForm(String providerName, String POS, String LOC,
			String formName) throws InterruptedException, IOException {

		PatientInfoBean referralDetails = workbookPageObj.addAPatientWithMandatoryFieldsOnly();
		navigateTo("build");
		dischargeBuildPageObj.addRemoveFormToPacket(formName);
		navigateTo("refer");
		updateReferralPacket(POS, LOC);
		dischargeReferPageObj.sendReferralPacket(providerName, "ZZ");
		return referralDetails;

	}

	public Boolean isReopenedTabdisplayed() {
		return isElementPresent(reopenedStatus, "reopened tab");
	}

	/**
	 * @description: navigates to given page name
	 * 
	 * @param: pageName - page name to be navigated to.
	 * 
	 * @return: void
	 */
	public void navigateTo(String pageName) {
		if (pageName.equals("refer")) {
			clickElement(referPage, "refer tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("connect")) {
			clickElement(connectPage, "connect tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("build")) {
			clickElement(buildPage, "build tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("archive")) {
			clickElement(archivePage, "archive page");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("dashboard")) {
			clickElement(dashboardPage, "dashboard page");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("new")) {
			clickElement(newStatus, "new status tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("in progress")) {
			clickElement(inProgressStatus, "in progress status tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("accepted")) {
			clickElement(acceptedStatus, "accepted status tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("booked")) {
			clickElement(bookedStatus, "booked status tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("declined")) {
			clickElement(declinedStatus, "declined status tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("reopened")) {
			clickElement(reopenedStatus, "reopened status tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("profile")) {
			clickElement(profilePage, "profile page");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("documents")) {
			clickElement(documentsTab, "document tab");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("referralDocuments")) {
			clickElement(referralDocuments, "document tab in referral details page");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		} else if (pageName.equals("referralConnect")) {
			clickElement(referralConnect, "connect tab in referral details page");
			waitForPageToLoad();
			waitForLoaderToDisappear();
		}
	}

	public String getTooltipComponent() {
		return retrieveText(tooltipComponent, "tool tip component");
	}

	/**
	 * @description: Selects and opens the different modal options present when the
	 *               downward arrow icon besides the patient name is clicked.
	 * 
	 * @param: OptionName - The modal name needs to be passed
	 * 
	 * @return: void
	 */
	public void openModalsViaDownwardArrowIconBesidePatientName(String optionName) {
		if (optionName.equals("Case History")) {
			clickElement(caretIcon, "Click On Caret Icon");
			if (isEnabled(caseHistoryOption, "Opening Case History Modal")) {
				clickAndWaitForPageLoad(caseHistoryOption, "Click On Case History");
			}
			waitForLoaderToDisappear();
		} else if (optionName.equals("Patient Summary")) {
			clickElement(caretIcon, "Click On Caret Icon");
			if (isEnabled(patientSummaryOption, "Opening Patient Summary Modal")) {
				clickElement(patientSummaryOption, "Click On Patient Summary Option");
			}
			waitForLoaderToDisappear();
		}
		reportLog("Modal selected to Open is : " + optionName);

	}

	public void clickOnCaretIconOpencaseHistory() {
		openModalsViaDownwardArrowIconBesidePatientName("Case History");
		// Check if case history modal is displayed
		isElementPresent(caseHistoryModal, "Case History Modal");
	}

	public void verifyCaseHistoryEventsAndCloseModal() {
		// Verify the events & close the modal
		Assert.assertEquals(
				isElementPresent(prepareWebElementWithDynamicXpath(eventsInCaseHistory, "Referral status changed from"),
						"Referral Status Change event"),
				true);
		Assert.assertEquals(isElementPresent(prepareWebElementWithDynamicXpath(eventsInCaseHistory,
				"Provider search performed with matching criteria"), "provider Search Performed event"), true);
		Assert.assertEquals(isElementPresent(
				prepareWebElementWithDynamicXpath(eventsInCaseHistory, "Document assigned from Document Manager"),
				"Doc assigned event"), true);
		Assert.assertEquals(
				isElementPresent(prepareWebElementWithDynamicXpath(eventsInCaseHistory, "assigned as primary contact"),
						"primary Contact Name Change event"),
				true);
		clickElement(caseHistoryCancelButton, "click on case history cancel Button");
		reportLog("Case History smoke testing is complete");
	}

	public PatientInfoBean createPatientForSmoke(String escString, String mirthURL)
			throws InterruptedException, IOException {
		PatientInfoBean patientDetails = new PatientInfoBean();
		try {

			String mrn = FakeDataProvider.getMRN();
			String acc = FakeDataProvider.getACC();
			String lName = FakeDataProvider.getLastName();
			String fName = FakeDataProvider.getFirstName();
			String DOB = getFormattedCurrentTimestamp("yyyyMMdd", "EST");
			String AdmitDate = DOB;
			String dispoCode = FakeDataProvider.getDispoCode();
			String gender = FakeDataProvider.getGender();
			String HL7Body = escString.replace("MRN", mrn).replace("ACC", acc).replace("FNAME", fName)
					.replace("LNAME", lName).replace("DISPO", dispoCode).replace("GENDER", gender).replace("DOB", DOB)
					.replace("ADMITDATE", AdmitDate).replace("EDD", "20301231");
			reportLog("Creating ADT patient using HL7\n" + HL7Body);
			apiUtil.sendHL7RequestToADT(mirthURL, HL7Body);
			patientDetails.setMRN(mrn);
			patientDetails.setAccountNumber(acc);
			patientDetails.setFirstName(fName);
			patientDetails.setLastName(lName);
			patientDetails.setReferralName(lName + ", " + fName);
			patientDetails.setFullName(fName + " " + lName);
			patientDetails.setDispoCode(dispoCode);
			patientDetails.setGender(gender);
			patientDetails.setDOB(getFormattedCurrentTimestamp("MM/dd/yyyy", "EST"));
			patientDetails.setAge(calculateAge(patientDetails.getDOB()));
			patientDetails.setAdmitDate(patientDetails.getDOB());
			patientDetails.setNotifyDate(getFormattedCurrentTimestamp("MM/dd/yyyy", "UTC"));
			patientDetails.setEstimatedDischargeDate("12/31/2030");
			patientDetails.setPrimaryDiagnosis("ALS");
			patientDetails.setSecondaryDiagnosis("ARDS");
			workbookPageObj.searchAllAndAddToWorkBook(mrn);
			workbookPageObj.searchPatientOnWorkbookAndGoToDetails(patientDetails.getReferralName());

		} catch (Exception e) {
			reportLog(e.getMessage());
			reportLog("Creating Manually as ADT Failed");
			refreshPage();
			waitForLoaderToDisappear();
			waitForElementToBeClickable(workbookPageObj.createNewPatientButton, "create new patinet button");
			patientDetails = workbookPageObj.addAPatientWithMandatoryFieldsOnly();
		}
		return patientDetails;
	}

	public PatientInfoBean createPatientForRegressionWithRandomData(String escString, String mirthURL)
			throws InterruptedException, IOException {
		PatientInfoBean patientDetails = new PatientInfoBean();
		// read the template
		try {
			JSONObject patientJson = null;
			try {
				File file = new File("src/main/resources/patientDetailsTemplate.json");
				String content = FileUtils.readFileToString(file, "utf-8");
				patientJson = new JSONObject(content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			patientDetails.setFirstName((patientJson.put("firstName", FakeDataProvider.getFirstName()).getString("firstName")));
			patientDetails.setLastName(patientJson.put("lastName", FakeDataProvider.getLastName()).getString("lastName"));
			patientDetails.setGender(patientJson.put("gender", FakeDataProvider.getGender()).getString("gender"));
			patientJson.put("DOB", getFormattedCurrentTimestamp("yyyyMMdd", "EST"));
			patientDetails.setDOB(getFormattedCurrentTimestamp("MM/dd/yyyy", "EST"));
			patientJson.put("AdmitDate", getFormattedCurrentTimestamp("yyyyMMdd", "EST"));
			patientDetails.setAdmitDate(getFormattedCurrentTimestamp("MM/dd/yyyy", "EST"));
			patientJson.put("estimatedDischargeDate", "20301231");
			patientDetails.setEstimatedDischargeDate("12/31/2030");
			patientDetails.setMRN(patientJson.put("MRN", FakeDataProvider.getMRN()).getString("MRN"));
			patientDetails.setAccountNumber(patientJson.put("accountNumber", FakeDataProvider.getACC()).getString("accountNumber"));
			patientDetails.setAddressLine1(patientJson.put("addressLine1", FakeDataProvider.getAddress1()).getString("addressLine1"));
			patientDetails.setAddressLine2(patientJson.put("addressLine2", FakeDataProvider.getAddress2()).getString("addressLine2"));
			patientDetails.setCity(patientJson.put("city", FakeDataProvider.getCity()).getString("city"));
			patientDetails.setState(patientJson.put("State", FakeDataProvider.getStateCode()).getString("State"));
			patientDetails.setZip(patientJson.put("zip", FakeDataProvider.getZipCode(patientJson.getString("State"))).getString("zip"));
			patientDetails.setSSN(patientJson.put("SSN", FakeDataProvider.getSSN()).getString("SSN"));
			patientDetails.setAdmitType(patientJson.put("admitType", "ATC").getString("admitType"));
			patientDetails.setUnit(patientJson.put("unit", "POC").getString("unit"));
			patientDetails.setHospital(patientJson.put("hospital", "ProtractorMBH").getString("hospital"));
			patientDetails.setSecondaryDiagnosis(patientJson.put("secondaryDiagnosis", "ARDS").getString("secondaryDiagnosis"));
			patientDetails.setOtherSecondary(patientJson.put("otherSecondary", FakeDataProvider.getDisease()).getString("otherSecondary"));
			patientDetails.setAttendingPhysicianFirstName(patientJson.put("attendingPhysicianFirstName", FakeDataProvider.getFirstName()).getString("attendingPhysicianFirstName"));
			patientDetails.setAttendingPhysicianLastName(patientJson.put("attendingPhysicianLastName", FakeDataProvider.getLastName()).getString("attendingPhysicianLastName"));
			patientDetails.setPrimaryDiagnosis(patientJson.put("primaryDiagnosis", "ALS").getString("primaryDiagnosis"));
			patientDetails.setOtherPrimary(patientJson.put("otherPrimary", FakeDataProvider.getDisease()).getString("otherPrimary"));
			patientDetails.setPrimaryCarePhysicianFirstName(patientJson.put("primaryCarePhysicianFirstName", FakeDataProvider.getFirstName()).getString("primaryCarePhysicianFirstName"));
			patientDetails.setPrimaryCarePhysicianLastName(patientJson.put("primaryCarePhysicianLastName", FakeDataProvider.getLastName()).getString("primaryCarePhysicianLastName"));
			patientDetails.setInsuredFirstName(patientJson.put("insuredFirstName", FakeDataProvider.getFirstName()).getString("insuredFirstName"));
			patientDetails.setInsuredLastName(patientJson.put("insuredLastName", FakeDataProvider.getLastName()).getString("insuredLastName"));			
			patientDetails.setPrimaryPayerName(patientJson.put("primaryPayerName", FakeDataProvider.getInsurer()).getString("primaryPayerName"));
			patientDetails.setDispoCode(patientJson.put("dispo", FakeDataProvider.getDispoCode()).getString("dispo"));
			patientDetails.setPrimaryPayerMemberID(patientJson.put("primaryPayerMemberID", FakeDataProvider.getPlanId()).getString("primaryPayerMemberID"));
			patientDetails.setBed(patientJson.put("bed",String.valueOf(randomNumberGenerator(1, 2))).getString("bed"));
			patientDetails.setRoom(patientJson.put("room", String.valueOf(randomNumberGenerator(1, 2))).getString("room"));
			patientDetails.setPolicyNumber(patientJson.put("policyNumber", String.valueOf(randomStringGenerator(8))).getString("policyNumber"));
			
			for (String keyStr : patientJson.keySet()) {				
				String value = patientJson.getString(keyStr);
				if (!value.isEmpty()) {
					escString = escString.replace(keyStr, value);
				} else {
					escString = escString.replace(keyStr, "");
				}

			}
			reportLog("Creating ADT patient using HL7\n" + escString);
			apiUtil.sendHL7RequestToADT(mirthURL, escString);
			workbookPageObj.searchAllAndAddToWorkBook(patientDetails.getMRN());
			workbookPageObj.searchPatientOnWorkbookAndGoToDetails(patientDetails.getReferralName());

		} catch (Exception e) {
			reportLog(e.getMessage());
			reportLog("Creating Manually as ADT Failed");
			refreshPage();
			waitForLoaderToDisappear();
			waitForElementToBeClickable(workbookPageObj.createNewPatientButton, "create new patinet button");
			patientDetails = workbookPageObj.addAPatientWithMandatoryFieldsOnly();
		}
		return patientDetails;
	}

	public void updateReferralPacket(String POS, String LOC) {
		// Select the service in Service card
		selectValueByVisibleText(selectServiceDropdown, POS, "Service Selection");
		waitForLoaderToDisappear();
		// Select the LOC in service card
		selectValueByVisibleText(selectLocDropdown, LOC, "LOC Selection");
		waitForLoaderToDisappear();
		waitForPageToLoad();
		reportLog("Updated the LOC and POS");
	}

	public String extractDischargeId(String url){
		String s="";
		Pattern p = Pattern.compile("/(?:workflowPages/).[a-zA-Z0-9]+");
		Matcher m = p.matcher(url);
		while(m.find()) {
			s= m.group();
		}
		String dischargeId=s.replaceAll("/workflowPages/","").trim();
		return dischargeId;
	}

	public void checkHelpPageOnIntakeDashboard() throws InterruptedException {
		//is capable to test the help page of discharge workbook as well
		//as locators of help button is same in the common header for discharge and intake
		clickAndWaitForPageLoad(helpBtnOnIntakeDashboard,"Help Button");
		waitForNewWindow(1);
		switchToSpecificTab(1);
		reportLog(getURL());
		//assertion needs to be done here once help pages are up
		switchToSpecificTab(0);
		closeAllSubTabs();
	}

	public void checkHelpPageOnIntakeDocs() throws InterruptedException {
		navigateTo("documents");
		clickAndWaitForPageLoad(helpBtnOnIntakeDocs,"Help Button");
		waitForNewWindow(1);
		switchToSpecificTab(1);
		reportLog(getURL());
		//assertion needs to be done here once help pages are up
		switchToSpecificTab(0);
		closeAllSubTabs();
	}

	public void checkHelpPageOnIntakeArchive() throws InterruptedException {
		navigateTo("archive");
		clickAndWaitForPageLoad(helpBtnOnIntakeArchive,"Help Button");
		waitForNewWindow(1);
		switchToSpecificTab(1);
		reportLog(getURL());
		//assertion needs to be done here once help pages are up
		switchToSpecificTab(0);
		closeAllSubTabs();
	}

	public void checkHelpPageOnIntakePatientSearch() throws InterruptedException {
		intakeDashboardPageObj.clickSearchPatientsButton();
		clickAndWaitForPageLoad(helpBtnOnIntakePatientSearch,"Help Button");
		waitForNewWindow(1);
		switchToSpecificTab(1);
		reportLog(getURL());
		//assertion needs to be done here once help pages are up
		switchToSpecificTab(0);
		intakeDashboardPageObj.clickCloseButtonInPatinetSearchModal();
		closeAllSubTabs();
	}

	public void checkHelpPage(Constants.HELPPAGES whichPage) throws InterruptedException {
		switch (whichPage){
			case INTAKE_DASHBOARD:
				reportLog("Verifying Help Page on INTAKE DASHBOARD");
				checkHelpPageOnIntakeDashboard();
				checkHelpPageOnIntakePatientSearch();
				break;
			case DISCHARGE_WORKBOOK:
				reportLog("Verifying Help Page on DISCHARGE WORKBOOK");
				checkHelpPageOnIntakeDashboard();
				break;
			case INTAKE_DOCS:
				reportLog("Verifying Help Page on INTAKE DOCUMENTS PAGE");
				checkHelpPageOnIntakeDocs();
				break;
			case INTAKE_ARCHIVE:
				reportLog("Verifying Help Page on INTAKE ARCHIVE PAGE");
				checkHelpPageOnIntakeArchive();
				break;
		}
	}


}
