package com.techvisio.einstitution.manager.impl;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.db.TaskFollowDao;
import com.techvisio.einstitution.manager.TaskFollowManager;
import com.techvisio.einstitution.util.ContextProvider;

public class TaskFollowManagerImpl implements TaskFollowManager {
	
	TaskFollowDao  taskFollowDao = ContextProvider.getContext().getBean(TaskFollowDao.class);

	public TaskAndFollowUp getTaskAndFollowUpByTaskId(int taskId) {
		TaskAndFollowUp followUp = taskFollowDao.getTaskAndFollowUpByTaskId(taskId);
		return followUp;
	}

	public TaskAndFollowUp getTaskAndFollowUpByParentTaskId(int parentTaskId) {
		TaskAndFollowUp followUp = taskFollowDao.getTaskAndFollowUpByParentTaskId(parentTaskId);
		return followUp;
	}

	public void addTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		taskFollowDao.addTaskAndFollowUp(taskAndFollowUp);
		
	}

	public void updateTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		taskFollowDao.updateTaskAndFollowUp(taskAndFollowUp);
	}

	public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		taskFollowDao.deleteTaskAndFollowUp(taskAndFollowUp);
	}

}
