package com.techvisio.einstitution.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.manager.impl.TransportManagerImpl;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
import com.techvisio.einstitution.workflow.impl.TransportWorkflowManagerImpl;

@RestController
@RequestMapping("/transport")

public class TransportService {

	
		private static final Logger logger = Logger.getLogger(InquiryService.class);
		
		@RequestMapping(value="/AvailableTransport",method = RequestMethod.GET)
		  public List<AvailableTransport> getTransportAllocation() {
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			List<AvailableTransport> availableTransports=workflowManager.getAvailableTransport();
			
			return availableTransports;  
		 
			
		  }
		
		@RequestMapping(value="/Allocation/{fileNo}",method = RequestMethod.GET)
		  public TransportAllocation getTransportAllocation(@PathVariable String fileNo) {
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			TransportAllocation transportAllocation=workflowManager.getTransportAllocationDtl(fileNo);
			
			return transportAllocation;  
		 
			
		  }
		@RequestMapping(value="/Allocation",method = RequestMethod.POST)
		public void addTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			workflowManager.addTransportAllocationDtl(transportAllocation);
		}
		
		@RequestMapping(value="/Allocation",method = RequestMethod.PUT)
		public void updateTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			workflowManager.updateTransportAllocationDtl(transportAllocation);
		}
		@RequestMapping(value="/Allocation/{fileNo}",method = RequestMethod.DELETE)
		public void deleteTransportAllocationDtl(@PathVariable String fileNo ) {  
			TransportWorkflowManager workflowManager = new TransportWorkflowManagerImpl();
			workflowManager.deleteTransportAllocationDtl(fileNo);
		}



		@RequestMapping(value="/Reservation/{file_No}",method = RequestMethod.GET)
		  public TransportReservation getTransportReservation(@PathVariable String fileNo) {  
		
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			TransportReservation transportReservation=workflowManager.getTransportReservationDtl(fileNo);
			
			return transportReservation;  
		  }
		@RequestMapping(value="/Reservation",method = RequestMethod.POST)
		public void addTransporReservation(@RequestBody TransportReservation transportReservation) {  
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			workflowManager.addTransportReservationDtl(transportReservation);
		}
		
		@RequestMapping(value="/Reservation",method = RequestMethod.PUT)
		public void updateTransportReservation(@RequestBody TransportReservation transportReservation) {  
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			workflowManager.updateTransportReservationDtl(transportReservation);
		}
		@RequestMapping(value="/Reservation/{file_No}",method = RequestMethod.DELETE)
		public void deleteTransportReservation(@PathVariable String fileNo ) {  
			TransportWorkflowManager workflowManager = new TransportWorkflowManagerImpl();
			workflowManager.deleteTransportAllocationDtl(fileNo);
		}

		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.GET)
		  public VehicleDetail getVehicleDetail(@PathVariable Long vehicleId) {  
			
			TransportWorkflowManager workflowManager = new TransportWorkflowManagerImpl();
			VehicleDetail vehicleDetail = workflowManager.getVehicleDetail(vehicleId);

			return vehicleDetail;
		  }
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.POST)
		public void addVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
			
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			workflowManager.addVehicleDetail(vehicleDetail);
		}
		
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.PUT)
		public void updateTransportReservation(@RequestBody VehicleDetail vehicleDetail) {  
			TransportWorkflowManager workflowManager=new TransportWorkflowManagerImpl();
			workflowManager.updateVehicleDetail(vehicleDetail);
		}
		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.DELETE)
		public void deleteTransportReservation(@PathVariable Long vehicleId ) {  
			TransportWorkflowManager workflowManager = new TransportWorkflowManagerImpl();
			workflowManager.deleteVehicleDetail(vehicleId);
		}
		
	}
