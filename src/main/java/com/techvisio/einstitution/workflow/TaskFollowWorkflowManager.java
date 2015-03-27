package com.techvisio.einstitution.workflow;

import java.util.List;

import com.techvisio.einstitution.beans.TaskAndFollowUp;

public interface TaskFollowWorkflowManager {

	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(Long entityId, String module);
	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps);

}
