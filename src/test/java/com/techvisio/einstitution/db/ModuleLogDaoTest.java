package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.ModuleLog;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})

public class ModuleLogDaoTest {
	@Autowired
	ModuleLogDao dao;
	
	@Test
	public void testAddModuleLog(){
		ModuleLog log = new ModuleLog();
		log.setDate(null);
		log.setEntityId(2);
		log.setErrorMessage("Error due to wrong data");
		log.setOperation("Do correctly");
		log.setUserId(1);
		log.setWorkFlowOperation("W-O-R-K");
		
		dao.addModuleLog(log);
		
		
	}
	
	@Test
	public void testGetModuleLog(){
		ModuleLog log = dao.getModuleLog(1);
		
		System.out.println("DATA is : - "+log);
		
	}
	
	
	@Test
	public void testUpdateModuleLog(){
		ModuleLog log = new ModuleLog();
		log.setDate(null);
		log.setEntityId(1);
		log.setErrorMessage("Error in Code");
		log.setOperation("Do");
		log.setUserId(2);
		log.setWorkFlowOperation("W-O-R-K-S");
		
		dao.updateModuleLog(log);
	}
	
	@Test
	public void testDeleteModuleLog(){
		
		dao.deleteModuleLog(1);
	}
	
}
