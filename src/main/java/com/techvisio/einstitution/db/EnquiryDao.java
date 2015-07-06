package com.techvisio.einstitution.db;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.Remark;
import com.techvisio.einstitution.beans.SearchCriteria;

@Component
public interface EnquiryDao {

	public AdmissionEnquiry getInquiry(Long inquiryId);
	public void saveInquiry (AdmissionEnquiry admissionInquiry);
}
