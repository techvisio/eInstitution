package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "counsellingmaster")    

public class CounsellingBody extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "Counselling_Id")
	private Long counsellingId;
	
	@Column(name = "Counselling_Body")
	private String CousellingBody;
	
	
	public Long getCousellingId() {
		return counsellingId;
	}
	
	public void setCousellingId(Long id) {
		this.counsellingId = id;
	}
	
	
	public String getCousellingBody() {
		return CousellingBody;
	}
	
	public void setCousellingBody(String cousellingBody) {
		CousellingBody = cousellingBody;
	}

	@Override
	public String toString() {
		return "CounsellingBody [id=" + counsellingId + ", CousellingBody="
				+ CousellingBody + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
