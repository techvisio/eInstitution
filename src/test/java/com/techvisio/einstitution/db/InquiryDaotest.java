package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.Student;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })

public class InquiryDaotest {

	@Autowired
	EnquiryDao dao;
	
	
	@Test
	public void testSaveEnquiry(){
	
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		admissionEnquiry.setName("Suraj");
		admissionEnquiry.setFatherName("Nihal");
		dao.saveInquiry(admissionEnquiry);	
	
	} 
	
}