package com.techvisio.einstitution.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techvisio.einstitution.beans.Amenities;
import com.techvisio.einstitution.beans.ApplicableFeeCriteria;
import com.techvisio.einstitution.beans.ApplicableFeeDetail;
import com.techvisio.einstitution.beans.FeeAdmissionBean;
import com.techvisio.einstitution.beans.FeeDiscountHead;
import com.techvisio.einstitution.beans.FeeTransaction;
import com.techvisio.einstitution.beans.FeeTransactionAdmissionBean;
import com.techvisio.einstitution.beans.StudentBasicInfo;
import com.techvisio.einstitution.beans.StudentDetail;
import com.techvisio.einstitution.beans.StudentFeeStaging;
import com.techvisio.einstitution.db.FeeDao;
import com.techvisio.einstitution.manager.AdmissionManager;
import com.techvisio.einstitution.manager.CacheManager;
import com.techvisio.einstitution.manager.FeeManager;
import com.techvisio.einstitution.util.AppConstants;
import com.techvisio.einstitution.util.CommonUtil;
import com.techvisio.einstitution.util.ContextProvider;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.workflow.ManagementWorkflowManager;
import com.techvisio.einstitution.workflow.impl.ManagementWorkflowManagerImpl;
@Component
public class FeeManagerImpl implements FeeManager{
	private static CustomLogger logger = CustomLogger.getLogger(FeeManagerImpl.class);
	@Autowired
	FeeDao feeDetailDao ;

	@Autowired
	AdmissionManager admissionManager;

	@Autowired
	CacheManager  cacheManager;
	
	private static FeeManagerImpl instance=null;
	public static synchronized FeeManagerImpl getInstance()
	{
		if(instance == null){
			instance=new FeeManagerImpl();
		}

		return instance;
	}

	private FeeManagerImpl() {

	}
	@Override
	public List<FeeAdmissionBean> getPendingfeeInfo(int limit){
		logger.info("{} : calling getPendingfeeInfo method by passing limit:{} ",this.getClass().getName(), limit);	
		List<FeeAdmissionBean> feeAdmissionBeans = feeDetailDao.getPendingfeeInfo(limit);

		return feeAdmissionBeans;

	}
	public FeeDiscountHead getfeeDiscountHead(Long headId) {
		logger.info("{} : calling getfeeDiscountHead method by passing headId:{} ",this.getClass().getName(), headId);
		FeeDiscountHead feeDiscountHead =  null;
		feeDiscountHead = feeDetailDao.getfeeDiscountHead(headId);

		return feeDiscountHead;
	}

	public void addFeeDiscountHead(FeeDiscountHead feeDiscountHead) {
		logger.info("{} : calling addFeeDiscountHead method for headId:{} ",this.getClass().getName(), feeDiscountHead.getHeadId());
		feeDetailDao.addFeeDiscountHead(feeDiscountHead);
	}

	public void updateFeeDiscountHead(FeeDiscountHead feeDiscountHead) {
		logger.info("{} : calling updateFeeDiscountHead method for headId:{} ",this.getClass().getName(), feeDiscountHead.getHeadId());
		feeDetailDao.updateFeeDiscountHead(feeDiscountHead);
	}

	public void deleteFeeDiscountHead(Long headId) {
		logger.info("{} : calling deleteFeeDiscountHead method for headId:{} ",this.getClass().getName(), headId);
		feeDetailDao.deleteFeeDiscountHead(headId);
	}

	@Override
	public List<ApplicableFeeDetail> getApplicableFeeDetail(ApplicableFeeCriteria criteria) {
		// details = null;
		logger.info("{} : calling getApplicableFeeDetails method for courseId:{} and branchId:{} ",this.getClass().getName(), criteria.getCourseId(),criteria.getBranchId());
		List<ApplicableFeeDetail> details =  feeDetailDao.getApplicableFeeDetails(criteria);
		return details;
	}

	public void addFeeDetail(ApplicableFeeDetail feeDetail) {
		logger.info("{} : calling addFeeDetail method for course:{} ",this.getClass().getName(), feeDetail.getCourse());
		feeDetailDao.addFeeDetail(feeDetail);

	}

