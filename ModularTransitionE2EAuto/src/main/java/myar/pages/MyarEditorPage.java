package myar.pages;

import static com.qa.util.TestUtil.assertText;
import static com.qa.util.TestUtil.clearAndInput;
import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.clickElement2;
import static com.qa.util.TestUtil.isElementPresent;
import static com.qa.util.TestUtil.prepareWebElementWithDynamicXpath;
import static com.qa.util.TestUtil.retrieveAttributeValue2;
import static com.qa.util.TestUtil.retrieveText;
import static com.qa.util.TestUtil.threadMethod;
import static com.qa.util.TestUtil.waitForElementToLoad;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarEditorPage extends TestBase {

	// Page Factory - OR:

	@FindBy(xpath = "//button[contains(@class,'workspace-settings')]")
	WebElement workSpace_Settings;

	@FindBy(xpath = "//input[@name='color-picker-parent']")
	WebElement backgroundColor;

	@FindBy(xpath = "//button[@data-testid='color-picker-save']")
	WebElement colorPicker_Save;

	@FindBy(xpath = "//button[@data-testid='header-close-item']")
	WebElement workSpace_Settings_CLOSE;

	@FindBy(xpath = "(//span[@class='dropdownLanguageSpan sub-header-font'])[2]")
	WebElement nameOfCurrentProduct;

	@FindBy(xpath = "//span[contains(text(),'Target')]")
	WebElement targetTab;

	@FindBy(xpath = "//button//i[@class='fa-plus fas ng-star-inserted']")
	WebElement addTarget;

	@FindBy(xpath = "(//div[@class='flag-container pl-1'])")
	WebElement productLang;

	@FindBy(xpath = "(//div[@class='flag-container pl-1'])//span[text()='EN']")
	WebElement lang1;

	@FindBy(xpath = "(//div[@class='flag-container pl-1'])//span[text()='DE']")
	WebElement lang2;

	@FindBy(xpath = "(//div[@class='flag-container pl-1'])//span[text()='ES']")
	WebElement lang2_s;

	@FindBy(xpath = "(//div[@class='flag-container pl-1'])//span[text()='FR']")
	WebElement lang3;

	@FindBy(xpath = "(//div[@class='flag-container pl-1'])//span[text()='IT']")
	WebElement lang4;

	@FindBy(xpath = "(//div[@class='flag-container pl-1'])//span[text()='SQ']")
	WebElement lang5;

	@FindBy(xpath = "//span[text()='Scenes']")
	WebElement scenes_tab;

	@FindBy(xpath = "//i[@class='fas fa-plus-circle']")
	WebElement AddElements_button;

	@FindBy(xpath = "//button[@data-testid='add-button']")
	WebElement AddButton;

	@FindBy(xpath = "//span[text()='Elements']")
	WebElement Elements_tab;

	@FindBy(xpath = "//div[@data-element-type='BUTTON']//span[text()=' Amazon ']")
	WebElement amazonButtonMYAR1870;

	@FindBy(xpath = "//div[@data-element-type='BUTTON']//span[text()=' Facebook ']")
	WebElement facebookButtonMYAR170;

	@FindBy(xpath = "//div[@id='target-button-41669']")
	WebElement FB_ButtonMYAR170;

	@FindBy(xpath = "//div[@id='target-button-41674']")
	WebElement FB_ButtonMYAR170Geo;

	@FindBy(xpath = "//div[@id='target-button-41668']")
	WebElement amazon_ButtonMYAR170;

	@FindBy(xpath = "//div[@id='target-button-41672']")
	WebElement amazon_ButtonMYAR170Geo;

	@FindBy(xpath = "//div//span[text()=' ButtonOfMyar1862 ']")
	WebElement buttonOfMyar1862;

	@FindBy(xpath = "(//div//span[text()=' https://snoopstar.com '])[1]")
	WebElement cbuttonOfMyar1862;

	@FindBy(xpath = "(//div//span[text()=' https://lsd.de '])[1]")
	WebElement cbuttonOfMyar1862_2;

	public String RGB_values = "//div[text()='xxxxx']// following :: input[@type='number']";

	// Initializing the Page Factory/Objects:
	public MyarEditorPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */

	public String getButtonName(String ButtonName) {
		threadMethod(5000);
		switch (ButtonName) {
		case "amazon":
			return retrieveText(AddElements_button, ButtonName);
		case "facebook":
			return retrieveText(facebookButtonMYAR170, "facebook");
		case "FB_BtnCpy":
			return retrieveText(FB_ButtonMYAR170, "FB_BtnCpy");
		case "Amazon_BtnCpy":
			return retrieveText(amazon_ButtonMYAR170, "Amazon_BtnCpy");
		case "FB_BtnCpyG":
			return retrieveText(FB_ButtonMYAR170Geo, "FB_BtnCpyG");
		case "Amazon_BtnCpyG":
			return retrieveText(amazon_ButtonMYAR170Geo, "Amazon_BtnCpyG");
		case "ButtonOfMyar1862":
			return retrieveText(buttonOfMyar1862, "ButtonOfMyar1862");
		case "https://snoopstar.com":
			return retrieveText(cbuttonOfMyar1862, "https://snoopstar.com");
		case "https://lsd.de":
			return retrieveText(cbuttonOfMyar1862_2, "https://lsd.de");

		default:
			return "";
		}
	}

	public void clickOnButtonByName(String ButtonName) {
		threadMethod(5000);
		switch (ButtonName) {
		case "amazon":
			clickElement(amazonButtonMYAR1870, "amazon");
			break;

		case "facebook":
			clickElement(facebookButtonMYAR170, "facebook");
			break;

		case "FB_BtnCpy":
			clickElement(FB_ButtonMYAR170, "FB_BtnCpy");
			break;

		case "Amazon_BtnCpy":
			clickElement(amazon_ButtonMYAR170, "Amazon_BtnCpy");
			break;

		case "FB_BtnCpyG":
			clickElement(FB_ButtonMYAR170Geo, "FB_BtnCpyG");
			break;

		case "Amazon_BtnCpyG":
			clickElement(amazon_ButtonMYAR170Geo, "Amazon_BtnCpyG");
			break;

		case "ButtonOfMyar1862":
			clickElement(buttonOfMyar1862, "ButtonOfMyar1862");
			break;

		case "https://snoopstar.com":
			clickElement(cbuttonOfMyar1862, "https://snoopstar.com");
			break;

		}
	}

	public void clickOnElementsTab() {
		threadMethod(5000);
		clickElement(Elements_tab, "ELEMENTS tabs");
	}

	public void clickOnAddButton() {
		clickElement(AddButton, "ADD BUTTON");
	}

	public void waitToDeleteProduct() {
		threadMethod(60000);
	}

	public void clickOnAddElements_button() {
		threadMethod(2000);
		clickElement(AddElements_button, "Add elements");
		threadMethod(2000);
	}

	public void clickOnAddTargetPlus() {
		clickElement(addTarget, "+ ADD TARGET");
	}

	public void clickOnScenesTab() {
		threadMethod(2000);
		clickElement(scenes_tab, "SCENES tab");
		threadMethod(2000);
	}

	public boolean isTargetTabDisplayed() {

		waitForElementToLoad(targetTab, "TARGET tab");
		return isElementPresent(targetTab, "TARGET tab");
	}

	public void selectProductLanguage(int index) {
		threadMethod(2000);
		clickElement(productLang, "Product Language");
		switch (index) {
		case 1:

			clickElement(lang1, "EN");
			break;

		case 2:
			clickElement(lang5, "SQ");
			break;
		case 3:
			clickElement(lang2_s, "ES");
			break;
		}
		threadMethod(2000);
	}

	public void selectGeoProductLanguage(int index) {
		threadMethod(5000);
		clickElement(productLang, "Product Language");
		switch (index) {
		case 1:
			threadMethod(2000);
			clickElement(lang1, "EN");
			break;

		case 2:
			threadMethod(2000);
			clickElement(lang2, "DE");
			break;
		}
		threadMethod(2000);
	}

	public String getNameOfCUrrentProduct() {
		threadMethod(5000);
		waitForElementToLoad(nameOfCurrentProduct, "Current Product name");
		return retrieveText(nameOfCurrentProduct, "Current Product name");
	}

	public void compareRGB_Values(String eleName, String actRGBValue, String expRGBValue) {

		assertText(eleName, actRGBValue, expRGBValue);
	}

	public void clickOnWorkspaceSettingCloseButton() {
		clickElement(workSpace_Settings_CLOSE, "WorkSpace settings close X icon");
	}

	public void clickOnColorPickerSave() {
		clickElement(colorPicker_Save, "Color-picker Save button");
	}

	public void enterColourValues(String colour, String value) {
		clearAndInput(prepareWebElementWithDynamicXpath(RGB_values, colour), colour, value);
	}

	public void clickOnBackgroundColor() {
		clickElement2(backgroundColor, "Background color");
	}

	public void clickOnWorkspaceSettings() {
		clickElement(workSpace_Settings, "Background color");
	}

	public String returnRGBValues() {
		String attributeName = "style";
		String attributeValue = "";
		attributeValue = retrieveAttributeValue2(backgroundColor, attributeName, "RGB values");
		attributeValue = attributeValue.substring((attributeValue.indexOf("(") + 1), attributeValue.indexOf(")"))
				.replaceAll(", ", " ").trim();

		return (attributeValue);

	}
}
