package myar.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.util.TestUtil.*;

import com.qa.base.TestBase;

public class MyarAddTargetPage extends TestBase
{
	// Page Factory - OR:
	@FindBy(xpath = "//button[@data-testid='target-image-select']")
	WebElement targetImage_select;

	@FindBy(xpath = "//span[contains(text(),'Save changes')]")
	WebElement save_changes_button;
	
	@FindBy(xpath = "//button[@data-testid='target-panel-delete']")
	WebElement TargetImage_Delete_button;
	 
	
	// Initializing the Page Factory/Objects:
	public MyarAddTargetPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/*
	 *Functions
	 */
	public void waitTargetImage_Delete_button_Clickable()
	{
		waitForElementToBeClickable(TargetImage_Delete_button, "Target Delete button");
	}
	public void clickOnSaveChangesButton()
	{
		scrollPageToElementAndClick(save_changes_button, "SAVE CHANGES button of ADD TARGET");
		threadMethod(5000);
	}
	
	public void clickTargetImage_select()
	{
		clickElement(targetImage_select, "Select folder icon of Target image");
	}
}
