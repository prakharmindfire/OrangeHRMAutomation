package com.qa.testcases;


import com.qa.base.TestBase;
import com.qa.pages.*;
import com.qa.util.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static com.qa.pages.LiveEventsPage.*;
import static com.qa.util.TestUtil.*;
import static org.testng.Assert.*;

public class LiveEventsSmoke extends TestBase {
    // Declare all pages to be used
    LoginPage loginPageObj;
    DischargeReferPage dischargeReferPageObj;
    DischargeConnectPage dischargeConnectPageObj;
    CommonPage commonPageObj;
    PatientDischargePage patientDischargePageobj;
    LiveEventsPage liveEventsPageObj;


    // declare testdata
    String provider;
    List<String> providerList;
    String homeHealthPOS;
    String homeHealthLOC;
    String escString;
    String mirthURL;
    List<String> homeHealthKeyServices;
    String phonenumber;
    String profileprovider;
    String addressline1text;
    String addressline2text;
    String citytext;
    String countytext;
    String organizationID;
    String metrotext;
    String PhysicianName;
    String payerName;
    String intakeLiveEventUsername;
    String intakeLiveEventsPassword;
    String dischLiveEventUsername;
    String dischLiveEventPassword;

    // shared testdata
    PatientInfoBean referralDetails;
    String referralName;

    @BeforeClass(alwaysRun = true)
    public void intialize() throws MalformedURLException {
        // initialize browser
        initialization();
        initSecondBrowser();
        // initialize testdata JSON object
        String className = this.getClass().getSimpleName();
        testDataFile = JsonReader.getTestDataJSON("ModularSmoke");//utilizing ModularSmoke.json file for test data
      //testDataEnv = testDataFile.getJSONObject(env);
      		testData = testDataFile.getJSONObject(env);
      		//testDataEnv.getJSONObject(browserName);
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        // Initilize page Objects
        loginPageObj = new LoginPage();
        dischargeReferPageObj = new DischargeReferPage();
        dischargeConnectPageObj = new DischargeConnectPage();
        commonPageObj = new CommonPage();
        patientDischargePageobj = new PatientDischargePage();
        liveEventsPageObj = new LiveEventsPage();


        // initialize testdata variables
        intakeLiveEventUsername = testData.getString("intakeLiveEventUsername");
        intakeLiveEventsPassword = testData.getString("intakeLiveEventsPassword");
        dischLiveEventUsername = testData.getString("dischLiveEventUsername");
        dischLiveEventPassword = testData.getString("dischLiveEventPassword");
        organizationID = testData.getString("organizationID");
        provider = testData.getString("provider");
        providerList = convertJsonArrayToList(testData.getJSONArray("providerList"));
        homeHealthPOS = testData.getString("homeHealthPOS");
        homeHealthLOC = testData.getString("homeHealthLOC");
        escString = testData.getString("escString");
        mirthURL = testData.getString("mirthURL");
        homeHealthKeyServices = convertJsonArrayToList(testData.getJSONArray("homeHealthKeyServices"));
        phonenumber = FakeDataProvider.getPhoneNumber();
        profileprovider = testData.getString("profileProvider");
        addressline1text = testData.getString("addressLine1");
        addressline2text = testData.getString("addressLine2");
        citytext = testData.getString("city");
        countytext = testData.getString("county");
        metrotext = testData.getString("metro");
        PhysicianName = FakeDataProvider.getFirstName();
        payerName = FakeDataProvider.getFirstName();
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshotInCaseOfFailure(ITestResult result) throws Exception {
        // Check if the test case failed or was skipped and take screenshot
        if (result.getStatus() == result.FAILURE || result.getStatus() == result.SKIP) {
            if (result.getStatus() == result.FAILURE)
                reportLog("TEST FAILED: " + result.getName());
            else
                reportLog("TEST SKIPPED: " + result.getName());
            String screenshotPath = getScreenshot(getdriver(), result.getName());
            result.setAttribute("screenshotPath", screenshotPath); // sets the value the variable/attribute
            // screenshotPath as the path of the screenshot
        } else if (result.getStatus() == result.SUCCESS) {
            reportLog("TEST PASSED: " + result.getName());
        }
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        reportLog("quitting browser");
        getdriver().quit();
        getdriver2().quit();
        reportLog("clearing threadlocal1");
        unload();
        reportLog("clearing threadlocal2");
        unload2();
    }


    @Test(priority = 0, groups = {"LiveEvents"}, description = "Verify LiveEvents")
    public void LiveEventsSmoke() throws Exception {
        //login to intake on firefox
        liveEventsPageObj.login2(intakeLiveEventUsername, intakeLiveEventsPassword);
        //login to discharge on chrome
        loginPageObj.login(dischLiveEventUsername, dischLiveEventPassword);
        // add patient to workbook
        referralDetails = commonPageObj.createPatientForSmoke(escString, mirthURL);
        referralName = referralDetails.getReferralName();
        // navigate to refer page
        commonPageObj.navigateTo("refer");
        // update packet to home health loc
        commonPageObj.updateReferralPacket(homeHealthPOS, homeHealthLOC);
        //check first patient in intake before sending referral
        String firstPatientBeforeNewReferral = liveEventsPageObj.getFirstPatientNameOnIntake("before");
        dischargeReferPageObj.sendReferralPacket(provider, "ZZ");
        String firstPatientAfterReferral = liveEventsPageObj.getFirstPatientNameOnIntake("after");
        assertNotEquals(firstPatientBeforeNewReferral, firstPatientAfterReferral);
        assertText(referralName, firstPatientAfterReferral);
        reportLog("LIVE EVENT ASSERTED: Referral showed up on intake dashboard: " + referralName);
        // navigate to refer page
        commonPageObj.navigateTo("connect");
        liveEventsPageObj.clickOnPatientOnIntake(referralName);
        //accept the referral from intake
        liveEventsPageObj.updateStatusForHomeHealthProvider2(provider,
                Constants.ACCEPT);
        //verify live event on connect page
        waitForPageToLoad();
        threadMethod(8000);
        String status = dischargeConnectPageObj.getProviderStatus(provider);
        assertText("Accepted", status);
        reportLog("LIVE EVENT ASSERTED: Provider status on connect page: " + status);
    }
}