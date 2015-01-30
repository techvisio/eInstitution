package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.CounsellingDetail;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })

public class CounsellingTest {

	@Autowired
	CounsellingDao counsellingDao;
	
	@Test
	public void addCounsellingDtl(){
		
		CounsellingDetail counsellingDetail = new CounsellingDetail();
		counsellingDetail.setCategory_Rank(120874L);
		counsellingDetail.setFileNo("2015/117");
		counsellingDetail.setCounsellingId(1L);
		counsellingDetail.setRank(12432556L);
		counsellingDetail.setRoll_No("bdg1232");
		counsellingDetail.setPercentile(89.5F);
		
		counsellingDao.addCounsellingDetail(counsellingDetail);
		
	}
	
	@Test
	public void getCounsellingDtl(){
		
		CounsellingDetail counsellingDetail = counsellingDao.getCounsellingDetail("2015/117");
		System.out.println(counsellingDetail);
	}
	
	@Test
	public void updateCounsellingDtl(){
		
		CounsellingDetail counsellingDetail = counsellingDao.getCounsellingDetail("2015/117");
		counsellingDetail.setRank(1111111L);
		
		counsellingDao.updateCounsellingDetail(counsellingDetail);
		
	}
	
	@Test
	public void deleteCounsellingDtl(){
		
		counsellingDao.deleteConsultantDtl("2015/117");
		
	}
}
