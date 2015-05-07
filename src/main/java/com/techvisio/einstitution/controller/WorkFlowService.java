package com.techvisio.einstitution.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.WorkFlowFieldMapping;
import com.techvisio.einstitution.manager.WorkFlowManager;
import com.techvisio.einstitution.manager.impl.WorkFlowManagerImpl;
import com.techvisio.einstitution.util.CustomLogger;

@RestController
@RequestMapping("/WorkFlow")

public class WorkFlowService {
	
	private static CustomLogger logger = CustomLogger.getLogger(WorkFlowService.class);	
	
	@Autowired
	WorkFlowManager flowManager;
	
	@RequestMapping(value="/GetByFlowStepId/{workFlowStepId}",method = RequestMethod.GET )
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(@PathVariable String workFlowStepId){
		logger.info("{}:  Calling getWorkFlowFieldMappingByWorkFlowStepId method by passing workFlowStepId : {}",this.getClass().getName(), workFlowStepId);
		WorkFlowFieldMapping fieldMapping=flowManager.getWorkFlowFieldMappingByWorkFlowStepId(workFlowStepId);
		return fieldMapping;
		}
	
	@RequestMapping(value="/{workFlowId}", method = RequestMethod.GET)
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(@PathVariable String workFlowId){
		logger.info("{}:  Calling getWorkFlowFieldMappingByWorkFlowId method by passing workFlowId : {}",this.getClass().getName(), workFlowId);
		WorkFlowFieldMapping fieldMapping = flowManager.getWorkFlowFieldMappingByWorkFlowId(workFlowId);
		return fieldMapping;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addWorkFlowFieldMapping(@RequestBody WorkFlowFieldMapping workFlowFieldMapping){
		logger.info("{}:  Calling addWorkFlowFieldMapping method  for workFlowId:{} and workFlowStepId:{}",this.getClass().getName(), workFlowFieldMapping.getWorkFlowId(), workFlowFieldMapping.getWorkFlowStepId());
		flowManager.addWorkFlowFieldMapping(workFlowFieldMapping);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateWorkFlowFieldMapping(@RequestBody WorkFlowFieldMapping workFlowFieldMapping){
		logger.info("{}:  Calling updateWorkFlowFieldMapping method for workFlowId:{} and workFlowStepId :{}",this.getClass().getName(), workFlowFieldMapping.getWorkFlowId(), workFlowFieldMapping.getWorkFlowStepId());
		flowManager.updateWorkFlowFieldMapping(workFlowFieldMapping);
		}
	
	@RequestMapping(value="/{workFlowStepId}", method = RequestMethod.DELETE)
	public void deleteWorkFlowFieldMapping(@PathVariable String workFlowStepId){
		logger.info("{}:  Calling deleteWorkFlowFieldMapping method by passing workFlowStepId : {}",this.getClass().getName(), workFlowStepId);
		flowManager.deleteWorkFlowFieldMapping(workFlowStepId);
	}
}