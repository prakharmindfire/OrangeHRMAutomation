package myar.testcases;

import static com.qa.util.TestUtil.getScreenshot;
import static com.qa.util.TestUtil.refreshPage;
import static com.qa.util.TestUtil.threadMethod;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.util.JsonReader;
import com.qa.util.TestUtil;

import myar.pages.MyarAddTargetPage;
import myar.pages.MyarAdminDashboardPage;
import myar.pages.MyarAdministrationPage;
import myar.pages.MyarButtonElementPage;
import myar.pages.MyarChooseMappingPage;
import myar.pages.MyarDashboardPage;
import myar.pages.MyarEditProfile;
import myar.pages.MyarEditorPage;
import myar.pages.MyarElementsPage;
import myar.pages.MyarExcel_ImportPage;
import myar.pages.MyarGeoProductSetUpPage;
import myar.pages.MyarImportMappingPage;
import myar.pages.MyarImportStatusPage;
import myar.pages.MyarLoginPage;
import myar.pages.MyarManageAPI_KeyPage;
import myar.pages.MyarMediaPage;
import myar.pages.MyarProductSetUpPage;
import myar.pages.MyarProductsPage;
import myar.pages.MyarSceneLibraryPage;
import myar.pages.MyarScenesPage;
import myar.pages.MyarSelectProductTypePage;
import myar.pages.MyarUploadExcelPage;

public class MyarRegression extends TestBase {
	// Declare all pages to be used
	MyarLoginPage myarLoginPageObj;
	MyarDashboardPage myarDashboardPageObj;
	MyarEditProfile myarEditProfileObj;
	MyarManageAPI_KeyPage myarManageAPI_KeyPageObj;
	MyarMediaPage myarMediaPageObj;
	MyarProductsPage myarProductsPageObj;
	MyarAdminDashboardPage myarAdminDashboardPageObj;
	MyarAdministrationPage myarAdministrationPageObj;
	MyarEditorPage myarEditorPageObj;
	MyarExcel_ImportPage myarExcel_ImportPageObj;
	MyarSelectProductTypePage myarSelectProductTypePageObj;
	MyarUploadExcelPage myarUploadExcelPageObj;
	MyarImportMappingPage myarImportMappingPageObj;
	MyarProductSetUpPage myarProductSetUpPageObj;
	MyarImportStatusPage myarImportStatusPageObj;
	TestUtil TestUtilObj;
	MyarScenesPage myarScenesPageObj;
	MyarChooseMappingPage myarChooseMappingPageObj;
	MyarGeoProductSetUpPage myarGeoProductSetUpPageObj;
	MyarAddTargetPage myarAddTargetPageObj;
	MyarButtonElementPage myarButtonElementPageObj;
	MyarSceneLibraryPage myarSceneLibraryPageObj;
	MyarElementsPage myarElementsPageObj;

	// declare testdata
	String lang;
	String clientEmail_Address;
	String clientPWD;
	String AdminUsername;
	String AdminPassword;
	String APIName;
	String newEmailAddress;
	String newFolderNameMedia;
	String renamedFolderMedia;
	String newFolderNameProduct;
	String renamedFolderProduct;
	String colorR;
	String colorG;
	String colorB;
	int R;
	int G;
	int B;
	int R2;
	int G2;
	int B2;
	String RGB_value;
	String now_rgb;
	String ini_rgb;
	String colorH;
	String colorS;
	String colorL;
	int H;
	int S;
	int L;
	int H2;
	int S2;
	int L2;
	String RGB_value2;
	String FileuploaderExe_1885;
	String FilePath_1885;
	String FileuploaderExe_1861;
	String FilePath_1861;
	String FileuploaderExe_1861_1;
	String FilePath_1861_1;
	String adminMainUserGrp;
	int Productlang1;
	int Productlang2;
	int chooseAR_action_URL;
	int selectAR_action_URL;

