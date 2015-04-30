package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.manager.ReportManager;
import com.techvisio.einstitution.manager.impl.ReportManagerImpl;
import com.techvisio.einstitution.workflow.ReportWorkflowManager;
@Component
public class ReportWorkflowManagerImpl implements ReportWorkflowManager {
	
	@Autowired
	ReportManager reportManager;
	
	@Override
	public List<ConsultantReport> getConsultantReport() {
		List<ConsultantReport> reports = reportManager.getConsultantReport();
		return reports;
	}

}
