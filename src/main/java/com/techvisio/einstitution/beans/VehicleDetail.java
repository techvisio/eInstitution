package com.techvisio.einstitution.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "VEHICLE_DETAIL")
public class VehicleDetail {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Vehicle_Id")
	private Long vehicleId;
	@ManyToOne( cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "Type_Id")
	private VehicleType vehicleType;
	@Column(name = "Capacity")
	private String capacity;
	@Column(name = "Vehicle_No")
	private String vehicleNo;
	@Column(name = "Route_Code")
	private String routeCode;

	

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

}
