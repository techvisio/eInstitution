package com.techvisio.einstitution.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AdmissionReport;
import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.beans.EnquiryReport;
import com.techvisio.einstitution.beans.EnquiryReportCriteria;
import com.techvisio.einstitution.beans.Response;
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
	
	
	@RequestMapping(value ="/downloadEnquiryReport/{reportId}", method = RequestMethod.GET)
	public void getEnquiryReportAsExcel(@PathVariable String reportId,HttpServletResponse response) throws IOException{
		File file = new File("test.xlsx");		
		file.createNewFile();
		InputStream targetStream = new FileInputStream(file);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + "test.xlsx" + "\"");
		org.apache.commons.io.IOUtils.copy(targetStream, response.getOutputStream());
		response.flushBuffer();	
	}
	
	@RequestMapping(value ="/searchAdmissionReportByCriteria/", method = RequestMethod.POST)
	public ResponseEntity<Response> getAdmissionReportByReportCriteria(@RequestBody EnquiryReportCriteria enquiryReportCriteria){
		logger.info("{}:  Calling getAdmissionReportByReportCriteria method by passing EnquiryReportCriteria:{} ",this.getClass().getName(), enquiryReportCriteria);
		Response response=new Response();
		try {
			 
			List<AdmissionReport> reports = manager.getAdmissionReportByReportCriteria(enquiryReportCriteria);
			response.setResponseBody(reports);
			
			if(reports == null){
				response.setError("No such reports found");
			}
		} catch (Exception e) {
			logger.error("{} :Error While Calling getEnquiryReportByEnquiryReportCriteria method by passing EnquiryReportCriteria:{} ",this.getClass().getName(),enquiryReportCriteria,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}








}
