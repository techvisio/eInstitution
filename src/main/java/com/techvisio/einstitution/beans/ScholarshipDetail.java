package com.techvisio.einstitution.beans;

import java.util.Date;
import java.util.List;

public class ScholarshipDetail {

	private String fileNo;
	private Double Amount;
	private Long stateId;
	private String remark;
	private Date createdDate;
	private List<ScholarshipPaymentDetail> scholarshipPaymentDetail;
	
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
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
	public List<ScholarshipPaymentDetail> getScholarshipPaymentDetail() {
		return scholarshipPaymentDetail;
	}
	public void setScholarshipPaymentDetail(List<ScholarshipPaymentDetail> scholarshipPaymentDetail) {
		this.scholarshipPaymentDetail = scholarshipPaymentDetail;
	}


}
