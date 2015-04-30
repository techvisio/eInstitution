package com.techvisio.einstitution.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.db.TaskFollowDao;
import com.techvisio.einstitution.factory.UniqueIdentifierFactory;
import com.techvisio.einstitution.factory.UniqueIdentifierGenerator;
import com.techvisio.einstitution.manager.TaskFollowManager;
import com.techvisio.einstitution.util.ContextProvider;
@Component
public class TaskFollowManagerImpl implements TaskFollowManager {
	
	@Autowired
	TaskFollowDao  taskFollowDao;
	
	@Autowired
	UniqueIdentifierGenerator identifierGenerator;
	

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

	

	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps){
if(taskAndFollowUps !=null){
		for(TaskAndFollowUp taskAndFollowUp : taskAndFollowUps){
			
			if(taskAndFollowUp.getTaskId()==null){
				
				Long taskId = identifierGenerator.getUniqueIdentifierForTask();
				
				taskAndFollowUp.setTaskId(taskId);
				taskAndFollowUp.setStatus("O");
			}
		}
		
		taskFollowDao.saveTaskAndFollowUp(taskAndFollowUps);
}
	}


	/*public void deleteTaskAndFollowUp(TaskAndFollowUp taskAndFollowUp) {
		taskFollowDao.deleteTaskAndFollowUp(taskAndFollowUp);
	}
*/
}
