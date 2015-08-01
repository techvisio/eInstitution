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

import com.techvisio.einstitution.beans.Activity;
import com.techvisio.einstitution.beans.Address;
import com.techvisio.einstitution.beans.AdmissionData;
import com.techvisio.einstitution.beans.AdmissionDiscount;
import com.techvisio.einstitution.beans.AdmissnConsltntDtl;
import com.techvisio.einstitution.beans.BranchPreference;
import com.techvisio.einstitution.beans.Counselling;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentBasics;
import com.techvisio.einstitution.beans.StudentDocument;
import com.techvisio.einstitution.beans.Workflow;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;

@RestController
@RequestMapping("/admission")
public class AdmissionService {

	private static CustomLogger logger = CustomLogger.getLogger(AdmissionService.class);

	@Autowired
	AdmissionWorkflowManager admWorkflowManager;

	@Autowired
	ConsultantWorkflowManager constntWorkflowManager;

	@Autowired
	ScholarshipWorkflowManager schlrshpWorkflowManager;
	
	@Autowired
	CacheManager cacheManager;

	@RequestMapping(value ="/search/", method = RequestMethod.POST)
	public ResponseEntity<Response> getStudentDtlByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}  Calling getStudentDtlBySearchCriteria method for name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		Response response=new Response();
		try
		{
			List<StudentBasicInfo> studentBasicInfo = admWorkflowManager.getStudentDtlBySearchCriteria(searchCriteria);
			response.setResponseBody(studentBasicInfo);
			
			if(studentBasicInfo == null){
				
				response.setError("No such record found");
			}
			}
			catch(Exception e)
			{
			logger.error("{} :Error while Calling getStudentDtlBySearchCriteria method for name:{}",this.getClass().getName(),searchCriteria.getFirstName(),e);
			response.setError(e.getMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}

	@RequestMapping(value ="/processWorkFlow/{stepId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> processWorkFlow(@RequestBody Student student,@PathVariable Long stepId){
		logger.info("{}  Calling getStudent method for file no:{}",this.getClass().getName(), student.getFileNo());
		Response response=new Response();
		AdmissionData ad = new AdmissionData();
		
			admWorkflowManager.processWorkFlow(student, stepId, student.getFileNo());
			Student studentDB = admWorkflowManager.getStudent(student.getFileNo());
			Workflow wf=cacheManager.getWorkflowByStepId(studentDB.getStudentBasics().getApplicationStatus());
			ad.setStudent(studentDB);
			ad.setWorkflows(wf.getChildWorkflow());
			response.setResponseBody(ad);
			return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudent(@PathVariable Long fileNo){
		logger.info("{}  Calling getStudent method for file no:{}",this.getClass().getName(), fileNo);
		Response response=new Response();
		AdmissionData ad=new AdmissionData();
		try
		{
		Student student = admWorkflowManager.getStudent(fileNo);
		Workflow wf=cacheManager.getWorkflowByStepId(student.getStudentBasics().getApplicationStatus());
		ad.setStudent(student);
		List<Workflow> childWorkflow = wf.getChildWorkflow();
		ad.setWorkflows(childWorkflow);
		response.setResponseBody(ad);
		}
		catch(Exception e)
		{
		logger.error("{} :Error while Calling getStudentDtl method for name:{}",this.getClass().getName(),fileNo,e);
		response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student/new", method = RequestMethod.GET)
	public ResponseEntity<Response> getNewAdmission(){
		logger.info("{}  Calling getNewAdmissionWorkFlow method ",this.getClass().getName());
		Workflow wf=admWorkflowManager.getNewAdmissionWorkFlow();
		Response response=new Response();
		if(wf!= null){
			response.setResponseBody(wf);
		}else{
			response.setError("No Workflow is defined for Admission");
		}
			
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public ResponseEntity<Response> saveStudent(@RequestBody Student student){
		logger.info("{}  Calling saveStudent method for file no:{}",this.getClass().getName(), student.getFileNo());
		Response response = new Response();
		try{
			admWorkflowManager.saveStudent(student);
			Student studentFromDB = admWorkflowManager.getStudent(student.getFileNo());
			response.setResponseBody(studentFromDB);
		}
		catch(Exception e){
			logger.error("{} :Error while Calling saveStudent method for file no:{}",this.getClass().getName(),student.getFileNo(),e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/studentbasic/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> geStudentBasics(@PathVariable Long fileNo){
		logger.info("{}  Calling getStudentBasics method for file no:{}",this.getClass().getName(), fileNo);
		Response response =new Response();
		try {
			StudentBasics studentBasics = admWorkflowManager.getStudentBasics(fileNo);
			response.setResponseBody(studentBasics);
		} catch (Exception e) {
			logger.error("{} :Error while Calling getStudentBasics method for file no:{}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/studentbasic/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> savestudentBasics(@RequestBody StudentBasics studentBasics, @PathVariable Long fileNo){
		logger.info("{}  Calling saveStudentBasics method for file no:{} and Name:{}",this.getClass().getName(), fileNo, studentBasics.getFirstName());
		Response response =new Response();
		try {
			admWorkflowManager.saveStudentBasics(studentBasics);
			StudentBasics studentBasicFromDB = admWorkflowManager.getStudentBasics(fileNo);
			response.setResponseBody(studentBasicFromDB);
		} catch (Exception e) {
			logger.error("{} :Error while Calling saveStudentBasics method for file no:{} and Name:{}",this.getClass().getName(),fileNo,studentBasics.getFirstName(),e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/academic/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentAcademic(@PathVariable Long fileNo){
		logger.info("{}  Calling getAcademicDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try{
			List<StudentAcademic> studentAcademics = admWorkflowManager.getAcademicDtl(fileNo);
			response.setResponseBody(studentAcademics);
		}
		catch(Exception e){
			logger.error("{} :Error while Calling getAcademicDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/academic/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveStudentAcademicDtl(@RequestBody List<StudentAcademic> studentAcademics,@PathVariable Long fileNo){
		logger.info("{}  Calling saveAcademicDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try{
			admWorkflowManager.saveAcademicDtl(studentAcademics, fileNo);
			List<StudentAcademic> academicFromDB = admWorkflowManager.getAcademicDtl(fileNo);
			response.setResponseBody(academicFromDB);
		}
		catch(Exception e){
			logger.error("{} :Error while Calling saveAcademicDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/discount/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDiscountDtl(@PathVariable Long fileNo){
		logger.info("{}  Calling getDiscountDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try{
			List<AdmissionDiscount> admissionDiscounts = admWorkflowManager.getDiscountDtl(fileNo);
			response.setResponseBody(admissionDiscounts);
		} 
		catch(Exception e){
			logger.error("{} :Error while Calling getDiscountDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/discount/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveDiscountDtl(@RequestBody List<AdmissionDiscount> admissionDiscounts, @PathVariable Long fileNo){
		logger.info("{}  Calling saveDiscountDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			admWorkflowManager.saveDiscountDtl(admissionDiscounts, fileNo);
			List<AdmissionDiscount> discountFromDB = admWorkflowManager.getDiscountDtl(fileNo);
			response.setResponseBody(discountFromDB);
		} 
		catch (Exception e) {
			logger.error("{} :Error while Calling saveDiscountDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/address/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getAddressDtl(@PathVariable Long fileNo){
		logger.info("{}  Calling getAddressDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<Address> addresses = admWorkflowManager.getAddressDtl(fileNo);
			response.setResponseBody(addresses);
		} 
		catch (Exception e) {
			logger.error("{} :Error while Calling getAddressDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/address/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveAddressDtl(@RequestBody List<Address> addresses, @PathVariable Long fileNo){
		logger.info("{}  Calling saveAddressDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			admWorkflowManager.saveAddressDtl(addresses, fileNo);
			List<Address> addressFromDB = admWorkflowManager.getAddressDtl(fileNo);
			response.setResponseBody(addressFromDB);
		} catch (Exception e) {
			logger.error("{} :Error while Calling saveAddressDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/branchpref/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getBranchPreference(@PathVariable Long fileNo){
		logger.info("{}  Calling getBranchPreference method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<BranchPreference> branchPreferences = admWorkflowManager.getBranchPreference(fileNo);
			response.setResponseBody(branchPreferences);
		} 
		catch (Exception e) {
			logger.error("{} :Error while Calling getBranchPreference method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/branchpref/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveBranchPreference(@RequestBody List<BranchPreference> branchPreferences, @PathVariable Long fileNo){
		logger.info("{}  Calling saveBranchPreference method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			admWorkflowManager.saveBranchPreference(branchPreferences, fileNo);
			List<BranchPreference> branchPrefFromDB = admWorkflowManager.getBranchPreference(fileNo);
			response.setResponseBody(branchPrefFromDB);
		} catch (Exception e) {
			logger.error("{} :Error while Calling saveBranchPreference method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/counselling/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getCounsellingDtl(@PathVariable Long fileNo){
		logger.info("{}  Calling getCounsellingDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<Counselling> counsellings = admWorkflowManager.getCounsellingDtl(fileNo);
			response.setResponseBody(counsellings);
		} catch (Exception e) {
			logger.error("{} :Error while Calling getCounsellingDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/counselling/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveCounsellingDtl (@RequestBody List<Counselling> counsellings, @PathVariable Long fileNo){
		logger.info("{}  Calling saveCounsellingDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			admWorkflowManager.saveCounsellingDtl(counsellings, fileNo);
			List<Counselling> counsellingFromDB = admWorkflowManager.getCounsellingDtl(fileNo);
			response.setResponseBody(counsellingFromDB);
		} catch (Exception e) {
			logger.error("{} :Error while Calling saveCounsellingDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/consultant/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getAdmissnConsltntDtl(@PathVariable Long fileNo){
		logger.info("{}  Calling getAdmissnConsltntDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<AdmissnConsltntDtl> admissnConsltntDtls = constntWorkflowManager.getAdmissnConsltntDtl(fileNo);
			response.setResponseBody(admissnConsltntDtls);
		} catch (Exception e) {
			logger.error("{} :Error while Calling getAdmissnConsltntDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/consultant/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveAdmissionConsultantDtl(@RequestBody List<AdmissnConsltntDtl> admissnConsltntDtls, @PathVariable Long fileNo){
		logger.info("{}  Calling saveAdmissionConsultantDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			constntWorkflowManager.saveAdmissionConsultantDtl(admissnConsltntDtls, fileNo);
			List<AdmissnConsltntDtl> consultantFromDB = constntWorkflowManager.getAdmissnConsltntDtl(fileNo);
			response.setResponseBody(consultantFromDB);

		} catch (Exception e) {
			logger.error("{} :Error while Calling saveAdmissionConsultantDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/scholarship/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getScholarship(@PathVariable Long fileNo){
		logger.info("{}  Calling getScholarship method for file no:{} ",this.getClass().getName(), fileNo);
		Response response =new Response();
		try {
			Scholarship scholarship = schlrshpWorkflowManager.getScholarship(fileNo);
			response.setResponseBody(scholarship);
		} catch (Exception e) {
			logger.error("{} :Error while Calling getScholarship method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/scholarship/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveScholarship(@RequestBody Scholarship scholarship, @PathVariable Long fileNo){
		logger.info("{}  Calling saveScholarship method for file no:{} ",this.getClass().getName(), fileNo);
		Response response =new Response();
		try {
			schlrshpWorkflowManager.saveScholarship(scholarship);
			Scholarship scholarshpFromDB = schlrshpWorkflowManager.getScholarship(fileNo);
			response.setResponseBody(scholarshpFromDB);
		} catch (Exception e) {
			logger.error("{} :Error while Calling saveScholarship method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}


	@RequestMapping(value = "/student/document/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDocumentDtl(@PathVariable Long fileNo){
		logger.info("{}  Calling getDocumentDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<StudentDocument> studentDocuments = admWorkflowManager.getDocumentDtl(fileNo);
			response.setResponseBody(studentDocuments);
		} catch (Exception e) {
			logger.error("{} :Error while Calling getDocumentDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);		
	}


	@RequestMapping(value = "/student/document/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveDocumentDtl(@RequestBody List<StudentDocument> documents, @PathVariable Long fileNo){
		logger.info("{}  Calling saveDocumentDtl method for file no:{} ",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			admWorkflowManager.saveDocumentDtl(documents, fileNo);
			List<StudentDocument> documentFromDB = admWorkflowManager.getDocumentDtl(fileNo);
			response.setResponseBody(documentFromDB);

		} catch (Exception e) {
			logger.error("{} :Error while Calling saveDocumentDtl method for file no:{} ",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "student/document", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentDocumentDtl(){
		logger.info("{}  Calling getStudentDocumentDtl method  ",this.getClass().getName());
		Response response = new Response();
		try {
			List<Object[]> studentDocuments = admWorkflowManager.getStudentDocumentDtl();
			response.setResponseBody(studentDocuments);
		} catch (Exception e) {
			logger.error("{} :Error while Calling getStudentDocumentDtl method  ",this.getClass().getName(),e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
}
