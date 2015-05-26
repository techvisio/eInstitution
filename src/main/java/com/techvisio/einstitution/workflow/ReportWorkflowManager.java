package com.techvisio.einstitution.workflow;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionReport;
import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.beans.EnquiryReport;
import com.techvisio.einstitution.beans.EnquiryReportCriteria;

@Component
public interface ReportWorkflowManager {
	
	public List<ConsultantReport> getConsultantReport();

	public List<EnquiryReport> getEnquiryReportByEnquiryReportCriteria(EnquiryReportCriteria enquiryreportCriteria);
	public List<AdmissionReport> getAdmissionReportByReportCriteria(EnquiryReportCriteria enquiryReportCriteria);

}