	public void updateFeeDetail(ApplicableFeeDetail feeDetail) {
		logger.info("{} : calling updateFeeDetail method for course:{} ",this.getClass().getName(), feeDetail.getCourse());
		feeDetailDao.updateFeeDetail(feeDetail);
	}

	public void deleteFeeDetail(Long course, Long branch, Long feeHeadId) {
		logger.info("{} : calling deleteFeeDetail method by passing courseId:{} and branchId:{} and feeHeadId:{} ",this.getClass().getName(), course,branch,feeHeadId);
		feeDetailDao.deleteFeeDetail(course, branch, feeHeadId);
	}

	public List<StudentFeeStaging> getStudentFeeStaging(Long fileNo, Long feeHeadId) {
		logger.info("{} : calling getStudentFeeStaging method by passing fileNo:{} and feeHeadId:{} ",this.getClass().getName(), fileNo,feeHeadId);
		List<StudentFeeStaging> feeStaging = new ArrayList<StudentFeeStaging>();
		feeStaging = feeDetailDao.getStudentFeeStaging(fileNo,feeHeadId);
		return feeStaging;
	}

	@Override
	public void moveStaggingandBaseFeetoTransaction(StudentBasicInfo basicInfo,List<StudentFeeStaging> newStagging){
		logger.info("{} :  Move Stagging and Base Fee to Transaction  for Student:{} ",this.getClass().getName(), basicInfo.getFirstName()+basicInfo.getLastName());
		Long fileNo = basicInfo.getFileNo();
		List<FeeTransaction> creditFeetransaction = new ArrayList<FeeTransaction>();
		List<FeeTransaction> debitFeetransaction = new ArrayList<FeeTransaction>();

		if (newStagging != null) {
			List<StudentFeeStaging> oldFeeStagingObj = feeDetailDao.getStudentFeeStaging(fileNo, null);
			List<StudentFeeStaging> newFeeStagingObj = newStagging;

			Set<StudentFeeStaging> oldFeeStagingSet = new HashSet<StudentFeeStaging>(oldFeeStagingObj);
			Set<StudentFeeStaging> newFeeStagingSet = new HashSet<StudentFeeStaging>(newStagging);

			//getting new Data of Feestaging
			newFeeStagingSet.removeAll(oldFeeStagingSet);

			// iterate over new obj and creating fee transaction
			if (newFeeStagingSet != null && newFeeStagingSet.size() > 0) {
				for (StudentFeeStaging feeStaging : newFeeStagingSet) {
					if (feeStaging.isApproved()) {
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(feeStaging.getDiscountHead());
						feeTransaction.setAmount(feeStaging.getAmount());
						creditFeetransaction.add(feeTransaction);
					}
				}
			}

			//creating map of old vs new objects for comparing older and new approved status
			Map<StudentFeeStaging, StudentFeeStaging> feeStagingMap = new HashMap<StudentFeeStaging, StudentFeeStaging>();
			for(StudentFeeStaging oldFeeStaging : oldFeeStagingObj){
				for(StudentFeeStaging newFeeStaging : newFeeStagingObj){
					if(oldFeeStaging.equals(newFeeStaging)){
						feeStagingMap.put(oldFeeStaging, newFeeStaging);
						break;
					}
				}
			}

			for(StudentFeeStaging oldFeeStag:feeStagingMap.keySet())
			{
				StudentFeeStaging newFeeStag=feeStagingMap.get(oldFeeStag);
				if(newFeeStag!=null && "W".equalsIgnoreCase(newFeeStag.getDiscountHead().getTransactionType())){
					if(newFeeStag.isApproved() && !oldFeeStag.isApproved()){
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(newFeeStag.getDiscountHead());
						feeTransaction.setAmount(newFeeStag.getAmount());
						creditFeetransaction.add(feeTransaction);
					}
					else if(!newFeeStag.isApproved() && oldFeeStag.isApproved()){
						oldFeeStag.getDiscountHead().setHeadId(AppConstants.REVERSAL_ID);
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(oldFeeStag.getDiscountHead());
						feeTransaction.setAmount(oldFeeStag.getAmount());
						debitFeetransaction.add(feeTransaction);
					}
					
					else if(newFeeStag.isApproved() && oldFeeStag.isApproved()){
						if(!newFeeStag.getAmount().equals(oldFeeStag.getAmount())){
						oldFeeStag.getDiscountHead().setHeadId(AppConstants.REVERSAL_ID);
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(oldFeeStag.getDiscountHead());
						feeTransaction.setAmount(oldFeeStag.getAmount());
						debitFeetransaction.add(feeTransaction);
						
						FeeTransaction feeTransac = new FeeTransaction();
						feeTransac.setAmount(newFeeStag.getAmount());
						feeTransac.setFeeDiscountHead(newFeeStag.getDiscountHead());
						creditFeetransaction.add(feeTransac);
						
						}
					}
				}
				//if no new entry found against old record means record is to be deleted
				//check if it was approved earlier if so create a reversal
				else{
					if(oldFeeStag.isApproved() && "W".equalsIgnoreCase(oldFeeStag.getDiscountHead().getTransactionType())){
						oldFeeStag.getDiscountHead().setHeadId(AppConstants.REVERSAL_ID);
						FeeTransaction feeTransaction = new FeeTransaction();
						feeTransaction.setFeeDiscountHead(oldFeeStag.getDiscountHead());
						feeTransaction.setAmount(oldFeeStag.getAmount());
						debitFeetransaction.add(feeTransaction);
					}
				}
			}
		}

		//creating base applicable fee and other amenties charges
		Boolean managemetApproval=isManagementApproved(fileNo);
		if(!managemetApproval){

			ApplicableFeeCriteria criteria = CommonUtil.getApplicableFeeCriteriaFromStudentBasicInfo(basicInfo);
			List<ApplicableFeeDetail> applicableFee=getApplicableFeeDetail(criteria);

			for(ApplicableFeeDetail applicableFeeDetail:applicableFee){
				FeeTransaction feeTransaction = new FeeTransaction(null, null, null, applicableFeeDetail.getFeeAmount(), applicableFeeDetail.getFeeDetail());
				debitFeetransaction.add(feeTransaction);
			}

			for(StudentFeeStaging feeStaging:newStagging){
				if("D".equalsIgnoreCase(feeStaging.getDiscountHead().getTransactionType())||"A".equalsIgnoreCase(feeStaging.getDiscountHead().getTransactionType())){
					FeeTransaction feeTransaction = new FeeTransaction(null, null, null, feeStaging.getAmount(), feeStaging.getDiscountHead());
					debitFeetransaction.add(feeTransaction);
				}
			}

			StudentDetail sd=admissionManager.getStudentDtl(fileNo);
			sd.setManagementApproval(true);
			admissionManager.updateStudentDtl(sd);

		}

		
		for(FeeTransaction feeTransaction : creditFeetransaction){

			feeTransaction.setFileNo(fileNo);
			feeTransaction.setBatchId(basicInfo.getBatch().getBatchId());
			feeTransaction.setSessionId(basicInfo.getSession().getSessionId());
			feeDetailDao.addFeeTransactionCredit(feeTransaction);
		}


		for(FeeTransaction feeTransaction : debitFeetransaction){

			feeTransaction.setFileNo(fileNo);
			feeTransaction.setBatchId(basicInfo.getBatch().getBatchId());
			feeTransaction.setSessionId(basicInfo.getSession().getSessionId());
			feeDetailDao.addFeeTransactionDebit(feeTransaction);
		}

		feeDetailDao.saveStudentFeeStaging(newStagging);
	}

