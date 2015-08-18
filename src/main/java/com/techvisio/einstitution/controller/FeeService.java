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
import com.techvisio.einstitution.beans.FeeAdmission;
import com.techvisio.einstitution.beans.FeeTransactionAdmission;
import com.techvisio.einstitution.beans.FeeTransactionCredit;
import com.techvisio.einstitution.beans.FeeTransactionDebit;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;

@RestController
@RequestMapping("/fee")
public class FeeService {

	private static CustomLogger logger = CustomLogger.getLogger(FeeService.class);

	@Autowired
	FeeWorkflowManager feeWorkflowManager;

	@Autowired
	CacheManager cacheManager;

	//FeeDetail	
	@RequestMapping(value="/applicablefeeDetail/", method = RequestMethod.POST)
	public List<ApplicableFeeDetail> getFeeDetail(@RequestBody ApplicableFeeCriteria feeCriteria){
		logger.info("{}: getting fee detail for Course Id {}", this.getClass().getName(), feeCriteria.getCourseId());
		List<ApplicableFeeDetail> details = feeWorkflowManager.getApplicableFeeDetail(feeCriteria);
		return details;
	}


	////FeeTransaction	
	@RequestMapping(value="/debitedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDebitedFeeTransaction(@PathVariable Long fileNo){
		logger.info("{}: Calling getDebitedFeeTransaction method by passing  fileNo : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<FeeTransactionDebit> transactionDebits = feeWorkflowManager.getDebitedFeeTransaction(fileNo);
			response.setResponseBody(transactionDebits);
		} catch (Exception e) {
			logger.error("{}:Error while Calling getDebitedFeeTransaction method by passing fileNo : {}",this.getClass().getName(), fileNo,e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}


	@RequestMapping(value="/feeTransactionDebit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionDebit(@RequestBody FeeTransactionDebit feeTransactionDebit){
		logger.info("{}:  Calling addFeeTransactionDebit method for : fileNo : {}",this.getClass().getName(), feeTransactionDebit.getFileNo());
		Response response = new Response();
		try {
			feeWorkflowManager.addFeeTransactionDebit(feeTransactionDebit);
			response.setResponseBody(feeTransactionDebit);
		} catch (Exception e) {
			logger.error("{}:Error while Calling addFeeTransactionDebit method for fileNo : {}",this.getClass().getName(), feeTransactionDebit.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/creditedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getCreditedFeeTransaction(@PathVariable Long fileNo){
		logger.info("{}:  Calling getCreditedFeeTransaction method by passing fileNo :{}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<FeeTransactionCredit> transactionCredits = feeWorkflowManager.getCreditedFeeTransaction(fileNo);
			response.setResponseBody(transactionCredits);
		} catch (Exception e) {

			logger.error("{}:Error while Calling getCreditedFeeTransaction method by passing fileNo :{}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);


	}

	@RequestMapping(value="/feeTransactionCredit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionCredit(@RequestBody FeeTransactionCredit feeTransactionCredit){
		logger.info("{} : Calling addFeeTransactionCredit method for fileNo : {}",this.getClass().getName(), feeTransactionCredit.getFileNo());
		Response response = new Response();
		try {
			feeWorkflowManager.addFeeTransactionCredit(feeTransactionCredit);
			response.setResponseBody(feeTransactionCredit);
		} catch (Exception e) {
			logger.error("{} :Error while Calling addFeeTransactionCredit method for  fileNo : {}",this.getClass().getName(),feeTransactionCredit.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/feeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getfeeTransactionDtl(@PathVariable Long fileNo){
		logger.info("{}:  Calling getFeeTransactionDetail method by passing File no : {}",this.getClass().getName(), fileNo);
		Response response = new Response();

		try {
			FeeTransactionAdmission admissionBean = feeWorkflowManager.getFeeTransactionDetail(fileNo);
			response.setResponseBody(admissionBean);
		} catch (Exception e) {

			logger.error("{} :Error while Calling getFeeTransactionDetail method for : File no : {}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/pandingFee/{limit}", method = RequestMethod.GET)
	public  ResponseEntity<Response> getPendingFeeInfo(@PathVariable int limit){
		logger.info("{}:  Calling getPendingfeeInfo method by passing Limit : {}",this.getClass().getName(), limit);
		Response response = new Response();
		try
		{
			List<FeeAdmission> feeAdmissionBeans = feeWorkflowManager.getPendingfeeInfo(limit);
			response.setResponseBody(feeAdmissionBeans);
		}
		catch(Exception e)
		{
			logger.error("{}: Error while Calling getPendingfeeInfo method by passing Limit :{}",this.getClass().getName(), limit,e);
			response.setError(e.getMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

}
