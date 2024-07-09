package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.qa.util.TestUtil.*;
import static org.testng.Assert.assertTrue;

public class QuickcasePage extends TestBase {
  	String qcURL = prop.getProperty(env + "QC_URL");
  
    @FindBy(xpath = "//input[@name='referralCode']")
    WebElement referralCodeInput;
    @FindBy(xpath = "//input[@name='pin']")
    WebElement referralPinInput;
    @FindBy(id = "loginSubmitBtn")
    WebElement loginSubmitBtn;
    @FindBy(xpath = "//input[@name='name']")
    WebElement cMNameInput;
    @FindBy(xpath = "//input[@name='emailAddress']")
    WebElement emailAddressInput;
    @FindBy(css ="span.agree-text")
    WebElement acknowledgementCheckbox;
    @FindBy(id="verifyBtn")
    WebElement getReferralBtn;
    @FindBy(id="acceptBtn")
    WebElement acceptBtn;
    @FindBy(xpath = "//button[contains(@class,'green-btn')]")
    WebElement acceptedGreenBtn;
    @FindBy(css = "span.text-normal")
    WebElement acceptanceMessageHeader;
   	@FindBy(id= "pendo-guide-container")
    WebElement pendoDiv;
    @FindBy(xpath = "//button[contains(@id,'pendo-close-guide')]")
    WebElement pendoClose;
    @FindBy(xpath = "//app-decline-reason//select")
    WebElement declineReasonDropdown;
    @FindBy(css = "button.print-btn.orange-btn")
    WebElement referralDeclinedRedBtn;
    @FindBy(xpath = "//div[@class='decline-response float-right']")
    WebElement declineTextPara;
    @FindBy(id = "coordinateTab")
    WebElement coordinatorTab;
    @FindBy(id = "edit0")
    WebElement editCoordinatorOne;
    @FindBy(id = "delete0")
    WebElement deleteCoordinatorOne;
    @FindBy(id = "save0")
    WebElement saveCoordinatorOne;
    @FindBy(xpath = "//div[@class='cdk-global-overlay-wrapper']/div")
    WebElement infoModal;
    @FindBy(css = ".mat-dialog-content.dialog-content>p")
    WebElement infoModalBody;
    @FindBy(xpath = "//button[normalize-space()='DELETE']")
    WebElement confirmDeleteBtn;
    @FindBy(xpath = "//button[normalize-space()='CANCEL']")
    WebElement cancelBtn;
    @FindBy(id = "addCoordinatorBtn")
    WebElement addNewCoordinatorBtn;
    @FindBy(xpath = "//button[normalize-space()='ADD']")
    WebElement addCoordinatorBtn;
    @FindBy(xpath = "//button[normalize-space()='CLOSE']")
    WebElement infoModalCloseBtn;
    @FindBy(id = "declineBtn")
    WebElement declineBtn;

    // dynamic locators
    String providerNameSelection = "//select[@name='facility']//option[@title='xxxxx']";
    String inputLname = "(//input[@name='lastName'])[xxxxx]";
    String inputFname = "(//input[@name='firstName'])[xxxxx]";
    String inputEmail = "(//input[@name='email'])[xxxxx]";
    String coordinatorRows = "//div[@class='coordinator-container']/form/div";

    //String constants
    final String acceptanceMsg = "Thank you. The discharging facility has been notified of your acceptance.";
    final String declinedResponseText = "Thank you for responding. Your response has been submitted to the referring facility.\n\nWe will now stop sending further updates on this referral and you will no longer have access to it.";



    // Initializing the Page Factory/Objects:
    public QuickcasePage() {
        PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
        // be initialized with this driver
    }
  
    //Opens QC in a new tab
    public void navigateToQuickcase(){
        reportLog("QC URL: " + qcURL);
        openNewWindowAndSwitch();
        getdriver().get(qcURL);
        waitForPageToLoad();
    }

