package com.techvisio.einstitution.controller;

import java.util.HashMap;
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

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.InquiryWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.InquiryWorkflowManagerImpl;


@RestController
@RequestMapping("/inquiry")
public class InquiryService {
	
	private static final Logger logger = Logger.getLogger(InquiryService.class);
	
	@RequestMapping(value="/{inquiryId}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getInquiry(@PathVariable Long inquiryId) {  
	 
		Response response=new Response();
		try{
		
		InquiryWorkflowManager inquiryWorkflowManager= new InquiryWorkflowManagerImpl();
		
		AdmissionInquiry admissionInquiry= inquiryWorkflowManager.getInquiry(inquiryId);
		
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
	public ResponseEntity<Response> addInquiry(@RequestBody AdmissionInquiry admissionInquiry) {  
		Map<String,Object> enquiryData=new HashMap<String, Object>();
		Response response=new Response();
		try{
			InquiryWorkflowManager inquiryWorkflowManager= new InquiryWorkflowManagerImpl();
		Long enquiryId=inquiryWorkflowManager.addInquiry(admissionInquiry);
		AdmissionInquiry admissionInquiryDB=inquiryWorkflowManager.getInquiry(enquiryId);
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
	public void updateInquiry(@RequestBody AdmissionInquiry admissionInquiry) {  
		InquiryWorkflowManager inquiryWorkflowManager= new InquiryWorkflowManagerImpl();
		inquiryWorkflowManager.updateInquiry(admissionInquiry);
	}
	@RequestMapping(value="/{inquiryId}",method = RequestMethod.DELETE)
	public void deleteInquiry(@PathVariable Long inquiryId) {  
		InquiryWorkflowManager inquiryWorkflowManager= new InquiryWorkflowManagerImpl();
		inquiryWorkflowManager.deleteInquiry(inquiryId);
	}
	
	@RequestMapping(value ="/search/", method = RequestMethod.POST)
	public ResponseEntity<Response> getInquiryByCriteria(@RequestBody SearchCriteria searchCriteria) {
		Response response=new Response();
		try
		{
			AdmissionWorkflowManager workflowManager = new AdmissionWorkflowManagerImpl();
			StudentDetail studentDetail = workflowManager.getStudentDtlBySearchCriteria(searchCriteria);
			response.setResponseBody(studentDetail);
			
			if(studentDetail == null){
				
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
