package com.techvisio.einstitution.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.FieldDesc;
import com.techvisio.einstitution.beans.MasterDataBean;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.CustomLogger;

@Component 
@RestController
@RequestMapping("/masterdata")
public class MasterDataService {
	private static CustomLogger logger = CustomLogger.getLogger(MasterDataService.class);
@Autowired
CacheManager cacheManager;
	
//	@Autowired
public void setCacheManager(CacheManager cacheManager) {
	this.cacheManager = cacheManager;
}


@RequestMapping(value = "/admission", method = RequestMethod.GET)	
public ResponseEntity<Map<String,Object>> getMasterDataforAdmission() throws NoSuchFieldException, SecurityException{
	logger.info("{}  ResponseEntity method",this.getClass().getName());
	//cacheManager=ContextProvider.getContext().getBean(CacheFactoryImpl.class);
	Map<String,Object> admissionMasterData=new HashMap<String, Object>();
	Map<String,Object> serverData=new HashMap<String, Object>();
	List<FieldDesc> personalDetailFieldsSingle=CommonUtil.createJSONfordynamicUI();
	List<List<FieldDesc>> personalDetailFields=new ArrayList<List<FieldDesc>>();
	int i=0;
	List<FieldDesc> innerList=null;
	for(FieldDesc desc:personalDetailFieldsSingle){
		if(i%2==0){
		innerList=new ArrayList<FieldDesc>();
		innerList.add(desc);
		}
		else
		{
			innerList.add(desc);	
			personalDetailFields.add(innerList);
		}
		
		if(i==personalDetailFieldsSingle.size()-1 && i%2==0){
			personalDetailFields.add(innerList);
		}
		
		i++;
	}
	admissionMasterData.put("personalDetailAttributes", personalDetailFields);
	admissionMasterData.put("dropdownMasterData", serverData);

	
	List<MasterDataBean> category=cacheManager.getCategoryAsMasterdata();
//	category.add(new MasterDataBean("1", "General"));
//	category.add(new MasterDataBean("2", "OBC"));
	serverData.put(AppConstants.CATEGORY, category);
	
	List<MasterDataBean> course=cacheManager.getCourseAsMasterdata();
//	course.add(new MasterDataBean("1", "B.Tech"));
//	course.add(new MasterDataBean("2", "MBA"));
	serverData.put(AppConstants.COURSE, course);
	
	List<MasterDataBean> branch=cacheManager.getBranchAsMasterdata();
//	branch.add(new MasterDataBean("1", "CS", "BTech"));
//	branch.add(new MasterDataBean("2", "Mechanical", "BTech"));
	serverData.put(AppConstants.BRANCH, branch);
	
	List<MasterDataBean> state=cacheManager.getStateAsMasterdata();
//	state.add(new MasterDataBean("1", "Uttar Pradesh"));
//	state.add(new MasterDataBean("2", "Delhi"));
	serverData.put(AppConstants.STATE, state);
	
	List<MasterDataBean> qualification=cacheManager.getQualificationAsMasterdata();
//	qualification.add(new MasterDataBean("1", "High School"));
//	qualification.add(new MasterDataBean("2", "Intermediate"));
	serverData.put(AppConstants.QUALIFICATION, qualification);
	//CommonUtil.convertJavatoJSON(admissionMasterData);
	
	List<MasterDataBean> subject=cacheManager.getSubjectAsMasterdata();
	serverData.put(AppConstants.SUBJECT, subject);
	
	List<MasterDataBean> quotaCode=cacheManager.getQuotaCodeAsMasterdata();
	serverData.put(AppConstants.QUOTACODE, quotaCode);
	
	List<MasterDataBean> counselling=cacheManager.getCounsellingBodyAsMasterdata();
	serverData.put(AppConstants.COUNSELLING, counselling);
	
	List<MasterDataBean> consultant=cacheManager.getConsultantAsMasterdata();
	serverData.put(AppConstants.CONSULTANT, consultant);
	
	List<MasterDataBean> feeDiscount=cacheManager.getFeeDiscountAsMasterdata();
	serverData.put(AppConstants.FEEHEAD, feeDiscount);
	
	
	List<MasterDataBean> semester=cacheManager.getSemesterAsMasterdata();
	serverData.put(AppConstants.SEMESTER, semester);
	
	List<MasterDataBean> batch = cacheManager.getBatchAsMasterdata();
	serverData.put(AppConstants.BATCH, batch);
	
	List<MasterDataBean> session = cacheManager.getSessionAsMasterdata();
	serverData.put(AppConstants.SESSION, session);
	
	List<MasterDataBean> centre = cacheManager.getCentreAsMasterdata();
	serverData.put(AppConstants.CENTRE, centre);
	
	List<MasterDataBean> shift = cacheManager.getShiftAsMasterdata();
	serverData.put(AppConstants.SHIFT, shift);
	
	List<MasterDataBean> section = cacheManager.getSectionAsMasterdata();
	serverData.put(AppConstants.SECTION, section);
	
	List<MasterDataBean> wing = cacheManager.getWingAsMasterdata();
	serverData.put(AppConstants.WING, wing);
	
	List<MasterDataBean> floor = cacheManager.getFloorAsMasterdata();
	serverData.put(AppConstants.FLOOR, floor);
	
	List<MasterDataBean> block = cacheManager.getBlockAsMasterdata();
	serverData.put(AppConstants.BLOCK, block);
	
	List<RoomTypeDetail> rooms = cacheManager.getRoomNoAsMasterdata();
	serverData.put(AppConstants.ROOMNO, rooms);
	
	List<MasterDataBean> vehicle = cacheManager.getVehicleIdAsMastersata();
	serverData.put(AppConstants.VEHICLE, vehicle);
	
	return new ResponseEntity<Map<String,Object>>(admissionMasterData,HttpStatus.OK);
	
}

 

}
