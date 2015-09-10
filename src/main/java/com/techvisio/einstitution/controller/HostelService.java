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
		try
		{

			HostelReservation hostelReservation = hostelWorkflowManager.getHostelReservation(fileNo);
			response.setResponseBody(hostelReservation);

			if(hostelReservation==null){
				response.setError("No record available");
			}
		}
		catch(EmptyResultDataAccessException e)
		{
			response.setError("No such record found");
		}
		catch(Exception e)
		{
			response.setError(e.getMessage());
			logger.error("{}: Error While Calling getHostelReservation method by passing fileno :{}",this.getClass().getName(),fileNo,e);
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}

	@RequestMapping(value ="/hostelReservation/{fileNo}",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelReservation(@RequestBody HostelReservation hostelReservation, @PathVariable Long fileNo){
		logger.info("{}:  Calling addHostelReservation method for  fileno : {}",this.getClass().getName(), hostelReservation.getFileNo());
		Response response = new Response();
		try
		{
			hostelWorkflowManager.saveHostelReservation(hostelReservation, fileNo);
			HostelReservation updatedReservation=hostelWorkflowManager.getHostelReservation(fileNo);
			response.setResponseBody(updatedReservation);
		}
		catch(Exception e)
		{
			logger.error("{}: Error While Calling addHostelReservation method for fileno : {}",this.getClass().getName(),hostelReservation.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}

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
		try
		{
			List<StudentBasicInfo> studentBasicInfo = hostelWorkflowManager.getStudentDtlBySearchCriteria(searchCriteria);
			response.setResponseBody(studentBasicInfo);

			if(studentBasicInfo == null){

				response.setError("No such record found");
			}
		}
		catch(Exception e)
		{
			logger.error("{} :Error while Calling getStudentDtlBySearchCriteria method for name:{}",this.getClass().getName(),searchCriteria.getFirstName(),e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/basicInfo/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentBasicInfo(@PathVariable Long fileNo){
		Response response=new Response();
		try
		{
			StudentBasicInfo basicInfo = hostelWorkflowManager.getStudentBsInfo(fileNo);
			response.setResponseBody(basicInfo);
		}
		catch(Exception e)
		{
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}

	
	@RequestMapping(value ="/roomAllocation/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getRoomAllocation(@PathVariable Long fileNo){
		Response response=new Response();
		try
		{
			RoomAllocation roomAllocation = hostelWorkflowManager.getRoomAllocation(fileNo);
			response.setResponseBody(roomAllocation);
		}
		catch(Exception e)
		{
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value ="/roomAllocation/{fileNo}",method = RequestMethod.POST)
	public ResponseEntity<Response> addHostelAllocation(@RequestBody RoomAllocation roomAllocation, @PathVariable Long fileNo){
		Response response = new Response();
		try
		{
			hostelWorkflowManager.saveRoomAllocation(roomAllocation, fileNo);
			RoomAllocation updatedAllocation=hostelWorkflowManager.getRoomAllocation(fileNo);
			response.setResponseBody(updatedAllocation);
		}
		catch(Exception e){
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/roomAllocation/{fileNo}",method = RequestMethod.DELETE)
	public ResponseEntity deleteRoomAllocation(@PathVariable Long fileNo){
		hostelWorkflowManager.deleteRoomAllocation(fileNo);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
