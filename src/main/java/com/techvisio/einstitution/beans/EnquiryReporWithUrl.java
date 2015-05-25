package com.techvisio.einstitution.beans;

import java.util.List;

public class EnquiryReporWithUrl {

	private List<EnquiryReport> enquiryReports;
	private String reportName;
	
	public List<EnquiryReport> getEnquiryReports() {
		return enquiryReports;
	}
	public void setEnquiryReports(List<EnquiryReport> enquiryReports) {
		this.enquiryReports = enquiryReports;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
}
