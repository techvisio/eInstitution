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
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;


@RestController
@RequestMapping("/service/consultant")
public class ConsultantService {

	private static CustomLogger logger = CustomLogger.getLogger(ConsultantService.class);

	@Autowired
	ConsultantWorkflowManager consltntWorkflowManager;

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getAdmissnConsltntDtl(@PathVariable Long fileNo){
		logger.info("{}  Calling getAdmissnConsltntDtl method for file no:{}",this.getClass().getName(), fileNo);
		Response response = new Response();

		List<AdmissnConsltntDtl> admissnConsltntDtls = consltntWorkflowManager.getAdmissnConsltntDtl(fileNo);
		response.setResponseBody(admissnConsltntDtls);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.POST)
	public ResponseEntity<Response> saveAdmissionConsultantDtl(@RequestBody List<AdmissnConsltntDtl> admissnConsltntDtls, @PathVariable Long fileNo){
		logger.info("{}  Calling saveAdmissionConsultantDtl method for file no:{}",this.getClass().getName(), fileNo);
		Response response = new Response();
		consltntWorkflowManager.saveAdmissionConsultantDtl(admissnConsltntDtls, fileNo);
		List<AdmissnConsltntDtl> consultntFromDB = consltntWorkflowManager.getAdmissnConsltntDtl(fileNo);
		response.setResponseBody(consultntFromDB);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "admConsultant", method = RequestMethod.PUT)
	public ResponseEntity<Response> addAdmissionConsultantDtl(@RequestBody AdmissnConsltntDtl admissnConsltntDtl){
		Response response = new Response();
		Long fileNo=null;

		fileNo=consltntWorkflowManager.saveAdmissionConsultantDtl(admissnConsltntDtl);
		AdmissnConsltntDtl consultntFromDB = consltntWorkflowManager.getAdmissionConsltntDtl(fileNo);
		response.setResponseBody(consultntFromDB);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="consultantAdmission",method = RequestMethod.POST)
	public ResponseEntity<Response> addConsultantAdmissionDetail(@RequestBody ConsultantAdmissionDetail consultantAdmissionDetail) {
		logger.info("{}:Calling getConsultantAdmissionDetail method for : Student : {}",this.getClass().getName(), consultantAdmissionDetail.getBasicInfo().getFirstName()+consultantAdmissionDetail.getBasicInfo().getLastName());
		Response response = new Response();

		consltntWorkflowManager.saveConsultantAdmissionDetail(consultantAdmissionDetail);

		Long fileNo = consultantAdmissionDetail.getBasicInfo().getFileNo();
		consultantAdmissionDetail = consltntWorkflowManager.getConsultantAdmissionDetail(fileNo);

		response.setResponseBody(consultantAdmissionDetail);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="consultantAdmission/{fileNo}",method = RequestMethod.GET)
	public ResponseEntity<Response> getConsultantAdmissionDetail(@PathVariable Long fileNo){
		logger.info("{} Calling getConsultantAdmissionDetail method  by passing file no :{}",this.getClass().getName(), fileNo);
		Response response = new Response();
		ConsultantAdmissionDetail consultantAdmissionDetail = consltntWorkflowManager.getConsultantAdmissionDetail(fileNo);

		response.setResponseBody(consultantAdmissionDetail);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}	

	@RequestMapping(value ="/searchStudent/", method = RequestMethod.POST)
	public ResponseEntity<Response> getStudentDtlByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}  Calling getStudentDtlBySearchCriteria method for name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		Response response=new Response();
		List<StudentBasicInfo> studentBasicInfo = consltntWorkflowManager.getStudentDtlBySearchCriteria(searchCriteria);
		response.setResponseBody(studentBasicInfo);

		if(studentBasicInfo == null){

			response.setError("No such record found");
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/consultantMaster/{consultantId}",method = RequestMethod.GET)
	public ResponseEntity<Response> getConsultant(@PathVariable Long consultantId){
		logger.info("{}: Calling getConsultant method by passing Consultant Id : {}",this.getClass().getName(), consultantId);
		Response response = new Response();
		Consultant consultant = consltntWorkflowManager.getConsultant(consultantId);
		response.setResponseBody(consultant);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/consultantMaster",method = RequestMethod.POST)
	public ResponseEntity<Response> saveConsultant(@RequestBody Consultant consultant){
		logger.info("{}: Calling saveConsultant method for : Consultant  : {}",this.getClass().getName(), consultant.getConsultantId());
		Response response = new Response();
		Long consultantId = consltntWorkflowManager.saveConsultant(consultant);
		Consultant consultantFromDb = consltntWorkflowManager.getConsultant(consultantId);
		response.setResponseBody(consultantFromDb);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/search", method = RequestMethod.POST)
	public ResponseEntity<Response> getConsultantByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}:  Calling getConsultantBySearchCriteria method  for: Name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		Response response=new Response();
		List<Consultant> consultants = consltntWorkflowManager.getConsultantBySearchCriteria(searchCriteria);
		response.setResponseBody(consultants);

		if(consultants == null){

			response.setError("No such record found");
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

}
