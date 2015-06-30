package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CODE_MAPPING_MASTER")    
public class CodeMapping extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Code_Map_Id")
	private Long codeMapId;
	@Column(name = "Name")
	private String name;
	@Column(name = "Code")
	private String code;
	@Column(name = "Description")
	private String description;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CodeMapping [name=" + name + ", code=" + code
				+ ", description=" + description + "]";
	}
	public Long getCodeMapId() {
		return codeMapId;
	}
	public void setCodeMapId(Long codeMapId) {
		this.codeMapId = codeMapId;
	}
	
	
	

}