	public void saveStudentFeeStaging(List<StudentFeeStaging> newStagging) {
		logger.info("{} : calling saveStudentFeeStaging method by passing newStagging:{} ",this.getClass().getName(), newStagging);
		feeDetailDao.saveStudentFeeStaging(newStagging);
	}

	public void saveStudentFeeStaging(StudentFeeStaging studentFeeStaging){
		logger.info("{} : calling saveStudentFeeStaging method for file no:{} ",this.getClass().getName(), studentFeeStaging.getFileNo());
		
		Boolean managemetApproval=isManagementApproved(studentFeeStaging.getFileNo());
		if(managemetApproval){
			
				if("D".equalsIgnoreCase(studentFeeStaging.getDiscountHead().getTransactionType()) || "A".equalsIgnoreCase(studentFeeStaging.getDiscountHead().getTransactionType())){
                    StudentBasicInfo basicInfo = admissionManager.getStudentBsInfo(studentFeeStaging.getFileNo()); 					
					FeeTransaction feeTransaction = new FeeTransaction(basicInfo.getBatch().getBatchId(), basicInfo.getSession().getSessionId(), studentFeeStaging.getFileNo(), studentFeeStaging.getAmount(), studentFeeStaging.getDiscountHead());
					addFeeTransactionDebit(feeTransaction);
				}
		}
		
		feeDetailDao.saveStudentFeeStaging(studentFeeStaging);
	}	
	public void deleteStudentFeeStagingbyfileNo(StudentFeeStaging feeStaging){
		logger.info("{} : calling deleteStudentFeeStagingByFileNo method for file no:{} ",this.getClass().getName(), feeStaging.getFileNo());
		feeDetailDao.deleteStudentFeeStagingByFileNo(feeStaging);
	}

