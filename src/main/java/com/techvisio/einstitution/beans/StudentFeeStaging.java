package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studentfeestaging")

public class StudentFeeStaging {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;
	@Column(name = "File_No")
	private Long fileNo;
	@Column(name = "Component_Id")
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
	@Column(name = "Created_Date")
	private String createdDate;
	@Column(name = "Modified_Date")
	private String modifiedDate;
	@Column(name = "Created_By")
	private String createdBy;
	@Column(name = "Updated_By")
	private String updatedBy;
	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
		result = prime * result + ((fileNo == null) ? 0 : fileNo.hashCode());
		return result;
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
		if (fileNo == null) {
			if (other.fileNo != null)
				return false;
		} else if (!fileNo.equals(other.fileNo))
			return false;
		return true;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
}

