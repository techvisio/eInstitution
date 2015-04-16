package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
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
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
import com.techvisio.einstitution.workflow.impl.ConsultantWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ScholarshipWorkflowManagerImpl;



@RestController
@RequestMapping("/scholarship")
public class ScholarshipService {

	private static final Logger logger = Logger.getLogger(ScholarshipService.class);
	
	@RequestMapping(value="/{fileNo}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getScholarshipDetail(@PathVariable Long fileNo) {  
	  
		Response response = new Response();
		
		try
		{
			ScholarshipWorkflowManager workflowManager =  new ScholarshipWorkflowManagerImpl();
    		ScholarshipDetail scholarshipDetail = workflowManager.getScholarshipDetail(fileNo);
		    
    		response.setResponseBody(scholarshipDetail);
    		
    		if(scholarshipDetail == null)
    		{
  			response.setError("No such record found");
  		}
		}
		catch(EmptyResultDataAccessException e)
		{
			e.printStackTrace();
			response.setError("No such record found");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addScholarshipDetail(@RequestBody ScholarshipDetail scholarshipDetail) {  
      
		Response response = new Response();
		
		try
		{
		ScholarshipWorkflowManager workflowManager=new ScholarshipWorkflowManagerImpl();
		workflowManager.addScholarDetail(scholarshipDetail);
   
		response.setResponseBody(scholarshipDetail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
		
	@RequestMapping(value="/{fileNo}",method = RequestMethod.DELETE)
	public void deleteConsultantDtl(@PathVariable Long fileNo) {  
		ScholarshipWorkflowManager workflowManager=new ScholarshipWorkflowManagerImpl();
		workflowManager.deleteScholarshipDetail(fileNo);
	}

	
}
