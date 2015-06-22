package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNSELLING_MASTER")    

public class CounsellingBody extends BasicEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Counselling_Id")
	private Long counsellingId;
	
	@Column(name = "Counselling_Body")
	private String CousellingBody;
	
	
	public Long getCounsellingId() {
		return counsellingId;
	}
	
	public void setCounsellingId(Long id) {
		this.counsellingId = id;
	}
	
	
	public String getCounsellingBody() {
		return CousellingBody;
	}
	
	public void setCounsellingBody(String cousellingBody) {
		CousellingBody = cousellingBody;
	}

}
