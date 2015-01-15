package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.manager.TransportManager;
import com.techvisio.einstitution.manager.impl.TransportManagerImpl;

@RestController
@RequestMapping("/transport")

public class TransportService {

	
	
	//TransportManager transportManager=null;
	
		private static final Logger logger = Logger.getLogger(InquiryService.class);
		
		@RequestMapping(value="/{fileNo}",method = RequestMethod.GET)
		  public TransportAllocation getTransportAllocation(@PathVariable String fileNo) {
			TransportManager transportManager=new TransportManagerImpl();
			TransportAllocation transportAllocation=transportManager.getTransportAllocationDtl(fileNo);
			
			return transportAllocation;  
		 
			
		  }
		@RequestMapping(method = RequestMethod.POST)
		public void addTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.addTransportAllocationDtl(transportAllocation);
		}
		
		@RequestMapping(method = RequestMethod.PUT)
		public void updateTransportAllocation(@RequestBody TransportAllocation transportAllocation) {  
			TransportManager transportManager=new TransportManagerImpl();
			transportManager.updateTransportAllocationDtl(transportAllocation);
		}
		@RequestMapping(value="/{fileNo}",method = RequestMethod.DELETE)
		public void deleteTransportAllocationDtl(@PathVariable String fileNo ) {  
			TransportManager transportManager = new TransportManagerImpl();
			transportManager.deleteTransportAllocationDtl(fileNo);
		}


		
	}
