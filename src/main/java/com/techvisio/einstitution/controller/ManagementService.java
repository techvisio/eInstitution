package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.ManagementAdmissionBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.ManagementWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.FeeWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ManagementWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ScholarshipWorkflowManagerImpl;


@RestController
@RequestMapping("/management")
public class ManagementService {

	@RequestMapping(value ="/admission/approval/{fileNo}", method = RequestMethod.GET )
	public ResponseEntity<Response> getAdmissionManagementView(@PathVariable Long fileNo){

		Response response = new Response();
		ResponseEntity<Response> result = new ResponseEntity<Response>(response, HttpStatus.OK);

		try{

			ManagementWorkflowManager managementWorkflowManager = new ManagementWorkflowManagerImpl();
			ManagementAdmissionBean admissionBean = managementWorkflowManager.getAdmissionManagementView(fileNo);

			response.setResponseBody(admissionBean);

		}
		catch(Exception e){
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return result ;
	}

	@RequestMapping(value = "/uapprovedList/{limit}", method = RequestMethod.GET)
	public  ResponseEntity<Response> getUnapprovedAdmissions(@PathVariable int limit){
		
		Response response = new Response();
		try
		{
			ManagementWorkflowManager managementWorkflowManager = new ManagementWorkflowManagerImpl();
		    List<StudentBasicInfo> basicInfo = managementWorkflowManager.getUnapprovedAdmissions(limit);
		    response.setResponseBody(basicInfo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     	}
	

@RequestMapping(value = "/updateManagementChanges", method = RequestMethod.PUT)
public  ResponseEntity<Response> updateManagementChanges(@RequestBody ManagementAdmissionBean admissionBean ){
	
	Response response = new Response();
	try
	{
		ManagementWorkflowManager managementWorkflowManager = new ManagementWorkflowManagerImpl();
	    admissionBean = managementWorkflowManager.updateManagementChanges(admissionBean);
	    
	    response.setResponseBody(admissionBean);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
 	}


}