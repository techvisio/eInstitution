package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.manager.HostelAllocationManger;
import com.techvisio.einstitution.manager.impl.HostelAllocationManagerImpl;

@RestController
@RequestMapping("/HostelAllocation")
public class HostelAllocationService {
	private static final Logger logger = Logger.getLogger(HostelAllocationService.class);
	
	@RequestMapping(value="{/fileNo}",method = RequestMethod.GET )
	public HostelAllocation getHostelAllocation(@PathVariable String fileNo){
		HostelAllocation hostelAllocation = new HostelAllocation();
		hostelAllocation.setFileNo(fileNo);
		
		return hostelAllocation;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		HostelAllocationManger hostelAllocationManger = new HostelAllocationManagerImpl();
		hostelAllocationManger.addHostelAllocation(hostelAllocation);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		HostelAllocationManger hostelAllocationManger = new HostelAllocationManagerImpl();
		hostelAllocationManger.addHostelAllocation(hostelAllocation);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteHostelAllocation(@PathVariable String fileNo){
		
		HostelAllocationManger hostelAllocationManger = new HostelAllocationManagerImpl();
		hostelAllocationManger.deleteHostelAllocation(fileNo);
		
	}	
	
}
