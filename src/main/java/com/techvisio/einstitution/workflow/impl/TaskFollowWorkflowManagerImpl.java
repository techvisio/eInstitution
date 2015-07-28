package com.techvisio.einstitution.workflow.impl;

import java.util.List;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.TaskFollowManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.TaskFollowWorkflowManager;
@Transactional
@Component
public class TaskFollowWorkflowManagerImpl implements TaskFollowWorkflowManager{
	private static CustomLogger logger=CustomLogger.getLogger(TaskFollowWorkflowManagerImpl.class);
	@Autowired
	TaskFollowManager manager;
	
	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(
			Long entityId, String module) {
		logger.info("{} : calling getTaskAndFollowUpByByModuleAndEntityId by passing entityId:{} and module:{} ",this.getClass().getName(),entityId,module);
		List<TaskAndFollowUp> followUps = manager.getTaskAndFollowUpByByModuleAndEntityId(entityId, module);
		return followUps;
	}

	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps) {
		if(taskAndFollowUps != null){
			logger.info("{} : calling saveTaskAndFollowUp by passing taskAndFollowUps:{} ",this.getClass().getName(),taskAndFollowUps);
			manager.saveTaskAndFollowUp(taskAndFollowUps);
		}
	}

}
