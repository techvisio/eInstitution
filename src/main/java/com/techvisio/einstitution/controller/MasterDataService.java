package com.techvisio.einstitution.controller;

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

import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CustomLogger;

@Component 
@RestController
@RequestMapping("/service/masterdata")
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
			AppConstants.ROUTE, 
			AppConstants.STOPPPAGE, 
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
		logger.error("Error while fetching master data for enquiry", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	
}

@RequestMapping(value = "/consultant", method = RequestMethod.GET)	
public ResponseEntity<Response> getMasterDataforConsultant() {
	logger.info("{} Get MasterData call for consultant ",this.getClass().getName());
	Response response=new Response();
	String[] masterEntity=new String[]{
			AppConstants.CONSULTANT,
			};
	
	try{
	Map<String,List> serverData=new HashMap<String, List>();
	
	for(String entity:masterEntity){
		serverData.put(entity, cacheManager.getEntityList(entity));
	}
	
	response.setResponseBody(serverData);
	}
	catch(Exception e){
		logger.error("Error while fetching master data for consultant", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	
}

@RequestMapping(value = "/fee", method = RequestMethod.GET)
public ResponseEntity<Response> getMasterDataforFee() {
	logger.info("{} Get MasterData call for Fee ",this.getClass().getName());
	Response response=new Response();
	String[] masterEntity=new String[]{
			AppConstants.COURSE,
			AppConstants.BRANCH
			};
	
	try{
	Map<String,List> serverData=new HashMap<String, List>();
	
	for(String entity:masterEntity){
		serverData.put(entity, cacheManager.getEntityList(entity));
	}
	
	response.setResponseBody(serverData);
	}
	catch(Exception e){
		logger.error("Error while fetching master data for consultant", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	
}

@RequestMapping(value = "/hostel", method = RequestMethod.GET)	
public ResponseEntity<Response> getMasterDataforHostel() {
	logger.info("{} Get MasterData call for hostel ",this.getClass().getName());
	Response response=new Response();
	String[] masterEntity=new String[]{
			AppConstants.WING, 
			AppConstants.FLOOR, 
			AppConstants.BLOCK, 
			AppConstants.ROOMNO, 
			};
	
	try{
	Map<String,List> serverData=new HashMap<String, List>();
	
	for(String entity:masterEntity){
		serverData.put(entity, cacheManager.getEntityList(entity));
	}
	
	response.setResponseBody(serverData);
	}
	catch(Exception e){
		logger.error("Error while fetching master data for hostel", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
}

@RequestMapping(value = "/transport", method = RequestMethod.GET)	
public ResponseEntity<Response> getMasterDataforTransport() {
	logger.info("{} Get MasterData call for transport ",this.getClass().getName());
	Response response=new Response();
	String[] masterEntity=new String[]{
			AppConstants.ROUTE, 
			AppConstants.STOPPPAGE, 
			AppConstants.VEHICLETYPE,
			AppConstants.VEHICLE
			};
	
	try{
	Map<String,List> serverData=new HashMap<String, List>();
	
	for(String entity:masterEntity){
		serverData.put(entity, cacheManager.getEntityList(entity));
	}
	
	response.setResponseBody(serverData);
	}
	catch(Exception e){
		logger.error("Error while fetching master data for transport", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
}

@RequestMapping(value = "/user", method = RequestMethod.GET)	
public ResponseEntity<Response> getMasterDataforUser() {
	logger.info("{} Get MasterData call for user ",this.getClass().getName());
	Response response=new Response();
	String[] masterEntity=new String[]{
			AppConstants.QUESTION
			};
	
	try{
	Map<String,List> serverData=new HashMap<String, List>();
	
	for(String entity:masterEntity){
		serverData.put(entity, cacheManager.getEntityList(entity));
	}
	
	response.setResponseBody(serverData);
	}
	catch(Exception e){
		logger.error("Error while fetching master data for user", e);
		response.setError(e.getMessage());
	}
	return new ResponseEntity<Response>(response,HttpStatus.OK);
}

}
