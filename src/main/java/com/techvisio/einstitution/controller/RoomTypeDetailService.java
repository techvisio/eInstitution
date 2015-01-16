package com.techvisio.einstitution.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.manager.RoomTypeDetailManager;
import com.techvisio.einstitution.manager.impl.RoomTypeDetailManagerImpl;


@RestController
@RequestMapping("/hostel")

public class RoomTypeDetailService {
	
		private static final Logger logger = Logger.getLogger(RoomTypeDetailService.class);
		
		@RequestMapping(value="/{typeCode}",method = RequestMethod.GET)
		  public RoomTypeDetail getInquiry(@PathVariable String typeCode) {  
		 
			RoomTypeDetailManager roomTypeDetailManager = new RoomTypeDetailManagerImpl();
			
			RoomTypeDetail roomTypeDetail= roomTypeDetailManager.getRoomTypeDetail(typeCode);
		
			return roomTypeDetail;
		}
		@RequestMapping(method = RequestMethod.POST)
		public void addRoomType(@RequestBody RoomTypeDetail roomTypeDetail) {  
			RoomTypeDetailManager roomTypeDetailManager = new RoomTypeDetailManagerImpl();
			
			roomTypeDetailManager.addRoomTypeDetail(roomTypeDetail);
		}
		
		@RequestMapping(method = RequestMethod.PUT)
		public void updateRoomType(@RequestBody RoomTypeDetail roomTypeDetail) {  
			RoomTypeDetailManager roomTypeDetailManager = new RoomTypeDetailManagerImpl();
			roomTypeDetailManager.updateRoomTypeDetail(roomTypeDetail);
		}
		@RequestMapping(value="/{typeCode}",method = RequestMethod.DELETE)
		public void deleteInquiry(@PathVariable String typeCode) {  
			RoomTypeDetailManager roomTypeDetailManager = new RoomTypeDetailManagerImpl();
			roomTypeDetailManager.deleteRoomTypeDetail(typeCode);
		}

	
}
