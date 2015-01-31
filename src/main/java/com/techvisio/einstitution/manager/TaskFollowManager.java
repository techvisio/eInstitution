package com.techvisio.einstitution.manager;

import com.techvisio.einstitution.beans.TaskAndFollowUp;

public interface TaskFollowManager {
	public TaskAndFollowUp getTaskAndFollowUpByTaskId(int taskId);
	public TaskAndFollowUp getTaskAndFollowUpByParentTaskId(int parentTaskId);
	public void addTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);
	public void updateTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);
	public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);

}
