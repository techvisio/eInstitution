package com.techvisio.einstitution.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SCHOLARSHIP_DETAIL")
public class Scholarship extends BasicEntity{

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Stdnt_Schlarshp_Id")
	private Long stdntSchlarshpId;
	@Column(name="File_No")
	private Long fileNo; 
	@Column(name = "Amount")
	private Double amount;
	@Column(name = "State_Id")
	private Long stateId;
	@Column(name = "Remark")
	private String remark;
	
	@Column(name = "Is_Approved")
    private boolean approved;
	@Column(name = "Is_Reoccuring")
    private boolean reoccurring;
	@Column(name = "Is_Conditional")
    private boolean conditional;
	@Column(name = "Parent_Income")
    private Double parentIncome;
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Stdnt_Schlarshp_Id")
    private List<ScholarshipPayment> scholarshipPaymentDetail;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<ScholarshipPayment> getScholarshipPaymentDetail() {
		return scholarshipPaymentDetail;
	}
	public void setScholarshipPaymentDetail(List<ScholarshipPayment> scholarshipPaymentDetail) {
		this.scholarshipPaymentDetail = scholarshipPaymentDetail;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isReoccurring() {
		return reoccurring;
	}
	public void setReoccurring(boolean isReoccurring) {
		this.reoccurring = isReoccurring;
	}
	public Double getParentIncome() {
		return parentIncome;
	}
	public void setParentIncome(Double parentIncome) {
		this.parentIncome = parentIncome;
	}
	public boolean isConditional() {
		return conditional;
	}
	public void setConditional(boolean conditional) {
		this.conditional = conditional;
	}
	
	public Long getStdntSchlarshpId() {
		return stdntSchlarshpId;
	}
	public void setStdntSchlarshpId(Long istdntSchlarshpIdd) {
		this.stdntSchlarshpId = istdntSchlarshpIdd;
	}
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

}
