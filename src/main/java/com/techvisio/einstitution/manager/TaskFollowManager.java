package com.techvisio.einstitution.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
@Component
public interface TaskFollowManager {
	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(Long entityId, String module);
	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps);
	//public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp);

}
