package com.techvisio.einstitution.beans;

import java.util.Arrays;

public class FieldDesc{
	private String id;
	private String type;
	private String Title;
	private String masterDataCode;
	private String[] validValue;
	private boolean mandatoryInd;
	private boolean visible;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isMandatoryInd() {
		return mandatoryInd;
	}
	public void setMandatoryInd(boolean mandatoryInd) {
		this.mandatoryInd = mandatoryInd;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String[] getValidValue() {
		return validValue;
	}
	public void setValidValue(String[] validValue) {
		this.validValue = validValue;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getMasterDataCode() {
		return masterDataCode;
	}
	public void setMasterDataCode(String masterDataCode) {
		this.masterDataCode = masterDataCode;
	}
	@Override
	public String toString() {
		return "FieldDesc [id=" + id + ", type=" + type + ", Title=" + Title
				+ ", masterDataCode=" + masterDataCode + ", validValue="
				+ Arrays.toString(validValue) + ", mandatoryInd="
				+ mandatoryInd + ", visible=" + visible + "]";
	}
	

	
	
}