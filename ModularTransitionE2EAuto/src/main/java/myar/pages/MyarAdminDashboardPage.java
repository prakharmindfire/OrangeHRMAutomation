package myar.pages;

import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarAdminDashboardPage extends TestBase
{
	// Page Factory - OR:
	
	@FindBy(xpath = "(//input[@id='custom-select'])[1]")
	WebElement AdminUserTab;
	
	@FindBy(xpath = "//span[text()='Administration']")
	WebElement Administration_option;
	
	
	// Initializing the Page Factory/Objects:
		public MyarAdminDashboardPage() 
		{
			PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
			// be initialized with this driver
		}
		
		/* FUNCTIONS */
		
		public void goToAdministration()
		{
			clickOnAdminAccountDD();
			threadMethod(2000);
			clickElement(Administration_option,"Administration option");
			
		}
		public void clickOnAdminAccountDD()
		{
			clickElement(AdminUserTab,"Admin Account drop-down");
			
		}
}
