package com.qa.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {
    public final static String CENTRALIZED_INTAKE_USER = "CENTRALIZED_INTAKE_USER";
    public final static String READONLY_USER = "READONLY_USER";
    public final static String READONLY_ARCHIVE_USER = "READONLY_ARCHIVE_USER";
    public final static String COMMUNITY_SERVICE_USER = "COMMUNITY_SERVICE_USER";
    public final static String RECASTING_USER = "RECASTING_USER";
    public final static String COMMUNITY_SERVICE_LITE_USER = "COMMUNITY_SERVICE_LITE_USER";
    
    public final static String RECEIVED = "Received";
    public final static String DECISION_PENDING_AUTHORIZATION = "Decision Pending Authorization";
    public final static String DECISION_PENDING_BED_AVAILABILITY = "Decision Pending Bed Availability";
    public final static String DECISION_PENDING_REVIEW = "Decision Pending Review";
    public final static String REQUEST_MORE_INFORMATION = "Request More Information";
    public final static String ACCEPT = "Accept";
    public final static String DECLINE = "Decline";
    public final static String REOPEN = "Reopen Referral";
    
    public final static String DELAY_EDD = "Delay EDD";
    public final static String SUSPEND = "Suspended";
    public final static String BOOK = "Book";
    public final static String CANCEL = "Cancel";
    public final static String REOPEN_REFERRAL = "Re-open Referral";
    
    public final static String DELAY_EDD_DISPLAY_VALUE_AT_INTAKE = "Delayed EDD";
    public final static String SUSPEND_DISPLAY_VALUE_AT_INTAKE = "Suspended";
    public final static String BOOK_DISPLAY_VALUE_AT_INTAKE = "Booked";
    public final static String CANCEL_DISPLAY_VALUE_AT_INTAKE = "Cancelled";
    public final static String REOPEN_REFERRAL_DISPLAY_VALUE_AT_INTAKE = "Re-open Referral";
    
    public final static String DISCHARGE_HOSPITAL_STATUS_NOTIFIED = "notified-status";
    public final static String DISCHARGE_HOSPITAL_STATUS_BOOKED = "hospital-status booked-color";
    public final static String DISCHARGE_HOSPITAL_STATUS_SUSPENDED = "hospital-status suspended-color";
    public final static String DISCHARGE_HOSPITAL_STATUS_DELAYED_EDD = "hospital-status delayed-color";
    public final static String DISCHARGE_HOSPITAL_STATUS_CANCELLED = "hospital-status cancelled-color";
    public final static String DISCHARGE_HOSPITAL_STATUS_REOPENED = "hospital-status reopened-color";
    
    public final static String OME_COLUMN_STATUS = "Status";
    public final static String OME_COLUMN_TEMPLATE = "Template";
    public final static String OME_COLUMN_DESTINATION = "Destination";
    
    public final static String DISCHARGE_NEW_MESSAGE_NOTIFICATION_TEMPLATE = "MESSAGE.PAP_NEW_MSG";
    public final static String DISCHARGE_ACCEPT_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.TARGET.ACCEPT";
    public final static String DISCHARGE_DECLINE_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "ACCEPT.Decline";
    public final static String DISCHARGE_PENDING_REVIEW_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.TARGET.PENDING_REVIEW";
    public final static String DISCHARGE_PENDING_AUTH_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.TARGET.PENDING_AUTH";
    public final static String DISCHARGE_REQUEST_INFO_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.TARGET.MORE_INFO";
    // there is no notification template for D2 for these provider status updates.
    // public final static String DISCHARGE_REOPEN_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "ACCEPT.Reopen";
    // public final static String DISCHARGE_PENDING_BED_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "ACCEPT.Pending Bed";
    public final static String INTAKE_NEW_REFERRAL_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.NEWREFERRAL";
    public final static String INTAKE_NEW_MESSAGE_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.NEWMESSAGE";
    public final static String INTAKE_STATUS_UPDATE_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.REFERRALSTATUSUPDATED";
    public final static String INTAKE_REOPEN_AFTER_DECLINE_NOTIFICATION_TEMPLATE = "CCARE.NOTIFICATION.REOPENED_AFTER_DECLINED";

    public enum HELPPAGES {
        INTAKE_DASHBOARD,
        DISCHARGE_WORKBOOK,
        INTAKE_DOCS,
        INTAKE_ARCHIVE
    }
    
	public static final List<String> COMMON_DECLINE_REASONS = Collections.unmodifiableList(Arrays.asList(
			"Accepted at Another Location", "Limited Equipment Resources", "Limited Staffing",
			"Noncompliant with agency/facility policy", "Behavioral/Mental Health concerns",
			"Does Not Meet Admission Criteria", "Level of Functioning Too Low", "No Following Physician",
			"Patient Too Complex", "Bad Debt/Owes Facility Money", "Insurance Denial", "Issue with Cost of Care",
			"No Payer Source", "Payer Not Accepted", "Discharged to another facility", "Hospital Cancellation",
			"Known with Other Agency/Facility", "Patient has Expired", "Patient/Family Declined or Refused Care",
			"COVID-19", "COVID-19 Limited Staffing, Equipment Resources", "COVID-19 Patient Too Complex",
			"COVID-19 Patient/Family Declined Care"));

	public static final List<String> BEDDED_ONLY_DECLINE_REASONS = Collections.unmodifiableList(Arrays.asList(
			"No Bed Available", "No Secure Units Available", "Concern about Transition to next Level of Care",
			"Level of Functioning Too High", "Payer Beds at Capacity", "COVID-19 No isolation beds available"));

	public static final List<String> HOMEHEALTH_ONLY_DECLINE_REASONS = Collections.unmodifiableList(Arrays
			.asList("Out of Service Area", "Issue with Cost of Medications", "No Home Health Order", "Not Homebound"));
}