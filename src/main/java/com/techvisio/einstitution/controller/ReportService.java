package com.techvisio.einstitution.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
import com.techvisio.einstitution.beans.AdmissionReportWithUrl;
import com.techvisio.einstitution.beans.ConsultantReport;
import com.techvisio.einstitution.beans.EnquiryReporWithUrl;
import com.techvisio.einstitution.beans.EnquiryReport;
import com.techvisio.einstitution.beans.EnquiryReportCriteria;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.util.ObjectExcelMapper;
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
			 
			EnquiryReporWithUrl reporWithUrl = new EnquiryReporWithUrl();
			List<EnquiryReport> reports = manager.getEnquiryReportByEnquiryReportCriteria(enquiryreportCriteria);
			reporWithUrl.setEnquiryReports(reports);
			if(reports != null){
				String reportName="EnquiryReport"+new Date().getTime()+".xlsx";
				ObjectExcelMapper.createExcel(reportName, reports, new String[]{"name","fatherName","contactNo","course","branch","applicationStatus","createBy","createdDate","updatedBy","updatedDate","remarks","emailId"});
			    reporWithUrl.setReportName(reportName);
			}
			
			response.setResponseBody(reporWithUrl);
			
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
		if(reportId != null && !reportId.contains(".xlsx")){
			reportId=reportId+".xlsx";
		}
		File file = new File(reportId);		
		InputStream targetStream = new FileInputStream(file);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + reportId + "\"");
		org.apache.commons.io.IOUtils.copy(targetStream, response.getOutputStream());
		response.flushBuffer();	
	}
	
	
	@RequestMapping(value ="/searchAdmissionReportByCriteria/", method = RequestMethod.POST)
	public ResponseEntity<Response> getAdmissionReportByReportCriteria(@RequestBody EnquiryReportCriteria enquiryReportCriteria){
		logger.info("{}:  Calling getAdmissionReportByReportCriteria method by passing EnquiryReportCriteria:{} ",this.getClass().getName(), enquiryReportCriteria);
		Response response=new Response();
		try {
			 
			AdmissionReportWithUrl reporWithUrl = new AdmissionReportWithUrl();
			List<AdmissionReport> reports = manager.getAdmissionReportByReportCriteria(enquiryReportCriteria);
			reporWithUrl.setAdmissionReports(reports);
			if(reports != null){
				String reportName="AdmissionReport"+new Date().getTime()+".xlsx";
				ObjectExcelMapper.createExcel(reportName, reports, new String[]{"registrationNo","course","branch","firstName","lastName","fatherName","gender","emailId","selfMobileNo","createdBy","createdOn","referredBy","consultant","remarks","applicationStatus",});
			    reporWithUrl.setReportName(reportName);
			}
			
			response.setResponseBody(reporWithUrl);
			
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
