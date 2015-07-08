package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHIFT_MASTER")
public class Shift extends BasicEntity {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Shift_Id")
	private Long shiftId;
	@Column(name = "Shift_Name")
	private String shiftName;
	
	public Long getShiftId() {
		return shiftId;
	}
	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	
}
