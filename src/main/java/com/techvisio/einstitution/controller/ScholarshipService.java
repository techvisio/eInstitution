package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;



@RestController
@RequestMapping("/service/scholarship")
public class ScholarshipService {

	private static CustomLogger logger = CustomLogger.getLogger(ScholarshipService.class);

	@Autowired
	ScholarshipWorkflowManager schlrshpWorkflowManager;

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getScholarship(@PathVariable Long fileNo){
		logger.info("{}  Calling getScholarship method for file no:{}",this.getClass().getName(), fileNo);
		Response response = new Response();
		Scholarship scholarship = schlrshpWorkflowManager.getScholarship(fileNo);
		response.setResponseBody(scholarship);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.POST)
	public ResponseEntity<Response> saveScholarship(@RequestBody Scholarship scholarship, @PathVariable Long fileNo){
		logger.info("{}  Calling saveScholarship method for file no:{}",this.getClass().getName(), fileNo);
		Response response = new Response();
		schlrshpWorkflowManager.saveScholarship(scholarship);
		Scholarship scholarshpFromDB = schlrshpWorkflowManager.getScholarship(fileNo);
		response.setResponseBody(scholarshpFromDB);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}


}
