package com.qa.pages;

import static com.qa.base.TestBase.getdriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.assertTrue;

public class IntakeReferralInternalNoteTabPage extends TestBase {

	// locators
	@FindBy(xpath = "//button[@angularticsaction='InternalNotesTabActivate']")
	WebElement referralDetailsInternalNoteTab;
	@FindBy(xpath = "//button[@angularticsaction='OpenNotesModal']")
	WebElement addNotesActionButton;
	@FindBy(xpath = "//textarea[@name='newNote']")
	WebElement notesTextArea;
	@FindBy(xpath = "//button[contains(.,'Save')]")
	WebElement internalNotesSaveButton;
	@FindBy(xpath = "//button[contains(.,'Close')]")
	WebElement internalNotesCloseButton;
	@FindBy(xpath = "//div[@id='patient-notes']//span[@class='pull-left']")
	List<WebElement> internalNoteAddedByList;
	@FindBy(xpath = "//div[@id='patient-notes']//li//strong")
	List<WebElement> internalNoteContentList;
	@FindBy(xpath = "//ul[@class='list-unstyled']//span[@class='pull-right']")
	List<WebElement> internalNoteTimestampList;
	@FindBy(xpath = "//mention-list/ul")
	WebElement mentionListContainer;
	@FindBy(css = "#patient-notes > ul > li > p")
	WebElement notesSentTo;
	@FindBy(xpath = "(//nh-intake-patient-detail-notes//span)[1]")
	WebElement notesCreatedBy;
	@FindBy(xpath = "(//nh-intake-patient-detail-notes//span)[2]")
	WebElement notesTimestamp;
	
	//dynamic locators
	String providerCheckboxInInternalNotesModal = "//label[contains(.,'xxxxx')]";
	String userNameInMentionList = "//mention-list//a[contains(.,'xxxxx')]";

	// Initializing the Page Factory/Objects:
	public IntakeReferralInternalNoteTabPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
														// be initialized with this driver
	}

	public void clickOnAddNoteButton() {
		clickElement(addNotesActionButton, "add notes button");
		waitForLoaderToDisappear();
	}

	public void enterNote(String note) {
		input(notesTextArea, "internal note text area", note);
	}

	public void clickOnInternalNotesSaveButton() {
		clickElement(internalNotesSaveButton, "internal notes save button");
		waitForLoaderToDisappear();
	}
	
	public void clickOnProviderCheckboxInInternalNotesModal(String providerName) {
		clickElement(prepareWebElementWithDynamicXpath(providerCheckboxInInternalNotesModal, providerName), "cehckbox for provider: " + providerName);
	}

	public String addInternalNote(String note) {
		clickOnAddNoteButton();
		enterNote(note);
		clickOnInternalNotesSaveButton();
		return note;
	}
	
	public String addInternalNote(String note, List<String> providers) {
		clickOnAddNoteButton();
		clickOnProviderCheckboxInInternalNotesModal("Uncheck All");
		for (String providerName : providers) {
			clickOnProviderCheckboxInInternalNotesModal(providerName);
		}
		enterNote(note);
		clickOnInternalNotesSaveButton();
		return note;
	}

	public ArrayList<String> getInternalNoteAddedByList() {

		ArrayList<String> internalNoteAddedByList = new ArrayList<String>();;
		for (WebElement internalNoteAddedBy : this.internalNoteAddedByList) {
			internalNoteAddedByList.add(retrieveText(internalNoteAddedBy, "message sent by"));
		}
		return internalNoteAddedByList;

	}

	public ArrayList<String> getInternalNoteContentList() {

		ArrayList<String> internalNoteContentList = new ArrayList<String>();;
		for (WebElement internalNoteContent : this.internalNoteContentList) {
			internalNoteContentList.add(retrieveText(internalNoteContent, "message sent by"));
		}
		return internalNoteContentList;

	}

	public ArrayList<String> getInternalNoteTimestampList() {

		ArrayList<String> internalNoteTimestampList = new ArrayList<String>();;
		for (WebElement internalNoteTimestamp : this.internalNoteTimestampList) {
			internalNoteTimestampList.add(retrieveText(internalNoteTimestamp, "message sent by"));
		}
		return internalNoteTimestampList;

	}

	public void mentionTagUserInInternalNotes(String userName){
		clickOnAddNoteButton();
		enterNote("@"+userName);
		waitForElementToLoad(mentionListContainer,"Mention List Container");
		clickElement(prepareWebElementWithDynamicXpath(userNameInMentionList,userName),"CaseManager username in Mention List");
	}

	public void addTaggedInternalNotes(String userName, String notes){
		mentionTagUserInInternalNotes(userName);
		enterNote(notes);
		clickOnInternalNotesSaveButton();
	}

	public String verifyTheAddedInternalNote(String fromUser,String toUser,String notes){
		assertTrue(retrieveText(notesCreatedBy,"Notes created by username").contains(fromUser));
		assertTrue(retrieveText(notesSentTo,"Notes sent to").contains(toUser));
		assertTrue(retrieveText(notesSentTo,"Notes").contains(notes));
		return retrieveText(notesTimestamp,"Notes creation timestamp");
	}

	public void verifyTheAddedInternalNoteWithTimestamp(String fromUser,String toUser,String notes, String timestamp){
		assertTrue(retrieveText(notesCreatedBy,"Notes created by username").contains(fromUser));
		assertTrue(retrieveText(notesSentTo,"Notes sent to").contains(toUser));
		assertTrue(retrieveText(notesSentTo,"Notes").contains(notes));
		assertTrue(retrieveText(notesTimestamp,"Notes creation timestamp").contains(timestamp));
	}

}
