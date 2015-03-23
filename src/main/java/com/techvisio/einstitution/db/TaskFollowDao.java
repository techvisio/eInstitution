package com.techvisio.einstitution.db;

import java.util.List;

import com.techvisio.einstitution.beans.TaskAndFollowUp;

public interface TaskFollowDao {
	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(Long entityId, String module);
	public void saveTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);
	//public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);

}
