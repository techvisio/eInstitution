package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.manager.ReportManager;
import com.techvisio.einstitution.manager.impl.ReportManagerImpl;
import com.techvisio.einstitution.workflow.ReportWorkflowManager;

public class ReportWorkflowManagerImpl implements ReportWorkflowManager {
	
	ReportManager reportManager =  ReportManagerImpl.getInstance();
	
	@Override
	public List<ConsultantReport> getConsultantReport() {
		List<ConsultantReport> reports = reportManager.getConsultantReport();
		return reports;
	}

}
