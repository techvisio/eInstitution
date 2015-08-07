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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	private Centre centre;
	@ManyToOne
	@JoinColumn(name="Shift_Id")
	private Shift shift;
	@ManyToOne
	@JoinColumn(name="Session_Id")
	private Session session;

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
	public Centre getCentre() {
		return centre;
	}
	public void setCentre(Centre centre) {
		this.centre = centre;
	}
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	@Override
	public String toString() {
		return "ApplicableFeeDetail [feeDetailId=" + feeDetailId + ", course="
				+ course + ", branch=" + branch + ", feeDetail=" + feeDetail
				+ ", feeAmount=" + feeAmount + ", centre=" + centre
				+ ", shift=" + shift + ", session=" + session + "]";
	}

}
