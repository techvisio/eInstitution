package com.techvisio.einstitution.beans;

public class AddressDetail {

	private String houseNo;
	private String locality;
	private String landmark;
	private String district;
	private String city;
	private int pincode;
	private String State;
	private Long fileNo;
	private String addressType;
	
	
	public String getHouseNo() {
		return houseNo;
	}
	
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	
	public String getLandmark() {
		return landmark;
	}
	
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public int getPincode() {
		return pincode;
	}
	
	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
	
	public String getState() {
		return State;
	}
	
	public void setState(String state) {
		State = state;
	}
	
	public String getAddressType() {
		return addressType;
	}
	
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "AddressDetail [houseNo=" + houseNo + ", locality=" + locality
				+ ", landmark=" + landmark + ", district=" + district
				+ ", city=" + city + ", pincode=" + pincode + ", State="
				+ State + ", fileNo=" + fileNo + ", addressType=" + addressType
				+ "]";
	}
	
}
