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

import com.techvisio.einstitution.beans.Address;
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
import com.techvisio.einstitution.beans.StudentDocument;
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

	@RequestMapping(value = "/student/{fileNo}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable Long fileNo){
		Student student = admWorkflowManager.getStudent(fileNo);
		return student;
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public ResponseEntity<Response> saveStudent(@RequestBody Student student){
		Response response = new Response();
		try{
			admWorkflowManager.saveStudent(student);
			Student studentFromDB = admWorkflowManager.getStudent(student.getFileNo());
			response.setResponseBody(studentFromDB);
		}
		catch(Exception e){
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}

	@RequestMapping(value = "/student/academic/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentAcademic(@PathVariable Long fileNo){

		Response response = new Response();
		try{
			List<StudentAcademic> studentAcademics = admWorkflowManager.getAcademicDtl(fileNo);
			response.setResponseBody(studentAcademics);
		}
		catch(Exception e){
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/academic/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveStudentAcademicDtl(@RequestBody List<StudentAcademic> studentAcademics,@PathVariable Long fileNo){

		Response response = new Response();
		try{
			admWorkflowManager.saveAcademicDtl(studentAcademics, fileNo);
			List<StudentAcademic> academicFromDB = admWorkflowManager.getAcademicDtl(fileNo);
			response.setResponseBody(academicFromDB);
		}
		catch(Exception e){
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/discount/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDiscountDtl(@PathVariable Long fileNo){

		Response response = new Response();
		try{
			List<AdmissionDiscount> admissionDiscounts = admWorkflowManager.getDiscountDtl(fileNo);
			response.setResponseBody(admissionDiscounts);
		} 
		catch(Exception e){
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/discount/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveDiscountDtl(@RequestBody List<AdmissionDiscount> admissionDiscounts, @PathVariable Long fileNo){
		Response response = new Response();
		try {
			admWorkflowManager.saveDiscountDtl(admissionDiscounts, fileNo);
			List<AdmissionDiscount> discountFromDB = admWorkflowManager.getDiscountDtl(fileNo);
			response.setResponseBody(discountFromDB);
		} 
		catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/address/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getAddressDtl(@PathVariable Long fileNo){
		Response response = new Response();
		try {
			List<Address> addresses = admWorkflowManager.getAddressDtl(fileNo);
			response.setResponseBody(addresses);
		} 
		catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/address/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveAddressDtl(@RequestBody List<Address> addresses, @PathVariable Long fileNo){
		Response response = new Response();
		try {
			admWorkflowManager.saveAddressDtl(addresses, fileNo);
			List<Address> addressFromDB = admWorkflowManager.getAddressDtl(fileNo);
			response.setResponseBody(addressFromDB);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/branchpref/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getBranchPreference(@PathVariable Long fileNo){
		Response response = new Response();
		try {
			List<BranchPreference> branchPreferences = admWorkflowManager.getBranchPreference(fileNo);
			response.setResponseBody(branchPreferences);
		} 
		catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/branchpref/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveBranchPreference(@RequestBody List<BranchPreference> branchPreferences, @PathVariable Long fileNo){
		Response response = new Response();
		try {
			admWorkflowManager.saveBranchPreference(branchPreferences, fileNo);
			List<BranchPreference> branchPrefFromDB = admWorkflowManager.getBranchPreference(fileNo);
			response.setResponseBody(branchPrefFromDB);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/counselling/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getCounsellingDtl(@PathVariable Long fileNo){
		Response response = new Response();
		try {
			List<Counselling> counsellings = admWorkflowManager.getCounsellingDtl(fileNo);
			response.setResponseBody(counsellings);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/counselling/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveCounsellingDtl (@RequestBody List<Counselling> counsellings, @PathVariable Long fileNo){
		Response response = new Response();
		try {
			admWorkflowManager.saveCounsellingDtl(counsellings, fileNo);
			List<Counselling> counsellingFromDB = admWorkflowManager.getCounsellingDtl(fileNo);
			response.setResponseBody(counsellingFromDB);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/consultant/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getAdmissnConsltntDtl(@PathVariable Long fileNo){
		Response response = new Response();
		try {
			List<AdmissnConsltntDtl> admissnConsltntDtls = constntWorkflowManager.getAdmissnConsltntDtl(fileNo);
			response.setResponseBody(admissnConsltntDtls);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/consultant/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveAdmissionConsultantDtl(@RequestBody List<AdmissnConsltntDtl> admissnConsltntDtls, @PathVariable Long fileNo){
		Response response = new Response();
		try {
			constntWorkflowManager.saveAdmissionConsultantDtl(admissnConsltntDtls, fileNo);
			List<AdmissnConsltntDtl> consultantFromDB = constntWorkflowManager.getAdmissnConsltntDtl(fileNo);
			response.setResponseBody(consultantFromDB);

		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/scholarship/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getScholarship(@PathVariable Long fileNo){
		Response response =new Response();
		try {
			Scholarship scholarship = schlrshpWorkflowManager.getScholarship(fileNo);
			response.setResponseBody(scholarship);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/student/scholarship/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveScholarship(@RequestBody Scholarship scholarship, @PathVariable Long fileNo){
		Response response =new Response();
		try {
			schlrshpWorkflowManager.saveScholarship(scholarship);
			Scholarship scholarshpFromDB = schlrshpWorkflowManager.getScholarship(fileNo);
			response.setResponseBody(scholarshpFromDB);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}


	@RequestMapping(value = "/student/document/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDocumentDtl(@PathVariable Long fileNo){
		Response response = new Response();
		try {
			List<StudentDocument> studentDocuments = admWorkflowManager.getDocumentDtl(fileNo);
			response.setResponseBody(studentDocuments);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);		
	}


	@RequestMapping(value = "/student/document/{fileNo}", method = RequestMethod.PUT)
	public ResponseEntity<Response> saveDocumentDtl(@RequestBody List<StudentDocument> documents, @PathVariable Long fileNo){
		Response response = new Response();
		try {
			admWorkflowManager.saveDocumentDtl(documents, fileNo);
			List<StudentDocument> documentFromDB = admWorkflowManager.getDocumentDtl(fileNo);
			response.setResponseBody(documentFromDB);

		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);

		
	}
	
	
	@RequestMapping(value = "student/document", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentDocumentDtl(){
		Response response = new Response();
		try {
			List<Object[]> studentDocuments = admWorkflowManager.getStudentDocumentDtl();
			response.setResponseBody(studentDocuments);
		} catch (Exception e) {
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	
}
