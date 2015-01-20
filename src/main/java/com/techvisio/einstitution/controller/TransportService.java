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

@RestController
@RequestMapping("/transport")

public class TransportService {

	
		private static final Logger logger = Logger.getLogger(InquiryService.class);
		
		@RequestMapping(value="/AvailableTransport",method = RequestMethod.GET)
		  public List<AvailableTransport> getTransportAllocation() {
			TransportManager transportManager=new TransportManagerImpl();
			List<AvailableTransport> availableTransports=transportManager.getAvailableTransport();
			
			return availableTransports;  
		 
			
		  }
		
		@RequestMapping(value="/Allocation/{fileNo}",method = RequestMethod.GET)
		  public TransportAllocation getTransportAllocation(@PathVariable String fileNo) {
			TransportManager transportManager=new TransportManagerImpl();
			TransportAllocation transportAllocation=transportManager.getTransportAllocationDtl(fileNo);
			
			return transportAllocation;  
		 
			
		  }
		@RequestMapping(value="/Allocation",method = RequestMethod.POST)
		public void addTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.addTransportAllocationDtl(transportAllocation);
		}
		
		@RequestMapping(value="/Allocation",method = RequestMethod.PUT)
		public void updateTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.updateTransportAllocationDtl(transportAllocation);
		}
		@RequestMapping(value="/Allocation/{fileNo}",method = RequestMethod.DELETE)
		public void deleteTransportAllocationDtl(@PathVariable String fileNo ) {  
			TransportManager transportManager = new TransportManagerImpl();
			transportManager.deleteTransportAllocationDtl(fileNo);
		}



		@RequestMapping(value="/Reservation/{file_No}",method = RequestMethod.GET)
		  public TransportReservation getTransportReservation(@PathVariable String fileNo) {  
			TransportReservation transportReservation = new TransportReservation();
			transportReservation.setFileNo(fileNo);
		  return transportReservation;
		  }
		@RequestMapping(value="/Reservation",method = RequestMethod.POST)
		public void addTransporReservation(@RequestBody TransportReservation transportReservation) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.addTransportReservationDtl(transportReservation);
		}
		
		@RequestMapping(value="/Reservation",method = RequestMethod.PUT)
		public void updateTransportReservation(@RequestBody TransportReservation transportReservation) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.updateTransportReservationDtl(transportReservation);
		}
		@RequestMapping(value="/Reservation/{file_No}",method = RequestMethod.DELETE)
		public void deleteTransportReservation(@PathVariable String fileNo ) {  
			TransportManager transportManager = new TransportManagerImpl();
			transportManager.deleteTransportAllocationDtl(fileNo);
		}

		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.GET)
		  public VehicleDetail getVehicleDetail(@PathVariable Long vehicleId) {  
			VehicleDetail vehicleDetail = new VehicleDetail();
            vehicleDetail.setVehicleId(vehicleId);
		  return vehicleDetail;
		  }
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.POST)
		public void addVehicleDetail(@RequestBody VehicleDetail vehicleDetail) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.addVehicleDetail(vehicleDetail);
		}
		
		@RequestMapping(value="/vehicleDetail",method = RequestMethod.PUT)
		public void updateTransportReservation(@RequestBody VehicleDetail vehicleDetail) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.updateVehicleDetail(vehicleDetail);
		}
		@RequestMapping(value="/vehicleDetail/{vehicleId}",method = RequestMethod.DELETE)
		public void deleteTransportReservation(@PathVariable Long vehicleId ) {  
			TransportManager transportManager = new TransportManagerImpl();
			transportManager.deleteVehicleDetail(vehicleId);
		}
		
	}
