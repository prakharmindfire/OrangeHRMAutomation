package com.qa.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.assertTrue;

import com.qa.base.TestBase;

public class OmePage extends TestBase {

	String omeUrl = prop.getProperty(env + "OmeURL");

	// locators
	@FindBy(id = "created-after")
	WebElement createdAfter;
	@FindBy(xpath = "//button[contains(@class,'datepicker')][contains(.,'Done')]")
	WebElement doneButton;
	@FindBy(xpath = "//select[@name='messages_length']")
	WebElement messageCountDropdown;
	@FindBy(id = "refresh")
	WebElement searchButton;
	@FindBy(id = "messages_info")
	WebElement messagesCount;
	@FindBy(id = "destinationId")
	WebElement destination;
	@FindBy(xpath = "//input[@id='dischargeId']")
	WebElement dischargeIdInput;
	@FindBy(xpath = "//button[contains(@class,'dropdown-toggle preview')]")
	WebElement previewButton;
	@FindBy(xpath = "//a[text()='TEXT']")
	WebElement textPreviewButton;
	@FindBy(xpath = "(//div[@class='referralContent']//span)[3]")
	WebElement referralCode;
	@FindBy(xpath = "(//div[@class='referralContent']//span)[4]")
	WebElement referralPin;
	@FindBy(xpath = "//td//span[contains(text(),'Verification Code')]")
	WebElement emailVerificationCode;

	// dynamic locators
	String omeColumnHeaders = "//table[@id='messages']/thead/tr/th[xxxxx]";
	String omeColumnValues = "(//table[@id='messages']//tr)[xxxxx]/td[xxxxx]";
	String targetNotification="//table[@id='messages']//td[contains(text(),'"+"xxxxx"+"')]/following-sibling::td[contains(text(),'"+"xxxxx"+"')]";
	String emailValidationNotification = "(//td[text()='xxxxx']/following::td[text()='CCARE.NOTIFICATION.EMAIL_VALIDATION_NOTIFICATION']/following::td//button[contains(@class,'dropdown-toggle preview')])[1]";

	Map<String, Integer> omeColumns = new HashMap<String, Integer>();

	// Initializing the Page Factory/Objects:
	public OmePage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	// Navigate to OME in a new tab
	public void navigateToOme() throws InterruptedException {
		reportLog("OME URL: " + omeUrl);
		openNewWindowAndSwitch();
		getdriver().get(omeUrl);
		waitForPageToLoad();
	}

	public void filterRecordByDischargeId(String dischargeId){
		waitForElementToLoad(dischargeIdInput,"dischargeId input");
		clearAndInput(dischargeIdInput, "dischargeId", dischargeId);
		clickElement(searchButton, "Search button");
		waitForPageToLoad();
	}

	public String[] getReferralCodeAndPinAndSwitchToDischargeTab() throws InterruptedException {
		clickElement(previewButton,"preview button");
		clickElement(textPreviewButton,"text option");
		switchToNewWindow();
		String[] referralAccessDetails = new String[2];
		referralAccessDetails[0]= retrieveText(referralCode,"referralCode");
		referralAccessDetails[1] = retrieveText(referralPin,"referralPin");
		switchToSpecificTab(0);
		closeAllSubTabs();
		return referralAccessDetails;
	}
	
	public void setCreatedAfter(String startTime) throws InterruptedException {
		clearAndInput(this.createdAfter, "created after textbox", startTime);
		clickElement(doneButton, "done in datepicker");
		selectValueByVisibleText(messageCountDropdown, "100", "message count dropdown");
		clickElement(searchButton, "search button");
		Thread.sleep(5000); // wait for results to refresh
	}
	
	public void setDestination(String destination) {
		clearAndInput(this.destination, "destination", destination);
	}
	
	public int getMessagesCount() {
		return Integer.valueOf(retrieveText(messagesCount, "messages count").split(" ")[5]);
	}

	private WebElement getOmeColumnHeaderByCount(int omeColumnCount) {
		return prepareWebElementWithDynamicXpath(omeColumnHeaders, String.valueOf(omeColumnCount));
	}

	public void setOmeColumns() {
		int omeColumnIndex = 1;
		while (isElementPresent(getOmeColumnHeaderByCount(omeColumnIndex), "ome header " + omeColumnIndex)) {
			// get column names and index
			omeColumns.put(retrieveText(getOmeColumnHeaderByCount(omeColumnIndex), "ome header " + omeColumnIndex),
					omeColumnIndex);
			omeColumnIndex++;
		}
		for (Map.Entry<String, Integer> entry : omeColumns.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	}

	public int getColumnNameIndex(String columnName) {
		return omeColumns.get(columnName);
	}

	public String getOmeColumnValue(int rowNumber, String columnName) {
		String temp = omeColumnValues.replaceFirst("xxxxx", String.valueOf(rowNumber + 1));
		return retrieveText(prepareWebElementWithDynamicXpath(temp, String.valueOf(omeColumns.get(columnName))),
				columnName + " from " + " notification entry " + rowNumber);
	}

	public void assertNotificationForDestinationEmail(String destEmail, String notificationType){
		reportLog("--Asserting "+notificationType+" notification for "+destEmail);
		assertTrue(isElementPresent(prepareWebElementWithDynamicXpath(targetNotification.replaceFirst("xxxxx", destEmail),
				notificationType),"Target Notification"));
		reportLog("Notification Present");
	}

	public String retrieveEmailVerificationCode(String destEmail) throws InterruptedException {
		reportLog("Filtering OME records by destination : " + destEmail);
		navigateToOme();
		waitForElementToLoad(destination, "Destination Input Field");
		setDestination(destEmail);
		clickElement(searchButton, "Search button");
		waitForPageToLoad();
		clickElement(prepareWebElementWithDynamicXpath(emailValidationNotification, destEmail), "Email Validation Notification");
		clickElement(textPreviewButton, "text option");
		switchToNewWindow();
		String fullText = retrieveText(emailVerificationCode, "Email Verification Code");
		String code = fullText.replaceAll("Verification Code: ", "").trim();
		switchToSpecificTab(0);
		closeAllSubTabs();
		return code;
	}

}
