<<<<<<< HEAD
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
}
=======
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
}
>>>>>>> branch 'master' of https://github.com/techvisio/eInstitution
