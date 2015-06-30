package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AMENITIES_CHARGES")    
public class Amenities extends BasicEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Amenity_Id")
	private Long amenityId;
	@ManyToOne
	@JoinColumn(name="Head_Id")
	private FeeDiscountHead feeDiscountHead;
	@Column(name="Charges")
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
	public Long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}


}
