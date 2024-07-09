package myar.pages;

import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.threadMethod;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarUploadExcelPage extends TestBase 
{
	// Page Factory - OR:
	@FindBy(xpath = "//label[contains(text(),' BROWSE FILES ')]")
	WebElement BrowseFilesBtn;
	
	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement uploadBtn;
	
	

	// Initializing the Page Factory/Objects:
	public MyarUploadExcelPage() 
	{
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */
	
	public void clickOnBrowseFilesBtn()
	{
		clickElement(BrowseFilesBtn, "BROWSE FILES button");
		threadMethod(2000);
	}
	
	public void clickOnUploadBtn()
	{
		threadMethod(2000);
		clickElement(uploadBtn, "UPLOAD button");
		
	}
	
	public void BrowseFiles(String FileLoc,String FileuploaderExe)throws AWTException, IOException
	{
		clickOnBrowseFilesBtn();
		Runtime.getRuntime().exec(FileuploaderExe);
		
	}

}
