package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.beans.Transport;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })
public class TransportDaoTest {

	@Autowired
	TransportDao dao;

	@Test
	public void testAddTransport() {

		Transport transport = new Transport();

		transport.setRouteCode("indira007");
		transport.setThreshold("30");
		transport.setDescription("it will cover indirapuram's area");
		transport.setPrice(1000D);

		dao.addTransport(transport);

	}

	@Test
	public void testgetTransport() {

		Transport transport = dao.getTransport("indira007");
		System.out.println(transport);

	}

	@Test
	public void testUpdateTransport() {

		Transport transport = dao.getTransport("indira007");
		transport.setPrice(500D);

		dao.updateTransport(transport);

	}

	@Test
	public void testAddVehicleDtl() {

		VehicleDetail vehicleDetail1 = new VehicleDetail();

		vehicleDetail1.setCapacity("50");
		vehicleDetail1.setRouteCode("indira007");
		vehicleDetail1.setType("van");
		vehicleDetail1.setVehicleId(189L);
		vehicleDetail1.setVehicleNo("UP 14 MH 7890)");
		dao.addVehicleDetail(vehicleDetail1);

	}

	@Test
	public void testGetVehicleDtl() {

		VehicleDetail vehicleDetail = dao.getVehicleDetail(199L);

		System.out.println(vehicleDetail);
	}

	@Test
	public void testupdateVehicleDtl() {

		VehicleDetail vehicleDetail = dao.getVehicleDetail(199L);

		vehicleDetail.setVehicleNo("UP 14 MH 0767)");
		dao.updateVehicleDetail(vehicleDetail);

	}

	@Test
	public void testDeleteVehicleDtl() {

		dao.deleteVehicleDetail(189L);
	}

	@Test
	public void testAddTransportAllocation() {

		TransportAllocation transportAllocation = new TransportAllocation();

		transportAllocation.setFileNo(1L);
		transportAllocation.setVehicleId(189L);

		dao.addTransportAllocationDtl(transportAllocation);

	}

	@Test
	public void testGetTransportAllocation() {

		TransportAllocation transportAllocation = dao
				.getTransportAllocationDtl(11L);
		System.out.println(transportAllocation);

	}

	@Test
	public void testUpdateTransportAllocation() {

		TransportAllocation transportAllocation = dao
				.getTransportAllocationDtl(11L);
		transportAllocation.setVehicleId(189L);
		dao.updateTransportAllocationDtl(transportAllocation);
	}

	@Test
	public void testDeleteTransportAllocation() {

		dao.deleteTransportAllocationDtl(1L);

	}

	@Test
	public void testAddTransportReservation() {

		TransportReservation transportReservation = new TransportReservation();

		transportReservation.setFileNo(11L);
		transportReservation.setFeePaid(true);
		transportReservation.setRouteCode("indira007");
		dao.addTransportReservationDtl(transportReservation);

	}

	@Test
	public void testGetTransportReservation() {

		TransportReservation transportReservation = dao
				.getTransportReservationDtl(11L);
		System.out.println(transportReservation);

	}

	@Test
	public void testUpdateTransportReservation() {

		TransportReservation transportReservation = dao
				.getTransportReservationDtl(11L);

		transportReservation.setRouteCode("vkNgr12");
		dao.updateTransportReservationDtl(transportReservation);

	}

	@Test
	public void testDeleteTransportReservation() {

		dao.deleteTransportReservationDtl(11L);

	}

}