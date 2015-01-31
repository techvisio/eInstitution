package com.techvisio.einstitution.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.WorkFlowFieldMapping;
import com.techvisio.einstitution.manager.WorkFlowManager;
import com.techvisio.einstitution.manager.impl.WorkFlowManagerImpl;

@RestController
@RequestMapping("/WorkFlow")

public class WorkFlowService {
	
	//private static final Logger logger = Logger.getLogger(WorkFlowService.class);	
	
	@RequestMapping(value="/GetByFlowStepId/{workFlowStepId}",method = RequestMethod.GET )
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(@PathVariable String workFlowStepId){
		WorkFlowManager flowManager= new WorkFlowManagerImpl();
		WorkFlowFieldMapping fieldMapping=flowManager.getWorkFlowFieldMappingByWorkFlowStepId(workFlowStepId);
		return fieldMapping;
		}
	
	@RequestMapping(value="/{workFlowId}", method = RequestMethod.GET)
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(@PathVariable String workFlowId){
		WorkFlowManager flowManager = new WorkFlowManagerImpl();
		WorkFlowFieldMapping fieldMapping = flowManager.getWorkFlowFieldMappingByWorkFlowId(workFlowId);
		return fieldMapping;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addWorkFlowFieldMapping(@RequestBody WorkFlowFieldMapping workFlowFieldMapping){
		WorkFlowManager flowManager = new WorkFlowManagerImpl();
		flowManager.addWorkFlowFieldMapping(workFlowFieldMapping);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateWorkFlowFieldMapping(@RequestBody WorkFlowFieldMapping workFlowFieldMapping){
		WorkFlowManager flowManager = new WorkFlowManagerImpl();
		flowManager.updateWorkFlowFieldMapping(workFlowFieldMapping);
		}
	
	@RequestMapping(value="/{workFlowStepId}", method = RequestMethod.DELETE)
	public void deleteWorkFlowFieldMapping(@PathVariable String workFlowStepId){
		WorkFlowManager flowManager = new WorkFlowManagerImpl();
		flowManager.deleteWorkFlowFieldMapping(workFlowStepId);
	}
}