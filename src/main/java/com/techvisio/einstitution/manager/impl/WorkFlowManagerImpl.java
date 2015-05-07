package com.techvisio.einstitution.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.WorkFlowFieldMapping;
import com.techvisio.einstitution.db.WorkFlowDao;
import com.techvisio.einstitution.manager.WorkFlowManager;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class WorkFlowManagerImpl implements WorkFlowManager{
	private static CustomLogger logger = CustomLogger.getLogger(WorkFlowManagerImpl.class);	
	@Autowired
	WorkFlowDao workFlowDao ;

	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowStepId(String workFlowStepId) {
		logger.info("{} : calling getWorkFlowFieldMappingByWorkFlowStepId method by passing workFlowStepId:{} ",this.getClass().getName(), workFlowStepId);
		WorkFlowFieldMapping work = workFlowDao.getWorkFlowFieldMappingByWorkFlowStepId(workFlowStepId);
		return work;
	}
	
	
	public WorkFlowFieldMapping getWorkFlowFieldMappingByWorkFlowId(String workFlowId) {
		logger.info("{} : calling getWorkFlowFieldMappingByWorkFlowId method by passing workFlowId:{} ",this.getClass().getName(), workFlowId);
		WorkFlowFieldMapping work = workFlowDao.getWorkFlowFieldMappingByWorkFlowId(workFlowId);
		return work;
	}
	

	public void addWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping) {
		logger.info("{} : calling addWorkFlowFieldMapping method for workFlowId:{} ",this.getClass().getName(), workFlowFieldMapping.getWorkFlowId());
		workFlowDao.addWorkFlowFieldMapping(workFlowFieldMapping);
	}

	public void updateWorkFlowFieldMapping(WorkFlowFieldMapping workFlowFieldMapping) {
		logger.info("{} : calling updateWorkFlowFieldMapping method for workFlowId:{} ",this.getClass().getName(), workFlowFieldMapping.getWorkFlowId());
		workFlowDao.updateWorkFlowFieldMapping(workFlowFieldMapping);
	}

	public void deleteWorkFlowFieldMapping(String workFlowStepId) {
		logger.info("{} : calling deleteWorkFlowFieldMapping method by passing workFlowStepId:{} ",this.getClass().getName(), workFlowStepId);
		workFlowDao.deleteWorkFlowFieldMapping(workFlowStepId);
	}

}
