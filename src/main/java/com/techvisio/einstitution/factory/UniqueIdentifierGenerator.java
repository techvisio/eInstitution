package com.techvisio.einstitution.factory;


public interface UniqueIdentifierGenerator {

	public Long getUniqueIdentifierForAdmission();
	public Long getUniqueIdentifierForEnquiry();
    public Long getUniqueIdentifierForTask();	
    public Long getUniqueIdentifierForConsultant();	

}
