package com.techvisio.einstitution.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;

@RestController
@RequestMapping("/admission")
public class AdmissionService {

	private static CustomLogger logger = CustomLogger.getLogger(AdmissionService.class);

	@Autowired
	AdmissionWorkflowManager workflowManager;
	
	@RequestMapping(value = "/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentDetail(@PathVariable Long fileNo) {
		logger.info("{}  Calling getStudentDetails method by passing : file no : {}",this.getClass().getName(), fileNo);
		Response response=new Response();
		try
		{
		StudentDetail studentDetail = workflowManager.getStudentDetails(fileNo);
		response.setResponseBody(studentDetail);
		
		if(studentDetail == null){
			
			response.setError("No such record found");
		}
		}
		catch(Exception e)
		{
		logger.error("{} :Error while AdmissionService Calling getStudentDetails method by passing : file no : {}",this.getClass().getName(),fileNo,e);
		response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addStudentDtl(@RequestBody StudentDetail studentDetail) {
		logger.info("{}  Calling addStudentDtl method for : Student : {}",this.getClass().getName(), studentDetail.getFirstName()+studentDetail.getLastName());
		Response response=new Response();
		try
		{
		    Long fileNo=workflowManager.addStudentDetails(studentDetail);
		    StudentDetail studentFromDB=workflowManager.getStudentDetails(fileNo);
		    response.setResponseBody(studentFromDB);
		}
		catch(Exception e)
		{
			logger.error("{} :Error while  Calling addStudentDtl method for : Student : {}",this.getClass().getName(),studentDetail.getFirstName()+ studentDetail.getLastName(),e);
			response.setError(e.getLocalizedMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response> updateStudentDtl(@RequestBody StudentDetail studentDetail) {
		logger.info("{}  Calling getStudentDetails method  : Student Name: {}",this.getClass().getName(), studentDetail.getFirstName()+studentDetail.getLastName());	
		Response response = new Response();
		try
		{
			Long fileNo=workflowManager.updateStudentDetails(studentDetail);
			 
			StudentDetail studentFromDB=workflowManager.getStudentDetails(fileNo);
		    response.setResponseBody(studentFromDB);
		}
		catch(Exception e)
		{
			logger.error("{} :Error while  Calling getStudentDetails method  : Student Name: {}",this.getClass().getName(),studentDetail.getFirstName()+studentDetail.getLastName(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		
	
	
	@RequestMapping(value = "/{fileNo}",method = RequestMethod.DELETE)
	public void deleteStudentDtl(@PathVariable Long fileNo) {
		logger.info("{}  Calling deleteStudentDetails method by passing  :file no : {}",this.getClass().getName(), fileNo );
		workflowManager.deleteStudentDetails(fileNo);
	}
	
	public ResponseEntity<Map<String,List>> getMasterData(){
		logger.info("{}  Calling getMasterData method",this.getClass().getName() );
		Map<String,List> masterData=new HashMap<String, List>();
		//create list of FieldDesc
		masterData.put("personalDetailAttributes", null);
		return new ResponseEntity<Map<String,List>>(masterData,HttpStatus.OK);
	}

	
	@RequestMapping(value ="/search/", method = RequestMethod.POST)
	public ResponseEntity<Response> getStudentDtlByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}  Calling getStudentDtlBySearchCriteria method for name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		Response response=new Response();
		try
		{
			List<StudentBasicInfo> studentBasicInfo = workflowManager.getStudentDtlBySearchCriteria(searchCriteria);
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

	@RequestMapping(value = "/StudentBsInfo/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentBsInfo(@PathVariable Long fileNo){
		logger.info("{}  Calling getStudentBsInfo method by passing file no:{}",this.getClass().getName(), fileNo );
		Response response = new Response();
		try {
			StudentBasicInfo info = workflowManager.getStudentBsInfo(fileNo);
			
			response.setResponseBody(info); 
				
		} catch (Exception e) {
			logger.error("{} :Error while Calling getStudentBsInfo method by passing  file no:{}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/LatestAdmissionInfo/{limit}", method = RequestMethod.GET)
	public  ResponseEntity<Response> getLatestAdmissionInfo(@PathVariable int limit){
		logger.info("{}  Calling getLatestAdmissionInfo method by passing  Limit :{}",this.getClass().getName(), limit);
		Response response = new Response();
		try
		{
		    List<StudentBasicInfo> basicInfo = workflowManager.getLatestAdmissionInfo(limit);
		    response.setResponseBody(basicInfo);
		}
		catch(Exception e)
		{
			logger.error("{} :Error while Calling getLatestAdmissionInfo method by passing  Limit:{}",this.getClass().getName(),limit,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     	}

	@RequestMapping(value ="/submitToManagement/", method = RequestMethod.POST)
	public ResponseEntity<Response> submitToManagement(@RequestBody StudentDetail studentDetail) {
		logger.info("{}  Calling moveAdmissiontoNextStep method  for Student Name : {}",this.getClass().getName(), studentDetail.getFirstName()+ studentDetail.getLastName() );	
		Response response=new Response();
		try
		{
			Long fileNo=workflowManager.moveAdmissiontoNextStep(studentDetail,"PENDING_MANAGEMENT");
		    StudentDetail student=workflowManager.getStudentDetails(fileNo);
		    response.setResponseBody(student);
			
			}
			catch(Exception e)
			{
			logger.error("{} :Error while  Calling moveAdmissiontoNextStep method for Student Name : {}",this.getClass().getName(),studentDetail.getFirstName()+ studentDetail.getLastName(),e );
			response.setError(e.getMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}


}
