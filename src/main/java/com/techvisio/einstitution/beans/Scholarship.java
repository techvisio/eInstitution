package com.techvisio.einstitution.beans;

import java.util.Date;
import java.util.List;

public class Scholarship {

	private Long fileNo;
	private Double amount;
	private Long stateId;
	private String remark;
	private Date createdDate;
    private boolean approved; 
    private boolean reoccurring;
    private boolean conditional;
    private Double parentIncome;
    private List<ScholarshipPayment> scholarshipPaymentDetail;
	
	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createdDate;
	}
	public void setCreateDate(Date createDate) {
		this.createdDate = createDate;
	}
	public List<ScholarshipPayment> getScholarshipPaymentDetail() {
		return scholarshipPaymentDetail;
	}
	public void setScholarshipPaymentDetail(List<ScholarshipPayment> scholarshipPaymentDetail) {
		this.scholarshipPaymentDetail = scholarshipPaymentDetail;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isReoccurring() {
		return reoccurring;
	}
	public void setReoccurring(boolean isReoccurring) {
		this.reoccurring = isReoccurring;
	}
	public Double getParentIncome() {
		return parentIncome;
	}
	public void setParentIncome(Double parentIncome) {
		this.parentIncome = parentIncome;
	}
	public boolean isConditional() {
		return conditional;
	}
	public void setConditional(boolean conditional) {
		this.conditional = conditional;
	}


}