package com.techvisio.einstitution.db;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techvisio.einstitution.beans.AdmissionEnquiry;

public class InquiryDaotest {

	@Test
	public void testAddInquiry(){
		AdmissionEnquiry enquiry=new AdmissionEnquiry();
		enquiry.setEnquiryId(1);
		enquiry.setIntrestedCourseId(1);
		enquiry.setIntrestedBranchId(1);
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
		InquiryDao dao=ctx.getBean(InquiryDao.class);
		dao.addInquiry(enquiry);
	}
}
