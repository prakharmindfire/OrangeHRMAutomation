package myar.pages;

import static com.qa.util.TestUtil.clearAndInput;
import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.clickElementwithJS;
import static com.qa.util.TestUtil.isElementPresent;
import static com.qa.util.TestUtil.retrieveText;
import static com.qa.util.TestUtil.scrollPageToElement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarEditProfile extends TestBase {
	// Page Factory - OR:

	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;

	@FindBy(xpath = "//a[text()=' Manage API keys ']")
	WebElement ManageAPIKeys;

	@FindBy(xpath = "(//div//button)[1]")
	WebElement editEmailIcon;

	@FindBy(id = "new-email-id")
	WebElement enterNewEmailAddress_textBox;

	@FindBy(id = "repeat-new-email-id")
	WebElement re_enterNewEmailAdd_textBox;

	@FindBy(xpath = "//span[text()='Change email address']")
	WebElement changeEmailAddress_Button;

	@FindBy(xpath = "//label[text()='Enter the token received via email']")
	WebElement Enter_the_token_received_via_email;

	@FindBy(xpath = "(//button[@data-testid='header-close-item'])[last()]")
	WebElement closeX;

	// Initializing the Page Factory/Objects:
	public MyarEditProfile() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}

	/* FUNCTIONS */

	public String getWebText_Title() {

		return retrieveText(webText_Title, "Web-text title");
	}

	public String getWebText_Content() {

		return retrieveText(webText_Content, "Web-text Content");
	}

	public boolean isLEnter_the_token_received_via_email_Displayed() {
		return isElementPresent(Enter_the_token_received_via_email,
				"Enter the token received via email text box label");
	}

	public void clickOnCloseX() {
		clickElement(closeX, "close X button");
	}

	public void clickOnchangeEmailAddress_Button() {
		clickElement(changeEmailAddress_Button, "CHANGE EMAIL ADDRESS button");
	}

	public void re_enterNewEmailAddress(String newEmailAddress) {
		clearAndInput(re_enterNewEmailAdd_textBox, "Re-enter new email address textbox", newEmailAddress);
	}

	public void enterNewEmailAddress(String newEmailAddress) {
		clearAndInput(enterNewEmailAddress_textBox, "Enter new email address textbox", newEmailAddress);
	}

	public void clickOnEditEmailIcon() {
		// threadMethod(1000);
		clickElementwithJS(editEmailIcon, "Edit email icon");
	}

	public void clickManageAPIKeys() {
		scrollPageToElement(ManageAPIKeys);
		clickElement(ManageAPIKeys, "Manage API keys");
	}

}
