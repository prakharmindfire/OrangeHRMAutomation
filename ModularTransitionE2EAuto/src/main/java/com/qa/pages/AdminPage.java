package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.qa.util.TestUtil.*;

import java.util.ArrayList;
import java.util.List;

public class AdminPage extends TestBase {
    public static String customerSearchResult = "//a[contains(.,'xxxxx')]";
    public static String customerConfigDefaultValue = "//tr[contains(.,'xxxxx')]//td[4]//span";

    public static String customerConfigOverrideValue = "//tr[contains(.,'xxxxx')]//td[5]//span";

    public static String customerConfigEditButton = "//tr[contains(.,'xxxxx')]//td[6]//button[contains(@id,'edit-setting')]";

    public static String customerConfigOverrideCheckbox = "//tr[contains(.,'xxxxx')]//td[5]//span[@class='check']";

    public static String customerConfigRemoveButton = "//tr[contains(.,'xxxxx')]//td[6]//button[contains(.,'Remove')]";

    public static String customerConfigSaveButton = "//tr[contains(.,'xxxxx')]//td[6]//button[contains(.,'Save')]";


    @FindBy(xpath = "//button[contains(.,'Settings')]")
    WebElement adminCustomerSettingsTab;
    @FindBy(xpath = "//a[@id='setup-tab-customer']")
    WebElement customerTab;
    @FindBy(xpath = "//input[@id='setup-search-by-customer']")
    WebElement searchByCustomerTextBox;
    @FindBy(xpath = "//button[contains(@id,'search-button')]")
    //button[contains(@id,'search-button')]
    WebElement searchButton;
    @FindBy(id = "customer-detail-team-assignments-tab")
    WebElement teamAssignmentTab;
    @FindBy(xpath = "//td[contains(@id,'team-name')]")
    List<WebElement> teamNamesInTab;
    @FindBy(xpath = "//td[contains(@id,'status')]")
    List<WebElement> teamStatusesInTab;

    // Initializing the Page Factory/Objects:
    public AdminPage() {
        PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
        // be initialized with this driver
    }


//Action Methods

    public void searchCustomer(String customerName) {
        clickElement(customerTab, "Customer Tab");
        clearAndInput(searchByCustomerTextBox, "Customer search Tex box", customerName);
        scrollPageToElementAndClick(searchButton, "Search button");
        threadMethod(5000);
        clickElement(prepareWebElementWithDynamicXpath(customerSearchResult, customerName), "click on customer from search result");
    }

    public void clickCustomerSettingsTab() {
        clickElement(adminCustomerSettingsTab, "Admin Customer setting tab");
    }

    public String getDefaultValue(String configName) {
        scrollPageToElement(prepareWebElementWithDynamicXpath(customerConfigDefaultValue, configName));

        return retrieveText(prepareWebElementWithDynamicXpath(customerConfigDefaultValue, configName), "Config default value text");
    }

    public List<String> getTeamNamesInTab() {
    	List<String> teamNames= new ArrayList<>();
    	for(int i=0;i<teamNamesInTab.size();i++) {
    		teamNames.add(retrieveText(teamNamesInTab.get(i), "teamNamesInTab"+i));
    	}
    	return teamNames;
    }
    public void setConfigValue(String configName, String currentDefaultValue, String requiredConfigValue) {

        String currentOverrideValue = retrieveText(prepareWebElementWithDynamicXpath(customerConfigOverrideValue, configName), "Current customer config override value");
        reportLog(configName + " override value is " + currentOverrideValue);
        //clear any override value if present
        if (!currentOverrideValue.isEmpty()) {
            reportLog("current override value is not null");
            scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(customerConfigEditButton, configName), "Customer config edit button");
            scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(customerConfigRemoveButton, configName), "Customer config remove button");
            reportLog("override value removed");
        }
        //set override value if needed

        if (currentDefaultValue != requiredConfigValue) {
            scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(customerConfigEditButton, configName), "Customer config edit button");
            if (requiredConfigValue == "true") {
                scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(customerConfigOverrideCheckbox, configName), "Customer Config override checkbox");
            }
            scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(customerConfigSaveButton, configName), "Customer config save button");
            reportLog("Updated override config value to " + requiredConfigValue);
        }
        waitForPageToLoad();
    }
    public void clickTeamAssignmentTab() {
    	clickAndWaitForPageLoad(teamAssignmentTab, "teamAssignmentTab");
    }
    public List<String> getTeamStatusesInTab() {
    	List<String> teamStatus= new ArrayList<>();
    	for(int i=0;i<teamStatusesInTab.size();i++) {
    		teamStatus.add(retrieveText(teamStatusesInTab.get(i), "teamStatusesInTab"+i));
    	}
    	return teamStatus;
    }
}
