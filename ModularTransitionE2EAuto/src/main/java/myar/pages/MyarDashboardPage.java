package myar.pages;

import static com.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarDashboardPage extends TestBase
{

	// Page Factory - OR:
	@FindBy(xpath = "//a[contains(text(),'DASHBOARD')]")
	WebElement DASHBOARD_modal;
	
	@FindBy(xpath = "//div[text()=' MEDIA ']")
	WebElement MEDIA_modal;
	
	@FindBy(xpath = "//div[contains(text(),' PRODUCTS ')]")
	WebElement PRODUCTS_modal;
	
	@FindBy(xpath = "//div[contains(text(),' SCENES ')]")
	WebElement SCENES_modal;
	
	@FindBy(xpath = "//div[@class='text md-inline-block drop-down']")
	WebElement myar_userDropdown;
	
	@FindBy(xpath = "//*[contains(text(),'Log out')]")
	WebElement myar_logout;
	
	@FindBy(xpath = "//*[contains(text(),'Edit Profile')]")
	WebElement myar_EditProfile;
	
	@FindBy(xpath = "//a[contains(text(),' EDITOR ')]")
	WebElement EDITOR_modal;
	
	@FindBy(xpath = "//span[contains(text(),'Start a new product')]")
	WebElement start_a_newProduct;
	
	
	

	// Initializing the Page Factory/Objects:
	public MyarDashboardPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}
	
	/* FUNCTIONS */
	
	public void clickOnStartNewProduct()
	{
		clickElement(start_a_newProduct,"START A NEW PRODUCT");
	}
	
	public void clickOnSCENES()
	{
		threadMethod(2000);
		clickElement(SCENES_modal,"SCENES modal");
	}
	public void clickOnEDITOR()
	{
		clickElement(EDITOR_modal,"EDITOR modal");
	}
	public void clickOnPRODUCTS()
	{
		threadMethod(2000);
		clickElement(PRODUCTS_modal,"PRODUCTS modal");
	}
	public boolean isMyarDashboardPresent()
	{
		return isElementPresent(DASHBOARD_modal, "DASHBOARD modal");
	}
	
	public void clickOnMEDIA()
	{
		threadMethod(2000);
		clickElement(MEDIA_modal,"MEDIA");
	}
	
	public void clickOnUserName()
	{
		scrollPageToElement(myar_userDropdown);
		clickElement(myar_userDropdown,"MYAR user drop-down");
	}
	
	public void clickOnEditProfile()
	{
		clickOnUserName();
		clickElement(myar_EditProfile, "Edit Profile option");
	}
	
	public void logout() 
	{
		threadMethod(5000);
		clickOnUserName();
		clickElement(myar_logout, "Log out option");
        waitForPageToLoad();
        reportLog("Logged Out");
    }
}
