package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

public class DocumentManagerPage extends TestBase {
	// Initializing the Page Factory/Objects:
	public DocumentManagerPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	@FindBy(css = "nh-intake-documents-view nh-intake-documents-list")
	WebElement unassignedDocumentListTab;
	@FindBy(css = "nh-intake-documents-view nh-intake-document-preview")
	WebElement documentPreviewTab;

	@FindBy(xpath = "/html/body/app-root/nh-intake-root/div/div[2]/nh-intake-documents-view/div/nh-intake-documents-list/button")
	WebElement UploadDocument;
	@FindBy(css = "nh-intake-documents-view nh-intake-patients-list")
	WebElement patientListPreviewTab;

	@FindBy(css = ".doc-list__content-list")
	WebElement documentList;

	@FindBy(css = ".pdf-container")
	WebElement documentPreviewInPreviewTab;

	@FindBy(xpath = "//button[contains(.,'Upload Document')]")
	WebElement uploadDocumentButton;

	@FindBy(css = "nh-intake-documents-list h3.doc-list__title span")
	WebElement unassignedDocumentListHeader;

	@FindBy(css = "nh-intake-documents-list ul.doc-list__content-list li.doc-item.doc-list__doc")
	List<WebElement> unassignedDocuments;

	@FindBy(css = "nh-intake-document-preview h3.doc-preview__doc-name")
	WebElement documentNameInPreview;

	@FindBy(css = "nh-intake-documents-list nh-help .help-icon")
	WebElement helpIcon;

	@FindBy(xpath = "//button[contains(text(),'SORT')]")
	WebElement sort;

	@FindBy(css = ".doc-preview__empty-msg")
	WebElement docPreviewEmptyMessage;
	@FindBy(css = "nh-intake-document-preview button[aria-label='edit']")
	WebElement docPreviewEditIcon;
	@FindBy(css = "nh-intake-document-preview button[aria-label='delete']")
	WebElement docPreviewTrashIcon;
	@FindBy(css = "nh-intake-document-preview button[aria-label='reply by fax']")
	WebElement docPreviewReplyByFaxIcon;
	@FindBy(css = "nh-intake-document-preview button[aria-label='download']")
	WebElement docPreviewDownloadIcon;
	@FindBy(css = "nh-intake-rename-document-dialog")
	WebElement renameModal;
	@FindBy(css = "input[name='newName']")
	WebElement newNameInRenameModal;
	@FindBy(xpath = "//button[contains(text(),'SAVE'")
	WebElement saveButtonInRenameModal;
	@FindBy(xpath = "//button[contains(@id,'patient-docs-back-to-discharge')]")
	WebElement goBackToWorkBookFromGenricDocMgr;
	@FindBy(xpath = "//div[contains(text(),'Document name must not be more than 100 characters.'')]")
	WebElement saveButtonInRenameWithMoreThan100CharTooltip;
	@FindBy(xpath = "//div[contains(text(),'Document name must be at least 8 characters.')]")
	WebElement saveButtonInRenameWithAtleast8CharTooltip;
	@FindBy(xpath = "//mat-dialog-container//button[contains(text(),'YES')]")
	WebElement docManagerYesButtonInModal;
	@FindBy(xpath = "//mat-dialog-container//button[contains(text(),'NO')]")
	WebElement docManagerNoButtonInModal;
	@FindBy(xpath = "//button[contains(@id,'docManagerLink')]")
	WebElement patSpecifcDocMgrButton;
	@FindBy(xpath = "//mat-dialog-container[contains(@id,'mat-dialog')]")
	WebElement docDeleteModal;
	@FindBy(xpath = "//button[contains(text(),' DELETE ')]")
	WebElement deleteConfirmModal;
	@FindBy(xpath = "//*[@id='delete-document']")
	WebElement deleteIcon;
	@FindBy(id = "patient-docs-back-to-assessment")
	WebElement backToBuildPage;
	@FindBy(css = "'nh-intake-send-fax'")
	WebElement faxModal;
	@FindBy(css = "h2.send-fax__header-title")
	WebElement faxModalTitle;
	@FindBy(css = "[name='orgName']")
	WebElement faxModalOrgName;
	@FindBy(css = "[name='faxNumber']")
	WebElement faxModalFaxNo;
	@FindBy(css = "label[for='sf_faxNumber']")
	WebElement faxNumberLabel;
	@FindBy(xpath = "//mat-checkbox[contains(@id,'select-row')]")
	List<WebElement> totalnoOfDocs;
	@FindBy(id = "providersSearch")
	WebElement providersSearchInput;
	@FindBy(css = "[name='attention']")
	WebElement faxModalAttention;
	@FindBy(css = "[name='comment']")
	WebElement faxModalComment;
	@FindBy(xpath = "//span[contains(.,'Include Selected Document')]")
	WebElement faxModalIncludeSelectedDocLabel;
	@FindBy(xpath = "//span[contains(.,'Include Selected Document')]//parent::label")
	WebElement faxModalIncludeSelectedDocCheckbox;
	@FindBy(xpath = "//button[contains(text(),'Send Fax')]")
	WebElement faxModalSendFaxButton;
	@FindBy(xpath = "//div[contains(@class,'selected')]")
	WebElement tabSelected;
	@FindBy(css = ".doc-item__doc-name")
	List<WebElement> unassignedDocumentName;
	
