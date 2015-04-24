package com.techvisio.einstitution.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.workflow.ReportWorkflowManager;
import com.techvisio.einstitution.workflow.impl.ReportWorkflowManagerImpl;

@RestController
@RequestMapping("/report")
public class ReportService {
	private static final Logger logger = Logger.getLogger(ReportService.class);
	
	@RequestMapping( method = RequestMethod.GET)
	public List<ConsultantReport> getConsultantReport(){
		ReportWorkflowManager manager = new ReportWorkflowManagerImpl();
		List<ConsultantReport> reports = manager.getConsultantReport();
		return reports;
		
	}

}
