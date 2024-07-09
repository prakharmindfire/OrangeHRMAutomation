package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.qa.util.TestUtil.*;

public class LoginPage extends TestBase {

    // Page Factory - OR:
    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login")
    WebElement loginBtn;
    
    @FindBy(id = "cp_current_password")
    WebElement currentPWChangePwModal;

    @FindBy(id = "cp_new_password")
    WebElement newPWChangePwModal;

    @FindBy(id = "cp_confirm_password")
    WebElement confirmPWChangePwModal;



    @FindBy(xpath = "//button[@id='settings-dropdown-trigger-btn']")
    WebElement settingsCog;

    @FindBy(xpath = "(//a[@id='settings-dropdown-trigger-btn'])[2]")
    WebElement adminSettingsCog;

    @FindBy(xpath = "(//a[@id='settings-dropdown-logout-btn'])[2]")
    WebElement adminLogoutOptionInCog;

    @FindBy(xpath = "//p[contains(text(),'Notice to Users - This application is the private ')]")
    WebElement footerText;

    @FindBy(xpath = "//button[@angularticsaction='Logout']")
    WebElement logout;
    
    @FindBy(xpath = "//button[@angularticsaction='OpenGenericDocManager']")
    WebElement genericDocumentManager;
    
    @FindBy(xpath = "//img[@alt='CarePort, powered by WellSky']")
    WebElement curaspanLogo;
    
    @FindBy(xpath = "//*[contains(@angularticsaction,'OpenChangePassword')]")
    WebElement changePwSettingsCog;
    
    @FindBy(xpath = "//*[contains(@class,'nh-modal-title')]")
    WebElement changePwModalTitle;
    
    @FindBy(xpath = "//*[contains(text(),'Save Changes')]")
    WebElement changePwModalSaveChanges;

    
    //okta locators
    @FindBy(id = "okta-signin-username")
    WebElement oktaUsername;
    
    @FindBy(id = "okta-signin-password")
    WebElement oktaUserPassword;
    
    @FindBy(id = "okta-signin-submit")
    WebElement oktaSignInButton;
    
    @FindBy(xpath = "//span[@class='dropdown-menu--button-toggle']")
    WebElement oktaProfileDropdownButton;
    
    @FindBy(xpath = "//o-link[contains(.,'Sign out')]")
    WebElement oktaSignOutLink;
    
    @FindBy(xpath = "//button[contains(.,'Send anyway')]")
    WebElement sendAnywayButton;

    @FindBy(css = "[angularticsaction=\"OpenAccountSettings\"]")
	public
    WebElement accountSettingOptionUnderSettingsMenu;
    
    @FindBy(id="acc_settings_faxNumber")
    WebElement faxNumberOnAccountSettingsModal;
    
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButtonOnAccountSettingsModal;
    
    String loginUrl = prop.getProperty(env + "URL");
    String oktaUrl = prop.getProperty("oktaUrl");
    int loginAttemptCounter = 1;

    // Initializing the Page Factory/Objects:
    public LoginPage() {
        PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
        // be initialized with this driver
    }

    // Navigate to login page
    public void navigateToLogin() {
        reportLog("Login URL: " + loginUrl);
        getdriver().get(loginUrl);
    }

    /**
     * Actions/Methods on Login page:
     *
     * @throws InterruptedException
     **/

    public void inputFaxNumberOnAccountSettingsModal(String faxNumber) {
    	clearAndInput(faxNumberOnAccountSettingsModal, "faxNumber", faxNumber);
    }
    public String getFaxNumber() {
    	return retrieveAttributeValue(faxNumberOnAccountSettingsModal, "value","fax Number");
    }
    public void clickSettingsCog() {
    	reportLog("Clicking on Settings COG wheel icon");
        clickElement(settingsCog, "settings cog");
        waitForElementToLoad(logout, "Logout button");
    }
    
    public void clickAccountSettings() {
    	TestUtil.clickElement(accountSettingOptionUnderSettingsMenu,"Account Settings");
    	waitForLoaderToDisappear();
    }
    
