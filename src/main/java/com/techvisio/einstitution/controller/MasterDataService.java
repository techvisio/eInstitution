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

import com.techvisio.einstitution.beans.Amenities;
import com.techvisio.einstitution.beans.FieldDesc;
import com.techvisio.einstitution.beans.MasterData;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.VehicleDetail;
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
public ResponseEntity<Response> getMasterDataforAdmission() {
	logger.info("{} Get MasterData call for admission ",this.getClass().getName());
	Response response=new Response();
	String[] masterEntity=new String[]{
			AppConstants.CATEGORY,
			AppConstants.COURSE,
			AppConstants.BRANCH,
			AppConstants.STATE,
			AppConstants.QUALIFICATION,
			AppConstants.SUBJECT,
			AppConstants.QUOTACODE,
			AppConstants.COUNSELLING,
			AppConstants.CONSULTANT,
			AppConstants.FEEHEAD,
			AppConstants.SEMESTER, 
			AppConstants.BATCH, 
			AppConstants.SESSION, 
			AppConstants.CENTRE, 
			AppConstants.SHIFT, 
			AppConstants.SECTION, 
			AppConstants.WING, 
			AppConstants.FLOOR, 
			AppConstants.BLOCK, 
			AppConstants.ROOMNO, 
			AppConstants.VEHICLE, 
			AppConstants.VEHICLETYPE, 
			AppConstants.AMENITIES,
			AppConstants.ADMISSION_WORKFLOW
			};
	
	try{
	Map<String,List> serverData=new HashMap<String, List>();
	
	for(String entity:masterEntity){
		serverData.put(entity, cacheManager.getEntityList(entity));
	}
	
	response.setResponseBody(serverData);
	}
	catch(Exception e){
		logger.error("Error while fetching master data for admssion", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	
}

@RequestMapping(value = "/enquiry", method = RequestMethod.GET)	
public ResponseEntity<Response> getMasterDataforEnquiry() {
	logger.info("{} Get MasterData call for enquiry ",this.getClass().getName());
	Response response=new Response();
	String[] masterEntity=new String[]{
			AppConstants.CATEGORY,
			AppConstants.COURSE,
			AppConstants.BRANCH,
			AppConstants.STATE,
			AppConstants.COUNSELLING,
			AppConstants.CONSULTANT,
			AppConstants.SEMESTER, 
			};
	
	try{
	Map<String,List> serverData=new HashMap<String, List>();
	
	for(String entity:masterEntity){
		serverData.put(entity, cacheManager.getEntityList(entity));
	}
	
	response.setResponseBody(serverData);
	}
	catch(Exception e){
		logger.error("Error while fetching master data for admssion", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	
}


}
