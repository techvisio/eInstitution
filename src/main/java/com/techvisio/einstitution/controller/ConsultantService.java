package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;


@RestController
@RequestMapping("/consultant")
public class ConsultantService {

	private static CustomLogger logger = CustomLogger.getLogger(ConsultantService.class);

@Autowired
ConsultantWorkflowManager consltntWorkflowManager;

@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(@PathVariable Long fileNo){
	List<AdmissnConsltntDtl> admissnConsltntDtls = consltntWorkflowManager.getAdmissnConsltntDtl(fileNo); 
	return admissnConsltntDtls;
}

@RequestMapping(value = "/{fileNo}", method = RequestMethod.POST)
public void saveAdmissionConsultantDtl(@RequestBody List<AdmissnConsltntDtl> admissnConsltntDtls, @PathVariable Long fileNo){
	consltntWorkflowManager.saveAdmissionConsultantDtl(admissnConsltntDtls, fileNo);
}
	
	
}
