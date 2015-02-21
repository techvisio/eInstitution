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
	public static final Long HOSTEL_FEE_ID=9999L;
	public static final Long TRANSPORT_FEE_ID=9998L;
	
	public enum AdmissionWorkFlowStatus{NEW,DOC_RECEIVED,DOC_VERIFIED,FEE_NEGOTIATED,APPROVED,FEE_DEPOSITED,COMPLETE};
	
}
