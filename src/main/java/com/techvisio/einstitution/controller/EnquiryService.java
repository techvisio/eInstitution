 package com.techvisio.einstitution.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
import com.techvisio.einstitution.beans.EnquiryAndTaskBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;
import com.techvisio.einstitution.workflow.impl.EnquiryWorkflowManagerImpl;


@RestController
@RequestMapping("/inquiry")
public class EnquiryService {
	
	private static final Logger logger = Logger.getLogger(EnquiryService.class);
	
	@RequestMapping(value="/{inquiryId}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getInquiry(@PathVariable Long inquiryId) {  
	 
		Response response=new Response();
		try{
		
		EnquiryWorkflowManager enquiryWorkflowManager= new EnquiryWorkflowManagerImpl();
		
		EnquiryAndTaskBean admissionInquiAndTask= enquiryWorkflowManager.getEnquiryandTask(inquiryId);
		
		response.setResponseBody(admissionInquiry);
                  		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			e.getLocalizedMessage();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addInquiry(@RequestBody AdmissionEnquiry admissionInquiry) {  
		Map<String,Object> enquiryData=new HashMap<String, Object>();
		Response response=new Response();
		try{
			EnquiryWorkflowManager inquiryWorkflowManager= new EnquiryWorkflowManagerImpl();
		Long enquiryId=inquiryWorkflowManager.addEnquiryandTask(admissionInquiry);
		AdmissionEnquiry admissionInquiryDB=inquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionInquiryDB != null){
			enquiryData.put(AppConstants.ENQUIRY, admissionInquiryDB);
		}
		response.setResponseBody(enquiryData);
		}
		catch(Exception e){
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateInquiry(@RequestBody AdmissionEnquiry admissionInquiry) {  
		EnquiryWorkflowManager inquiryWorkflowManager= new EnquiryWorkflowManagerImpl();
		inquiryWorkflowManager.updateEnquiryandTask(admissionInquiry);
	}
	@RequestMapping(value="/{inquiryId}",method = RequestMethod.DELETE)
	public void deleteInquiry(@PathVariable Long inquiryId) {  
		EnquiryWorkflowManager inquiryWorkflowManager= new EnquiryWorkflowManagerImpl();
		inquiryWorkflowManager.deleteInquiry(inquiryId);
	}
	
	@RequestMapping(value ="/search/", method = RequestMethod.POST)
	public ResponseEntity<Response> getInquiryByCriteria(@RequestBody SearchCriteria searchCriteria) {
		Response response=new Response();
		try
		{
			EnquiryWorkflowManager workflowManager = new EnquiryWorkflowManagerImpl();
			List<AdmissionEnquiry> admissionInquiry = workflowManager.searchInqByCriteria(searchCriteria);
			response.setResponseBody(admissionInquiry);
			
			if(admissionInquiry == null){
				
				response.setError("No such record found");
			}
			}
		catch(EmptyResultDataAccessException e)
		{
			response.setError("No such record found");
		}
		catch(IncorrectResultSizeDataAccessException e)
		{
			response.setError("multiple record found for this idetifier");
		}
			catch(Exception e)
			{
			e.printStackTrace();
			response.setError(e.getMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}

}
