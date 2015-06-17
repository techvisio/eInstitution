package com.techvisio.einstitution.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "amenitiescharges")    
public class Amenities extends BasicEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private FeeDiscountHead feeDiscountHead;
private Double charges;

public Double getCharges() {
	return charges;
}
public void setCharges(Double charges) {
	this.charges = charges;
}
public FeeDiscountHead getFeeDiscountHead() {
	return feeDiscountHead;
}
public void setFeeDiscountHead(FeeDiscountHead feeDiscountHead) {
	this.feeDiscountHead = feeDiscountHead;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

	
}
