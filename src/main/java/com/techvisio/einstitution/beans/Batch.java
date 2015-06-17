package com.techvisio.einstitution.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batchmaster")    
public class Batch extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