    //Login to quickcase using referral code and pin
    public void loginToQuickcase(String[] referralAccessDetails) {
        reportLog("Logging in to Quickcase");
      	navigateToQuickcase();
        closePendoAlert();
        input(referralCodeInput,"referralCodeInput",referralAccessDetails[0]);
        input(referralPinInput, "referralPinInput", referralAccessDetails[1]);
        clickElement(loginSubmitBtn,"LoginSubmitBtn");
        waitForLoaderToDisappear();
        reportLog("Logged in to Quickcase");
    }

    //Submit details on verify page
    public void submitDetailsOnVerifyPage(String providerName){
        clearAndInput(cMNameInput,"case manager name","AutomationTest");
        clearAndInput(emailAddressInput, "CM email","Automation@Automation.Test");
        clickElement(prepareWebElementWithDynamicXpath(providerNameSelection,providerName),"Facility Name from Dropdown");
        clickElement(acknowledgementCheckbox,"Acknowledgement Checkbox");
        clickElement(getReferralBtn,"Get Referral Button on Verify Page");
        waitForLoaderToDisappear();
    }

    //accept referral
    public void acceptReferralOnReferralPage(){
        assert(getURL().contains("referral"));
        waitForElementToBeClickable(acceptBtn, "Accept Button");
        clickElement(acceptBtn,"Accept Button");
        waitForLoaderToDisappear();
        waitForElementToLoad(acceptedGreenBtn, "accepted green button");
        assert(retrieveText(acceptanceMessageHeader,"Acceptance Message Header").contains(acceptanceMsg));
    }
  
    public void closePendoAlert(){
        boolean isPresent= isElementPresent(pendoDiv,"Pendo Div");
        if(isPresent){
            clickElement(pendoClose,"pendo close button");
        }
    }


    //decline referral
    public void declineReferralOnReferralPage() {
        assertTrue(getURL().contains("referral"));
        waitForElementToBeClickable(declineBtn, "Decline Button");
        clickAndWaitForPageLoad(declineBtn, "Decline Button");
        selectValueByVisibleText(declineReasonDropdown, "Payer Not Accepted", "Decline Reason Dropdown");
        waitForLoaderToDisappear();
        clickAndWaitForPageLoad(declineBtn, "Decline Button");
        reportLog("Clicked on Decline Button");
        waitForElementToLoad(referralDeclinedRedBtn, "Referral Declined Red Button");
        assertTrue(retrieveText(declineTextPara, "Declined Test").contains(declinedResponseText));
        reportLog("Successfully declined referral");
    }

    public void navigateToCoordinatorTab() {
        boolean isPresent = isElementPresent(coordinatorTab, "Intake Coordinators Tab");
        if (isPresent) {
            clickAndWaitForPageLoad(coordinatorTab, "Intake Coordinator Tab");
            waitForLoaderToDisappear();
            reportLog("Asserting URL to contain 'coordinator'");
            assertTrue(getURL().contains("coordinator"));
            reportLog("Navigated to coordinator page");
            closePendoAlert();
        } else reportLog("Intake Coordinators Tab not present");
    }

    public void deleteAllCoordinator() {
        closePendoAlert();
        int initalCount = getdriver().findElements(By.xpath(coordinatorRows)).size();
        while (initalCount > 0 && isElementPresent(editCoordinatorOne, "Edit Coordinator One Button")) {
            reportLog("Number of coordinators before deleting: " + initalCount);
            clickElement(editCoordinatorOne,"Coordinator one edit btn");
            clickAndWaitForPageLoad(deleteCoordinatorOne, "Coordinator one delete btn");
            waitForElementToLoad(infoModal, "Info Modal");
            assertTrue(retrieveText(infoModalBody, "Info Modal Body").contains("Are you sure you want to delete"));
            assertTrue(isElementPresent(cancelBtn, "Cancel Button"));
            assertTrue(isElementPresent(confirmDeleteBtn, "Confirm Delete Button"));
            clickAndWaitForPageLoad(confirmDeleteBtn, "Confirm Delete Button");
            waitForLoaderToDisappear();
            try {
                initalCount = getdriver().findElements(By.xpath(coordinatorRows)).size();
                reportLog("Number of coordinators after deleting: " + initalCount);
            } catch (NoSuchElementException e) {
                reportLog("No more coordinator data row found");
                initalCount = 0;
            }
            reportLog("Successfully deleted coordinators data");
        }
    }

