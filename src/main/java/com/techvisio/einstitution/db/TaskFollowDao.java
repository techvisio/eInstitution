package com.techvisio.einstitution.db;

import com.techvisio.einstitution.beans.TaskAndFollowUp;

public interface TaskFollowDao {
	public TaskAndFollowUp getTaskAndFollowUpByTaskId(int taskId);
	public TaskAndFollowUp getTaskAndFollowUpByParentTaskId(int parentTaskId);
	public void addTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);
	public void updateTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);
	public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);

}
