package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.qa.util.TestUtil.*;
import static org.testng.Assert.*;

public class LegacySearchPage extends TestBase {
    // Initializing the Page Factory/Objects:
    public LegacySearchPage() {
        PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
        // be initialized with this driver
    }

    @FindBy(xpath = "//span[@class='mat-button-wrapper']")
    WebElement arrowIconForDropdown;
    @FindBy(xpath = "//button[contains(@id,'patient-summary')]")
    WebElement patientSummaryBtn;
    @FindBy(xpath = "//button[contains(@id,'case-history')]")
    WebElement caseHistoryBtn;
    @FindBy(xpath = "//div[@id='patient-summary']")
    WebElement patientSummaryModal;
    @FindBy(xpath = "//div[@id='patient-summary']//h1[@class='modal-title']")
    WebElement patientSummaryTitle;
    @FindBy(xpath = "//button[contains(text(), 'PRINT')]")
    WebElement summaryPrint;
    @FindBy(xpath = "//button[contains(text(), 'CANCEL')]")
    WebElement summaryCancel;
    @FindBy(xpath = "//div[contains(@id,'mat-dialog-title')]/div[1]/h4")
    WebElement caseHistoryTitle;
    @FindBy(xpath = "//span[normalize-space(text())='EXPAND ALL']")
    WebElement chExpandAllButton;
    @FindBy(xpath = "//span[normalize-space(text())='COLLAPSE ALL']")
    WebElement chCollapseAllButton;
    @FindBy(xpath = "//button[normalize-space(text())='CLOSE']")
    WebElement caseHistoryCloseButton;
    @FindBy(xpath = "//nh-ls-app-patient-case-history-modal/div")
    WebElement caseHistoryModal;
    @FindBy(xpath = "//nh-ls-app-patient-case-history-modal//mat-row[1]")
    WebElement firstCaseHistoryRecord;
    @FindBy(xpath = "(//nh-ls-patient-summary-modal//div[normalize-space()='Patient Name:'])[1]/following-sibling::div/strong")
    WebElement patientNameOnPatSummary;
    @FindBy(xpath = "(//nh-ls-patient-summary-modal//div[normalize-space()='MRN:'])[1]/following-sibling::div/strong")
    WebElement mrnOnPatSummary;
    @FindBy(xpath = "(//nh-ls-patient-summary-modal//div[normalize-space()='Account Number:'])[1]/following-sibling::div/strong")
    WebElement accNumberOnPatSummary;
    @FindBy(xpath = "//div[contains(@class,'app-title')]/a/span")
    WebElement legacySearchHeaderDropdown;
    @FindBy(xpath = "//div[@class='search-label']")
    WebElement searchSubheaderText;
    @FindBy(xpath = "//div[@class='patient-info-row']")
    WebElement searchResult;
    @FindBy(xpath = "//div[@class='workbook-search-form']//button[@id='btn-search']")
    WebElement searchIcon;
    @FindBy(id = "btn-clear-search")
    WebElement clearBtn;
    @FindBy(id = "accountNumber")
    WebElement accNumInput;
    @FindBy(id = "mrn")
    WebElement mrnInput;
    @FindBy(id = "ssn")
    WebElement ssnInput;
    @FindBy(id = "firstName")
    WebElement fnameInput;
    @FindBy(id = "lastName")
    WebElement lnameInput;
    @FindBy(xpath = "//div[contains(@class,'error-message-label')]")
    WebElement errorMessageForFname;
    @FindBy(xpath = "//div[@class='row patient-info-row']")
    WebElement searchResultClinicalDetailRow;

    final String noResultText = "No Results Found\nPlease update or add search criteria to view results.";
    final String defaultSearchResultText = "You are now searching in Legacy CarePort Discharge.\nPlease update or add search criteria to view results.";
    final String errorMessage = "Please provide last name in order to use first name in your search.";

    public void assertLegacySearchLandingPage() {
        reportLog("Asserting the URL");
        assertTrue(getURL().contains("legacysearch"));
        reportLog("Assertion Pass: URL contains legacysearch string");
        reportLog("Asserting the dropdown and text");
        assertTrue(isElementPresent(legacySearchHeaderDropdown, "Legacy Search Dropdown"));
        assertEquals(retrieveText(legacySearchHeaderDropdown, "Legacy Search Header Dropdown"), "Legacy Search Tool");
        reportLog("Assertion Pass: Legacy Search Dropdown is present");
        reportLog("Asserting legacy search subheader text.");
        assertEquals(retrieveText(searchSubheaderText, "Legacy Search Subheader Text"), "Search Patients by completing one of the following fields:");
        reportLog("Assertion Pass: Legacy Search subheader is present");
        reportLog("Asserting legacy search default search result text.");
        assertEquals(retrieveText(searchResult, "Deafult Search Result Container"), "You are now searching in Legacy CarePort Discharge.\nPlease update or add search criteria to view results.");
        reportLog("Assertion Pass: Legacy search default search result text is present");
        reportLog("Asserting legacy search button is disabled");
        assertFalse(isEnabled(searchIcon, "Search Btn"));
        reportLog("Assertion Pass: legacy search button is disabled");
        reportLog("Asserting clear button is present");
        assertTrue(isElementPresent(clearBtn, "Clear Button"));
        reportLog("Assertion Pass: clear button is present");
    }


