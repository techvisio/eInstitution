package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AdmissionInquiry;
import com.techvisio.einstitution.manager.InquiryManager;
import com.techvisio.einstitution.manager.impl.InquiryManagerImpl;


@RestController
@RequestMapping("/inquiry")
public class InquiryService {
	
	private static final Logger logger = Logger.getLogger(InquiryService.class);
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	  public AdmissionInquiry getInquiry(@PathVariable Long id) {  
	  AdmissionInquiry admissionEnquiry= new AdmissionInquiry();
	  admissionEnquiry.setEnquiryId(id);
	  return admissionEnquiry;
	  }
	@RequestMapping(method = RequestMethod.POST)
	public void addInquiry(@RequestBody AdmissionInquiry admissionInquiry) {  
		InquiryManager inquiryManager=new InquiryManagerImpl();
		inquiryManager.addInquiryDetail(admissionInquiry);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateInquiry(@RequestBody AdmissionInquiry admissionInquiry) {  
		InquiryManager inquiryManager=new InquiryManagerImpl();
		inquiryManager.updateInquiryDetail(admissionInquiry);
	}
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteInquiry(@RequestBody Long inquiryId) {  
		InquiryManager inquiryManager=new InquiryManagerImpl();
		inquiryManager.deleteInquiryDetail(inquiryId);
	}

}
