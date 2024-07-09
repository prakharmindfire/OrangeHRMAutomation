package com.qa.pages;

import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.waitForLoaderToDisappear;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.assertTrue;

public class IntakeInternalNoteNotificationPage extends TestBase {

	// internal note notification modal
	@FindBy(xpath = "//span[contains(text(),'Internal Notes')]")
	WebElement internalNotesTab;
	@FindBy(xpath = "//span[contains(text(),'Internal Notes')]//span[contains(.,'New')]")
	WebElement internalNotesTabNewTag;
	@FindBy(xpath = "//mat-dialog-container/nh-intake-internal-notes-dialog")
	WebElement internalNotesNotificationModal;
	@FindBy(xpath = "//div[@class='card tagged-internal-note']")
	List<WebElement> entriesInInternalNoteModal;
	@FindBy(xpath = "//button[contains(text(),'CLEAR NOTIFICATIONS')]")
	WebElement clearNotificationsButton;
	@FindBy(xpath = "//button[contains(text(),'CLOSE')]")
	WebElement closeButtonInInternalNotesModal;
	@FindBy(css = "p.tagged-internal-note__patient-name")
	WebElement patientNameOnNotification;
	@FindBy(css = "div.tagged-internal-note__patient-loc")
	WebElement locNameOnNotification;
	@FindBy(css="p.tagged-internal-note__note-formatted-text")
	WebElement notesOnNotification;
	@FindBy(xpath = "//div[contains(@class,'tagged-internal-note__creator')]")
	WebElement taggedByUserName;
	@FindBy(xpath = "//p[@class='tagged-internal-note__note-formatted-text']/../following-sibling::div")
	WebElement timestampOnNotification;

	// Initializing the Page Factory/Objects:
	public IntakeInternalNoteNotificationPage() {
			PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
															// be initialized with this driver
		}

	public void clickOnInternalNotesTab() {
		clickElement(internalNotesTab, "internal notes notification tab");
		waitForElementToLoad(internalNotesNotificationModal,"Internal Notes Notification Modal");
	}

	public void clickOnClearNotificationsButton() {
		clickElement(clearNotificationsButton, "clear notifications button");
		waitForLoaderToDisappear();
	}

	public void clickOnCloseButtonInInternalNotesModal() {
		clickElement(closeButtonInInternalNotesModal, "close button in internal notes modal");
		waitForLoaderToDisappear();
	}

	public void clearInternalNotesNotifications() {
		clickOnInternalNotesTab();
		if (entriesInInternalNoteModal.size() > 0) {
			for (WebElement entryInInternalNoteModal : entriesInInternalNoteModal) {
				clickElement(entryInInternalNoteModal, "entry in internal note notification modal");
			}
			clickOnClearNotificationsButton();
		}
		clickOnCloseButtonInInternalNotesModal();
	}
	
	public boolean presenceOfInternalNotesTabNewTag() {
		return isElementPresent(internalNotesTabNewTag, "new tag in internal notes notification tab");
	}
	
	public int numberOfNotificationsInInternalNoteModal() {
		return entriesInInternalNoteModal.size();
	}
	
	public void clickOnInternalNoteByIndex(int index) {
		clickElement(entriesInInternalNoteModal.get(index), "internal note no." + (index+1));
	}
	
	public boolean presenceOfInternalNotesNotificationModal() {
		return isElementPresent(internalNotesNotificationModal, "internal notes notification modal");
	}

	public void verifyTaggedInternalNoteNotificationAndGoToReferralDetails(String patientName, String locName, String note, String taggedByUsername, String sentToUsername, String timestamp){
		assertTrue(presenceOfInternalNotesTabNewTag());
		clickOnInternalNotesTab();
		assertTrue(retrieveText(patientNameOnNotification,"Patient Name of Notification").contains(patientName));
		assertTrue(retrieveText(locNameOnNotification,"LOC name on notification").contains(locName));
		assertTrue(retrieveText(notesOnNotification,"Notes on notification").contains(note));
		assertTrue(retrieveText(notesOnNotification,"Tagged User").contains(sentToUsername));
		assertTrue(retrieveText(taggedByUserName,"Tagged by user").contains(taggedByUsername));
		softAssert.assertTrue(retrieveText(timestampOnNotification,"Notification timestamp").contains(timestamp),"Timestamp check on tagged internal notes notification");
		clickAndWaitForPageLoad(patientNameOnNotification,"Referral Details");
	}

}
