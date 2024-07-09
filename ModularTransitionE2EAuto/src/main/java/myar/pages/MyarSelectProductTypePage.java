package myar.pages;

import com.qa.base.TestBase;
import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory; 

public class MyarSelectProductTypePage extends TestBase 
{
	// Page Factory - OR:
		@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
		WebElement confirmBtn;
		
		@FindBy(xpath = "//i[@class='fa fa-circle radio-button left-45']")
		WebElement geoProductRadioButtonXpath;

		
		
		// Initializing the Page Factory/Objects:
		public MyarSelectProductTypePage() 
		{
			PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
			// be initialized with this driver
		}

		/* FUNCTIONS */
		public void selectGeoProduct()
		{
			clickElement(geoProductRadioButtonXpath,"Geo Product radio button");
			clickConfirmBtn();
		}
		public void clickConfirmBtn()
		{
			clickElement(confirmBtn, "CONFIRM button");
		}
		
}
