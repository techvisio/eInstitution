package com.techvisio.einstitution.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
@Entity
@Table(name = "ADMISSION_CONSULTANT_DTL")    
public class AdmissnConsltntDtl extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Consltant_Dtl_Id")
	private Long consltantDtlId;
	@Column(name="File_No")
	private Long fileNo;
	@ManyToOne
	@JoinColumn(name="Consltant_Id")
	private Consultant consultant = new Consultant();
	@Column(name="Is_Consultancy_Agreed")
	private boolean consultancyAgreed;
	@Column(name="Payment_Mode")
	private String paymentMode;
	@Column(name="Amount_To_Pay")
	private Double amountToPay; 
	@Column(name="Due_Date")
	private Date dueDate;
	@Column(name="Remarks")
    private String remarks;
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="Consltant_Dtl_Id")
	private List<ConsultantPayment> consultantPaymentDetail=new ArrayList<ConsultantPayment>();
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="Consltant_Dtl_Id")
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


	public Long getConsltantDtlId() {
		return consltantDtlId;
	}


	public void setConsltantDtlId(Long consltantDtlId) {
		this.consltantDtlId = consltantDtlId;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date date) {
		this.dueDate = date;
	}

}
