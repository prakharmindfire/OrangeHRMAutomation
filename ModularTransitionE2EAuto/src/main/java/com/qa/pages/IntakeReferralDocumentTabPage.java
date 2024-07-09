package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.*;
import static com.qa.base.TestBase.reportLog;
import java.util.ArrayList;
import java.util.List;

					  

public class IntakeReferralDocumentTabPage {

	// Locators in Document tab in referral details page
	@FindBy(xpath = "//button[@angularticsaction='DocumentTabActivate']/span[contains(.,'New')]")
	WebElement referralDetailsDocumentNewtag;

	@FindBy(xpath = "//button[@angularticsaction='DocumentTabActivate']")
	WebElement referralDetailsDocumentTab;

	@FindBy(xpath = "//table[@id='docs-table']/tbody")
	WebElement referralDocumentsSection;

	@FindBy(xpath = "//button[contains(.,'Mark All Read')]")
	WebElement markAllReadButton;
	
	@FindBy(xpath="//mat-dialog-container//button[contains(text(),\"Yes\")]")
	WebElement yesButtonInModal;
	
	@FindBy(xpath="//a[@class='clickable' and @angularticsaction= 'ViewDocument']")
	List<WebElement> documentsNames;
	
	@FindBy(xpath = "//table[@id='docs-table']//thead")
    List<WebElement> patientDetailsDocumentColumnHeaders;
	
	@FindBy(xpath = "//table[@id='docs-table']//thead//tr//th//span[@class='checkbox-material header-checkbox cs-checkbox-label-margin']")
	WebElement documentHeaderCheckbox;

	@FindBy(xpath = "//span[contains(text(),'Print Preview')]")
	WebElement printPrviewInDocumentTab;
	
	@FindBy(css="button[angularticsaction=\"UnAttachDocument\"]")
	List<WebElement> unattachDocumentButtons;

	@FindBy(id = "document-header")
	WebElement documentNameColumn;
	@FindBy(id = "date-header")
	WebElement dateColumn;
	@FindBy(id = "size-header")
	WebElement documentSizeColumn;
	@FindBy(id = "added-by-header")
	WebElement documentAddedByColumn;
	@FindBy(id = "last-read-header")
	WebElement documentLastReadColumn;
	@FindBy(xpath = "//input[@id='check-all']//following-sibling::span")
	WebElement selectAllCheckBox;
	@FindBy(xpath = "//button[contains(.,'Print Preview')]")
	WebElement printPreview;
	@FindBy(xpath = "//table[@id='docs-table']//td[2]/a")
	List<WebElement> documents;
	@FindBy(xpath = "//table[@id='docs-table']//td[2]/span")
	List<WebElement> documentsDeclinedCancelled;
	@FindBy(xpath = "//table[@id='docs-table']//td[6]")
	List<WebElement> addedByName;
	@FindBy(xpath = "//table[@id='docs-table']//td[3]//span[contains(@class,'small-date')]")
	List<WebElement> documentDateList;
	@FindBy(xpath = "//table[@id='docs-table']//td[5]//span")
	List<WebElement> documentPagesList;
	@FindBy(xpath = "//table[@id='docs-table']//td[7]")
	List<WebElement> lastReadName;
	@FindBy(xpath = "//table[@id='docs-table']//tbody//tr//span[contains(@class, 'checkbox-material')]")
	List<WebElement> documentCheckboxes;

	// dynamic locators
	String documentCheckBox = "//tr[contains(.,'xxxxx')]//td[1]//span[contains(@class, 'checkbox-material')]";
	String documentName = "//tr[contains(.,'xxxxx')]//td[2]/a";
	String documentDate = "//tr[contains(.,'xxxxx')]//td[3]//span[contains(@class,'small-date')]";
	String newTagForDocument = "//tr[contains(.,'xxxxx')]//td[4]";
	String documentPages = "//tr[contains(.,'xxxxx')]//td[5]//span";
	String documentAddedBy = "//tr[contains(.,'xxxxx')]//td[6]";
	String documentLastRead = "//tr[contains(.,'xxxxx')]//td[7]";
	String declinedCancelledDocumentName = "//tr[contains(.,'xxxxx')]//td[2]/span";
	
	
	// Initializing the Page Factory/Objects:
	public IntakeReferralDocumentTabPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
														// be initialized with this driver
	}

	public void clickOnMarkRead() {
		clickElement(markAllReadButton, "Mark All read Button");
		waitForLoaderToDisappear();
		reportLog("Clicked on Mark All Read button");
	}
	
	public List<String> getpatientDetailsDocumentColumnHeaders(){
		List<String> PatientDetailsDocumentColumnHeadersValues = new ArrayList<String>();
		for(WebElement patientDetailsDocumentColumnHeader : patientDetailsDocumentColumnHeaders) {
			PatientDetailsDocumentColumnHeadersValues.add(retrieveText(patientDetailsDocumentColumnHeader, "Search results column header"));
		}
		 return PatientDetailsDocumentColumnHeadersValues;
	}

