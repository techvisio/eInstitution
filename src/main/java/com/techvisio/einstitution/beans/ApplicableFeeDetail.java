package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FEE_DETAIL_MASTER")   
public class ApplicableFeeDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Fee_Detail_Id")
	private Long feeDetailId;	
	@ManyToOne
	@JoinColumn(name="Course_Id")	
	private Course course;
	@ManyToOne
	@JoinColumn(name="Branch_Id")
	private Branch branch;
	@ManyToOne
	@JoinColumn(name="Head_Id")
	private FeeDiscountHead feeDetail;
	@Column(name="Fee_Amount")
	private Double feeAmount;
	@ManyToOne
	@JoinColumn(name="Centre_Id")
	private Centre centreId;
	@ManyToOne
	@JoinColumn(name="Shift_Id")
	private Shift shiftId;
	@ManyToOne
	@JoinColumn(name="Sessiont_Id")
	private Session sessionId;

	public Long getFeeDetailId() {
		return feeDetailId;
	}
	public void setFeeDetailId(Long feeDetailId) {
		this.feeDetailId = feeDetailId;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public FeeDiscountHead getFeeDetail() {
		return feeDetail;
	}
	public void setFeeDetail(FeeDiscountHead feeDetail) {
		this.feeDetail = feeDetail;
	}
	public Double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public Centre getCentreId() {
		return centreId;
	}
	public void setCentreId(Centre centreId) {
		this.centreId = centreId;
	}
	public Shift getShiftId() {
		return shiftId;
	}
	public void setShiftId(Shift shiftId) {
		this.shiftId = shiftId;
	}
	public Session getSessionId() {
		return sessionId;
	}
	public void setSessionId(Session sessionId) {
		this.sessionId = sessionId;
	}

}
