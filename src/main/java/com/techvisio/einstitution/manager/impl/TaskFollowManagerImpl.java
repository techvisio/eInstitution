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
import com.techvisio.einstitution.util.CustomLogger;
@Component
public class TaskFollowManagerImpl implements TaskFollowManager {
	private static CustomLogger logger = CustomLogger.getLogger(TaskFollowManagerImpl.class);
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
		logger.info("{} : calling getTaskAndFollowUpByByModuleAndEntityId method by passing entityId:{} and module:{} ",this.getClass().getName(), entityId,module);		
		List<TaskAndFollowUp> followUp = taskFollowDao.getTaskAndFollowUpByByModuleAndEntityId(entityId, module);
		return followUp;
	}

	

	public void saveTaskAndFollowUp(List<TaskAndFollowUp> taskAndFollowUps){
		logger.info("{} : calling saveTaskAndFollowUp method by passing taskAndFollowUps:{}  ",this.getClass().getName(),taskAndFollowUps);
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
