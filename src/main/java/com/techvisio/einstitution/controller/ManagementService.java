package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.ManagementAdmissionBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.ScholarshipDetail;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.FeeWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ScholarshipWorkflowManagerImpl;


@RestController
@RequestMapping("/management")
public class ManagementService {
	AdmissionWorkflowManager admissionWorkFlow=new AdmissionWorkflowManagerImpl();
	FeeWorkflowManager feeworkFlow= new FeeWorkflowManagerImpl();
	ScholarshipWorkflowManager schlarshipWorkFlow=new ScholarshipWorkflowManagerImpl();
	
	@RequestMapping(value ="/admission/approval/{fileNo}", method = RequestMethod.GET )
	public ResponseEntity<Response> getRoomTypeDetail(@PathVariable String fileNo){
		Response response=new Response();
		ResponseEntity<Response> result=new ResponseEntity<Response>(response, HttpStatus.OK);
		try{
			ManagementAdmissionBean admissionBean=new ManagementAdmissionBean();
			StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
			admissionBean.setBasicInfo(basicInfo);
			if(basicInfo!=null){
				List<StudentFeeStaging> staggingFee=feeworkFlow.getStudentFeeStaging(fileNo);
				admissionBean.setStagingFee(staggingFee);
				
				ScholarshipDetail scholarshipDetail=schlarshipWorkFlow.getScholarshipDetail(fileNo);
				admissionBean.setScholarship(scholarshipDetail);
				
				response.setResponseBody(admissionBean);
			}
			
		}
		catch(Exception e){
			response.setError(e.getLocalizedMessage());
		}
		return result;
	}

}
