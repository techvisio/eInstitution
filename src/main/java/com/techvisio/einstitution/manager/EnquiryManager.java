package com.techvisio.einstitution.manager;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.SearchCriteria;
@Component
public interface EnquiryManager {

	public AdmissionEnquiry getInquiry(Long inquiryId);
	public void saveInquiry (AdmissionEnquiry admissionInquiry);

}
