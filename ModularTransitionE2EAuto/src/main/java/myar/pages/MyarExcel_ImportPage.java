package myar.pages;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MyarExcel_ImportPage extends TestBase
{
	// Page Factory - OR:
	@FindBy(xpath = "//button[@data-testid='import-product-excel']")
	WebElement importProductExcel;
	
	@FindBy(xpath = "(//div[@class='mapping-delete'])[2]")
	WebElement deleteMapping;
	
	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement confirmDeleteButton;
	
	@FindBy(xpath = "(//button[@data-testid='header-close-item'])[last()]")
	WebElement closeHeader;

	String importMappingnamexpath="//div[contains(text(),' xxxxx ')]";
	
	// Initializing the Page Factory/Objects:
	public MyarExcel_ImportPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */
	public void clickImportProductExcel()
	{
		clickElement(importProductExcel, "IMPORT EXCEL button of EXCEL IMPORT");
	}
	
	public boolean isMappingNameDisplayed(String mappingName)
	{
		return isElementPresent(prepareWebElementWithDynamicXpath(importMappingnamexpath,mappingName), "Mapping Name : "+mappingName);
	}
	
	public String returnImportMappingNameifPresent(String mappingName)
	{
		isMappingNameDisplayed( mappingName);
		return retrieveText(prepareWebElementWithDynamicXpath(importMappingnamexpath,mappingName), "Mapping Name");
	}
	
	public void deleteMapping(String MappingName)
	{
		Assert.assertEquals(returnImportMappingNameifPresent(MappingName), MappingName);
		clickElement(deleteMapping, "Trash button of Mapping name : "+MappingName);
	}
	
	public void clickOnConfirmDelete()
	{
		clickElement(confirmDeleteButton, "Delete confirmation button");
	}
	
	public void click_closeX_toCloseExcelImportModal()
	{
		threadMethod(2000);
		clickElement(closeHeader,"Close X icon of Excel Import modal");
	}
}
