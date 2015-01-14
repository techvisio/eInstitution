package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.manager.HostelAllocationManger;
import com.techvisio.einstitution.manager.HostelReservationManger;
import com.techvisio.einstitution.manager.impl.HostelReservationMangerImpl;
@Controller
@RequestMapping("/HostelReservation")

public class HostelReservationService {
	private static final Logger logger = Logger.getLogger(HostelReservationService.class);
	
	@RequestMapping(value ="{/fileNo}", method = RequestMethod.GET)
	public HostelReservation getHostelReservation(@PathVariable String fileNo){
		HostelReservation hostelReservation = new HostelReservation();
		hostelReservation.setFileNo(fileNo);
		return hostelReservation;
		
		}

	@RequestMapping(method = RequestMethod.POST)
	public void addHostelReservation(@RequestBody HostelReservation hostelReservation){
		HostelReservationManger hostelReservationManger = new HostelReservationMangerImpl();
		hostelReservationManger.addHostelReservation(hostelReservation);
	}
	@RequestMapping(method = RequestMethod.PUT)
	public void updateHostelReservation(@RequestBody HostelReservation hostelReservation){
		HostelReservationManger hostelReservationManger = new HostelReservationMangerImpl();
		hostelReservationManger.updateHostelReservation(hostelReservation);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteHostelReservation(@PathVariable String fileNo){
		HostelReservationManger hostelReservationManger = new HostelReservationMangerImpl();
		hostelReservationManger.deleteHostelReservation(fileNo);
	}

}
