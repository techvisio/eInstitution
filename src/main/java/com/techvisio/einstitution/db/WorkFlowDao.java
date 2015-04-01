package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.WorkFlowFieldMapping;

public interface WorkFlowDao   {
	
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(String workFlowStepId);
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(String workFlowId);
	public void addWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping);
	public void updateWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping);
	public void deleteWorkFlowFieldMapping(String workFlowStepId);
	
	
}
