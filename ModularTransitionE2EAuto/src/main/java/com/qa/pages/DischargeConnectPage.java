/**
 * 
 */
package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

import static com.qa.util.TestUtil.*;

import java.util.ArrayList;
import java.util.List;

public class DischargeConnectPage extends TestBase {

	// update status locators
	@FindBy(xpath = "//button[contains(@id,'discharge-implementation-view-implementation-respond-to-referral-button')]")
	WebElement respondDropdown;

	//Message modal related locator
	@FindBy(xpath="//mat-row//mat-cell//a[1]//span")
	WebElement messagecountpopup;
	@FindBy(xpath="//div[@class='message nh-notes-modal-theirmessage']//h4")
	WebElement messageHeaderD2MsgModal;
	@FindBy(xpath="//div[@class='message nh-notes-modal-theirmessage']//p")
	WebElement messageBodyD2MsgModal;
	@FindBy(xpath="//nh-discharge-messages-view//a")
	WebElement messageModalClose;
	@FindBy(xpath="//textarea[@id='messages-modal--new-message']")
	WebElement messageTxtField;
	@FindBy(xpath="(//button[contains(.,'SEND')])[2]")
	WebElement msgSendBtn;
	@FindBy(xpath="//span[@class='notified-status']")
	WebElement notifiedStatusAtD2ConnectPage;
	@FindBy(xpath = "//div[contains(@class,'mymessage')]//p")
	List<WebElement> hospitalMessagesInGlobalMessagingModal;
	@FindBy(xpath = "//div[contains(@class,'theirmessage')]//p")
	List<WebElement> providerMessagesInGlobalMessagingModal;
	@FindBy(xpath = "//mat-row[contains(@class,'case-history')]//mat-cell[2]")
	List<WebElement> referralHistoryEntries;
	@FindBy(xpath = "//nh-discharge-referal-history//a")
	WebElement referralHistoryCloseIcon;

	// Dynamic locators
	String statusOption = "//button[contains(.,'xxxxx')]";
	String messagecountforprvdr = "//mat-cell[contains(.,'xxxxx')]/ancestor::mat-row//mat-cell//a[1]//span";
	String messageIconForProvider = "//mat-cell[text()[normalize-space()='xxxxx']]/..//mat-cell//a[1]/nh-svg-icon";
	String referralHistoryIconForProvider = "//mat-cell[text()[normalize-space()='xxxxx']]/following-sibling::mat-cell[3]//nh-svg-icon[@iconname='ic_history']";
	String hospitalStatus = "//mat-cell[text()[normalize-space()='xxxxx']]/..//mat-cell[contains(@id,'source-status')]//span";
	String providerStatus = "//mat-cell[text()[normalize-space()='xxxxx']]/..//mat-cell[contains(@class,'latestTargetStatus')]//p";

	// Initializing the Page Factory/Objects:
	public DischargeConnectPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	// Actions/Methods on this page:
	public void updateStatus(String status) {
		//click on respond dropdown
		clickElement(respondDropdown , "respond dropdown");
		//select status from respond dropdown
		clickElement(prepareWebElementWithDynamicXpath(statusOption, status), status + " in respond dropdown");
		waitForLoaderToDisappear();
		reportLog("updated status to " + status);
	}

	//Get the Count of the Message on connect Tab 
	public String messageCountConnectTab(String provider) {
		//Get the count and convert to int 
		String count= retrieveText(prepareWebElementWithDynamicXpath(messagecountforprvdr, provider), "Message count on msg icon");

		return count;
	}

	//Clicking on Message link on connect Tab
	public void clickOnMessageIconConnectTab(String Provider) {

		clickElement(prepareWebElementWithDynamicXpath(messageIconForProvider, Provider), "Message Icon On Connect Tab");
		waitForLoaderToDisappear();
		reportLog("Clicked on Message Icon for:  " + Provider+ " ----->SUCCESS!");

	}
	
	// clicking on referral history icon on connect Tab
	public void clickOnReferralHistoryConnectTab(String provider) {
		clickElement(prepareWebElementWithDynamicXpath(referralHistoryIconForProvider, provider),
				"referral history icon for provider: " + provider);
		waitForLoaderToDisappear();
	}

	//Get the Message Header text on Connect Message modal
	public String messageHeaderText() {

		return retrieveText(messageHeaderD2MsgModal, "Message Header Text");

	}
	//Get the message Content text on Connect Message Modal
	public String messageBodytext() {

		return retrieveText(messageBodyD2MsgModal, "Message Body Text");

	}

	//Close Message Modal
	public void closeMessageModal() {
		clickElement(messageModalClose, "Close button on Message Modal");
		waitForLoaderToDisappear();
		reportLog("Message Modal Close -----> SUCCESS!");

	}
	
	// close referral history
	public void closeReferralHistory() {
		clickElement(referralHistoryCloseIcon, "close icon on referral history modal");
		waitForLoaderToDisappear();
	}

	//Send message from Discharge
	public void sendMsgFromDischarge(String msg) {

		clearAndInput(messageTxtField, "D2 Message Text Field", msg);
		clickElement(msgSendBtn, "Send button on D2 Msg modal");
		waitForLoaderToDisappear();
	}

	//Retrieve the Notified status from connect page of D2
	public boolean isNotifiedStatusTextPresent() {

		return isElementPresent(notifiedStatusAtD2ConnectPage, "Notified Status at D2");
	}

	// get hospital status for provider
	public String getHospitalStatus(String provider) {
		return retrieveAttributeValue(prepareWebElementWithDynamicXpath(hospitalStatus, provider), "class",
				"hospital status for provider: " + provider);
	}

	// get provider status
	public String getProviderStatus(String provider) {
		return retrieveText(prepareWebElementWithDynamicXpath(providerStatus, provider),
				"provider status for provider: " + provider).trim();
	}

	// get messages from global messaging modal
	public List<String> getHospitalMessagesFromGlobalMessagingModal(String provider) {
		clickOnMessageIconConnectTab(provider);
		List<String> messages = new ArrayList<String>();
		for (WebElement messageInGlobalMessagingModal : hospitalMessagesInGlobalMessagingModal) {
			messages.add(retrieveText(messageInGlobalMessagingModal, "hospital message"));
		}
		closeMessageModal();
		return messages;
	}

	// get messages from global messaging modal
	public List<String> getProviderMessagesFromGlobalMessagingModal(String provider) {
		clickOnMessageIconConnectTab(provider);
		List<String> messages = new ArrayList<String>();
		for (WebElement messageInGlobalMessagingModal : providerMessagesInGlobalMessagingModal) {
			messages.add(retrieveText(messageInGlobalMessagingModal, "provider message"));
		}
		closeMessageModal();
		return messages;
	}
	
	//get referral history
	public List<String> getReferralHistory(String provider) {
		clickOnReferralHistoryConnectTab(provider);
		List<String> messages = new ArrayList<String>();
		for (WebElement referralHistoryEntry : referralHistoryEntries) {
			messages.add(retrieveText(referralHistoryEntry, "referral history"). replaceAll("\\s+", " "));
		}
		closeReferralHistory();
		return messages;
	}
	
}
