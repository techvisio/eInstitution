package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "castecategorymaster")    
public class CasteCategory extends BasicEntity  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	private Long  categoryId;
	private String CategoryName;

	
	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long id) {
		this.categoryId = id;
	}
	
	
	public String getCategoryName() {
		return CategoryName;
	}
	
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CasteCategory [id=" + categoryId + ", CategoryName=" + CategoryName
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
