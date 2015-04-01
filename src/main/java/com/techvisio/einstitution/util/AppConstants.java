package com.techvisio.einstitution.util;

public interface AppConstants {

	
	public static final String COURSE="COURSE";
	public static final String BRANCH="BRANCH";
	public static final String STATE="STATE";
	public static final String SUBJECT="SUBJECT";
	public static final String QUOTACODE="QUOTACODE";
	public static final String QUALIFICATION="QUALIFICATION";
	public static final String CATEGORY="CATEGORY";
	public static final String FEEHEAD="FEEHEAD";
	public static final String COUNSELLING="COUNSELLING";
	public static final String BLOODGROUP="BLOODGROUP";
	public static final String CONSULTANT="CONSULTANT";
	public static final String SEMESTER="SEMESTER";
	public static final String ENQUIRY="ENQUIRY";
	public static final String ADMISSION="ADMISSION";
	public static final String TASK="TASK";
	public static final Long HOSTEL_FEE_ID=9999L;
	public static final Long TRANSPORT_FEE_ID=9998L;
	public static final Long CASH_DEPOSITE_ID=9996L;
	public static final Long DD_DEPOSITE_ID=9995L;
	public static final Long CHQ_DEPOSITE_ID=9994L;
	
	
	public enum AdmissionWorkFlowStatus{NEW,DOC_RECEIVED,DOC_VERIFIED,FEE_NEGOTIATED,APPROVED,FEE_DEPOSITED,COMPLETE};
	
	public enum EnquiryStatus{OPEN,CLOSED,MOVED_TO_ADMISSION};
}
