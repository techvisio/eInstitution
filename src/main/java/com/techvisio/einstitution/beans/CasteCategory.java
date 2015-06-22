package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CASTECATEGORY_MASTER")    
public class CasteCategory extends BasicEntity  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Categry_Id")
	private Long  categoryId;
	@Column(name="Category_Name")
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

}
