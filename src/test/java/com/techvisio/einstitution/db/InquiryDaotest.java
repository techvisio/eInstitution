package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.AdmissionInquiry;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })




public class InquiryDaotest {

	@Autowired
	InquiryDao dao;

	// @Test
	// public void testAddInquiry(){
	// AdmissionInquiry enquiry=new AdmissionInquiry();
	// enquiry.setEnquiryId(5L);
	// enquiry.setIntrestedCourseId(1L);
	// enquiry.setIntrestedBranchId(1L);
	// ApplicationContext ctx=new
	// ClassPathXmlApplicationContext("spring-config/Application-context.xml");
	// InquiryDao dao=ctx.getBean(InquiryDao.class);
	// dao.addInquiry(enquiry);
	// }


	@Test
	public void testAddInquiry(){

		AdmissionInquiry admissionInquiry = new AdmissionInquiry();

		admissionInquiry.setEnquiryId(2L);
		admissionInquiry.setIntrestedCourseId(2L);
		admissionInquiry.setIntrestedBranchId(2L);
		admissionInquiry.setFatherName("Anil Pathak");
		admissionInquiry.setName("Ananad");
		admissionInquiry.setContactNo("8087682394");

		dao.addInquiry(admissionInquiry);


	}

	@Test
	public void testGetInquiry() {

		AdmissionInquiry admissionInquiry = dao.getInquiry(1L);
		System.out.println(admissionInquiry);


	}

	@Test	
	public void testUpdateInquiry(){

		AdmissionInquiry admissionInquiry = dao.getInquiry(1L);
		admissionInquiry.setContactNo("9808790878");
		dao.updateInquiry(admissionInquiry);


	}

	@Test	
	public void testDeleteInquiry(){

		dao.deleteInquiry(2L);
	}
}