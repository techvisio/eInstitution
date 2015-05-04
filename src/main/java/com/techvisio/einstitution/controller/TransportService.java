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

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.HostelAllocationAdmissionBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmissionBean;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
import com.techvisio.einstitution.workflow.impl.HostelWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.TransportWorkflowManagerImpl;

@RestController
@RequestMapping("/transport")

public class TransportService {
	
	private static CustomLogger logger = CustomLogger.getLogger(TransportService.class);
		
		@Autowired
		TransportWorkflowManager workflowManager;
		
		@RequestMapping(value="/availableTransport",method = RequestMethod.GET)
		  public List<AvailableTransport> getTransportAllocation() {
			logger.info("{} TransportService Calling getAvailableTransport method by : {}",this.getClass().getName());
			List<AvailableTransport> availableTransports=workflowManager.getAvailableTransport();
			return availableTransports;  
			
		  }
		
		@RequestMapping(value="/allocation/{fileNo}",method = RequestMethod.GET)
		  public TransportAllocation getTransportAllocation(@PathVariable Long fileNo) {
			logger.info("{} TransportService Calling getTransportAllocationDtl method by : fileNo :{}",this.getClass().getName(), fileNo);
			TransportAllocation transportAllocation=workflowManager.getTransportAllocationDtl(fileNo);
			return transportAllocation;  
			
		  }
		@RequestMapping(value="/allocation",method = RequestMethod.POST)
		public void addTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			logger.info("{} TransportService Calling addTransportAllocationDtl method for : fileNo :{}",this.getClass().getName(), transportAllocation.getFileNo());
			workflowManager.addTransportAllocationDtl(transportAllocation);
		}
		
		@RequestMapping(value="/allocation",method = RequestMethod.PUT)
		public void updateTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			logger.info("{} TransportService Calling updateTransportAllocationDtl method for : fileNo :{}",this.getClass().getName(), transportAllocation.getFileNo());
			workflowManager.updateTransportAllocationDtl(transportAllocation);
		}
		@RequestMapping(value="/allocation/{fileNo}",method = RequestMethod.DELETE)
		public void deleteTransportAllocationDtl(@PathVariable Long fileNo ) {  
			logger.info("{} TransportService Calling deleteTransportAllocationDtl method by : fileNo :{}",this.getClass().getName(), fileNo);
			workflowManager.deleteTransportAllocationDtl(fileNo);
		}



		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.GET)
		  public ResponseEntity<Response> getTransportReservation(@PathVariable Long fileNo) {  
			logger.info("{} TransportService Calling getTransportReservationDtl method by : fileNo :{}",this.getClass().getName(), fileNo);
			Response response=new Response();
			try
			{
			       TransportReservation transportReservation=workflowManager.getTransportReservationDtl(fileNo);
				   response.setResponseBody(transportReservation);
				   if(transportReservation==null){
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
		         	logger.error("Error While",e);
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
      		}

		@RequestMapping(value="/reservation",method = RequestMethod.POST)
		public ResponseEntity<Response> addTransporReservation(@RequestBody TransportReservation transportReservation) {  
			logger.info("{} TransportService Calling addTransportReservationDtl method for : fileNo :{}",this.getClass().getName(), transportReservation.getFileNo());
			Response response = new Response();
			try
			{
			   Long fileNo=workflowManager.addTransportReservationDtl(transportReservation);
			   TransportReservation updatedReservation=workflowManager.getTransportReservationDtl(fileNo);
			   response.setResponseBody(updatedReservation);
			}
			catch(Exception e)
			{
				logger.error("Error While",e);
				response.setError(e.getLocalizedMessage());
			}
			
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		
		@RequestMapping(value="/reservation",method = RequestMethod.PUT)
		public ResponseEntity<Response> updateTransportReservation(@RequestBody TransportReservation transportReservation) {  
			logger.info("{} TransportService Calling updateTransportReservationDtl method for : fileNo :{}",this.getClass().getName(), transportReservation.getFileNo());
			Response response = new Response();
			try
			{
				   Long fileNo=workflowManager.updateTransportReservationDtl(transportReservation);
				   TransportReservation updatedReservation=workflowManager.getTransportReservationDtl(fileNo);
				   response.setResponseBody(updatedReservation);
			}
			catch(Exception e)
			{
				logger.error("Error While",e);
				response.setError(e.getLocalizedMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
			}
			
		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.DELETE)
		public ResponseEntity deleteTransportReservation(@PathVariable Long fileNo ) {  
			logger.info("{} TransportService Calling deleteTransportReservationDtl method by : fileNo :{}",this.getClass().getName(), fileNo);
			workflowManager.deleteTransportReservationDtl(fileNo);
			return new ResponseEntity(HttpStatus.OK);
		}

		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.GET)
		  public VehicleDetail getVehicleDetail(@PathVariable Long vehicleId) {  
			logger.info("{} TransportService Calling getVehicleDetail method for : vehicleId :{}",this.getClass().getName(), vehicleId);
			VehicleDetail vehicleDetail = workflowManager.getVehicleDetail(vehicleId);

			return vehicleDetail;
		  }
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.POST)
		public void addVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
			logger.info("{} TransportService Calling addVehicleDetail method for : vehicleId :{}",this.getClass().getName(), vehicleDetail.getVehicleId());
			workflowManager.addVehicleDetail(vehicleDetail);
		}
		
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.PUT)
		public void updateVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
			logger.info("{} TransportService Calling updateVehicleDetail method for : vehicleId :{}",this.getClass().getName(), vehicleDetail.getVehicleId());
			workflowManager.updateVehicleDetail(vehicleDetail);
		}
		
		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.DELETE)
		public void deleteVehicleDetail(@PathVariable Long vehicleId ) {  
			logger.info("{} TransportService Calling deleteVehicleDetail method by : vehicleId :{}",this.getClass().getName(), vehicleId);
			workflowManager.deleteVehicleDetail(vehicleId);
		}
		
		@RequestMapping(value="TransportAllocationAdmission",method = RequestMethod.POST)
		public ResponseEntity<Response> addTransportAllocationAdmissionDtl(@RequestBody TransportAllocationAdmissionBean transportAllocationAdmissionBean) {
			logger.info("{} TransportService Calling addTransportAllocationAdmissionDtl method for :Student  :{}",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName());
			Response response = new Response();
			try{
				
				TransportWorkflowManager transportWorkflowManager = new TransportWorkflowManagerImpl();
	            transportWorkflowManager.addTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);

	            Long fileNo = transportAllocationAdmissionBean.getBasicInfo().getFileNo();
	            transportAllocationAdmissionBean = transportWorkflowManager.getTransportAllocationAdmissiondtl(fileNo);
	            
	            response.setResponseBody(transportAllocationAdmissionBean);
	 		}
			catch(Exception e)
			{
				logger.error("Error While{}",e);
				response.setError(e.getLocalizedMessage());
				
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
	    	}
		
		@RequestMapping(value="hostelTransportAdmission",method = RequestMethod.PUT)
		public ResponseEntity<Response> updateTransportAllocationAdmissionDtl(@RequestBody TransportAllocationAdmissionBean transportAllocationAdmissionBean) {
			logger.info("{} TransportService Calling updateTransportAllocationAdmissionDtl method for :Student  :{}",this.getClass().getName(), transportAllocationAdmissionBean.getBasicInfo().getFirstName()+ transportAllocationAdmissionBean.getBasicInfo().getLastName());
			Response response = new Response();
			try{
				
		        TransportWorkflowManager transportWorkflowManager = new TransportWorkflowManagerImpl();
	            transportWorkflowManager.updateTransportAllocationAdmissionDtl(transportAllocationAdmissionBean);

	            Long fileNo = transportAllocationAdmissionBean.getBasicInfo().getFileNo();
	            transportAllocationAdmissionBean = transportWorkflowManager.getTransportAllocationAdmissiondtl(fileNo);
	            
	            response.setResponseBody(transportAllocationAdmissionBean);
	 		}
			catch(Exception e)
			{
				logger.error("Error While{}",e);
				response.setError(e.getLocalizedMessage());
				
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
	    	}
		
		@RequestMapping(value="transportAllocationAdmission/{fileNo}",method = RequestMethod.GET)
		public ResponseEntity<Response> getConsultantAdmissionDetail(@PathVariable Long fileNo){
			logger.info("{} TransportService Calling getTransportAllocationAdmissiondtl method : File no  :{}",this.getClass().getName(), fileNo);
			Response response = new Response();
			try{
            TransportWorkflowManager transportWorkflowManager = new TransportWorkflowManagerImpl(); 
			TransportAllocationAdmissionBean transportAllocationAdmissionBean = transportWorkflowManager.getTransportAllocationAdmissiondtl(fileNo);

			response.setResponseBody(transportAllocationAdmissionBean);
			}
			catch(Exception e)
			{
				logger.error("Error While{}",e);
				response.setError(e.getLocalizedMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
	     	}
		
	}
