package com.techvisio.einstitution.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.AdmissionReport;
import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.beans.EnquiryReport;
import com.techvisio.einstitution.beans.EnquiryReportCriteria;
import com.techvisio.einstitution.db.ReportDao;
import com.techvisio.einstitution.manager.ReportManager;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;

@Component
public class ReportManagerImpl implements ReportManager {
	private static CustomLogger logger = CustomLogger.getLogger(ReportManagerImpl.class);
	@Autowired
	ReportDao reportDao;

	private static ReportManagerImpl instance = null;
	public static synchronized ReportManagerImpl getInstance()
	{
		if(instance == null){
			instance=new ReportManagerImpl();
		}
		
		return instance;
	}
	@Override
	public List<ConsultantReport> getConsultantReport() {
		logger.info("{} : calling getConsultantReport method  ",this.getClass().getName());
		List<ConsultantReport> reports = reportDao.getConsultantReport();
		return reports;
	}
	@Override
	public List<EnquiryReport> getEnquiryReportByEnquiryReportCriteria(EnquiryReportCriteria enquiryreportCriteria) {
		logger.info("{} : calling getEnquiryReportByEnquiryReportCriteria method by passing EnquiryReportCriteria:{}  ",this.getClass().getName(),enquiryreportCriteria);
		List<EnquiryReport> enquiryReports = reportDao.getEnquiryReportByEnquiryReportCriteria(enquiryreportCriteria);
		return enquiryReports;
	}
	@Override
	public List<AdmissionReport> getAdmissionReportByReportCriteria(EnquiryReportCriteria enquiryReportCriteria) {
		logger.info("{} : calling getAdmissionReportByReportCriteria method by passing EnquiryReportCriteria:{}  ",this.getClass().getName(),enquiryReportCriteria);
		List<AdmissionReport> admissionReports = reportDao.getAdmissionReportByReportCriteria(enquiryReportCriteria);
		
		return admissionReports;
	}
	
}
