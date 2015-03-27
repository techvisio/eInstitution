package com.techvisio.einstitution.factory;


public interface UniqueIdentifierGenerator {

	public String getUniqueIdentifierForAdmission();
	public Long getUniqueIdentifierForEnquiry();
    public Long getUniqueIdentifierForTask();	
    public Long getUniqueIdentifierForConsultant();	

}
