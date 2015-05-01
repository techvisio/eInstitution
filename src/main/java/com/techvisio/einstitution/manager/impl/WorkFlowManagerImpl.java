package com.techvisio.einstitution.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.WorkFlowFieldMapping;
import com.techvisio.einstitution.db.WorkFlowDao;
import com.techvisio.einstitution.manager.WorkFlowManager;
import com.techvisio.einstitution.util.ContextProvider;
@Component
public class WorkFlowManagerImpl implements WorkFlowManager{
	
	@Autowired
	WorkFlowDao workFlowDao ;

	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(String workFlowStepId) {
		WorkFlowFieldMapping work = workFlowDao.getWorkFlowFieldMappingByWorkFlowStepId(workFlowStepId);
		return work;
	}
	
	
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(String workFlowId) {
		WorkFlowFieldMapping work = workFlowDao.getWorkFlowFieldMappingByWorkFlowId(workFlowId);
		return work;
	}
	

	public void addWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping) {
		workFlowDao.addWorkFlowFieldMapping(workFlowFieldMapping);
	}

	public void updateWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping) {
		workFlowDao.updateWorkFlowFieldMapping(workFlowFieldMapping);
	}

	public void deleteWorkFlowFieldMapping(String workFlowStepId) {
		workFlowDao.deleteWorkFlowFieldMapping(workFlowStepId);
	}

}
