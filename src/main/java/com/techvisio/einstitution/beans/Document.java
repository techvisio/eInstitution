package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT_MASTER")    
public class Document {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Document_Id")
	private Long documentID;
	
	@Column(name = "Document_Name")
	private String documentName;
	
	@Column(name = "Document_Type")
	private String documentType;
	
	public Long getDocumentID() {
		return documentID;
	}
	public void setDocumentID(Long documentID) {
		this.documentID = documentID;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
}
