package com.techvisio.einstitution.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.AvailableTransport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })
public class TransportDaoTest {

	@Autowired
	TransportDao dao;

	@Test
	public void getAvailableTransports(){
		
		List<AvailableTransport> availableTransports = dao.getAvailableTransports();
		System.out.println("data is:" + availableTransports);
	}
}