    public void clickSaveOnAccountSettings() {
    	TestUtil.clickElement(saveButtonOnAccountSettingsModal,"Account Settings Save Button");
    	waitForLoaderToDisappear();
    }

    public void clickOnGenericDocMgrUnderSettingsCog() {
    	reportLog("Clicking on Generic Doc Manager Under setings icon options");
        clickElement(genericDocumentManager, "Generic Document Manager link under settings Cog");
        reportLog("Waiting for the Generic Doc manager page to load");
        waitForPageToLoad();
        waitForLoaderToDisappear();
        reportLog("Asserting whether user has landed on generic doc manager page");
        Assert.assertTrue(getURL().toString().contains("generic"));
        reportLog("User has successfully landed on the Generic Doc manager page");
     }
    
    public void clickOnChangePasswordOption() {
    	reportLog("Clicking on change password under settings cog");
        clickElement(changePwSettingsCog, "Change Password settings cog");
        reportLog("Clicked on change password under settings cog");
        boolean modalDisplayed = isChangePasswordModalOpened();
        String modalTitle = retrieveText(changePwModalTitle,"Title of the change password modal");
        if(modalDisplayed) {
        	Assert.assertEquals(modalTitle, "Change Your Password");
        	reportLog("change password option is clicked and change password modal is being displayed");
        }else {
        	reportLog("Change password modal is not displayed");
        }
    }

    public String changePasswordNow(String caseHistoryPassword) {
    	String newPassword = "Password"+randomNumberGenerator(10, 99);
    	reportLog("New password to be passed is -> "+newPassword);
    	reportLog("Provide the current password value now ");
    	input(currentPWChangePwModal, "Current Password field", caseHistoryPassword);
    	reportLog("Entered the current password value ");
    	reportLog("Provide the new password value now ");
    	input(newPWChangePwModal, "New Password field", newPassword);
    	reportLog("Entered the new password value ");
    	reportLog("Provide the confirm password value now ");
    	input(confirmPWChangePwModal, "Confirm Password field", newPassword);
    	reportLog("Entered the confirm password value ");
    	reportLog("Clicking on Save Changes Button");
    	clickElement(changePwModalSaveChanges, "Save Changes Button ");
    	reportLog("Clicked on Save Changes Button");
    	return newPassword;
    }

    public void revertBackTheOriginalPassword(String changedPassword, String caseHistoryPassword) {
    	reportLog("Reverting back to the Original Password");
    	reportLog("Provide the changed password value in current password field now ");
    	input(currentPWChangePwModal, "Current Password field", changedPassword);
    	reportLog("Entered the current password value ");
    	reportLog("Provide the original password value for new password field now ");
    	input(newPWChangePwModal, "New Password field", caseHistoryPassword);
    	reportLog("Entered the new password value ");
    	reportLog("Provide the confirm password value for the confirm password field now ");
    	input(confirmPWChangePwModal, "Confirm Password field", caseHistoryPassword);
    	reportLog("Entered the confirm password value ");
    	reportLog("Clicking on Save Changes Button");
    	clickElement(changePwModalSaveChanges, "Save Changes Button ");
    	reportLog("Clicked on Save Changes Button");
    	reportLog("Changed password value was = "+changedPassword);
    	reportLog("Original(reverted) password value is = "+caseHistoryPassword);
    }

    public void checkIfUserLoggedOutAfterPasswordChange(){
    	//Wait for 5 mins it takes time to logout 
    	refreshPage();
    	waitForPageToLoad();
    	waitForLoaderToDisappear();
    	reportLog("fetching the current page url");
        String currUrl = getURL();
        reportLog("current page url is -> "+currUrl);
        Assert.assertTrue(currUrl.contains("/login?service"));
        reportLog("Url is pointing to login page ---------- 1");
        Assert.assertEquals(username.isDisplayed(), true);
        reportLog("Username field is present ---------- 2");
        Assert.assertEquals(password.isDisplayed(), true);
        reportLog("Password field is present ---------- 3");
        Assert.assertEquals(loginBtn.isDisplayed(), true);
        reportLog("Login Button is present ------------- 4");
        reportLog(" Confirmed ! User is successfully redirected to login page ");
    }

    
    public void clickAdminSettingsCog() {
        clickElement(adminSettingsCog, "settings cog");
        waitForElementToLoad(adminLogoutOptionInCog, "Logout button");
    }

