package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "CONSULTANT_PAYMENT_DETAIL")    
public class ConsultantPayment extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Consltnt_Pymnt_Id")
	private Long consltntPymntId;
	
	@Column(name = "Amount")
	private Double amount;
	
	@Column(name = "pay_date")
    private Date payDate;
	
	@Column(name = "File_No")
    private Long fileNo;
	
	@Column(name = "Consltant_Dtl_Id")
    private Long consltantDtlId;
	
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

	public Long getConsltntPymntId() {
		return consltntPymntId;
	}

	public void setConsltntPymntId(Long consltntPymntId) {
		this.consltntPymntId = consltntPymntId;
	}

	public Long getConsltantDtlId() {
		return consltantDtlId;
	}

	public void setConsltantDtlId(Long consltantDtlId) {
		this.consltantDtlId = consltantDtlId;
	}

}
