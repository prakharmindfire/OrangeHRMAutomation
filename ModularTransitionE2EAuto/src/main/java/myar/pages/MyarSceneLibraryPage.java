package myar.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.util.TestUtil.*;

import com.qa.base.TestBase;

public class MyarSceneLibraryPage extends TestBase
{
	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;
	
	@FindBy(xpath = "//input[@placeholder='SEARCH']")
	WebElement searchSceneTextBox;
	
	@FindBy(xpath = "//span[text()='Add to current product']")
	WebElement addToCurrentProduct;
	
	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement YES_CONFIRMATION;
	
	@FindBy(xpath = "//button[@data-testid='header-close-item']")
	WebElement close_X_button;
	
	@FindBy(xpath = "//input[@value='mainCheckbox']")
	WebElement checkAllBox;
	
	@FindBy(xpath = "(//i[@class='fas fa-trash-alt delete-icon'])[1]")
	WebElement deleteAllIcon;
	
	@FindBy(xpath = "//div[contains(text(),'NO DATA AVAILABLE.')] ")
	WebElement noDataAvailable;
	
	
	// Initializing the Page Factory/Objects:
	public MyarSceneLibraryPage()
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}
	
	/* FUNCTIONS */
	public String getWebText_Title()
	{
		return retrieveText(webText_Title,"Web-text title");
	}
	public String getWebText_Content()
	{

		return retrieveText(webText_Content,"Web-text Content");
	}
	public boolean isNoDataAvailable_Displayed()
	{
		threadMethod(5000);
		return isElementPresent(noDataAvailable, "NO DATA AVAILABLE");
	}
	public void clickOnDeleteAllTrashIcon()
	{
		threadMethod(2000);
		clickElementwithJS(deleteAllIcon, "delete all checked scene trash icon");
	}
	public void click_CheckAllBox()
	{
		threadMethod(2000);
		clickElement(checkAllBox, "select all listed scenes check-box");
	}
	public void searchScene(String sceneName)
	{
		threadMethod(2000);
		clearAndInput(searchSceneTextBox, "Search scene textbox", sceneName);
		pressEnter();
	}
	
	public void clickAddToCurrentProduct()
	{
		threadMethod(2000);
		clickElement(addToCurrentProduct, "ADD TO CURRENT PRODUCT");
	}
	
	public void clickOnYesConfirmation()
	{
		threadMethod(2000);
		clickElement(YES_CONFIRMATION, "YES button of CONFIRMATION modal");
	}
	
	public void closeSceneLibrary()
	{
		threadMethod(2000);
		clickElement(close_X_button, "close X button of Scene Library");
		threadMethod(2000);
	}
	
	public void loadSceneFromLib(String sceneName)
	{
		searchScene(sceneName);
		clickAddToCurrentProduct();
		clickOnYesConfirmation();
		closeSceneLibrary();
	}
}
