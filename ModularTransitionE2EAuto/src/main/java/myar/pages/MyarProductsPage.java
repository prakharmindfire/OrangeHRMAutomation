package myar.pages;

import static com.qa.util.TestUtil.clearAndInput;
import static com.qa.util.TestUtil.clickElement;
import static com.qa.util.TestUtil.clickElementwithJS;
import static com.qa.util.TestUtil.isElementPresent;
import static com.qa.util.TestUtil.prepareWebElementWithDynamicXpath;
import static com.qa.util.TestUtil.retrieveText;
import static com.qa.util.TestUtil.threadMethod;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class MyarProductsPage extends TestBase {
	// Page Factory - OR:

	@FindBy(xpath = "//div[contains(@class,'sn-title')]")
	WebElement webText_Title;

	@FindBy(xpath = "//simple-notifications//div[contains(@class,'sn-content')]")
	WebElement webText_Content;

	@FindBy(xpath = "//div[@title]")
	WebElement client;

	@FindBy(xpath = "//button[@data-testid='folder-create-product']")
	WebElement addFolderProducts;

	@FindBy(xpath = "//input[@placeholder='Please enter a folder name!']")
	WebElement pleaseEnterFolderNameTextBox;

	@FindBy(xpath = "//span[text()='Add folder']")
	WebElement addfolderButton;

	@FindBy(xpath = "//button[@data-testid='folder-edit-product']")
	WebElement renameFolderProducts;

	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement RenamefolderButton;

	@FindBy(xpath = "//button[@data-testid='folder-delete-product']")
	WebElement deleteFolderProducts;

	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement deleteButton;

	@FindBy(xpath = "//button[@data-testid='header-close-item']")
	WebElement closeHeader;

	@FindBy(xpath = "//button[@data-testid='import-product-excel-btn']")
	WebElement importExcelIcon;

	@FindBy(xpath = "//input[@name='search-text']")
	WebElement productSearchInputBox;

	@FindBy(xpath = "//div[@class='delete-btn']/i")
	WebElement deleteFirstProductInListIcon;

	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement confirmDeleteButton;

	@FindBy(xpath = "//h4[contains(text(),'NO DATA AVAILABLE.')]")
	WebElement noDataAvailable;

	@FindBy(xpath = "(//button[@class='bten round-btn white-back mdgrey-font'])")
	WebElement showLanguages;

	@FindBy(xpath = "(//div[@class='act-deact-btn']//button[@data-testid='product-setup-test-id'])")
	WebElement openProductInEditorIconButton;

	public String folder = "//div[@title='xxxxx']";
	String Productnamexpath = "//span[contains(text(),'xxxxx')]";

	// Initializing the Page Factory/Objects:
	public MyarProductsPage() {
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

	public void clickToOpenProductInEditor() {
		clickElementwithJS(openProductInEditorIconButton, "Open product in editor icon");
	}

	public void clickOnShowLanguages() {
		clickElementwithJS(showLanguages, "Show languages icon");
	}

	public boolean isNoDataAvailable_Displayed() {
		threadMethod(5000);
		return isElementPresent(noDataAvailable, "NO DATA AVAILABLE");
	}

	public void deleteProduct() {
		threadMethod(5000);
		clickElementwithJS(deleteFirstProductInListIcon, "Delete");
		threadMethod(5000);
		clickElement(confirmDeleteButton, "Confirm Delete");
	}

	public String returnProductNameifPresent(String productName) {
		return retrieveText(prepareWebElementWithDynamicXpath(Productnamexpath, productName), "Product Name");
	}

	public void searchProduct(String productName) {
		clearAndInput(productSearchInputBox, "Search Product textbox", productName);
		getdriver().switchTo().activeElement().sendKeys(Keys.ENTER);

	}

	public void clickOnImportExcelIcon() {
		clickElement(importExcelIcon, "Import excel");
	}

	public void click_closeX_toCloseProducts() {
		threadMethod(2000);
		clickElement(closeHeader, "Close X icon of Products modal");
	}

	public void clickOnDeleteButton() {
		clickElement(deleteButton, "Delete button");
	}

	public void clickOnDeleteFolderProducts() {
		clickElement(deleteFolderProducts, "Delete Folder icon of Products");
	}

	public void clickOnRenameFolderBtn() {
		clickElement(RenamefolderButton, "Rename Folder button");
	}

	public void clickOnrenameFolderProducts() {
		clickElement(renameFolderProducts, "Rename Folder icon of Products");
	}

	public void clickOnFolderUsingName(String folderName) {
		clickElement(prepareWebElementWithDynamicXpath(folder, folderName), "Folder : " + folderName);
	}

	public boolean isFolderPresent(String folderName) {
		threadMethod(5000);
		return isElementPresent(prepareWebElementWithDynamicXpath(folder, folderName), "Folder : " + folderName);
	}

	public void clickOnAddFolderBtn() {
		clickElement(addfolderButton, "Add Folder button");
	}

	public void enterFolderName(String folderName) {
		clearAndInput(pleaseEnterFolderNameTextBox, "Please enter a folder name textbox", folderName);
	}

	public void clickOnAddFolderProducts() {
		clickElement(addFolderProducts, "Add Folder icon of Products");
	}

	public void clickOnClient() {
		clickElement(client, "client deepak");
	}

}
