package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.manager.RoomTypeDetailManager;
import com.techvisio.einstitution.manager.impl.RoomTypeDetailManagerImpl;

@Controller
@RequestMapping("/RoomTypeDetail")
public class RoomTypeDetailService {
	private static final Logger logger = Logger.getLogger(RoomTypeDetailService.class);
	
	@RequestMapping(value = "{/typeCode}", method = RequestMethod.GET )
	public RoomTypeDetail getRoomTypeDetail(@PathVariable String typeCode){
		RoomTypeDetail roomTypeDetail = new RoomTypeDetail();
		roomTypeDetail.setTypeCode(typeCode);
		return roomTypeDetail;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		RoomTypeDetailManager roomTypeDetailManager = new RoomTypeDetailManagerImpl();
		roomTypeDetailManager.addRoomTypeDetail(roomTypeDetail);
		
	}
	@RequestMapping(method = RequestMethod.PUT)
	public void updateRoomTypeDetail(@RequestBody RoomTypeDetail roomTypeDetail){
		RoomTypeDetailManager roomTypeDetailManager = new RoomTypeDetailManagerImpl();
		roomTypeDetailManager.updateRoomTypeDetail(roomTypeDetail);
	}
	@RequestMapping(value = "{/typeCode}", method = RequestMethod.DELETE)
	public void deleteRoomTypeDetail(String typeCode){
		RoomTypeDetailManager roomTypeDetailManager= new RoomTypeDetailManagerImpl();
		roomTypeDetailManager.deleteRoomTypeDetail(typeCode);
	}
}
