package com.techvisio.einstitution.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "qualificationMaster") 
public class Qualification {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;   
	private Long qualificationId;
	private String qulaifyingExam;
	
	@Id
	public Long getQualificationId() {
		return qualificationId;
	}
	
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}
	
	
	public String getQulaifyingExam() {
		return qulaifyingExam;
	}
	
	public void setQulaifyingExam(String qulaifyingExam) {
		this.qulaifyingExam = qulaifyingExam;
	}

	@Override
	public String toString() {
		return "Qualfication [id=" + qualificationId + ", qulaifyingExam=" + qulaifyingExam
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

}
