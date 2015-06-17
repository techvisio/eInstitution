package com.techvisio.einstitution.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "consultantdetail")    
public class ConsultantDetail extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long fileNo;
	private Consultant consultant=new Consultant();
	private boolean consultancyAgreed; 
	private String paymentMode;
	private Double amountToPay; 
	private Date dueDate;
    private String remarks;
	private List<ConsultantPayment> consultantPaymentDetail=new ArrayList<ConsultantPayment>();
    private List<ConsultantPaymentCriteria> consultantPaymentCriterias=new ArrayList<ConsultantPaymentCriteria>();
    
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

	public List<ConsultantPayment> getConsultantPaymentDetail() {
		return consultantPaymentDetail;
	}

	public void setConsultantPaymentDetail(List<ConsultantPayment> consultantPaymentDetail) {
		this.consultantPaymentDetail = consultantPaymentDetail;
	}


	public List<ConsultantPaymentCriteria> getConsultantPaymentCriterias() {
		return consultantPaymentCriterias;
	}

	public void setConsultantPaymentCriterias(
			List<ConsultantPaymentCriteria> consultantPaymentCriterias) {
		this.consultantPaymentCriterias = consultantPaymentCriterias;
	}

	
	public Long getFileNo() {
		return fileNo;
	}


	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Consultant getConsultant() {
		return consultant;
	}


	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

}
