package com.techvisio.einstitution.beans;

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
	private Long id;
	private Long counsellingId;
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
