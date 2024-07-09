package myar.pages;

import org.openqa.selenium.support.PageFactory;
import static com.qa.util.TestUtil.*;

import com.qa.base.TestBase;

public class MyarElementsPage extends TestBase
{

	String editButton ="(//div[@class='item-parent']//button[@class='bten round-btn black-back white-font'])[xxxxx]";
	String elementName = "(//div[@class='filename'])[xxxxx]";
	
	// Page Factory - OR:

	// Initializing the Page Factory/Objects:
	public MyarElementsPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */
	public void goToSpecificElement(int index)
	{
		clickElement(prepareWebElementWithDynamicXpath(editButton, String.valueOf(index)), "Edit button of "+retrieveText(prepareWebElementWithDynamicXpath(elementName, String.valueOf(index)), "Element Name"));
	}
	
}
