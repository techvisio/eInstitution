package com.techvisio.einstitution.beans;

public class FeeHead {
	
	private Long id;
	private String feeHead;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getFeeHead() {
		return feeHead;
	}
	
	public void setFeeHead(String feeHead) {
		this.feeHead = feeHead;
	}

	@Override
	public String toString() {
		return "FeeHead [id=" + id + ", feeHead=" + feeHead + "]";
	}

}
