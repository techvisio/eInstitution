package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.manager.CacheManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })

public class CacheManagerTest{
	
	@Autowired
	CacheManager manager;
	
	@Test
	public void test(){
		System.out.println(manager);
	}

}
