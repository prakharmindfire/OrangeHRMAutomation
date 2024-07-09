package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.PatientInfoBean;
import com.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.qa.util.TestUtil.*;
import static org.testng.Assert.assertTrue;

public class IntakeDashboardPage extends TestBase {

	// Page Factory - OR:
	@FindBy(xpath = "//a[@class='logo-nav']/img")
	WebElement dashboardLogo;
	@FindBy(css = ".footer-copyright.footer-col")
	WebElement copyRight;

	// Change Password modals

	@FindBy(xpath = "//button[contains(.,'Documents')]")
	WebElement documentsTab;
	@FindBy(css = ".mat-tooltip")
	WebElement tooltip;

	// Page Factory - OR:

	WebElement popUpTitle;
	// Change Password modals

	@FindBy(id = "cp_current_password")
	WebElement currentPasswordInput;
	
	@FindBy(xpath = "//button[contains(.,'Archive')]")
    WebElement archiveButtonOnDashboard;

	@FindBy(id = "cp_new_password")
	WebElement newPasswordInput;
	@FindBy(css = ".filtered-locs .mat-expansion-panel-body span")
	List<WebElement> locFilterKeyServicesValues;

	@FindBy(css = ".filtered-locs mat-panel-title span")
	List<WebElement> locFilterLOCVAlues;

	@FindBy(xpath = "//button[contains(.,'Profile')]")
	WebElement profileTab;
	@FindBy(xpath = "//span[@class='referral-card-label'][contains(text(),'Required Level of Care')]//following-sibling::div[contains(@class,'referral-card-data')]")
	List<WebElement> requiredLOCValues;
	@FindBy(xpath = "//button[text()='CANCEL']")
	WebElement cancelTeamFilterModal;
	@FindBy(xpath = "//button[contains(.,'UPDATE')][@angularticsaction='FilterTeamsUpdate']")
	WebElement teamsFilterModalUpdate;
	@FindBy(xpath = "//span[@class='referral-card-label'][contains(text(),'Key Services')]//following-sibling::div[contains(@class,'referral-card-data')]")
	List<WebElement> allReferralKeyServices;
	@FindBy(css = ".filtered-locs mat-expansion-panel .mat-expansion-indicator")
	List<WebElement> allLOCExpandButtons;
	@FindBy(xpath = "//div[@id='search-results']//th")
    List<WebElement> searchResultsColumnHeaders;
	WebElement teamsFilterClearAll;
	@FindBy(xpath = "//button[contains(.,'TEAMS')]")
	List<WebElement> allTeamsIcon;

	@FindBy(xpath = "//button[contains(text(),'Clear All')]")
	WebElement LOCAndReferralSourceFilterModalClearAll;
	@FindBy(id = "cp_confirm_password")
	WebElement confirmPasswordInput;
	@FindBy(xpath = "//button[contains(.,'CLEAR ALL')]")
	WebElement filterModalClearAll;
	@FindBy(xpath = "//button[contains(text(),'Save Changes')]")
	WebElement saveChangesButtonInChangePassword;
    @FindBy(xpath = "//button[contains(text(),'New') and contains(@angularticsaction,'onSwitchStatusTab')]/span")
    WebElement newTabCountValue;
    @FindBy(xpath = "//div[@class='dashboard-view']")
	WebElement centralizedIntakeDashboard;

	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement cancelButtonInChangePasswordePage;

	@FindBy(id = "cust_supp_email")
	WebElement userEmail;

	@FindBy(css = ".team-assignments-status-view .team-assignment span")
	List<WebElement> teamsIconPopOverTeamList;
	// dashboard search locators

	@FindBy(xpath = "//button[contains(.,'Search Patients')]")
	WebElement searchPatientsButton;
	@FindBy(id = "search_patients")
	WebElement searchPatientTextBox;
	@FindBy(xpath = "//nh-intake-search-patients//button[contains(.,'search')]")
	WebElement searchButton;
	@FindBy(xpath = "//nh-intake-search-patients-result")
	WebElement searchResults;
	@FindBy(xpath = "//nh-intake-search-patients-result//td[1]")
	List<WebElement> referralNamesInSerchModal;
	@FindBy(xpath = "//nh-intake-search-patients//button[text()='Close']")
	WebElement closeButtonInSerchModal;
	@FindBy(xpath = "//button[contains(.,'Notification Settings')]")
	WebElement notificationSettingsOptionInCog;
	@FindBy(xpath = "/html/body/app-root/nh-intake-root/div/div[1]/div/div[1]/button[2]")
	WebElement Documents;

	@FindBy(id = "cust_supp_phone")
	WebElement phoneNumber;

	@FindBy(id = "cust_supp_details")
	WebElement customerSupportDetails;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitButton;
	@FindBy(xpath = "//button[contains(.,'Update')][@angularticsaction='FilterLocsUpdate']")
	WebElement LOCfilterModalUpdate;
	@FindBy(css = "[angularticsaction='OpenFilterTeams']")
	WebElement allTeamFilter;
	@FindBy(xpath = "//button[contains(.,'UPDATE')]")
	WebElement filterModalUpdate;
	@FindBy(xpath = "//button[contains(.,'Contact Client Support')]")
	WebElement customerContactSupport;

	@FindBy(xpath = "//button[contains(.,'Change Password')]")
	WebElement changePasswordOptionInCog;
	@FindBy(css = ".team-assignments-status-view")
	WebElement teamAssignmentPopUp;
	@FindBy(css = "[angularticsaction='OpenFilterServices']")
	WebElement LOCFilter;
	@FindBy(css = ".team-assignments-status-view [iconname='ic_check_box']")
	WebElement teamAssignmentCompletedIcon;
	@FindBy(xpath = "//button[contains(.,'Referrals')]")
	WebElement referralsTab;

