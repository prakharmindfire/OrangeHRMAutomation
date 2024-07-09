package com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class DischargedocumentManagerPage extends TestBase {

	// Initializing the Page Objects:

	@FindBy(xpath = "//a[@class ='chosen-single']/span")
	WebElement selectCompanyDrop;

	@FindBy(xpath = "//*[@id=\"drpCompanyID_chosen\"]/div/div/input")
	WebElement searchBox;

	@FindBy(xpath = "//button[contains(text(),'Select')]")
	WebElement selectBtn;

	@FindBy(xpath = "//a[@id='lnkCreateCompany']")
	WebElement createNewCmpny;

	// Create New Company Modal
	@FindBy(xpath = "//h4[contains(text(),'Create Company')]")
	WebElement createCompanyText;

	@FindBy(xpath = "//input[@id='CompanyName']")
	WebElement companyNameTextBox;

	@FindBy(xpath = "//div[contains(text(),'Inbox')]")
	WebElement header;

	@FindBy(className = "top-page-header")
	WebElement submissionHead;

	@FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[6]/ul[1]/li[1]/a[1]/span[1]")
	WebElement submissionsBtn;

	// Initializing the Page Factory/Objects:
	public DischargedocumentManagerPage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
														// be initialized with this driver
	}

	// Actions/Methods on this page:
	public String verifySelectCompanyPageTitle() {
		return getdriver().getCurrentUrl();

	}

	public boolean createCompanyTextMsg() {
		return createCompanyText.isDisplayed();
	}

	public void autoSelectCompany() {
		selectCompanyDrop.click();
	}

	public void searchSelectCompany(String search) {
		searchBox.sendKeys(search);
	}

	public void selectCompanyOption() {
		searchBox.sendKeys(Keys.ENTER);
	}

	public void selectButton() {
		selectBtn.submit();
	}

	public String inboxHeader() {
		return header.getText();
	}

	public String submissionHeader() {
		return submissionHead.getText();
	}

	public boolean submPageBtn() {
		return submissionsBtn.isDisplayed();
	}

	public void getHeader() {

		if (submPageBtn()) {
			String header = submissionHeader();
			String actualHeader = "Submissions";
			Assert.assertEquals(header, actualHeader);
			reportLog("<b><font color='Purple'>Validated the </font><b> " + header
					+ " header in Submission Inbox Screen.");
			TestUtil.threadMethod(2000);
		} else {
			String inhead = inboxHeader();
			String actualHead = "Inbox";
			Assert.assertEquals(inhead, actualHead);
			reportLog("<b><font color='Purple'>Validated the </font><b> " + inhead + " header in Inbox Screen.");
			TestUtil.threadMethod(2000);
		}

	}

}
