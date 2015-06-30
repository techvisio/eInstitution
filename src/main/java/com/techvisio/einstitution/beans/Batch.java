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
@Table(name = "BATCH_MASTER")    
public class Batch extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Batch_Id")
	private Long batchId;
	@Column(name = "Batch")
	private String batch;
    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name="Course_Id")
	private Course course;
	
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
