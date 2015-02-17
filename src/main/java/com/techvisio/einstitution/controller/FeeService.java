package com.techvisio.einstitution.controller;

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
import com.techvisio.einstitution.workflow.FeeDetailWorkflowManager;
import com.techvisio.einstitution.workflow.impl.FeeDetailWorkflowManagerImpl;

@RestController
@RequestMapping("/Fee")
public class FeeService {
	
	private static final Logger logger = Logger.getLogger(FeeService.class);
	

//FeeDetail	
	@RequestMapping(value="/FeeDetail/{feeHeadId/course/branch}", method = RequestMethod.GET)
	public FeeDetail getFeeDetail(@PathVariable Long feeHeadId,Long course,Long branch ){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		
		FeeDetail detail = detailWorkflowManager.getFeeDetail(feeHeadId, course, branch);
		return detail;
		
	}
	
	@RequestMapping(value="/FeeDetail",method = RequestMethod.POST)
	public void addFeeDetail(@RequestBody FeeDetail feeDetail){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.addFeeDetail(feeDetail);
	}

	@RequestMapping(value="/FeeDetail", method = RequestMethod.PUT)
	public void updateFeeDetail(@RequestBody FeeDetail feeDetail){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.updateFeeDetail(feeDetail);
		
		}
	
	@RequestMapping(value="/FeeDetail/{feeHeadId/course/branch}", method = RequestMethod.DELETE)
	public void deleteFeeDetail(@PathVariable Long feeHeadId,Long course,Long branch ){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.deleteFeeDetail(feeHeadId, course, branch);
	}
	

	
//StudentFeeStaging	
	@RequestMapping(value="/StudentFeeStaging/{fileNo}", method = RequestMethod.GET)
	public StudentFeeStaging getStudentFeeStaging(@PathVariable String fileNo){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		StudentFeeStaging feeStaging = detailWorkflowManager.getStudentFeeStaging(fileNo);
		return feeStaging;
		
	}
	
	@RequestMapping(value="/StudentFeeStaging" , method = RequestMethod.POST)
	public void addStudentFeeStaging(@RequestBody StudentFeeStaging studentFeeStaging){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.addStudentFeeStaging(studentFeeStaging);
	}
	
	@RequestMapping(value="/StudentFeeStaging" , method = RequestMethod.PUT)
	public void updateStudentFeeStaging(@RequestBody StudentFeeStaging studentFeeStaging){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.updateStudentFeeStaging(studentFeeStaging);
	}

	
	
	@RequestMapping(value="/StudentFeeStaging/{fileNo}", method = RequestMethod.DELETE)
	public void deleteStudentFeeStaging(@PathVariable String fileNo){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.deleteStudentFeeStaging(fileNo);
	}


//FeeTransaction	
	@RequestMapping(value="/FeeTransaction/{fileNo}", method = RequestMethod.GET)
	public FeeTransaction getFeeTransaction(@PathVariable String fileNo){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		FeeTransaction transaction = detailWorkflowManager.getFeeTransaction(fileNo);
		return transaction;
		
	}
	
	@RequestMapping(value="/FeeTransaction" , method = RequestMethod.POST)
	public void addFeeTransaction(@RequestBody FeeTransaction feeTransaction){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.addFeeTransaction(feeTransaction);
	}
	

//FeeDiscountHead
	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.GET)
	public FeeDiscountHead getfeeDiscountHead(@PathVariable Long headId){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		FeeDiscountHead discountHead = detailWorkflowManager.getfeeDiscountHead(headId);
		return discountHead;
	}
	
	@RequestMapping(value="/FeeDiscountHead" , method = RequestMethod.POST)
	public void addFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.addFeeDiscountHead(feeDiscountHead);
	}
	
	@RequestMapping(value="/FeeDiscountHead" , method = RequestMethod.PUT)
	public void updateFeeDiscountHead(@RequestBody FeeDiscountHead feeDiscountHead){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();	
		detailWorkflowManager.updateFeeDiscountHead(feeDiscountHead);
		
	}
	
	@RequestMapping(value="/FeeDiscountHead/{headId}", method = RequestMethod.DELETE)
	public void deleteFeeDiscountHead(@PathVariable Long headId){
		FeeDetailWorkflowManager detailWorkflowManager = new FeeDetailWorkflowManagerImpl();
		detailWorkflowManager.deleteFeeDiscountHead(headId);
	}

}
