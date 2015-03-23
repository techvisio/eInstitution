package com.techvisio.einstitution.manager.impl;

import java.util.List;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.db.TaskFollowDao;
import com.techvisio.einstitution.manager.TaskFollowManager;
import com.techvisio.einstitution.util.ContextProvider;

public class TaskFollowManagerImpl implements TaskFollowManager {
	
	TaskFollowDao  taskFollowDao = ContextProvider.getContext().getBean(TaskFollowDao.class);
	
	

	private static TaskFollowManagerImpl instance=null;
	public static synchronized TaskFollowManagerImpl getInstance()
	{
		if(instance == null){
			instance=new TaskFollowManagerImpl();
		}
		
		return instance;
	}
	
	private TaskFollowManagerImpl() {

	}	 

	public List<TaskAndFollowUp> getTaskAndFollowUpByByModuleAndEntityId(Long entityId, String module) {
		
		List<TaskAndFollowUp> followUp = taskFollowDao.getTaskAndFollowUpByByModuleAndEntityId(entityId, module);
		return followUp;
	}

	

	public void saveTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		taskFollowDao.saveTaskAndFollowUp(taskAndFollowUp);
		
	}


	/*public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		taskFollowDao.deleteTaskAndFollowUp(taskAndFollowUp);
	}
*/
}
