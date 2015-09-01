package com.techvisio.einstitution.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROUTE_MAPPING")
public class RouteMapping {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Route_Map_Id")
	private Long routeMapId;
	@ManyToOne
	@JoinColumn(name = "Route_Id")
	private Route route;
	@Column(name = "Stop")
	private String stop;
	@Column(name = "Price")
	private Double price;

	public Long getRouteMapId() {
		return routeMapId;
	}

	public void setRouteMapId(Long routeMapId) {
		this.routeMapId = routeMapId;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
