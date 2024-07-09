package com.qa.util;

public class PatientInfoBean {
	//D2 Fields
	String firstName;
	String middleName;
	String lastName;
	String referralName;
	String SSN;
	String MRN;
	String accountNumber;
	String addressLine1;
	String addressLine2;
	String city;
	String State;
	String zip;
	String DOB;
	String age;
	String admitDate;
	String notifyDate;
	String notifyTime;
	String estimatedDischargeDate;
	String gender;
	String primaryDiagnosis;
	String secondaryDiagnosis;
	String admitType;
	String payer1Name;
	String payer1PlanID;
	String fullName;
	String unit;
	String room;
	String bed;
	String caseContactTel;
	String caseContact;
	String attendingPhysicianFirstName;
	String attendingPhysicianLastName;
	String primaryCarePhysicianFirstName;
	String primaryCarePhysicianLastName;
	String otherPrimary;
	String otherSecondary;
	String policyNumber;
	String insuredLastName;
	String insuredFirstName;
	String primaryPayerName;
	String primaryPayerMemberID;
	String primaryPayerGroupNumber;
	String primaryPayerSubscriberFirstName;
	String primaryPayerSubscriberLastName;
	String secondaryPayerName;
	String secondaryPayerMemberID;
	String secondaryPayerGroupNumber;
	String secondaryPayerSubscriberFirstName;
	String secondaryPayerSubscriberLastName;
	String referralSource;
	String levelOfcare;
	String currentSystemDate;
	String hospital;
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	String dispoCode;

	public String getDispoCode() {
		return dispoCode;
	}

	public void setDispoCode(String dispoCode) {
		this.dispoCode = dispoCode;
	}

	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getCaseContactTel() {
		return caseContactTel;
	}

	public void setCaseContactTel(String caseContactTel) {
		this.caseContactTel = caseContactTel;
	}

	public String getCaseContact() {
		return caseContact;
	}

	public void setCaseContact(String caseContact) {
		this.caseContact = caseContact;
	}

	public String getAttendingPhysicianFirstName() {
		return attendingPhysicianFirstName;
	}

	public void setAttendingPhysicianFirstName(String attendingPhysicianFirstName) {
		this.attendingPhysicianFirstName = attendingPhysicianFirstName;
	}
	
	public String getAttendingPhysicianLastName() {
		return attendingPhysicianLastName;
	}

	public void setAttendingPhysicianLastName(String attendingPhysicianLastName) {
		this.attendingPhysicianLastName = attendingPhysicianLastName;
	}

	public String getPrimaryCarePhysicianFirstName() {
		return primaryCarePhysicianFirstName;
	}

	public void setPrimaryCarePhysicianFirstName(String primaryCarePhysicianFirstName) {
		this.primaryCarePhysicianFirstName = primaryCarePhysicianFirstName;
	}
	
	public String getPrimaryCarePhysicianLastName() {
		return primaryCarePhysicianLastName;
	}

	public void setPrimaryCarePhysicianLastName(String primaryCarePhysicianLastName) {
		this.primaryCarePhysicianLastName = primaryCarePhysicianLastName;
	}

	public String getOtherPrimary() {
		return otherPrimary;
	}

	public void setOtherPrimary(String otherPrimary) {
		this.otherPrimary = otherPrimary;
	}

	public String getOtherSecondary() {
		return otherSecondary;
	}

