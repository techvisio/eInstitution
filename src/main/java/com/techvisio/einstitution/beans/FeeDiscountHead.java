package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FEE_DISCOUNTHEAD_MASTER")    
public class FeeDiscountHead extends BasicEntity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((headId == null) ? 0 : headId.hashCode());
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
		FeeDiscountHead other = (FeeDiscountHead) obj;
		if (headId == null) {
			if (other.headId != null)
				return false;
		} else if (!headId.equals(other.headId))
			return false;
		return true;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Head_Id")
	private Long headId;
	
	@Column(name = "Head")
	private String head;
	
	@Column(name = "Transaction_Type")
	private String transactionType;
	
	@Column(name = "Parent_Type_Id")
	private Long parentId;
	
	@Column(name = "Is_Reoccurring")
	private boolean isReoccurring;
	
	@Column(name = "Refund_Type")
	private String refundType;
	
	
	public Long getHeadId() {
		return headId;
	}
	public void setHeadId(Long headId) {
		this.headId = headId;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public boolean isReoccurring() {
		return isReoccurring;
	}
	public void setReoccurring(boolean isReoccurring) {
		this.isReoccurring = isReoccurring;
	}
	public String getRefundType() {
		return refundType;
	}
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	
}
