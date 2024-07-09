package myar.pages;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MyarGeoProductSetUpPage extends TestBase
{
	// Page Factory - OR:
	@FindBy(xpath = "//input[@data-testid='productSetupProductName']")
	WebElement productNameInputField;
	
	@FindBy(xpath = "//select[@data-testid='productType']")
	WebElement productTypeDropDown;
	
	@FindBy(xpath = "//div[@class='inner-header flex-container ng-star-inserted']//button[@class='btn wide-btn text-uppercase black-back white-font']")
	WebElement loadGeoSetUp;
	
	@FindBy(xpath = "(//button[@data-testid='saveBtnTestId'])[2]")
	WebElement loadSelected;
	
	@FindBy(xpath = "//span[contains(text(),'Save and open in editor')]")
	WebElement saveAndOpenInEditor;
	
	
	String chooseLocation="//span[text()='xxxxx']//preceding::span[1]";
	
	
	// Initializing the Page Factory/Objects:
	public MyarGeoProductSetUpPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}
	
	/*
	 *Functions
	 */
		
	public void enterProductName(String ProductName)
	{
		clearAndInput(productNameInputField, "Enter Product name textbox", ProductName);
	}
	
	public void setProductType(String productType) 
	{	
		selectValueByVisibleText(productTypeDropDown,productType,"Product type");
	}
	
	public void createGeoProduct(String ProductName,String productType, String location)
	{
		enterProductName(ProductName);
		setProductType(productType);
		LoadGeoSetUp(location);
		clickSaveAndOpenInEditor();
	}
	public void clickSaveAndOpenInEditor()
	{
		threadMethod(2000);
		clickElement(saveAndOpenInEditor, "SAVE AND OPEN IN EDITOR button");
	}
	public void LoadGeoSetUp(String location)
	{
		clickOnLoadGeoSetup();
		clickToChooseLocation(location);
		clickOnLoadSelected();
	}
	
	public void clickOnLoadGeoSetup()
	{
		clickElement(loadGeoSetUp, "LOAD GEO SETUP");
	}
	
	public void clickToChooseLocation(String location)
	{
		clickElement(prepareWebElementWithDynamicXpath(chooseLocation, location),"Check-box of "+location);
	}
	
	public void clickOnLoadSelected()
	{
		clickElement(loadSelected, "LOAD SELECTED button");
	}

}