	@FindBy(xpath = "(//div[@class='referral-card card-row card']//div//div//h4)[1]")
	WebElement firstReferralName;

	// Referral card locators
	@FindBy(xpath = "//h4")
	List<WebElement> allReferralNames;
	@FindBy(xpath = "//div[contains(@class,'new-referral')]//select[@name='refOrgSelect']//option")
	List<WebElement> referralSourcePredefinedOptions;
	@FindBy(xpath = "//div[@class='bed-status-count available']")
	WebElement availableBedIcon;

	@FindBy(xpath = "//div[@class='bed-status-count unavailable']//a")
	WebElement notAvailableBedIcon;

	@FindBy(xpath = "//div[@class='bed-status-count unset']//a")
	WebElement notSetBedIcon;

	@FindBy(xpath = "//a[contains(.,'Set Bed Availability')]")
	WebElement popOverLinkInNotSetBedIcon;

	@FindBy(xpath = "//button[contains(.,'Bed Availability')]")
	WebElement bedAvailabilityButton;

	@FindBy(xpath = "//nh-svg-icon[@class='status-available']")
	WebElement availableLegendaryIcon;

	@FindBy(xpath = "//nh-svg-icon[@class='status-unavailable']")
	WebElement notAvailableLegendaryIcon;

	@FindBy(xpath = "//nh-svg-icon[@class='status-unset']")
	WebElement notSetLegendaryIcon;

	@FindBy(xpath = "//button[contains(.,'Close')]")
	WebElement closeButtonInBedAvailabilityModal;

	@FindBy(xpath = "//button[contains(.,'No Beds Available')]")
	WebElement bedsNotAvailableUpdateButtonInModal;

	@FindBy(xpath = "//button[contains(.,'Beds available')]")
	WebElement bedsAvailableUpdateButtonInModal;

	@FindBy(xpath = "//div[@class='bed-availability-modal']//button[contains(.,'Alphabetical')]")
	WebElement alphabeticalSortButtonInModal;

	@FindBy(xpath = "//div[@class='bed-availability-modal']//button[contains(.,'Availability')]")
	WebElement avilablitySortButtonInModal;

	@FindBy(xpath = "//button[@id='settings-dropdown-trigger-btn']")
	WebElement settingsCog;

	@FindBy(xpath = "//div[@id='providers-filter']//nh-intake-organizations//li/div[2]/span")
	List<WebElement> providerNamesInFilterProvider;

	@FindBy(css = "button[angularticsaction=\"OpenFilterTargetOrgs\"]")
	WebElement filteredProvider;

	@FindBy(xpath = "//button[text()='CANCEL']")
	WebElement cancelButton;

	@FindBy(xpath = "//p[contains(.,'Intake Coordinator')]//ancestor::li//span")
	List<WebElement> ciUserNotificationNames;
	@FindBy(xpath = "//p[contains(.,'Read Only Liaison')]//ancestor::li//label")
	List<WebElement> readOnlyUserNotificationNames;
	@FindBy(xpath = "//p[contains(.,'Community Services')]//ancestor::li//label")
	List<WebElement> communityServiceUserNotificationNames;
	@FindBy(xpath = "//p[contains(.,'Read Only Liaison with Archive')]//ancestor::li//label")
	List<WebElement> readOnlyArchiveUserNotificationNames;

	@FindBy(xpath = "//p[contains(.,'Intake Coordinator Lite')]//ancestor::li//label")
	List<WebElement> csLiteUserNotificationNames;

