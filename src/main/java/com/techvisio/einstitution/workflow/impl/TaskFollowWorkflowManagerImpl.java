package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.TaskFollowManager;
import com.techvisio.einstitution.manager.impl.TaskFollowManagerImpl;
import com.techvisio.einstitution.workflow.TaskFollowWorkflowManager;
@Component
public class TaskFollowWorkflowManagerImpl implements TaskFollowWorkflowManager{
	
	@Autowired
	TaskFollowManager manager;
	
	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(
			Long entityId, String module) {
		List<TaskAndFollowUp> followUps = manager.getTaskAndFollowUpByByModuleAndEntityId(entityId, module);
		return followUps;
	}

	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps) {
		if(taskAndFollowUps != null){
		manager.saveTaskAndFollowUp(taskAndFollowUps);
		}
	}

}
