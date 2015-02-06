package com.techvisio.einstitution.beans;

public class AvailableTransport {

	private String routeCode;
	private int reserved;
	private int available;
	private String threshold;
	private Double price;


	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
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

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	@Override
	public String toString() {
		return "AvailableTransport [routeCode=" + routeCode
				+ ", reserved=" + reserved
				+ ", available=" + available + ", threshold="
				+ threshold + ", price=" + price + "]";
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	
}
