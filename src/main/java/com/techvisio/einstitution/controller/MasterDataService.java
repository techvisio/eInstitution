package com.techvisio.einstitution.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.FieldDesc;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.util.CommonUtil;

@RestController
@RequestMapping("/masterdata")
public class MasterDataService {
	
@RequestMapping(value = "/admission", method = RequestMethod.GET)	
public ResponseEntity<Map<String,Object>> getMasterDataforAdmission() throws NoSuchFieldException, SecurityException{
	Map<String,Object> admissionMasterData=new HashMap<String, Object>();
	Map<String,Object> serverData=new HashMap<String, Object>();
	List<FieldDesc> personalDetailFields=CommonUtil.createJSONfordynamicUI();
	admissionMasterData.put("personalDetailAttributes", personalDetailFields);
	admissionMasterData.put("dropdownMasterData", serverData);
	List<MasterDataBean> category=new ArrayList<MasterDataBean>();
	category.add(new MasterDataBean("1", "General"));
	category.add(new MasterDataBean("2", "OBC"));
	serverData.put("category", category);
	
	List<MasterDataBean> course=new ArrayList<MasterDataBean>();
	course.add(new MasterDataBean("1", "B.Tech"));
	course.add(new MasterDataBean("2", "MBA"));
	serverData.put("course", course);
	
	List<MasterDataBean> branch=new ArrayList<MasterDataBean>();
	branch.add(new MasterDataBean("1", "CS", "BTech"));
	branch.add(new MasterDataBean("2", "Mechanical", "BTech"));
	serverData.put("branch", branch);
	
	List<MasterDataBean> state=new ArrayList<MasterDataBean>();
	state.add(new MasterDataBean("1", "Uttar Pradesh"));
	state.add(new MasterDataBean("2", "Delhi"));
	serverData.put("state", state);
	
	List<MasterDataBean> qualification=new ArrayList<MasterDataBean>();
	qualification.add(new MasterDataBean("1", "High School"));
	qualification.add(new MasterDataBean("2", "Intermediate"));
	serverData.put("qualification", qualification);
	//CommonUtil.convertJavatoJSON(admissionMasterData);
	return new ResponseEntity<Map<String,Object>>(admissionMasterData,HttpStatus.OK);
	
}
}
