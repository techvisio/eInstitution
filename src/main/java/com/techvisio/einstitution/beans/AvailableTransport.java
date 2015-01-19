package com.techvisio.einstitution.beans;

public class AvailableTransport {

	private int reservedTransport;
	private int allocatedTransport;
	private int threshold;
	private Double price;


	public int getReservedTransport() {
		return reservedTransport;
	}

	public void setReservedTransport(int reservedTransport) {
		this.reservedTransport = reservedTransport;
	}

	public int getAllocatedTransport() {
		return allocatedTransport;
	}

	public void setAllocatedTransport(int allocatedTransport) {
		this.allocatedTransport = allocatedTransport;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