    public void searchWithInvalidData() {
        //search with invalid mrn
        reportLog("Search with invalid mrn");
        clearAndInput(mrnInput, "MRN input", "abcd");
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(retrieveText(searchResult, "Search Result").contains(noResultText));

        clearAllInputData();

        //search with invalid account number
        reportLog("Search with invalid account number");
        clearAndInput(accNumInput, "AccNumber input", "xyz");
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(retrieveText(searchResult, "Search Result").contains(noResultText));

        clearAllInputData();

        //search with only first name (NOT ALLOWED)
        reportLog("Searching with only first name and asserting");
        clearAndInput(fnameInput, "First Name Input", "098");
        waitForPageToLoad();
        reportLog("Asserting search button is disabled");
        assertFalse(isEnabled(searchIcon, "Search Button"));
        reportLog("Asserting the error message");
        assertTrue(isElementPresent(errorMessageForFname, "Error Message"));
        assertTrue(retrieveText(errorMessageForFname, "Error Message").contains(errorMessage));

        //enter last name and check if error message is gone
        reportLog("Inputting values to Last Name field to check the error message is gone");
        clearAndInput(lnameInput, "Last Name Input", "xyzabcd");
        waitForPageToLoad();
        reportLog("asserting search button is now enabled");
        assertTrue(isEnabled(searchIcon, "Search Button"));
        assertFalse(isElementPresent(errorMessageForFname, "Error Message"));
        //search and assert for no results message
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(retrieveText(searchResult, "Search Result").contains(noResultText));

        //clear search results
        clearAllInputData();

        //search with only lname
        reportLog("Searching with only last name");
        clearAndInput(lnameInput, "Last Name Input", "xyzabcd");
        waitForPageToLoad();
        reportLog("Asserting search button is now enabled");
        assertTrue(isEnabled(searchIcon, "Search Button"));
        //search and assert for no results message
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(retrieveText(searchResult, "Search Result").contains(noResultText));

        clearAllInputData();
    }

    public void clearAllInputData() {
        //clear search results
        clickAndWaitForPageLoad(clearBtn, "Clear Button");
        reportLog("Clearing search results & Input data fields");
        assertEquals(retrieveText(searchResult, "Default Search Result Container"), defaultSearchResultText);
        reportLog("Legacy search default search result text is present after clearing search results");
    }


    //Send all String patientDob,String patientName, String mrn, String accNum, String ssn, String admitDate, String estDischDate, String actualDischDate, String unit, String room, String primaryCont in String[]
    public void assertSearchResultStaticData(List<String> staticPatientDetail) {
        reportLog("Asserting the search result patient data");
        String patientDetails = retrieveText(searchResult, "Search Result/Patient details");
        String patientClinicalDetails = retrieveText(searchResultClinicalDetailRow, "Search Result/Patient Clinical Details");
        String allDetails = patientDetails + " " + patientClinicalDetails;
        for (String s : staticPatientDetail) {
            assertTrue(allDetails.contains(s));
        }
        reportLog("Patient DOB,Name, MRN, Acc Number, SSN, Admit date, Actual Discharge Date, Est Discharge Date, Unit info, Room/Bed info, Primary contact info- ALL correct");
        reportLog("Asserting header titles of the clinical details");
        assertTrue(patientClinicalDetails.contains("Admit Date"));
        assertTrue(patientClinicalDetails.contains("Est. Discharge Date"));
        assertTrue(patientClinicalDetails.contains("Actual Discharge Date"));
        assertTrue(patientClinicalDetails.contains("Unit"));
        assertTrue(patientClinicalDetails.contains("Room/Bed"));
        assertTrue(patientClinicalDetails.contains("Primary Contact"));
    }

    public void searchWithValidMRN(String validMrn) {
        clearAllInputData();
        //search with valid mrn
        reportLog("Search with valid mrn");
        clearAndInput(mrnInput, "MRN input", validMrn);
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(isElementPresent(searchResult, "Search Result"));
    }