	//	public FeeTransaction getFeeTransaction(String fileNo) {
	//		FeeTransaction transaction = null;
	//		transaction = feeDetailDao.getFeeTransaction(fileNo);
	//		return transaction;
	//	}
	//
	//	public void addFeeTransaction(FeeTransaction feeTransaction) {
	//		feeDetailDao.addFeeTransaction(feeTransaction);
	//	}

	@Override
	public void generateDiscountinStagging(Long fileNo) {
		logger.info("{} : calling generateDiscountforStudent method by passing file no:{} ",this.getClass().getName(), fileNo);
		feeDetailDao.generateDiscountforStudent(fileNo);
	}

	public List<FeeTransaction> getDebitedFeeTransaction(Long fileNo) {
		logger.info("{} : calling getDebitedFeeTransaction method by passing file no:{} ",this.getClass().getName(), fileNo);
		List<FeeTransaction> feeTransactions = null;
		feeTransactions = feeDetailDao.getDebitedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionDebit(FeeTransaction feeTransaction) {
		logger.info("{} : calling addFeeTransactionDebit method for file no:{} ",this.getClass().getName(),feeTransaction.getFileNo());	
		feeDetailDao.addFeeTransactionDebit(feeTransaction);

	}

	public List<FeeTransaction> getCreditedFeeTransaction(Long fileNo) {
		logger.info("{} : calling getCreditedFeeTransaction method for file no:{} ",this.getClass().getName(),fileNo);
		List<FeeTransaction> feeTransactions = null;
		feeTransactions = feeDetailDao.getCreditedFeeTransaction(fileNo);
		return feeTransactions;
	}

	public void addFeeTransactionCredit(FeeTransaction feeTransaction) {
		logger.info("{} : calling addFeeTransactionCredit method for file no:{} ",this.getClass().getName(),feeTransaction.getFileNo());	
		feeDetailDao.addFeeTransactionCredit(feeTransaction);

	}

	@Override
	public FeeTransactionAdmissionBean getFeeTransactionDetail(Long fileNo){
		logger.info("{} : calling getCreditedFeeTransaction, getDebitedFeeTransaction and getPreviousSemBalance  method by passing file no:{} ",this.getClass().getName(),fileNo);		
		FeeTransactionAdmissionBean transactionAdmissionBean = new FeeTransactionAdmissionBean();

		List<FeeTransaction> TransactionCredit = feeDetailDao.getCreditedFeeTransaction(fileNo);
		transactionAdmissionBean.setFeeTransactionCredit(TransactionCredit); 	

		List<FeeTransaction> TransactionDebit = feeDetailDao.getDebitedFeeTransaction(fileNo);
		transactionAdmissionBean.setFeeTransactionDebit(TransactionDebit);

		Double previousBalance = feeDetailDao.getPreviousSemBalance(fileNo);
		transactionAdmissionBean.setAmountDiffrence(previousBalance);

		return transactionAdmissionBean;
	}

	public Boolean isManagementApproved(Long fileNo){
		logger.info("{} : calling isManagementApproved method by passing file no:{} ",this.getClass().getName(),fileNo);
		return feeDetailDao.isManagementApproved(fileNo);
	}

	@Override
	public void saveAmenities(StudentFeeStaging studentFeeStaging){
		
	    saveStudentFeeStaging(studentFeeStaging);
	}

}

