package com.techvisio.einstitution.beans;

import java.util.Date;

public class Remark {
	private Long fileNo;
	private String enquiryRemark;
	private Date enquiryRemarkDate;
	private String feeRemark;
	private Date feeRemarkDate;
	private String managementRemark;
	private Date managementRemarkDate;
	
	public String getEnquiryRemark() {
		return enquiryRemark;
	}
	public void setEnquiryRemark(String enquiryRemark) {
		this.enquiryRemark = enquiryRemark;
	}
	public Date getEnquiryRemarkDate() {
		return enquiryRemarkDate;
	}
	public void setEnquiryRemarkDate(Date enquiryRemarkDate) {
		this.enquiryRemarkDate = enquiryRemarkDate;
	}
	public String getFeeRemark() {
		return feeRemark;
	}
	public void setFeeRemark(String feeRemark) {
		this.feeRemark = feeRemark;
	}
	public Date getFeeRemarkDate() {
		return feeRemarkDate;
	}
	public void setFeeRemarkDate(Date feeRemarkDate) {
		this.feeRemarkDate = feeRemarkDate;
	}
	public String getManagementRemark() {
		return managementRemark;
	}
	public void setManagementRemark(String managementRemark) {
		this.managementRemark = managementRemark;
	}
	public Date getManagementRemarkDate() {
		return managementRemarkDate;
	}
	public void setManagementRemarkDate(Date managementRemarkDate) {
		this.managementRemarkDate = managementRemarkDate;
	}
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	@Override
	public String toString() {
		return "Remark [fileNo=" + fileNo + ", enquiryRemark=" + enquiryRemark
				+ ", enquiryRemarkDate=" + enquiryRemarkDate + ", feeRemark="
				+ feeRemark + ", feeRemarkDate=" + feeRemarkDate
				+ ", managementRemark=" + managementRemark
				+ ", managementRemarkDate=" + managementRemarkDate + "]";
	}
	
	
	
}
