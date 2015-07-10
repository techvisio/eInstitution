package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECT_MASTER")

public class Subject extends BasicEntity {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Subject_Id")
	private Long subjectId;
	@Column(name = "Subject_Name")
	private String subjectName;
	
	
	public Long getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
