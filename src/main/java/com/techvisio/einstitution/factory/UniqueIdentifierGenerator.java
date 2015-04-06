package com.techvisio.einstitution.factory;

import com.techvisio.einstitution.beans.StudentDetail;


public interface UniqueIdentifierGenerator {

	public Long getUniqueIdentifierForAdmission();
	public Long getUniqueIdentifierForEnquiry();
    public Long getUniqueIdentifierForTask();	
    public Long getUniqueIdentifierForConsultant();
    public String getUniqueIdentifierForRegistration(StudentDetail studentDetail);	

}
