
package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})

public class FeeDetailDaoTest {
	
	@Autowired
	FeeDetailDao dao;
	
	
	//FeeDetail
	
	@Test
	public void testAddFeeDetail(){
		FeeDetail detail = new FeeDetail();
		detail.setBranch(4L);
		detail.setCourse(2L);
		detail.setFeeAmount(2000.0);
		detail.setFeeHeadId(2L);
		detail.setSemester(1);
		
		dao.addFeeDetail(detail);
		
	}
	
	@Test
	public void testGetFeedetail(){
		FeeDetail detail = dao.getFeeDetail(2L, 2L, 4L);
		System.out.println("Data is :- "+detail);
	}
	
	@Test 
	public void testUpdateFeeDetail(){
		FeeDetail detail = new FeeDetail();
		detail.setBranch(4L);
		detail.setCourse(2L);
		detail.setFeeAmount(2000.12);
		detail.setFeeHeadId(2L);
		detail.setSemester(1);
		
		dao.updateFeeDetail(detail);
	}
	
	@Test
	public void testDeleteFeeDetail(){
		dao.deleteFeeDetail(1L, 1L, 1L);
	}
	
	
	
//	StudentFeeStaging
	
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
	
//FeeTransaction	
	
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


    @Test
    public void testFeeDiscountHead(){
    	
    	FeeDiscountHead discountHead= dao.getfeeDiscountHead(1234L);
    	System.err.println("DISCOUNTHEAD::::: " + discountHead);
    }
    
    @Test
	public void testAddFeeDiscountHead(){
    	FeeDiscountHead feeDiscountHead = new FeeDiscountHead();
		feeDiscountHead.setDiscountType("IOP");
		feeDiscountHead.setHead("QWE");
		feeDiscountHead.setHeadId(897L);
		feeDiscountHead.setParentId(343L);
		feeDiscountHead.setRefundType("MNB");
		feeDiscountHead.setType("ZXC");
		dao.addFeeDiscountHead(feeDiscountHead);
	}

    @Test
	public void testUpdateFeeDiscountHead(){
    	FeeDiscountHead feeDiscountHead = dao.getfeeDiscountHead(897L);
		feeDiscountHead.setRefundType("Refundable");
		dao.updateFeeDiscountHead(feeDiscountHead);
	}

}
