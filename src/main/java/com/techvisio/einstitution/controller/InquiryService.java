package com.techvisio.einstitution.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.manager.impl.InquiryManagerImpl;
import com.techvisio.einstitution.util.AppConstants;


@RestController
@RequestMapping("/inquiry")
public class InquiryService {
	
	private static final Logger logger = Logger.getLogger(InquiryService.class);
	
	@RequestMapping(value="/{inquiryId}",method = RequestMethod.GET)
	  public AdmissionInquiry getInquiry(@PathVariable Long inquiryId) {  
	 
		InquiryManager inquiryManager= new InquiryManagerImpl();
		
		AdmissionInquiry admissionInquiry= inquiryManager.getInquiry(inquiryId);
	
		return admissionInquiry;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity addInquiry(@RequestBody AdmissionInquiry admissionInquiry) {  
		Map<String,Object> enquiryData=new HashMap<String, Object>();
		Response response=new Response();
		try{
		InquiryManager inquiryManager=new InquiryManagerImpl();
		Long enquiryId=inquiryManager.addInquiry(admissionInquiry);
		AdmissionInquiry admissionInquiryDB=inquiryManager.getInquiry(enquiryId);
		if(admissionInquiryDB != null){
			enquiryData.put(AppConstants.ENQUIRY, admissionInquiryDB);
		}
		response.setResponseBody(enquiryData);
		}
		catch(Exception e){
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity(response,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateInquiry(@RequestBody AdmissionInquiry admissionInquiry) {  
		InquiryManager inquiryManager=new InquiryManagerImpl();
		inquiryManager.updateInquiry(admissionInquiry);
	}
	@RequestMapping(value="/{inquiryId}",method = RequestMethod.DELETE)
	public void deleteInquiry(@PathVariable Long inquiryId) {  
		InquiryManager inquiryManager=new InquiryManagerImpl();
		inquiryManager.deleteInquiry(inquiryId);
	}

}
