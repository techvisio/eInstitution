package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.AvailableTransport;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.TransportAllocation;
import com.techvisio.einstitution.beans.TransportAllocationAdmission;
import com.techvisio.einstitution.beans.TransportAllocationDtlForVehicle;
import com.techvisio.einstitution.beans.TransportAllocationForStudent;
import com.techvisio.einstitution.beans.TransportReservation;
import com.techvisio.einstitution.beans.VehicleDetail;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.TransportWorkflowManager;
import com.techvisio.einstitution.workflow.impl.TransportWorkflowManagerImpl;

@RestController
@RequestMapping("/service/transport")

public class TransportService {

	private static CustomLogger logger = CustomLogger.getLogger(TransportService.class);

	@Autowired
	TransportWorkflowManager transportWorkflowManager;

	@RequestMapping(value="/availableTransport",method = RequestMethod.GET)
	public List<AvailableTransport> getTransportAllocation() {
		logger.info("{}:  Calling getAvailableTransport method",this.getClass().getName());
		List<AvailableTransport> availableTransports=transportWorkflowManager.getAvailableTransport();
		return availableTransports;  
	}
	@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.GET)
	public ResponseEntity<Response> getTransportReservation(@PathVariable Long fileNo) {  
		logger.info("{}  Calling getTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
		Response response=new Response();
		TransportReservation transportReservation=transportWorkflowManager.getTransportReservationDtl(fileNo);
		response.setResponseBody(transportReservation);
		if(transportReservation==null){
			response.setError("No record available");
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.POST)
	public ResponseEntity<Response> addTransporReservation(@RequestBody TransportReservation transportReservation, @PathVariable Long fileNo) {  
		logger.info("{} : Calling addTransportReservationDtl method for fileNo :{}",this.getClass().getName(), transportReservation.getFileNo());
		Response response = new Response();
		transportWorkflowManager.saveTransportReservationDtl(transportReservation, fileNo);
		TransportReservation updatedReservation=transportWorkflowManager.getTransportReservationDtl(transportReservation.getFileNo());
		response.setResponseBody(updatedReservation);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/reservation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteTransportReservation(@PathVariable Long fileNo ) {  
		logger.info("{}:  Calling deleteTransportReservationDtl method by passing fileNo :{}",this.getClass().getName(), fileNo);
		transportWorkflowManager.deleteTransportReservationDtl(fileNo);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value ="/searchStudent/", method = RequestMethod.POST)
	public ResponseEntity<Response> getStudentDtlByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}  Calling getStudentDtlBySearchCriteria method for name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		Response response=new Response();
		List<StudentBasicInfo> studentBasicInfo = transportWorkflowManager.getStudentDtlBySearchCriteria(searchCriteria);
		response.setResponseBody(studentBasicInfo);

		if(studentBasicInfo == null){

			response.setError("No such record found");
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/basicInfo/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentBasicInfo(@PathVariable Long fileNo){
		Response response=new Response();
		StudentBasicInfo basicInfo = transportWorkflowManager.getStudentBsInfo(fileNo);
		response.setResponseBody(basicInfo);

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}


	@RequestMapping(value ="/transportAllocation/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getTransportAllocation(@PathVariable Long fileNo){
		Response response=new Response();
		TransportAllocation transportAllocation = transportWorkflowManager.getTransportAllocation(fileNo);
		response.setResponseBody(transportAllocation);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/transportAllocation/{fileNo}",method = RequestMethod.POST)
	public ResponseEntity<Response> addTransportAllocation(@RequestBody TransportAllocation transportAllocation, @PathVariable Long fileNo){
		Response response = new Response();
		transportWorkflowManager.saveTransportAllocationDtl(transportAllocation, fileNo);
		TransportAllocation updatedAllocation=transportWorkflowManager.getTransportAllocation(fileNo);
		response.setResponseBody(updatedAllocation);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/transportAllocation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteTransportAllocation(@PathVariable Long fileNo){
		transportWorkflowManager.deleteTransportAllocationDtl(fileNo);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value ="/availableVehicles/", method = RequestMethod.GET)
	public ResponseEntity<Response> getAvailableVehicles(){
		Response response=new Response();
		List<VehicleDetail> vehicleDetails = transportWorkflowManager.getAvailableVehicles();
		response.setResponseBody(vehicleDetails);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

}
