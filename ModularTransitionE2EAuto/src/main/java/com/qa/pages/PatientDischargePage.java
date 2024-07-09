
/**
 * 
 */
package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.util.TestUtil.*;

public class PatientDischargePage extends TestBase {
	@FindBy(id = "discharge-header-discharge-button")
    WebElement DischargePageButton;
    @FindBy(xpath = "//input[@id='actual-discharge-date']//following-sibling::mat-datepicker-toggle")
    WebElement actualDischargeDate;
    @FindBy(id = "dischargeDisposition")
    WebElement dischargeDisposition;
    @FindBy(id = "dischargeStatus")
    WebElement dischargeStatus;
    @FindBy(xpath = "//button[text()=' SAVE AND REMOVE FROM WORKBOOK ']")
    WebElement saveAndRemoveFromWorkbook;
    @FindBy(xpath = ".//label[@for='no-services-']/span/span|//*[@formcontrolname='noServices']//div[@class='mat-checkbox-inner-container']")
    WebElement noServiceCheckbox;
    @FindBy(xpath="//mat-checkbox[@formcontrolname='noServices']")
    WebElement noServiceCheckboxStatus;
    @FindBy(xpath = "//div[contains(@class,'mat-calendar-body-today')]")
    WebElement dateModalTodayButton;
    @FindBy(id="delayReason")
    WebElement delayReasonDropDown;
    @FindBy(xpath = "//nh-discharge-discharge-patient-modal/div")
    WebElement dischargeModal;
    @FindBy(xpath = "//nh-svg-icon[@iconname='ic_discharge']/parent::button")
    WebElement dischargeButtonOnWorkbook;

    public PatientDischargePage() {
        PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
        // be initialized with this driver
    }

    public void navigateToDischargePage() {
    	scrollToTop();
    	reportLog("Scrolled up and waiting for the Discharge Button to load");
    	waitForElementToLoad(DischargePageButton, "Discharge Button");
    	reportLog("Discharge Button is loaded");
    	if (isElementPresent(DischargePageButton, "Discharge Page Button"))
            clickElement(DischargePageButton, "Discharge Page Button");
    	reportLog("Waiting for Discharge Modal to load");
        waitForElementToLoad(dischargeModal, "Discharge Modal");
    }
    public void selectDelayReason() {
    	if(isElementPresent(delayReasonDropDown,"Delay Reason")) {
            selectValueByIndex(delayReasonDropDown, 1, "Delay Reasoon");
    	}
    }
    public void selectDischargeDispostion() {
        if(isElementPresent(dischargeDisposition,"Discharge Dispo")) {
            selectValueByIndex(dischargeDisposition, 1, "Discharge Disposition");
        }
    }
    
    public void noServiceCheckboxSelect() {
        String isChecked =retrieveAttributeValue(noServiceCheckboxStatus,"class","No Service Checkbox Status");
        if(!isChecked.contains("checkbox-checked")) {
            clickElement(noServiceCheckbox, "No Service Checkbox");
        }
        else reportLog("No Service checkbox is already checked");
    }
    
    public void selectDischargestatus() {
        selectValueByIndex(dischargeStatus, 1, "Discharge status");
    }
    public void saveAndRemoveFromWorkbookbutton(){
        clickElement(saveAndRemoveFromWorkbook, "Save and Remove");
    }
    /**
     * This function will discharge a patient with default values and 1st option from the dropdowns
     */
    public void completeDischargeProcess() {
    	reportLog("Waiting for the Discharge modal to Open Up");
    	waitForPageToLoad();
    	reportLog("Waiting for the loader to disappear");
    	waitForLoaderToDisappear();
    	reportLog("Clicking on Actual Discharge Date ");
    	clickElement(actualDischargeDate, "Actual Discharge Date");
    	reportLog("Setting Actual Discharge Date as current date");
    	clickElement(dateModalTodayButton, "Current Date as ADD");
    	reportLog("Setting Discharge Disposition value");
    	selectDischargeDispostion();
    	reportLog("Setting Discharge Status value");
    	selectDischargestatus();
    	reportLog("Setting Delay Reason");
        selectDelayReason();
        reportLog("Selecting No Service Checkbox");
    	noServiceCheckboxSelect();
    	reportLog("Clicking on Save And Remove Button");
    	saveAndRemoveFromWorkbookbutton();
    	reportLog("Waiting for the loader to disappear");
    	waitForLoaderToDisappear();
    }
  
     public void openDischargeModalFromWorkbook(){//use after searching a particular patient for discharging purposes
        waitForElementToLoad(dischargeButtonOnWorkbook, "Discharge Button On workbook");
        reportLog("Discharge Button is loaded");
        if (isElementPresent(dischargeButtonOnWorkbook, "Discharge Page Button On workbook"))
            clickElement(dischargeButtonOnWorkbook, "Discharge Page Button On workbook");
        reportLog("Waiting for Discharge Modal to load");
        waitForElementToLoad(dischargeModal, "Discharge Modal");
    }

}
