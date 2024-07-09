package myar.pages;

import static com.qa.util.TestUtil.assertText;
import static com.qa.util.TestUtil.clearAndInput;
import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.isElementPresent;
import static com.qa.util.TestUtil.retrieveText;
import static com.qa.util.TestUtil.threadMethod;
import static com.qa.util.TestUtil.waitForLoaderToDisappear;
import static com.qa.util.TestUtil.waitForPageToLoad;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarLoginPage extends TestBase {
	// Page Factory - OR:

	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;

	@FindBy(xpath = "(//input[@name='select'])[3]")
	WebElement languageDropDown;

	@FindBy(xpath = "//form//ul//li[text()='EN']")
	WebElement languageEN;

	@FindBy(xpath = "//form//ul//li[text()='DE']")
	WebElement languageDE;

	@FindBy(xpath = "//form//ul//li[text()='FR']")
	WebElement languageFR;

	@FindBy(xpath = "//form//ul//li[text()='NL']")
	WebElement languageNL;

	@FindBy(name = "emailAddress")
	WebElement enterEmailAddressTextBox;

	@FindBy(name = "password")
	WebElement enterPasswordTextBox;

	@FindBy(xpath = "//button[@data-testid='productSetupOpenMedia']")
	WebElement loginButton;

	@FindBy(xpath = "//span[contains(text(),' Forgot your password? ')]")
	WebElement forgotPasswordLink;

	@FindBy(xpath = "//input[@name='email']")
	WebElement resetPasswordEmailInput;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement continueBtn;

	String loginUrl = prop.getProperty(env + "URL");
	int loginAttemptCounter = 1;

	// Initializing the Page Factory/Objects:
	public MyarLoginPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	public boolean isLoginButtonPresent() {
		threadMethod(2000);
		return isElementPresent(loginButton, "Login Button");
	}

	public String getWebText_Title() {

		return retrieveText(webText_Title, "Web-text title");
	}

	public String getWebText_Content() {

		return retrieveText(webText_Content, "Web-text Content");
	}

	public void assertWebText_Title(String Expected_WebTextTitle) {
		assertText(Expected_WebTextTitle, getWebText_Title());
	}

	public void assertWebText_Content(String Expected_WebTextContent) {
		assertText(Expected_WebTextContent, getWebText_Content());
	}

	public void clickOnContinueButton() {
		clickElement(continueBtn, "CONTINUE button");
	}

	public void enterEmailAddress_resetPWD(String emailAddress) {
		clearAndInput(resetPasswordEmailInput, "Enter email address textbox", emailAddress);
	}

	public void clickOnForgotPasswordLink() {
		clickElement(forgotPasswordLink, "Forgot your password?");
	}

	public void login(String lang, String emailAddress, String password) {
		navigateToLoginURL();
		clickOnLanguageDropDown();
		selectLoginLanguage(lang);
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnLoginButton();
		waitForPageToLoad();
		waitForLoaderToDisappear();
		reportLog("loginAttempt: " + loginAttemptCounter);
		loginAttemptCounter++;
		boolean visibilityOfusernameTextbox = isElementPresent(enterEmailAddressTextBox,
				"Enter email address textbox on login page");
		if (loginAttemptCounter <= 3 && visibilityOfusernameTextbox) {
			reportLog("Login failed, attempting login again");
//            threadMethod(2000);
			login(lang, emailAddress, password);
		} else {
			loginAttemptCounter = 1;
			if (visibilityOfusernameTextbox)
				reportLog("Login Failed after 3 attempts");
			else
				reportLog("Logged In");
		}
	}

	// Navigate to login page
	public void navigateToLoginURL() {
		// clear cookie before starting
		getdriver().manage().deleteAllCookies();
		reportLog("Cleared browser cookies");

		reportLog("Login URL: " + loginUrl);
		getdriver().get(loginUrl);
	}

	public void clickOnLanguageDropDown() {
		// click on language drop down option
		clickElement(languageDropDown, "Language selector drop-down");
	}

	public void selectLoginLanguage(String lang) {
		if (lang.equals("EN")) {
			clickElement(languageEN, "Language " + lang);
		}
		if (lang.equals("DE")) {
			clickElement(languageDE, "Language " + lang);
		}
		if (lang.equals("FR")) {
			clickElement(languageFR, "Language " + lang);
		}
		if (lang.equals("NL")) {
			clickElement(languageNL, "Language " + lang);
		}
	}

	public void enterEmailAddress(String emailAddress) {
		clearAndInput(enterEmailAddressTextBox, "Enter email address textbox", emailAddress);
	}

	public void enterPassword(String password) {
		clearAndInput(enterPasswordTextBox, "Enter password textbox", password);
	}

	public void clickOnLoginButton() {
		clickElement(loginButton, "Login Button");
	}

}
