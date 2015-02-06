package com.techvisio.einstitution.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;

@RestController
@RequestMapping("/admission")
public class AdmissionService {

	private static final Logger logger = Logger
			.getLogger(AdmissionService.class);

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentDetail(@PathVariable String fileNo) {
		Response response=new Response();
		try
		{
		AdmissionWorkflowManager workflowManager = new AdmissionWorkflowManagerImpl();
		
		StudentDetail studentDetail = workflowManager.getStudentDetails(fileNo);
		response.setResponseBody(studentDetail);
		if(studentDetail == null){
			
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
	public ResponseEntity<Response> addStudentDtl(@RequestBody StudentDetail studentDetail) {
		Response response=new Response();
		try
		{
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		admissionManager.addStudentDtl(studentDetail);
		response.setResponseBody(studentDetail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response> updateStudentDtl(@RequestBody StudentDetail studentDetail) {
		
		Response response = new Response();
		try
		{
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		admissionManager.updateStudentDtl(studentDetail);
		response.setResponseBody(studentDetail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		@RequestMapping(value = "/{fileNo}",method = RequestMethod.DELETE)
	public void deleteStudentDtl(@PathVariable String fileNo) {
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		admissionManager.deleteSudentDtl(fileNo);
	}
	
	public ResponseEntity<Map<String,List>> getMasterData(){
		Map<String,List> masterData=new HashMap<String, List>();
		//create list of FieldDesc
		masterData.put("personalDetailAttributes", null);
		return new ResponseEntity<Map<String,List>>(masterData,HttpStatus.OK);
	}

}
