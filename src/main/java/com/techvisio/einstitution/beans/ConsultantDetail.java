package com.techvisio.einstitution.beans;

import java.sql.Date;
import java.util.List;

public class ConsultantDetail {

	private String fileNo;
	private Long consultantId;
	private boolean consultancyAgreed; 
	private String paymentMode;
	private Double amountToPay; 
	private Date dueDate;
    private String remarks;
	private List<ConsultantPaymentDtl> consultantPaymentDetail;
    private List<ConsultantPaymentCriteria> consultantPaymentCriterias;
	
	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}



	public Double getAmountToPay() {
		return amountToPay;
	}


	public void setAmountToPay(Double amountToPay) {
		this.amountToPay = amountToPay;
	}



	public java.util.Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isConsultancyAgreed() {
		return consultancyAgreed;
	}

	public void setConsultancyAgreed(boolean consultancyAgreed) {
		this.consultancyAgreed = consultancyAgreed;
	}

	public Long getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}

	public List<ConsultantPaymentDtl> getConsultantPaymentDetail() {
		return consultantPaymentDetail;
	}

	public void setConsultantPaymentDetail(List<ConsultantPaymentDtl> consultantPaymentDetail) {
		this.consultantPaymentDetail = consultantPaymentDetail;
	}


	public List<ConsultantPaymentCriteria> getConsultantPaymentCriterias() {
		return consultantPaymentCriterias;
	}

	public void setConsultantPaymentCriterias(
			List<ConsultantPaymentCriteria> consultantPaymentCriterias) {
		this.consultantPaymentCriterias = consultantPaymentCriterias;
	}

	@Override
	public String toString() {
		return "ConsultantDetail [fileNo=" + fileNo + ", consultantId="
				+ consultantId + ", consultancyAgreed=" + consultancyAgreed
				+ ", paymentMode=" + paymentMode + ", amountToPay="
				+ amountToPay + ", dueDate=" + dueDate
				+ ", consultantPaymentDetail=" + consultantPaymentDetail
				+ ", consultantPaymentCriterias=" + consultantPaymentCriterias
				+ "]";
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
