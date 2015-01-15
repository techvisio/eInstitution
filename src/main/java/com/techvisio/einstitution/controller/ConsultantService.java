package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.ConsultantDetail;
import com.techvisio.einstitution.manager.ConsultantManager;
import com.techvisio.einstitution.manager.impl.ConsultantManagerImpl;


@RestController
@RequestMapping("/consultant")
public class ConsultantService {

private static final Logger logger = Logger.getLogger(ConsultantService.class);
	
	@RequestMapping(value="/{fileNo}",method = RequestMethod.GET)
	  public ConsultantDetail getConsultantDetail(@PathVariable String fileNo) {  
	  
		ConsultantManager consultantManager =  new ConsultantManagerImpl();

		ConsultantDetail consultantDetail = consultantManager.getConsultantDtl(fileNo);
	 
		return consultantDetail;
	}
	@RequestMapping(method = RequestMethod.POST)
	public void addConsultantDtl(@RequestBody ConsultantDetail consultantDetail) {  
ConsultantManager consultantManager=new ConsultantManagerImpl();
		consultantManager.addConsultantDtl(consultantDetail);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateConsultantDtl(@RequestBody ConsultantDetail consultantDetail) {  
		ConsultantManager consultantManager= new ConsultantManagerImpl();
		consultantManager.updateConsultantDtl(consultantDetail);
	}
	@RequestMapping(value="/{fileNo}",method = RequestMethod.DELETE)
	public void deleteConsultantDtl(@PathVariable String fileNo) {  
		ConsultantManager consultantManager=new ConsultantManagerImpl();
		consultantManager.deleteConsultantDtl(fileNo);
	}

	
}
