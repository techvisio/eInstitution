package com.techvisio.einstitution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;

@RestController
@RequestMapping("/fee")
public class FeeService {

	private static CustomLogger logger = CustomLogger.getLogger(FeeService.class);

	@Autowired
	FeeWorkflowManager detailWorkflowManager;
	
	//FeeDetail	
	@RequestMapping(value="/applicablefeeDetail/", method = RequestMethod.POST)
	public List<ApplicableFeeDetail> getFeeDetail(@RequestBody ApplicableFeeCriteria feeCriteria){
		//logger.info("{} getting fee detail :  {}", this.getClass().getName(), feeCriteria.get);
		List<ApplicableFeeDetail> details = detailWorkflowManager.getApplicableFeeDetail(feeCriteria);
		return details;
	}

	@RequestMapping(value="/feeDetail",method = RequestMethod.POST)
	public void addFeeDetail(@RequestBody ApplicableFeeDetail feeDetail){
		logger.info("{} FeeService Calling addFeeDetail method for : FeeHeadId : {}", this.getClass().getName(), feeDetail.getFeeDetail().getHeadId());
		detailWorkflowManager.addFeeDetail(feeDetail);
	}

	@RequestMapping(value="/feeDetail", method = RequestMethod.PUT)
	public void updateFeeDetail(@RequestBody ApplicableFeeDetail feeDetail){
		logger.info("{} FeeService Calling updateFeeDetail method for : FeeHeadId : {}", this.getClass().getName(), feeDetail.getFeeDetail().getHeadId());
		detailWorkflowManager.updateFeeDetail(feeDetail);

	}

	@RequestMapping(value="/feeDetail/course/{course}/branch/{branch}/feeHeadId/{feeHeadId}", method = RequestMethod.DELETE)
	public void deleteFeeDetail(@PathVariable Long course,@PathVariable Long branch, @PathVariable Long feeHeadId ){
		logger.info("{}FeeService Calling deleteFeeDetail method for : FeeHeadId : {}", this.getClass().getName(), feeHeadId);
		detailWorkflowManager.deleteFeeDetail(course, branch, feeHeadId);
	}



