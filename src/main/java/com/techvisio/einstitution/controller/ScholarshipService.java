package com.techvisio.einstitution.controller;

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
@RequestMapping("/scholarship")
public class ScholarshipService {

	private static CustomLogger logger = CustomLogger.getLogger(ScholarshipService.class);
	
	@Autowired
	ScholarshipWorkflowManager schlrshpWorkflowManager;

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public Scholarship getScholarship(@PathVariable Long fileNo){
		Scholarship scholarship = schlrshpWorkflowManager.getScholarship(fileNo);
		return scholarship;
	}

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.POST)
	public void saveScholarship(@RequestBody Scholarship scholarship, @PathVariable Long fileNo){
		schlrshpWorkflowManager.saveScholarship(scholarship);
	}

	
}
