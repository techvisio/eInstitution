 package com.techvisio.einstitution.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.EnquiryAndTask;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;
import com.techvisio.einstitution.workflow.impl.EnquiryWorkflowManagerImpl;


@RestController
@RequestMapping("/enquiry")
public class EnquiryService {
	
	private static CustomLogger logger = CustomLogger.getLogger(EnquiryService.class);
	
	@Autowired
	EnquiryWorkflowManager enquiryWorkflowManager;
	
	@RequestMapping(value="/{enquiryId}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getEnquiryandTask(@PathVariable Long enquiryId) {  
		logger.info("{}  Calling getEnquiryandTask method by passing enquiry Id : {}",this.getClass().getName(),enquiryId);
		Response response=new Response();
		try{
		
		AdmissionEnquiry admissionEnquiry= enquiryWorkflowManager.getInquiry(enquiryId);
		response.setResponseBody(admissionEnquiry);
		}
		catch(Exception e)
		{
			logger.error("{} :Error While Calling getEnquiryandTask method by passing enquiry Id : {}",this.getClass().getName(),enquiryId,e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{enquiryId}",method = RequestMethod.POST)
	public ResponseEntity<Response> addEnquiryandTask(@RequestBody AdmissionEnquiry admissionEnquiry , @PathVariable Long enquiryId) {  
		Response response=new Response();
		try{
		enquiryWorkflowManager.saveInquiry(admissionEnquiry);
		AdmissionEnquiry admissionInquiryDB=enquiryWorkflowManager.getInquiry(enquiryId);
		if(admissionInquiryDB != null){
			response.setResponseBody(admissionInquiryDB);
		}
		}
		catch(Exception e){
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
		
}