	@FindBy(xpath="/div[@id='toast-container']/div/div")
	WebElement docUploadSuccessToast;
	
	@FindBy(xpath="//li[1]/div[1]/span[1]")
	WebElement firstDocNameInList;
	
	@FindBy(xpath="//input[@accept='.pdf']")
	WebElement uploadInput;
	
	@FindBy(xpath="//ul[@class='doc-list__content-list ng-star-inserted']//li/div/span[1]")
	List<WebElement>  docNameList;

	String name = "//span[contains(@class,'doc-item__doc-name')][contains(text(),'xxxxx')]//ancestor::li[contains(@class,'doc-item')]";

	@FindBy(css = ".doc-item__page-count")
	List<WebElement> unassignedDocumentPage;
	@FindBy(xpath = "//p[contains(@class,'doc-item__footer')]//span[2]")
	List<WebElement> unassignedDocumentFaxNo;
	@FindBy(xpath = "//p[contains(@class,'doc-item__footer')]//span[1]")
	WebElement unassignedDocumentTimeStamp;

	@FindBy(xpath = "//nh-intake-patients-list//button[contains(.,'CREATE NEW REFERRAL')]")
	WebElement createNewReferralButton;
	@FindBy(xpath = "//div[contains(text(),'Unassigned')]")
	WebElement unassignedTab;
	@FindBy(xpath = "//input[@type='checkbox' and @id='select-row-0-input']")
	WebElement selectFirstDocumentCheckbox;
	@FindBy(xpath = "//*[contains(@id,'document-row')]")
	WebElement selectFirstDocumentCheckboxAssigned;
	
	@FindBy(xpath = "//*[contains(@id,'document-row')]")
	List<WebElement> selectDocumentsAssigned;
	
	
	@FindBy(xpath = "//nh-docmanager-actions/div/button[2]")
	WebElement docRenameIcon;
	@FindBy(xpath = "//*[@id='assign-document']")
	WebElement assignIcon;
	@FindBy(xpath = "//*[contains(@id,'new-document-name') or @id='typeahead-control']")
	WebElement renameTextField;
	@FindBy(xpath = "//*[contains(text(),'SAVE')]")
	WebElement saveIconDocRename;
	@FindBy(xpath = "//mat-table/mat-row[1]/mat-cell/div[1]")
	WebElement newDocNameAfterRenaming;
	@FindBy(xpath = "//div[contains(text(),'Assigned')]")
	WebElement assignedTab;
	@FindBy(xpath = "//mat-dialog-container[contains(@id,'mat-dialog')]")
	WebElement docAssignModal;
	@FindBy(xpath = "//button[contains(@id,'confirm-action-btn')]")
	WebElement confirmDocAssign;
	
	
	
	
	@FindBy(css = ".new-referral.row .new-referral__body")
	WebElement addNewReferralBody;
	@FindBy(css = "nh-intake-patients-list input#patient-query")
	WebElement searchForPatientInput;

	@FindBy(css = "body .mat-select-panel")
	WebElement multipleLOCDropdown;
	@FindBy(css = ".mat-option-text")
	List<WebElement> multipleLOCValuesForReferral;
	@FindBy(css = ".cdk-overlay-backdrop")
	WebElement backdropOfMultipleLOCropdown;
	@FindBy(xpath = "//button[contains(text(),'Add New LOC')]")
	WebElement addNewLOCButton;

	@FindBy(css = "mat-dialog-container")
	WebElement addLOCModalTab;
	@FindBy(css = "nh-intake-new-referral-dialog")
	WebElement addLOCModal;
	@FindBy(css = "h2.new-referral__header-title")
	WebElement addLOCModalHeader;
	@FindBy(css = "nh-intake-new-referral-dialog .new-referral__document")
	WebElement addLOCModalDocPreview;
	@FindBy(xpath = "//label[@id='add-another-loc']/input")
	WebElement addLOCModalCheckboxAnotherLOC;

	@FindBy(css = "select[name='locSelect'] option")
	List<WebElement> addLOCModalLOCNames;
	@FindBy(css = "body .mat-select-panel .mat-option")
	WebElement locsInMultipleSelectDropdown;
	@FindBy(css = "body .mat-select-panel .mat-option mat-pseudo-checkbox")
	WebElement locCheckboxInMultipleSelectDropdown;
	@FindBy(css = ".loc-previously-added")
	WebElement locsPreviouslyAdded;
	@FindBy(css = "[formcontrolname='dob']")
	WebElement DOB;
	@FindBy(css = "[formcontrolname='admitDate']")
	WebElement admitDate;
	@FindBy(css = "[formcontrolname='estDischargeDate']")
	WebElement readyDate;

