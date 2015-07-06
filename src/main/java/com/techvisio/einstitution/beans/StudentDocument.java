package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_DOCUMENTS")    

public class StudentDocument {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Student_Doc_Id")
	private Long studentDocumentId;
	
	@Column(name = "File_No")
	private Long fileNo;
	
	@OneToOne
	@JoinColumn(name = "Document_Id")
	private Document document;

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	

	public Long getStudentDocumentId() {
		return studentDocumentId;
	}

	public void setStudentDocumentId(Long studentDocumentId) {
		this.studentDocumentId = studentDocumentId;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
