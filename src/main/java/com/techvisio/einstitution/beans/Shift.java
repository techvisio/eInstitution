package com.techvisio.einstitution.beans;

public class Shift {
	private Long shiftId;
	private String shiftName;
	@Override
	public String toString() {
		return "Shift [shiftId=" + shiftId + ", shiftName=" + shiftName + "]";
	}
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
