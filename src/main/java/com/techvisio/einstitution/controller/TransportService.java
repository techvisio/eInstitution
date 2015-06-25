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

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmission;
import com.techvisio.einstitution.beans.TransportAllocationDtlForVehicle;
import com.techvisio.einstitution.beans.TransportAllocationForStudent;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
import com.techvisio.einstitution.workflow.impl.TransportWorkflowManagerImpl;

@RestController
@RequestMapping("/transport")

public class TransportService {
	
//	private static CustomLogger logger = CustomLogger.getLogger(TransportService.class);
//		
//		@Autowired
//		TransportWorkflowManager transportWorkflowManager;
//		
//		@RequestMapping(value="/availableTransport",method = RequestMethod.GET)
//		  public List<AvailableTransport> getTransportAllocation() {
//			logger.info("{}:  Calling getAvailableTransport method",this.getClass().getName());
//			List<AvailableTransport> availableTransports=transportWorkflowManager.getAvailableTransport();
//			return availableTransports;  
//			
//		  }
//		
//		@RequestMapping(value="/allocation/{fileNo}",method = RequestMethod.GET)
//		  public TransportAllocation getTransportAllocation(@PathVariable Long fileNo) {
//			logger.info("{}  Calling getTransportAllocationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
//			TransportAllocation transportAllocation=transportWorkflowManager.getTransportAllocationDtl(fileNo);
//			return transportAllocation;  
//			
//		  }
//		@RequestMapping(value="/allocation",method = RequestMethod.POST)
//		public void addTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
//			logger.info("{}  Calling addTransportAllocationDtl method for : fileNo :{}",this.getClass().getName(), transportAllocation.getFileNo());
//			transportWorkflowManager.addTransportAllocationDtl(transportAllocation);
//		}
//		
////		@RequestMapping(value="/allocation",method = RequestMethod.PUT)
////		public void updateTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
////			logger.info("{}  Calling updateTransportAllocationDtl method for : fileNo :{}",this.getClass().getName(), transportAllocation.getFileNo());
////			transportWorkflowManager.updateTransportAllocationDtl(transportAllocation);
////		}
//		@RequestMapping(value="/allocation/{fileNo}",method = RequestMethod.DELETE)
//		public void deleteTransportAllocationDtl(@PathVariable Long fileNo ) {  
//			logger.info("{}  Calling deleteTransportAllocationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
//			transportWorkflowManager.deleteTransportAllocationDtl(fileNo);
//		}
//
//
//
//		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.GET)
//		  public ResponseEntity<Response> getTransportReservation(@PathVariable Long fileNo) {  
//			logger.info("{}  Calling getTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
//			Response response=new Response();
//			try
//			{
//			       TransportReservation transportReservation=transportWorkflowManager.getTransportReservationDtl(fileNo);
//				   response.setResponseBody(transportReservation);
//				   if(transportReservation==null){
//					   response.setError("No record available");
//				   }
//			       
//			}
//			catch(EmptyResultDataAccessException e)
//			{
//				response.setError("No such record found");
//				
//			}
//			catch(Exception e)
//			{
//		         	response.setError(e.getMessage());
//		         	logger.error("{}: Error While Calling getTransportReservationDtl method by fileNo :{}",this.getClass().getName(),fileNo,e);
//			}
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//      		}
//
//		@RequestMapping(value="/reservation",method = RequestMethod.POST)
//		public ResponseEntity<Response> addTransporReservation(@RequestBody TransportReservation transportReservation) {  
//			logger.info("{} : Calling addTransportReservationDtl method for fileNo :{}",this.getClass().getName(), transportReservation.getFileNo());
//			Response response = new Response();
//			try
//			{
//			   Long fileNo=transportWorkflowManager.addTransportReservationDtl(transportReservation);
//			   TransportReservation updatedReservation=transportWorkflowManager.getTransportReservationDtl(fileNo);
//			   response.setResponseBody(updatedReservation);
//			}
//			catch(Exception e)
//			{
//				logger.error("{}: Error While  Calling addTransportReservationDtl method for fileNo :{}",this.getClass().getName(),transportReservation.getFileNo(),e);
//				response.setError(e.getLocalizedMessage());
//			}
//			
//			 return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		
//		@RequestMapping(value="/reservation",method = RequestMethod.PUT)
//		public ResponseEntity<Response> updateTransportReservation(@RequestBody TransportReservation transportReservation) {  
//			logger.info("{}:  Calling updateTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(), transportReservation.getFileNo());
//			Response response = new Response();
//			try
//			{
//				   Long fileNo=transportWorkflowManager.updateTransportReservationDtl(transportReservation);
//				   TransportReservation updatedReservation=transportWorkflowManager.getTransportReservationDtl(fileNo);
//				   response.setResponseBody(updatedReservation);
//			}
//			catch(Exception e)
//			{
//				logger.error("{}: Error While TransportService Calling updateTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(),transportReservation.getFileNo(),e);
//				response.setError(e.getLocalizedMessage());
//			}
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//			}
//			
//		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.DELETE)
//		public ResponseEntity deleteTransportReservation(@PathVariable Long fileNo ) {  
//			logger.info("{}:  Calling deleteTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
//			transportWorkflowManager.deleteTransportReservationDtl(fileNo);
//			return new ResponseEntity(HttpStatus.OK);
//		}
//
//		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.GET)
//		  public VehicleDetail getVehicleDetail(@PathVariable Long vehicleId) {  
//			logger.info("{}:  Calling getVehicleDetail method by passing vehicleId :{}",this.getClass().getName(), vehicleId);
//			VehicleDetail vehicleDetail = transportWorkflowManager.getVehicleDetail(vehicleId);
//
//			return vehicleDetail;
//		  }
//		@RequestMapping(value="/vehicleDetail",method = RequestMethod.POST)
//		public void addVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
//			logger.info("{}:  Calling addVehicleDetail method for  vehicleId :{}",this.getClass().getName(), vehicleDetail.getVehicleId());
//			transportWorkflowManager.addVehicleDetail(vehicleDetail);
//		}
//		
//		@RequestMapping(value="/vehicleDetail",method = RequestMethod.PUT)
//		public void updateVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
//			logger.info("{}:  Calling updateVehicleDetail method for  vehicleId :{}",this.getClass().getName(), vehicleDetail.getVehicleId());
//			transportWorkflowManager.updateVehicleDetail(vehicleDetail);
//		}
//		
//		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.DELETE)
//		public void deleteVehicleDetail(@PathVariable Long vehicleId ) {  
//			logger.info("{}:  Calling deleteVehicleDetail method by passing vehicleId :{}",this.getClass().getName(), vehicleId);
//			transportWorkflowManager.deleteVehicleDetail(vehicleId);
//		}
//		
//		@RequestMapping(value="TransportAllocationAdmission",method = RequestMethod.POST)
//		public ResponseEntity<Response> addTransportAllocationAdmissionDtl(@RequestBody TransportAllocationAdmission transportAllocationAdmissionBean) {
//			logger.info("{}:  Calling addTransportAllocationAdmissionDtl method for Student  :{}",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName());
//			Response response = new Response();
//			try{
//				
//	            transportWorkflowManager.addTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);
//
//	            Long fileNo = transportAllocationAdmissionBean.getBasicInfo().getFileNo();
//	            transportAllocationAdmissionBean = transportWorkflowManager.getTransportAllocationAdmissiondtl(fileNo);
//	            
//	            response.setResponseBody(transportAllocationAdmissionBean);
//	 		}
//			catch(Exception e)
//			{
//				logger.error("{}: Error While  Calling addTransportAllocationAdmissionDtl method for Student  :{}",this.getClass().getName(),transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName(),e);
//				response.setError(e.getLocalizedMessage());
//				
//			}
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//	    	}
//		
////		@RequestMapping(value="hostelTransportAdmission",method = RequestMethod.PUT)
////		public ResponseEntity<Response> updateTransportAllocationAdmissionDtl(@RequestBody TransportAllocationAdmissionBean transportAllocationAdmissionBean) {
////			logger.info("{}: Calling updateTransportAllocationAdmissionDtl method for Student  :{}",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName());
////			Response response = new Response();
////			try{
////				
////		        TransportWorkflowManager transportWorkflowManager = new TransportWorkflowManagerImpl();
////	            transportWorkflowManager.updateTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);
////
////	            Long fileNo = transportAllocationAdmissionBean.getBasicInfo().getFileNo();
////	            transportAllocationAdmissionBean = transportWorkflowManager.getTransportAllocationAdmissiondtl(fileNo);
////	            
////	            response.setResponseBody(transportAllocationAdmissionBean);
////	 		}
////			catch(Exception e)
////			{
////				logger.error("{}: Error While  Calling updateTransportAllocationAdmissionDtl method for Student :{}",this.getClass().getName(),transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName());
////				response.setError(e.getLocalizedMessage());
////				
////			}
////			return new ResponseEntity<Response>(response,HttpStatus.OK);
////	    	}
//		
//		@RequestMapping(value="transportAllocationAdmission/{fileNo}",method = RequestMethod.GET)
//		public ResponseEntity<Response> getConsultantAdmissionDetail(@PathVariable Long fileNo){
//			logger.info("{}:  Calling getTransportAllocationAdmissiondtl method by File no  :{}",this.getClass().getName(), fileNo);
//			Response response = new Response();
//			try{
//			TransportAllocationAdmission transportAllocationAdmissionBean = transportWorkflowManager.getTransportAllocationAdmissiondtl(fileNo);
//
//			response.setResponseBody(transportAllocationAdmissionBean);
//			}
//			catch(Exception e)
//			{
//				logger.error("{}: Error While Calling getTransportAllocationAdmissiondtl method by File no :{}",this.getClass().getName(),fileNo,e);
//				response.setError(e.getLocalizedMessage());
//				e.printStackTrace();
//			}
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//	     	}
//
////		@RequestMapping(value ="/transportAllocationDetailForVehicle/{vehicleId}", method = RequestMethod.GET )
////		public ResponseEntity<Response> getTransportAllocationDetailForVehicle(@PathVariable Long vehicleId){
////			logger.info("{}:  Calling getCurrentAllocationByVehichleId method by passing vehicleId : {}",this.getClass().getName(), vehicleId);
////			Response response = new Response();
////			try {
////				TransportAllocationDtlForVehicle allocationDetailForVehicle = transportWorkflowManager.getCurrentAllocationByVehichleId(vehicleId);
////				response.setResponseBody(allocationDetailForVehicle);
////			} catch (Exception e) {
////				logger.error("{}:Error While Calling getCurrentAllocationByVehichleId method by passing vehicle id : {}",this.getClass().getName(),vehicleId);
////				response.setError(e.getMessage());
////				e.printStackTrace();
////			}
////			return new ResponseEntity<Response>(response,HttpStatus.OK);
////	
////		}
////		
//		
//		@RequestMapping(value ="/studentTransportAllocationDtl/{fileNo}", method = RequestMethod.GET )	
//		public ResponseEntity<Response> getTransportAllocationDetailForStudent(@PathVariable Long fileNo){
//			logger.info("{}:  Calling getAllocationForStudent method by passing fileNo : {}",this.getClass().getName(), fileNo);
//			Response response = new Response();
//			try {
//				TransportAllocationForStudent allocationForStudent = transportWorkflowManager.getAllocationForStudent(fileNo);
//			response.setResponseBody(allocationForStudent);
//			} catch (Exception e) {
//				logger.error("{}:Error While Calling getAllocationForStudent method by passing fileNo : {}",this.getClass().getName(),fileNo,e);
//				response.setError(e.getMessage());
//			}
//			
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//			
//		}
}