    public void searchWithValidACC(String validAcc) {
        clearAllInputData();
        //search with valid acc
        reportLog("Search with valid account number");
        clearAndInput(accNumInput, "ACC input", validAcc);
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(isElementPresent(searchResult, "Search Result"));
    }

    public void searchWithValidSSN(String validSsn) {
        clearAllInputData();
        //search with valid ssn
        reportLog("Search with valid SSN");
        clearAndInput(ssnInput, "SSN input", validSsn);
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(isElementPresent(searchResult, "Search Result"));
    }

    public void searchWithValidLname(String validLname) {
        clearAllInputData();
        //search with valid Lname
        reportLog("Search with valid Last Name");
        clearAndInput(lnameInput, "LName input", validLname);
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(isElementPresent(searchResult, "Search Result"));
    }

    public void searchWithValidFnameLname(String validFname, String validLname) {
        clearAllInputData();
        //search with valid Lname& Fname
        reportLog("Search with valid Last Name & First Name");
        clearAndInput(lnameInput, "LName input", validLname);
        clearAndInput(fnameInput, "FName input", validFname);
        clickAndWaitForPageLoad(searchIcon, "Search button");
        waitForLoaderToDisappear();
        assertTrue(isElementPresent(searchResult, "Search Result"));
    }

    public void openPatientDropdownAndNavigateTo(String whichPage) {
        waitForElementToLoad(arrowIconForDropdown, "Arrow Icon for Dropdown");
        clickElement(arrowIconForDropdown, "Arrow Icon For Dropdown");
        if (whichPage.contains("patSum")) {//pat summary
            waitForElementToLoad(patientSummaryBtn, "Patient Summary Button");
            clickAndWaitForPageLoad(patientSummaryBtn, "Patient Summary Button");
            waitForLoaderToDisappear();
            waitForElementToLoad(patientSummaryModal, "Patient Summary window");
        } else {//casehistory
            waitForElementToLoad(caseHistoryBtn, "Case History Button");
            clickAndWaitForPageLoad(caseHistoryBtn, "Case History Button");
            waitForLoaderToDisappear();
            waitForElementToLoad(caseHistoryModal, "Patient Summary window");
        }
    }

    public void assertCaseHistoryModal(String patName) {
        reportLog("Asserting case history modal UI");
        assertTrue(retrieveText(caseHistoryTitle, "Case History Header/Title").contains("Case History for " + patName));
        reportLog("Case history title asserted.");
        reportLog("Asserting expand all and collapse all button presence");
        assertTrue(isElementPresent(chCollapseAllButton, "Collapse All Button"));
        assertTrue(isElementPresent(chExpandAllButton, "Expand All Button"));
        reportLog("Asserting if case history data is showing up");
        assertTrue(isElementPresent(firstCaseHistoryRecord, "first case record of case history"));
        reportLog("Asserting Close button is present and enabled");
        assertTrue(isElementPresent(caseHistoryCloseButton, "Case History Close Button"));
        assertTrue(isEnabled(caseHistoryCloseButton, "Case History Close Button"));
        reportLog("Asserting close button is working");
        clickAndWaitForPageLoad(caseHistoryCloseButton, "Case History Close Button");
        assertFalse(isElementPresent(caseHistoryModal, "Case History Modal"));
        reportLog("Successfully closed case history modal");

    }


    public void assertPatientSummaryModal(String patName, String mrn, String accNum) {
        reportLog("Asserting Patient Summary modal UI");
        assertTrue(retrieveText(patientSummaryTitle, "Patient Summary Header/Title").contains("Patient Detailed Summary"));
        reportLog("Asserting patient name,mrn and account number on patient summary");
        assertTrue(retrieveText(patientNameOnPatSummary, "Pat Name").toLowerCase().contains(patName.toLowerCase()));
        assertTrue(retrieveText(mrnOnPatSummary, "MRN").contains(mrn));
        assertTrue(retrieveText(accNumberOnPatSummary, "Acc Number").contains(accNum));
        reportLog("Asserting presence of cancel and print buttons");
        assertTrue(isElementPresent(summaryCancel, "CancelButton"));
        assertTrue(isElementPresent(summaryPrint, "PrintButton"));
        reportLog("Asserting the cancel button functionality");
        scrollPageToElementAndClick(summaryCancel, "Summary Cancel Button");
        waitForPageToLoad();
        assertFalse(isElementPresent(patientSummaryModal, "Patient Summary Modal"));
        reportLog("Successfully closed the pat summary modal");
    }

}