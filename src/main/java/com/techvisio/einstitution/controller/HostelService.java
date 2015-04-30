package com.techvisio.einstitution.controller;

import java.util.List;

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

import com.techvisio.einstitution.beans.ConsultantAdmissionDetail;
import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.workflow.ConsultantWorkflowManager;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;
import com.techvisio.einstitution.workflow.impl.ConsultantWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.HostelWorkflowManagerImpl;

@RestController
@RequestMapping("/hostel")

public class HostelService {
	private static final Logger logger = Logger.getLogger(HostelService.class);
	
	@Autowired
	HostelWorkflowManager workflowManager;
	
//RoomTypeDetail 
	
	@RequestMapping(value ="/roomTypeDetail/{typeCode}", method = RequestMethod.GET )
	public ResponseEntity<Response> getRoomTypeDetail(@PathVariable String typeCode){
		
		Response response = new Response();
		
		try
		{
		RoomTypeDetail roomTypeDetail = workflowManager.getRoomTypeDetail(typeCode);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/roomTypeDetail",method =RequestMethod.POST)
	public ResponseEntity<Response> addRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		
		Response response =  new Response();
		try
		{
		workflowManager.addRoomTypeDetail(roomTypeDetail);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/roomTypeDetail",method =RequestMethod.PUT)
	public ResponseEntity<Response> updateRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
	
		Response response = new Response();
		try{
		workflowManager.updateRoomTypeDetail(roomTypeDetail);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(value ="/roomTypeDetail/{typeCode}", method =RequestMethod.DELETE)
	public void deleteRoomTypeDetail(@PathVariable String typeCode){
		workflowManager.deleteRoomTypeDetail(typeCode);
	}

//HostelAllocation
	
	@RequestMapping(value="/hostelAllocation/{fileNo}",method = RequestMethod.GET )
	public HostelAllocation getHostelAllocation(@PathVariable Long fileNo){
		HostelAllocation hostelAllocation = workflowManager.getHostelAllocation(fileNo);
		return hostelAllocation;
	}
	
	@RequestMapping(value="/hostelAllocation",method = RequestMethod.POST)
	public void addHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		workflowManager.addHostelAllocation(hostelAllocation);
		
	}
	
	@RequestMapping(value="/hostelAllocation",method = RequestMethod.PUT)
	public void updateHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		workflowManager.updateHostelAllocation(hostelAllocation);
	}
	
	@RequestMapping(value="/hostelAllocation/{fileNo}",method = RequestMethod.DELETE)
	public void deleteHostelAllocation(@PathVariable Long fileNo){
		workflowManager.deleteHostelAllocation(fileNo);
	}	

	
//HostelReservation
	
	
	@RequestMapping(value ="/hostelReservation/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getHostelReservation(@PathVariable Long fileNo){
		
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
			e.printStackTrace();
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		}

	@RequestMapping(value ="/hostelReservation",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelReservation(@RequestBody HostelReservation hostelReservation){
		
		Response response = new Response();
		try
		{
		   Long fileNo=workflowManager.addHostelReservation(hostelReservation);
		   HostelReservation updatedReservation=workflowManager.getHostelReservation(fileNo);
		   response.setResponseBody(updatedReservation);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(value ="/hostelReservation",method = RequestMethod.PUT)
	public ResponseEntity<Response> updateHostelReservation(@RequestBody HostelReservation hostelReservation){
		
		Response response = new Response();
		try
		{
			   Long fileNo=workflowManager.updateHostelReservation(hostelReservation);
			   HostelReservation updatedReservation=workflowManager.getHostelReservation(fileNo);
			   response.setResponseBody(updatedReservation);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);	
	}
	
	@RequestMapping(value ="/hostelReservation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteHostelReservation(@PathVariable Long fileNo){
		workflowManager.deleteHostelReservation(fileNo);
	
		return new ResponseEntity(HttpStatus.OK);
	}
	
//HostelAvailability
	@RequestMapping(value ="/hostelAvailability", method = RequestMethod.GET)
	public List<HostelAvailability> getHostelAvailability(){
		List<HostelAvailability> hostelAvailability = workflowManager.getHostelAvailability();
		return hostelAvailability;
		
	}
	
	
	@RequestMapping(value="hostelAllocationAdmission",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelAllocationAdmissionDtl(@RequestBody HostelAllocationAdmissionBean hostelAllocationAdmissionBean) {
		
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
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
			
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    	}
	
	@RequestMapping(value="hostelAllocationAdmission",method = RequestMethod.PUT)
	public ResponseEntity<Response> updateHostelAllocationAdmissionDtl(@RequestBody HostelAllocationAdmissionBean hostelAllocationAdmissionBean) {
		
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
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
			
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    	}
	
	@RequestMapping(value="hostelAllocationAdmission/{fileNo}",method = RequestMethod.GET)
	public ResponseEntity<Response> getConsultantAdmissionDetail(@PathVariable Long fileNo){

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