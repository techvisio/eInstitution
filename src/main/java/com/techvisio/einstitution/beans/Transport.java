package com.techvisio.einstitution.beans;

public class Transport {

	private String routeCode;
	private String description;
	private String threshold;
	private Double price;

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Transport [routeCode=" + routeCode + ", description="
				+ description + ", threshold=" + threshold + ", price=" + price
				+ "]";
	}
}
