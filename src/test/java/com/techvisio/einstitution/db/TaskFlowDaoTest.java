package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.TaskAndFollowUp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})
public class TaskFlowDaoTest {
	@Autowired
	TaskFollowDao dao;
	
	
	@Test
	public void testAddTaskAndFollowUp(){
		TaskAndFollowUp followUp1 = new TaskAndFollowUp();
		followUp1.setTaskId(2);
		followUp1.setParentTaskId(1);
		followUp1.setTaskEntry("ABCD");
		followUp1.setRole("break2");
		followUp1.setUserId("2001");
		followUp1.setDueDate(null);
		followUp1.setStatus("Active");
		followUp1.setRemark("Now yo have to do something");
		dao.addTaskAndFollowUp(followUp1);
	}
	
	@Test
	public void testUpdateTaskAndFollowUp(){
		TaskAndFollowUp followUp = new TaskAndFollowUp();
		followUp.setTaskId(1);
		followUp.setParentTaskId(2);
		followUp.setTaskEntry("AB");
		followUp.setRole("break1");
		followUp.setUserId("202");
		followUp.setDueDate(null);
		followUp.setStatus("Active");
		followUp.setRemark("Now yo have to do something");
		dao.updateTaskAndFollowUp(followUp);
		
	}
	
	@Test
	public void testDeleteTaskAndFollowUp(){
		TaskAndFollowUp followUp = new TaskAndFollowUp();
		followUp.setStatus("Proccessing");
		followUp.setTaskId(2);
		dao.deleteTaskAndFollowUp(followUp);
	}
	
	@Test
	public void testGetTaskAndFollowUpByTaskId(){
		TaskAndFollowUp followUp = dao.getTaskAndFollowUpByTaskId(1);
		System.out.println("DATA BY TASKID :-  "+followUp);
	}
	
	@Test
	public void testGetTaskAndFollowUpByParentId(){
		TaskAndFollowUp followUp = dao.getTaskAndFollowUpByParentTaskId(1);
		System.out.println("DATA  BY PARENTID :-  "+followUp);
	}
	
	
	
	
}
