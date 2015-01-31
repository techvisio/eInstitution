package com.techvisio.einstitution.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.TaskAndFollowUp;
import com.techvisio.einstitution.manager.TaskFollowManager;
import com.techvisio.einstitution.manager.impl.TaskFollowManagerImpl;

@RestController
@RequestMapping("/TaskFollow")
public class TaskFollowService {
	//private static final Logger logger = Logger.getLogger(TaskFollowService.class);
	@RequestMapping(value="/GetByTaskId/{taskId}",method= RequestMethod.GET )
	public TaskAndFollowUp getTaskAndFollowUpByTaskId(@PathVariable int taskId){
		TaskFollowManager tManager = new TaskFollowManagerImpl();
		TaskAndFollowUp followUp = tManager.getTaskAndFollowUpByTaskId(taskId);
		
		
		return followUp;
		}
	@RequestMapping(value="/GetByParentTaskId/{parentTaskId}",method = RequestMethod.GET)
	public TaskAndFollowUp getTaskAndFollowUpByParentTaskId(@PathVariable int parentTaskId){
		TaskFollowManager tManager = new TaskFollowManagerImpl();
		TaskAndFollowUp followUp = tManager.getTaskAndFollowUpByParentTaskId(parentTaskId);
		
		return followUp;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addTaskAndFollowUp(@RequestBody TaskAndFollowUp taskAndFollowUp){
		TaskFollowManager tManager = new TaskFollowManagerImpl();
		tManager.addTaskAndFollowUp(taskAndFollowUp);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateTaskAndFollowUp(@RequestBody TaskAndFollowUp taskAndFollowUp){
		TaskFollowManager followManager = new TaskFollowManagerImpl();
		followManager.updateTaskAndFollowUp(taskAndFollowUp);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteTaskAndFollowUp(@RequestBody TaskAndFollowUp taskAndFollowUp){
		TaskFollowManager followManager = new TaskFollowManagerImpl();
		followManager.deleteTaskAndFollowUp(taskAndFollowUp);
	}
	
	
}
