package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_FEE_STAGING")

public class StudentFeeStaging extends BasicEntity {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "feeStgingId")
	private Long feeStgingId;
	
	@Column(name="File_No")
	private Long fileNo;
	
	@OneToOne
	@JoinColumn(name="Head_Id")
	private FeeDiscountHead discountHead;
	@Column(name = "Amount")
	private Double amount;
	@Column(name = "Approved")
	private boolean approved;
	@Column(name = "Is_Fee_Generatated")
	private boolean feeGenerated;
	@Column(name = "Is_Reoccuring")
	private boolean reoccuring;
	@Column(name = "Is_Conditional")
	private boolean conditional;
	
	
	
	public FeeDiscountHead getDiscountHead() {
		return discountHead;
	}
	public void setDiscountHead(FeeDiscountHead discountHead) {
		this.discountHead = discountHead;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isFeeGenerated() {
		return feeGenerated;
	}
	public void setFeeGenerated(boolean feeGenerated) {
		this.feeGenerated = feeGenerated;
	}
	public boolean isReoccuring() {
		return reoccuring;
	}
	public void setReoccuring(boolean isReoccuring) {
		this.reoccuring = isReoccuring;
	}
	
	public boolean isConditional() {
		return conditional;
	}
	public void setConditional(boolean isConditional) {
		this.conditional = isConditional;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discountHead == null) ? 0 : discountHead.hashCode());
		result = prime * result + ((getFileNo() == null) ? 0 : getFileNo().hashCode());
		return result;
	}
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentFeeStaging other = (StudentFeeStaging) obj;
		if (discountHead == null) {
			if (other.discountHead != null)
				return false;
		} else if (!discountHead.equals(other.discountHead))
			return false;
		if (getFileNo() == null) {
			if (other.getFileNo() != null)
				return false;
		} else if (!getFileNo().equals(other.getFileNo()))
			return false;
		return true;
	}
	public Long getFeeStgingId() {
		return feeStgingId;
	}
	public void setFeeStgingId(Long feeStgingId) {
		this.feeStgingId = feeStgingId;
	}

	
	
}

