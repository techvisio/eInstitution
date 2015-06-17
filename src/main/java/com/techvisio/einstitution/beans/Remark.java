package com.techvisio.einstitution.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AdmissionRemark")
public class Remark {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;	
	@Column(name = "File_No")
	private Long fileNo;
	@Column(name="Remark")
	private String remark;
	@Column(name="Remark")
	private String remarkType;
	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemarkType() {
		return remarkType;
	}
	public void setRemarkType(String remarkType) {
		this.remarkType = remarkType;
	}
	
}
