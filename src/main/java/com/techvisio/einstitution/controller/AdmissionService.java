package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentAcademic;
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
	
	@RequestMapping(value = "student/{fileNo}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable Long fileNo){
		Student student = admWorkflowManager.getStudent(fileNo);
		return student;
	}

	@RequestMapping(value = "student/{fileNo}", method = RequestMethod.POST)
	public void saveStudent(@RequestBody Student student, @PathVariable Long fileNo){
		admWorkflowManager.saveStudent(student);
	}

	@RequestMapping(value = "student/academic/{fileNo}", method = RequestMethod.GET)
	public List<StudentAcademic> getStudentAcademic(@PathVariable Long fileNo){
		List<StudentAcademic> studentAcademics = admWorkflowManager.getAcademicDtl(fileNo);
		return studentAcademics;
	}

	@RequestMapping(value = "student/academic/{fileNo}", method = RequestMethod.PUT)
	public void saveStudentAcademicDtl(@RequestBody List<StudentAcademic> studentAcademics,@PathVariable Long fileNo){
		admWorkflowManager.saveAcademicDtl(studentAcademics, fileNo);
	}

	@RequestMapping(value = "student/discount/{fileNo}", method = RequestMethod.GET)
	public List<AdmissionDiscount> getDiscountDtl(@PathVariable Long fileNo){
		List<AdmissionDiscount> admissionDiscounts = admWorkflowManager.getDiscountDtl(fileNo); 
		return admissionDiscounts;
	}

	@RequestMapping(value = "student/discount/{fileNo}", method = RequestMethod.PUT)
	public void saveDiscountDtl(@RequestBody List<AdmissionDiscount> admissionDiscounts, @PathVariable Long fileNo){
		admWorkflowManager.saveDiscountDtl(admissionDiscounts, fileNo);
	}

	@RequestMapping(value = "student/address/{fileNo}", method = RequestMethod.GET)
	public List<Address> getAddressDtl(@PathVariable Long fileNo){
		List<Address> address = admWorkflowManager.getAddressDtl(fileNo);
		return address;
	}

	@RequestMapping(value = "student/address/{fileNo}", method = RequestMethod.PUT)
	public void saveAddressDtl(@RequestBody List<Address> addresses, @PathVariable Long fileNo){
		admWorkflowManager.saveAddressDtl(addresses, fileNo);
	}

	@RequestMapping(value = "student/branchpref{fileNo}", method = RequestMethod.GET)
	public List<BranchPreference> getBranchPreference(@PathVariable Long fileNo){
		List<BranchPreference> branchPreferences = admWorkflowManager.getBranchPreference(fileNo); 
		return branchPreferences;
	}

	@RequestMapping(value = "student/branchpref/{fileNo}", method = RequestMethod.PUT)
	public void saveBranchPreference(@RequestBody List<BranchPreference> branchPreferences, @PathVariable Long fileNo){
		admWorkflowManager.saveBranchPreference(branchPreferences, fileNo);
	}

	@RequestMapping(value = "student/counselling/{fileNo}", method = RequestMethod.GET)
	public List<Counselling> getCounsellingDtl(@PathVariable Long fileNo){
		List<Counselling> counsellings = admWorkflowManager.getCounsellingDtl(fileNo); 
		return counsellings;
	}

	@RequestMapping(value = "student/counselling/{fileNo}", method = RequestMethod.PUT)
	public void saveCounsellingDtl (@RequestBody List<Counselling> counsellings, @PathVariable Long fileNo){
		admWorkflowManager.saveCounsellingDtl(counsellings, fileNo);
	}

	@RequestMapping(value = "student/consultant/{fileNo}", method = RequestMethod.GET)
	public List<AdmissnConsltntDtl> getAdmissnConsltntDtl(@PathVariable Long fileNo){
		List<AdmissnConsltntDtl> admissnConsltntDtls = constntWorkflowManager.getAdmissnConsltntDtl(fileNo); 
		return admissnConsltntDtls;
	}

	@RequestMapping(value = "student/consultant/{fileNo}", method = RequestMethod.PUT)
	public void saveAdmissionConsultantDtl(@RequestBody List<AdmissnConsltntDtl> admissnConsltntDtls, @PathVariable Long fileNo){
		constntWorkflowManager.saveAdmissionConsultantDtl(admissnConsltntDtls, fileNo);
	}

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public Scholarship getScholarship(@PathVariable Long fileNo){
		Scholarship scholarship = schlrshpWorkflowManager.getScholarship(fileNo);
		return scholarship;
	}

	@RequestMapping(value = "/{fileNo}", method = RequestMethod.PUT)
	public void saveScholarship(@RequestBody Scholarship scholarship, @PathVariable Long fileNo){
		schlrshpWorkflowManager.saveScholarship(scholarship);
	}

}
