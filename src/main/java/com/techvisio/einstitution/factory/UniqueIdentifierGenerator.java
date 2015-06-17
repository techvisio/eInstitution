package com.techvisio.einstitution.factory;

import com.techvisio.einstitution.beans.Student;


public interface UniqueIdentifierGenerator {

	public Long getUniqueIdentifierForAdmission();
	public Long getUniqueIdentifierForEnquiry();
    public Long getUniqueIdentifierForTask();	
    public Long getUniqueIdentifierForConsultant();
    public String getUniqueIdentifierForRegistration(Student studentDetail);	

}
