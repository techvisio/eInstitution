package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.manager.HostelManager;
import com.techvisio.einstitution.manager.impl.HostelManagerImpl;

@RestController
@RequestMapping("/Hostel")

public class HostelService {
	private static final Logger logger = Logger.getLogger(HostelService.class);
	
//RoomTypeDetail 
	
	@RequestMapping(value ="/RoomTypeDetail/{typeCode}", method = RequestMethod.GET )
	public RoomTypeDetail getRoomTypeDetail(@PathVariable String typeCode){
		HostelManager hostelManager = new HostelManagerImpl();
		RoomTypeDetail roomTypeDetail = hostelManager.getRoomTypeDetail(typeCode);
		return roomTypeDetail;
		
	}
	
	@RequestMapping(value = "/RoomTypeDetail",method =RequestMethod.POST)
	public void addRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.addRoomTypeDetail(roomTypeDetail);
		
	}
	@RequestMapping(value = "/RoomTypeDetail",method =RequestMethod.PUT)
	public void updateRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.updateRoomTypeDetail(roomTypeDetail);
	}
	@RequestMapping(value ="/RoomTypeDetail/{typeCode}", method =RequestMethod.DELETE)
	public void deleteRoomTypeDetail(@PathVariable String typeCode){
		HostelManager hostelManager= new HostelManagerImpl();
		hostelManager.deleteRoomTypeDetail(typeCode);
	}

//HostelAllocation
	
	@RequestMapping(value="/HostelAllocation/{fileNo}",method = RequestMethod.GET )
	public HostelAllocation getHostelAllocation(@PathVariable String fileNo){
		HostelManager hostelManager = new HostelManagerImpl(); 
		HostelAllocation hostelAllocation = hostelManager.getHostelAllocation(fileNo);
		
		
		return hostelAllocation;
		
	}
	
	@RequestMapping(value="/HostelAllocation",method = RequestMethod.POST)
	public void addHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.addHostelAllocation(hostelAllocation);
		
	}
	
	@RequestMapping(value="/HostelAllocation",method = RequestMethod.PUT)
	public void updateHostelAllocation(@RequestBody HostelAllocation hostelAllocation){
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.addHostelAllocation(hostelAllocation);
	}
	
	@RequestMapping(value="/HostelAllocation",method = RequestMethod.DELETE)
	public void deleteHostelAllocation(@PathVariable String fileNo){
		
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.deleteHostelAllocation(fileNo);
		
	}	

	
//HostelReservation
	
	
	@RequestMapping(value ="/HostelReservation/{fileNo}", method = RequestMethod.GET)
	public HostelReservation getHostelReservation(@PathVariable String fileNo){
		HostelManager hostelManager = new HostelManagerImpl();
		HostelReservation hostelReservation = hostelManager.getHostelReservation(fileNo);
		
		return hostelReservation;
		
		}

	@RequestMapping(value ="/HostelReservation",method = RequestMethod.POST)
	public void addHostelReservation(@RequestBody HostelReservation hostelReservation){
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.addHostelReservation(hostelReservation);
	}
	@RequestMapping(value ="/HostelReservation",method = RequestMethod.PUT)
	public void updateHostelReservation(@RequestBody HostelReservation hostelReservation){
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.updateHostelReservation(hostelReservation);
		
	}
	
	@RequestMapping(value ="/HostelReservation",method = RequestMethod.DELETE)
	public void deleteHostelReservation(@PathVariable String fileNo){
		HostelManager hostelManager = new HostelManagerImpl();
		hostelManager.deleteHostelReservation(fileNo);
	}
	
	
}
