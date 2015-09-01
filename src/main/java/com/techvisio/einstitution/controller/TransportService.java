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
@RequestMapping("/service/transport")

public class TransportService {
	
	private static CustomLogger logger = CustomLogger.getLogger(TransportService.class);
		
		@Autowired
		TransportWorkflowManager transportWorkflowManager;
		
		@RequestMapping(value="/availableTransport",method = RequestMethod.GET)
		  public List<AvailableTransport> getTransportAllocation() {
			logger.info("{}:  Calling getAvailableTransport method",this.getClass().getName());
			List<AvailableTransport> availableTransports=transportWorkflowManager.getAvailableTransport();
			return availableTransports;  
		  }
		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.GET)
		  public ResponseEntity<Response> getTransportReservation(@PathVariable Long fileNo) {  
			logger.info("{}  Calling getTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
			Response response=new Response();
			try
			{
			       TransportReservation transportReservation=transportWorkflowManager.getTransportReservationDtl(fileNo);
				   response.setResponseBody(transportReservation);
				   if(transportReservation==null){
					   response.setError("No record available");
				   }
			}
			catch(Exception e)
			{
		         	response.setError(e.getMessage());
		         	logger.error("{}: Error While Calling getTransportReservationDtl method by fileNo :{}",this.getClass().getName(),fileNo,e);
			}
			return new ResponseEntity<Response>(response,HttpStatus.OK);
      		}

		@RequestMapping(value="/reservation",method = RequestMethod.POST)
		public ResponseEntity<Response> addTransporReservation(@RequestBody TransportReservation transportReservation) {  
			logger.info("{} : Calling addTransportReservationDtl method for fileNo :{}",this.getClass().getName(), transportReservation.getFileNo());
			Response response = new Response();
			try
			{
			   transportWorkflowManager.saveTransportReservationDtl(transportReservation);
			   TransportReservation updatedReservation=transportWorkflowManager.getTransportReservationDtl(transportReservation.getFileNo());
			   response.setResponseBody(updatedReservation);
			}
			catch(Exception e)
			{
				logger.error("{}: Error While  Calling addTransportReservationDtl method for fileNo :{}",this.getClass().getName(),transportReservation.getFileNo(),e);
				response.setError(e.getLocalizedMessage());
			}
			
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.DELETE)
		public ResponseEntity deleteTransportReservation(@PathVariable Long fileNo ) {  
			logger.info("{}:  Calling deleteTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
			transportWorkflowManager.deleteTransportReservationDtl(fileNo);
			return new ResponseEntity(HttpStatus.OK);
		}
}
