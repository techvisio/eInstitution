package com.techvisio.einstitution.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.ConsultantWorkflowManagerImpl;


@RestController
@RequestMapping("/consultant")
public class ConsultantService {

	private static CustomLogger logger = CustomLogger.getLogger(ConsultantService.class);

@Autowired
ConsultantWorkflowManager workflowManager;

@RequestMapping(value ="/searchStudent/", method = RequestMethod.POST)
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



@RequestMapping(value="/consultantMaster/{consultantId}",method = RequestMethod.GET)
public ResponseEntity<Response> getConsultant(@PathVariable Long consultantId){
	logger.info("{}: Calling getConsultant method by passing Consultant Id : {}",this.getClass().getName(), consultantId);
	Response response = new Response();
	
	try
	{
		
  		Consultant consultant = workflowManager.getConsultant(consultantId);
           
  			response.setResponseBody(consultant);
  		}
	catch(Exception e)
	{
		logger.error("{}:Error While  ConsultantService Calling getConsultant method by passing Consultant Id : {}",this.getClass().getName(),consultantId,e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);

	
}

@RequestMapping(value="/consultantMaster",method = RequestMethod.POST)
public ResponseEntity<Response> saveConsultant(@RequestBody Consultant consultant){
	logger.info("{}: Calling saveConsultant method for : Consultant  : {}",this.getClass().getName(), consultant.getConsultantId());
	Response response = new Response();
	
	try
	{
	//workflowManager.saveConsultant(consultant);
	Long consultantId = workflowManager.saveConsultant(consultant);
	Consultant consultantFromDb = workflowManager.getConsultant(consultantId);
	response.setResponseBody(consultantFromDb);
	}
	catch(Exception e)
	{
		logger.error("{}:Error While ConsultantService Calling saveConsultant method for : Consultant  : {}",this.getClass().getName(),consultant.getConsultantId(),e);
		response.setError(e.getLocalizedMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
}

@RequestMapping(value="/consultantMaster/{consultantId}",method = RequestMethod.DELETE)
public ResponseEntity<Response> deleteConsultant(@PathVariable Long consultantId){
	logger.info("{}: Calling deleteConsultant method by passing  Consultant id  :{}",this.getClass().getName(), consultantId);
	Response response = new Response();
	
	try
	{
  		 workflowManager.deleteConsultant(consultantId);
           
  		
  			response.setResponseBody(consultantId);
  			
  		}
	catch(Exception e)
	{
		logger.error("{} Error While ConsultantService Calling deleteConsultant method by passing  Consultant id :{}",this.getClass().getName(),consultantId,e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	
}



	@RequestMapping(value="/{fileNo}",method = RequestMethod.GET)
	  public ResponseEntity<Response> getConsultantDetail(@PathVariable Long fileNo) {  
		logger.info("{}: Calling getConsultantDtl method  by passing File no  : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		
		try
		{
      		List<ConsultantDetail> consultantDetails = workflowManager.getConsultantDtl(fileNo);
               
      		for(ConsultantDetail consultantDetail : consultantDetails){
      			response.setResponseBody(consultantDetail);
      			}
      		}
		catch(Exception e)
		{
			logger.error("{}:Error While  ConsultantService Calling getConsultantDtl method by passing file no :{}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> addConsultantDtl(@RequestBody List<ConsultantDetail> consultantDetails) {  
		logger.info("{}: Calling saveConsultant method by passing consultantDetails:{}  ",this.getClass().getName(),consultantDetails);    
		Response response = new Response();
		
		try
		{
		workflowManager.saveConsultant(consultantDetails);
     
		response.setResponseBody(consultantDetails);
		}
		catch(Exception e)
		{
			logger.error("{}:Error While  Calling saveConsultant method by passing consultantDetails:{}",this.getClass().getName(),consultantDetails,e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/search", method = RequestMethod.POST)
	public ResponseEntity<Response> getConsultantByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}:  Calling getConsultantBySearchCriteria method  for: Name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		Response response=new Response();
		try
		{
			List<Consultant> consultants = workflowManager.getConsultantBySearchCriteria(searchCriteria);
			response.setResponseBody(consultants);
			
			if(consultants == null){
				
				response.setError("No such record found");
			}
			}
			catch(Exception e)
			{
			logger.error("{}:Error While ConsultantService Calling getStudentDtlByCriteria method fo name:{}",this.getClass().getName(),searchCriteria.getFirstName(),e);
			response.setError(e.getMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}

	
	
	@RequestMapping(value="consultantAdmission",method = RequestMethod.POST)
	public ResponseEntity<Response> addConsultantAdmissionDetail(@RequestBody ConsultantAdmissionDetail consultantAdmissionDetail) {
		logger.info("{}:Calling getConsultantAdmissionDetail method for : Student : {}",this.getClass().getName(), consultantAdmissionDetail.getBasicInfo().getFirstName()+consultantAdmissionDetail.getBasicInfo().getLastName());
		Response response = new Response();
		try{
			
			workflowManager.saveConsultantAdmissionDetail(consultantAdmissionDetail);

            Long fileNo = consultantAdmissionDetail.getBasicInfo().getFileNo();
            consultantAdmissionDetail = workflowManager.getConsultantAdmissionDetail(fileNo);
            
            response.setResponseBody(consultantAdmissionDetail);
            
           
 		}
		catch(Exception e)
		{
			logger.error("{}: Error While ConsultantService Calling getConsultantAdmissionDetail method for : Student : {}",this.getClass().getName(),consultantAdmissionDetail.getBasicInfo().getFirstName()+consultantAdmissionDetail.getBasicInfo().getLastName(),e);
			response.setError(e.getLocalizedMessage());
			
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    	}
	
	
	@RequestMapping(value="consultantAdmission/{fileNo}",method = RequestMethod.GET)
	public ResponseEntity<Response> getConsultantAdmissionDetail(@PathVariable Long fileNo){
		logger.info("{} Calling getConsultantAdmissionDetail method  by passing file no :{}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try{
		ConsultantAdmissionDetail consultantAdmissionDetail = workflowManager.getConsultantAdmissionDetail(fileNo);

		response.setResponseBody(consultantAdmissionDetail);
		}
		catch(Exception e)
		{
			logger.error("{} :Error While  ConsultantService Calling getConsultantAdmissionDetail method by passing file no  :{}",this.getClass().getName(),fileNo,e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     	}
	
	
	
//	@RequestMapping(value="/{fileNo}",method = RequestMethod.DELETE)
//	public void deleteConsultantDtl(@PathVariable List fileNo) {  
//		ConsultantWorkflowManager workflowManager=new ConsultantWorkflowManagerImpl();
//		
//	}

	
	
}
