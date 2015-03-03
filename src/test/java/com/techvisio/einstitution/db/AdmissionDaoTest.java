package com.techvisio.einstitution.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })

public class AdmissionDaoTest {

	@Autowired
	AdmissionDao dao;
	
	@Test
	public void testaddAdmissionDao(){
		
		StudentDetail studentDetail=new StudentDetail();
		studentDetail.setFileNo("11");
		studentDetail.setEnrollNo("3");
		studentDetail.setFirstName("Sandeep");
		studentDetail.setLastName("Gusain");
		studentDetail.setFatherName("Chaman Singh Gusain");
		studentDetail.setMotherName("Beena Gusain");
        studentDetail.setFatherOccupation("Service");
//		studentDetail.setCategoryId("3");
//		studentDetail.setCourseId("1");
//		studentDetail.setBranchId("4");
//		studentDetail.setDomicileState("2");
//		studentDetail.setCategoryId("5");
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
		AdmissionDao dao=ctx.getBean(AdmissionDao.class);
		dao.addStudentDtl(studentDetail);
	}
	
	
	@Test
	public void testGetStudentDtl(){
		StudentDetail detail = dao.getStudentDtl("1");
		System.out.println("Data is :- "+detail);
	}
	
	@Test
	public void testGetAdmission(){
		StudentDetail detail=dao.getStudentDtl("11");
		System.out.println(detail);
		
		
	}
	
	@Test
	public void testGetStudentBsInfo(){
		StudentBasicInfo info = dao.getStudentBsInfo("1");
		System.out.println("Data is :- "+info);
	}
	
	@Test
	public void testGetAdmissionBsInfo(){
		List<StudentBasicInfo> basicInfos = dao.getLatestAdmissionInfo(2);
		System.out.println("Data is :- "+basicInfos);
	}
	
		
	}

