package com.qa.util;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "bedded-loc-provider-status")
	public Object[][] beddedLOCProviderStatus() {
		return new Object[][] { { Constants.RECEIVED }, { Constants.DECISION_PENDING_AUTHORIZATION },
				{ Constants.DECISION_PENDING_BED_AVAILABILITY }, { Constants.DECISION_PENDING_REVIEW },
				{ Constants.REQUEST_MORE_INFORMATION }, { Constants.ACCEPT }, { Constants.DECLINE },
				{ Constants.REOPEN } };
	}

	@DataProvider(name = "discharge-status")
	public Object[][] dischargeStatus() {
		return new Object[][] { { Constants.DELAY_EDD, Constants.DELAY_EDD_DISPLAY_VALUE_AT_INTAKE },
				{ Constants.SUSPEND, Constants.SUSPEND_DISPLAY_VALUE_AT_INTAKE },
				{ Constants.BOOK, Constants.BOOK_DISPLAY_VALUE_AT_INTAKE },
				{ Constants.CANCEL, Constants.CANCEL_DISPLAY_VALUE_AT_INTAKE },
				{ Constants.REOPEN_REFERRAL, Constants.REOPEN_REFERRAL_DISPLAY_VALUE_AT_INTAKE } };
	}
	
	@DataProvider(name = "cancel-and-decline")
	public Object[][] declinedAndCancelledStatus() {
		return new Object[][] { { Constants.CANCEL }, { Constants.DECLINE } };
	}
	
	@DataProvider(name = "intake-sso-authentication-protocols")
	public Object[][] intakeSsoAuthenticationProtocols() {
		return new Object[][] { { "OIDC" }, { "SAML" } };
	}

}
