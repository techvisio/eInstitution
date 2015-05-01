package com.techvisio.einstitution.manager;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.WorkFlowFieldMapping;
@Component
public interface WorkFlowManager {
	
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(String workFlowStepId);
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(String workFlowId);
	public void addWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping);
	public void updateWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping);
	public void deleteWorkFlowFieldMapping(String workFlowStepId);


}
