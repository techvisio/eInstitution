package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMISSION_REMARK")
public class Remark extends BasicEntity{
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Remark_Id")
	private Long remarkId;	
	@Column(name = "File_No")
	private Long fileNo;
	@Column(name="Remark")
	private String remark;
	@Column(name="Remark_Type")
	private String remarkType;
	
	public Long getFileNo() {
		return fileNo;
	}
	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}
	
	public Long getRemarkId() {
		return remarkId;
	}
	public void setRemarkId(Long remarkId) {
		this.remarkId = remarkId;
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
