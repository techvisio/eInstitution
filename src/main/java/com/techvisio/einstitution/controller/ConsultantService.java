package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.manager.impl.ConsultantManagerImpl;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.impl.ConsultantWorkflowManagerImpl;


@RestController
@RequestMapping("/consultant")
public class ConsultantService {

private static final Logger logger = Logger.getLogger(ConsultantService.class);
	
	@RequestMapping(value="/{fileNo}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getConsultantDetail(@PathVariable String fileNo) {  
	  
		Response response = new Response();
		
		try
		{
			ConsultantWorkflowManager workflowManager =  new ConsultantWorkflowManagerImpl();
      		ConsultantDetail consultantDetail = workflowManager.getConsultantDtl(fileNo);
		    
      		response.setResponseBody(consultantDetail);
      		
      		if(consultantDetail == null)
      		{
    			response.setError("No such record found");
    		}
		}
		catch(Exception e)
		{
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addConsultantDtl(@RequestBody ConsultantDetail consultantDetail) {  
        
		Response response = new Response();
		
		try
		{
		ConsultantWorkflowManager workflowManager=new ConsultantWorkflowManagerImpl();
		workflowManager.addConsultantDtl(consultantDetail);
     
		response.setResponseBody(consultantDetail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{fileNo}",method = RequestMethod.DELETE)
	public void deleteConsultantDtl(@PathVariable String fileNo) {  
		ConsultantWorkflowManager workflowManager=new ConsultantWorkflowManagerImpl();
		workflowManager.deleteConsultantDtl(fileNo);
	}

	
}