	@FindBy(xpath = "//h3[contains(.,'Notification Sources')]//parent::div//div[@class='form-group']//label//span")
	List<WebElement> notificationSources;

	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement cancelButtonOnNotificationModal;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][2]//div[contains(text(),'Referral Source')]")
	WebElement firstPatientCardFirstRowReferralSourceColumnHeader;

	@FindBy(xpath = "//span[contains(text(),'Sort by')]/..//button[@data-toggle='dropdown']")
	WebElement sortByDropdownButton;

	@FindBy(xpath = "//a[@class='dropdown-item'][contains(.,'Unread Documents')]")
	WebElement sortByUnreadDocuments;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][2]//div[contains(text(),'Estimated Discharge Date')]")
	WebElement firstPatientCardFirstRowEstDischargeDateColumnHeader;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][2]//div[contains(text(),'Actual Discharge Date')]")
	WebElement firstPatientCardFirstRowActDischargeDateColumnHeader;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='col ng-star-inserted']//span[contains(text(),'Anticipated Start of Care')]")
	WebElement firstpatientCardFirstRowASOCColumnHeader;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='col ng-star-inserted']//span[contains(text(),'Key Services')]")
	WebElement firstpatientCardFirstRowKeyServicesColumnHeader;

	// first patient card second row All Column headers

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][3]//span[contains(text(),'Date of Birth')]")
	WebElement firstPatientcardSecondRowDOBColumnHeader;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][3]//span[contains(text(),'Unit/Room/Bed')]")
	WebElement firstPatientcardSecondRowUnitBedRoomColumnHeader;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][3]//span[contains(text(),'Payer')]")
	WebElement firstPatientcardSecondRowPayerColumnHeader;

	@FindBy(xpath = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][3]//span[contains(text(),'Required Level of Care')]")
	WebElement firstPatientcardSecondRowRequiredLOCColumnHeader;
	@FindBy(xpath = "//div[@id='search-results']//table//th")
	WebElement tableHeadersInSearchModal;
	@FindBy(xpath = "//span[@class='pull-right svg-med']")
	List<WebElement> documentIconOnDashboard;
	String messageIcon = "//h4[normalize-space(.) = 'xxxxx']//ancestor::div[@class='referral-card card-row card']//span[@class='messages right ng-star-inserted']";
	@FindBy(xpath = "//a[@class='dropdown-item'][contains(.,'Antic. Start of Care')]")
	WebElement sortByASOC;
	
	@FindBy(xpath = "//div[@class='cdk-overlay-container']//mat-tooltip-component//div")
	WebElement tooltipComponent;

	// Dynamic locators
	String expandLoc = "//div[contains(@class,'filtered-locs')]//span[contains(text(),'xxxxx')]//ancestor::mat-panel-title";
	String keryServicesforLOCFilter = "//div[contains(@class,'filtered-locs')]//span[contains(text(),'xxxxx')]//ancestor::mat-expansion-panel//li//span";
	String allTeamsTeamCheckbox = "//*[contains(@class,'team-selection')]//li//span[contains(text(),'xxxxx')]//ancestor::label";
	String documentIcon = "//h4[normalize-space(.) = ' xxxxx ']//ancestor::div[@class='referral-card card-row card']//span[@class='documents right ng-star-inserted']";
	String referralOndashboard = "//h4[normalize-space(.) = 'xxxxx']";
	String keyServicesOnReferralCard = "//h4[normalize-space(.) = 'xxxxx']//ancestor::div[@angularticsaction='GotoPatientDetail']//span[contains(.,'Key Services')]/following-sibling::div";
	String providerNameByPosition = "(//div[@id='bed-availability-modal']//div[contains(@class,'provider-name-container')])[xxxxx]";
	String bedAvailabilityFacilityName = "//div[@class='checkbox-container'][contains(.,'xxxxx')]//span[contains(@class,'check')]";
	String notificationTypeCheckbox = "//label[contains(.,'xxxxx')]//input";
	String keyServiceIcon = "//h4[@class='card-title'][contains(.,'xxxxx')]//ancestor::div[contains(@class,'patient-detai-info')]//span[@class='referral-card-label'][contains(text(),'Key Services')]'));";
	String keyServiceValue = "//h4[@class='card-title][contains(.,'xxxxx')]//ancestor::div[contains(@class,'patient-detail-info')]//span[@class='referral-card-label'][contains(text(),'Key Services')]//following-sibling::div[contains(@class,'referral-card-data')]'))";

	String requiredLOCLabel = "//h4[@class='card-title'][contains(.,'xxxxx')]//ancestor::div[contains(@class,'patient-detail-info')]//span[@class='referral-card-label'][contains(text(),'Required Level of Care')]'))";
	String manualeferralIcon = "//h4[@class='card-title'][contains(.,'xxxxx')]//nh-svg-icon[@iconname='ic_unconnected']";
	String asocIconOnReferralValue = "//h4[@class='card-title'][contains(.,'xxxxx')]//ancestor::div[contains(@class,'patient-detail-info')]//span[@class='referral-card-label'][contains(text(),'Anticipated Start of Care')]//following-sibling::div[contains(@class,'referral-card-data')]'))";
	String locFilterLOCCheckbox = "//div[contains(@class,'filtered-locs')]//span[contains(text(),'xxxxx')]//..//input[@type='checkbox']//ancestor::label";
	String referralmultipleLOCIconOnDashboard = "//div[@class='referral-card card-row card'][contains(.,'xxxxx')][contains(normalize-space(),'xxxxx')]//nh-svg-icon[@iconname='ic_content_copy']";
	String referralmultipleLOCProviderName = "//div[@class='popover-body']//table//tbody//tr[contains(.,'xxxxx')]//td[2]";
	String referralmultipleLOCProviderStatus = "//div[@class='popover-body']//table//tbody//tr[contains(.,'xxxxx')]//td[3]";
	String referralmultipleLOCProviderStatusList = "//div[@class='popover-body']//table//tbody//tr[contains(.,'xxxxx')]//td//*";
	String referralInSearchPatientsModal = "//tr[contains(.,'xxxxx')][contains(.,'xxxxx')]";
	String patientCardDivHeader = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][rowNum]//div[contains(text(),'xxxxx')]";

	String patientCardSpanHeader = "(//div[@class='referral-card card-row card'])[1]//div[@class='row'][rowNum]//span[contains(text(),'xxxxx')]";
	String patientCardCustomHeader = "(//div[@class='referral-card card-row card'])[1]//div[@class='col ng-star-inserted']//span[contains(text(),'xxxxx')]";

	String filterButton = "//nh-intake-dashboard-toolbar//button[text()[normalize-space() = 'xxxxx']]";
	String asocIconOnReferral = "//h4[@class='card-title'][contains(.,'xxxxx')]//ancestor::div[contains(@class,'patient-detail-info')]//span[@class='referral-card-label'][contains(text(),'Anticipated Start of Care')]";
	String teamsIcon = "//h4[normalize-space(.) = 'xxxxx']//ancestor::div[@class='referral-card card-row card']//button[@angularticsaction='ShowTeamAssignmentsStatus']";
	String teamStatus = "//div[@class='popover-body']//div[@class='team-assignment ng-star-inserted'][contains(.,'XXXX')]//div[@class='completed-by-info ng-star-inserted']";
	String reactivateBadge = "//h4[normalize-space(.) = 'xxxxx']//ancestor::div[@class='referral-card card-row card']//div[@class='cs-chip-btn reactivated-chip']";
	String referralInSearchModal = "//nh-intake-search-patients-result//td[contains(.,'xxxxx')]";
	
	// Initializing the Page Factory/Objects:
	public IntakeDashboardPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	// Return xpath with parameters
	public WebElement referralOnSearachedResult(String ref) {

		return getdriver()
				.findElement(By.xpath("//div[@id='search-results']//tbody/tr[1]/td[contains(.,'" + ref + "')]"));
	}

	public int getDocumentIconCount() {
		return documentIconOnDashboard.size();
	}
	
    public int getTotalReferralCountInNewTab() {
        return Integer.parseInt(retrieveText(newTabCountValue, "Referral Count in New Tab"));
        
    }

	/**
	 * Actions/Methods on Workbook page:
	 *
	 * @throws InterruptedException
	 **/

	public void clickReferralOnDashboard(String referralName) {
		// click on referral name on dashboard
		clickElement(prepareWebElementWithDynamicXpath(referralOndashboard, referralName),
				"referral Name: " + referralName);
		waitForPageToLoad();
		waitForLoaderToDisappear();
	}

	public void clickSearchPatientsButton() {
		// click on patient search button
		clickElement(searchPatientsButton, "patient search button");
	}

	public void clickContactCustomerSupport() {
		clickElement(customerContactSupport, "Contact Customer Support");
		waitForElementToLoad(userEmail, "User email");
	}

	public void clickDropdownButtonsOfSort() {
		clickElement(sortByDropdownButton, "Sort by drop down button");
	}

	public Boolean isSortByUnreadDocumentsDisplayed() {
		return TestUtil.isElementPresent(sortByUnreadDocuments, "Sort by unread documents option in dropdown button");
	}

	public void clickCancelButton() {
		clickElement(cancelButton, "cancel button on provider filter");
		waitForLoaderToDisappear();
	}

	/**
	 * @param filterName        - "All Providers", "Loc & Services", "All Teams", "All Referral Sources"
	 */
	public void clickOnFilterButton(String filterName) {
		clickElement(prepareWebElementWithDynamicXpath(filterButton, filterName), "filter button");
		waitForLoaderToDisappear();
	}

	public String getProviderNameFromFilterResult(int index) {
		return retrieveText(providerNamesInFilterProvider.get(index), "provider names in filter");
	}
	
	public List<String> getAllProvidersFromProviderFilter() {
		List<String> providerList = new ArrayList<String>();
		for(WebElement providerName: providerNamesInFilterProvider) {
			providerList.add(retrieveText(providerName, "provider name in provider filter modal"));
		}
		return providerList;
	}

	public Boolean isDocumentIconDisplayed(String referralName) {
		return TestUtil.isElementPresent(prepareWebElementWithDynamicXpath(documentIcon, referralName),
				"Document icon on referral");
	}

	public Boolean isMessageIconDisplayed(String referralName) {
		return TestUtil.isElementPresent(prepareWebElementWithDynamicXpath(messageIcon, referralName),
				"Message icon on referral");
	}

	public void clickSearchButtonInPatinetSearchModal() {
		// click on search button inside search modal
		clickElement(searchButton, "search button inside search modal");
		waitForLoaderToDisappear();
	}

	public void clickCloseButtonInPatinetSearchModal() {
		// click on close button in search modal
		clickElement(closeButtonInSerchModal, "search button inside search modal");
	}

	public void clickOnSettingCog() {
		clickElement(settingsCog, "Settings button on Intake");
	}

	public void clickSubmitOnSetting() {
		clickElement(submitButton, "submit button on notification settings ");
	}

	// This method will search Ref on dashboard and click on it
	public void searchAndClickOnReferral(PatientInfoBean referralDetails) throws InterruptedException {
		
		// click on patient search button
		clickSearchPatientsButton();
		// enter referral fullName in search modal
		input(searchPatientTextBox, "referral search text box", referralDetails.getFirstName() + " " + referralDetails.getLastName());
		// click on search button inside search modal
		clickSearchButtonInPatinetSearchModal();
		//click on referral in searched result
		clickElement(prepareWebElementWithDynamicXpath(referralInSearchModal, referralDetails.getReferralName()), "searched referral");
		waitForLoaderToDisappear();
	}

	public boolean referralSearch(PatientInfoBean referralDetails) {
		boolean referralPresence = false;
		// click on patient search button
		clickSearchPatientsButton();
		// enter referral fullName in search modal
		input(searchPatientTextBox, "referral search text box", referralDetails.getFirstName() + " " + referralDetails.getLastName());
		// click on search button inside search modal
		clickSearchButtonInPatinetSearchModal();
		// look for the referral name in search results
		if (retrieveText(searchResults, "search results").contains("No results found.")) {
			referralPresence = false;
		} else {
			for (WebElement element : referralNamesInSerchModal) {
				if (retrieveText(element, "referral search result").equals(referralDetails.getReferralName())) {
					referralPresence = true;
				}
			}
		}
		// close search modal
		clickCloseButtonInPatinetSearchModal();
		return referralPresence;
	}

	public void searchAutoInSearchField() throws InterruptedException {
        // click on close button in search modal
        clearAndInput(searchPatientTextBox, "Search field", "Auto");
        clickElement(searchButton, "search button inside search modal");
        waitForLoaderToDisappear();
        Thread.sleep(10000);
    }
	
	public List<String> getSearchResultsColumnHeaders(){
		List<String> SearchResultsColumnHeaderValues = new ArrayList<String>();
		for(WebElement SearchResultsColumnHeader : searchResultsColumnHeaders) {
			SearchResultsColumnHeaderValues.add(retrieveText(SearchResultsColumnHeader, "Search results column header"));
		}
		 return SearchResultsColumnHeaderValues;
	}
	
	public boolean isSearchButtonPresent() {
        return isElementPresent(searchButton, "Search button");
    }
    
    public boolean isArchiveButtonPresent() {
        return isElementPresent(archiveButtonOnDashboard, "Archive button");
    }
    public boolean isCloseButtonInSearchModalPresent() {
        return isElementPresent(closeButtonInSerchModal, "Close button in search Modal");
    }
    
    public boolean isSearchPatientTextBoxPresent() {
    	return isElementPresent(searchPatientTextBox, "Search patient textBox");
    }
	
	public boolean compareFirstReferralName(String referralName) {
		return (retrieveText(allReferralNames.get(0), "first referral name").trim()).equals(referralName);
	}

	public Boolean isCentralizedIntakeDashboardDispalyed() {
		return isElementPresent(centralizedIntakeDashboard, "centralized Intake Dashboard");
	}

	public void clickOnBedAvailabilityButton() {
		clickElement(bedAvailabilityButton, "Bed availability button");
	}

	public void clickOnCloseBedAvailabilityButton() {
		clickElement(closeButtonInBedAvailabilityModal, "Close Bed availability Modal");
		waitForPageToLoad();
	}

	public void clickOnNotSetBedIcon() {
		clickElement(notSetBedIcon, "Not Set Bed Icon");
		waitForPageToLoad();
	}

	public void clickOnPopOverLinkInNotSetBedIcon() {
		clickElement(popOverLinkInNotSetBedIcon, "Not Set Bed Icon");
		waitForPageToLoad();
	}

	public WebElement teamstatus(String team) {
		return prepareWebElementWithDynamicXpath(teamStatus, team);
	}

	public boolean isAvailableBedIconPresent() {
		return isElementPresent(availableBedIcon, "Available Bed Icon");
	}

	public boolean isNotAvailableBedIconPresent() {
		return isElementPresent(notAvailableBedIcon, "Not Available Bed Icon");
	}

	public boolean isNotSetBedIconPresent() {
		return isElementPresent(notSetBedIcon, "Not Set Bed Icon");
	}

	public boolean isASOSColumnHeaderPresent() {
		return isElementPresent(firstpatientCardFirstRowASOCColumnHeader, "ASOS Column header");
	}

	public boolean isKeyServiceColumnHeaderPresent() {
		return isElementPresent(firstpatientCardFirstRowKeyServicesColumnHeader, "Key Service Column header");
	}

	public boolean isAvailableLegendaryIconPresent() {
		return isElementPresent(availableLegendaryIcon, "Available Legendary Icon");
	}

	public boolean isNotAvailableLegendaryIconPresent() {
		return isElementPresent(notAvailableLegendaryIcon, "Not Available Legendary Icon");
	}

	public boolean isNotSetLegendaryIconPresent() {
		return isElementPresent(notSetLegendaryIcon, "Not Set Legendary Icon");
	}

	public void updateFacilityBedAvailability(String facilityName, boolean bedsAvailable) {
		clickElement(prepareWebElementWithDynamicXpath(bedAvailabilityFacilityName, facilityName),
				"Check Bed Availability checkbox");
		if (bedsAvailable) {
			clickElement(bedsAvailableUpdateButtonInModal, "Update Beds Availability Button");
		} else {
			clickElement(bedsNotAvailableUpdateButtonInModal, "Update Beds Not Availability Button");
		}
		waitForPageToLoad();
	}

	public List<String> getBedAvailabilityProvidersList() {
		List<WebElement> elementList = getdriver().findElements(
				By.xpath("//div[@id='bed-availability-modal']//div[contains(@class,'provider-name-container')]"));
		List<String> providerList = new ArrayList<>();
		for (WebElement element : elementList) {
			providerList.add(retrieveText(element, "provider"));
		}
		return providerList;
	}

	public boolean assertSorting(List<String> listToAssert, boolean ascending) {
		List<String> beforeSortList = new ArrayList<>();
		List<String> afterSortList = new ArrayList<>();
		beforeSortList.addAll(listToAssert);
		if (ascending) {
			Collections.sort(listToAssert);
		} else {
			Collections.sort(listToAssert, Collections.reverseOrder());
		}
		afterSortList.addAll(listToAssert);
		return beforeSortList.equals(afterSortList);
	}

	public void clickOnAlphabeticalSortButtonInModal() {
		clickElement(alphabeticalSortButtonInModal, "Alphabetical Sort Button in Modal");
		waitForPageToLoad();
	}

	public void clickOnAvailabilitySortButtonInModal() {
		clickElement(avilablitySortButtonInModal, "Availability Sort Button in Modal");
		waitForPageToLoad();
	}

	public String getBedAvailabilityProviderNameByPosition(int position) {
		return retrieveText(prepareWebElementWithDynamicXpath(providerNameByPosition, String.valueOf(position)),
				"Provider position : " + position);
	}

	public void openNotificationsModalAndOpenOptions() {
		clickElement(settingsCog, "settings cog");
		clickElement(notificationSettingsOptionInCog, "notification settings");
		waitForPageToLoad();
	}

	public void closeNotificationModal() {
		clickElement(cancelButtonOnNotificationModal, "Notification modal close");
	}

	public List<String> getNotificationsByUserType(String userType) {
		List<WebElement> elementList = null;
		List<String> notificationList = new ArrayList<>();
		switch (userType) {
		case Constants.CENTRALIZED_INTAKE_USER:
			elementList = ciUserNotificationNames;
			break;
		case Constants.READONLY_USER:
			elementList = readOnlyUserNotificationNames;
			break;
		case Constants.READONLY_ARCHIVE_USER:
			elementList = readOnlyArchiveUserNotificationNames;
			break;
		case Constants.COMMUNITY_SERVICE_USER:
			elementList = communityServiceUserNotificationNames;
			break;

		case Constants.COMMUNITY_SERVICE_LITE_USER:
			elementList = csLiteUserNotificationNames;
			break;

		}
		for (WebElement element : elementList) {
			notificationList.add(retrieveText(element, "notification text"));
		}
		return notificationList;
	}

	public List<String> getNotificationSources() {
		List<String> notificationSourceList = new ArrayList<>();
		for (WebElement element : notificationSources) {
			notificationSourceList.add(retrieveText(element, "notification text"));
		}
		return notificationSourceList;
	}

	public boolean isNotificationCheckboxSelected(String notificationType) {
		return prepareWebElementWithDynamicXpath(notificationTypeCheckbox, notificationType).isSelected();
	}

	public String getPatientCardColumnHeaderName(String row, String header) {
		List<String> customHeaders = Arrays.asList("Anticipated Start of Care", "Key Services");
		List<String> spanHeaders = Arrays.asList("Date of Birth", "Unit/Room/Bed", "Payer", "Required Level of Care");
		if (!customHeaders.contains(header)) {
			if (spanHeaders.contains(header)) {
				return retrieveText(
						prepareWebElementWithDynamicXpath(patientCardSpanHeader.replace("rowNum", row), header),
						header);
			} else {
				return retrieveText(
						prepareWebElementWithDynamicXpath(patientCardDivHeader.replace("rowNum", row), header), header);
			}

		} else {
			return retrieveText(prepareWebElementWithDynamicXpath(patientCardCustomHeader, header), header);
		}
	}

	public boolean isDashboardLogoDisplayed() {
		return isElementPresent(dashboardLogo, "Dashboard Logo");
	}

	public boolean isNotificationSettingsDisplayed() {
		return isElementPresent(notificationSettingsOptionInCog, "Notifications settings option in settings");
	}

	public boolean isCustomerContactSupportDisplayed() {
		return isElementPresent(customerContactSupport, "Customer Contact Support settings option in settings");
	}

	public boolean isChangePasswordDisplayed() {
		return isElementPresent(changePasswordOptionInCog, "Changepassword settings option in settings");
	}

	public String getCopyRightText() {
		return retrieveText(copyRight, "Copyright Logo");
	}

	public void clickChangePasswordOptionInSettingsCog() {
		clickElement(changePasswordOptionInCog, "change Password Option In Cog");
		waitForPageToLoad();
	}

	public void enterCurrentPassword(String currentPassword) {
		TestUtil.clearAndInput(currentPasswordInput, "current Password Input", currentPassword);
	}

	public void enterNewPassword(String newPassword) {
		TestUtil.clearAndInput(newPasswordInput, "new Password Input", newPassword);
	}

	public void enterWrongPassword(String wrongPassword) {
		TestUtil.clearAndInput(currentPasswordInput, "currentPasswordInput", wrongPassword);
	}

	public boolean isSaveButtonPresent() {
		return isElementPresent(saveChangesButtonInChangePassword, "saveChangesButtonInChangePassword");
	}

	public void clickCancelButtonInChangePassword() {
		clickElement(cancelButtonInChangePasswordePage, "cancelButtonInChangePassword");
	}

	public String getKeyServiceOnReferralCard(String referralName) {
		return retrieveText(prepareWebElementWithDynamicXpath(keyServicesOnReferralCard, referralName),
				"KeyService Name");
	}

	public String getCustomerSupportDetailsText() {
		return retrieveText(customerSupportDetails, "customerSupportDetails");
	}

	public void enterCustomerSupportDetails(String details) {
		clearAndInput(customerSupportDetails, "customerSupportDetails", details);
	}

	public void clickLOCFilter() {
		clickElement(LOCFilter, "LOCFilter");

	}

	public void clickFilterModalUpdate() {
		clickElement(filterModalUpdate, "FilterModalClearAll");
	}

	public void clickFilterModalClearAll() {
		clickElement(filterModalClearAll, "getFilterModalClearAll");
	}

	public void clickLOCAndReferralSourceFilterModalClearAll() {
		clickElement(LOCAndReferralSourceFilterModalClearAll, "LOCAndReferralSourceFilterModalClearAll");

	}

	public void clickLOCfilterModalUpdate() {
		clickElement(LOCfilterModalUpdate, "LOCfilterModalUpdate");
	}

	public String getAttributeLOCAndReferralSourceFilterModalClearAll(String attributeName, String elementname) {
		return retrieveAttributeValue(LOCAndReferralSourceFilterModalClearAll, attributeName, elementname);
	}

	public String getAttributeFilterModalClearAll(String attributeName, String elementname) {
		return retrieveAttributeValue(filterModalClearAll, attributeName, elementname);
	}

	public void clickallTeamFilter() {
		clickElement(allTeamFilter, "allTeamFilter");
	}

	public boolean isAsocIconOnReferralPresent(String referralName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(asocIconOnReferral, referralName), "ASOC ICON");
	}

	public void clickSortByDropdownButton() {
		clickElement(sortByDropdownButton, "soryByDropdownButton");
	}

	public WebElement sortByASOC() {
		return sortByASOC;
	}

	public boolean isSortByASOCPresent() {
		return isElementPresent(sortByASOC, "Sort By ASOC");
	}

	public boolean isCentralizedIntakeDashboardPresent() {
		return isElementPresent(centralizedIntakeDashboard, "centralizedIntakeDashboard");
	}

	public WebElement teamsIcon(String referralName) {
		return prepareWebElementWithDynamicXpath(teamsIcon, referralName);
	}

	public boolean isTeamsIconPresent(String referralname) {
		return isElementPresent(teamsIcon(referralname), "teamsIcon in" + referralname);
	}

	public String teamsIconText(String referralName) {
		return retrieveText(teamsIcon(referralName), "teamsIcon text");
	}

	public void clickTeamsIcon(String referralName) {
		clickAndWaitForPageLoad(teamsIcon(referralName), "teamsIcon");
	}

	public boolean isTeamAssignmentPopUpPresent() {
		return isElementPresent(teamAssignmentPopUp, "teamAssignmentPopUp");
	}

	public String teamAssignmentPopUpText() {
		return retrieveText(teamAssignmentPopUp, "teamAssignmentPopUp");
	}

	public boolean isTeamAssignmentCompletedIconPresent() {
		return isElementPresent(teamAssignmentCompletedIcon, "teamAssignmentCompletedIcon");
	}

	public String teamStatusText(String team) {
		return retrieveText(teamstatus(team), "teamAssignmentPopUp");
	}

	public String teamsIconPopOverTeam1Text(String team) {
		return retrieveText(teamsIconPopOverTeamList.get(0), "teamAssignmentPopUp");
	}

	public void clickTeamsCheckbox(String team) {
		clickAndWaitForPageLoad(prepareWebElementWithDynamicXpath(allTeamsTeamCheckbox, team), "allTeamsTeamCheckbox");
	}

	public void clickCancelTeamFilterModal() {
		clickAndWaitForPageLoad(cancelTeamFilterModal, "cancelTeamFilterModal");
	}

	public String getAttributeTeamFilterModalUpdateButton(String attr) {
		return retrieveAttributeValue(teamsFilterModalUpdate, attr, "teamsFilterModalUpdate");
	}

	public boolean isReferralmultipleLOCIconOnDashboardPresent(String referralname, String LOC) {
		return isElementPresent(
				prepareWebElementWithDynamicXpath(
						referralmultipleLOCIconOnDashboard.replaceFirst("xxxxx", referralname), LOC),
				"referralmultipleLOCIconOnDashboard");

	}

	public void clickReferralmultipleLOCIconOnDashboard(String referralname, String LOC) {
		clickAndWaitForPageLoad(
				prepareWebElementWithDynamicXpath(
						referralmultipleLOCIconOnDashboard.replaceFirst("xxxxx", referralname), LOC),
				"referralmultipleLOCIconOnDashboard");

	}

	public void clickReferralmultipleLOCProviderStatusList(String LOC) {
		clickAndWaitForPageLoad(prepareWebElementWithDynamicXpath(referralmultipleLOCProviderStatusList, LOC),
				"referralmultipleLOCProviderStatusList");
	}

	public String referralmultipleLOCProviderNameText(String LOC) {
		return retrieveText(prepareWebElementWithDynamicXpath(referralmultipleLOCProviderName, LOC),
				"referralmultipleLOCProviderName");
	}

	public String referralmultipleLOCProviderStatusText(String LOC) {
		return retrieveText(prepareWebElementWithDynamicXpath(referralmultipleLOCProviderStatus, LOC),
				"referralmultipleLOCProviderStatus");
	}

	public void setTeamsFilter(String Teams) {
		clickallTeamFilter();
		String attr = teamsFilterClearAllGetAttribute("disabled");
		reportLog(attr + "-------------------------------------->>");

		// Clear LOC if it is not already cleared
		if ((attr == null)) {
			System.out.println("it is enabled");
			clickTeamsFilterClearAll();
			clickTeamsCheckbox(Teams);
			String attr1 = getAttributeTeamFilterModalUpdateButton("disabled");
			if (attr1 == null) {
				clickTeamsFilterModalUpdate();
			} else {
				clickCancelTeamFilterModal();
			}
		} else {
			System.out.println("it is disabled");

			clickTeamsCheckbox(Teams);
			clickTeamsFilterModalUpdate();
		}
		waitForLoaderToDisappear();
	}

	public String teamsFilterClearAllGetAttribute(String attr) {
		return retrieveAttributeValue(teamsFilterClearAll, attr, "teamsFilterClearAll");
	}

	public void clickTeamsFilterClearAll() {
		clickAndWaitForPageLoad(teamsFilterClearAll, "teamsFilterClearAll");
	}

	public boolean isLOCfilterPresent() {
		return isElementPresent(LOCFilter, "LOCFilter");

	}

	public String LOCFilterText() {
		return retrieveText(LOCFilter, "LOCFilter");

	}

	public String LOCFilterCSS(String css) {
		return retrieveCSSValue(LOCFilter, css, "LOCFilter");

	}

	public void clickTeamsFilterModalUpdate() {
		clickAndWaitForPageLoad(teamsFilterModalUpdate, "teamsFilterModalUpdate");
	}

	public boolean isAllTeamFilterPresent() {
		return isElementPresent(allTeamFilter, "allTeamFilter");
	}

	public String allTeamFilterText() {
		return retrieveText(allTeamFilter, "allTeamFilter");
	}

	public void clickOnReferralInDashboardForASpecificLOC(String referralName, String LOC) {
		clickElement(
				prepareWebElementWithDynamicXpath(referralInSearchPatientsModal.replaceFirst("xxxxx", referralName),
						LOC),
				"referral " + referralName + " in Dashboard page");
		waitForLoaderToDisappear();
	}

	public boolean isManualReferralIconPresent(String patientName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(manualeferralIcon, patientName), "manualeferralIcon");

	}

	public boolean isRequiredLOCLabelPresent(String patientName) {
		return isElementPresent(requiredLOCLabel(patientName), "requiredLOCLabel");

	}

	public String requiredLOCLabelText(String patientName) {
		return retrieveText(requiredLOCLabel(patientName), "requiredLOCLabelText");

	}

	public WebElement requiredLOCLabel(String patientName) {
		return prepareWebElementWithDynamicXpath(requiredLOCLabel, patientName);

	}

	public void assertPatientTileLabelsAndFieldsOnConnected(String patientName) {
		isManualReferralIconPresent(patientName);
	}

	public WebElement asocIconOnReferral(String PatientName) {
		return prepareWebElementWithDynamicXpath(asocIconOnReferralValue, PatientName);
	}

	public String popUpTitleText() {
		return retrieveText(popUpTitle, "popUpTitle");
	}

	public String asocIconOnReferralText(String PatientName) {
		return retrieveText(asocIconOnReferral(PatientName), "asocIconOnReferral");
	}

	public boolean isKeyServiceIconPresent(String patientName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(keyServiceIcon, patientName), "keyServiceIcon");
	}

	public WebElement keyServiceValue(String PatientName) {
		return prepareWebElementWithDynamicXpath(keyServiceValue, PatientName);
	}

	public String keyServiceValueText(String PatientName) {
		return retrieveText(keyServiceValue(PatientName), "keyServiceValue");
	}


	public void clickLocFilterLOCCheckbox(String LOC) {
		clickElement(prepareWebElementWithDynamicXpath(locFilterLOCCheckbox, LOC), "Locfiltercheck " + LOC);
	}

	public void verifyTeamsIconTextAfterFiltered(String teams) {
		waitForLoaderToDisappear();
		for (int i = 0; i < allTeamsIcon.size(); i++) {
			clickAndWaitForPageLoad(allTeamsIcon.get(i), "allTeamsIcon");
			assertTrue(retrieveText(teamAssignmentPopUp, "teamAssignmentPopUp").contains(teams));
		}
	}

	public void assertPatientTileLabelsAndFieldsOnUnConnected(String patientName) {

		// Asserting MR icon present
		// assertTrue(TestUtil.isElementPresent(IntakeDashboardPage.manualReferralIcon(patientName)));
		assertTrue(isManualReferralIconPresent(patientName));

		// Asserting ASOC label present with ASOC value as --
		assertTrue(isAsocIconOnReferralPresent(patientName));

		assertTrue(asocIconOnReferralText(patientName).contains("--"));

		// Asserting KeyServices label and Value is present
		assertTrue(isKeyServiceIconPresent(patientName));

		Assert.assertEquals(keyServiceValueText(patientName), "COVID-Ready");

		// Assert LOC label and value is present
		assertTrue(isRequiredLOCLabelPresent(patientName));

		Assert.assertEquals(requiredLOCLabelText(patientName), testData.getString("LOCValue"));

		reportLog("In method - Executing : verifyLabelsAndFieldsOnUnconnectedPatientTile");
	}

	public List<String> getAllLocFilterValues() {
		List<String> allLocFiltervalues = new ArrayList<>();
		for (int i = 0; i < locFilterLOCVAlues.size(); i++) {
			allLocFiltervalues.add(retrieveText(locFilterLOCVAlues.get(i), "Loc filtervalues" + i));
		}
		return allLocFiltervalues;
	}

	public void isRequiredLOCValuesContainsFilteredLoc(String LOC) {

		for (int i = 0; i < requiredLOCValues.size(); i++) {
			assertTrue(retrieveText(requiredLOCValues.get(i), "requiredLOCValues").contains(LOC));
		}
	}

	public void isReferralKeyServiceContainsFilteredKeyservice(List<String> filteredKeyServices) {

		for (int i = 0; i < allReferralKeyServices.size(); i++) {
			assertTrue(
					filteredKeyServices.contains(retrieveText(allReferralKeyServices.get(i), "allReferralKeyServices"))
							|| retrieveText(allReferralKeyServices.get(i), "allReferralKeyServices").contains("--"));
		}
	}

	public void clickAllLocExpandIcon() {
		for (int i = 0; i < allLOCExpandButtons.size(); i++) {
			clickAndWaitForPageLoad(allLOCExpandButtons.get(i), "Loc expand button" + i);
		}
	}

	public void isRequiredLOCValuesContainsFilteredLoc(String LOC1, String LOC2) {

		for (int i = 0; i < requiredLOCValues.size(); i++) {
			assertTrue(retrieveText(requiredLOCValues.get(i), "requiredLOCValues").contains(LOC1)
					|| retrieveText(requiredLOCValues.get(i), "requiredLOCValues").contains(LOC2));
		}
	}

	public void expandAllLoc() {
		for (int i = 0; i < allLOCExpandButtons.size(); i++) {
			clickAndWaitForPageLoad(allLOCExpandButtons.get(i), "Loc expan button");
		}
	}

	public void expandLoc(String LOC) {
		clickAndWaitForPageLoad(prepareWebElementWithDynamicXpath(expandLoc, LOC), "Clik" + LOC);
	}

	public List<String> getAllKeyServicesFilterValues() {
		List<String> allkeyServicesFiltervalues = new ArrayList<>();
		for (int i = 0; i < locFilterKeyServicesValues.size(); i++) {
			String text = retrieveText(locFilterKeyServicesValues.get(i), "Loc filtervalues" + i);
			if (!text.contains("Without Key Services")) {
				allkeyServicesFiltervalues.add(text);

			}
		}
		return allkeyServicesFiltervalues;
	}

	public List<String> getKeyserviceForLoc(String LOC) {
		List<String> keyServcies = new ArrayList<>();
		List<WebElement> keyServicesElement = prepareWebElemenstWithDynamicXpath(keryServicesforLOCFilter, LOC);
		for (int i = 0; i < keyServicesElement.size(); i++) {
			String text = retrieveText(keyServicesElement.get(i), "KEyservice filtervalues" + i);
			if (!text.contains("Without Key Services")) {
				keyServcies.add(text);

			}
		}
		return keyServcies;
	}



	public String tooltipText() {
		return retrieveText(tooltip, "tooltip");
	}

	

	public String firstReferralNameText() {
		return retrieveText(firstReferralName, "firstReferralName");
	}

	public void clickFirstReferralName() {
		clickAndWaitForPageLoad(firstReferralName, "firstReferralName");
		waitForLoaderToDisappear();
	}
	
	public boolean isReactivateBadgePresent(String referralName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(reactivateBadge, referralName), "reactivate beage for referral: " + referralName);
	}
	
	public String getReactivateBadgeTooltip(String referralName) {
		hoverOver(prepareWebElementWithDynamicXpath(reactivateBadge, referralName));
		return retrieveText(tooltipComponent, "tooltip component");
	}
	
	public boolean isReferralPresent(String referralName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(referralOndashboard, referralName), referralName + "on dashboard");
	}

}
