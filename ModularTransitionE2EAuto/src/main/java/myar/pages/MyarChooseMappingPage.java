package myar.pages;

import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarChooseMappingPage extends TestBase
{
	@FindBy(xpath = "//div[text()='No Data Available']")
	WebElement chooseMappingNoDataFound;
	
	@FindBy(xpath = "(//button[@data-testid='rightBtnTestId'])[last()]")
	WebElement cancelButton;
	

	
	public MyarChooseMappingPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */
	
	public boolean isNoDataAvailableDisplayed()
	{
		return isElementPresent(chooseMappingNoDataFound, "No Data Available");
	}
	
	public void clickOnCancelButton()
	{
		clickElement(cancelButton, "Cancel Button");
		
	}
}
