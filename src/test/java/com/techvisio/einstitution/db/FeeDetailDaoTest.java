
package com.techvisio.einstitution.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})

public class FeeDetailDaoTest {
	
	@Autowired
	FeeDao dao;
	
	
	//FeeDetail
	
	@Test
	public void testAddFeeDetail(){
		ApplicableFeeDetail detail = new ApplicableFeeDetail();
		detail.setBranch(4L);
		detail.setCourse(2L);
		detail.setFeeAmount(2000.0);
		
		dao.addFeeDetail(detail);
		
	}
	
	@Test
	public void testGetFeedetail(){
		List<ApplicableFeeDetail> detail = dao.getFeeDetail(2L, 2L);
		System.out.println("Data is :- "+detail);
	}
	
	@Test 
	public void testUpdateFeeDetail(){
		ApplicableFeeDetail detail = new ApplicableFeeDetail();
		detail.setBranch(4L);
		detail.setCourse(2L);
		detail.setFeeAmount(2000.12);

		detail.setFeeHeadId(2L);
		dao.updateFeeDetail(detail);
	}
	
//	@Test
//	public void testDeleteFeeDetail(){
//		dao.deleteFeeDetail(1L, 1L, 1);
//	}
	@Test
	public void testDeleteFeeDetail(){
		dao.deleteFeeDetail(1L, 1L, 1L);
	}
	
	
	
//	StudentFeeStaging
	
	@Test
	
	public void testAddStudentFeeStaging(){
		StudentFeeStaging feeStaging = new StudentFeeStaging();
		//feeStaging.setBranch(1L);
		//feeStaging.setCourse(1L);
		feeStaging.setFileNo(2015-151L);
		feeStaging.setCreatedBy("Anil");
		
		dao.addStudentFeeStaging(feeStaging);
	}
	
	@Test
	public void testGetStudentFeeStaging(){
//		StudentFeeStaging feeStaging = dao.getStudentFeeStaging("122");
//		
//		System.out.println("Data is :- "+feeStaging);
	}
	
	@Test
	public void testUpdateStudentFeeStaging(){
		StudentFeeStaging feeStaging = new StudentFeeStaging();
		//feeStaging.setBranch(4L);
	//	feeStaging.setCourse(2L);
		feeStaging.setFileNo(1L);
		
		dao.updateStudentFeeStaging(feeStaging);
	}
	
//FeeTransaction	
	
//	@Test
//	public void testAddFeeTransactiondr(){
//		FeeTransaction feeTransaction1 = new FeeTransaction();
//	
//		feeTransaction1.setComponentId(897L);
//		feeTransaction1.setFileNo("2015-148");
//        feeTransaction1.setUser("Vikas");
//        feeTransaction1.setSemester(2);
//        feeTransaction1.setRemark("jshshshshshshsh");
//		dao.addFeeTransactionDebit(feeTransaction1);
//	}
//	
//	@Test
//	public void testGetFeeTransaction(){
//		FeeTransaction feeTransaction = dao.getDebitedFeeTransaction("2015-151");
//		System.out.println("Data is :- "+feeTransaction);
//	}
//	
//	@Test
//	public void testAddFeeTransactionCr(){
//		FeeTransaction feeTransaction = new FeeTransaction();
//	
//		feeTransaction.setComponentId(897L);
//		feeTransaction.setFileNo("2015-151");
//        feeTransaction.setUser("Anil");
//        feeTransaction.setSemester(2);
//        feeTransaction.setRemark("qwerttytdadsa");
//		dao.addFeeTransactionCredit(feeTransaction);
//	}
//
//	@Test
//	public void testGetFeeTransactionCr(){
//		FeeTransaction feeTransaction = dao.getCreditedFeeTransaction("2015-151");
//		System.out.println("Data is :- "+feeTransaction);
//	}
//	
//	@Test
//	public void testDeleteStudentFeeStaging(){
//		//dao.deleteStudentFeeStaging("1");
//	}
//

    @Test
    public void testFeeDiscountHead(){
    	
    	FeeDiscountHead discountHead= dao.getfeeDiscountHead(1234L);
    	System.err.println("DISCOUNTHEAD::::: " + discountHead);
    }
    
    @Test
	public void testAddFeeDiscountHead(){
    	FeeDiscountHead feeDiscountHead = new FeeDiscountHead();
		feeDiscountHead.setHead("QWE");
		feeDiscountHead.setHeadId(897L);
		feeDiscountHead.setParentId(343L);
		feeDiscountHead.setRefundType("MNB");
		dao.addFeeDiscountHead(feeDiscountHead);
	}

    @Test
	public void testUpdateFeeDiscountHead(){
    	FeeDiscountHead feeDiscountHead = dao.getfeeDiscountHead(897L);
		feeDiscountHead.setRefundType("Refundable");
		dao.updateFeeDiscountHead(feeDiscountHead);
	}

}
