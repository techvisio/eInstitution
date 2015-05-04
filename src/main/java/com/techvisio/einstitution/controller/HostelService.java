package com.techvisio.einstitution.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;
import com.techvisio.einstitution.workflow.impl.HostelWorkflowManagerImpl;

@RestController
@RequestMapping("/hostel")

public class HostelService {
	private static CustomLogger logger = CustomLogger.getLogger(HostelService.class);
	
	@Autowired
	HostelWorkflowManager workflowManager;
	
	
//RoomTypeDetail 
	
	@RequestMapping(value ="/roomTypeDetail/{typeCode}", method = RequestMethod.GET )
	public ResponseEntity<Response> getRoomTypeDetail(@PathVariable String typeCode){
		logger.info("{} HostelService Calling getRoomTypeDetail method by : typecode : {}",this.getClass().getName(), typeCode);
		Response response = new Response();
		
		try
		{
		RoomTypeDetail roomTypeDetail = workflowManager.getRoomTypeDetail(typeCode);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			logger.error("Error While{}",e);
			response.setError(e.getMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/roomTypeDetail",method =RequestMethod.POST)
	public ResponseEntity<Response> addRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		logger.info("{} HostelService Calling addRoomTypeDetail method for : typecode : {}",this.getClass().getName(), roomTypeDetail.getTypeCode());
		Response response =  new Response();
		try
		{
		workflowManager.addRoomTypeDetail(roomTypeDetail);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			logger.error("Error While{}",e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/roomTypeDetail",method =RequestMethod.PUT)
	public ResponseEntity<Response> updateRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		logger.info("{} HostelService Calling updateRoomTypeDetail method for : typecode : {}",this.getClass().getName(), roomTypeDetail.getTypeCode());
		Response response = new Response();
		try{
		workflowManager.updateRoomTypeDetail(roomTypeDetail);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			logger.error("Error While{}",e);
			response.setError(e.getLocalizedMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(value ="/roomTypeDetail/{typeCode}", method =RequestMethod.DELETE)
	public void deleteRoomTypeDetail(@PathVariable String typeCode){
		logger.info("{} HostelService Calling deleteRoomTypeDetail method by : typecode : {}",this.getClass().getName(), typeCode);
		workflowManager.deleteRoomTypeDetail(typeCode);
	}

//HostelAllocation
	
	@RequestMapping(value="/hostelAllocation/{fileNo}",method = RequestMethod.GET )
	public HostelAllocation getHostelAllocation(@PathVariable Long fileNo){
		logger.info("{} HostelService Calling getHostelAllocation method by : fileno : {}",this.getClass().getName(), fileNo);
		HostelAllocation hostelAllocation = workflowManager.getHostelAllocation(fileNo);
		return hostelAllocation;
	}
	
	@RequestMapping(value="/hostelAllocation",method = RequestMethod.POST)
	public void addHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		logger.info("{} HostelService Calling addHostelAllocation method for : fileno : {}",this.getClass().getName(), hostelAllocation.getFileNo());
		workflowManager.addHostelAllocation(hostelAllocation);
		
	}
	
	@RequestMapping(value="/hostelAllocation",method = RequestMethod.PUT)
	public void updateHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		logger.info("{} HostelService Calling updateHostelAllocation method for : fileno : {}",this.getClass().getName(), hostelAllocation.getFileNo());
		workflowManager.updateHostelAllocation(hostelAllocation);
	}
	
	@RequestMapping(value="/hostelAllocation/{fileNo}",method = RequestMethod.DELETE)
	public void deleteHostelAllocation(@PathVariable Long fileNo){
		logger.info("{} HostelService Calling deleteHostelAllocation method by : fileno : {}",this.getClass().getName(), fileNo);
		workflowManager.deleteHostelAllocation(fileNo);
	}	

	
//HostelReservation
	
	
	@RequestMapping(value ="/hostelReservation/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getHostelReservation(@PathVariable Long fileNo){
		logger.info("{} HostelService Calling getHostelReservation method by : fileno : {}",this.getClass().getName(), fileNo);	
		Response response=new Response();
		try
		{
		
		HostelReservation hostelReservation = workflowManager.getHostelReservation(fileNo);
		response.setResponseBody(hostelReservation);
		
		if(hostelReservation==null){
			   response.setError("No record available");
		   }
		}
		catch(EmptyResultDataAccessException e)
		{
			response.setError("No such record found");
		}
		catch(Exception e)
		{
			response.setError(e.getMessage());
			logger.error("Error While{}",e);
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		}

	@RequestMapping(value ="/hostelReservation",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelReservation(@RequestBody HostelReservation hostelReservation){
		logger.info("{} HostelService Calling addHostelReservation method for : fileno : {}",this.getClass().getName(), hostelReservation.getFileNo());
		Response response = new Response();
		try
		{
		   Long fileNo=workflowManager.addHostelReservation(hostelReservation);
		   HostelReservation updatedReservation=workflowManager.getHostelReservation(fileNo);
		   response.setResponseBody(updatedReservation);
		}
		catch(Exception e)
		{
			logger.error("Error While{}",e);
			response.setError(e.getLocalizedMessage());
		}
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(value ="/hostelReservation",method = RequestMethod.PUT)
	public ResponseEntity<Response> updateHostelReservation(@RequestBody HostelReservation hostelReservation){
		logger.info("{} HostelService Calling updateHostelReservation method for : fileno : {}",this.getClass().getName(), hostelReservation.getFileNo());
		Response response = new Response();
		try
		{
			   Long fileNo=workflowManager.updateHostelReservation(hostelReservation);
			   HostelReservation updatedReservation=workflowManager.getHostelReservation(fileNo);
			   response.setResponseBody(updatedReservation);
		}
		catch(Exception e)
		{
			logger.error("Error While{}",e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);	
	}
	
	@RequestMapping(value ="/hostelReservation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteHostelReservation(@PathVariable Long fileNo){
		logger.info("{} HostelService Calling deleteHostelReservation method by : fileno : {}",this.getClass().getName(), fileNo);
		workflowManager.deleteHostelReservation(fileNo);
	
		return new ResponseEntity(HttpStatus.OK);
	}
	
//HostelAvailability
	@RequestMapping(value ="/hostelAvailability", method = RequestMethod.GET)
	public List<HostelAvailability> getHostelAvailability(){
		logger.info("{} HostelService Calling getHostelAvailability method for : fileno : {}",this.getClass().getName());
		List<HostelAvailability> hostelAvailability = workflowManager.getHostelAvailability();
		return hostelAvailability;
		
	}
	
	
	@RequestMapping(value="hostelAllocationAdmission",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelAllocationAdmissionDtl(@RequestBody HostelAllocationAdmissionBean hostelAllocationAdmissionBean) {
		logger.info("{} HostelService Calling getHostelAllocationAdmissiondtl method by : fileno : {}",this.getClass().getName(), hostelAllocationAdmissionBean.getBasicInfo().getFileNo());
		Response response = new Response();
		try{
			
			HostelWorkflowManager hostelWorkflowManager = new HostelWorkflowManagerImpl();
            hostelWorkflowManager.addHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);

            Long fileNo = hostelAllocationAdmissionBean.getBasicInfo().getFileNo();
            hostelAllocationAdmissionBean = hostelWorkflowManager.getHostelAllocationAdmissiondtl(fileNo);
            
            response.setResponseBody(hostelAllocationAdmissionBean);
 		}
		catch(Exception e)
		{
			logger.error("Error While{}",e);
			response.setError(e.getLocalizedMessage());
			
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    	}
	
	@RequestMapping(value="hostelAllocationAdmission",method = RequestMethod.PUT)
	public ResponseEntity<Response> updateHostelAllocationAdmissionDtl(@RequestBody HostelAllocationAdmissionBean hostelAllocationAdmissionBean) {
		logger.info("{} HostelService Calling updateHostelAllocationAdmissiondtl method by : fileno : {}",this.getClass().getName(), hostelAllocationAdmissionBean.getBasicInfo().getFileNo());
		Response response = new Response();
		try{
			
			HostelWorkflowManager hostelWorkflowManager = new HostelWorkflowManagerImpl();
            hostelWorkflowManager.updateHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);

            Long fileNo = hostelAllocationAdmissionBean.getBasicInfo().getFileNo();
            hostelAllocationAdmissionBean = hostelWorkflowManager.getHostelAllocationAdmissiondtl(fileNo);
            
            response.setResponseBody(hostelAllocationAdmissionBean);
 		}
		catch(Exception e)
		{
			logger.error("Error While{}",e);
			response.setError(e.getLocalizedMessage());
			
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    	}
	
	@RequestMapping(value="hostelAllocationAdmission/{fileNo}",method = RequestMethod.GET)
	public ResponseEntity<Response> getConsultantAdmissionDetail(@PathVariable Long fileNo){
		logger.info("{} HostelService Calling getHostelAllocationAdmissiondtl method by : fileno : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try{
		HostelWorkflowManager hostelWorkflowManager = new HostelWorkflowManagerImpl();
		HostelAllocationAdmissionBean hostelAllocationAdmissionBean = hostelWorkflowManager.getHostelAllocationAdmissiondtl(fileNo);

		response.setResponseBody(hostelAllocationAdmissionBean);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     	}

}
