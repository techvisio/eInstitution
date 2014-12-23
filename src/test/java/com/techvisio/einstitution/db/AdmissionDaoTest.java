package com.techvisio.einstitution.db;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techvisio.einstitution.beans.AdmissionEnquiry;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.db.impl.AdmissionDaoImpl;

public class AdmissionDaoTest {

	@Test
	public void testAdmissionDao(){
		
		StudentDetail studentDetail=new StudentDetail();
		studentDetail.setFileNo("11");
		studentDetail.setEnrollNo("3");
		studentDetail.setFirstName("Sandeep");
		studentDetail.setLastName("Gusain");
		studentDetail.setFatherName("Chaman Singh Gusain");
		studentDetail.setMotherName("Beena Gusain");
        studentDetail.setFatherOccupation("Service");
		studentDetail.setCategoryId("3");
		studentDetail.setCourseId("1");
		studentDetail.setBranchId("4");
		studentDetail.setDomicileState("2");
		studentDetail.setCategoryId("5");
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
		AdmissionDao dao=ctx.getBean(AdmissionDao.class);
		dao.addStudentDtl(studentDetail);
	}
	
	
	
		
	}

