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
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmission;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.StudentFeeStaging;
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

	@RequestMapping(value="/feeDetail",method = RequestMethod.POST)
	public void addFeeDetail(@RequestBody ApplicableFeeDetail feeDetail){
		logger.info("{}:  Calling addFeeDetail method for FeeHeadId : {}", this.getClass().getName(), feeDetail.getFeeDetail().getHeadId());
		feeWorkflowManager.addFeeDetail(feeDetail);
	}

	@RequestMapping(value="/feeDetail", method = RequestMethod.PUT)
	public void updateFeeDetail(@RequestBody ApplicableFeeDetail feeDetail){
		logger.info("{}:  Calling updateFeeDetail method for FeeHeadId : {}", this.getClass().getName(), feeDetail.getFeeDetail().getHeadId());
		feeWorkflowManager.updateFeeDetail(feeDetail);

	}

	@RequestMapping(value="/feeDetail/course/{course}/branch/{branch}/feeHeadId/{feeHeadId}", method = RequestMethod.DELETE)
	public void deleteFeeDetail(@PathVariable Long course,@PathVariable Long branch, @PathVariable Long feeHeadId ){
		logger.info("{}: Calling deleteFeeDetail method for : FeeHeadId : {}", this.getClass().getName(), feeHeadId);
		feeWorkflowManager.deleteFeeDetail(course, branch, feeHeadId);
	}



	//StudentFeeStaging	
	@RequestMapping(value="/studentFeeStaging/fileNo/{fileNo}/feeHeadId/{feeHeadId}", method = RequestMethod.GET)
	public ResponseEntity<Response> getStudentFeeStaging(@PathVariable Long fileNo, @PathVariable  Long feeHeadId){
		logger.info("{}:  Calling getStudentFeeStaging method for  fileNo : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try
		{
		 
		List<StudentFeeStaging> feeStaging = feeWorkflowManager.getStudentFeeStaging(fileNo,null);
		
		response.setResponseBody(feeStaging);
		}
		catch(Exception e)
		{
			logger.error("{}:Error while calling getStudentFeeStaging method for file no: {}",this.getClass().getName(),fileNo,e);
		     response.setError(e.getLocalizedMessage());   
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK); 

	}

	@RequestMapping(value="/studentFeeStaging" , method = RequestMethod.POST)
	public ResponseEntity<Response> addStudentFeeStaging(@RequestBody List<StudentFeeStaging> studentFeeStagings){
		logger.info("{}:  Calling saveStudentFeeStaging method by passing studentFeeStagings:{} ",this.getClass().getName(), studentFeeStagings);
		Response response = new Response();
		try
		{
			feeWorkflowManager.saveStudentFeeStaging(studentFeeStagings); 

			response.setResponseBody(studentFeeStagings);
		}
		catch(Exception e)
		{
			logger.error("{} :Error while calling saveStudentFeeStaging method by passsing studentFeeStagings:{}",this.getClass().getName(),studentFeeStagings,e);
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
		logger.info("{}:  Calling saveStudentFeeStaging method by passing studentFeeStagings:{} for updation ",this.getClass().getName(), studentFeeStagings);
		Response response = new Response();
		try
		{
			feeWorkflowManager.saveStudentFeeStaging(studentFeeStagings); 

			response.setResponseBody(studentFeeStagings);
		}
		catch(Exception e)
		{
			logger.error("{}:Error while Calling saveStudentFeeStaging method by passing studentFeeStagings:{} for updation",this.getClass().getName(),studentFeeStagings,e);
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
		logger.info("{}: Calling getDebitedFeeTransaction method by passing  fileNo : {}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<FeeTransaction> transactions = feeWorkflowManager.getDebitedFeeTransaction(fileNo);
			response.setResponseBody(transactions);
		} catch (Exception e) {
			logger.error("{}:Error while Calling getDebitedFeeTransaction method by passing fileNo : {}",this.getClass().getName(), fileNo,e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}


	@RequestMapping(value="/feeTransactionDebit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionDebit(@RequestBody FeeTransaction feeTransaction){
		logger.info("{}:  Calling addFeeTransactionDebit method for : fileNo : {}",this.getClass().getName(), feeTransaction.getFileNo());
		Response response = new Response();
		try {
			feeWorkflowManager.addFeeTransactionDebit(feeTransaction);
			response.setResponseBody(feeTransaction);
		} catch (Exception e) {
			logger.error("{}:Error while Calling addFeeTransactionDebit method for fileNo : {}",this.getClass().getName(), feeTransaction.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(value="/creditedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getCreditedFeeTransaction(@PathVariable Long fileNo){
		logger.info("{}:  Calling getCreditedFeeTransaction method by passing fileNo :{}",this.getClass().getName(), fileNo);
		Response response = new Response();
		try {
			List<FeeTransaction> transactions = feeWorkflowManager.getCreditedFeeTransaction(fileNo);
			response.setResponseBody(transactions);
		} catch (Exception e) {

			logger.error("{}:Error while Calling getCreditedFeeTransaction method by passing fileNo :{}",this.getClass().getName(),fileNo,e);
			response.setError(e.getMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);


	}

	@RequestMapping(value="/feeTransactionCredit" , method = RequestMethod.POST)
	public ResponseEntity<Response> addFeeTransactionCredit(@RequestBody FeeTransaction feeTransaction){
		logger.info("{} : Calling addFeeTransactionCredit method for fileNo : {}",this.getClass().getName(), feeTransaction.getFileNo());
		Response response = new Response();
		try {
			feeWorkflowManager.addFeeTransactionCredit(feeTransaction);
			response.setResponseBody(feeTransaction);
		} catch (Exception e) {
			logger.error("{} :Error while Calling addFeeTransactionCredit method for  fileNo : {}",this.getClass().getName(),feeTransaction.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}

		return new ResponseEntity<Response>(response,HttpStatus.OK);
      	}
	//FeeDiscountHead
	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.GET)
	public FeeDiscountHead getfeeDiscountHead(@PathVariable Long headId){
		logger.info("{}:  Calling getfeeDiscountHead method by passing FeeHeadId : {}",this.getClass().getName(), headId);
		FeeDiscountHead discountHead = feeWorkflowManager.getfeeDiscountHead(headId);
		return discountHead;
	}

	@RequestMapping(value="/FeeDiscountHead", method = RequestMethod.POST)
	public void addFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		logger.info("{}:  Calling addFeeDiscountHead method for : FeeHeadId : {}",this.getClass().getName(), feeDiscountHead.getHeadId());
		feeWorkflowManager.addFeeDiscountHead(feeDiscountHead);
	}

	@RequestMapping(value="/FeeDiscountHead" , method = RequestMethod.PUT)
	public void updateFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		logger.info("{} : Calling updateFeeDiscountHead method for : FeeHeadId : {}",this.getClass().getName(), feeDiscountHead.getHeadId());
		feeWorkflowManager.updateFeeDiscountHead(feeDiscountHead);

	}

	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.DELETE)
	public void deleteFeeDiscountHead(@PathVariable Long headId){
		logger.info("{} : Calling deleteFeeDiscountHead method by passing FeeHeadId :{}",this.getClass().getName(), headId);
		feeWorkflowManager.deleteFeeDiscountHead(headId);
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
	
	@RequestMapping(value="/saveAmenity",method = RequestMethod.POST)
	public ResponseEntity<Response> saveAmenity(@RequestBody StudentFeeStaging studentFeeStaging){

		logger.info("{} : Calling saveAmenity method for fileNo : {}",this.getClass().getName(), studentFeeStaging.getFileNo());
		Response response = new Response();
		try {
			//get enrich feediscount object having transaction type and other details
			FeeDiscountHead discountHead=cacheManager.getFeeDiscountById(studentFeeStaging.getDiscountHead().getHeadId());
			studentFeeStaging.setDiscountHead(discountHead);
			
			feeWorkflowManager.saveAmenities(studentFeeStaging);
			response.setResponseBody(studentFeeStaging);
		} catch (Exception e) {
			logger.error("{} :Error while Calling addFeeTransactionCredit method for  fileNo : {}",this.getClass().getName(),studentFeeStaging.getFileNo(),e);
			response.setError(e.getLocalizedMessage());
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}


}
