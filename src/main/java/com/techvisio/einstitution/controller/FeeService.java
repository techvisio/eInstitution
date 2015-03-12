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

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.workflow.AdmissionWorkflowManager;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.impl.AdmissionWorkflowManagerImpl;
import com.techvisio.einstitution.workflow.impl.FeeWorkflowManagerImpl;

@RestController
@RequestMapping("/fee")
public class FeeService {
	
	private static final Logger logger = Logger.getLogger(FeeService.class);
	
//FeeDetail	
	@RequestMapping(value="/feeDetail/course/{course}/branch/{branch}", method = RequestMethod.GET)
	public List<FeeDetail> getFeeDetail(@PathVariable Long course,@PathVariable Long branch){
		
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		
		List<FeeDetail> details = detailWorkflowManager.getFeeDetail( course, branch);
		return details;
		
	}
	
	@RequestMapping(value="/FeeDetail",method = RequestMethod.POST)
	public void addFeeDetail(@RequestBody FeeDetail feeDetail){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.addFeeDetail(feeDetail);
	}

	@RequestMapping(value="/FeeDetail", method = RequestMethod.PUT)
	public void updateFeeDetail(@RequestBody FeeDetail feeDetail){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.updateFeeDetail(feeDetail);
		
		}
	
	@RequestMapping(value="/FeeDetail/{feeHeadId/course/branch}", method = RequestMethod.DELETE)
	public void deleteFeeDetail(@PathVariable Long course,Long branch, Integer semester ){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.deleteFeeDetail(course, branch, semester);
	}
	

	
//StudentFeeStaging	
	@RequestMapping(value="/StudentFeeStaging/{fileNo}", method = RequestMethod.GET)
	public List<StudentFeeStaging> getStudentFeeStaging(@PathVariable String fileNo){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		List<StudentFeeStaging> feeStaging = detailWorkflowManager.getStudentFeeStaging(fileNo);
		return feeStaging;
		
	}
	
	@RequestMapping(value="/StudentFeeStaging" , method = RequestMethod.POST)
	public void addStudentFeeStaging(@RequestBody StudentFeeStaging studentFeeStaging){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.addStudentFeeStaging(studentFeeStaging);
	}
	
	@RequestMapping(value="/StudentFeeStaging" , method = RequestMethod.PUT)
	public void updateStudentFeeStaging(@RequestBody StudentFeeStaging studentFeeStaging){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.updateStudentFeeStaging(studentFeeStaging);
	}

	
	
	@RequestMapping(value="/StudentFeeStaging/{fileNo}", method = RequestMethod.DELETE)
	public void deleteStudentFeeStaging(@PathVariable StudentFeeStaging studentFeeStaging){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.deleteStudentFeeStaging(studentFeeStaging);
	}


////FeeTransaction	
	@RequestMapping(value="/debitedFeeTransaction/{fileNo}", method = RequestMethod.GET)
	public ResponseEntity<Response> getDebitedFeeTransaction(@PathVariable String fileNo){
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
	public ResponseEntity<Response> getCreditedFeeTransaction(@PathVariable String fileNo){
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
	
	@RequestMapping(value="/FeeDiscountHead" , method = RequestMethod.POST)
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
	public ResponseEntity<Response> getfeeTransactionDtl(@PathVariable String fileNo){
		
		AdmissionWorkflowManager admissionWorkFlow=new AdmissionWorkflowManagerImpl();
		FeeWorkflowManager feeworkFlow= new FeeWorkflowManagerImpl();
		
		Response response=new Response();
		ResponseEntity<Response> result=new ResponseEntity<Response>(response, HttpStatus.OK);
		try
		{
			FeeTransactionAdmissionBean transactionAdmissionBean = new FeeTransactionAdmissionBean();
			StudentBasicInfo basicInfo=admissionWorkFlow.getStudentBsInfo(fileNo);
			transactionAdmissionBean.setBasicInfo(basicInfo);
			
			if(basicInfo!=null){
				
				List<FeeTransaction> TransactionCredit = feeworkFlow.getCreditedFeeTransaction(fileNo);
			    transactionAdmissionBean.setFeeTransactionCredit(TransactionCredit); 	
		
			    List<FeeTransaction> TransactionDebit = feeworkFlow.getDebitedFeeTransaction(fileNo);
			    transactionAdmissionBean.setFeeTransactionDebit(TransactionDebit);
			
			    response.setResponseBody(transactionAdmissionBean);
			}
		}
			catch(Exception e)
			{
				response.setError(e.getLocalizedMessage());
			}
			return result;
	}
		
	
}
