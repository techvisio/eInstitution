package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})
public class HostelDaoTest {
  
	@Autowired
	HostelDao dao;
	
	@Test
	public void test(){
		
		boolean result = dao.isRoomAvailable("RNAC1");
		System.out.println("Result is:" + result);
	}
}