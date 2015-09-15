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

import com.techvisio.einstitution.beans.HostelAllocationAdmission;
import com.techvisio.einstitution.beans.HostelAvailability;
import com.techvisio.einstitution.beans.HostelReservation;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.RoomAllocation;
import com.techvisio.einstitution.beans.RoomAllocationDetailForRoom;
import com.techvisio.einstitution.beans.RoomAllocationForStudent;
import com.techvisio.einstitution.beans.RoomTypeDetail;
import com.techvisio.einstitution.beans.SearchCriteria;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.HostelWorkflowManager;

@RestController
@RequestMapping("/service/hostel")

public class HostelService {
	private static CustomLogger logger = CustomLogger.getLogger(HostelService.class);

	@Autowired
	HostelWorkflowManager hostelWorkflowManager;

	@RequestMapping(value ="/hostelAvailability", method = RequestMethod.GET)
	public List<HostelAvailability> getHostelAvailability(){
		logger.info("{}: Calling getHostelAvailability method for : fileno : {}",this.getClass().getName());
		List<HostelAvailability> hostelAvailability = hostelWorkflowManager.getHostelAvailability();
		return hostelAvailability;
	}

	@RequestMapping(value ="/hostelReservation/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getHostelReservation(@PathVariable Long fileNo){
		logger.info("{}:  Calling getHostelReservation method by passing fileno : {}",this.getClass().getName(), fileNo);	
		Response response=new Response();

		HostelReservation hostelReservation = hostelWorkflowManager.getHostelReservation(fileNo);
		response.setResponseBody(hostelReservation);

		if(hostelReservation==null){
			response.setError("No record available");
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}

	@RequestMapping(value ="/hostelReservation/{fileNo}",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelReservation(@RequestBody HostelReservation hostelReservation, @PathVariable Long fileNo){
		logger.info("{}:  Calling addHostelReservation method for  fileno : {}",this.getClass().getName(), hostelReservation.getFileNo());
		Response response = new Response();
		hostelWorkflowManager.saveHostelReservation(hostelReservation, fileNo);
		HostelReservation updatedReservation=hostelWorkflowManager.getHostelReservation(fileNo);
		response.setResponseBody(updatedReservation);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/hostelReservation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteHostelReservation(@PathVariable Long fileNo){
		logger.info("{}:  Calling deleteHostelReservation method by passing fileno : {}",this.getClass().getName(), fileNo);
		hostelWorkflowManager.deleteHostelReservation(fileNo);

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value ="/searchStudent/", method = RequestMethod.POST)
	public ResponseEntity<Response> getStudentDtlByCriteria(@RequestBody SearchCriteria searchCriteria) {
		logger.info("{}  Calling getStudentDtlBySearchCriteria method for name:{}",this.getClass().getName(), searchCriteria.getFirstName());
		Response response=new Response();
		List<StudentBasicInfo> studentBasicInfo = hostelWorkflowManager.getStudentDtlBySearchCriteria(searchCriteria);
		response.setResponseBody(studentBasicInfo);

		if(studentBasicInfo == null){

			response.setError("No such record found");
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/basicInfo/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentBasicInfo(@PathVariable Long fileNo){
		Response response=new Response();
		StudentBasicInfo basicInfo = hostelWorkflowManager.getStudentBsInfo(fileNo);
		response.setResponseBody(basicInfo);

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}


	@RequestMapping(value ="/roomAllocation/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getRoomAllocation(@PathVariable Long fileNo){
		Response response=new Response();
		RoomAllocation roomAllocation = hostelWorkflowManager.getRoomAllocation(fileNo);
		response.setResponseBody(roomAllocation);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/roomAllocation/{fileNo}",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelAllocation(@RequestBody RoomAllocation roomAllocation, @PathVariable Long fileNo){
		Response response = new Response();

		hostelWorkflowManager.saveRoomAllocation(roomAllocation, fileNo);
		RoomAllocation updatedAllocation=hostelWorkflowManager.getRoomAllocation(fileNo);
		response.setResponseBody(updatedAllocation);

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/roomAllocation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteRoomAllocation(@PathVariable Long fileNo){
		hostelWorkflowManager.deleteRoomAllocation(fileNo);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value ="/availableRooms/", method = RequestMethod.GET)
	public ResponseEntity<Response> getAvailableRooms(){
		Response response=new Response();
		List<RoomTypeDetail> roomTypeDetails = hostelWorkflowManager.getAvailableRooms();
		response.setResponseBody(roomTypeDetails);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

}
