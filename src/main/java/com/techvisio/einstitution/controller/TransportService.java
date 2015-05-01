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
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
import com.techvisio.einstitution.workflow.impl.TransportWorkflowManagerImpl;

@RestController
@RequestMapping("/transport")

public class TransportService {
	
		private static final Logger logger = Logger.getLogger(EnquiryService.class);
		
		@Autowired
		TransportWorkflowManager workflowManager;
		
		@RequestMapping(value="/availableTransport",method = RequestMethod.GET)
		  public List<AvailableTransport> getTransportAllocation() {
			List<AvailableTransport> availableTransports=workflowManager.getAvailableTransport();
			return availableTransports;  
			
		  }
		
		@RequestMapping(value="/allocation/{fileNo}",method = RequestMethod.GET)
		  public TransportAllocation getTransportAllocation(@PathVariable Long fileNo) {
			TransportAllocation transportAllocation=workflowManager.getTransportAllocationDtl(fileNo);
			return transportAllocation;  
			
		  }
		@RequestMapping(value="/allocation",method = RequestMethod.POST)
		public void addTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			workflowManager.addTransportAllocationDtl(transportAllocation);
		}
		
		@RequestMapping(value="/allocation",method = RequestMethod.PUT)
		public void updateTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			workflowManager.updateTransportAllocationDtl(transportAllocation);
		}
		@RequestMapping(value="/allocation/{fileNo}",method = RequestMethod.DELETE)
		public void deleteTransportAllocationDtl(@PathVariable Long fileNo ) {  
			workflowManager.deleteTransportAllocationDtl(fileNo);
		}



		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.GET)
		  public ResponseEntity<Response> getTransportReservation(@PathVariable Long fileNo) {  
		
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
		         	e.printStackTrace();
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
      		}

		@RequestMapping(value="/reservation",method = RequestMethod.POST)
		public ResponseEntity<Response> addTransporReservation(@RequestBody TransportReservation transportReservation) {  
			
			Response response = new Response();
			try
			{
			   Long fileNo=workflowManager.addTransportReservationDtl(transportReservation);
			   TransportReservation updatedReservation=workflowManager.getTransportReservationDtl(fileNo);
			   response.setResponseBody(updatedReservation);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				response.setError(e.getLocalizedMessage());
			}
			
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		
		@RequestMapping(value="/reservation",method = RequestMethod.PUT)
		public ResponseEntity<Response> updateTransportReservation(@RequestBody TransportReservation transportReservation) {  

			Response response = new Response();
			try
			{
				   Long fileNo=workflowManager.updateTransportReservationDtl(transportReservation);
				   TransportReservation updatedReservation=workflowManager.getTransportReservationDtl(fileNo);
				   response.setResponseBody(updatedReservation);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				response.setError(e.getLocalizedMessage());
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
			}
			
		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.DELETE)
		public ResponseEntity deleteTransportReservation(@PathVariable Long fileNo ) {  
			workflowManager.deleteTransportReservationDtl(fileNo);
			return new ResponseEntity(HttpStatus.OK);
		}

		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.GET)
		  public VehicleDetail getVehicleDetail(@PathVariable Long vehicleId) {  
			
			VehicleDetail vehicleDetail = workflowManager.getVehicleDetail(vehicleId);

			return vehicleDetail;
		  }
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.POST)
		public void addVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
			
			workflowManager.addVehicleDetail(vehicleDetail);
		}
		
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.PUT)
		public void updateVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
			workflowManager.updateVehicleDetail(vehicleDetail);
		}
		
		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.DELETE)
		public void deleteVehicleDetail(@PathVariable Long vehicleId ) {  
			workflowManager.deleteVehicleDetail(vehicleId);
		}
		
	}
