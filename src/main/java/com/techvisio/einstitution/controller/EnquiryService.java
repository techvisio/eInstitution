 package com.techvisio.einstitution.controller;

import java.util.List;

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
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.EnquiryWorkflowManager;


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
		
		EnquiryAndTask enquiryAndTask= enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		response.setResponseBody(enquiryAndTask);
		}
		catch(Exception e)
		{
			logger.error("{} :Error While Calling getEnquiryandTask method by passing enquiry Id : {}",this.getClass().getName(),enquiryId,e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{enquiryId}",method = RequestMethod.POST)
	public ResponseEntity<Response> addEnquiryandTask(@RequestBody EnquiryAndTask enquiryAndTask , @PathVariable Long enquiryId) {  
		Response response=new Response();
		try{
		enquiryWorkflowManager.saveEnquiryAndTask(enquiryAndTask);
		EnquiryAndTask admissionEnquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionEnquiryDB != null){
			response.setResponseBody(admissionEnquiryDB);
		}
		}
		catch(Exception e){
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{enquiryId}",method = RequestMethod.PUT)
	public ResponseEntity<Response> UpdateEnquiryandTask(@RequestBody EnquiryAndTask enquiryAndTask , @PathVariable Long enquiryId) {  
		Response response=new Response();
		try{
			enquiryWorkflowManager.saveEnquiryAndTask(enquiryAndTask);
			EnquiryAndTask admissionEnquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
			if(admissionEnquiryDB != null){
				response.setResponseBody(admissionEnquiryDB);
		}
		}
		catch(Exception e){
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/search/", method = RequestMethod.POST)
	public ResponseEntity<Response> getInquiryByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}  Calling searchInqByCriteria method by for enquiry Id : {}",this.getClass().getName(), searchCriteria.getInquryId());
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
			logger.error("{} :Error While Calling getInquiryByCriteria method for enquiry Id : {}",this.getClass().getName(),searchCriteria.getInquryId(),e);
			response.setError(e.getMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}

	@RequestMapping(value="/proceedToAdmission/",method = RequestMethod.POST)
	public ResponseEntity<Response> proceedToAdmission(@RequestBody EnquiryAndTask enquirynTask) {  
		logger.info("{}  Calling proceedToAdmission method for : enquiry Id : {}",this.getClass().getName(), enquirynTask.getAdmissionEnquiry().getEnquiryId());
		Response response=new Response();
		try{
			
		Long enquiryId=enquiryWorkflowManager.proceedToAdmission(enquirynTask);
		EnquiryAndTask admissionInquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionInquiryDB != null){
			response.setResponseBody(admissionInquiryDB);
		}
		}
		catch(Exception e){
			logger.error("{} :Error While Calling proceedToAdmission method for : enquiry Id : {}",this.getClass().getName(),enquirynTask.getAdmissionEnquiry().getEnquiryId(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/toggleEnquiryStatus",method = RequestMethod.PUT)
	public ResponseEntity<Response> toggleEnquiryStatus(@RequestBody EnquiryAndTask enquirynTask) {  
		logger.info("{}  Calling toggleEnquiryStatus method for enquiry Id : {}",this.getClass().getName(), enquirynTask.getAdmissionEnquiry().getEnquiryId());
		Response response=new Response();
		try{
			
		Long enquiryId=enquiryWorkflowManager.toggleEnquiryStatus(enquirynTask);
		EnquiryAndTask admissionInquiryDB=enquiryWorkflowManager.getEnquiryandTask(enquiryId);
		if(admissionInquiryDB != null){
		}
		response.setResponseBody(admissionInquiryDB);
		}
		catch(Exception e){
			logger.error("{} :Error While Calling toggleEnquiryStatus method for enquiry Id : {}",this.getClass().getName(),enquirynTask.getAdmissionEnquiry().getEnquiryId(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
}
