package com.techvisio.einstitution.beans;

import java.util.List;

import com.techvisio.einstitution.db.impl.BasicDetailBeanEntity;

public class ManagementAdmissionBean extends BasicDetailBeanEntity {
private List<StudentFeeStaging> stagingFee;
private ScholarshipDetail scholarship;
public List<StudentFeeStaging> getStagingFee() {
	return stagingFee;
}
public void setStagingFee(List<StudentFeeStaging> stagingFee) {
	this.stagingFee = stagingFee;
}
public ScholarshipDetail getScholarship() {
	return scholarship;
}
public void setScholarship(ScholarshipDetail scholarship) {
	this.scholarship = scholarship;
}

}