	@BeforeClass(alwaysRun = true)
	public void intialize() throws MalformedURLException {
		className = this.getClass().getSimpleName();
		// initialize browser
		initialization();
		// initialize testdata JSON object
		testDataFile = JsonReader.getTestDataJSON(className);
		testDataEnv = testDataFile.getJSONObject(env);
		testData = testDataEnv.getJSONObject(browserName);
	}

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		// Initilize page Objects
		myarLoginPageObj = new MyarLoginPage();
		myarDashboardPageObj = new MyarDashboardPage();
		myarEditProfileObj = new MyarEditProfile();
		myarManageAPI_KeyPageObj = new MyarManageAPI_KeyPage();
		myarMediaPageObj = new MyarMediaPage();
		myarProductsPageObj = new MyarProductsPage();
		myarAdminDashboardPageObj = new MyarAdminDashboardPage();
		myarAdministrationPageObj = new MyarAdministrationPage();
		myarEditorPageObj = new MyarEditorPage();
		myarExcel_ImportPageObj = new MyarExcel_ImportPage();
		myarSelectProductTypePageObj = new MyarSelectProductTypePage();
		myarUploadExcelPageObj = new MyarUploadExcelPage();
		myarImportMappingPageObj = new MyarImportMappingPage();
		myarProductSetUpPageObj = new MyarProductSetUpPage();
		myarImportStatusPageObj = new MyarImportStatusPage();
		TestUtilObj = new TestUtil();
		myarScenesPageObj = new MyarScenesPage();
		myarChooseMappingPageObj = new MyarChooseMappingPage();
		myarGeoProductSetUpPageObj = new MyarGeoProductSetUpPage();
		myarAddTargetPageObj = new MyarAddTargetPage();
		myarButtonElementPageObj = new MyarButtonElementPage();
		myarSceneLibraryPageObj = new MyarSceneLibraryPage();
		myarElementsPageObj = new MyarElementsPage();

		// initialize testdata variables
		lang = testDataEnv.getString("language");
		clientEmail_Address = testData.getString("clientEmail_Address");
		clientPWD = testData.getString("clientPWD");
		AdminUsername = testData.getString("AdminUsername");
		AdminPassword = testData.getString("AdminPassword");
		APIName = testDataEnv.getString("API_name");
		newEmailAddress = testDataEnv.getString("newEmailAddress");
		newFolderNameMedia = testDataEnv.getString("newFolderNameMedia");
		renamedFolderMedia = testDataEnv.getString("renamedFolderMedia");
		newFolderNameProduct = testDataEnv.getString("newFolderNameProduct");
		renamedFolderProduct = testDataEnv.getString("renamedFolderProduct");
		adminMainUserGrp = testDataEnv.getString("adminMainUserGrp");
		Productlang1 = testDataEnv.getInt("Productlang1");
		Productlang2 = testDataEnv.getInt("Productlang2");
		chooseAR_action_URL = testDataEnv.getInt("chooseAR_action_URL");
		selectAR_action_URL = testDataEnv.getInt("selectAR_action_URL");
		colorR = "R";
		colorG = "G";
		colorB = "B";
		R = 255;
		G = 255;
		B = 255;
		R2 = 175;
		G2 = 235;
		B2 = 112;
		RGB_value = String.valueOf(R) + " " + G + " " + B;
		colorH = "H";
		colorS = "S";
		colorL = "L";
		H = 90;
		S = 100;
		L = 100;
		H2 = 200;
		S2 = 100;
		L2 = 93;
		RGB_value2 = "255 255 255";

		FilePath_1885 = testDataEnv.getString("FilePath_1885");
		FileuploaderExe_1885 = testDataEnv.getString("FileuploaderExe_1885");

		FilePath_1861 = testDataEnv.getString("FilePath_1861");
		FileuploaderExe_1861 = testDataEnv.getString("FileuploaderExe_1861");