public void clickDocumentHeaderCheckbox() {
        clickElement(documentHeaderCheckbox, "document header checkbox");
    }
	
	
	public void isPrintPrviewInDocumentTabEnabled() {
        isEnabled(printPrviewInDocumentTab, "print privew button");
    }
	
	public String documentsNames1Text() {
		return retrieveText(documentsNames.get(0), "documentsNames"+1);
	}

	public String getDocumentTabText() {
		return retrieveText(referralDetailsDocumentTab, "referral documnet tab");
	}

	public String getDocumentTag(String documentName) {
		return retrieveText(prepareWebElementWithDynamicXpath(newTagForDocument, documentName),
				"New tag For Documents");
	}

	public boolean isDocumentPresent(String documentName) {
		return (retrieveText(referralDocumentsSection, "Document name")).contains(documentName);
	}
	public void clickyesButtonInModal() {
		clickAndWaitForPageLoad(yesButtonInModal, "yesButtonInModal");
		waitForLoaderToDisappear();
	}
	
	public boolean isDocumentNameColumnPresent() {
		return isElementPresent(documentNameColumn, "document name column");
	}

	public boolean isDateColumnPresent() {
		return isElementPresent(dateColumn, "date column");
	}

	public boolean isDocumentSizeColumnPresent() {
		return isElementPresent(documentSizeColumn, "document size column");
	}

	public boolean isDocumentAddedByColumnPresent() {
		return isElementPresent(documentAddedByColumn, "document added by column");
	}

	public boolean isDocumentLastReadColumnPresent() {
		return isElementPresent(documentLastReadColumn, "document last read column");
	}

	public boolean isSelectAllCheckBoxPresent() {
		return isElementPresent(selectAllCheckBox, "select all check box");
	}

	public boolean isPrintPreviewPresent() {
		return isElementPresent(printPreview, "print preview");
	}
	
	public int documentCount() {
		return documents.size();
	}
	
	public List<String> getDoucumentNames(){
		List<String> documentNames = new ArrayList<String>();
		for(WebElement docName: documents) {
			documentNames.add(retrieveText(docName, "document name"));
		}
		return documentNames;
	}
	
	public List<String> getDeclinedCancelledDoucumentNames(){
		List<String> documentNames = new ArrayList<String>();
		for(WebElement docName: documentsDeclinedCancelled) {
			documentNames.add(retrieveText(docName, "document name"));
		}
		return documentNames;
	}
	
	public String getDocumentAddedBy(String documentName) {
		return retrieveText(prepareWebElementWithDynamicXpath(documentAddedBy, documentName), "document added by");
	}
	
	public String getDocumentDate(String documentName) {
		return retrieveText(prepareWebElementWithDynamicXpath(documentDate, documentName), "document date");
	}
	
	public String getDocumentPages(String documentName) {
		return retrieveText(prepareWebElementWithDynamicXpath(documentPages, documentName), "document pages");
	}
	
	public String getDocumentLastRead(String documentName) {
		return retrieveText(prepareWebElementWithDynamicXpath(documentLastRead, documentName), "document last read");
	}
	
	public void selectDocumentCheckBox(String documentName) {
		clickElement(prepareWebElementWithDynamicXpath(documentCheckBox, documentName), "checkbox for " + documentName);
	}
	
	public void clickDocumentName(String documentName) {
		clickElement(prepareWebElementWithDynamicXpath(this.documentName, documentName), documentName);
	}
	
	public void clickDeclinedCancelledDocument(String documentName) {
		clickElement(prepareWebElementWithDynamicXpath(declinedCancelledDocumentName, documentName), documentName);
	}
	
	public void printPreviewDocument(String referralName) throws InterruptedException {
		clickElement(printPreview, "print preview button");
		waitForLoaderToDisappear();
		verifyViewDocument(referralName);
	}
	
	public void verifyViewDocument(String referralName) throws InterruptedException {
		switchToNewWindow();
		assertTrue(verifyTextInPageSource("type=\"application/pdf\""));
		assertTitle(referralName);
		assertFalse(getURL().toLowerCase().contains("error"));
		switchToNewWindow();
		closeAllSubTabs();
	}
	
	public void hoverOverDeclinedCancelledDocument(String documentName) {
		WebElement declinedCancelledDocumentWebElement =  prepareWebElementWithDynamicXpath(declinedCancelledDocumentName, documentName);
		scrollPageToElement(declinedCancelledDocumentWebElement);
		hoverOver(declinedCancelledDocumentWebElement);
	}
	
	public String getMarkAllReadDisabledAttribute() {
		return retrieveAttributeValue(markAllReadButton, "disabled", "mark all read button");
	}

}