	@FindBy(id = "add-another-loc")
	WebElement addAnotherLOCLabel;

	@FindBy(xpath = "//button[contains(text(),'Save and Attach')]")
	WebElement saveAndAttachButton;

	@FindBy(css = ".patient-list__content")
	WebElement resultList;

	@FindBy(css = ".patient-list__content li.patient-item")
	WebElement resultsInSearchBar;

	@FindBy(css = ".patient-list__content li.patient-item .patient-item__name")
	WebElement resultsNameInSearchBar;

	@FindBy(xpath = "//button[contains(text(),'ATTACH')]")
	WebElement attachButton;

	@FindBy(id = "rsi_locSelect")
	WebElement requestedLOCDropdown;

	String referralInSerachResults = "//nh-intake-patients-list//li[contains(.,'xxxxx')]";
	String addLOCButtonForReferral = "//h3[@class='patient-item__name'][contains(text(),'xxxxx')]//parent::li//button[contains(@class,'add-loc-btn')]";
	String singleLOCLabel = "//h3[@class='patient-item__name'][contains(text(),'xxxxx')]//parent::li//span[contains(text(),'LOC')]//parent::div";
	String multiplePleaseSelectButtonForReferral = "//h3[@class='patient-item__name'][contains(text(),'xxxxx')]//parent::li//mat-select[@placeholder='Multiple, please select']";
	String multiplePleaseSelectPlaceholder = "//h3[@class='patient-item__name'][contains(text(),'xxxxx')]//parent::li//mat-select[@placeholder='Multiple, please select']//span[contains(@class,'mat-select-placeholder')]";
	String multipleLOCLabelValue = "//h3[@class='patient-item__name'][contains(text(),'xxxxx')]//parent::li//mat-select//span[contains(@class,'mat-select-value-text')]//span";
	String providerNameInAutoComplete = "//div[contains(@class,'mat-autocomplete-visible')]//mat-option[contains(.,'xxxxx')]";
	String keyService = "//label[contains(.,'xxxx')]";
	String unassignedDocumentNameInList = "//span[contains(@class,'doc-item__doc-name')][contains(text(),'xxxxx')]//ancestor::li[contains(@class,'doc-item')]";

	public boolean isUnassignedDocumentsPresent() {
		return isElementPresent(unassignedDocuments.get(0), "First unassignedDocuments");
	}

	public void clickUnassignedDocuments() {
		clickAndWaitForPageLoad(unassignedDocuments.get(0), "First unassignedDocuments");
	}