    public void addACoordinator() {
        reportLog("Add coordinator Test");
        String fname = "FAuto" + randomStringGenerator(4);
        String lname = "LAuto" + randomStringGenerator(4);
        String email = randomStringGenerator(4) + "@testme.com";
        int count = 1;
        if (isEnabled(addNewCoordinatorBtn, "Add New Coordinator Btn")) {
            reportLog("Add New Coordinator Btn Enabled");
            clickAndWaitForPageLoad(addNewCoordinatorBtn, "Add new coordinator btn");
            count = getdriver().findElements(By.xpath(coordinatorRows)).size();
            reportLog("Number of coordinators: " + count);
        } else reportLog("Add new coordinator btn not enabled");
        input(prepareWebElementWithDynamicXpath(inputFname, Integer.toString(count)), "First Name", fname);
        input(prepareWebElementWithDynamicXpath(inputLname, Integer.toString(count)), "Last Name", lname);
        input(prepareWebElementWithDynamicXpath(inputEmail, Integer.toString(count)), "Email", email);
        clickAndWaitForPageLoad(addCoordinatorBtn, "Add Coordinator Btn");
        waitForLoaderToDisappear();
        waitForElementToLoad(infoModal, "Info Modal");
        assertTrue(retrieveText(infoModalBody, "Info Modal Boyd").contains("Coordinator saved"));
        clickElement(infoModalCloseBtn, "Info Modal Close Button");
        reportLog("Successfully added a coordinator");
        //assert added data on UI
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputFname, "1"), "value", "Fname").contains(fname));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputLname, "1"), "value", "Lname").contains(lname));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputEmail, "1"), "value", "EMail").contains(email));
    }

    public void editCoordinator(String finalEditedEmail) {
        reportLog("Testing Editing Coordinator Data");
        reportLog("Asserting the fields are readonly before clicking on edit icon");
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputFname, "1"), "readonly", "Fname").contains("true"));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputLname, "1"), "readonly", "Lname").contains("true"));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputEmail, "1"), "readonly", "EMail").contains("true"));
        clickAndWaitForPageLoad(editCoordinatorOne, "Coordinator One Edit Btn");
        reportLog("Validating place holder text of the fields");
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputFname, "1"), "placeholder", "Fname").contains("Enter First Name"));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputLname, "1"), "placeholder", "Lname").contains("Enter Last Name"));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputEmail, "1"), "placeholder", "EMail").contains("Enter Email Address"));
        clearAndInput(prepareWebElementWithDynamicXpath(inputFname, "1"), "Fname", "EditedFname");
        clearAndInput(prepareWebElementWithDynamicXpath(inputLname, "1"), "Lname", "EditedLname");
        clearAndInput(prepareWebElementWithDynamicXpath(inputEmail, "1"), "Email", finalEditedEmail);
        clickAndWaitForPageLoad(saveCoordinatorOne, "Save First Coordinator Btn");
        waitForLoaderToDisappear();
        waitForElementToLoad(infoModal, "Info Modal");
        clickElement(infoModalCloseBtn, "Close Btn on Info Modal");
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputFname, "1"), "value", "Fname").contains("EditedFname"));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputLname, "1"), "value", "Lname").contains("EditedLname"));
        assertTrue(retrieveAttributeValue(prepareWebElementWithDynamicXpath(inputEmail, "1"), "value", "EMail").contains(finalEditedEmail));
        reportLog("sucessfully edited coordinator data.");
    }



}
