package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import static com.qa.util.TestUtil.*;

public class LiveEventsPage extends TestBase 
{
	int loginAttemptCounter = 1;
	String loginUrl = prop.getProperty(env + "URL");


	public static final By username = By.id("username");
	public static final By password = By.id("password");
	public static final By loginBtn = By.id("login");
	public static final By settingsCog = By.xpath("//button[@id='settings-dropdown-trigger-btn']");
	public static final By firstPatNameIntake = By.xpath("//div[@class='active-status-referral-list']/nh-intake-dashboard-referral-card[1]//h4");
	public static final By statusModalSelectStatusDropdown = By.xpath("//h5[contains(.,'Select Provider Status:')]/parent::div/select");
	public static final By statusModalFirstLOC = By.xpath("//h5[contains(.,'* Select Level of Care')]/parent::div//li[1]//label/span");
	public static final By statusModalDeclineReason = By.xpath("(//label[@id='decline-reason-grouping-lbl']/parent::div[contains(.,'Patient Too Complex')])[1]//span[@class='circle']");
	public static final By statusModalReopenButton = By.xpath("//button[@class='btn-flat waves-effect waves-light blue'][contains(.,'Reopen Referral')]");
	public static final By statusModalSendResponseButton = By.xpath("//div[@class='pull-right']/button[contains(.,'Send Response')]");
	public static final By declineAllOtherProvidersCheckbox = By.xpath("//span[contains(text(),'Decline all other providers')]//ancestor::label[@class='checkbox']");
	public static final By recordProviderStatusSave = By.xpath("//button[@class='btn-flat waves-effect waves-light'][contains(.,'Save')]");
	public static final By recordHospitalStatusSave = By.xpath("//button[@class='btn-flat waves-effect waves-light blue'][contains(.,'Save')]");
	public static final By statusModalASOCInput = By.xpath("//nh-datepicker[@formcontrolname='anticipatedSOCDate']//input");

	private static String propertiesFileName;
	// To make driver thread safe. This will be required for parallel execution from
	// testNG
	private static ThreadLocal<WebDriver> dr2 = new ThreadLocal<WebDriver>(); // Initialize thread local variable.

	public static WebDriver getdriver2()
	{
		return dr2.get();
	}

	// Setter method. This will set the webdriver reference driverref
	public static void setDriver2(WebDriver driverref) 
	{
		dr2.set(driverref);
	}

	public static void unload2() 
	{
		dr2.remove();
	}

