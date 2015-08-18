
package com.techvisio.einstitution.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})
@Transactional
public class FeeDetailDaoTest {
	
	@Autowired
	FeeDao dao;
	
	@Test
	public void getFee(){
		ApplicableFeeCriteria applicableFeeCriteria = new ApplicableFeeCriteria();
		applicableFeeCriteria.setBranchId(1L);
		applicableFeeCriteria.setCentreId(20L);
		applicableFeeCriteria.setCourseId(1L);
		applicableFeeCriteria.setSessionId(1L);
		applicableFeeCriteria.setShiftId(10L);
		
		List<ApplicableFeeDetail> applicableFeeDetail = dao.getApplicableFeeDetails(applicableFeeCriteria);
		
		System.out.println("Data Is = " + applicableFeeDetail);
	}

//	@Test
//	public void getApplicableFee(){
//		
//		ApplicableFeeCriteria applicableFeeCriteria= dao.getApplicableFeeCriteria();
//		
//		System.out.println("data is :" + applicableFeeCriteria);
//	}
	}
