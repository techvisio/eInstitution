package com.techvisio.einstitution.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.Consultant;
import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.beans.ConsultantPaymentCriteria;

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
	
	List<ConsultantDetail> consultantDetail = dao.getConsultantDtl("2015-151");
	System.out.println("consultant:" + consultantDetail);
}
	
@Test	
public void testGetConsulTant(){
	
	Consultant consultant = dao.getConsultant(2L);
	System.out.println(consultant);
}


@Test
public void testAddConsultantPaymentCriteria(){

	ConsultantPaymentCriteria consultantPaymentCriteria1 =  new ConsultantPaymentCriteria();
	consultantPaymentCriteria1.setFileNo("2015-151");
	consultantPaymentCriteria1.setConsultantId(2L);
	consultantPaymentCriteria1.setFeeReceived(30000D);
	consultantPaymentCriteria1.setAmountToBePaid(6000D);
	
	dao.addConsultantPaymentCriteria(consultantPaymentCriteria1);

}

@Test
public void testGetConsultantPaymentCriteria(){

	List<ConsultantPaymentCriteria> consultantPaymentCriteria = dao.getConsultantPaymentCriteria("2015-151", 1L);
     
	System.out.println("Data is:" + consultantPaymentCriteria);

}

@Test
public void testUpdateConsultantPaymentCriteria(){

	List<ConsultantPaymentCriteria> consultantPaymentCriteria = dao.getConsultantPaymentCriteria("2015-151", 1L);
for(ConsultantPaymentCriteria consultantPaymentCriteria2 : consultantPaymentCriteria)  
{
	consultantPaymentCriteria2.setAmountToBePaid(9000D); 
	
    dao.updateConsultantPaymentCriteria(consultantPaymentCriteria2);
    
}

}




}
