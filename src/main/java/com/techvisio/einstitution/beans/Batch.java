package com.techvisio.einstitution.beans;

public class Batch {
	private Long batchId;
	private String batch;
	private Long courseId;
	private Long prevBatchId;
	private Long nextBatchId;
	
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getPrevBatchId() {
		return prevBatchId;
	}
	public void setPrevBatchId(Long prevBatchId) {
		this.prevBatchId = prevBatchId;
	}
	public Long getNextBatchId() {
		return nextBatchId;
	}
	public void setNextBatchId(Long nextBatchId) {
		this.nextBatchId = nextBatchId;
	}
	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batch=" + batch + ", courseId="
				+ courseId + ", prevBatchId=" + prevBatchId + ", nextBatchId="
				+ nextBatchId + "]";
	}
	
	
}
