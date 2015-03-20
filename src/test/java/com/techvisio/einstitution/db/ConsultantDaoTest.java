package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ConsultantPaymentDtl;
import com.techvisio.einstitution.db.impl.ConsultantDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })


public class ConsultantDaoTest {
	
	@Autowired
	ConsultantDao dao;
	

//	@Test
//	public void testAddConsultantDtl(){
//		ConsultantDetail consultant =  new ConsultantDetail();
//		consultant.setConsultantId("3");
//		consultant.setFileNo("2");
//		consultant.setAmountToPay(2000);
//		consultant.setPaymentMode("cash");
//		consultant.setConsultancyAgreed(true);
//		consultant.setDueDate(null);
//		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-config/Application-context.xml");
//		ConsultantDao dao= ctx.getBean(ConsultantDao.class);
//		dao.addConsultantDtl(consultant);
//	
//	}
//	
	@Test
public void testGetConsultantDtl(){
	
	ConsultantDetail consultantDetail = dao.getConsultantDtl("2015-151");
	System.out.println("consultant:" + consultantDetail);
}
	
@Test	
public void testGetConsulTant(){
	
	Consultant consultant = dao.getConsultant(2L);
	System.out.println(consultant);
}



@Test
public void testDeleteConsultantDtl(){
	
	dao.deleteConsultantDtl("11");
}}