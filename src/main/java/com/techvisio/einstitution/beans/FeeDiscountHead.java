package com.techvisio.einstitution.beans;

public class FeeDiscountHead {

	private Long headId;
	private String head;
	private String transactionType;
	private Long parentId;
	private boolean isReoccurring;
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
