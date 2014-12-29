package com.techvisio.einstitution.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.StudentAcademicDetail;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.impl.AdmissionManagerImpl;

@RestController
@RequestMapping("/admission")
public class AdmissionService {

	private static final Logger logger = Logger
			.getLogger(AdmissionService.class);

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<StudentDetail> getStudentDetail(@PathVariable String fileNo) {
		StudentDetail studentDetail = new StudentDetail();
		studentDetail.setFileNo(fileNo);
		List<StudentAcademicDetail> academicDetails=new ArrayList<StudentAcademicDetail>();
		StudentAcademicDetail studentAcademicDetail =  new StudentAcademicDetail();
		academicDetails.add(studentAcademicDetail);
		studentDetail.setAcademicDtl(academicDetails);
		return new ResponseEntity<StudentDetail>(studentDetail,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addStudentDtl(@RequestBody StudentDetail studentDetail) {
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		admissionManager.addStudentDetail(studentDetail);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateStudentDtl(@RequestBody StudentDetail studentDetail) {
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		admissionManager.updateStudentDetail(studentDetail);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteStudentDtl(@RequestBody StudentDetail studentDetail) {
		AdmissionManager admissionManager = new AdmissionManagerImpl();
		admissionManager.deleteStudentDetail(studentDetail);
	}

}
