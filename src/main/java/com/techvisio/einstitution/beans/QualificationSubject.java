package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUALIFICATION_SUBJECT_DTL") 
public class QualificationSubject {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="Stdnt_Subjct_Id")
	private Long stdntSubjctId;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Subject_Id")
	private Subject subject;
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="Qualification_Id")
	private Qualification qualification;
	@Column(name="File_No")
	private Long fileNo;
	
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	@Column(name="Marks_Obtained")
	private double marksObtained;
	@Column(name="Max_Marks")
	private double maxMarks;
	

	public double getMarksObtained() {
		return marksObtained;
	}
	
	public void setMarksObtained(double marksObtained) {
		this.marksObtained = marksObtained;
	}
	
	
	public double getMaxMarks() {
		return maxMarks;
	}
	
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public Long getStdntSubjctId() {
		return stdntSubjctId;
	}

	public void setStdntSubjctId(Long stdntSubjctId) {
		this.stdntSubjctId = stdntSubjctId;
	}

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
}
