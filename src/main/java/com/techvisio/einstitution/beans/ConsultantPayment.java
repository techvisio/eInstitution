package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "consultantpaymentdetail")    
public class ConsultantPayment extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "Amount")
	private Double amount;
	
	@Column(name = "pay_date")
    private Date payDate;
	
	@Column(name = "File_No")
    private Long fileNo;
	
	@Column(name = "Consultant_Id")
    private Long consultantId;
	
    public Double getAmount() {
		return amount;
	}
	
    public void setAmount(Double amount) {
		this.amount = amount;
	}
	
    
    public Date getPayDate() {
		return payDate;
	}
	
    public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	@Override
	public String toString() {
		return "ConsultantPaymentDtl [amount=" + amount + ", payDate="
				+ payDate + ", fileNo=" + fileNo + "]";
	}

	public Long getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
