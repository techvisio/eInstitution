package com.techvisio.einstitution.beans;

import java.util.List;

public class AdmissionReportWithUrl {

   private List<AdmissionReport> admissionReports;
   private String reportName;
public List<AdmissionReport> getAdmissionReports() {
	return admissionReports;
}
public void setAdmissionReports(List<AdmissionReport> admissionReports) {
	this.admissionReports = admissionReports;
}
public String getReportName() {
	return reportName;
}
public void setReportName(String reportName) {
	this.reportName = reportName;
}
   
}
