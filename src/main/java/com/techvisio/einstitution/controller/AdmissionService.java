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

import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;

@RestController
@RequestMapping("/admission")
public class AdmissionService {

	private static final Logger logger = Logger
			.getLogger(AdmissionService.class);

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public StudentDetail getStudentDetail(@PathVariable String fileNo) {
		
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		
		StudentDetail studentDetail = admissionManager.getStudentDtl(fileNo);
		return studentDetail;
	}

	@RequestMapping(method = RequestMethod.POST)
	public StudentDetail addStudentDtl(@RequestBody StudentDetail studentDetail) {
		//AdmissionManager admissionManager = new AdmissionManagerImpl();
		//admissionManager.addStudentDtl(studentDetail);
		return new StudentDetail();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateStudentDtl(@RequestBody StudentDetail studentDetail) {
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		admissionManager.updateStudentDtl(studentDetail);
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
