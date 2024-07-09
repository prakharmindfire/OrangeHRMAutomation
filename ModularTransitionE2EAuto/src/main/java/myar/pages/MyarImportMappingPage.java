package myar.pages;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyarImportMappingPage extends TestBase
{
	@FindBy(xpath = "//span[contains(text(),'Select Active Countries')]")
	WebElement selectActiveCountriesButton;

	@FindBy(id = "fastSelectOptions")
	WebElement fastSelectionDD_btn;

	@FindBy(xpath = "(//span[text()='Save and close'])")
	WebElement saveAndClose_btn;

	@FindBy(xpath = "//i[@data-testid='productSetupMasterLanguageSelector']")
	WebElement masterLanguageSelector;

	@FindBy(xpath = "//i[@data-testid='productSetupActiveLanguageSelector']")
	WebElement activeLanguageSelector;

	@FindBy(xpath = "(//span[text()='Confirm selection'])")
	WebElement activeLanguageConfirmSelection;

	@FindBy(xpath = "(//button[@data-testid='rightBtnTestId'])[2]")
	WebElement confirmSelectionCountryLevel;

	@FindBy(xpath = "(//button[@data-testid='rightBtnTestId'])[2]")
	WebElement oKCountryLevel;

	@FindBy(xpath = "//button[@data-testid='autofill-btn']")
	WebElement autofillButton;

	@FindBy(xpath = "//span[contains(text(),'Import')]")
	WebElement import_button;
	
	@FindBy(xpath = "//input[@data-testid='mapping-name-input']")
	WebElement enterMappingNameTxtBox;
	
	@FindBy(xpath = "(//button[@data-testid='saveBtnTestId'])[last()]")
	WebElement importMappingConfirmationImport;
	
	@FindBy(xpath = "//button[@data-testid='load-mapping-btn']")
	WebElement loadMappingsButton;
	
	@FindBy(xpath = "(//button[@data-testid='header-close-item'])[last()]")
	WebElement close_X_Button;
	
	
	
	

	String selectScenesfromDD ="(//div[@class='col-xs-6']//select[@class='form-control select-record'])";
	String OptionFromFastSelection = "(//i[@class='fa fa-circle radio-button'])[xxxxx]";
	String masterLanguage =  "(//i[@data-testid='masterLanguageSelectorEN'])[xxxxx]";
	String activeLanguageXpath =  "(//i[@data-testid='activeLanguageSelectorToggle'])[xxxxx]";
	// Initializing the Page Factory/Objects:
	public MyarImportMappingPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */

	public void click_closeX_toCloseImportMapping()
	{
		clickElement(close_X_Button, "Close X icon of Import Mapping modal");
		
	}
	public void clickOnLoadMappingsButton()
	{
		clickElement(loadMappingsButton, "Load Mappings Button");
		threadMethod(1000);
	}
	
	public void clickOnImportMappingConfirmationImport()
	{
		clickElement(importMappingConfirmationImport, "IMPORT button of Import Mapping Confirmation");
		threadMethod(1000);
	}
	public void EnterMappingName(String text)
	{
		clearAndInput(enterMappingNameTxtBox, "Enter mapping name textbox", text);
	}
	
	public void clickOnImport()
	{
		threadMethod(5000);
		clickElement(import_button, "IMPORT button");
		
	}
	public void selectScenesFromDD(String lang)
	{
		String xpath="";
		String visibleText="";

		if(lang.equalsIgnoreCase("EN"))
		{
			xpath=selectScenesfromDD+"[1]";
			visibleText="Scene for EN";
		}
		if(lang.equalsIgnoreCase("DE"))
		{
			xpath=selectScenesfromDD+"[2]";
			visibleText="Scene for DE";
		}
		if(lang.equalsIgnoreCase("ES"))
		{
			xpath=selectScenesfromDD+"[2]";
			visibleText="Scene for ES";
		}
		if(lang.equalsIgnoreCase("FR"))
		{
			xpath=selectScenesfromDD+"[3]";
			visibleText="Scene for FR";
		}
		scrollPageToElement(getdriver().findElement(By.xpath(xpath)));
		selectValueByVisibleText(getdriver().findElement(By.xpath(xpath)),visibleText,"Scenes for "+lang);

	}
	public void clickOnAutofill()
	{
		threadMethod(2000);
		clickElement(autofillButton, "AUTO FILL button");
		threadMethod(5000);
	}
	public void clickConfirmSelectionCountryLevel()
	{
		clickElement(confirmSelectionCountryLevel, "CONFIRM SELECTION button of COUNTRY LEVEL");
	}
	public void clickOkCountryLevel()
	{
		clickElement(oKCountryLevel, "OK button of COUNTRY LEVEL");
	}
	public void clickOnConfirmSelection()
	{
		clickElement(activeLanguageConfirmSelection, "CONFIRM SELECTION button");
	}
	public void clickOnActiveLanguageSelector()
	{
		clickElement(activeLanguageSelector, "Active Language Selector  button");
	}

	public void selectActiveLanguages(int langQty)
	{
		for(int i=2;i<=langQty;i++)
		{

			clickElement(prepareWebElementWithDynamicXpath(activeLanguageXpath, String.valueOf(i)), "language : "+i);
		}
	}
	public void setActiveLanguageExcelImport(int langQuantity)
	{
		threadMethod(2000);
		clickOnActiveLanguageSelector();
		selectActiveLanguages(langQuantity);
		clickSave_CloseButton();
		clickOnConfirmSelection();
		clickConfirmSelectionCountryLevel();
		clickOkCountryLevel();

	}

	public void setMasterLanguageExcelImport()
	{
		clickOnMasterLanguageSelector();
		selectMasterLanguage("1");
		clickSave_CloseButton();	
	}

	public void selectMasterLanguage(String lang)
	{
		clickElement(prepareWebElementWithDynamicXpath(masterLanguage, lang), "MASTER LANGUAGE option : "+lang);
	}

	public void clickOnMasterLanguageSelector()
	{
		clickElement(masterLanguageSelector, "MASTER LANGUAGE selector");
	}

	public void setActiveCountriesforExcelImport()
	{
		clickSelectActiveCountriesButton();
		clickFastSelectionButton();
		selectRegionFromFastSelection("2");
		clickSave_CloseButton();
	}

	public void clickSelectActiveCountriesButton()
	{
		clickElement(selectActiveCountriesButton, "SELECT ACTIVE COUNTRIES");
	}
	public void clickFastSelectionButton()
	{
		clickElement(fastSelectionDD_btn, "FAST SELECTION DROP DOWN BUTTON");
	}
	public void selectRegionFromFastSelection(String option)
	{
		clickElement(prepareWebElementWithDynamicXpath(OptionFromFastSelection,option), "Selected Option : "+option);
	}
	public void clickSave_CloseButton()
	{
		clickElement(saveAndClose_btn, "SAVE AND CLOSE button");
	}

}