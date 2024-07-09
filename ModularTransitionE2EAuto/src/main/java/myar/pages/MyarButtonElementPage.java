package myar.pages;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyarButtonElementPage extends TestBase
{

	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;
	
	@FindBy(xpath = "//input[@data-testid='btn-name']")
	WebElement EnterButtonName_textbox;
	
	@FindBy(xpath = "//span[text()='Button text']")
	WebElement ButtonText;
	
	@FindBy(xpath = "//select[@data-testid='btn-choose-ar-action-type']")
	WebElement chooseARaction;
	
	@FindBy(xpath = "//select[@data-testid='btn-choose-ar-action']")
	WebElement selectARaction;
	
	@FindBy(xpath = "//div//input[@data-testid='btn-ar-action-text']")
	WebElement URL_InputBox;

	@FindBy(xpath = "//label[contains(text(),'url')]")
	WebElement urlText;
	
	
	public MyarButtonElementPage()
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}
	
	/* FUNCTIONS */
	public void clearURL()
	{
		scrollPageToElement(URL_InputBox);
		clickclearFieldUsingCRTL_A_BackSpace(URL_InputBox, "URL input textbox");
	}
	
	public void copyURL()
	{
		scrollPageToElement(URL_InputBox);
		copyData(URL_InputBox, "URL input textbox");
	}
	public void enterURL(String URL)
	{
		clearAndInput(URL_InputBox, "URL input textbox", URL);
		clickElement(urlText, "url text");
	}
	
	public void selectAr_action(int index)
	{
		selectValueByIndex(selectARaction,index,"Select AR action");
	}
	public void chooseAr_action(int index)
	{
		selectValueByIndex(chooseARaction,index,"Choose AR action");
	}
	public String getWebText_Title()
	{
		return retrieveText(webText_Title,"Web-text title");
	}
	public String getWebText_Content()
	{

		return retrieveText(webText_Content,"Web-text Content");
	}
	public void pasteURL_ButtomName()
	{
		scrollPageToElement(EnterButtonName_textbox);
		click_and_PasteData(EnterButtonName_textbox, "Enter button name textbox");
		clickElement(ButtonText, "BUTTON TEXT");
	}
	public void enterButtonName(String buttonName)
	{
		waitForElementToBeClickable(EnterButtonName_textbox, "Enter button name textbox");
		clearAndInput(EnterButtonName_textbox, "Enter button name textbox", buttonName);
		clickElement(ButtonText, "BUTTON TEXT");
	}
	
}