	//initialize a second browser - FIREFOX
	public static void initSecondBrowser() throws MalformedURLException
	{
		try
		{
			propertiesFileName = "config.properties";
			prop = new Properties(); // initializing the prop variable
			FileInputStream fs = new FileInputStream("src/main/java/com/qa/config/" + propertiesFileName);
			prop.load(fs);

			// read browser, env and seleniumgrid url
			browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");
			reportLog("browser: " + browserName);
			env = System.getProperty("environment") != null ? System.getProperty("environment")
					: prop.getProperty("environment");
			reportLog("environment: " + env);
			if (prop.getProperty("remote").equals("true")) {
				seleniumGridURL = System.getProperty("seleniumgrid_url") != null
						? System.getProperty("seleniumgrid_url")
								: prop.getProperty("seleniumgrid_url");
				reportLog("seleniumgrid_url: " + seleniumGridURL);
			}

			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("webdriver.accept.untrusted.certs", true);
			options.setCapability("marionette", true);
			driver2 = new RemoteWebDriver(new URL(seleniumGridURL), options);

			setDriver2(driver2);

			// Create EventFiringWebDriver class object
			e_driver2 = new EventFiringWebDriver(getdriver2());
			// Now create object of EventListerHandler to register it with
			// EventFiringWebDriver
			eventListener2 = new WebEventListener();
			// Register the eventListener class object with event firing webdriver object.
			e_driver2.register(eventListener2);
			// Assign this to the main driver
			// driver = e_driver;
			setDriver2(e_driver2);

			getdriver2().manage().window().maximize();
			getdriver2().manage().deleteAllCookies();
			getdriver2().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
			getdriver2().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
			defaultWindow2 = getdriver2().getWindowHandle();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	// Navigate to login page
	public void navigateToLogin2()
	{
		reportLog("Login URL: " + loginUrl);
		getdriver2().get(loginUrl);
	}

	public void login2(String usrname, String pwd) 
	{
		reportLog("######################LOGGING IN ON A SECOND BROWSER################");
		//force logout
		getdriver2().get(loginUrl + "j_spring_cas_security_logout");
		// clear cookie before starting
		getdriver2().manage().deleteAllCookies();
		reportLog("cleared browser cookies");
		// navigate to login URL
		navigateToLogin2();
		// enter username and password
		getdriver2().findElement(username).sendKeys(usrname);
		getdriver2().findElement(password).sendKeys(pwd);
		// click on login button
		getdriver2().findElement(loginBtn).click();
		// wait for dashboard to load
		waitForPageToLoad();
		waitForLoaderToDisappear2();
		reportLog("loginAttempt: " + loginAttemptCounter);
		loginAttemptCounter++;
		try {
			boolean visibilityOfusernameTextbox = getdriver2().findElement(username).isDisplayed();
			if (loginAttemptCounter <= 3 && visibilityOfusernameTextbox) {
				reportLog("Login failed, attempting login again");
				login2(usrname, pwd);
			} else {
				loginAttemptCounter = 1;
				if (visibilityOfusernameTextbox)
					reportLog("Login Failed after 3 attempts");
				else
					reportLog("Logged In");
			}
		} catch (Exception e) {
			reportLog("Logged In");
		}
	}

	//when -> beforereferral, afterreferral
	public String getFirstPatientNameOnIntake(String when) {
		if (when.contains("before")) {
			reportLog("Fetching the first patient name on intake dashboard");
		} else {
			reportLog("Checking if the patient referral is updated on intake dashboard");
			waitForPageToLoad();
			threadMethod(8000);
		}
		String name = getdriver2().findElement(firstPatNameIntake).getText().trim();
		reportLog("First patient on intake dashboard: " + name);
		return name;
	}

	public static void scrollPageToElementAndClick2(WebElement element, String elemName) {
		JavascriptExecutor js = (JavascriptExecutor) getdriver2();
		reportLog("Clicking on " + elemName);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		threadMethod(2000);
		js.executeScript("arguments[0].click();", element);
		reportLog("Clicked on " + elemName);
	}

	public static void clickOnPatientOnIntake(String patientName) {
		By patientNameIntake = By.xpath("//div[@class='active-status-referral-list']/nh-intake-dashboard-referral-card//h4[normalize-space()='" + patientName + "']");
		reportLog("Clicking on patient name: " + patientName);
		scrollPageToElementAndClick2(getdriver2().findElement(patientNameIntake), "Patient Name");
		waitForLoaderToDisappear2();
		waitForPageToLoad();
	}

	// click on set status button
	public void clickOnSetStatusButton(String providerName) {
		By setStatusButton = By.xpath("//table[@id='providers-table']//td[contains(.,'" + providerName + "')]//parent::tr//button[contains(.,'Set Status')]");
		getdriver2().findElement(setStatusButton).click();
		reportLog("Clicked on set status button for the provider");
		waitForLoaderToDisappear2();
	}

	public static void waitForElementToLoad2(final WebElement element, String elementName) {
		WebDriverWait wait2 = new WebDriverWait(getdriver2(), Duration.ofSeconds(EXPLICIT_WAIT));
		reportLog("Waiting for web element to load on the page : " + elementName);
		// Wait until the element is located on the page
		@SuppressWarnings("unused")
		WebElement element1 = wait2.until(ExpectedConditions.visibilityOf(element));
	}

	public static String selectValueByVisibleText2(WebElement element, String Option, String elemName) {
		reportLog("Selecting '" + Option + "' from : " + elemName);
		// Wait for drop-down element to load on the page
		waitForElementToLoad2(element, elemName);
		// Locate drop-down field
		Select select = new Select(element);
		// Select value from drop-down
		select.selectByVisibleText(Option);
		// Log result
		reportLog("Selected '" + Option + "' from : " + elemName);
		return Option;
	}


	// update status for home health provider
	public String updateStatusForHomeHealthProvider2(String providerName, String status) {
		String asoc = null;
		// click on set status button for the given provider
		clickOnSetStatusButton(providerName);

		if (status != "Reopen Referral") {
			// select status from dropdown
			selectValueByVisibleText2(getdriver2().findElement(statusModalSelectStatusDropdown), status, "status dropdown");
			if (status == "Decision Pending Authorization" || status == "Decision Pending Review") {
				getdriver2().findElement(statusModalFirstLOC).click();
			} else if (status == "Decline") {
				getdriver2().findElement(statusModalDeclineReason).click();
			} else if (status == "Accept") {
				getdriver2().findElement(statusModalFirstLOC).click();
				asoc = getRandomDate(new Date(), new Date("11/31/2030"));
				getdriver2().findElement(statusModalASOCInput).sendKeys(asoc);
				reportLog("asoc is: " + asoc);
			}
		}
		// send status response for referral
		waitForElementToLoad2(getdriver2().findElement(statusModalSendResponseButton), "Send Response Button");
		reportLog("Clicking on Send Response");
		try {
			getdriver2().findElement(statusModalSendResponseButton).click();
		}catch (Exception e){
			reportLog(e.getMessage());
			scrollPageToElementAndClick2(getdriver2().findElement(statusModalSendResponseButton),"Send Response Button");
		}
		waitForLoaderToDisappear2();
		return asoc;
	}


	public static boolean waitForLoaderToDisappear2() {
		WebDriverWait wait2 = new WebDriverWait(getdriver2(), Duration.ofSeconds(EXPLICIT_WAIT));
		reportLog("Waiting for application loader to disappear");
		boolean invisbleFlag = false;
		int attempt = 1;

		while (attempt <= 3) {
			getdriver2().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			try {
				WebElement applicationLoaderWebElement = getdriver2()
						.findElement(By.xpath("//*[contains(@class,'loader-overlay')]"));
				invisbleFlag = wait2.until(invisibilityOf(applicationLoaderWebElement));
				break;
			} catch (NoSuchElementException e) {
				invisbleFlag = true;
				reportLog("loader is not present");
				break;
			} catch (StaleElementReferenceException e) {
				invisbleFlag = false;
				reportLog("Stale Element Reference Exception caught, attemptNo: " + attempt);
			} finally {
				getdriver2().manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
			}
			attempt++;
		}
		return invisbleFlag;
	}


}