package com.techvisio.einstitution.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.FieldDesc;
import com.techvisio.einstitution.beans.WorkFlowFieldMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})
public class WorkFlowDaoTest {
	@Autowired
	WorkFlowDao dao;
	
	
	@Test
	
	public void testAddWorkFlowFieldMapping(){
		FieldDesc desc = new FieldDesc();
		String[] t = {"nikhil,Sharma"};
		desc.setId("tata");
		desc.setMandatoryInd(false);
		desc.setMasterDataCode("kaka");
		desc.setTitle("jai ho");
		desc.setType("JAI HO");
		desc.setValidValue(t);
		desc.setVisible(true);
		
		
		List<FieldDesc> descs=new ArrayList<FieldDesc>();
		descs.add(desc);
		
		WorkFlowFieldMapping fieldMapping = new WorkFlowFieldMapping();
		fieldMapping.setWorkFlowStepId("FLOW-STEP-ID");
		fieldMapping.setWorkFlowId("FLOW-ID");
		fieldMapping.setFieldDesc(descs);
		
		
		dao.addWorkFlowFieldMapping(fieldMapping);
		
		
	}
	
	@Test
	public void testGetWorkFlowFieldMappingByWorkFlowId(){
		WorkFlowFieldMapping work = dao.getWorkFlowFieldMappingByWorkFlowId("FLOW-ID");
		System.out.println("DATA IS :-"+work);
	}
	
	
	@Test
	public void testGetWorkFlowFieldMappingByWorkFlowStepId(){
		WorkFlowFieldMapping work = dao.getWorkFlowFieldMappingByWorkFlowStepId("FLOW-STEP-ID");
		System.out.println("DATA  :-"+work);
	}
	
	
	
	
	
	
	@Test
	
	public void testUpdateWorkFlowFieldMapping(){
		FieldDesc desc = new FieldDesc();
		String[] t = {"Sandeep,pradeep"};
		desc.setId("tata");
		desc.setMandatoryInd(true);
		desc.setMasterDataCode("local");
		desc.setTitle("jai ho");
		desc.setType("JAI HO");
		desc.setValidValue(t);
		desc.setVisible(false);
		
		
		List<FieldDesc> descs=new ArrayList<FieldDesc>();
		descs.add(desc);
		
		WorkFlowFieldMapping fieldMapping = new WorkFlowFieldMapping();
		fieldMapping.setWorkFlowStepId("FLOW-STEP-ID");
		fieldMapping.setWorkFlowId("FLOW-ID");
		fieldMapping.setFieldDesc(descs);
		
		dao.updateWorkFlowFieldMapping(fieldMapping);
		
		
	}
	
	
@Test

public void testDeleteWorkFlowFieldMapping(){
	dao.deleteWorkFlowFieldMapping("FLOW-STEP-ID");
}


	

}
