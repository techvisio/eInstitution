package com.techvisio.einstitution.beans;

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
@Table(name = "ADMISSION_DISCOUNT_DTL") 
public class AdmissionDiscount extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Discount_Dtl_Id")
	private Long discountDtlId;
	@Column(name="File_No")
	private Long fileNo;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Head_Id")
	private FeeDiscountHead discountHead;
	public FeeDiscountHead getDiscountHead() {
		return discountHead;
	}

	public void setDiscountHead(FeeDiscountHead discountHead) {
		this.discountHead = discountHead;
	}

	@Column(name="Amount")
	private double amount;
	@Column(name="Percent")
	private float percent;
	@Column(name="Discout_Type")
	private String discountType;
	
	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	public float getPercent() {
		return percent;
	}
	
	public void setPercent(float percent) {
		this.percent = percent;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	public Long getDiscountDtlId() {
		return discountDtlId;
	}

	public void setDiscountDtlId(Long discountDtlId) {
		this.discountDtlId = discountDtlId;
	}
	
	
}
