package myar.pages;

import static com.qa.util.TestUtil.clearAndInput;
import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.isElementPresent;
import static com.qa.util.TestUtil.prepareWebElementWithDynamicXpath;
import static com.qa.util.TestUtil.pressEnter;
import static com.qa.util.TestUtil.retrieveText;
import static com.qa.util.TestUtil.scrollPageToElementAndClick;
import static com.qa.util.TestUtil.threadMethod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarMediaPage extends TestBase {
	// Page Factory - OR:

	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;

	@FindBy(xpath = "//div[@title]")
	WebElement client;

	@FindBy(xpath = "//button[@data-testid='mosaic-view']")
	WebElement addFolderMedia;

	@FindBy(xpath = "(//button[@data-testid='tile-view'])[2]")
	WebElement renameFolderMedia;

	@FindBy(xpath = "(//button[@data-testid='tile-view'])[1]")
	WebElement deleteFolderMedia;

	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement deleteButton;

	@FindBy(xpath = "//input[@placeholder='Please enter a folder name!']")
	WebElement pleaseEnterFolderNameTextBox;

	@FindBy(xpath = "//span[text()='Add folder']")
	WebElement addfolderButton;

	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement RenamefolderButton;

	@FindBy(xpath = "//button[@data-testid='rightBtnTestId']")
	WebElement cancelButton;
	@FindBy(xpath = "//button[@data-testid='header-close-item']")
	WebElement closeHeader;

	@FindBy(xpath = "//input[@name='search-text']")
	WebElement searchBox;

	@FindBy(xpath = "//div[contains(@class,'img')]")
	WebElement select_img;

	@FindBy(xpath = "(//button[contains(@class,'btn wide-btn text-uppercase black-back white-font')])[3]")
	WebElement select_button;

	public String folder = "//div[@title='xxxxx']";

	// Initializing the Page Factory/Objects:
	public MyarMediaPage() {
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

	public void browseSelectTargetImage(String mediaName) {
		searchMedia(mediaName);
		clickToSelectImage();
		clickOnSelectButton();
		clickOn_Cancel_Button();
	}

	public void searchMedia(String mediaName) {
		clearAndInput(searchBox, "Search box", mediaName);
		pressEnter();
		threadMethod(5000);
	}

	public void clickOnSelectButton() {
		clickElement(select_button, "SELECT button");
	}

	public void clickToSelectImage() {
		clickElement(select_img, "Searched Image");
	}

	public void click_closeX_toCloseMedia() {
		threadMethod(2000);
		clickElement(closeHeader, "Close X icon of MEDIA modal");
	}

	public void clickOn_Cancel_Button() {

		scrollPageToElementAndClick(cancelButton, "CANCEL button");
	}

	public void clickOnDeleteButton() {
		clickElement(deleteButton, "Delete button");
	}

	public void clickOnDeleteFolderMedia() {
		clickElement(deleteFolderMedia, "Delete Folder icon of Media");
	}

	public void clickOnrenameFolderMedia() {
		clickElement(renameFolderMedia, "Rename Folder icon of Media");
	}

	public void clickOnRenameFolderBtn() {
		clickElement(RenamefolderButton, "Rename Folder button");
	}

	public void clickOnFolderUsingName(String folderName) {
		clickElement(prepareWebElementWithDynamicXpath(folder, folderName), "Folder : " + folderName);
	}

	public boolean isFolderPresent(String folderName) {
		threadMethod(5000);
		return isElementPresent(prepareWebElementWithDynamicXpath(folder, folderName), "Folder : " + folderName);
	}

	public void enterFolderName(String folderName) {
		clearAndInput(pleaseEnterFolderNameTextBox, "Please enter a folder name textbox", folderName);
	}

	public void clickOnClient() {
		clickElement(client, "client deepak");
	}

	public void clickOnAddFolderMedia() {
		clickElement(addFolderMedia, "Add Folder icon of Media");
	}

	public void clickOnAddFolderBtn() {
		clickElement(addfolderButton, "Add Folder button");
	}

}
