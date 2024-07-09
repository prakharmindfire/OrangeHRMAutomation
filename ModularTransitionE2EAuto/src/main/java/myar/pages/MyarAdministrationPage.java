package myar.pages;

import static com.qa.util.TestUtil.clearAndInput;
import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.clickElementwithJS;
import static com.qa.util.TestUtil.getOptionsFromDropdown;
import static com.qa.util.TestUtil.isElementPresent;
import static com.qa.util.TestUtil.prepareWebElementWithDynamicXpath;
import static com.qa.util.TestUtil.retrieveText;
import static com.qa.util.TestUtil.scrollPageToElement;
import static com.qa.util.TestUtil.scrollPageToElementAndClick;
import static com.qa.util.TestUtil.selectValueByVisibleText;
import static com.qa.util.TestUtil.threadMethod;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

public class MyarAdministrationPage extends TestBase {
	// Page Factory - OR:

	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;

	@FindBy(xpath = "//span[@title='Add subgroup']")
	WebElement addSubGroup_icon;

	@FindBy(xpath = "//input[@id='user-group-name-for-add-user']")
	WebElement groupNameText;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement SaveButton;

	@FindBy(xpath = "//span[@title='Delete group']")
	WebElement deleteSubGroup_icon;

	@FindBy(xpath = "//button[text()='Delete']")
	WebElement deleteButton;

	@FindBy(xpath = "(//input[@id='custom-select'])[1]")
	WebElement AdminUserTab;

	@FindBy(xpath = "//span[text()='Log out']")
	WebElement admin_logout;

	@FindBy(xpath = "//span[@title='Add user']")
	WebElement addUser_icon;

	@FindBy(xpath = "//input[@id='salutation']")
	WebElement salutation;

	@FindBy(xpath = "//input[@id='firstName']")
	WebElement firstNameTextBox;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement lastNameTextBox;

	@FindBy(xpath = "//input[@id='email']")
	WebElement emailAddress;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='confirmPassword']")
	WebElement confirmPassword;

	@FindBy(xpath = "//button[text()=' CREATE ']")
	WebElement createButton;

	@FindBy(xpath = "//span[@title='Delete user']")
	WebElement delUser_icon;

	@FindBy(xpath = "//*[text()='Trusted Partner']")
	WebElement trustedPartner;

	@FindBy(xpath = "//select[contains(@class,'year-dropdown')]")
	WebElement selectYearDD;

	@FindBy(xpath = "//div[contains(@class,'table')]")
	WebElement billed_trustedPartner;

	public String user_userGrpName = "//span[text()='xxxxx']";
	public String salutations = "//button[text()='xxxxx']";

	// Initializing the Page Factory/Objects:
	public MyarAdministrationPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */

	public String getWebText_Title() {

		return retrieveText(webText_Title, "Web-text title");
	}

	public String getWebText_Content() {

		return retrieveText(webText_Content, "Web-text Content");
	}

	public boolean test_billed_trustedPartner_isDisplayed() {
		List<Boolean> st = new ArrayList();
		for (String year : getYears()) {
			selectYear(year);
			st.add(billed_trustedPartner_isDisplayed(year));
		}
		try {
			Assert.assertEquals(st.contains(false), false);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<String> getYears() {
		return getOptionsFromDropdown(selectYearDD, "Select Year");
	}

	public String selectYear(String year) {
		return selectValueByVisibleText(selectYearDD, year, "Select Year drop-down");
	}

	public void clickOnTrustedPartner() {
		clickElement(trustedPartner, "Trusted Partner");
	}

	public boolean billed_trustedPartner_isDisplayed(String year) {
		return isElementPresent(billed_trustedPartner, "Trusted Partner billing table for year : " + year);
	}

	public void clickOnDeleteUser() {

		scrollPageToElementAndClick(delUser_icon, "Delete user icon");
	}

	public void clickOnCreateButton() {
		clickElement(createButton, "CREATE button");
	}

	public void enterconfirmPWD(String pwd) {
		clearAndInput(confirmPassword, "Confirm password textbox", pwd);
	}

	public void enterPWD(String pwd) {
		clearAndInput(password, "Enter password textbox", pwd);
	}

	public void enterEmailaddress(String email) {
		clearAndInput(emailAddress, "Enter email address textbox", email);
	}

	public void enterLastname(String lName) {
		clearAndInput(lastNameTextBox, "Enter last name textbox", lName);
	}

	public void enterFirstname(String fName) {
		clearAndInput(firstNameTextBox, "Enter first name textbox", fName);
	}

	public void clickToSelectSalutation(String sal) {
		clickOnSalutation();
		clickElement(prepareWebElementWithDynamicXpath(salutations, sal), "Salutation as : " + sal);
	}

	public void clickOnSalutation() {
		clickElement(salutation, "Salutation drop down");
	}

	public void clickOnAddUser_icon() {
		clickElement(addUser_icon, "Add user icon");
	}

	public void clickOnDeleteButton() {
		clickElement(deleteButton, "Delete confirmation button");
	}

	public void clickOndeleteSubGroup_icon() {
		threadMethod(2000);
		clickElement(deleteSubGroup_icon, "Delete sub-group icon");
	}

	public boolean user_userGroup_isDisplayed(String UserGrp) {
		threadMethod(10000);
		return isElementPresent(prepareWebElementWithDynamicXpath(user_userGrpName, UserGrp),
				"subGroupname : " + UserGrp);
	}

	public void clickOnSaveButton() {
		clickElement(SaveButton, "Save Button");
	}

	public void enterGroupName(String groupName) {
		clearAndInput(groupNameText, "Enter Group name textbox", groupName);
	}

	public void clickToSelect_User_UserGrp(String UserGrp) {
		threadMethod(2000);
		scrollPageToElementAndClick(prepareWebElementWithDynamicXpath(user_userGrpName, UserGrp),
				"User Group : " + UserGrp);
	}

	public void clickOnAddSubGroup() {
		clickElement(addSubGroup_icon, "Add sub-group icon");
	}

	public void clickOnAdminAccountDD() {
		scrollPageToElement(AdminUserTab);
		clickElementwithJS(AdminUserTab, "Admin Account drop-down");

	}

	public void adminLogout() {
		threadMethod(10000);
		clickOnAdminAccountDD();
		threadMethod(2000);
		clickElement(admin_logout, "Administration option");
		// waitForPageToLoad();
		reportLog("Logged Out");
	}
}
