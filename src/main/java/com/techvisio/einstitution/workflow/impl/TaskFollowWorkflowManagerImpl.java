package com.techvisio.einstitution.workflow.impl;

import java.util.List;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.TaskFollowManager;
import com.techvisio.einstitution.manager.impl.TaskFollowManagerImpl;
import com.techvisio.einstitution.workflow.TaskFollowWorkflowManager;

public class TaskFollowWorkflowManagerImpl implements TaskFollowWorkflowManager{
	
	TaskFollowManager manager =  TaskFollowManagerImpl.getInstance();
	
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
