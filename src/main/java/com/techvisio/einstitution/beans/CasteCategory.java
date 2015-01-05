package com.techvisio.einstitution.beans;

public class CasteCategory {

	private Long  id;
	private String CategoryName;

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getCategoryName() {
		return CategoryName;
	}
	
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CasteCategory [id=" + id + ", CategoryName=" + CategoryName
				+ "]";
	}



}
