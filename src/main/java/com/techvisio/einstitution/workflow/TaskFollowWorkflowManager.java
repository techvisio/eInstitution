package com.techvisio.einstitution.workflow;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
@Component
public interface TaskFollowWorkflowManager {

	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(Long entityId, String module);
	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps);

}
