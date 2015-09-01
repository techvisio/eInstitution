package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table(name = "STUDENT_DOCUMENTS")    

@JsonIgnoreType
public class StudentDocument extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Student_Doc_Id")
	private Long studentDocumentId;
	
	@Column(name = "File_No")
	private Long fileNo;
	
	@OneToOne
	@JoinColumn(name = "Document_Id")
	private Document document;
	
	@Column(name = "Is_Received")
	private boolean received;

	@Column(name = "Document_No")
	private String documentNo;

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

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}
	
	public String getDocumentNo() {
		return documentNo;
	}
	
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
}