	public Boolean isMultiplePleaseSelectButtonForReferralPresent(String referralName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(multiplePleaseSelectButtonForReferral, referralName),
				"multiplePleaseSelectButtonForReferral");
	}

	public boolean isSaveAndAttachEnabled() {
		return isEnabled(saveAndAttachButton, "saveAndAttachButton");
	}

	public boolean isHelpIconPresent() {
		return isElementPresent(helpIcon, "helpIcon");
	}

	public boolean isDocPreviewEditIconEnabled() {
		return isEnabled(docPreviewEditIcon, "docPreviewEditIcon");
	}

	public String multipleLOCValuesForReferral1Text() {
		return retrieveText(multipleLOCValuesForReferral.get(0), "multipleLOCValuesForReferral1");
	}

	public String multipleLOCValuesForReferral2Text() {
		return retrieveText(multipleLOCValuesForReferral.get(1), "multipleLOCValuesForReferral2");
	}

	public void clickAddNewLOCButton() {
		clickAndWaitForPageLoad(addNewLOCButton, "addNewLOCButton");
	}

	public String addLOCModalHeaderText() {
		return retrieveText(addLOCModalHeader, "addLOCModalHeader");
	}

	public List<String> getAllAddLOCModalLOCNames() {
		List<String> addLOCModalLOCNames1 = new ArrayList<>();
		for (int i = 0; i < addLOCModalLOCNames.size(); i++) {
			addLOCModalLOCNames1.add(retrieveText(addLOCModalLOCNames.get(i), "addLOCModalLOCNames" + i));
		}
		return addLOCModalLOCNames1;
	}

	public void clickDocPreviewEditIcon() {
		clickAndWaitForPageLoad(docPreviewEditIcon, "docPreviewEditIcon");
		waitForLoaderToDisappear();
	}

	public String unassignedDocumentsCSS(String CSS) {
		return retrieveCSSValue(unassignedDocuments.get(0), CSS, "unassignedDocuments");
	}

	public String faxNumberLabelText() {
		return retrieveText(faxNumberLabel, "faxNumberLabel");
	}

	public String faxModalAttentionAttribute(String attr) {
		return retrieveAttributeValue(faxModalAttention, attr, "faxModalAttention");
	}

	public String faxModalIncludeSelectedDocLabelText() {
		return retrieveText(faxModalIncludeSelectedDocLabel, "faxModalIncludeSelectedDocLabel");
	}

	public boolean isfaxModalSendFaxButtonEnabled() {
		return isEnabled(faxModalSendFaxButton, "faxModalSendFaxButton");
	}

	public boolean isFaxModalIncludeSelectedDocCheckboxPresent() {
		return isElementPresent(faxModalIncludeSelectedDocCheckbox, "faxModalIncludeSelectedDocCheckbox");
	}

	public void setFaxModalFaxNo(String value) {
		clearAndInput(faxModalFaxNo, "faxModalFaxNo", value);
	}

	public void setFaxModalAttention(String value) {
		clearAndInput(faxModalAttention, "faxModalAttention", value);
	}

	public void setFaxModalComment(String value) {
		clearAndInput(faxModalComment, "faxModalComment", value);
	}

	public void setFaxModalOrgName(String name) {
		clearAndInput(faxModalOrgName, "faxModalOrgName", name);
	}
	public void clickUpload() {
		clickElement(UploadDocument, "UploadDocument");
	}
	
	public boolean isDocPreviewTrashIconEnabled() {
		return isEnabled(docPreviewTrashIcon, "docPreviewTrashIcon");
	}

	public void unassignedDocumentFaxNoClick() {
		clickAndWaitForPageLoad(unassignedDocumentFaxNo.get(0), "unassignedDocumentFaxNo");
	}

	public String unassignedDocumentNameInListText(String name) {
		return retrieveText(prepareWebElementWithDynamicXpath(unassignedDocumentNameInList, name),
				"unassignedDocumentNameInList");
	}

	public String faxModalTitleText() {
		return retrieveText(faxModalTitle, "faxModalTitle");
	}

	public boolean isFaxModalOrgNamePresent() {
		return isElementPresent(faxModalOrgName, "faxModalOrgName");
	}

	public boolean isFaxModalFaxNoPresent() {
		return isElementPresent(faxModalFaxNo, "faxModalFaxNo");
	}

	public boolean isFaxModalAttentionPresent() {
		return isElementPresent(faxModalAttention, "faxModalOrgName");
	}

	public boolean isFaxModalCommentPresent() {
		return isElementPresent(faxModalComment, "faxModalComment");
	}

	public void docManagerYesButtonInModalClick() {
		clickAndWaitForPageLoad(docManagerYesButtonInModal, "docManagerYesButtonInModal");
		waitForPageToLoad();
	}

	public void docPreviewTrashIconClick() {
		clickAndWaitForPageLoad(docPreviewTrashIcon, "docPreviewTrashIcon");
		waitForPageToLoad();
	}

	public boolean isDocPreviewReplyByFaxIconPresent() {
		return isElementPresent(docPreviewReplyByFaxIcon, "docPreviewReplyByFaxIcon");
	}

	public boolean isDocPreviewReplyByFaxIconEnabled() {
		return isEnabled(docPreviewReplyByFaxIcon, "docPreviewReplyByFaxIcon");
	}

	public boolean isFaxModalPresent() {
		return isElementPresent(faxModal, "faxModal");
	}

	public void docPreviewReplyByFaxIconClick() {
		clickAndWaitForPageLoad(docPreviewReplyByFaxIcon, "docPreviewReplyByFaxIcon");
	}

	public void clickUnassignedDocumentNameInList(String name) {
		clickAndWaitForPageLoad(
				prepareWebElementWithDynamicXpath(unassignedDocumentNameInList, "unassignedDocumentNameInList"),
				"unassignedDocumentNameInList");
	}

	public boolean isSearchForPatientInputEnabled() {
		return isEnabled(searchForPatientInput, "searchForPatientInpu");
	}

	public String unassignedDocumentPageText() {
		return retrieveText(unassignedDocumentPage.get(0), "unassignedDocumentPage");
	}

	public void docManagerNoButtonInModalClick() {
		clickAndWaitForPageLoad(docManagerNoButtonInModal, "docManagerNoButtonInModal");
	}

	public boolean isUnassignedDocumentNameInListPresent(String name) {
		return isElementPresent(prepareWebElementWithDynamicXpath(unassignedDocumentNameInList, name),
				"unassignedDocumentNameInList");
	}

	public boolean isRenameModalPresent() {
		return isElementPresent(renameModal, "renameModal");
	}

	public String documentNameInPreviewText() {
		return retrieveText(documentNameInPreview, "documentNameInPreview");
	}

	public void inputNewNameInRenameModal(String value) {
		clearAndInput(newNameInRenameModal, "newNameInRenameModal", value);
	}

	public String unassignedDocumentNameText() {
		return retrieveText(unassignedDocumentName.get(0), "unassignedDocumentName");
	}

	public void clickSaveButtonInRenameModal() {
		clickAndWaitForPageLoad(saveButtonInRenameModal, "saveButtonInRenameModal");
		waitForLoaderToDisappear();
	}

	public String getSaveButtonInRenameModalAttribute(String attr) {
		return retrieveAttributeValue(saveButtonInRenameModal, attr, "saveButtonInRenameModal");
	}

	public void UploadDoc(String fileName) throws AWTException, InterruptedException {
		// UploadDocument.sendKeys("C:\\Users\\mohammad.chaudry\\Downloads\\TestManual.pdf");
		Thread.sleep(5000);
		// creating object of Robot class
		//System.setProperty("java.awt.headless", "false");
		Robot rb = new Robot();

		// copying File path to Clipboard
		StringSelection str = new StringSelection(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\testdata\\FilesToUpload\\" + fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		waitForLoaderToDisappear();
	}
	
	public void renameDocumentFromGenericDocMgrSmokeTest(String renameTextInitial) {

		// Select the very first document in unassigned section for renaming
		reportLog("Selecting the very first document in unassigned section for renaming");
		scrollPageToElementAndClick(selectFirstDocumentCheckbox,
				"Select the very first document in unassigned section");
		reportLog("Clicked on first Document Check Box in Unassigned Section");

		// Click on the Rename icon for the selected document
		reportLog("Clicking on the Rename icon for the selected document");
		clickElement(docRenameIcon, "Click on document rename icon");

		// Enter the rename text in the field
		reportLog("Entering the rename text in the field");
		String expectedValue = renameTextInitial + Math.floor(Math.random() * 90 + 10);
		reportLog("Text going to be Entered is " +expectedValue);
		input(renameTextField, "New doc Rename text being entered",
				expectedValue);

		// Click on save icon
		reportLog("Clicking on the Save Icon");
		clickElement(saveIconDocRename, "Save Icon in document renaming modal");
		reportLog("Renamed the Document using free text option");

		// Check the new document name
		reportLog("Waiting for the page to load back ");
		waitForPageToLoad();
		// Scroll Up
		reportLog("Scrolling up the page");
		scrollToTop();
		reportLog("Scrolled up");
		reportLog("Assertion for renamed document begins");

		Assert.assertTrue(retrieveText(newDocNameAfterRenaming, "New Document name post renaming")
				.contains(expectedValue));

		// Check the renaming date appended
		reportLog("Printing the new name and the date appended for the document");
		reportLog("Name of the New Renamed Document = "
				+ retrieveText(newDocNameAfterRenaming, "New Document name post renaming").split("-")[0]);
		reportLog("Date Appended to the New Renamed Document = "
				+ retrieveText(newDocNameAfterRenaming, "New Document name post renaming").split("-")[1]);
	}

	public void renameDocumentFromAssignedtab(String renameTextInitial) {

		// Select the very first document in unassigned section for renaming
		reportLog("Selecting the very first document in assigned section for renaming");
		clickElement(selectFirstDocumentCheckboxAssigned,
				"Select the very first document in assigned section");
		reportLog("Clicked on first Document Check Box in assigned Section");

		// Click on the Rename icon for the selected document
		reportLog("Clicking on the Rename icon for the selected document");
		clickElement(docRenameIcon, "Click on document rename icon");

		// Enter the rename text in the field
		reportLog("Entering the rename text in the field");
		String expectedValue = renameTextInitial + Math.floor(Math.random() * 90 + 10);
		reportLog("Text going to be Entered is " +expectedValue);
		input(renameTextField, "New doc Rename text being entered",
				expectedValue);

		// Click on save icon
		reportLog("Clicking on the Save Icon");
		clickElement(saveIconDocRename, "Save Icon in document renaming modal");
		reportLog("Renamed the Document using free text option");

		// Check the new document name
		reportLog("Waiting for the page to load back ");
		waitForPageToLoad();
		// Scroll Up
		reportLog("Scrolling up the page");
		scrollToTop();
		reportLog("Scrolled up");
		reportLog("Assertion for renamed document begins");

		Assert.assertTrue(retrieveText(newDocNameAfterRenaming, "New Document name post renaming")
				.contains(expectedValue));

		// Check the renaming date appended
		reportLog("Printing the new name and the date appended for the document");
		reportLog("Name of the New Renamed Document = "
				+ retrieveText(newDocNameAfterRenaming, "New Document name post renaming").split("-")[0]);
		reportLog("Date Appended to the New Renamed Document = "
				+ retrieveText(newDocNameAfterRenaming, "New Document name post renaming").split("-")[1]);
	}

	public void  assignADocumentFromDocMgrSmokeTest() {

		// navigate to Assigned docs Tab
		clickElement(assignedTab, "Assigned Documents");
		reportLog("Clicked on Assigned Documents tab");
		reportLog("Checking total no of docs now on Assigned Documents tab");
		int noOfDocs = selectDocumentsAssigned.size();
		reportLog("Total no of docs now on Assigned Documents tab ( before Assigning ) : "+noOfDocs);		 
		
		//Navigate back to the Unassigned Documents tab
		clickElement(unassignedTab, "UnAssigned Documents");
		reportLog("Clicked on UnAssigned Documents tab");
		
		// Select the very first document in unassigned section
		reportLog("Selecting the very first document in unassigned section");
		scrollPageToElementAndClick(selectFirstDocumentCheckbox,
				"Very first document in unassigned section");
		reportLog("Clicked on first Document Check Box in Unassigned Section");

		// Click on the Assign icon for the selected document
		reportLog("Clicking on the Assign icon for the selected document");
		clickElement(assignIcon, "Document assign icon");
		confirmOnAssigningOfDocument();
		waitForLoaderToDisappear();
		waitForPageToLoad();
		
		//Navigate to the Assigned docs Tab  
		clickElement(assignedTab, "Assigned Documents");
		reportLog("Clicked on Assigned Documents tab again");
		reportLog("Checking total no of docs on Assigned Documents tab after assigning ");
		
		//Check the total number of documents now
		int noOfDocsAfter = selectDocumentsAssigned.size();
		reportLog("Total Number of docs - Before Assigning = "+noOfDocs);
		reportLog("Total Number of docs - After Assigning = "+noOfDocsAfter);
		Assert.assertEquals(noOfDocsAfter, noOfDocs+1);
		
		}

	public void  unassignADocumentFromDocMgr() {

		// Navigate to UnAssigned docs Tab
		clickElement(unassignedTab, "UnAssigned Documents");
		reportLog("Clicked on UnAssigned Documents tab");
		reportLog("Checking total no of docs now on UnAssigned Documents tab");
		int noOfDocs = totalnoOfDocs.size();
		reportLog("Total no of docs now on UnAssigned Documents tab ( before UnAssigning ) : "+noOfDocs);		 
		
		//Navigate back to the Assigned Documents tab
		clickElement(assignedTab, "Assigned Documents Tab");
		reportLog("Clicked on Assigned Documents tab");
		
		// Select the very first document in assigned section
		reportLog("Selecting the very first document in assigned section");
		scrollPageToElementAndClick(selectFirstDocumentCheckboxAssigned,
				"Very first document in assigned section");
		reportLog("Clicked on first Document in assigned Section");

		// Click on the Assign icon for the selected document
		reportLog("Clicking on the Assign icon for the selected document");
		clickElement(assignIcon, "Document assign icon");
		waitForLoaderToDisappear();
		waitForPageToLoad();
		
		//Navigate to the UnAssigned docs Tab  
		clickElement(unassignedTab, "Un Assigned Documents");
		reportLog("Clicked on Un Assigned Documents tab again");
		reportLog("Checking total no of docs on Un Assigned Documents tab after Un assigning ");
		
		//Check the total number of documents now
		int noOfDocsAfter = totalnoOfDocs.size();
		reportLog("Total Number of docs - Before UnAssigning = "+noOfDocs);
		reportLog("Total Number of docs - After UnAssigning = "+noOfDocsAfter);
		Assert.assertEquals(noOfDocsAfter, noOfDocs+1);
		
		}

	public void  confirmOnAssigningOfDocument() {
		boolean isModaldisplayed = docAssignModal.isDisplayed();
		// Check if the modal is displayed
		reportLog("Checking if the document assign modal is displayed");
		
		if(isModaldisplayed) {
			reportLog("doc Assign modal is displayed");
			clickElement(confirmDocAssign, "Confirm Document assign button ");
		}else {
			reportLog("doc Assign modal is not displayed");
		}
	}

	
	public void checkRenameAndAssignADocumentFromUnAssignedTab(String renameTextInitial) {

		if(unassignedTab.getText().equalsIgnoreCase("Unassigned")){
			reportLog("User is on Unassigned doc mgr tab");
			reportLog("Renaming a doc - Unassigned doc mgr tab");
			renameDocumentFromGenericDocMgrSmokeTest(renameTextInitial);
			assignADocumentFromDocMgrSmokeTest();
			
		}else if(unassignedTab.getText().equalsIgnoreCase("Assigned")){
			reportLog("User is on assigned doc mgr tab");
			clickElement(unassignedTab, "UnAssigned tab on patient specific Document manager Page");
			reportLog("User is moved back to Unassigned doc mgr tab");
			reportLog("Renaming a doc - Unassigned doc mgr tab");
			renameDocumentFromGenericDocMgrSmokeTest(renameTextInitial);
			assignADocumentFromDocMgrSmokeTest();
		}
			
	}

	public void checkRenameAndUnAssigningDocumentFromAssignedTab(String renameTextInitial) {

		if(assignedTab.getText().equalsIgnoreCase("assigned")){
			reportLog("User is on assigned doc mgr tab");
			reportLog("Renaming a doc - Unassigned doc mgr tab");
			renameDocumentFromAssignedtab(renameTextInitial);
			unassignADocumentFromDocMgr();
			
		}else {
			reportLog("Tab Mismatch - User is not on Un Assigned tab");
			clickElement(assignedTab, "Assigned tab on patient specific Document manager Page");
			reportLog("User is moved back to assigned doc mgr tab");
			reportLog("Renaming a doc - assigned doc mgr tab");
			renameDocumentFromAssignedtab(renameTextInitial);
			unassignADocumentFromDocMgr();
		}
			
	}

	public void deleteADocument() {

		String tab = verifyTheTabNameSelectedByDefault();
		
		if(tab.equalsIgnoreCase("Unassigned")) {
			reportLog("Going to delete a unassigned document");
			reportLog("Checking total no of documents in unassigned tab");
			int noOfDocs = selectDocumentsAssigned.size();
			reportLog("Total no of documents in unassigned tab (before deletion) : "+noOfDocs);
			reportLog("Proceeding for deletion");
			clickElement(selectFirstDocumentCheckbox, "First unassigned document");
			reportLog("Selected an unassigned document");
			clickElement(deleteIcon, "Delete Icon");
			reportLog("Clicked on delete icon");
			reportLog("Checking if deletion confirmation modal is dsplayed ? ");
			boolean isModaldisplayed = docDeleteModal.isDisplayed();
			if(isModaldisplayed) {
				reportLog("Deletion confirmation Modal is displayed");
				reportLog("Confirming deletion");
				clickElement(deleteConfirmModal, "Confirm Deletion Button in modal");
				}else {
					reportLog("No deletion confirmation modal is dislayed");
				}

			waitForLoaderToDisappear();
			waitForPageToLoad();
			reportLog("Checking total no of documents in unassigned tab ( post deletion )");
			int noOfDocsPost = selectDocumentsAssigned.size();
			reportLog("Total no of documents in unassigned tab (after deletion) : "+noOfDocsPost);
			Assert.assertEquals(noOfDocsPost, noOfDocs-1);
			reportLog("Document Deletion is successfull in unassigned tab ");
		}else if(tab.equalsIgnoreCase("Assigned")) {
			reportLog("Going to delete a assigned document");
			reportLog("Checking total no of documents in assigned tab");
			int noOfDocsAssignedtab= totalnoOfDocs.size(); 
			reportLog("Total no of documents in assigned tab (before deletion) : "+noOfDocsAssignedtab);
			reportLog("Proceeding for deletion");
			clickElement(selectFirstDocumentCheckboxAssigned, "First assigned document");
			reportLog("Selected an assigned document");
			clickElement(deleteIcon, "Delete Icon");
			reportLog("Clicked on delete icon");
			reportLog("Checking if deletion confirmation modal is dsplayed ? ");
			boolean isModaldisplayed = docDeleteModal.isDisplayed();
			if(isModaldisplayed) {
				reportLog("Deletion confirmation Modal is displayed");
				reportLog("Confirming deletion");
				clickElement(deleteConfirmModal, "Confirm Deletion Button in modal");
				}else {
					reportLog("No deletion confirmation modal is dislayed");
				}
			waitForLoaderToDisappear();
			waitForPageToLoad();
			reportLog("Checking total no of documents in assigned tab ( post deletion )");
			int noOfDocsPostAssignedTab = totalnoOfDocs.size();
			reportLog("Total no of documents in assigned tab (after deletion) : "+noOfDocsPostAssignedTab);
			Assert.assertEquals(noOfDocsPostAssignedTab, noOfDocsAssignedtab-1);
			reportLog("Document Deletion is successfull in assigned tab ");

		}
		
			
	}

	public void deleteAAssignedDocument() {
		String tab = verifyTheTabNameSelectedByDefault();
		reportLog("Current tab is : "+tab);
		if(tab.equalsIgnoreCase("Assigned")) {
			reportLog("User is on Assigned tab ");
			deleteADocument();		
		}else if(tab.equalsIgnoreCase("Unassigned")) {
			reportLog("User is on un-Assigned tab ");
			reportLog("clicking on Assigned tab ");
			clickElement(assignedTab, "Assigned document Tab");
			reportLog("Moving back to Assigned tab ");
			deleteADocument();
		}
		
	}
	
	public void deleteunAAssignedDocument() {
		String tab = verifyTheTabNameSelectedByDefault();
		reportLog("Current tab is : "+tab);
		if(tab.equalsIgnoreCase("Unassigned")) {
			reportLog("User is on Un-Assigned tab ");
			deleteADocument();	
		}else if(tab.equalsIgnoreCase("Assigned")) {
			reportLog("User is on Assigned tab ");
			reportLog("clicking on Un-Assigned tab ");
			clickElement(unassignedTab, "Un-Assigned document Tab");
			reportLog("Moving back to Un-Assigned tab ");
			deleteADocument();	
		}
			
	}
	
	public void navigateBackToBuildPage() {
		reportLog("Navigating back to the Build Page of the patient");
		clickElement(backToBuildPage, "Navigate Back to Build Page Button");
		reportLog("Waiting for loader to disappearand page to load");
		waitForLoaderToDisappear();
		waitForPageToLoad();
	}

	public void attachDocFromBuildpage() {
		reportLog("Checking whether user is on Build Page or not");
		String currURL = getURL();
		reportLog("Url of current page : "+currURL);
		assertTrue(currURL.contains("build"));
		reportLog("Confirmed - User is on Build Page now");
		
		reportLog("Selecting the document to share ");
		
		
		reportLog("Navigating back to the Build Page of the patient");
		clickElement(backToBuildPage, "Navigate Back to Build Page Button");
		reportLog("Waiting for loader to disappearand page to load");
		waitForLoaderToDisappear();
		waitForPageToLoad();
	}

	public void checkWarningModalWhileUnAssigningSharedDoc(String renameTextInitial) {
		assignADocumentFromDocMgrSmokeTest();
		navigateBackToBuildPage();
		attachDocFromBuildpage();
		clickPatspecficDocMgrButton();
		
		/*
		 	* Assign a doc 
		 	* Go to build page
		 	* Attach the doc 
		 	* Open Doc Mgr
		 * Go to Assigned Doc Mgr
		 * Unassign the doc
		 * Check if modal is visible
		 * click on Unassign it
		 * check whether visible in UnAssign tab 
		 */

		
		if(assignedTab.getText().equalsIgnoreCase("assigned")){
			reportLog("User is on assigned doc mgr tab");
			reportLog("Renaming a doc - Unassigned doc mgr tab");
			renameDocumentFromAssignedtab(renameTextInitial);
			unassignADocumentFromDocMgr();
			
		}else {
			reportLog("Tab Mismatch - User is not on Assigned tab");
		}
			
	}

	public void uploadWithSendKeys(String filepath)throws Exception {
		
		//((RemoteWebElement) uploadInput ).setFileDetector(new LocalFileDetector());
		uploadInput.sendKeys(filepath);
		waitForLoaderToDisappear();
	}

	public void selectProviderInManualReferral(String providerName) {
		input(providersSearchInput, "provider search textbox", providerName);
		clickElement(prepareWebElementWithDynamicXpath(providerNameInAutoComplete, providerName),
				"provider name in auto complete");
		waitForLoaderToDisappear();
	}

	public void setSearchForPatientInput(String value) {
		clearAndInput(searchForPatientInput, "First Name", value);

	}

	public void addManualReferralWithDocMgr(String LOC, String provider, String keyService) {
		selectValueByVisibleText(requestedLOCDropdown, LOC, "loc dropdown");
		assertFalse(isSaveAndAttachEnabled());

		selectProviderInManualReferral(provider);
		assertTrue(isSaveAndAttachEnabled());
		clickAndWaitForPageLoad(prepareWebElementWithDynamicXpath(keyService, keyService), keyService);
		assertTrue(isSaveAndAttachEnabled());

	}

	public void addLOCBatchWithDocument(String LOC, String provider, String keyService, String fileName,
			String firstName, String referralName) throws AWTException, InterruptedException {

		if (!isUnassignedDocumentsPresent()) {
			UploadDoc(fileName);

		}
		clickUnassignedDocuments();
		clearAndInput(searchForPatientInput, "First Name", firstName);
		clickAndWaitForPageLoad(prepareWebElementWithDynamicXpath(addLOCButtonForReferral, referralName),
				"Referral Name");
		addManualReferralWithDocMgr(LOC, provider, keyService);
		clickAndWaitForPageLoad(saveAndAttachButton, "save and attach button");
		waitForLoaderToDisappear();

	}

	

	public void clicksaveAndAttachButton() {
		clickAndWaitForPageLoad(saveAndAttachButton, "save and attach button");
		waitForLoaderToDisappear();

	}
	
	//Navigate to Workbook Page from Generic Document Manger page 
	public void clickOnWorkBookButtonOnHeader() {
		reportLog("Clicking on workbook button from generic document manager page");
		clickElement(goBackToWorkBookFromGenricDocMgr, "Workbook Button on generic Document manager Page");
		waitForLoaderToDisappear();
		waitForPageToLoad();
	}
	
	//Navigate to Patient Specific Document Manager page  
	public void clickPatspecficDocMgrButton() {
		reportLog("Clicking on Doc Mgr button from Build page");
		clickElement(patSpecifcDocMgrButton, "Patient Specifc Doc mgr button from build page");
		waitForLoaderToDisappear();
		waitForPageToLoad();
	}
	
	public String verifyTheTabNameSelectedByDefault() {
		reportLog("By default user landed on Unassigned Docs Section");
		
		String tabNameSelected = retrieveText(tabSelected, "Doc Renaming Tab selected");
		reportLog("Tab Name Selected : "+tabNameSelected);

		if(tabNameSelected.equalsIgnoreCase("Assigned")) {
			reportLog("User is on Assigned Documents tab");
		}else if(tabNameSelected.equalsIgnoreCase("Unassigned")) {
			reportLog("User is on Unassigned Documents tab");
		}
		return tabNameSelected;
			
	}
	
	public String getSuccessfulToastMessagetext() {
		
	 return	retrieveText(docUploadSuccessToast, "Successful Toast meassage after upload");
		
	}
	
	public String getDocNameFromList(int index) {
		
		return retrieveText(docNameList.get(index), "Selected Doc name from List");
	}
}
