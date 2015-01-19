package com.techvisio.einstitution.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.HostelAllocation;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelInventory;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.RoomTypeDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config/Application-context.xml"})

public class HostelDaoTest {
  
	@Autowired
	HostelDao dao;

 
	@Test
	public void testAddHostelAllocation(){
		HostelAllocation hostel = new HostelAllocation();	
		hostel.setBlock("A");
		hostel.setFileNo("11");
		hostel.setFloor("2");
		hostel.setName("nikhil");
		hostel.setRoomNo("2");
		hostel.setWing("Q");
		dao.addHostelAllocation(hostel);

	}

	@Test
	public void testGetHostelAllocation(){
		HostelAllocation hAllocation = dao.getHostelAllocation("11");
		System.out.println(hAllocation);
	}
	
	@Test
	public void testGetHostelAvailability(){
		List<HostelAvailability> hostelAvailability = dao.getHostelAvailability();
		System.out.println("hostel availability");
	System.out.println(hostelAvailability);
	}
	
	
	@Test
	public void testAddHostelInventory(){
		HostelInventory hostel = new HostelInventory();
		hostel.setDescription("mast");
		hostel.setPrice(1200.00);
		hostel.setRoomCapacity(1);
		hostel.setThreshold(1);
		hostel.setTypeCode("1");
		
		dao.addHostelInventory(hostel);
	}

	@Test
	public void testGetHostelInventory(){
		HostelInventory hostelInventory=dao.getHostelInventory("1");

		System.out.println(hostelInventory);
	}
	
	
	@Test
	public void testAddHostelReservation(){
		HostelReservation hostel = new HostelReservation();
		hostel.setFeePaid(true);
		hostel.setFileNo("11");
		hostel.setTypeCode("1");
		
		dao.addHostelReservation(hostel);
	}

	@Test
	public void testGetHostelReservation(){
		HostelReservation hostelReservation=dao.getHostelReservation("11");

		System.out.println(hostelReservation);
	}

	
	
	
	@Test
	public void testAddRoomTypeDetail(){
		RoomTypeDetail r = new RoomTypeDetail();
		r.setRoomNo("2");
		r.setTypeCode("1");
		dao.addRoomTypeDetail(r);
	}
	
	
	

	@Test
	public void testGetRoomTypeDetail(){
		RoomTypeDetail r=dao.getRoomTypeDetail("1");

		System.out.println(r);
	}

	
	@Test
	public void testUpdateHostelInventory(){
		HostelInventory hostel = new HostelInventory();
		hostel.setDescription("jhakazz");
		hostel.setPrice(12.5);
		hostel.setRoomCapacity(2);
		hostel.setThreshold(2);
		hostel.setTypeCode("1");
		
		dao.updateHostelInventory(hostel);
		
	}

	@Test
	public void testUpdateHostelAllocation(){
		HostelAllocation hostel2 = new HostelAllocation();
		hostel2.setBlock("B");
		hostel2.setFileNo("11");
		hostel2.setFloor("3");
		hostel2.setName("Nikhil Sharma");
		hostel2.setRoomNo("2AA");
		hostel2.setWing("QA");
		
		dao.updateHostelAllocation(hostel2);
		
	}
	
	@Test
	
	public void testUpdateHostelReservation(){
		HostelReservation hostel = new HostelReservation();
		hostel.setFeePaid(false);
		hostel.setFileNo("11");
		hostel.setTypeCode("1");
		
		dao.updateHostelReservation(hostel);
	}
	
	@Test
	
	public void testUpdateRoomTypeDetail(){
		RoomTypeDetail r = new RoomTypeDetail();
		r.setRoomNo("2AA");
		r.setTypeCode("1");
		
		dao.updateRoomTypeDetail(r);
	}
	
	
	@Test
	public void testDeleteHostelInventory(){
	dao.deleteHostelInventory("1");
	}
	
	@Test
	
	public void testDeleteHotelReservation(){
		dao.deleteHostelReservation("11");
	}
	
	@Test
	
	public void testDeleteRoomTypeDetail(){
		dao.deleteRoomTypeDetail("2");
		
	}
	
	@Test
	
	public void testDeleteHostelAllocation(){
		dao.deleteHostelAllocation("11");
	}
	
}
