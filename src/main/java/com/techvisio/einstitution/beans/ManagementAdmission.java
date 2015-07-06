package com.techvisio.einstitution.beans;

import java.util.List;

import com.techvisio.einstitution.db.impl.BasicDetailBeanEntity;

public class ManagementAdmission extends BasicDetailBeanEntity {
private List<StudentFeeStaging> stagingFee;
private Scholarship scholarship; 
private List<ApplicableFeeDetail> applicableFeeDetails;
public List<StudentFeeStaging> getStagingFee() {
	return stagingFee;
}
public void setStagingFee(List<StudentFeeStaging> stagingFee) {
	this.stagingFee = stagingFee;
}
public Scholarship getScholarship() {
	return scholarship;
}
public void setScholarship(Scholarship scholarship) {
	this.scholarship = scholarship;
}
public List<ApplicableFeeDetail> getApplicableFeeDetails() {
	return applicableFeeDetails;
}
public void setApplicableFeeDetails(
		List<ApplicableFeeDetail> applicableFeeDetails) {
	this.applicableFeeDetails = applicableFeeDetails;
}

}
