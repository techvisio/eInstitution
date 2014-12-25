package com.techvisio.einstitution.db;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techvisio.einstitution.beans.AdmissionInquiry;

public class InquiryDaotest {

	@Test
	public void testAddInquiry(){
		AdmissionInquiry enquiry=new AdmissionInquiry();
		enquiry.setEnquiryId(5L);
		enquiry.setIntrestedCourseId(1L);
		enquiry.setIntrestedBranchId(1L);
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
		InquiryDao dao=ctx.getBean(InquiryDao.class);
		dao.addInquiry(enquiry);
	}
}
