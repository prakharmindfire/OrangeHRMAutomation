package myar.pages;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyarProductSetUpPage extends TestBase
{
	// Page Factory - OR:
	@FindBy(xpath = "//span[contains(text(),' SELECT ACTIVE COUNTRIES ')]")
	WebElement selectActiveCountriesButton;
	
	// Initializing the Page Factory/Objects:
	public MyarProductSetUpPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */
	
	/* FUNCTIONS */
	public void clickSelectActiveCountriesButton()
	{
		clickElement(selectActiveCountriesButton, "SELECT ACTIVE COUNTRIES");
	}
}