    public void login(String usrname, String pwd) {
    	
    	//force logout
    	getdriver().get(loginUrl + "j_spring_cas_security_logout");
        // clear cookie before starting
        getdriver().manage().deleteAllCookies();
        reportLog("Cleared browser cookies");
        // navigate to login URL
        navigateToLogin();
        // enter username and password
        clearAndInput(username, "Username Field", usrname);
        clearAndInput(password, "Password Field", pwd);
        // click on login button
        clickElement(loginBtn, "Login Button");
        // wait for dashboard to load
        waitForPageToLoad();
        waitForLoaderToDisappear();
        reportLog("LoginAttempt: " + loginAttemptCounter);
        loginAttemptCounter++;
        boolean visibilityOfusernameTextbox = isElementPresent(username, "username textbox on login page");
		if (loginAttemptCounter <= 3 && visibilityOfusernameTextbox) {
            reportLog("Login failed, attempting login again");
            login(usrname, pwd);
        } else {
            loginAttemptCounter = 1;
            if (visibilityOfusernameTextbox)
                reportLog("Login Failed after 3 attempts");
            else
                reportLog("Logged In");
        }
    }

    public void logout() {
        clickSettingsCog();
        clickElementwithJS(logout, "logout button");
        waitForPageToLoad();
        reportLog("Logged Out");
    }

    public void adminLogout() {
        clickAdminSettingsCog();
        clickElement(adminLogoutOptionInCog, "Admin logout button");
        waitForPageToLoad();
        reportLog("Logged Out");
    }

    public void openLoginPage(){
        driver.get(loginUrl + "j_spring_cas_security_logout");
        driver.get(loginUrl);
    }
    public boolean isUserNameDisplayed(){
        return  isElementPresent(username,"UserName text box");
    }
    public boolean isChangePasswordModalOpened(){
        return  isElementPresent(changePwModalTitle,"Change password Modal title");
    }
    public void clickLogout(){
          clickElement(logout,"logout button");
    }
    public boolean ispasswordDisplayed(){
        return  isElementPresent(password,"password text box");
    }
    public boolean isLoginBtnDisplayed(){
        return  isElementPresent(loginBtn,"Login Button");
    }

    public boolean isCuraspanLogoDisplayed(){
        return  isElementPresent(curaspanLogo,"Curaspan logo");
    }
    public String getFooterText(){
        return  retrieveText(footerText,"Footer Text");
    }


	public String getCuraSpanLogo() {
		return retrieveAttributeValue(curaspanLogo, "alt", "Logo text");
	}
    
    public void intakeSsoLaunchViaOkta(String ssoLaunchUrl, String ssoUsername, String oktaPassword) {
    	
		getdriver().get(ssoLaunchUrl);
		waitForElementToBeClickable(oktaSignInButton, "okta sign in button");
		input(oktaUsername, "okta username field", ssoUsername);
		input(oktaUserPassword, "okta user password field", oktaPassword);
		clickElement(oktaSignInButton, "okta sign in button");
		if (ssoLaunchUrl.contains("saml") && browserName.equals("firefox")) {
			
			acceptAlert("alert");
		} else if (ssoLaunchUrl.contains("saml") && (browserName.equals("chrome") || browserName.equals("edge"))) {
			clickElement(sendAnywayButton, "send ayway button");
		}
		waitForPageToLoad();
		waitForLoaderToDisappear();
    	
    }
    
    public void navigateToOkta() {
    	getdriver().get(oktaUrl);
    }
    
    public void logoutFromOkta() {   	
    	clickElement(oktaProfileDropdownButton, "okta profile dropdown button");
    	clickElement(oktaSignOutLink, "okta sign out link");
    }

}
