package com.techvisio.einstitution.beans;

public class Qualification {

	private Long id;
	private String qulaifyingExam;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getQulaifyingExam() {
		return qulaifyingExam;
	}
	
	public void setQulaifyingExam(String qulaifyingExam) {
		this.qulaifyingExam = qulaifyingExam;
	}

	@Override
	public String toString() {
		return "Qualfication [id=" + id + ", qulaifyingExam=" + qulaifyingExam
				+ "]";
	}
 

}
