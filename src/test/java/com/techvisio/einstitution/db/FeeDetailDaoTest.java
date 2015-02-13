package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})

public class FeeDetailDaoTest {
	
	@Autowired
	FeeDetailDao dao;
	
	@Test
	public void testGetFeeDetail(){
		System.out.println("Data is :-"+dao.getFeeDetail());
	}
	
	
}