	public void setOtherSecondary(String otherSecondary) {
		this.otherSecondary = otherSecondary;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getInsuredLastName() {
		return insuredLastName;
	}

	public void setInsuredLastName(String insuredLastName) {
		this.insuredLastName = insuredLastName;
	}

	public String getInsuredFirstName() {
		return insuredFirstName;
	}

	public void setInsuredFirstName(String insuredFirstName) {
		this.insuredFirstName = insuredFirstName;
	}

	public String getCurrentSystemDate() {
		return currentSystemDate;
	}

	public void setCurrentSystemDate(String currentSystemDate) {
		this.currentSystemDate = currentSystemDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getReferralName() {
		this.referralName = lastName + ", " + firstName;
		return referralName;
	}
	
	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getMRN() {
		return MRN;
	}

	public void setMRN(String mRN) {
		MRN = mRN;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getDOB() {
		return DOB;
	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}
	
	public String getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(String notifyDate) {
		this.notifyDate = notifyDate;
	}
	
	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getEstimatedDischargeDate() {
		return estimatedDischargeDate;
	}

	public void setEstimatedDischargeDate(String estimatedDischargeDate) {
		this.estimatedDischargeDate = estimatedDischargeDate;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPrimaryDiagnosis() {
		return primaryDiagnosis;
	}

	public void setPrimaryDiagnosis(String primaryDiagnosis) {
		this.primaryDiagnosis = primaryDiagnosis;
	}

	public String getSecondaryDiagnosis() {
		return secondaryDiagnosis;
	}

	public void setSecondaryDiagnosis(String secondaryDiagnosis) {
		this.secondaryDiagnosis = secondaryDiagnosis;
	}

	public String getAdmitType() {
		return admitType;
	}

	public void setAdmitType(String admitType) {
		this.admitType = admitType;
	}
	
	public String getPayer1Name() {
		return payer1Name;
	}

	public void setPayer1Name(String payer1Name) {
		this.payer1Name = payer1Name;
	}

	public String getPayer1PlanID() {
		return payer1PlanID;
	}

	public void setPayer1PlanID(String payer1PlanID) {
		this.payer1PlanID = payer1PlanID;
	}
	
	public String getPrimaryPayerName() {
		return primaryPayerName;
	}

	public void setPrimaryPayerName(String primaryPayerName) {
		this.primaryPayerName = primaryPayerName;
	}

	public String getPrimaryPayerMemberID() {
		return primaryPayerMemberID;
	}

	public void setPrimaryPayerMemberID(String primaryPayerMemberID) {
		this.primaryPayerMemberID = primaryPayerMemberID;
	}

	public String getPrimaryPayerGroupNumber() {
		return primaryPayerGroupNumber;
	}

	public void setPrimaryPayerGroupNumber(String primaryPayerGroupNumber) {
		this.primaryPayerGroupNumber = primaryPayerGroupNumber;
	}

	public String getPrimaryPayerSubscriberFirstName() {
		return primaryPayerSubscriberFirstName;
	}

	public void setPrimaryPayerSubscriberFirstName(String primaryPayerSubscriberFirstName) {
		this.primaryPayerSubscriberFirstName = primaryPayerSubscriberFirstName;
	}

	public String getPrimaryPayerSubscriberLastName() {
		return primaryPayerSubscriberLastName;
	}

	public void setPrimaryPayerSubscriberLastName(String primaryPayerSubscriberLastName) {
		this.primaryPayerSubscriberLastName = primaryPayerSubscriberLastName;
	}
	
	public String getSecondaryPayerName() {
		return secondaryPayerName;
	}

	public void setSecondaryPayerName(String secondaryPayerName) {
		this.secondaryPayerName = secondaryPayerName;
	}

	public String getSecondaryPayerMemberID() {
		return secondaryPayerMemberID;
	}

	public void setSecondaryPayerMemberID(String secondaryPayerMemberID) {
		this.secondaryPayerMemberID = secondaryPayerMemberID;
	}

	public String getSecondaryPayerGroupNumber() {
		return secondaryPayerGroupNumber;
	}

	public void setSecondaryPayerGroupNumber(String secondaryPayerGroupNumber) {
		this.secondaryPayerGroupNumber = secondaryPayerGroupNumber;
	}

	public String getSecondaryPayerSubscriberFirstName() {
		return secondaryPayerSubscriberFirstName;
	}

	public void setSecondaryPayerSubscriberFirstName(String secondaryPayerSubscriberFirstName) {
		this.secondaryPayerSubscriberFirstName = secondaryPayerSubscriberFirstName;
	}

	public String getSecondaryPayerSubscriberLastName() {
		return secondaryPayerSubscriberLastName;
	}

	public void setSecondaryPayerSubscriberLastName(String secondaryPayerSubscriberLastName) {
		this.secondaryPayerSubscriberLastName = secondaryPayerSubscriberLastName;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getReferralSource() {
		return referralSource;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public String getLevelOfcare() {
		return levelOfcare;
	}

	public void setLevelOfcare(String levelOfcare) {
		this.levelOfcare = levelOfcare;
	}

}
