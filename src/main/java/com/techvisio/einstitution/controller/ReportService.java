package com.techvisio.einstitution.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.beans.EnquiryReport;
import com.techvisio.einstitution.beans.EnquiryReportCriteria;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ReportWorkflowManager;

@RestController
@RequestMapping("/report")


public class ReportService {
	private static CustomLogger logger = CustomLogger.getLogger(ReportService.class);
	
	@Autowired
	ReportWorkflowManager manager;
	
	@RequestMapping( method = RequestMethod.GET)
	public List<ConsultantReport> getConsultantReport(){
		logger.info("{}:  Calling getConsultantReport method",this.getClass().getName());
		
		List<ConsultantReport> reports = manager.getConsultantReport();
		return reports;
		
	}

	@RequestMapping(value ="/searchEnquiryReportByCriteria/", method = RequestMethod.POST)
	public ResponseEntity<Response> getEnquiryReportByEnquiryReportCriteria(@RequestBody EnquiryReportCriteria enquiryreportCriteria){
		logger.info("{}:  Calling getEnquiryReportByEnquiryReportCriteria method by passing EnquiryReportCriteria:{} ",this.getClass().getName(), enquiryreportCriteria);
		Response response=new Response();
		try {
			 
			List<EnquiryReport> reports = manager.getEnquiryReportByEnquiryReportCriteria(enquiryreportCriteria);
			response.setResponseBody(reports);
			
			if(reports == null){
				response.setError("No such reports found");
			}
		} catch (Exception e) {
			logger.error("{} :Error While Calling getEnquiryReportByEnquiryReportCriteria method by passing EnquiryReportCriteria:{} ",this.getClass().getName(),enquiryreportCriteria,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
}
