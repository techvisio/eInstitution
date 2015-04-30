package com.techvisio.einstitution.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	TaskFollowManager tManager;
	
	@RequestMapping(value="/GetByTaskId/{taskId}",method= RequestMethod.GET )
	public TaskAndFollowUp getTaskAndFollowUpByTaskId(@PathVariable int taskId){
		
//		TaskAndFollowUp followUp = tManager.getTaskAndFollowUpByTaskId(taskId);
		
		
		return null;
		}
	@RequestMapping(value="/GetByParentTaskId/{parentTaskId}",method = RequestMethod.GET)
	public TaskAndFollowUp getTaskAndFollowUpByParentTaskId(@PathVariable int parentTaskId){
//		TaskAndFollowUp followUp = tManager.getTaskAndFollowUpByParentTaskId(parentTaskId);
		
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addTaskAndFollowUp(@RequestBody TaskAndFollowUp taskAndFollowUp){
//		tManager.addTaskAndFollowUp(taskAndFollowUp);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateTaskAndFollowUp(@RequestBody TaskAndFollowUp taskAndFollowUp){
//		followManager.updateTaskAndFollowUp(taskAndFollowUp);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteTaskAndFollowUp(@RequestBody TaskAndFollowUp taskAndFollowUp){
//		followManager.deleteTaskAndFollowUp(taskAndFollowUp);
	}
	
	
}
