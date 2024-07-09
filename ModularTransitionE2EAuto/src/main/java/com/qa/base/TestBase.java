package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v106.network.Network;
import org.openqa.selenium.devtools.v106.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TestBase {

	// A static member is a member of a class that isn't associated with an instance
	// of a class. Instead, the member belongs to the class itself.

	
	public static WebDriver driver; // Initialize webdriver with static variable. This is set to private because I
									// am not using this anywhere outside this class
	public static DesiredCapabilities capabilities;
	public static Properties prop; // Public class variable for properties. This is a global variable(public
									// static).I can use inside TestBase.java class and all other child classes also
	@SuppressWarnings("deprecation")
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String browserName1;
	public static String browserVersion1;
	private static String propertiesFileName;
	public static String env;
	public static JSONObject testDataFile;
	public static JSONObject testDataEnv;
	public static JSONObject testData;
	public static String browserName = null;
	public static String seleniumGridURL;
	public static String saucelabGridURL;
	public static String saucelabuser;
	public static String saucelabAccessKey;
	public static String saucelabPass;
	public static String defaultWindow;
	public static String remoteValue;
	public static String saucelabValue;
	public static String jobname;
	public static String saucelabTunnelName;
	public static String suiteXmlName;
	public static String className;
	
	static ChromeOptions options;
	static EdgeOptions edgeOptions;
	static FirefoxOptions ffoptions;

	//public variables for second browser initialization
	public static String defaultWindow2;
	public static WebDriver driver2;
	public static EventFiringWebDriver e_driver2;
	public static WebEventListener eventListener2;
	//soft assert
	public static SoftAssert softAssert = new SoftAssert();
	
	// this will retrive Jenkins build number for reports folder creation.
	// will be null for local run and reports folder will instead have timestamp in foldername
	public static String buildNumber = System.getenv("BUILD_NUMBER");

	// private static final String KEY = "environment";

	// This is the logger class. Generate the logs? : use Apache log4j API (log4j
	// jar)
	public static Logger log = Logger.getLogger(TestBase.class);

	// Method for adding logs passed from test cases. This is for extentTest HTML
	// report and log4j log files as well
	public static void reportLog(String message) {
		log.info(message);
		Reporter.log(message);
	}

	// Actions Class to take actions method.
	// Actions action = new Actions(getdriver());

	// Method to take screenshot at any step you want
	public static void takeScreenshotAtAnyStep(String screenshotPath) {
		Reporter.log(screenshotPath);
	}

	// To make driver thread safe. This will be required for parallel execution from
	// testNG
	private static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>(); // Initialize thread local variable.

	// Getter method ThreadLocal variable dr. This is the driver instance, it will
	// check which thread is calling this for parallel running.
	public static WebDriver getdriver() {
		return dr.get();
	}

	// Setter method. This will set the webdriver reference driverref
	public static void setDriver(WebDriver driverref) {
		dr.set(driverref);
	}

	// For cleanup operation. This will remove any values stored by threadLocal
	// variable. This will be used in afterclass method of each test.
	public static void unload() {
		dr.remove();
	}

	// API request failure logging
	public static void enableNetworkLogging(WebDriver driver){
		try {
			driver = new Augmenter().augment(driver);
			DevTools devTools = ((HasDevTools) driver).getDevTools();
			devTools.createSession();
			devTools.send(Network.enable(Optional.empty(),Optional.empty(), Optional.empty()));
			/*devTools.addListener((Network.requestWillBeSent()),request->{
				Request req = request.getRequest();
				reportLog(req.getUrl());
				reportLog(req.getHeaders().toString());
			});*/
			devTools.addListener(Network.responseReceived(),response ->{
				Response res= response.getResponse();
				String statusCode = res.getStatus().toString();
				String requestUrl = res.getUrl();
				if(requestUrl.contains("curaspan")){
				if(statusCode.startsWith("4") || statusCode.startsWith("5")) {
					reportLog("[API FAILURE] Failed API Request: "+res.getUrl() + " FAILED WITH STATUS CODE "+ statusCode);
					Assert.assertTrue(statusCode.startsWith("2")||statusCode.startsWith("3"),"[API FAILURE] Failed API Request: "+res.getUrl() + " FAILED WITH STATUS CODE "+ statusCode);
				}}
			});
		} catch (Exception e) {			
			reportLog(e.getMessage());
		}

	}

	// Initialization method
	@SuppressWarnings("deprecation")
	public static void initialization() throws MalformedURLException 
	{

		// Read the properties file.Based on the parameter value provided for
		// environment, the properties file will load
		try
		{
			try
			{
				suiteXmlName = System.getProperty("xmlSuiteFileName").replaceFirst("src/main/resources/", "").replaceAll(".xml", "");
				reportLog("Test Suite Name: " + suiteXmlName);
			}
			catch (NullPointerException e)
			{
				suiteXmlName = className;
				reportLog("Test Suite Name: " + suiteXmlName);
			}
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
//			remoteValue = System.getProperty("remote") != null ? System.getProperty("remote")
//					: prop.getProperty("remote");
//			reportLog("remoteValue: " + remoteValue);
//			//if (remoteValue.equals("true")) {
//				seleniumGridURL = System.getProperty("seleniumgrid_url") != null
//						? System.getProperty("seleniumgrid_url")
//						: prop.getProperty("seleniumgrid_url");
//				reportLog("seleniumgrid_url: " + seleniumGridURL);
//			//}
//			
//			
//			
//			saucelabuser = System.getProperty("saucelabUser") != null
//					? System.getProperty("saucelabUser")
//					: prop.getProperty("saucelabUser");
//			
//			saucelabAccessKey = System.getProperty("saucelabAccessKey") != null
//					? System.getProperty("saucelabAccessKey")
//					: prop.getProperty("saucelabAccessKey");
//			
//			saucelabPass = System.getProperty("sauclabPass") != null
//					? System.getProperty("sauclabPass")
//					: prop.getProperty("sauclabPass");
//			
//			saucelabTunnelName = System.getProperty("tunnelName") != null
//					? System.getProperty("tunnelName")
//					: prop.getProperty("tunnelName");
//			
//			saucelabGridURL = "https://"+saucelabuser+":"+saucelabAccessKey+"@ondemand.us-west-1.saucelabs.com:443/wd/hub";
//			reportLog("saucelabGridURL: " + saucelabGridURL);
//			
//			reportLog("Jenkins Build Number: " + buildNumber);

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		/*
		 * if (browserName.trim().equalsIgnoreCase("chrome")) {
		 * 
		 * //WebDriverManager.chromedriver().setup(); capabilities = new
		 * DesiredCapabilities();
		 * //capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 * capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		 * capabilities.setCapability("browserName", "chrome");
		 * capabilities.setPlatform(Platform.ANY); options = new ChromeOptions();
		 * options.setAcceptInsecureCerts(true);
		 * options.setExperimentalOption("excludeSwitches",
		 * Collections.singletonList("enable-automation"));
		 * capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		 * //enableNetworkLogging(driver);
		 * 
		 * } else if (browserName.trim().equalsIgnoreCase("edge")) {
		 * 
		 * //WebDriverManager.edgedriver().setup(); capabilities = new
		 * DesiredCapabilities(); capabilities.setCapability("acceptSslCerts", "true");
		 * capabilities.setBrowserName("MicrosoftEdge");
		 * capabilities.setAcceptInsecureCerts(true);
		 * capabilities.setPlatform(Platform.ANY); edgeOptions = new EdgeOptions();
		 * edgeOptions.setAcceptInsecureCerts(true);
		 * edgeOptions.setExperimentalOption("excludeSwitches",
		 * Collections.singletonList("enable-automation"));
		 * capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
		 * 
		 * 
		 * 
		 * } else if (browserName.trim().equalsIgnoreCase("firefox")) {
		 * 
		 * //WebDriverManager.firefoxdriver().setup();
		 *  capabilities = new DesiredCapabilities(); capabilities.setCapability("acceptInsecureCerts",
		 * true); capabilities.setCapability("marionette", true);
		 * capabilities.setPlatform(Platform.ANY); ffoptions = new FirefoxOptions();
		 * ffoptions.setAcceptInsecureCerts(true);
		 * //ffoptions.setExperimentalOption("excludeSwitches",
		 * Collections.singletonList("enable-automation"));
		 * capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, ffoptions);
		 * 
		 * ffoptions.addPreference("webdriver.accept.untrusted.certs", true);
		 * ffoptions.setCapability("marionette", true);
		 * 
		 * }
		 * 
		 * if (prop.getProperty("remote").equals("true")) {
		 * System.out.println("Running on remote"); try { driver = new
		 * RemoteWebDriver(new URL(seleniumGridURL), capabilities);
		 * enableNetworkLogging(driver); } catch (MalformedURLException e) {
		 * e.printStackTrace(); } } else {
		 * 
		 * if (browserName.trim().equalsIgnoreCase("chrome")) { driver = new
		 * ChromeDriver(options); enableNetworkLogging(driver); } else if
		 * (browserName.trim().equalsIgnoreCase("firefox")) driver = new
		 * FirefoxDriver(ffoptions); else if
		 * (browserName.trim().equalsIgnoreCase("edge")) driver = new
		 * EdgeDriver(edgeOptions); }
		 */
		remoteValue = System.getProperty("remote") != null ? System.getProperty("remote")
				: prop.getProperty("remote");
		saucelabValue = System.getProperty("sauceLab") != null ? System.getProperty("sauceLab")
				: prop.getProperty("sauceLab");
		
		if(remoteValue.equals("true"))  {
			if(saucelabValue.equals("true")) {
				
				if (browserName.trim().equalsIgnoreCase("chrome")) {
	                ChromeOptions options = new ChromeOptions();
	                capabilities = new DesiredCapabilities();
	                options.addArguments("--ignore-certificate-errors");
	                options.setPlatformName("Windows 10");
	                options.setBrowserVersion("latest");
	                //Added below to handle ExceptionInitializationError for Ch versions > 110
	                options.addArguments("--remote-allow-origins=*");
	                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	               //options.setCapability(CapabilityType., true);
	                Map<String, Object> sauceOptions = new HashMap<>();
	                sauceOptions.put("username", saucelabuser);
	                sauceOptions.put("accessKey", saucelabAccessKey);
	                sauceOptions.put("tunnelName", saucelabTunnelName);
	                sauceOptions.put("name", suiteXmlName);
	                options.setCapability("sauce:options", sauceOptions); 
	                driver = new RemoteWebDriver(new URL(saucelabGridURL), options);
	                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	                
	            } else if (browserName.trim().equalsIgnoreCase("firefox")) {
	                FirefoxOptions options = new FirefoxOptions();
	                options.addPreference("webdriver.accept.untrusted.certs", true);
	                options.setCapability("marionette", true);
	                options.setPlatformName("Windows 10");
	                options.setBrowserVersion("latest");
	                Map<String, Object> sauceOptions = new HashMap<>();
	                sauceOptions.put("username", saucelabuser);
	                sauceOptions.put("accessKey", saucelabAccessKey);
	                sauceOptions.put("tunnelName", saucelabTunnelName);
					sauceOptions.put("name", suiteXmlName);
	                options.setCapability("sauce:options", sauceOptions);
	                driver = new RemoteWebDriver(new URL(saucelabGridURL), options);
	                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	            } else if (browserName.trim().equalsIgnoreCase("edge")) {
	                EdgeOptions options = new EdgeOptions();
	                options.setCapability("acceptInsecureCerts", true);
	                options.setPlatformName("Windows 10");
	                options.setBrowserVersion("latest");
	                Map<String, Object> sauceOptions = new HashMap<>();
	                sauceOptions.put("username", saucelabuser);
	                sauceOptions.put("accessKey", saucelabAccessKey);
	                sauceOptions.put("tunnelName", saucelabTunnelName);
					sauceOptions.put("name", suiteXmlName);
	                options.setCapability("sauce:options", sauceOptions);
	                driver = new RemoteWebDriver(new URL(saucelabGridURL), options);
	                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	            } else {
	                throw new RuntimeException("Unrecognized browser: " + browserName.trim());
				
			  }
			}else{
			if (browserName.trim().equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-certificate-errors");
              //Added below to handle ExceptionInitializationError for Ch versions > 110
                options.addArguments("--remote-allow-origins=*");
                driver = new RemoteWebDriver(new URL(seleniumGridURL), options);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                enableNetworkLogging(driver);
            } else if (browserName.trim().equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("webdriver.accept.untrusted.certs", true);
                options.setCapability("marionette", true);
                driver = new RemoteWebDriver(new URL(seleniumGridURL), options);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            } else if (browserName.trim().equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.setCapability("acceptInsecureCerts", true);
                driver = new RemoteWebDriver(new URL(seleniumGridURL), options);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            } else {
                throw new RuntimeException("Unrecognized browser: " + browserName.trim());
            }
		  }
		}
		else {
			if (browserName.trim().equalsIgnoreCase("chrome")) {
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("--ignore-certificate-errors");
	          //Added below to handle ExceptionInitializationError for Ch versions > 110
	            options.addArguments("--remote-allow-origins=*");
	            driver = new ChromeDriver(options);
	            enableNetworkLogging(driver);
	        } else if (browserName.trim().equalsIgnoreCase("firefox")) {
	            FirefoxOptions options = new FirefoxOptions();
	            options.addPreference("webdriver.accept.untrusted.certs", true);
	            options.setCapability("marionette", true);
	            driver = new FirefoxDriver(options);
	        } else if (browserName.trim().equalsIgnoreCase("edge")) {
	            EdgeOptions options = new EdgeOptions();
	            options.setCapability("acceptInsecureCerts", true);
	            driver = new EdgeDriver(options);
		}
		}

		setDriver(driver);

		// To get Browser details:
		Capabilities browserCap = ((RemoteWebDriver) getdriver()).getCapabilities();
		browserName1 = browserCap.getBrowserName();
		browserVersion1 = browserCap.getBrowserVersion();
		reportLog(browserName1 + "   " + browserVersion1);

		// To get Application environment currently running

		// Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(KEY);

		// Create EventFiringWebDriver class object
		e_driver = new EventFiringWebDriver(getdriver());
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		// Register the eventListener class object with event firing webdriver object.
		e_driver.register(eventListener);
		// Assign this to the main driver
		// driver = e_driver;
		setDriver(e_driver);

		getdriver().manage().window().maximize();
		getdriver().manage().deleteAllCookies();
		getdriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		defaultWindow = getdriver().getWindowHandle();
	}

}