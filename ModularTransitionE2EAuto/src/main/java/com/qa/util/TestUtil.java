package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.formula.functions.Now;
import org.json.JSONArray;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;

public class TestUtil extends TestBase {
	// Static public variable to use anywhere.
	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 20;
	public static long EXPLICIT_WAIT = 60;
	public static WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(EXPLICIT_WAIT));
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MMM_yyyy_HH_mm_ss");
	static LocalDateTime now = LocalDateTime.now();
	public static String CurrentTime = dtf.format(now);
	static JavascriptExecutor js = (JavascriptExecutor) getdriver();

	public void switchToFrame() {
		getdriver().switchTo().frame("mainpanel");
	}

	public static void threadMethod(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException ie) {
		}
	}

	public static String randomStringGenerator(int length) {
		String randomString = RandomStringUtils.randomAlphabetic(length);
		return randomString;
	}

	//Scroll to top of the page 
	public static void scrollToTop() {
		reportLog("Scrolling to the the top of the Page");
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	//Scroll to top of the page 
	public static void pressEnter() {
		reportLog("Pressing Enter");
		getdriver().switchTo().activeElement().sendKeys(Keys.ENTER);
		reportLog("Enter Pressed");
	}
	/* Below function is written to convert Json Array passed to List 
	 * 
	 */

	public static List<String> convertJsonArrayToList(JSONArray jsonArray) {
		List<String> listdata = new ArrayList<String>();
		// Checking whether the JSON array has some value or not
		if (jsonArray != null) {

			// Iterating JSON array
			for (int i = 0; i < jsonArray.length(); i++) {

				// Adding each element of JSON array into ArrayList
				listdata.add(jsonArray.getString(i));
			}
		}
		return listdata;
	}

	public static long randomNumberGenerator(long min, long max) {
		Random r = new Random();
		long range = max - min + 1;
		long fraction = (long) (range * r.nextDouble());
		long randomNumber = fraction + min;
		reportLog("Generated : " + randomNumber);
		return randomNumber;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) getdriver()).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "//screenshots//" + System.currentTimeMillis() + ".png"));
	}

	// Creating a method getScreenshot and passing two parameters
	// driver and screenshotName
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		// below line is just to append the date format with the screenshot name to
		// avoid duplicate names
		reportLog("Capturing Screenshot");
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// screenshot path
		// for Jenkins - Jenkins build number in folder name
		// for Local run - it will use currenttime in folder name
		String screenshotLocation = buildNumber != null
				? System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "TestResults" + File.separator + "Report_" + buildNumber
						+ File.separator + screenshotName + dateName + ".png"
						: System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "TestResults" + File.separator + "Report_" + TestUtil.CurrentTime
						+ File.separator + screenshotName + dateName + ".png";
		reportLog("BUILD NUMBER in screenshot method: " + buildNumber);
		reportLog("SCREENSHOT LOCATION: " + screenshotLocation);
		String destination = screenshotLocation;
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// This is required for Jenkins
		String ImagePathForRemote = "./" + screenshotName + dateName + ".png";
		// Returns the captured file path
		return ImagePathForRemote;
	}

	/*
	 * public static void runTimeInfo(String messageType, String message) throws
	 * InterruptedException { js = (JavascriptExecutor) getdriver(); // Check for
	 * jQuery on the page, add it if need be
	 * js.executeScript("if (!window.jQuery) {" +
	 * "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
	 * +
	 * "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
	 * + "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
	 * Thread.sleep(5000);
	 *
	 * // Use jQuery to add jquery-growl to the page js.executeScript(
	 * "$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')"
	 * );
	 *
	 * // Use jQuery to add jquery-growl styles to the page
	 * js.executeScript("$('head').append('<link rel=\"stylesheet\" " +
	 * "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " +
	 * "type=\"text/css\" />');"); Thread.sleep(5000);
	 *
	 * // jquery-growl w/ no frills
	 * js.executeScript("$.growl({ title: 'GET', message: '/' });"); //'"+color+"'"
	 * if (messageType.equals("error")) {
	 * js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });"
	 * ); }else if(messageType.equals("info")){ js.
	 * executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });"
	 * ); }else if(messageType.equals("warning")){ js.
	 * executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });"
	 * ); }else System.out.println("no error message"); // jquery-growl w/ colorized
	 * output // js.
	 * executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });"
	 * ); // js.
	 * executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });"
	 * ); // js.
	 * executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });"
	 * ); Thread.sleep(5000); }
	 */
	public static void scrollPageToElement(WebElement element) {

		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollPageToElementAndClick(WebElement element, String elemName) {
		reportLog("Clicking on " + elemName);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		threadMethod(2000);
		js.executeScript("arguments[0].click();", element);
		reportLog("Clicked on " + elemName);
	}

	public static void scrollPageToLocation(String x, String y) {
		js.executeScript("window.scrollTo("+x+","+y+");");
	}

	// Get the current time(formatted)
	public static String getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MMM_yyyy_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String currTime = dtf.format(now);
		return currTime;
	}

	public static WebDriverWait setExplicitWait(int seconds) {
		wait = new WebDriverWait(getdriver(), Duration.ofMillis(seconds));
		return wait;
	}

	/*
	 * public static String closeAllSubTabs() method specification :-
	 * 
	 * Closes all windows or tabs Keeping default/main window
	 * 
	 */

	public static void closeAllSubTabs() throws InterruptedException {

		reportLog("Closing all sub tabs");
		defaultWindow = getdriver().getWindowHandle();
		Set<String> allWindows = getdriver().getWindowHandles();

		// Use Iterator to iterate over windows
		Iterator<String> windowIterator = allWindows.iterator();

		// Verify next window is available
		while (windowIterator.hasNext()) {
			// Store the Recruiter window id
			String childWindow = windowIterator.next();
			// Here we will compare if parent window is not equal to child window
			if (!defaultWindow.equals(childWindow)) {
				getdriver().switchTo().window(childWindow);
				getdriver().close();
			}
		}
		getdriver().switchTo().window(defaultWindow);
	}

	/*
	 *
	 * public static String input(WebElement element, String elemName, String Value)
	 * method specification :-
	 *
	 * 1) Inputs/sends value 2) element -> Locator derived from FindBy in Page 3)
	 * elemName -> the name of the web element where we intend to input/send values
	 * 4) Value -> the string value which we intend to input/send 5)
	 * waitForElementToLoad(element) -> waits for web element to load 6)
	 * element.sendKeys(Value) -> inputs/sends the value to the intended web element
	 *
	 * @param : WebElement for the input-box, name of the web element, value to be
	 * inputed *
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String input(WebElement element, String elemName, String value) {
		reportLog("Sending Values in : " + elemName);
		// Wait for the input box to appear on the page
		waitForElementToLoad(element, elemName);
		// Highlight the input box
		highlightElement(element);
		// Send values to the input box
		element.sendKeys(value);
		// Log result
		reportLog("Inputted '" + value + "' text into : " + elemName);
		return value;
	}

	/*
	 *
	 * public static String clearAndInput(WebElement element, String elemName,
	 * String Value) method specification :-
	 *
	 * 1) Clears and Inputs/sends value 2) element -> Locator derived from FindBy in
	 * Page 3) elemName -> the name of the web element where we intend to input/send
	 * values 4) Value -> the string value which we intend to input/send 5)
	 * waitForElementToLoad(element) -> waits for web element to load
	 * 5)element.clear(); -> Clear the input field 6)element.sendKeys(Value) ->
	 * inputs/sends the value to the intended web element
	 *
	 * @param : WebElement for the input-box, name of the web element, value to be
	 * inputed *
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String clearAndInput(WebElement element, String elemName, String Value) {
		reportLog("Sending Values in : " + elemName);
		// Wait for the input box to appear on the page
		waitForElementToLoad(element, elemName);
		// Highlight the input box
		highlightElement(element);
		// Clear input field
		element.clear();
		reportLog("Cleared : " + elemName);
		// Send values to the input box
		element.sendKeys(Value);
		// Log result
		reportLog("Inputted '" + Value + "' text into : " + elemName);
		return "Pass : Inputted '" + Value + "' text into : " + elemName;
	}

	/*
	 * public static void waitForElementToLoad(final WebElement element) method
	 * specification :- 1) Waits for the web element to appear on the page 2)
	 * Wait<WebDriver> wait = TestUtil.setExplicitWait(90);-> Waits for 90 seconds
	 * 3) wait.until((ExpectedCondition<Boolean>) -> Wait until expected condition
	 * (All documents present on the page get ready) met
	 *
	 * @param : WebElement to be waited *
	 *
	 * @return : void
	 */
	public static void waitForElementToLoad(final WebElement element, String elementName) {
		reportLog("Waiting for web element to load on the page : " + elementName);
		// Wait until the element is located on the page
		@SuppressWarnings("unused")
		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * @param Webelement  element
	 * @param elementName
	 * @return Boolean true when elements is not visible anymore
	 */
	public static Boolean waitForElementToDisappear(final WebElement element, String elementName) {
		reportLog("Waiting for web element to disappear from the page : " + elementName);
		Boolean invisbleFlag = wait.until(ExpectedConditions.invisibilityOf(element));
		return invisbleFlag;
	}

	public static void waitForElementToBeClickable(final WebElement element, String elementName) {
		reportLog("Waiting for web element to be clickable : " + elementName);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static ExpectedCondition<Boolean> waitForAjaxCalls() {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
						"return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)")
						.toString());
			}
		};
	}

	// highlight the element on which action will be performed
	public static void highlightElement(WebElement element) {
		for (int i = 0; i < 3; i++) {
			JavascriptExecutor js = (JavascriptExecutor) getdriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"outline: 2px solid rgb(255, 0, 0);");
		}
	}

	//	/*
	//	 * public static ExpectedCondition<WebElement> visibilityOfElementLocated(final
	//	 * WebElement element) method specification :-
	//	 *
	//	 * 1) Waits for the web element to appear on the page 2) WebElement
	//	 * toReturn.isDisplayed() -> Returns true if displayed on the page, else returns
	//	 * false *
	//	 *
	//	 * @param : Web element
	//	 *
	//	 * @return : ExpectedCondition about the web element
	//	 */
	//	public static ExpectedCondition<WebElement> visibilityOfElementLocated(final WebElement element) {
	//		return new ExpectedCondition<WebElement>() {
	//			@Override
	//			public WebElement apply(WebDriver driver) {
	//				// Store the web element
	//				WebElement toReturn = element;
	//				// Check whether the web element is displayed on the page
	//				if (toReturn.isDisplayed())
	//					return toReturn;
	//				return null;
	//			}
	//		};
	//	}

	/*
	 * public static String clickElementwithJS(WebElement element , String elemName)
	 * method specification :-
	 * Using JavaScript Executor
	 * 1) Clicks on a web element 2) element -> element by id, x-path, name,etc. 3)
	 * elemName -> the name of the element which we intend to click 4)
	 * waitForElementToLoad(element) -> waits for element to load 5) element.click()
	 * -> clicks on the intended element
	 *
	 * @param : Locator for the link, name of the web element
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String clickElementwithJS(WebElement element, String elemName) {
		reportLog("Clicking on : " + elemName);
		// Wait for link to appear on the page
		waitForElementToLoad(element, elemName);
		// Highlight link
		highlightElement(element);

		// Click on the link
		js.executeScript("arguments[0].click();", element);

		// Log result
		reportLog("Clicked on : " + elemName);
		return "Pass : Clicked on : " + elemName;
	}

	/*
	 * public static String clickElement(WebElement element , String elemName)
	 * method specification :-
	 *
	 * 1) Clicks on a web element 2) element -> element by id, x-path, name,etc. 3)
	 * elemName -> the name of the element which we intend to click 4)
	 * waitForElementToLoad(element) -> waits for element to load 5) element.click()
	 * -> clicks on the intended element
	 *
	 * @param : Locator for the link, name of the web element
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String clickElement(WebElement element, String elemName) {
		reportLog("Clicking on : " + elemName);
		// Wait for link to appear on the page
		waitForElementToLoad(element, elemName);
		// Highlight link
		highlightElement(element);

		// Click on the link
		element.click();

		// Log result
		reportLog("Clicked on : " + elemName);
		return "Pass : Clicked on : " + elemName;
	}
	public static String clickElement2(WebElement element, String elemName) {
		reportLog("Clicking on : " + elemName);
		// Wait for link to appear on the page
		waitForElementToLoad(element, elemName);
		// Highlight link
		//		highlightElement(element);

		// Click on the link
		element.click();

		// Log result
		reportLog("Clicked on : " + elemName);
		return "Pass : Clicked on : " + elemName;
	}

	/*
	 * public static void waitForPageToLoad() method specification :-
	 *
	 * 1) Waits for a new page to load completely 2)
	 * wait.until((ExpectedCondition<Boolean>) -> Wait until expected condition (All
	 * documents present on the page get ready) met
	 *
	 * @param : no parameters passed
	 *
	 * @return : void
	 */
	public static void waitForPageToLoad() {
		for (int i = 0; i < 120; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("Page has not loaded yet ");
			}
			// again check page state
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page has loaded");
				break;
			}
		}
	}

	/*
	 * public static void uncheckCheckBox(WebElement element, String elemName)
	 * method specification :-
	 *
	 * 1) Checks a check-box if not checked already 2) locator -> to locate the
	 * element by id,x-path,name,etc. 3) elemName -> the name/type of the check-box
	 * which we intend to check 4)
	 * getdriver().findElement(locator).getAttribute("value") == "on" -> is used to
	 * verify whether the intended checkbox is earlier checked or not 5)
	 * getdriver().findElement(locator).click() -> checks the check-box
	 *
	 * @param : Locator for the Check-box, name of the web element
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String uncheckCheckBox(WebElement element, String elemName) {
		reportLog("Unchecking the checkbox : " + elemName);
		// Highlight check-box
		highlightElement(element);
		// Wait for check-box to appear on the page
		waitForElementToLoad(element, elemName);
		// UnCheck check-box if already checked
		if (element.isSelected()) {
			element.click();
		}
		// Log the result
		reportLog("Unchecked '" + elemName + "'");
		return "Pass : Unchecked '" + elemName + "'";
	}

	/*
	 * public static String verifyTextPresentInPageSource(String expText) method
	 * specification :-
	 *
	 * 1) Verifies text present in the page source 2) expText -> Expected text to be
	 * verified from page source 3)
	 * Assert.assertTrue(getdriver().getPageSource().contains(expText)) -> verifies
	 * whether the expected text exist in the page source or not
	 *
	 * @param : Expected text to verify
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String verifyTextPresentInPageSource(String expText) {
		reportLog("Verifying Text : '" + expText + "' " + "present in the Page Source");
		// Verify page source contains expected text
		Assert.assertTrue(getdriver().getPageSource().contains(expText));
		// Log result
		reportLog("'" + expText + "' present in the Page Source");
		return "Pass : '" + expText + "' present in the Page Source";
	}


	public static boolean verifyTextInPageSource(String text) {
		return getdriver().getPageSource().contains(text);
	}


	/*
	 * public static String clearField(WebElement element, String elemName) method
	 * specification :-
	 *
	 * 1) Clears a text field 2) locator -> identify the text field by
	 * id,x-path,name,etc. 3) elemName -> the name of the text-field which we intend
	 * to clear 4) waitForElementToLoad(locator) -> waits for text-field to load 5)
	 * getdriver().findElement(locator).clear() -> clears the intended text-field
	 *
	 * @param : Locator for the input-box, name of the web element
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String clearField(WebElement element, String elemName) {
		reportLog("Clearing field : " + elemName);
		// Wait for the input-box to load on the page
		waitForElementToLoad(element, elemName);
		// Highlight the input-box
		highlightElement(element);
		// Clear the input-box
		element.clear();
		// Log result
		reportLog("Cleared : " + elemName);
		return "Pass : Cleared : " + elemName;
	}



	/*
	 * public static String assertText(String elemName,String actValue, String
	 * expValue) method specification :-
	 *
	 * 1) Verifies and returns TRUE if expected and actual text match 2) elemName ->
	 * the name/type of text we intend to compare 3) actValue -> the actual string
	 * value which is shown in the application 4) expValue -> the expected string
	 * value which should be shown in the application 5)
	 * Assert.assertEquals(expValue.trim(), actValue.trim())) -> trims and compares
	 * the actual and expected string value
	 *
	 * @param : Name of the web element, Actual text and expected text
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String assertText(String elemName, String actValue, String expValue) {
		reportLog("Asserting  Text  where : ExpectedText = " + expValue + "  and ActualText = " + actValue);
		// Assert that expected value matches with actual value
		Assert.assertEquals(expValue.trim(), actValue.trim());
		// Log result
		reportLog("Successfully asserted text for : " + elemName + " where Expected text is '" + expValue
				+ "' and Actual text is '" + actValue + "'");
		return "Pass : Expected text matches with actual text";
	}

	/*
	 * public static Boolean isElementPresent(WebElement element,String elemName)
	 * method specification :-
	 * 
	 * 1) Check whether an element present or not on the webpage 2)
	 * driver.findElement(Locator).isDisplayed() -> Return true/false based on
	 * whether element is displayed or not on the page
	 * 
	 * @param : Locator for the web element, Name of the web element
	 * 
	 * @return : True/false based on whether element is displayed on the page or not
	 */

	public static Boolean isElementPresent(WebElement element, String elemName) {

		reportLog("Verifying whether Element : " + elemName + " is displayed");
		Boolean present = null;

		try {

			// Verify whether element is displayed on the page or not
			present = element.isDisplayed();

		}

		catch (Throwable isElementPresentException) {

			// Log error
			reportLog("Error while verifying - " + elemName + " element displayed : "
					+ isElementPresentException.getMessage());

			return false;
		}
		return present;

	}

	/*
	 * public static String retrieveText(WebElement element, String elemName) method
	 * specification :-
	 *
	 * 1) Return retrieved text from webpage 2)
	 * getdriver().findElement(locator).getText() -> Retrieves text from the web
	 * element targeted by specified locator
	 *
	 * @param : Locator for the web element, Name of the web element
	 *
	 * @return : Text retrieved from the webpage
	 */
	public static String retrieveText(WebElement element, String elemName) {
		String retrievedText = null;
		reportLog("Retrieving Text from : " + elemName);
		// Wait for web element to load on the page
		waitForElementToLoad(element, elemName);
		// Highlight the web element
		highlightElement(element);
		// Retrieve text from web element
		retrievedText = element.getText().trim();
		// Log result
		reportLog("Retrieved text : " + retrievedText);
		return retrievedText;
	}

	/*
	 * public static String retrieveAttributeValue(WebElement element,String
	 * value,String elemName) method specification :-
	 *
	 * 1) Return retrieved HTML attribute value from webpage 2)
	 * getdriver().findElement(locator).getAttribute(value) -> Retrieves attribute
	 * (present under a web element) value
	 *
	 * @param : Locator for the web element, Attribute name, Name of the web element
	 *
	 * @return : Attribute value retrieved
	 */
	public static String retrieveAttributeValue(WebElement element, String value, String elemName) {
		String attributeValue = null;
		reportLog("Getting Attribute '" + value + "'  Value from : " + elemName);
		// Wait for web element to load
		waitForElementToLoad(element, elemName);
		// Highlight the web element
		highlightElement(element);
		// Get attribute value for the web element
		attributeValue = element.getAttribute(value);
		// Log result
		reportLog("Got Attribute '" + value + "'  Value from : " + elemName);
		return attributeValue;
	}
	public static String retrieveAttributeValue2(WebElement element, String value, String elemName) {
		String attributeValue = null;
		reportLog("Getting Attribute '" + value + "'  Value from : " + elemName);
		// Wait for web element to load
		waitForElementToLoad(element, elemName);
		// Highlight the web element
		//		highlightElement(element);
		// Get attribute value for the web element
		attributeValue = element.getAttribute(value);
		// Log result
		reportLog("Got Attribute '" + value + "'  Value from : " + elemName);
		return attributeValue;
	}
	public static String retrieveCSSValue(WebElement element, String value, String elemName) {
		String CSSValue = null;
		reportLog("Getting Attribute '" + value + "'  Value from : " + elemName);
		// Wait for web element to load
		waitForElementToLoad(element, elemName);
		// Highlight the web element
		highlightElement(element);
		// Get attribute value for the web element
		CSSValue = element.getCssValue(value);
		// Log result
		reportLog("Got CSS '" + value + "'  Value from : " + elemName);
		return CSSValue;
	}												  				 

	/*
	 * public static String acceptAlert(String elemName) method specification :-
	 *
	 * 1) Accepts an alert 2) getdriver().switchTo().alert() -> Switch to the
	 * desired alert 3) alert.accept() -> Accepts the alert
	 *
	 * @param : Name of the web element
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String acceptAlert(String elemName) {
		waitAlertToLoad(elemName);
		reportLog("Accepting alert : " + elemName);
		// Create a new alert object
		Alert alert = getdriver().switchTo().alert();
		// Accept the alert
		alert.accept();
		// Log result
		reportLog("Accepted alert : " + elemName);
		return "Pass : Accepted the alert '" + elemName + "'";
	}

	/*
	 * public static void clickAndWaitForPageLoad(WebElement element, String
	 * elemName) method specification :-
	 *
	 * 1) Click and wait for next page to load 2)
	 * getdriver().findElement(locator).click() -> Clicks on the web element
	 * targeted by locator 3) getdriver().navigate().refresh() -> Refresh the page
	 *
	 * @param : Locator to locate the web element, Name of the web element
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static String clickAndWaitForPageLoad(WebElement element, String elemName) {
		// Highlight the web element
		highlightElement(element);
		// Click on the web element targeted WebElement element
		clickElement(element, elemName);
		// Wait for new page to load
		waitForPageToLoad();
		// Log result
		reportLog("Clicked on the element : '" + elemName + " and new page loaded with title : "
				+ getdriver().getTitle());
		return "Pass : Clicked on the element : '" + elemName + " and new page loaded with title : "
		+ getdriver().getTitle();
	}

	/*
	 * public static void refreshPage() method specification : -
	 *
	 * 1) Refresh the page 2) getdriver().navigate().refresh : This is used to
	 * refresh the current page
	 */
	public static void refreshPage() {
		reportLog("Executing : refreshPage() method");
		reportLog("Refreshing page");
		getdriver().navigate().refresh();
		reportLog("Page successfully refreshed");
	}

	/**
	 * public static String waitAlertToLoad(String alertName) :-
	 *
	 * Waits for alert to load
	 *
	 * @param alertName : Name of alert
	 * @return : Pass/Fail
	 */
	public static String waitAlertToLoad(String alertName) {
		reportLog("Waiting for Alert :" + alertName);
		wait.until(ExpectedConditions.alertIsPresent());
		return "Pass: Alert shown";
	}

	/**
	 * public static String rightClick(WebElement element, String eleName) method
	 * specification:-
	 *
	 * Right clicks on Element
	 *
	 * @param locator : Takes locator as xpath,ID,Class etc.
	 * @param eleName : Name of element
	 * @return : Pass and fail
	 */
	public static String rightClick(WebElement element, String eleName) {
		reportLog(" Opening context menu of element " + eleName);
		// Wait for locator to appear on the page
		waitForElementToLoad(element, eleName);
		// Highlight locator
		highlightElement(element);
		// Create a new action object
		Actions action = new Actions(getdriver());
		// Opening context menu
		action.contextClick(element).build().perform();
		reportLog("Right clicked on element : " + eleName);
		return "Pass: Right clicked on element : " + eleName;
	}

	public static String clickclearFieldUsingCRTL_A_BackSpace(WebElement element, String elemName) {
		reportLog("Clearing field : " + elemName);
		// Wait for the input-box to load on the page
		waitForElementToLoad(element, elemName);
		// Highlight the input-box
		highlightElement(element);
		// Create a new action object
		Actions action = new Actions(getdriver());
		// Clear the input-box
		action.click(element).keyDown(Keys.CONTROL) .sendKeys("a") .keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
		// Log result
		reportLog("Cleared : " + elemName);
		return "Pass : Cleared : " + elemName;
	}
	
	public static String copyData(WebElement element, String elemName) {
		reportLog("Copying data from field : " + elemName);
		// Wait for the input-box to load on the page
		waitForElementToLoad(element, elemName);
		// Highlight the input-box
		highlightElement(element);
		// Create a new action object
		Actions action = new Actions(getdriver());
		// Clear the input-box
		action.click(element).keyDown(Keys.CONTROL) .sendKeys("a","c").keyUp(Keys.CONTROL).build().perform();
		// Log result
		reportLog("Copied data from : " + elemName);
		return "Pass : Copied data from : " + elemName;
	}
	
	public static String click_and_PasteData(WebElement element, String elemName) {
		reportLog("Pasting data to field : " + elemName);
		// Wait for the input-box to load on the page
		waitForElementToLoad(element, elemName);
		// Highlight the input-box
		highlightElement(element);
		// Create a new action object
		Actions action = new Actions(getdriver());
		// Clear the input-box
		action.click(element).keyDown(Keys.CONTROL) .sendKeys("a","v").keyUp(Keys.CONTROL).build().perform();
		// Log result
		reportLog("Pasted data to : " + elemName);
		return "Pass : Pasted data to : " + elemName;
	}
	

	/*
	 * public static void isChecked(WebElement element, String elemName) method
	 * specification :-
	 *
	 * 1) Verifies whether a Checkbox is checked or not 2) locator -> to locate the
	 * element by id,x-path,name,etc. 3) elemName -> the name/type of the check-box
	 * which we intend to check 4) getdriver().findElement(locator).isSelected() ->
	 * is to verify whether the intended checkbox is checked or not
	 *
	 * @param : Locator for the Check-box, name of the web element
	 *
	 * @return : Result of execution - Pass or fail (with cause)
	 */
	public static boolean isChecked(WebElement element, String elemName) {
		reportLog("Verifying is the checkbox checked : " + elemName);
		boolean result = false;
		// Highlight check-box
		highlightElement(element);
		// Wait for check-box to appear on the page
		waitForElementToLoad(element, elemName);
		// Verify whether check-box if already checked
		if (element.isSelected()) {
			// Log the result
			reportLog("Is checked '" + elemName + "'");
			result = true;
		} else {
			// Log the result
			reportLog("Is not checked '" + elemName + "'");
			result = false;
		}
		return result;
	}

	/**
	 * String assertText(String expectedString, String actualString)
	 *
	 * @param expectedString
	 * @param actualString
	 * @return
	 */
	public static void assertText(String expectedString, String actualString) {
		reportLog("Asserting  Text  where : ExpectedText = " + expectedString + "  and ActualText = " + actualString);
		Assert.assertEquals(expectedString.trim(), actualString.trim());
		reportLog("Success : ExpectedText = " + expectedString + "  and ActualText = " + actualString
				+ " and both are same");
	}

	/*
	 *
	 * public static void maximizeWindow() method specification : -
	 *
	 * 1) Maximize the currently opened browser window 2)
	 * getdriver().manage().window().maximize() : Maximize browser window
	 */
	public static void maximizeWindow() {
		reportLog("Executing : maximizeWindow() method");
		reportLog("Maximizing Browser window");
		getdriver().manage().window().maximize();
		reportLog("Browser window successfully maximized");
	}

	/*
	 *
	 * public static Boolean verifyPartialText(String elemName, String
	 * expValue,String actValue) method specification :
	 *
	 * 1) This method is for verifying presence of a sub-string in between a larger
	 * string 2) String elemName : Passed as a parameter for naming the element 3)
	 * String expValue : Passed as a parameter for storing the expected value 4)
	 * String actValue : Passed as a parameter for storing the actual value 5)
	 * Boolean check = actValue.trim().contains(expValue.trim()) : Checks if actual
	 * text contains the expected text
	 */
	public static Boolean verifyPartialText(String elemName, String expValue, String actValue) {
		reportLog("Verifying Partial Text - '" + expValue + "' for : " + elemName);
		return actValue.trim().contains(expValue.trim());
	}

	/*
	 * public static void checkCheckBox(WebElement element, String elemName) method
	 * specification :
	 *
	 * 1) Checks a check-box if it is not checked already 2) if
	 * (!getdriver().findElement(locator).isSelected()) {
	 * getdriver().findElement(locator).click() : Checks the checkbox if it is not
	 * checked already 3) String elemName : Passed as a parameter to name the
	 * element
	 */
	public static void checkCheckBox(WebElement element, String elemName) {
		reportLog("Clicking on: " + elemName);
		waitForElementToLoad(element, elemName);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 *
	 * @param element
	 * @param elemName
	 * @return
	 */
	public static Boolean isEnabled(WebElement element, String elemName) {
		reportLog("Is Element enabled : " + elemName);
		waitForElementToLoad(element, elemName);
		highlightElement(element);
		Boolean present = element.isEnabled();
		return present;
	}

	/*
	 * public static String assertTitle(String expectedTitle) method specification
	 * :-
	 *
	 * 1) Asserts page title 2) getdriver().getTitle() -> Retrieves page title 3)
	 * Assert.assertEquals() -> Asserts for equality
	 *
	 * @param : Expected title to assert
	 *
	 * @return : void
	 */
	public static void assertTitle(String expectedTitle) {
		String actualTitle = null;
		reportLog("Asserting  title  where : Expected title = " + expectedTitle);
		// Fetch actual title of the webpage
		actualTitle = getdriver().getTitle();
		// Asserts whether actual title matches with expected one
		Assert.assertEquals(expectedTitle.trim(), actualTitle.trim());
		// Log result
		reportLog("Actual title = " + actualTitle + " and matches with Expected title = " + expectedTitle);
	}

	/*
	 * public static void waitForNewWindow(int prevWndCount) method specification :-
	 *
	 * 1) Waits for a new window to appear 2) new WebDriverWait(driver, 60) -> Waits
	 * for 60 seconds 3) wait.until((ExpectedCondition<Boolean>) -> Wait until
	 * expected condition (Window count increases) met 4)
	 * d.getWindowHandles().size() -> Returns number of window handles present
	 *
	 * @param : Previous window count
	 *
	 * @return : void
	 */
	public static void waitForNewWindow(int prevWndCount) {
		final int currWndCount = prevWndCount;
		// Wait until expected condition (Window count increases) met
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				// Return true if window count increases, else return false
				return d.getWindowHandles().size() > currWndCount;
			}
		});
	}

	/*
	 * public static String selectValueByVisibleText(WebElement element, String
	 * Option, String elemName) method specification :-
	 *
	 * 1) Select value from drop-down by visible text 2) Select -> This is a
	 * in-built class in Selenium which is used to represent a drop-down 3)
	 * select.selectByVisibleText(Value) -> Select by visible text
	 *
	 * @param : Locator for the drop-down field, Option to be selected, Name of the
	 * web element
	 *
	 * @return :void
	 */
	public static String selectValueByVisibleText(WebElement element, String Option, String elemName) {
		reportLog("Selecting '" + Option + "' from : " + elemName);
		// Wait for drop-down element to load on the page
		waitForElementToLoad(element, elemName);
		// Highlight the drop-down
		highlightElement(element);
		// Locate drop-down field
		Select select = new Select(element);
		// Select value from drop-down
		select.selectByVisibleText(Option);
		// Log result
		reportLog("Selected '" + Option + "' from : " + elemName);
		return Option;
	}

	/*
	 * public static String switchToNewWindow() method specification :-
	 *
	 * 1) Switches to pop-up window 2) getdriver().getWindowHandle() -> Returns
	 * current window handle 3) getdriver().getWindowHandles() -> Returns all the
	 * available window handles 4) getdriver().switchTo().window(popUpWindowHandle)
	 * -> Switches to pop-up window
	 *
	 * @param : no parameters
	 *
	 * @return - void
	 */
	public static void switchToNewWindow() throws InterruptedException {
		reportLog("Executing switchToNewWindow");
		String popUpWindowHandle = null;
		// Save current window handle for future reference
		String defaultWindow = getdriver().getWindowHandle();
		// Get all the window handles one by one
		for (String windowHandle : getdriver().getWindowHandles()) {
			// Save new window handle
			if (!windowHandle.equals(defaultWindow)) {
				popUpWindowHandle = windowHandle;
			}
		}
		// Switches to pop-up window
		getdriver().switchTo().window(popUpWindowHandle);
		// Log result
		reportLog("Switched to new window");
	}

	/**
	 * 
	 * @return - Current URL
	 */
	public static String getURL() {
		reportLog("Fetching Current URL");
		TestUtil.waitForPageToLoad();
		reportLog("Current URL is : " + getdriver().getCurrentUrl());
		return getdriver().getCurrentUrl();
	}

	/**
	 * This function will prepare a dynamic xpath by substituting "xxxxx" with
	 * actual data
	 * 
	 * @param xpathValue
	 * @param substitutionValue
	 * @return Webelement
	 */
	public static WebElement prepareWebElementWithDynamicXpath(String xpathValue, String substitutionValue) {
		try {
			return getdriver().findElement(By.xpath(xpathValue.replace("xxxxx", substitutionValue)));
		} catch (NoSuchElementException e){
			reportLog("element not present");
			return null;
		}

	}

	public static List<WebElement> prepareWebElementsWithDynamicXpath(String xpathValue, String substitutionValue) {
		try {
			return getdriver().findElements(By.xpath(xpathValue.replace("xxxxx", substitutionValue)));
		} catch (NoSuchElementException e){
			reportLog("element not present");
			return null;
		}
	}


	public static List<WebElement> prepareWebElemenstWithDynamicXpath(String xpathValue, String substitutionValue) {

		return getdriver().findElements(By.xpath(xpathValue.replace("xxxxx", substitutionValue)));
	}																									 

	/**
	 * getRandomDOB will generate DOB in between 1901 and 2021
	 * 
	 * @return Random DOB in MM/DD/YYYY format
	 */
	public static String getRandomDOB() {
		GregorianCalendar gc = new GregorianCalendar();

		int year = 1901 + (int) Math.round(Math.random() * (2021 - 1900));

		gc.set(Calendar.YEAR, year);

		int dayOfYear = 01 + (int) Math.round(Math.random() * (01 - gc.getActualMaximum(Calendar.DAY_OF_YEAR)));

		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

		return (gc.get(Calendar.MONTH) + 1) + "/" + gc.get(Calendar.DAY_OF_MONTH) + "/" + gc.get(Calendar.YEAR);
	}

	public static String getRandomDate(Date start, Date end) {
		Date date = new Date(start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime())));

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int dayOfMOnth = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		String dateString;

		if (month < 9) {
			if (dayOfMOnth < 10)
				dateString = "0" + (1 + month) + "/0" + dayOfMOnth + "/" + year;
			else
				dateString = "0" + (1 + month) + "/" + dayOfMOnth + "/" + year;

		} else if (dayOfMOnth < 10) {
			dateString = 1 + month + "/0" + dayOfMOnth + "/" + year;
		} else {
			dateString = 1 + month + "/" + dayOfMOnth + "/" + year;
		}

		return dateString;
	}

	/*
	 * public static void selectValueByIndex(WebElement element, int index, String
	 * elemName) method specification :-
	 * 
	 * 1) Select value from drop-down by index 2) Select -> This is a in-built class
	 * in Selenium which is used to represent a drop-down 3)
	 * select.selectByIndex(index) -> Select by index
	 * 
	 * @param : Webelement for the drop-down field, Index for the option to be
	 * selected, Name of the web element
	 * 
	 * @return
	 */

	public static String selectValueByIndex(WebElement element, int index, String elemName) {

		reportLog("Selecting value from : " + elemName);

		// Wait for drop-down element to load on the page
		waitForElementToLoad(element, elemName);

		// Highlight the drop-down
		TestUtil.highlightElement(element);

		// Locate drop-down field
		Select select = new Select(element);

		// Select value from drop-down
		select.selectByIndex(index);

		String value = select.getFirstSelectedOption().getText();

		// Log result
		reportLog("Selected " + value + " from : " + elemName);

		return value;

	}

	public static boolean compareTwoLists(List<String> list1, List<String> list2){
		Collections.sort(list1);
		Collections.sort(list2);
		return list1.containsAll(list2);
	}

	/**
	 * waits for loader to disappear, in case of StaleElementReferenceException
	 * webelement is declared again and it will do 3 attempts
	 * 
	 * @return Boolean true when loader is not visible anymore
	 */
	public static boolean waitForLoaderToDisappear() {
		reportLog("Waiting for application loader to disappear");
		boolean invisbleFlag = false;
		int attempt = 1;

		while (attempt <= 3) {
			getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			try {
				WebElement applicationLoaderWebElement = getdriver()
						.findElement(By.xpath("//*[contains(@class,'loader-overlay')]"));
				invisbleFlag = wait.until(invisibilityOf(applicationLoaderWebElement));
				break;
			} catch (NoSuchElementException e) {
				invisbleFlag = true;
				reportLog("loader is not present");
				break;
			} catch (StaleElementReferenceException e) {
				invisbleFlag = false;
				reportLog("Stale Element Reference Exception caught, attemptNo: " + attempt);
			}
			finally {
				getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
			}
			attempt++;
		}
		return invisbleFlag;
	}

	public static ExpectedCondition<Boolean> invisibilityOf(final WebElement element) {
		return new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver webDriver) {
				return !element.isDisplayed();
			}

			@Override
			public String toString() {
				return "invisibility of " + element;
			}
		};
	}

	public static void hoverOver(WebElement element) {
		// Create a new action object
		Actions action = new Actions(getdriver());
		// Opening context menu
		action.moveToElement(element).build().perform();
	}
	public static String getTodaysDate() {
		LocalDate dateObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String date = dateObj.format(formatter);
		return date;
	}

	public static int getTabCount() {
		Set<String> handles = getdriver().getWindowHandles();
		return handles.size();
	}

	public static void executeJSToMakeElementInteractableViaSelenium(WebElement element) {
		js.executeScript("arguments[0].style.height='1px'; arguments[0].style.width='1px'; arguments[0].style.opacity='1';", element);
	}

	public static String calculateAge(String dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(dob, formatter);
		LocalDate currentDate = LocalDate.now();
		Period age = Period.between(birthDate, currentDate);
		return String.valueOf(age.getYears());
	}

	public static String getFormattedCurrentTimestamp(String format, String timeZone) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		return dateFormat.format(new Date());
	}


	public static void openNewWindowAndSwitch(){
		reportLog("Opening a new tab and switching control to it");
		getdriver().switchTo().newWindow(WindowType.TAB);
		reportLog("Switched to new tab and switched control");
	}

	public static void switchToSpecificTab(int tabNumber){
		reportLog("Switching to tab number "+ tabNumber);
		ArrayList<String> newTb = new ArrayList(getdriver().getWindowHandles());
		//switch to a specific tab
		getdriver().switchTo().window(newTb.get(tabNumber));
		reportLog("Switched to tab number "+ tabNumber);
	}

	/** 
	 * @param: dropdown Webelement, should be for a select element.
	 * @return list of available option for a drop-down element
	 */
	public static List<String> getOptionsFromDropdown(WebElement element, String elementName) {
		List<String> values = new ArrayList<String>();
		Select dropdown = new Select(element);
		reportLog(elementName + " dropdown contains: ");
		for (WebElement option: dropdown.getOptions()) {
			String value = option.getText();
			values.add(value);
			reportLog(value);
		}
		return values;
	}


}
