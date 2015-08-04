package com.techvisio.einstitution.beans;

import java.util.Date;

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
@Table(name = "STUDENT_ACTIVITY")    
public class StudentActivity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Student_Activity_Id")
	private Long studentActivityId;
	@ManyToOne
	@JoinColumn(name="Activity_Name")
	private Activity activity;
	@Column(name="File_No")
	private Long fileNo;
	
	public Activity getActivity() {
		return activity;
	}
	public Long getStudentActivityId() {
		return studentActivityId;
	}
	public void setStudentActivityId(Long studentActivityId) {
		this.studentActivityId = studentActivityId;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

}
