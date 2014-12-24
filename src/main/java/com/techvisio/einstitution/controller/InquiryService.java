package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AdmissionEnquiry;

@Component
@RestController
@RequestMapping("/inquiry/")
public class InquiryService {
	
	private static final Logger logger = Logger.getLogger(InquiryService.class);
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET,headers="Accept=application/json")
	  public AdmissionEnquiry getInquiry(@PathVariable Long id) {  
	  AdmissionEnquiry admissionEnquiry= new AdmissionEnquiry();
	  admissionEnquiry.setEnquiryId(id);
	  return admissionEnquiry;
	  }

}
