package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "QUALIFICATION_MASTER") 
public class Qualification extends BasicEntity {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
    @Column(name="Qualification_Id")   
	private Long qualificationId;
	@Column(name="Qualifying_Exam")
	private String qualifyingExam;
	
	public Qualification() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getQualificationId() {
		return qualificationId;
	}
	
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}
	
	
	public String getQualifyingExam() {
		return qualifyingExam;
	}
	
	public void setQualifyingExam(String qulaifyingExam) {
		this.qualifyingExam = qulaifyingExam;
	}

	
}
