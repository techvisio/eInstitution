package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.FeeTransactionAdmission;
import com.techvisio.einstitution.beans.ManagementAdmission;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.Scholarship;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.Student;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.ManagementWorkflowManager;
import com.techvisio.einstitution.workflow.ScholarshipWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.FeeWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ManagementWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ScholarshipWorkflowManagerImpl;


@RestController
@RequestMapping("/management")
public class ManagementService {
//	
//	@Autowired
//	ManagementWorkflowManager managementWorkflowManager ;
//
//	@Autowired
//	 AdmissionWorkflowManager admissionWorkflowManager;
//	
//	private static CustomLogger logger = CustomLogger.getLogger(ManagementService.class);
//	
//	@RequestMapping(value ="/admission/approval/{fileNo}", method = RequestMethod.GET )
//	public ResponseEntity<Response> getAdmissionManagementView(@PathVariable Long fileNo){
//		logger.info("{}:  Calling getAdmissionManagementView method by passing fileno : {}",this.getClass().getName(), fileNo);
//		Response response = new Response();
//		ResponseEntity<Response> result = new ResponseEntity<Response>(response, HttpStatus.OK);
//
//		try{
//			
//			ManagementAdmission admissionBean = managementWorkflowManager.getAdmissionManagementView(fileNo);
//
//			response.setResponseBody(admissionBean);
//
//		}
//		catch(Exception e){
//			logger.error("{}: Error While Calling getAdmissionManagementView method by passing fileno :{}",this.getClass().getName(),fileNo,e);
//			response.setError(e.getLocalizedMessage());
//		}
//		return result ;
//	}
//
//	@RequestMapping(value = "/uapprovedList/{limit}", method = RequestMethod.GET)
//	public  ResponseEntity<Response> getUnapprovedAdmissions(@PathVariable int limit){
//		logger.info("{}  Calling getUnapprovedAdmissions method by passing limit :{}",this.getClass().getName(), limit);
//		Response response = new Response();
//		try
//		{
//		    List<StudentBasicInfo> basicInfo = managementWorkflowManager.getUnapprovedAdmissions(limit);
//		    response.setResponseBody(basicInfo);
//		}
//		catch(Exception e)
//		{
//			logger.error("{}: Error While Calling getUnapprovedAdmissions method by passing limit: {}",this.getClass().getName(),limit,e);
//			response.setError(e.getMessage());
//		}
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//     	}
//	
//
//@RequestMapping(value = "/updateManagementChanges", method = RequestMethod.PUT)
//public  ResponseEntity<Response> updateManagementChanges(@RequestBody ManagementAdmission admissionBean ){
//	logger.info("{}  Calling updateManagementChanges method for Student : {}",this.getClass().getName(), admissionBean.getBasicInfo().getFatherName()+admissionBean.getBasicInfo().getLastName());
//	Response response = new Response();
//	try
//	{
//       
//		admissionBean.getBasicInfo().getRemark().setFileNo(admissionBean.getBasicInfo().getFileNo());
//	    admissionBean = managementWorkflowManager.updateManagementChanges(admissionBean);
//	    response.setResponseBody(admissionBean);
//	}
//	catch(Exception e)
//	{
//		logger.error("{}: Error While Calling updateManagementChanges method for : Student : {}",this.getClass().getName(),admissionBean.getBasicInfo().getFatherName()+admissionBean.getBasicInfo().getLastName(),e);
//		response.setError(e.getMessage());
//	}
//	return new ResponseEntity<Response>(response,HttpStatus.OK);
// 	}
//

}