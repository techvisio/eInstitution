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
import com.techvisio.einstitution.beans.EnquiryAndTaskBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;
import com.techvisio.einstitution.workflow.impl.EnquiryWorkflowManagerImpl;


@RestController
@RequestMapping("/enquiry")
public class EnquiryService {
	
	private static final Logger logger = Logger.getLogger(EnquiryService.class);
	
	@Autowired
	EnquiryWorkflowManager enquiryWorkflowManager;
	
	@RequestMapping(value="/enquiryByTaskDate/",method = RequestMethod.GET)
	public ResponseEntity<Response> getInquiryByTaskDate(Date taskDate){
		Response response=new Response();
		try{
		Date date = new Date();
		taskDate=CommonUtil.removeTimeFromDate(date);
		List<AdmissionEnquiry> admissionEnquiry= enquiryWorkflowManager.getInquiryByTaskDate(taskDate);
		response.setResponseBody(admissionEnquiry);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			e.getLocalizedMessage();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/{enquiryId}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getEnquiryandTask(@PathVariable Long enquiryId) {  
	 
		Response response=new Response();
		try{
		
		EnquiryAndTaskBean admissionInquiAndTask= enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		response.setResponseBody(admissionInquiAndTask);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addEnquiryandTask(@RequestBody EnquiryAndTaskBean enquirynTask) {  
		Response response=new Response();
		try{
			
		Long enquiryId=enquiryWorkflowManager.addEnquiryandTask(enquirynTask);
		EnquiryAndTaskBean admissionInquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionInquiryDB != null){
			response.setResponseBody(admissionInquiryDB);
		}
		}
		catch(Exception e){
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response> updateEnquiryandTask(@RequestBody EnquiryAndTaskBean enquirynTask) {  
		Response response=new Response();
		try{
			
		Long enquiryId=enquiryWorkflowManager.updateEnquiryandTask(enquirynTask);
		EnquiryAndTaskBean admissionInquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionInquiryDB != null){
		}
		response.setResponseBody(admissionInquiryDB);
		}
		catch(Exception e){
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{enquiryId}",method = RequestMethod.DELETE)
	public void deleteInquiry(@PathVariable Long inquiryId) {  
		enquiryWorkflowManager.deleteInquiry(inquiryId);
	}
	
	@RequestMapping(value ="/search/", method = RequestMethod.POST)
	public ResponseEntity<Response> getInquiryByCriteria(@RequestBody SearchCriteria searchCriteria) {
		Response response=new Response();
		try
		{
			List<AdmissionEnquiry> admissionInquiry = enquiryWorkflowManager.searchInqByCriteria(searchCriteria);
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

	@RequestMapping(value="/proceedToAdmission/",method = RequestMethod.POST)
	public ResponseEntity<Response> proceedToAdmission(@RequestBody EnquiryAndTaskBean enquirynTask) {  
		Response response=new Response();
		try{
			
		Long enquiryId=enquiryWorkflowManager.proceedToAdmission(enquirynTask);
		EnquiryAndTaskBean admissionInquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionInquiryDB != null){
			response.setResponseBody(admissionInquiryDB);
		}
		}
		catch(Exception e){
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/toggleEnquiryStatus",method = RequestMethod.PUT)
	public ResponseEntity<Response> toggleEnquiryStatus(@RequestBody EnquiryAndTaskBean enquirynTask) {  
		Response response=new Response();
		try{
			
		Long enquiryId=enquiryWorkflowManager.toggleEnquiryStatus(enquirynTask);
		EnquiryAndTaskBean admissionInquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionInquiryDB != null){
		}
		response.setResponseBody(admissionInquiryDB);
		}
		catch(Exception e){
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
}
