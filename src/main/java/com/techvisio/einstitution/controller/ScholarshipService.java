package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
import com.techvisio.einstitution.workflow.impl.ConsultantWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ScholarshipWorkflowManagerImpl;



@RestController
@RequestMapping("/scholarship")
public class ScholarshipService {

	private static CustomLogger logger = CustomLogger.getLogger(ScholarshipService.class);
	
	@Autowired
	ScholarshipWorkflowManager workflowManager;
	
	@RequestMapping(value="/{fileNo}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getScholarshipDetail(@PathVariable Long fileNo) {  
		logger.info("{}:  Calling getScholarshipDetail method by passing fileno : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		
		try
		{
			
    		Scholarship scholarshipDetail = workflowManager.getScholarshipDetail(fileNo);
		    
    		response.setResponseBody(scholarshipDetail);
    		
    		if(scholarshipDetail == null)
    		{
  			response.setError("No such record found");
  		}
		}
		catch(EmptyResultDataAccessException e)
		{
			response.setError("No such record found");
		}
		
		catch(Exception e)
		{
			logger.error("{}: Error While Calling getScholarshipDetail method by passing fileno : {}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
			//e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addScholarshipDetail(@RequestBody Scholarship scholarshipDetail) {  
		logger.info("{} : Calling addScholarDetail method for fileno : {}",this.getClass().getName(), scholarshipDetail.getFileNo());
		Response response = new Response();
		
		try
		{
		workflowManager.addScholarDetail(scholarshipDetail);
   
		response.setResponseBody(scholarshipDetail);
		}
		catch(Exception e)
		{
			logger.error("{}: Error While Calling addScholarDetail method for fileno : {}",this.getClass().getName(),scholarshipDetail.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
		
	@RequestMapping(value="/{fileNo}",method = RequestMethod.DELETE)
	public void deleteConsultantDtl(@PathVariable Long fileNo) {  
		logger.info("{}:  Calling deleteScholarshipDetail method by fileno : {}",this.getClass().getName(), fileNo);
		workflowManager.deleteScholarshipDetail(fileNo);
	}

	
}
