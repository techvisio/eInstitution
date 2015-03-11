package com.techvisio.einstitution.beans;

import java.util.List;

public class ManagementAdmissionBean {
private StudentBasicInfo basicInfo;
private List<StudentFeeStaging> stagingFee;
private ScholarshipDetail scholarship;
public StudentBasicInfo getBasicInfo() {
	return basicInfo;
}
public void setBasicInfo(StudentBasicInfo basicInfo) {
	this.basicInfo = basicInfo;
}
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
