package com.techvisio.einstitution.beans;

public class AvailableTransport {

	private String RouteCode;
	private int reservedTransport;
	private int availableTransport;
	private String threshold;
	private Double price;


	public int getReservedTransport() {
		return reservedTransport;
	}

	public void setReservedTransport(int reservedTransport) {
		this.reservedTransport = reservedTransport;
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
		return RouteCode;
	}

	public void setRouteCode(String typeCode) {
		this.RouteCode = typeCode;
	}

	public int getAvailableTransport() {
		return availableTransport;
	}

	public void setAvailableTransport(int availableTransport) {
		this.availableTransport = availableTransport;
	}

	@Override
	public String toString() {
		return "AvailableTransport [typeCode=" + RouteCode
				+ ", reservedTransport=" + reservedTransport
				+ ", availableTransport=" + availableTransport + ", threshold="
				+ threshold + ", price=" + price + "]";
	}
}
