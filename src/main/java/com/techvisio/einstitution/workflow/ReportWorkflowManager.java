package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ConsultantReport;

@Component
public interface ReportWorkflowManager {
	
	public List<ConsultantReport> getConsultantReport();
}
