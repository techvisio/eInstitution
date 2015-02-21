package com.techvisio.einstitution.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.FeeDetail;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.workflow.FeeWorkflowManager;
import com.techvisio.einstitution.workflow.impl.FeeWorkflowManagerImpl;

@RestController
@RequestMapping("/fee")
public class FeeService {
	
	private static final Logger logger = Logger.getLogger(FeeService.class);
	

//FeeDetail	
	@RequestMapping(value="/feeDetail/course/{course}/branch/{branch}/semester/{semester}", method = RequestMethod.GET)
	public List<FeeDetail> getFeeDetail(@PathVariable Long course,@PathVariable Long branch,@PathVariable Integer semester){
		
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		
		List<FeeDetail> details = detailWorkflowManager.getFeeDetail( course, branch,semester);
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
	public StudentFeeStaging getStudentFeeStaging(@PathVariable String fileNo){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		StudentFeeStaging feeStaging = detailWorkflowManager.getStudentFeeStaging(fileNo);
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
	public void deleteStudentFeeStaging(@PathVariable String fileNo){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.deleteStudentFeeStaging(fileNo);
	}


//FeeTransaction	
	@RequestMapping(value="/FeeTransaction/{fileNo}", method = RequestMethod.GET)
	public FeeTransaction getFeeTransaction(@PathVariable String fileNo){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		FeeTransaction transaction = detailWorkflowManager.getFeeTransaction(fileNo);
		return transaction;
		
	}
	
	@RequestMapping(value="/FeeTransaction" , method = RequestMethod.POST)
	public void addFeeTransaction(@RequestBody FeeTransaction feeTransaction){
		FeeWorkflowManager detailWorkflowManager = new FeeWorkflowManagerImpl();
		detailWorkflowManager.addFeeTransaction(feeTransaction);
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

}
