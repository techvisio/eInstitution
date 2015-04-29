package com.techvisio.einstitution.db;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.WorkFlowFieldMapping;

@Component
public interface WorkFlowDao   {
	
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(String workFlowStepId);
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(String workFlowId);
	public void addWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping);
	public void updateWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping);
	public void deleteWorkFlowFieldMapping(String workFlowStepId);
	
	
}
