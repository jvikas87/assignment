package com.congnitive.scale.assignment.constant;

public class Constant {

	public static final String RESPONSE_OK = "ok";

	public static final String RESPONSE_ERROR = "error";

	public static final long MILLIS_IN_TEN_MINUTE = 10 * 60 * 1000;

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
	public static class Message {
		public static final String SESSION_TIME_OUT = "Your session is timeout.Please login again";
		public static final String SESSION_REQUIRED_EXCEPTION = "Please login";
		public static final String BENEFICIARY_DOES_NOT_EXIST = "Beneficiary doesn't exist";
		public static final String INSUFFICIENT_ACCOUNT_BALANCE_EXCEPTION = "Account doesn't have sufficient balance";
		public static final String BENEFIACIRY_ID_NOT_PRESENT = "Beneficiary Id not present in RequestBody";
		public static final String BENEFICIARY_WITH_THIS_ID_NOT_PRESENT = "Beneficiary with this primary doesn't exists";
		public static final String BENEFICIARY_NOT_LINKED_WITH_USER = "Beneficiary is not linked with current user";
	}

	public static class HttpRequestAttr {
		public static final String USER = "ActiveUser";
		public static final String CURRENT_TIME_STAMP = "timestamp";
	}
}
