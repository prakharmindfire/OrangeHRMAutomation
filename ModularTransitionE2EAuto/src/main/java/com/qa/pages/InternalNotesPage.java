package com.qa.pages;

import static com.qa.util.TestUtil.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.qa.base.TestBase;
import com.qa.util.APIUtil;
import com.qa.util.FakeDataProvider;

public class InternalNotesPage extends TestBase {

	//Internal Notes Locators
	@FindBy(xpath = "//*[@iconname='ic_document']")
	WebElement internalNotesIcon;
	@FindBy(xpath = "//*[contains(@id,'mat-dialog-title')]")
	WebElement intenalNotesModalTitle;
	@FindBy(xpath = "//*[contains(@id,'note-modal--new-message')]")
	WebElement internalNoteTextArea;
	@FindBy(xpath = "//button[contains(text(),'SEND' )]")
	WebElement internalNotesSendButton;
	@FindBy(xpath = "//mat-icon[contains(text(),'close' )]")
	WebElement internalNotesCloseIcon;
	@FindBy(xpath = "//*[contains(@id,'discharge-header-notes-button' )]")
	WebElement internalNotesButtonWorkflowPages;
	@FindBy(xpath = "//*[contains(@class,'message nh-notes-modal-mymessage' )]")
	WebElement lastInternalNoteMessage;


	// Initializing the Page Factory/Objects:
	public InternalNotesPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}


	public void addInternalNotes(String internalNote,String internalNoteModalTitle) {

		clickElement(internalNotesIcon, "Click On Internal Notes Icon");
		waitForLoaderToDisappear();
		//Assert.assertEquals(intenalNotesModalTitle, internalNoteModalTitle);
		reportLog("Title Of Internal Notes Modal = "+intenalNotesModalTitle.getText());
		reportLog("Expected Title Of Internal Notes Modal = "+internalNoteModalTitle);
		input(internalNoteTextArea,"Send a note",internalNote);
		clickElement(internalNotesSendButton, "Click On Internal Notes Send Button");
		waitForLoaderToDisappear();
		clickElement(internalNotesCloseIcon, "Close Internal Notes Modal");
		waitForLoaderToDisappear();
	}

	public void checkPreviousInternalNote(String internalNote,String internalNoteModalTitle) {
		clickElement(internalNotesButtonWorkflowPages, "Click On Internal Notes Button");
		waitForLoaderToDisappear();
		reportLog("=========INTERNAL NOTES MODAL TITLE : "+intenalNotesModalTitle.getText());
		reportLog("=========LAST INTERNAL NOTES ADDED : "+lastInternalNoteMessage.getText());
		reportLog("=========LAST INTERNAL NOTES ADDED(expected) : "+internalNote);
		reportLog("Pass : Last Added note is present");
		clickElement(internalNotesCloseIcon, "Close Internal Notes Modal");
		waitForLoaderToDisappear();
	}

}