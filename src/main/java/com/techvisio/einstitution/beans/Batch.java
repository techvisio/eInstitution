package com.techvisio.einstitution.beans;

import javax.persistence.Column;
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
	@Column(name = "Id")
	private Long id;
	@Column(name = "Batch_Id")
	private Long batchId;
	@Column(name = "Batch")
	private String batch;
	@Column(name = "Course_Id")
	private Long courseId;
	@Column(name = "Prev_Batch_Id")
	private Long prevBatchId;
	@Column(name = "Next_Batch_Id")
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
