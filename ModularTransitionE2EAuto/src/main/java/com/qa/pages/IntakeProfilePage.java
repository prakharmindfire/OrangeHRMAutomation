package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.util.TestUtil.*;
public class IntakeProfilePage extends TestBase {

	@FindBy(xpath = "//div[@class='profile-view']")
	WebElement profileTabContainer;
	@FindBy(xpath = "//*[text()=' Services ']")
	WebElement services;
	@FindBy(xpath = "//ul[@class='nav']//li//a")
	List<WebElement>listOfFacilities;

	@FindBy(xpath = "//h4[contains(text(),' Level of Care ')]/parent::div/ul/li")
	List<WebElement>listOfLoc;
	@FindBy(xpath = "//div[contains(@class,'clinical-services-section')]//div[@class='profile-service-section']//div[@class='ng-star-inserted']")
	List<WebElement>listofServices;
	@FindBy(xpath="//a[@class='d-inline-block edit-profile-btn']")
	WebElement editProfileButton;
	@FindBy(id="eppml_phoneNumber")
	WebElement editPhoneNumberField;
	@FindBy(xpath="//button[contains(.,'SAVE')]")
	WebElement saveButtonOnProfile;
	@FindBy(xpath="//h4[contains(.,'Contact Info')]/parent::div//li[1]")
	WebElement phoneNumberValue;
	@FindBy(xpath="//h4[contains(.,' Address ')]/parent::div//span[1]")
	WebElement addressline1;
	@FindBy(xpath="//h4[contains(.,' Address ')]/parent::div//span[2]")
	WebElement addressline2;
	@FindBy(xpath="//h4[contains(.,' Address ')]/parent::div//span[3]")
	WebElement city;
	@FindBy(xpath="//h4[contains(.,' Address ')]/parent::div//span[4]")
	WebElement county;
	@FindBy(xpath="//h4[contains(.,' Address ')]/parent::div//span[5]")
	WebElement metro;
	@FindBy(xpath="//mat-panel-title[contains(.,'Services')]")
	WebElement serviceTab;
	@FindBy(xpath="//h4[contains(.,'Clinical Services')]/parent::div/a")
	WebElement editClinicalServiceButton;
	@FindBy(xpath="//mat-panel-title[contains(.,' Physicians/Clinicians ')]")
	WebElement physicianTab;
	@FindBy(xpath="//mat-panel-title[contains(.,' Physicians/Clinicians ')]/parent::span//a")
	WebElement editButtonOnPhysicianTab;
	@FindBy(id="ep-name0")
	WebElement physicianNameField;
	@FindBy(xpath="//button[@angularticsaction='EditPhysicians']")
	WebElement saveButtonForPhysician;
	@FindBy(xpath="//mat-panel-title[contains(.,' Physicians/Clinicians ')]/ancestor::mat-expansion-panel/div/div[1]/div[1]/div[1]/div[1]/div[1]")
	WebElement firstPhysicianName;
	@FindBy(xpath="//button[contains(@angularticsaction,'SaveClinicalServices')]")
	WebElement saveButtonClincicalService;
	@FindBy(xpath="//mat-panel-title[contains(.,' Payers ')]")
	WebElement payerTab;
	@FindBy(xpath="//mat-panel-title[contains(.,' Payers ')]/parent::span/div[1]/a")
	WebElement editPayersButton;
	@FindBy(xpath="//input[@id='payerName0']")
	WebElement firstPayerNamefield;
	@FindBy(xpath="//button[contains(@angularticsaction,'SavePayers')]")
	WebElement saveButtonEditPayer;
	@FindBy(xpath="//mat-panel-title[contains(.,' Payers ')]/ancestor::mat-expansion-panel/div/div[1]/div[1]/div[1]/div[1]/div[1]")
	WebElement payerNameValue;
	


	String providerSelect = "//a[contains(.,'xxxxx')]";
	String physicianName = "//div[contains(text(),'xxxxxx')]";

	public WebElement getEditProfileSaveButtonxpath() {
		return saveButtonOnProfile;
	}


