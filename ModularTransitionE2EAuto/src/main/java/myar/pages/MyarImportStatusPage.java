package myar.pages;

import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarImportStatusPage extends TestBase
{

	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;

	@FindBy(xpath = "//span[contains(text(),'Pending')]")
	WebElement pendingStatus;

	@FindBy(xpath = "//span[contains(text(),'Validation completed')]")
	WebElement validationCompleteStatus;
	
	@FindBy(xpath = "//span[contains(text(),'Validation failed')]")
	WebElement validationFailedStatus;

	@FindBy(xpath = "//span[contains(text(),'Import completed')]")
	WebElement importCompleteStatus;


	@FindBy(xpath = "//div[@class='import-info info-table']//td//span")
	WebElement productID;

	public String dynamicProductID = "//div[@class='import-info info-table']//td//span[contains(text(),' xxxxx ')]";
	// Initializing the Page Factory/Objects:
	public MyarImportStatusPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */
	public String returnProductID()
	{
		return retrieveText(productID, "Imported Product ID");
	}
	public void clickOnProductID()
	{
		threadMethod(10000);
		clickElement(prepareWebElementWithDynamicXpath(dynamicProductID, returnProductID()), "Product ID : "+returnProductID());
	}

	public String returnImportStatus()
	{
		pendingStatusIsDisplayed();
		validationCompleteStatusIsDisplayed();
		importCompleteStatusIsDisplayed();
		return retrieveText(importCompleteStatus, "Status : Import Completed");
	}
	
	public String returnImportStatusValidationFail()
	{
		pendingStatusIsDisplayed();
		validationFailedStatusIsDisplayed();
		return retrieveText(validationFailedStatus, "Status : Validation failed");
	}
	public void importCompleteStatusIsDisplayed()
	{
		waitForElementToLoad(importCompleteStatus, "Status : Import Completed");
		isElementPresent(importCompleteStatus, "Status : Import Completed");
	}
	public void validationCompleteStatusIsDisplayed()
	{
		waitForElementToLoad(validationCompleteStatus, "Status : Validation Completed");
		isElementPresent(validationCompleteStatus, "Status : Validation Completed");
	}
	
	public void validationFailedStatusIsDisplayed()
	{
		waitForElementToLoad(validationFailedStatus, "Status : Validation failed");
		isElementPresent(validationFailedStatus, "Status : Validation failed");
	}

	public void pendingStatusIsDisplayed()
	{
		waitForElementToLoad(pendingStatus, "Status : Pending");
		isElementPresent(pendingStatus, "Status : Pending");
	}


	public String getWebText_Title()
	{
		return retrieveText(webText_Title,"Web-text title");
	}
	public String getWebText_Content()
	{

		return retrieveText(webText_Content,"Web-text Content");
	}

	//	public void assertWebText_Title(String Expected_WebTextTitle)
	//	{
	//		assertText(Expected_WebTextTitle,getWebText_Title());
	//	}
	//	public void assertWebText_Content(String Expected_WebTextContent)
	//	{
	//		assertText(Expected_WebTextContent,getWebText_Content());
	//	}

}
