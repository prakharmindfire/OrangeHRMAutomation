package com.qa.pages;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.input.Input;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.PatientInfoBean;
import static com.qa.util.TestUtil.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntakeArchivePage extends TestBase {

	// Page Factory - OR:
	// Archive search locators
	@FindBy(xpath = "//div[contains(@class,'archive-view')]")
	WebElement archivePage;
	@FindBy(xpath = "//input[@placeholder='Enter name of patient...']")
	WebElement searchByPatientNameTextBox;
	@FindBy(xpath = "//input[@id='referral_source']")
	WebElement searchBySourceNameTextBox;
	@FindBy(xpath = "//input[@id='mat-input-0']")
	WebElement fromDate;
	@FindBy(xpath = "//input[@id='mat-input-1']")
	WebElement toDate;
	@FindBy(xpath = "//button[contains(@class,'search-btn')]")
	WebElement searchButton;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchButtonInArchivePage;
	@FindBy(xpath = "//ul[@class='dropdown-menu']")
	WebElement matchingReferralsInSearchDropdown;
	@FindBy(xpath = "//tbody//mat-row[1]//mat-cell[1]")
	WebElement nameOfFirstReferralInArchivePage;
	// message tab locators
	@FindBy(xpath = "//button[contains(.,'MESSAGES')]")
	WebElement messagesTab;
	@FindBy(xpath = "//div[@class='cdk-overlay-container']//mat-tooltip-component//div")
	WebElement messagesTabTooltip;
	@FindBy(xpath = "//div[contains(@class,'message-card-body')]//div[@class='card-header']")
	WebElement messageSectionHeader;
	@FindBy(xpath = "(//div[@id='patient-notes'])[1]//li//h4")
	List<WebElement> messagesSentByList;
	@FindBy(xpath = "(//div[@id='patient-notes'])[1]//li//p/strong")
	List<WebElement> messagesContentList;

	// internal note locators
	@FindBy(xpath = "//div[contains(@class,'notes-card-body')]//div[@class='card-header']")
	WebElement internalNoteSectionHeader;
	@FindBy(xpath = "(//div[@id='patient-notes'])[2]//li//h4")
	List<WebElement> internalNoteAddedByList;
	@FindBy(xpath = "(//div[@id='patient-notes'])[2]//li//p/strong")
	List<WebElement> internalNoteContentList;
	@FindBy(xpath = "(//div[@id='patient-notes'])[2]//li//span[contains(@class,'note-timestamp')]")
	List<WebElement> internalNoteTimestampList;
	@FindBy(xpath = "//button[contains(.,'Add a Note')]")
	WebElement addNoteButton;
	@FindBy(id = "newNote")
	WebElement addNoteTextArea;
	@FindBy(xpath = "//button[contains(.,'Save')]")
	WebElement saveAddNoteButton;
	
	//team assignment locators
	@FindBy(xpath = "//div[@id='team-assignments-list']//h3[@id='team-assignments-row--name']")
	List<WebElement> teamAssignementList;
	
	//search result columns
	@FindBy(xpath = "//tbody//mat-cell[1]")
	List<WebElement> referralNameColumn;
	@FindBy(xpath = "//button[contains(.,'Last Name, First Name MI')]")
	WebElement referralNameColumnHeader;
	
	@FindBy(xpath = "//tbody//mat-cell[3]")
	List<WebElement> DOBColumn;
	@FindBy(xpath = "//button[contains(.,'DOB')]")
	WebElement DOBColumnHeader;
	
	@FindBy(xpath = "//tbody//mat-cell[7]")
	List<WebElement> admitDateColumn;
	@FindBy(xpath = "//button[contains(.,'Admit')]")
	WebElement admitDateColumnHeader;
	
	@FindBy(xpath = "//tbody//mat-cell[7]")
	List<WebElement> EDDColumn;
	@FindBy(xpath = "//button[contains(.,'Est. Discharge')]")
	WebElement EDDColumnHeader;
	
	@FindBy(xpath = "//tbody//mat-cell[7]")
	List<WebElement> actualDischargeColumn;
	@FindBy(xpath = "//button[contains(.,'Act. Discharge')]")
	WebElement actualDischargeColumnHeader;

	// Dynamic locators
	String referralReactivateButton = "//mat-cell[contains(normalize-space(.),'xxxxx')]//parent::mat-row//button[contains(.,'Reactivate')]";
	String referralInArchivePage = "//mat-cell[contains(normalize-space(.),'xxxxx')]";
	String referralInArchivePageForProvider = "//mat-row[contains(normalize-space(.),'xxxxx')][contains(.,'xxxxx')]";
	String usernameInInternalNoteAutoSuggest = "//mention-list//a[contains(.,'xxxxx')]";
	String archivedReferralDetailsPage = "//nh-intake-archived-patient-detail[contains(.,'xxxxx')]";
	String teamAssignmentCompletedUsername = "//div[@id='team-assignments-list']//h3[contains(.,'xxxxx')]//parent::li//following-sibling::li//div//p[1]";
	String teamAssignmentCompletedTimestamp = "//div[@id='team-assignments-list']//h3[contains(.,'xxxxx')]//parent::li//following-sibling::li//div//p[2]";

	// Initializing the Page Factory/Objects:
	public IntakeArchivePage() {
		PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
														// be initialized with this driver
	}

	// page methods
	public void enterReferralNameInsearchBox(String referralFullName) {
		input(searchByPatientNameTextBox, "referral name search box", referralFullName);
	}

	public void clickSearchButton() {
		// wait for search button to enable and click on search button inside search
		// modal
		isEnabled(searchButton, "search button");
		clickElement(searchButton, "search button");
		waitForLoaderToDisappear();
	}

	public boolean referralSearch(PatientInfoBean referralDetails) {
		// enter referral full name in text box
		enterReferralNameInsearchBox(referralDetails.getFullName());
		// click on search button
		try {
			Thread.sleep(2000); // selenium clicks too quickly and search does not populate always.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickSearchButton();
		// check if search dropdown appeared
		if (isElementPresent(matchingReferralsInSearchDropdown, "archive search result dropdown")) {
			// look for referral name in search dropdown results
			String allSearchResults = retrieveText(matchingReferralsInSearchDropdown,
					"matching referrals in search dropdown");
			return allSearchResults.contains(referralDetails.getFullName());
		} else {
			reportLog("referral is not present");
			return false;
		}
	}

	public void reactivateReferral(String referralName) {
		// click on reactivate button for the referral
		clickElement(prepareWebElementWithDynamicXpath(referralReactivateButton, referralName.split(",")[0]),
				"reactivate button for referral: " + referralName);
		waitForLoaderToDisappear();
		reportLog("Reactivated referral: " + referralName);
	}

	public void clickOnReferralInArchivePage(String referralName) {
		clickElement(prepareWebElementWithDynamicXpath(referralInArchivePage, referralName),
				"referral " + referralName + " in archive page");
		waitForLoaderToDisappear();
	}

	public void clickOnReferralInArchivePageForASpecificProvider(String referralName, String providerName) {
		clickElement(
				prepareWebElementWithDynamicXpath(referralInArchivePageForProvider.replaceFirst("xxxxx", referralName),
						providerName),
				"referral " + referralName + " in archive page");
		waitForLoaderToDisappear();
	}

	public void clickOnMessagesTab() {
		clickElement(messagesTab, "messages tab");
		waitForLoaderToDisappear();
	}

	public String getMessagesTabTooltip() {
		hoverOver(messagesTab);
		return retrieveText(messagesTabTooltip, "messages tab tooltip");
	}

	public String getMessageSectionHeader() {
		return retrieveText(messageSectionHeader, "message section header");
	}

	public String getInterNoteSectionHeader() {
		return retrieveText(internalNoteSectionHeader, "internal notes section header");
	}

	public ArrayList<String> getMessageSentByList() {

		ArrayList<String> messagesSentByList = new ArrayList<String>();
		for (WebElement messageSentBy : this.messagesSentByList) {
			messagesSentByList.add(retrieveText(messageSentBy, "message sent by"));
		}
		return messagesSentByList;

	}

	public ArrayList<String> getMessageContentList() {

		ArrayList<String> messagesContentList = new ArrayList<String>();
		for (WebElement messageContent : this.messagesContentList) {
			messagesContentList.add(retrieveText(messageContent, "message content"));
		}
		return messagesContentList;

	}

	public ArrayList<String> getInternalNoteAddedByList() {

		ArrayList<String> internalNoteAddedByList = new ArrayList<String>();
		for (WebElement internalNoteAddedBy : this.internalNoteAddedByList) {
			internalNoteAddedByList.add(retrieveText(internalNoteAddedBy, "message sent by"));
		}
		return internalNoteAddedByList;

	}

	public ArrayList<String> getInternalNoteContentList() {

		ArrayList<String> internalNoteContentList = new ArrayList<String>();
		for (WebElement internalNoteContent : this.internalNoteContentList) {
			internalNoteContentList.add(retrieveText(internalNoteContent, "message sent by"));
		}
		return internalNoteContentList;

	}

	public ArrayList<String> getInternalNoteTimestampList() {

		ArrayList<String> internalNoteTimestampList = new ArrayList<String>();
		for (WebElement internalNoteTimestamp : this.internalNoteTimestampList) {
			internalNoteTimestampList.add(retrieveText(internalNoteTimestamp, "message sent by"));
		}
		return internalNoteTimestampList;

	}

	public void clickOnAddNoteButton() {
		clickElement(addNoteButton, "add notes button");
	}

	public void enterNote(String note) {
		input(addNoteTextArea, "internal note text area", note);
	}

	public void clickOnInternalNotesSaveButton() {
		clickElement(saveAddNoteButton, "internal notes save button");
		waitForLoaderToDisappear();
	}

	public void tagUser(String username) {
		enterNote("@" + username);
		clickElement(prepareWebElementWithDynamicXpath(usernameInInternalNoteAutoSuggest, username),
				username + "in auto suggest list");
	}

	public String addInternalNote(String note) {
		clickOnAddNoteButton();
		enterNote(note);
		clickOnInternalNotesSaveButton();
		return note;
	}

	public String addInternalNoteAndTagAUser(String note, String username) {
		clickOnAddNoteButton();
		tagUser(username);
		enterNote(note);
		clickOnInternalNotesSaveButton();
		return note;
	}

	public boolean presenceOfArchiveReferralDetailsPage(String referralName) {
		return isElementPresent(prepareWebElementWithDynamicXpath(archivedReferralDetailsPage, referralName),
				referralName + "archived referral details page");
	}

	public void clicknameOfFirstReferralInArchivePage() {
		clickAndWaitForPageLoad(nameOfFirstReferralInArchivePage, "nameOfFirstReferralInArchivePage");
	}
	
	 public boolean isSearchByPatientNameTextBoxPresent() {
	        return isElementPresent(searchByPatientNameTextBox, "Search by patient name");

	 }
	 
	 public boolean isSearchBySourceNameTextBoxPresent() {
	        return isElementPresent(searchBySourceNameTextBox, "Search by source name");

	 }
	 public boolean isFromDatePresent() {
	        return isElementPresent(fromDate, "From date");

	 }
	 public boolean isToDatePresent() {
	        return isElementPresent(toDate, "From date");

	 }
	 public void inputValueInSearchByPatient() throws InterruptedException {
	        // click on close button in search modal
	        clearAndInput(searchByPatientNameTextBox, "search field by patient name", "Auto");
	        
	     }
	 public boolean isSearchbuttonInArchivePagePresent() {
	        return isElementPresent(searchButtonInArchivePage, "search button in archive page");
	 }
	 
	 public int getTeamAssignmentCount() {
		 return teamAssignementList.size();
	 }
	 
	 public String getTeamAssignmentNameByIndex(int index) {
		 return retrieveText(teamAssignementList.get(index), "team number: " + (index+1));
	 }
	 
	 public boolean isTeamAssignmentCompletedUsernamePresent(String team) {
		 return isElementPresent(prepareWebElementWithDynamicXpath(teamAssignmentCompletedUsername, team), "team assignment completed username");
	 }
	 
	 public boolean isTeamAssignmentCompletedTimestampPresent(String team) {
		 return isElementPresent(prepareWebElementWithDynamicXpath(teamAssignmentCompletedTimestamp, team), "team assignment completed timestamp");
	 }
	 
	 public String getTeamAssignmentCompletedUsername(String team) {
		 return retrieveText(prepareWebElementWithDynamicXpath(teamAssignmentCompletedUsername, team), team + "completed username");
	 }
	 
	 public String getTeamAssignmentCompletedTimestamp(String team) {
		 return retrieveText(prepareWebElementWithDynamicXpath(teamAssignmentCompletedTimestamp, team), team + "completed timestamp");
	 }
	 
	 public void clickReferralNameColumnHeader() {
		 clickElement(referralNameColumnHeader, "referral name column header");
		 waitForLoaderToDisappear();
	 }
	 
	 public void clickDOBColumnHeader() {
		 clickElement(DOBColumnHeader, "DOB column header");
		 waitForLoaderToDisappear();
	 }
	 
	 public void clickAdmitDateColumnHeader() {
		 clickElement(admitDateColumnHeader, "admit date column header");
		 waitForLoaderToDisappear();
	 }
	 
	 public void clickEDDColumnHeader() {
		 clickElement(EDDColumnHeader, "EDD column header");
		 waitForLoaderToDisappear();
	 }
	 
	 public void clickActualDischargeColumnHeader() {
		 clickElement(actualDischargeColumnHeader, "actual discharge column header");
		 waitForLoaderToDisappear();
	 }
	 
	 // order = ascending or order = descending
	 public void verifyReferralNameColumnSort(String order) {
		 List<String> referralNamesInColumn = new ArrayList<String>();
		 for(WebElement referralName: referralNameColumn) {
			 String referralNameParts[] = retrieveText(referralName, "referral name").split(", ");
			 String referralNameText = referralNameParts[1] + " " + referralNameParts[0];
			 referralNamesInColumn.add(referralNameText);
		 }
		 List<String> referralNamesInColumnOrdered = referralNamesInColumn;
		 Collections.sort(referralNamesInColumnOrdered);
		 
		 if(order == "ascending") {
			 referralNamesInColumnOrdered.equals(referralNamesInColumn);
		 }else if (order == "descending") {
			 Collections.reverse(referralNamesInColumnOrdered);
			 referralNamesInColumnOrdered.equals(referralNamesInColumn);
		 }
	 }
	 
		// order = ascending or order = descending
		public void verifyDOBColumnSort(String order) {
			List<Integer> DOBInColumn = new ArrayList<Integer>();
			for (WebElement DOB : DOBColumn) {
				String DOBParts[] = retrieveText(DOB, "DOB").split("/");
				if(Integer.parseInt(DOBParts[2]) > 30) {
					DOBParts[2] = 19 + DOBParts[2];
				} else {
					DOBParts[2] = 20 + DOBParts[2];
				}
				String DOBText = DOBParts[2]+DOBParts[0]+DOBParts[1];
				DOBInColumn.add(Integer.parseInt(DOBText));
			}
			List<Integer> DOBInColumnOrdered = DOBInColumn;
			Collections.sort(DOBInColumnOrdered);

			if (order == "ascending") {
				DOBInColumnOrdered.equals(DOBInColumn);
			} else if (order == "descending") {
				Collections.reverse(DOBInColumnOrdered);
				DOBInColumnOrdered.equals(DOBInColumn);
			}
		}
		
		// order = ascending or order = descending
		public void verifyAdmitDateColumnSort(String order) {
			List<Integer> admitDateInColumn = new ArrayList<Integer>();
			for (WebElement admitDate : admitDateColumn) {
				String admitDateParts[] = retrieveText(admitDate, "admitDate").split("/");
				if (Integer.parseInt(admitDateParts[2]) > 30) {
					admitDateParts[2] = 19 + admitDateParts[2];
				} else {
					admitDateParts[2] = 20 + admitDateParts[2];
				}
				String admitDateText = admitDateParts[2] + admitDateParts[0] + admitDateParts[1];
				admitDateInColumn.add(Integer.parseInt(admitDateText));
			}
			List<Integer> admitDateInColumnOrdered = admitDateInColumn;
			Collections.sort(admitDateInColumnOrdered);

			if (order == "ascending") {
				admitDateInColumnOrdered.equals(admitDateInColumn);
			} else if (order == "descending") {
				Collections.reverse(admitDateInColumnOrdered);
				admitDateInColumnOrdered.equals(admitDateInColumn);
			}
		}
		
		// order = ascending or order = descending
		public void verifyEDDColumnSort(String order) {
			List<Integer> EDDInColumn = new ArrayList<Integer>();
			for (WebElement EDD : EDDColumn) {
				String EDDParts[] = retrieveText(EDD, "EDD").split("/");
				if (Integer.parseInt(EDDParts[2]) > 30) {
					EDDParts[2] = 19 + EDDParts[2];
				} else {
					EDDParts[2] = 20 + EDDParts[2];
				}
				String EDDText = EDDParts[2] + EDDParts[0] + EDDParts[1];
				EDDInColumn.add(Integer.parseInt(EDDText));
			}
			List<Integer> EDDInColumnOrdered = EDDInColumn;
			Collections.sort(EDDInColumnOrdered);

			if (order == "ascending") {
				EDDInColumnOrdered.equals(EDDInColumn);
			} else if (order == "descending") {
				Collections.reverse(EDDInColumnOrdered);
				EDDInColumnOrdered.equals(EDDInColumn);
			}
		}
		
		// order = ascending or order = descending
		public void verifyActualDischargeColumnSort(String order) {
			List<Integer> actualDischargeInColumn = new ArrayList<Integer>();
			for (WebElement actualDischarge : admitDateColumn) {
				String actualDischargeParts[] = retrieveText(actualDischarge, "actualDischarge").split("/");
				if (Integer.parseInt(actualDischargeParts[2]) > 30) {
					actualDischargeParts[2] = 19 + actualDischargeParts[2];
				} else {
					actualDischargeParts[2] = 20 + actualDischargeParts[2];
				}
				String actualDischargeText = actualDischargeParts[2] + actualDischargeParts[0]
						+ actualDischargeParts[1];
				actualDischargeInColumn.add(Integer.parseInt(actualDischargeText));
			}
			List<Integer> actualDischargeInColumnOrdered = actualDischargeInColumn;
			Collections.sort(actualDischargeInColumnOrdered);

			if (order == "ascending") {
				actualDischargeInColumnOrdered.equals(actualDischargeInColumn);
			} else if (order == "descending") {
				Collections.reverse(actualDischargeInColumnOrdered);
				actualDischargeInColumnOrdered.equals(actualDischargeInColumn);
			}
		}
		
		public boolean isArchivePagePresent() {
			return isElementPresent(archivePage, "archive page");
		}
}
