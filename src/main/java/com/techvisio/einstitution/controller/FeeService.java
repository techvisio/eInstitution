package com.techvisio.einstitution.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.impl.FeeWorkflowManagerImpl;

@RestController
@RequestMapping("/fee")
public class FeeService {

	private static final Logger logger = Logger.getLogger(FeeService.class);

	//FeeDetail	
	@RequestMapping(value="/feeDetail/course/{course}/branch/{branch}/feeHeadId/{feeHeadId}", method = RequestMethod.GET)
	public List<FeeDetail> getFeeDetail(@PathVariable Long course,@PathVariable Long branch,@PathVariable Long feeHeadId){

		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();

		List<FeeDetail> details = detailWorkflowManager.getFeeDetail( course, branch,feeHeadId);
		return details;

	}

	@RequestMapping(value="/feeDetail",method = RequestMethod.POST)
	public void addFeeDetail(@RequestBody FeeDetail feeDetail){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.addFeeDetail(feeDetail);
	}

	@RequestMapping(value="/feeDetail", method = RequestMethod.PUT)
	public void updateFeeDetail(@RequestBody FeeDetail feeDetail){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.updateFeeDetail(feeDetail);

	}

	@RequestMapping(value="/feeDetail/course/{course}/branch/{branch}/feeHeadId/{feeHeadId}", method = RequestMethod.DELETE)
	public void deleteFeeDetail(@PathVariable Long course,@PathVariable Long branch, @PathVariable Long feeHeadId ){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.deleteFeeDetail(course, branch, feeHeadId);
	}



	//StudentFeeStaging	
	@RequestMapping(value="/studentFeeStaging/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentFeeStaging(@PathVariable Long fileNo){
	  
		Response response = new Response();
		try
		{
		 
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		List<StudentFeeStaging> feeStaging = detailWorkflowManager.getStudentFeeStaging(fileNo,null);
		
		response.setResponseBody(feeStaging);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		     response.setError(e.getLocalizedMessage());   
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK); 

	}

	@RequestMapping(value="/studentFeeStaging" , method = RequestMethod.POST)
	public ResponseEntity<Response> addStudentFeeStaging(@RequestBody StudentFeeStaging studentFeeStaging){

		Response response = new Response();
		try
		{
			FeeWorkflowManager feeWorkflowManager = new FeeWorkflowManagerImpl();
			feeWorkflowManager.addStudentFeeStaging(studentFeeStaging); 

			response.setResponseBody(studentFeeStaging);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/studentFeeStaging" , method = RequestMethod.PUT)
	public void updateStudentFeeStaging(@RequestBody StudentFeeStaging studentFeeStaging){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.updateStudentFeeStaging(studentFeeStaging);
	}

	@RequestMapping(value="/studentFeeStagings/" , method = RequestMethod.PUT)
	public ResponseEntity<Response> updateStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings) {

		Response response = new Response();
		try
		{
			FeeWorkflowManager feeWorkflowManager = new FeeWorkflowManagerImpl();
			feeWorkflowManager.updateStudentFeeStaging(studentFeeStagings); 

			response.setResponseBody(studentFeeStagings);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/StudentFeeStaging/{fileNo}", method = RequestMethod.DELETE)
	public void deleteStudentFeeStaging(@PathVariable StudentFeeStaging studentFeeStaging){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.deleteStudentFeeStaging(studentFeeStaging);
	}


	////FeeTransaction	
	@RequestMapping(value="/debitedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDebitedFeeTransaction(@PathVariable Long fileNo){
		Response response = new Response();
		try {
			FeeWorkflowManager manager = new FeeWorkflowManagerImpl();
			List<FeeTransaction> transactions = manager.getDebitedFeeTransaction(fileNo);
			response.setResponseBody(transactions);
		} catch (Exception e) {
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}


	@RequestMapping(value="/feeTransactionDebit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionDebit(@RequestBody FeeTransaction feeTransaction){
		Response response = new Response();
		try {
			FeeWorkflowManager manager = new FeeWorkflowManagerImpl();
			manager.addFeeTransactionDebit(feeTransaction);
			response.setResponseBody(feeTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/creditedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getCreditedFeeTransaction(@PathVariable Long fileNo){
		Response response = new Response();
		try {
			FeeWorkflowManager manager = new FeeWorkflowManagerImpl();
			List<FeeTransaction> transactions = manager.getCreditedFeeTransaction(fileNo);
			response.setResponseBody(transactions);
		} catch (Exception e) {

			e.printStackTrace();
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);


	}

	@RequestMapping(value="/feeTransactionCredit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionCredit(@RequestBody FeeTransaction feeTransaction){
		Response response = new Response();
		try {
			FeeWorkflowManager manager = new FeeWorkflowManagerImpl();
			manager.addFeeTransactionCredit(feeTransaction);
			response.setResponseBody(feeTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			response.setError(e.getLocalizedMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);
      	}
	//FeeDiscountHead
	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.GET)
	public FeeDiscountHead getfeeDiscountHead(@PathVariable Long headId){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		FeeDiscountHead discountHead = detailWorkflowManager.getfeeDiscountHead(headId);
		return discountHead;
	}

	@RequestMapping(value="/FeeDiscountHead", method = RequestMethod.POST)
	public void addFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.addFeeDiscountHead(feeDiscountHead);
	}

	@RequestMapping(value="/FeeDiscountHead" , method = RequestMethod.PUT)
	public void updateFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();	
		detailWorkflowManager.updateFeeDiscountHead(feeDiscountHead);

	}

	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.DELETE)
	public void deleteFeeDiscountHead(@PathVariable Long headId){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.deleteFeeDiscountHead(headId);
	}

	@RequestMapping(value="/feeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getfeeTransactionDtl(@PathVariable Long fileNo){

		Response response = new Response();

		try {
			FeeWorkflowManager manager = new FeeWorkflowManagerImpl();
			FeeTransactionAdmissionBean admissionBean = manager.getFeeTransactionDetail(fileNo);
			response.setResponseBody(admissionBean);
		} catch (Exception e) {

			e.printStackTrace();
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pandingFee/{limit}", method = RequestMethod.GET)
	public  ResponseEntity<Response> getPendingFeeInfo(@PathVariable int limit){
		
		Response response = new Response();
		try
		{
			FeeWorkflowManager manager = new FeeWorkflowManagerImpl();
		    List<FeeAdmissionBean> feeAdmissionBeans = manager.getPendingfeeInfo(limit);
		    response.setResponseBody(feeAdmissionBeans);
		}
		catch(Exception e)
		{
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     	}

}