	//StudentFeeStaging	
	@RequestMapping(value="/studentFeeStaging/fileNo/{fileNo}/feeHeadId/{feeHeadId}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentFeeStaging(@PathVariable Long fileNo, @PathVariable  Long feeHeadId){
		logger.info("{} FeeService Calling getStudentFeeStaging method for : fileNo : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try
		{
		 
		List<StudentFeeStaging> feeStaging = detailWorkflowManager.getStudentFeeStaging(fileNo,null);
		
		response.setResponseBody(feeStaging);
		}
		catch(Exception e)
		{
			logger.error("Error while {}",e);
		     response.setError(e.getLocalizedMessage());   
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK); 

	}

	@RequestMapping(value="/studentFeeStaging" , method = RequestMethod.POST)
	public ResponseEntity<Response> addStudentFeeStaging(@RequestBody List<StudentFeeStaging> studentFeeStagings){
		//logger.info("{} FeeService Calling saveStudentFeeStaging method for : fileNo : {}",this.getClass().getName(),);
		Response response = new Response();
		try
		{
			detailWorkflowManager.saveStudentFeeStaging(studentFeeStagings); 

			response.setResponseBody(studentFeeStagings);
		}
		catch(Exception e)
		{
			logger.error("Error while {}",e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
//
//	@RequestMapping(value="/studentFeeStaging" , method = RequestMethod.PUT)
//	public void updateStudentFeeStaging(@RequestBody List<StudentFeeStaging> studentFeeStagings){
//		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
//		detailWorkflowManager.saveStudentFeeStaging(studentFeeStagings);
//	}

	@RequestMapping(value="/studentFeeStagings/" , method = RequestMethod.PUT)
	public ResponseEntity<Response> updateStudentFeeStaging(List<StudentFeeStaging> studentFeeStagings) {

		Response response = new Response();
		try
		{
			detailWorkflowManager.saveStudentFeeStaging(studentFeeStagings); 

			response.setResponseBody(studentFeeStagings);
		}
		catch(Exception e)
		{
			logger.error("Error while {}",e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

//	@RequestMapping(value="/StudentFeeStaging/{fileNo}", method = RequestMethod.DELETE)
//	public void deleteStudentFeeStaging(@PathVariable Long fileNoStudentFeeStaging studentFeeStaging){
//		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
//		detailWorkflowManager.deleteStudentFeeStaging(studentFeeStaging);
//	}


	////FeeTransaction	
	@RequestMapping(value="/debitedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDebitedFeeTransaction(@PathVariable Long fileNo){
		logger.info("{} FeeService Calling getDebitedFeeTransaction method for : fileNo : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<FeeTransaction> transactions = detailWorkflowManager.getDebitedFeeTransaction(fileNo);
			response.setResponseBody(transactions);
		} catch (Exception e) {
			logger.error("Error while {}",e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}


	@RequestMapping(value="/feeTransactionDebit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionDebit(@RequestBody FeeTransaction feeTransaction){
		logger.info("{} FeeService Calling addFeeTransactionDebit method for : fileNo : {}",this.getClass().getName(), feeTransaction.getFileNo());
		Response response = new Response();
		try {
			detailWorkflowManager.addFeeTransactionDebit(feeTransaction);
			response.setResponseBody(feeTransaction);
		} catch (Exception e) {
			logger.error("Error while {}",e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/creditedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getCreditedFeeTransaction(@PathVariable Long fileNo){
		logger.info("{} FeeService Calling getCreditedFeeTransaction method for : fileNo : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<FeeTransaction> transactions = detailWorkflowManager.getCreditedFeeTransaction(fileNo);
			response.setResponseBody(transactions);
		} catch (Exception e) {

			logger.error("Error while {}",e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);


	}

	@RequestMapping(value="/feeTransactionCredit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionCredit(@RequestBody FeeTransaction feeTransaction){
		logger.info("{} FeeService Calling addFeeTransactionCredit method for : fileNo : {}",this.getClass().getName(), feeTransaction.getFileNo());
		Response response = new Response();
		try {
			detailWorkflowManager.addFeeTransactionCredit(feeTransaction);
			response.setResponseBody(feeTransaction);
		} catch (Exception e) {
			logger.error("Error while {}",e);
			response.setError(e.getLocalizedMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);
      	}
	//FeeDiscountHead
	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.GET)
	public FeeDiscountHead getfeeDiscountHead(@PathVariable Long headId){
		logger.info("{} FeeService Calling getfeeDiscountHead method for : FeeHeadId : {}",this.getClass().getName(), headId);
		FeeDiscountHead discountHead = detailWorkflowManager.getfeeDiscountHead(headId);
		return discountHead;
	}

	@RequestMapping(value="/FeeDiscountHead", method = RequestMethod.POST)
	public void addFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		logger.info("{} FeeService Calling addFeeDiscountHead method for : FeeHeadId : {}",this.getClass().getName(), feeDiscountHead.getHeadId());
		detailWorkflowManager.addFeeDiscountHead(feeDiscountHead);
	}

	@RequestMapping(value="/FeeDiscountHead" , method = RequestMethod.PUT)
	public void updateFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		logger.info("{} FeeService Calling updateFeeDiscountHead method for : FeeHeadId : {}",this.getClass().getName(), feeDiscountHead.getHeadId());
		detailWorkflowManager.updateFeeDiscountHead(feeDiscountHead);

	}

	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.DELETE)
	public void deleteFeeDiscountHead(@PathVariable Long headId){
		logger.info("{} FeeService Calling deleteFeeDiscountHead method for : FeeHeadId : {}",this.getClass().getName(), headId);
		detailWorkflowManager.deleteFeeDiscountHead(headId);
	}

	@RequestMapping(value="/feeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getfeeTransactionDtl(@PathVariable Long fileNo){
		logger.info("{} FeeService Calling getFeeTransactionDetail method for : File no : {}",this.getClass().getName(), fileNo);
		Response response = new Response();

		try {
			FeeTransactionAdmissionBean admissionBean = detailWorkflowManager.getFeeTransactionDetail(fileNo);
			response.setResponseBody(admissionBean);
		} catch (Exception e) {

			logger.error("Error while {}",e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pandingFee/{limit}", method = RequestMethod.GET)
	public  ResponseEntity<Response> getPendingFeeInfo(@PathVariable int limit){
		logger.info("{} FeeService Calling getPendingfeeInfo method on the basis of : Limit : {}",this.getClass().getName(), limit);
		Response response = new Response();
		try
		{
		    List<FeeAdmissionBean> feeAdmissionBeans = detailWorkflowManager.getPendingfeeInfo(limit);
		    response.setResponseBody(feeAdmissionBeans);
		}
		catch(Exception e)
		{
			logger.error("Error while {}",e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     	}

}