		FilePath_1861_1 = testDataEnv.getString("FilePath_1861_1");
		FileuploaderExe_1861_1 = testDataEnv.getString("FileuploaderExe_1861_1");

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
		reportLog("Quitting browser");
		getdriver().quit();
		reportLog("Clearing threadlocal");
		unload();
	}

	@Test
	public void verifyLoginFunctionalityWithValidCredentials() throws Exception {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.logout();
	}

	@Test
	public void verifyManageAPI_KeyFunctionality() throws Exception {

		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnEditProfile();
		myarEditProfileObj.clickManageAPIKeys();
		myarManageAPI_KeyPageObj.clickAddAPIKeys();
		myarManageAPI_KeyPageObj.enterAPIName(APIName);
		myarManageAPI_KeyPageObj.clickAddButtonOfenterAPIName();
		myarManageAPI_KeyPageObj.clickOnCloseBtn_APIKeys();
		assertTrue(myarManageAPI_KeyPageObj.isMyarInformationAPI_keyTablePresent(),
				"Information API key table presence");
		myarManageAPI_KeyPageObj.Delete_APIkeyTable();
		assertFalse(myarManageAPI_KeyPageObj.isMyarInformationAPI_keyTablePresent(),
				"Information API key table not-present");

		myarDashboardPageObj.logout();
	}

	@Test
	public void verifyChangeEmailAddressFunctionality() throws Exception {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnEditProfile();
		myarEditProfileObj.clickOnEditEmailIcon();
		myarEditProfileObj.enterNewEmailAddress(newEmailAddress);
		myarEditProfileObj.re_enterNewEmailAddress(newEmailAddress);
		myarEditProfileObj.clickOnchangeEmailAddress_Button();
		Assert.assertEquals(myarEditProfileObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarEditProfileObj.getWebText_Content(),
				"An email has been sent to your new email address with a token. Please check your inbox.");

		assertTrue(myarEditProfileObj.isLEnter_the_token_received_via_email_Displayed(),
				"Enter the token received via email text box label presence");

		myarEditProfileObj.clickOnCloseX();
		myarDashboardPageObj.logout();
	}

	@Test
	public void verifyAddRenameDeleteFolderActionsOfMediaSection() {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnMEDIA();
		myarMediaPageObj.clickOnClient();
		myarMediaPageObj.clickOnAddFolderMedia();
		myarMediaPageObj.enterFolderName(newFolderNameMedia);
		myarMediaPageObj.clickOnAddFolderBtn();
		Assert.assertEquals(myarMediaPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarMediaPageObj.getWebText_Content(), "Folder added successfully");
		assertTrue(myarMediaPageObj.isFolderPresent(newFolderNameMedia), newFolderNameMedia + " presence");

		myarMediaPageObj.clickOnFolderUsingName(newFolderNameMedia);
		myarMediaPageObj.clickOnrenameFolderMedia();
		myarMediaPageObj.enterFolderName(renamedFolderMedia);
		myarMediaPageObj.clickOnRenameFolderBtn();
		Assert.assertEquals(myarMediaPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarMediaPageObj.getWebText_Content(), "Folder renamed successfully.");
		assertTrue(myarMediaPageObj.isFolderPresent(renamedFolderMedia), renamedFolderMedia + " presence");

		myarMediaPageObj.clickOnFolderUsingName(renamedFolderMedia);
		myarMediaPageObj.clickOnDeleteFolderMedia();
		myarMediaPageObj.clickOnDeleteButton();
		Assert.assertEquals(myarMediaPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarMediaPageObj.getWebText_Content(), "Folder deleted successfully.");
		// assertFalse(myarMediaPageObj.isFolderPresent(renamedFolder),renamedFolder+"
		// not present");

		myarMediaPageObj.click_closeX_toCloseMedia();

		myarDashboardPageObj.logout();
	}

	@Test
	public void verifyAddRenameDeleteFolderActionsOfProductSection() {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.clickOnClient();
		myarProductsPageObj.clickOnAddFolderProducts();
		myarProductsPageObj.enterFolderName(newFolderNameProduct);
		myarProductsPageObj.clickOnAddFolderBtn();
		Assert.assertEquals(myarProductsPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarProductsPageObj.getWebText_Content(), "Folder added successfully");
		assertTrue(myarProductsPageObj.isFolderPresent(newFolderNameProduct), newFolderNameProduct + " presence");

		myarProductsPageObj.clickOnFolderUsingName(newFolderNameProduct);
		myarProductsPageObj.clickOnrenameFolderProducts();
		myarProductsPageObj.enterFolderName(renamedFolderProduct);
		myarProductsPageObj.clickOnRenameFolderBtn();
		Assert.assertEquals(myarProductsPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarProductsPageObj.getWebText_Content(), "Folder renamed successfully.");
		assertTrue(myarProductsPageObj.isFolderPresent(renamedFolderProduct), renamedFolderProduct + " presence");

		myarProductsPageObj.clickOnFolderUsingName(renamedFolderProduct);
		myarProductsPageObj.clickOnDeleteFolderProducts();
		myarProductsPageObj.clickOnDeleteButton();
		Assert.assertEquals(myarProductsPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarProductsPageObj.getWebText_Content(), "Folder deleted successfully.");
		// assertFalse(myarProductsPageObj.isFolderPresent(renamedFolder),renamedFolder+"
		// not present");

		myarProductsPageObj.click_closeX_toCloseProducts();

		myarDashboardPageObj.logout();
	}

	@Test
	public void verifyResetPasswordFunctionality() {
		// navigating to URL
		myarLoginPageObj.navigateToLoginURL();
		myarLoginPageObj.clickOnLanguageDropDown();
		myarLoginPageObj.selectLoginLanguage(lang);

		myarLoginPageObj.clickOnForgotPasswordLink();
		myarLoginPageObj.enterEmailAddress_resetPWD(clientEmail_Address);
		myarLoginPageObj.clickOnContinueButton();

		assertTrue(myarLoginPageObj.isLoginButtonPresent(), "Login button is displayed ");

		// Assert.assertEquals(myarLoginPageObj.getWebText_Title(), "Success");
		// Assert.assertEquals(myarLoginPageObj.getWebText_Content(),
		// "A link to reset your password has been sent to your email address.");

	}

	@Test
	public void verifyAddingNewSubgroupAdminUserFunctionality() {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, AdminUsername, AdminPassword);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarAdminDashboardPageObj.goToAdministration();
		myarAdministrationPageObj.clickToSelect_User_UserGrp(adminMainUserGrp);
		myarAdministrationPageObj.clickOnAddSubGroup();
		myarAdministrationPageObj.enterGroupName("a_subUser_1");
		myarAdministrationPageObj.clickOnSaveButton();
		Assert.assertEquals(myarAdministrationPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarAdministrationPageObj.getWebText_Content(), "a_subUser_1 Group added successfully");
		assertTrue(myarAdministrationPageObj.user_userGroup_isDisplayed("a_subUser_1"),
				"a_subUser_1" + " : Sub group name presence");

		myarAdministrationPageObj.clickToSelect_User_UserGrp("a_subUser_1");
		myarAdministrationPageObj.clickOndeleteSubGroup_icon();
		myarAdministrationPageObj.clickOnDeleteButton();

		Assert.assertEquals(myarAdministrationPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarAdministrationPageObj.getWebText_Content(), "Usergroup deleted successfully");

		myarAdministrationPageObj.adminLogout();

	}

	@Test
	public void verifyAddingNewUserAdminUserFunctionality() {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, AdminUsername, AdminPassword);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarAdminDashboardPageObj.goToAdministration();
		myarAdministrationPageObj.clickToSelect_User_UserGrp(adminMainUserGrp);
		myarAdministrationPageObj.clickOnAddUser_icon();
		myarAdministrationPageObj.clickToSelectSalutation("Mr.");
		myarAdministrationPageObj.enterFirstname("a_user1");
		myarAdministrationPageObj.enterLastname("LastName");
		myarAdministrationPageObj.enterEmailaddress("a_user1LastName@gmail.com");
		myarAdministrationPageObj.enterPWD("Abc@123456");
		myarAdministrationPageObj.enterconfirmPWD("Abc@123456");
		myarAdministrationPageObj.clickOnCreateButton();
		Assert.assertEquals(myarAdministrationPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarAdministrationPageObj.getWebText_Content(), "User added successfully.");
		assertTrue(myarAdministrationPageObj.user_userGroup_isDisplayed("a_user1LastName@gmail.com"),
				"a_user1LastName@gmail.com" + " : user is  displayed");

		myarAdministrationPageObj.clickToSelect_User_UserGrp("a_user1LastName@gmail.com");
		myarAdministrationPageObj.clickOnDeleteUser();
		myarAdministrationPageObj.clickOnDeleteButton();
		Assert.assertEquals(myarAdministrationPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarAdministrationPageObj.getWebText_Content(), "Deleted successfully.");

		myarAdministrationPageObj.adminLogout();
	}

	@Test(groups = { "Snoopstar-1.8.0", "Myar-1887" }, description = "1.8.0 release testing")
	public void verifyChangingRGB_ValuesChangesBackgroundColorOfEditor() {
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnEDITOR();
		myarEditorPageObj.clickOnWorkspaceSettings();

		ini_rgb = myarEditorPageObj.returnRGBValues();
		reportLog("Initials respective RGB values are : " + ini_rgb);

		myarEditorPageObj.clickOnBackgroundColor();
		myarEditorPageObj.enterColourValues(colorR, String.valueOf(R));
		myarEditorPageObj.enterColourValues(colorG, String.valueOf(G));
		myarEditorPageObj.enterColourValues(colorB, String.valueOf(B));
		myarEditorPageObj.clickOnColorPickerSave();

		now_rgb = myarEditorPageObj.returnRGBValues();
		reportLog("Current respective RGB values are : " + now_rgb);

		assertFalse(ini_rgb.equals(now_rgb));
		assertTrue(RGB_value.equals(now_rgb));
		myarEditorPageObj.compareRGB_Values("RGB values", now_rgb, RGB_value);

		myarEditorPageObj.clickOnBackgroundColor();
		myarEditorPageObj.enterColourValues(colorR, String.valueOf(R2));
		myarEditorPageObj.enterColourValues(colorG, String.valueOf(G2));
		myarEditorPageObj.enterColourValues(colorB, String.valueOf(B2));
		myarEditorPageObj.clickOnColorPickerSave();
		myarEditorPageObj.clickOnWorkspaceSettingCloseButton();

		myarDashboardPageObj.logout();

	}

	@Test(groups = { "Snoopstar-1.8.0", "Myar-1887" }, description = "1.8.0 release testing")
	public void verifyChangingHSL_ValuesChangesBackgroundColorOfEditor() {
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnEDITOR();
		myarEditorPageObj.clickOnWorkspaceSettings();

		ini_rgb = myarEditorPageObj.returnRGBValues();
		reportLog("Initials respective RGB values are : " + ini_rgb);

		myarEditorPageObj.clickOnBackgroundColor();
		myarEditorPageObj.enterColourValues(colorH, String.valueOf(H));
		myarEditorPageObj.enterColourValues(colorS, String.valueOf(S));
		myarEditorPageObj.enterColourValues(colorL, String.valueOf(L));
		myarEditorPageObj.clickOnColorPickerSave();

		now_rgb = myarEditorPageObj.returnRGBValues();
		reportLog("Current respective RGB values are : " + now_rgb);

		assertFalse(ini_rgb.equals(now_rgb));
		assertTrue(RGB_value2.equals(now_rgb));
		myarEditorPageObj.compareRGB_Values("RGB values", now_rgb, RGB_value2);

		myarEditorPageObj.clickOnBackgroundColor();
		myarEditorPageObj.enterColourValues(colorH, String.valueOf(H2));
		myarEditorPageObj.enterColourValues(colorS, String.valueOf(S2));
		myarEditorPageObj.enterColourValues(colorL, String.valueOf(L2));
		myarEditorPageObj.clickOnColorPickerSave();
		myarEditorPageObj.clickOnWorkspaceSettingCloseButton();

		myarDashboardPageObj.logout();

	}

	@Test(groups = { "Snoopstar-1.8.0",
			"Myar-1888" }, description = "Store the trusted partner data, instead of counting everytime api hits")
	public void verifyBilledTargetsForTrustedPartnersDisplayed() {
		myarLoginPageObj.login(lang, AdminUsername, AdminPassword);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarAdminDashboardPageObj.goToAdministration();
		myarAdministrationPageObj.clickToSelect_User_UserGrp(adminMainUserGrp);
		myarAdministrationPageObj.clickOnTrustedPartner();
		Assert.assertEquals(myarAdministrationPageObj.test_billed_trustedPartner_isDisplayed(), true);

		myarAdministrationPageObj.adminLogout();

	}

	@Test(groups = { "Snoopstar-1.8.0", "Myar-1885" }, description = "Issue related to excel import")
	public void verifyOnExcelImportClickingProductIdAtImportStatusOpensImportedProductInEditor()
			throws AWTException, IOException {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.clickOnImportExcelIcon();
		myarExcel_ImportPageObj.clickImportProductExcel();
		myarSelectProductTypePageObj.clickConfirmBtn();
		myarUploadExcelPageObj.BrowseFiles(FilePath_1885, FileuploaderExe_1885);
		myarUploadExcelPageObj.clickOnUploadBtn();

		myarImportMappingPageObj.setActiveCountriesforExcelImport();
		myarImportMappingPageObj.setMasterLanguageExcelImport();
		myarImportMappingPageObj.setActiveLanguageExcelImport(2);
		myarImportMappingPageObj.clickOnAutofill();
		myarImportMappingPageObj.selectScenesFromDD("EN");
		myarImportMappingPageObj.selectScenesFromDD("ES");
		myarImportMappingPageObj.clickOnImport();
		myarImportMappingPageObj.EnterMappingName("MYAR-1885_ImportMapping");
		myarImportMappingPageObj.clickOnImportMappingConfirmationImport();

		Assert.assertEquals(myarImportStatusPageObj.returnImportStatus(), "Import Completed");

		Assert.assertEquals(myarImportStatusPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarImportStatusPageObj.getWebText_Content(),
				"Excel import finished. Please refresh page to see new/updated records.");

		myarImportStatusPageObj.clickOnProductID();
		myarProductsPageObj.click_closeX_toCloseProducts();

		myarEditorPageObj.isTargetTabDisplayed();
		Assert.assertEquals(myarEditorPageObj.getNameOfCUrrentProduct(), "Myar1861Product");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.clickOnImportExcelIcon();
		myarExcel_ImportPageObj.deleteMapping("MYAR-1885_ImportMapping");
		myarExcel_ImportPageObj.clickOnConfirmDelete();
		myarExcel_ImportPageObj.click_closeX_toCloseExcelImportModal();

		myarEditorPageObj.waitToDeleteProduct();
		myarProductsPageObj.searchProduct("Myar1861Product");
		Assert.assertEquals(myarProductsPageObj.returnProductNameifPresent("Myar1861Product"), "Myar1861Product");

		myarProductsPageObj.deleteProduct();
		assertTrue(myarProductsPageObj.isNoDataAvailable_Displayed(), "NO DATA AVAILABLE displayed");
		myarProductsPageObj.click_closeX_toCloseProducts();

		myarDashboardPageObj.logout();
	}

	@Test(groups = { "Snoopstar-1.7.5",
			"Myar-1861" }, description = "Change excel import for standard products to update existing products using the product name instead of product id")
	public void verifyStandardProductCreationUsingProductNameThroughExcelImport() throws AWTException, IOException {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.clickOnImportExcelIcon();

		myarExcel_ImportPageObj.clickImportProductExcel();
		myarSelectProductTypePageObj.clickConfirmBtn();
		myarUploadExcelPageObj.BrowseFiles(FilePath_1861, FileuploaderExe_1861);
		myarUploadExcelPageObj.clickOnUploadBtn();
		myarImportMappingPageObj.setActiveCountriesforExcelImport();
		myarImportMappingPageObj.setMasterLanguageExcelImport();
		myarImportMappingPageObj.setActiveLanguageExcelImport(2);
		myarImportMappingPageObj.clickOnAutofill();
		myarImportMappingPageObj.selectScenesFromDD("EN");
		myarImportMappingPageObj.selectScenesFromDD("ES");
		myarImportMappingPageObj.clickOnImport();
		myarImportMappingPageObj.EnterMappingName("MYAR-1861_ImportMapping");
		myarImportMappingPageObj.clickOnImportMappingConfirmationImport();

		Assert.assertEquals(myarImportStatusPageObj.returnImportStatus(), "Import Completed");
		Assert.assertEquals(myarImportStatusPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarImportStatusPageObj.getWebText_Content(),
				"Excel import finished. Please refresh page to see new/updated records.");
		threadMethod(15000);
		refreshPage();

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.searchProduct("Myar1861Product");
		Assert.assertEquals(myarProductsPageObj.returnProductNameifPresent("Myar1861Product"), "Myar1861Product");

		myarProductsPageObj.clickOnImportExcelIcon();
		myarExcel_ImportPageObj.deleteMapping("MYAR-1861_ImportMapping");
		myarExcel_ImportPageObj.clickOnConfirmDelete();
		myarExcel_ImportPageObj.click_closeX_toCloseExcelImportModal();

		myarEditorPageObj.waitToDeleteProduct();
		myarProductsPageObj.deleteProduct();
		assertTrue(myarProductsPageObj.isNoDataAvailable_Displayed(), "NO DATA AVAILABLE displayed");
		myarProductsPageObj.click_closeX_toCloseProducts();

		myarDashboardPageObj.logout();

	}

	@Test(groups = { "Snoopstar-1.7.5",
			"Myar-1861" }, description = "Change excel import for standard products to update existing products using the product name instead of product id")
	public void verifyScenesMappingWithScenesInExcel() throws AWTException, IOException {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.clickOnImportExcelIcon();

		myarExcel_ImportPageObj.clickImportProductExcel();
		myarSelectProductTypePageObj.clickConfirmBtn();
		myarUploadExcelPageObj.BrowseFiles(FilePath_1861, FileuploaderExe_1861);
		myarUploadExcelPageObj.clickOnUploadBtn();
		myarImportMappingPageObj.setActiveCountriesforExcelImport();
		myarImportMappingPageObj.setMasterLanguageExcelImport();
		myarImportMappingPageObj.setActiveLanguageExcelImport(2);
		myarImportMappingPageObj.clickOnAutofill();
		myarImportMappingPageObj.selectScenesFromDD("EN");
		myarImportMappingPageObj.selectScenesFromDD("ES");
		myarImportMappingPageObj.clickOnImport();
		myarImportMappingPageObj.EnterMappingName("MYAR-1861_ImportMapping");
		myarImportMappingPageObj.clickOnImportMappingConfirmationImport();

		Assert.assertEquals(myarImportStatusPageObj.returnImportStatus(), "Import Completed");
		Assert.assertEquals(myarImportStatusPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarImportStatusPageObj.getWebText_Content(),
				"Excel import finished. Please refresh page to see new/updated records.");
		threadMethod(15000);
		refreshPage();

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.searchProduct("Myar1861Product");
		Assert.assertEquals(myarProductsPageObj.returnProductNameifPresent("Myar1861Product"), "Myar1861Product");

		myarProductsPageObj.clickOnShowLanguages();
		myarProductsPageObj.clickToOpenProductInEditor();

		myarEditorPageObj.selectProductLanguage(Productlang1);
		myarEditorPageObj.clickOnScenesTab();
		Assert.assertEquals(myarScenesPageObj.getSceneName(1), "MYAR_1861_scEN");

		myarEditorPageObj.selectProductLanguage(Productlang2);
		myarEditorPageObj.clickOnScenesTab();
		Assert.assertEquals(myarScenesPageObj.getSceneName(1), "MYAR_1861_scES");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.searchProduct("Myar1861Product");
		Assert.assertEquals(myarProductsPageObj.returnProductNameifPresent("Myar1861Product"), "Myar1861Product");

		myarProductsPageObj.clickOnImportExcelIcon();
		myarExcel_ImportPageObj.deleteMapping("MYAR-1861_ImportMapping");
		myarExcel_ImportPageObj.clickOnConfirmDelete();
		myarExcel_ImportPageObj.click_closeX_toCloseExcelImportModal();

		myarEditorPageObj.waitToDeleteProduct();
		myarProductsPageObj.deleteProduct();
		assertTrue(myarProductsPageObj.isNoDataAvailable_Displayed(), "NO DATA AVAILABLE displayed");
		myarProductsPageObj.click_closeX_toCloseProducts();

		myarDashboardPageObj.logout();

	}

	@Test(groups = { "Snoopstar-1.7.5",
			"Myar-1861" }, description = "Change excel import for standard products to update existing products using the product name instead of product id")
	public void verifyGeoMappingsNotVisibleInCHOOSE_MAPPING_SectionImportingStandardProduct()
			throws AWTException, IOException {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.clickOnImportExcelIcon();

		myarExcel_ImportPageObj.clickImportProductExcel();
		myarSelectProductTypePageObj.clickConfirmBtn();
		myarUploadExcelPageObj.BrowseFiles(FilePath_1861, FileuploaderExe_1861);
		myarUploadExcelPageObj.clickOnUploadBtn();

		myarImportMappingPageObj.clickOnLoadMappingsButton();

		assertTrue(myarChooseMappingPageObj.isNoDataAvailableDisplayed(), "No Data Available displayed");
		myarChooseMappingPageObj.clickOnCancelButton();

		myarImportMappingPageObj.click_closeX_toCloseImportMapping();
		myarProductsPageObj.click_closeX_toCloseProducts();

		myarDashboardPageObj.logout();
	}

	@Test(groups = { "Snoopstar-1.7.5",
			"Myar-1861" }, description = "Change excel import for standard products to update existing products using the product name instead of product id")
	public void verifyValidationFailureCreatingStandardProductWhenGeoProductOfSameNameExists()
			throws AWTException, IOException {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.searchProduct("P_TC5_MYAR_1861_Stagging_Geo");
		Assert.assertEquals(myarProductsPageObj.returnProductNameifPresent("P_TC5_MYAR_1861_Stagging_Geo"),
				"P_TC5_MYAR_1861_Stagging_Geo");

		myarProductsPageObj.clickOnImportExcelIcon();

		myarExcel_ImportPageObj.clickImportProductExcel();
		myarSelectProductTypePageObj.clickConfirmBtn();
		myarUploadExcelPageObj.BrowseFiles(FilePath_1861_1, FileuploaderExe_1861_1);
		myarUploadExcelPageObj.clickOnUploadBtn();
		myarImportMappingPageObj.setActiveCountriesforExcelImport();
		myarImportMappingPageObj.setMasterLanguageExcelImport();
		myarImportMappingPageObj.setActiveLanguageExcelImport(2);
		myarImportMappingPageObj.clickOnAutofill();
		myarImportMappingPageObj.selectScenesFromDD("EN");
		myarImportMappingPageObj.selectScenesFromDD("ES");
		myarImportMappingPageObj.clickOnImport();
		myarImportMappingPageObj.EnterMappingName("MYAR-1861_ImportMappingCheck2");
		myarImportMappingPageObj.clickOnImportMappingConfirmationImport();

		Assert.assertEquals(myarImportStatusPageObj.returnImportStatusValidationFail(), "Validation Failed");
		// Assert.assertEquals(myarImportStatusPageObj.getWebText_Title(), "Error");
		// Assert.assertEquals(myarImportStatusPageObj.getWebText_Content(), "Same
		// product name exist with geo product type.");

		threadMethod(15000);
		refreshPage();

		myarDashboardPageObj.clickOnPRODUCTS();
		myarProductsPageObj.clickOnImportExcelIcon();
		myarExcel_ImportPageObj.deleteMapping("MYAR-1861_ImportMappingCheck2");
		myarExcel_ImportPageObj.clickOnConfirmDelete();
		myarExcel_ImportPageObj.click_closeX_toCloseExcelImportModal();
		myarProductsPageObj.click_closeX_toCloseProducts();

		myarDashboardPageObj.logout();
	}

	@Test(groups = { "Snoopstar-1.7.5", "Myar-1862" }, description = "Issue with copy scene for another language")
	public void verify_different_URL_Ar_Actions_can_be_assigned_in_differnt_languages() {
		// login with language email address and password and verify the login Success
		myarLoginPageObj.login(lang, clientEmail_Address, clientPWD);
		assertTrue(myarDashboardPageObj.isMyarDashboardPresent(), "MYAR Dashboard presence");

		myarDashboardPageObj.clickOnStartNewProduct();
		myarSelectProductTypePageObj.selectGeoProduct();
		myarGeoProductSetUpPageObj.createGeoProduct("P_TC1_MYAR_1862_Geo", "Brochures", "Kolkata");
		myarEditorPageObj.isTargetTabDisplayed();
		Assert.assertEquals(myarEditorPageObj.getNameOfCUrrentProduct(), "P_TC1_MYAR_1862_Geo");

		myarEditorPageObj.clickOnAddTargetPlus();
		myarAddTargetPageObj.clickTargetImage_select();
		myarMediaPageObj.browseSelectTargetImage("AudiSedan.jpeg");
		myarAddTargetPageObj.clickOnSaveChangesButton();
		myarAddTargetPageObj.waitTargetImage_Delete_button_Clickable();

		myarEditorPageObj.selectGeoProductLanguage(1);
		myarEditorPageObj.clickOnScenesTab();

		myarScenesPageObj.createScene("MYAR_1862_sc_EN");
		Assert.assertEquals(myarScenesPageObj.getSceneName(1), "MYAR_1862_sc_EN");
		// Assert.assertEquals(myarScenesPageObj.getSceneName(1),"MYAR_1862_sc_EN");

		myarEditorPageObj.clickOnAddElements_button();

		myarEditorPageObj.clickOnAddButton();
		myarButtonElementPageObj.enterButtonName("ButtonOfMyar1862");
		Assert.assertEquals(myarButtonElementPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarButtonElementPageObj.getWebText_Content(), "Button details saved successfully.");

		myarButtonElementPageObj.chooseAr_action(chooseAR_action_URL);
		myarButtonElementPageObj.selectAr_action(selectAR_action_URL);
		myarButtonElementPageObj.enterURL("https://snoopstar.com");

		myarEditorPageObj.clickOnScenesTab();

		myarScenesPageObj.copyScene("MYAR_1862_sc_EN_Copy");

		myarEditorPageObj.selectGeoProductLanguage(2);
		myarEditorPageObj.clickOnScenesTab();

		myarScenesPageObj.clickOnSelectSceneButton();
		myarSceneLibraryPageObj.loadSceneFromLib("MYAR_1862_sc_EN_Copy");
		Assert.assertEquals(myarScenesPageObj.getSceneName(1), "MYAR_1862_sc_EN_Copy");

		myarEditorPageObj.clickOnElementsTab();
		myarElementsPageObj.goToSpecificElement(1);
		myarButtonElementPageObj.clearURL();
		myarButtonElementPageObj.enterURL("https://lsd.de");

		myarEditorPageObj.selectGeoProductLanguage(1);
		myarEditorPageObj.clickOnButtonByName("ButtonOfMyar1862");
		myarButtonElementPageObj.copyURL();
		myarEditorPageObj.clickOnAddElements_button();
		myarEditorPageObj.clickOnAddButton();
		myarButtonElementPageObj.pasteURL_ButtomName();
		Assert.assertEquals(myarButtonElementPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarButtonElementPageObj.getWebText_Content(), "Button details saved successfully.");

		String validURLck = myarEditorPageObj.getButtonName("https://snoopstar.com");

		myarEditorPageObj.selectGeoProductLanguage(2);
		myarEditorPageObj.clickOnButtonByName("ButtonOfMyar1862");
		myarButtonElementPageObj.copyURL();
		myarEditorPageObj.clickOnAddElements_button();
		myarEditorPageObj.clickOnAddButton();
		myarButtonElementPageObj.pasteURL_ButtomName();
		Assert.assertEquals(myarButtonElementPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarButtonElementPageObj.getWebText_Content(), "Button details saved successfully.");

		String validURL2ck = myarEditorPageObj.getButtonName("https://lsd.de");

		Assert.assertEquals(validURLck, "https://snoopstar.com");
		Assert.assertEquals(validURL2ck, "https://lsd.de");

		myarDashboardPageObj.clickOnPRODUCTS();
		myarEditorPageObj.waitToDeleteProduct();
		myarProductsPageObj.searchProduct("P_TC1_MYAR_1862_Geo");
		Assert.assertEquals(myarProductsPageObj.returnProductNameifPresent("P_TC1_MYAR_1862_Geo"),
				"P_TC1_MYAR_1862_Geo");

		myarProductsPageObj.deleteProduct();
		assertTrue(myarProductsPageObj.isNoDataAvailable_Displayed(), "NO DATA AVAILABLE displayed");
		myarProductsPageObj.click_closeX_toCloseProducts();

		myarDashboardPageObj.clickOnSCENES();
		myarSceneLibraryPageObj.click_CheckAllBox();
		myarSceneLibraryPageObj.clickOnDeleteAllTrashIcon();
		Assert.assertEquals(myarSceneLibraryPageObj.getWebText_Title(), "Success");
		Assert.assertEquals(myarSceneLibraryPageObj.getWebText_Content(), "Scenes deleted successfully.");
		assertTrue(myarSceneLibraryPageObj.isNoDataAvailable_Displayed(), "NO DATA AVAILABLE displayed");
		myarSceneLibraryPageObj.closeSceneLibrary();

		myarDashboardPageObj.logout();
	}

}
