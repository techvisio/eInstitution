package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.RoomAllocationDetail;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;
import com.techvisio.einstitution.workflow.impl.HostelWorkflowManagerImpl;

@RestController
@RequestMapping("/hostel")

public class HostelService {
	private static CustomLogger logger = CustomLogger.getLogger(HostelService.class);
	
	@Autowired
	HostelWorkflowManager hostelWorkflowManager;
	
	
//RoomTypeDetail 
	
	@RequestMapping(value ="/roomTypeDetail/{typeCode}", method = RequestMethod.GET )
	public ResponseEntity<Response> getRoomTypeDetail(@PathVariable String typeCode){
		logger.info("{}:  Calling getRoomTypeDetail method by passing typecode : {}",this.getClass().getName(), typeCode);
		Response response = new Response();
		
		try
		{
		RoomTypeDetail roomTypeDetail = hostelWorkflowManager.getRoomTypeDetail(typeCode);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			logger.error("{}:Error While Calling getRoomTypeDetail method by passing typecode : {}",this.getClass().getName(),typeCode,e);
			response.setError(e.getMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/roomTypeDetail",method =RequestMethod.POST)
	public ResponseEntity<Response> addRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		logger.info("{}:  Calling addRoomTypeDetail method for typecode : {}",this.getClass().getName(), roomTypeDetail.getTypeCode());
		Response response =  new Response();
		try
		{
		hostelWorkflowManager.addRoomTypeDetail(roomTypeDetail);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			logger.error("{}: Error While Calling addRoomTypeDetail method for  typecode : {}",this.getClass().getName(),roomTypeDetail.getTypeCode(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/roomTypeDetail",method =RequestMethod.PUT)
	public ResponseEntity<Response> updateRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		logger.info("{}  Calling updateRoomTypeDetail method for typecode : {}",this.getClass().getName(), roomTypeDetail.getTypeCode());
		Response response = new Response();
		try{
		hostelWorkflowManager.updateRoomTypeDetail(roomTypeDetail);
		
		response.setResponseBody(roomTypeDetail);
		}
		catch(Exception e)
		{
			logger.error("{}: Error While Calling updateRoomTypeDetail method for typecode :{}",this.getClass().getName(),roomTypeDetail.getTypeCode(),e);
			response.setError(e.getLocalizedMessage());
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(value ="/roomTypeDetail/{typeCode}", method =RequestMethod.DELETE)
	public void deleteRoomTypeDetail(@PathVariable String typeCode){
		logger.info("{}:  Calling deleteRoomTypeDetail method by passing typecode:{}",this.getClass().getName(), typeCode);
		hostelWorkflowManager.deleteRoomTypeDetail(typeCode);
	}

//HostelAllocation
	
	@RequestMapping(value="/hostelAllocation/{fileNo}",method = RequestMethod.GET )
	public RoomAllocationDetail getHostelAllocation(@PathVariable Long fileNo){
		logger.info("{}:  Calling getHostelAllocation method by passing fileno :{}",this.getClass().getName(), fileNo);
		RoomAllocationDetail hostelAllocation = hostelWorkflowManager.getHostelAllocation(fileNo);
		return hostelAllocation;
	}
	
	@RequestMapping(value="/hostelAllocation",method = RequestMethod.POST)
	public void addHostelAllocation(@RequestBody RoomAllocationDetail hostelAllocation){
		logger.info("{}:  Calling addHostelAllocation method for fileno : {}",this.getClass().getName(), hostelAllocation.getFileNo());
		hostelWorkflowManager.addHostelAllocation(hostelAllocation);
		
	}
	
//	@RequestMapping(value="/hostelAllocation",method = RequestMethod.PUT)
//	public void updateHostelAllocation(@RequestBody RoomAllocationDetail hostelAllocation){
//		logger.info("{}:  Calling updateHostelAllocation method for fileno : {}",this.getClass().getName(), hostelAllocation.getFileNo());
//		hostelWorkflowManager.updateHostelAllocation(hostelAllocation);
//	}
	
	@RequestMapping(value="/hostelAllocation/{fileNo}",method = RequestMethod.DELETE)
	public void deleteHostelAllocation(@PathVariable Long fileNo){
		logger.info("{}:  Calling deleteHostelAllocation method by passing fileno : {}",this.getClass().getName(), fileNo);
		hostelWorkflowManager.deleteHostelAllocation(fileNo);
	}	

	
//HostelReservation
	
	
	@RequestMapping(value ="/hostelReservation/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getHostelReservation(@PathVariable Long fileNo){
		logger.info("{}:  Calling getHostelReservation method by passing fileno : {}",this.getClass().getName(), fileNo);	
		Response response=new Response();
		try
		{
		
		HostelReservation hostelReservation = hostelWorkflowManager.getHostelReservation(fileNo);
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
			logger.error("{}: Error While Calling getHostelReservation method by passing fileno :{}",this.getClass().getName(),fileNo,e);
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		}

	@RequestMapping(value ="/hostelReservation",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelReservation(@RequestBody HostelReservation hostelReservation){
		logger.info("{}:  Calling addHostelReservation method for  fileno : {}",this.getClass().getName(), hostelReservation.getFileNo());
		Response response = new Response();
		try
		{
		   Long fileNo=hostelWorkflowManager.addHostelReservation(hostelReservation);
		   HostelReservation updatedReservation=hostelWorkflowManager.getHostelReservation(fileNo);
		   response.setResponseBody(updatedReservation);
		}
		catch(Exception e)
		{
			logger.error("{}: Error While Calling addHostelReservation method for fileno : {}",this.getClass().getName(),hostelReservation.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}
		
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(value ="/hostelReservation",method = RequestMethod.PUT)
	public ResponseEntity<Response> updateHostelReservation(@RequestBody HostelReservation hostelReservation){
		logger.info("{}:  Calling updateHostelReservation method for fileno : {}",this.getClass().getName(), hostelReservation.getFileNo());
		Response response = new Response();
		try
		{
			   Long fileNo=hostelWorkflowManager.updateHostelReservation(hostelReservation);
			   HostelReservation updatedReservation=hostelWorkflowManager.getHostelReservation(fileNo);
			   response.setResponseBody(updatedReservation);
		}
		catch(Exception e)
		{
			logger.error("{}: Error While Calling updateHostelReservation method for fileno : {}",this.getClass().getName(),hostelReservation.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);	
	}
	
	@RequestMapping(value ="/hostelReservation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteHostelReservation(@PathVariable Long fileNo){
		logger.info("{}:  Calling deleteHostelReservation method by passing fileno : {}",this.getClass().getName(), fileNo);
		hostelWorkflowManager.deleteHostelReservation(fileNo);
	
		return new ResponseEntity(HttpStatus.OK);
	}
	
//HostelAvailability
	@RequestMapping(value ="/hostelAvailability", method = RequestMethod.GET)
	public List<HostelAvailability> getHostelAvailability(){
		logger.info("{}: Calling getHostelAvailability method for : fileno : {}",this.getClass().getName());
		List<HostelAvailability> hostelAvailability = hostelWorkflowManager.getHostelAvailability();
		return hostelAvailability;
		
	}
	
	
	@RequestMapping(value="hostelAllocationAdmission",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelAllocationAdmissionDtl(@RequestBody HostelAllocationAdmissionBean hostelAllocationAdmissionBean) {
		logger.info("{}:  Calling getHostelAllocationAdmissiondtl method for fileno : {}",this.getClass().getName(), hostelAllocationAdmissionBean.getBasicInfo().getFileNo());
		Response response = new Response();
		try{
			
			hostelWorkflowManager.saveHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);

            Long fileNo = hostelAllocationAdmissionBean.getBasicInfo().getFileNo();
            hostelAllocationAdmissionBean = hostelWorkflowManager.getHostelAllocationAdmissiondtl(fileNo);
            
            response.setResponseBody(hostelAllocationAdmissionBean);
 		}
		catch(Exception e)
		{
			logger.error("{}: Error While Calling getHostelAllocationAdmissiondtl method for fileno : {}",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFileNo(),e);
			response.setError(e.getLocalizedMessage());
			
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    	}
	
//	@RequestMapping(value="hostelAllocationAdmission",method = RequestMethod.PUT)
//	public ResponseEntity<Response> updateHostelAllocationAdmissionDtl(@RequestBody HostelAllocationAdmissionBean hostelAllocationAdmissionBean) {
//		logger.info("{}:  Calling updateHostelAllocationAdmissiondtl method for fileno : {}",this.getClass().getName(), hostelAllocationAdmissionBean.getBasicInfo().getFileNo());
//		Response response = new Response();
//		try{
//			
//			HostelWorkflowManager hostelWorkflowManager = new HostelWorkflowManagerImpl();
//            hostelWorkflowManager.updateHostelAllocationAdmissionDtl(hostelAllocationAdmissionBean);
//
//            Long fileNo = hostelAllocationAdmissionBean.getBasicInfo().getFileNo();
//            hostelAllocationAdmissionBean = hostelWorkflowManager.getHostelAllocationAdmissiondtl(fileNo);
//            
//            response.setResponseBody(hostelAllocationAdmissionBean);
// 		}
//		catch(Exception e)
//		{
//			logger.error("{}: Error While Calling updateHostelAllocationAdmissiondtl method for fileno : {}",this.getClass().getName(),hostelAllocationAdmissionBean.getBasicInfo().getFileNo());
//			response.setError(e.getLocalizedMessage());
//			
//		}
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//    	}
//	
	@RequestMapping(value="hostelAllocationAdmission/{fileNo}",method = RequestMethod.GET)
	public ResponseEntity<Response> getHostelAllocationAdmissiondtl(@PathVariable Long fileNo){
		logger.info("{}:  Calling getHostelAllocationAdmissiondtl method by passing fileno : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try{
		HostelAllocationAdmissionBean hostelAllocationAdmissionBean = hostelWorkflowManager.getHostelAllocationAdmissiondtl(fileNo);

		response.setResponseBody(hostelAllocationAdmissionBean);
		}
		
		catch(EmptyResultDataAccessException e)
		{
			response.setError("No such record found");
		}
		catch(Exception e)
		{
			logger.error("{}: Error while Calling getHostelAllocationAdmissiondtl method by passing fileno : {}",this.getClass().getName(),fileNo,e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     	}

	@RequestMapping(value ="/studenRoomAllocationDtl/{fileNo}", method = RequestMethod.GET )
	public ResponseEntity<Response> getCurrentAllocationDetailForStudent(@PathVariable Long fileNo){
		logger.info("{}:  Calling getAllocationForStudent method by passing fileNo : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		
		try
		{
		RoomAllocationForStudent allocationForStudent = hostelWorkflowManager.getAllocationForStudent(fileNo);
		
		response.setResponseBody(allocationForStudent);
		}
		catch(Exception e)
		{
			logger.error("{}:Error While Calling getAllocationForStudent method by passing fileNo : {}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/RoomAllocationDtlForRoomNo/{roomNo}", method = RequestMethod.GET )
	public ResponseEntity<Response> getCurrentAllocationForRoomNo(@PathVariable String roomNo){
		logger.info("{}:  Calling getCurrentAllocationByRoom method by passing roomNo : {}",this.getClass().getName(), roomNo);
		Response response = new Response();
		
		try
		{
		RoomAllocationDetailForRoom allocationDetailForRoom = hostelWorkflowManager.getCurrentAllocationByRoom(roomNo);
		
		response.setResponseBody(allocationDetailForRoom);
		}
		catch(Exception e)
		{
			logger.error("{}:Error While Calling getCurrentAllocationByRoom method by passing roomNo : {}",this.getClass().getName(),roomNo,e);
			response.setError(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
}
