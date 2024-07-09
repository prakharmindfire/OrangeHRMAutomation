package myar.pages;

import static com.qa.util.TestUtil.*;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarManageAPI_KeyPage extends TestBase
{

	// Page Factory - OR:
			@FindBy(xpath = "//button[text()='Add API Key']")
			WebElement addAPIKeysBtn;
			
			@FindBy(xpath = "//input[@formcontrolname='keyName']")
			WebElement API_Name_textBox;
			
			@FindBy(xpath = "//input[@value='Add']")
			WebElement apiADD_button;
			
			@FindBy(xpath = "//button[text()=' Close ']")
			WebElement API_KeyClose_button;
			
			@FindBy(xpath = "//tbody//tr")
			WebElement APIkeyTable;
			
			@FindBy(xpath = "//tbody//tr//span/i")
			WebElement APIkeyTable_DeleteIcon;
			
			@FindBy(xpath = "//span[text()='Delete']")
			WebElement DeleteConfirmed;
			
			
			
			
	// Initializing the Page Factory/Objects:
		public MyarManageAPI_KeyPage() 
		{
			PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
			// be initialized with this driver
		}
		
		/* FUNCTIONS */
		
		public void Delete_APIkeyTable()
		{
			clickOnAPIkeyTable_DeleteIcon();
			clickOnDeleteConfirmationOfAPIkeyTable_DeleteIcon();
		}
		
		public void clickOnDeleteConfirmationOfAPIkeyTable_DeleteIcon()
		{
			clickElement(DeleteConfirmed, "Delete button of API key table delete confirmation");
		}
		
		public void clickOnAPIkeyTable_DeleteIcon()
		{
			clickElement(APIkeyTable_DeleteIcon, "Delete Icon of API key Table");
		}
		
		public boolean isMyarInformationAPI_keyTablePresent()
		{
			threadMethod(5000);
			return isElementPresent(APIkeyTable, "Information API key table");
		}
		public void clickAddButtonOfenterAPIName()
		{
			clickElement(apiADD_button, "Add button of Add API key box");
		}
		public void enterAPIName(String APIName)
		{
			clearAndInput(API_Name_textBox, "API Name textbox", APIName);
		}
		
		public void clickAddAPIKeys()
		{			
			clickElement(addAPIKeysBtn, "Add API key button");
		}
		
		public void clickOnCloseBtn_APIKeys()
		{			
			clickElement(API_KeyClose_button, "Close Button of API key box");
		}
}
