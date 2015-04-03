package com.techvisio.einstitution.beans;

public class Centre {
	private Long centreId;
	private String centreName;
	public Long getCentreId() {
		return centreId;
	}
	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	@Override
	public String toString() {
		return "Centre [centreId=" + centreId + ", centreName=" + centreName
				+ "]";
	}


}
