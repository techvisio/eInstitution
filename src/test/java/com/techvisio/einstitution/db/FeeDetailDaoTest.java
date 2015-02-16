package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})

public class FeeDetailDaoTest {
	
	@Autowired
	FeeDetailDao dao;
	
	@Test
	public void testGetFeeDetail(){
		System.out.println("Data is :-"+dao.getFeeDetail());
	}
	
	@Test
	
	public void testAddStudentFeeStaging(){
		StudentFeeStaging feeStaging = new StudentFeeStaging();
		feeStaging.setBranch(1L);
		feeStaging.setCourse(1L);
		feeStaging.setFeeGenerated(false);
		feeStaging.setFileNo("1");
		feeStaging.setSemester(1);
		
		dao.addStudentFeeStaging(feeStaging);
	}
	
	@Test
	public void testGetStudentFeeStaging(){
		StudentFeeStaging feeStaging = dao.getStudentFeeStaging("1");
		System.out.println("Data is :- "+feeStaging);
	}
	
	@Test
	public void testUpdateStudentFeeStaging(){
		StudentFeeStaging feeStaging = new StudentFeeStaging();
		feeStaging.setBranch(4L);
		feeStaging.setCourse(2L);
		feeStaging.setFeeGenerated(true);
		feeStaging.setFileNo("1");
		feeStaging.setSemester(2);
		
		dao.updateStudentFeeStaging(feeStaging);
	}
	
	
	@Test
	public void testAddFeeTransaction(){
		FeeTransaction feeTransaction = new FeeTransaction();
		feeTransaction.setAmount(1000.0);
		feeTransaction.setAmountTransactionType("Debit");
		feeTransaction.setDate(null);
		feeTransaction.setFeeId(2L);
		feeTransaction.setFileNo("1");
		feeTransaction.setUser("ABC");
		
		dao.addFeeTransaction(feeTransaction);
	}
	
	@Test
	public void testGetFeeTransaction(){
		FeeTransaction feeTransaction = dao.getFeeTransaction("1");
		System.out.println("Data is :- "+feeTransaction);
	}
	
	@Test
	public void testDeleteStudentFeeStaging(){
		dao.deleteStudentFeeStaging("1");
	}
}