	public IntakeProfilePage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
		// be initialized with this driver
	}




	public Boolean isProfileTabContainerDisplayed(){
		return isElementPresent(profileTabContainer,"profile Tab Container");

	}
	public void expandServices() {
		clickAndWaitForPageLoad(services, "Service panel is expanded");
	}
	public List<String> getAllLOCInProfileTab(){
		List<String> allLocValue=new ArrayList<>();

		for(int i=0;i<listOfFacilities.size();i++) {
			clickAndWaitForPageLoad(listOfFacilities.get(i), "List of facility"+i+1);
			for(int j=0;j<listOfLoc.size();j++) {
				allLocValue.add(retrieveText(listOfLoc.get(j), "LOC Text"));
			}
		}
		return allLocValue;
	}
	 public List<String> getAllSerivcesInProfileTab(){
		List<String> allSerivceInProfile=new ArrayList<>();
		expandServices();

		for(int i=0;i<listOfFacilities.size();i++) {
			clickAndWaitForPageLoad(listOfFacilities.get(i), "List of facility"+i+1);
			for(int j=0;j<listofServices.size();j++) {
				allSerivceInProfile.add(retrieveText(listofServices.get(j), "Service Text"));
			}
		}
		return allSerivceInProfile;
	}

	public void clickOnEditProfileButton() {

		
		clickAndWaitForPageLoad(editProfileButton, "Edit Butotn for Profile Details");
	}

	public void inputPhoneNumber(String phonenum) {

		clearAndInput(editPhoneNumberField, "Phone Number field in profile", phonenum);
	}

	public String getPhoneNumberValue() {

		return retrieveText(phoneNumberValue, "Phone number value in contact Info");
	}

	public void clickOnProviderforProfile(String providername) {

		clickElement(prepareWebElementWithDynamicXpath(providerSelect, providername), "Provider in profile");
	}

	public String getAdressLine1Text() {
		return retrieveText(addressline1, "AddressLine1 on Profile");
	}

	public String getAdressLine2Text() {
		return retrieveText(addressline2, "AddressLine2 on Profile");
	}

	public String getCityText() {
		return retrieveText(city, "city on Profile");
	}

	public String getCountyText() {
		return retrieveText(county, "County on Profile");
	}

	public String getMetroText() {
		return retrieveText(metro, "Metro on Profile");
	}

	public void clickOnServiceTab() {

		clickElement(serviceTab, "Clinical Service Tab");
	}

	public void clickOnEditClinicalService() {
		clickElement(editClinicalServiceButton, "Edit button for clinical service");
	}
	
	public void clickOnPhysicianTab() {

		clickElement(physicianTab, "Physician details Tab");
	}
	
	public void clickEditButtonOnPhysicianTab() {

		clickElement(editButtonOnPhysicianTab, "Edit Button Physician details Tab");
	}
	
	public void inputPhysicianNameField(String physicianName) {
		
		clearAndInput(physicianNameField, "Physician name Field", physicianName);
	}
	
	public void clickOnSaveforPhysician() {
		clickElement(saveButtonForPhysician, "Save Button on Edit Physician");
	}
	
	public String getPhysicianNameText() {
		
		return retrieveText(firstPhysicianName, "Name of First Physician");
	}
	
	public void clickOnSaveforClicnicalService() {
		clickElement(saveButtonClincicalService, "Save Button on Edit Clicnical Service");
	}
	
	public void clickOnEditPayersButton() {
		clickElement(editPayersButton, "Edit Button for Payers");
	}
	
	public void clickOnPayersTab() {
		clickElement(payerTab, "Payers Tab");
	}
	
	public void inputPayersName(String payerName) {
		
		clearAndInput(firstPayerNamefield, "Payer Name", payerName);
	}
	
	public void clickOnSaveButtonEditPayerModal() {
		
		clickElement(saveButtonEditPayer, "Save button on Edit Payer modal");
	}
	
	public String getPayerNameText() {
		
		return retrieveText(payerNameValue, "Payer Name text ");
	}
	
	







}
