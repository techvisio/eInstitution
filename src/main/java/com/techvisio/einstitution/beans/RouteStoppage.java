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
@Table(name = "ROUTE_STOPPAGE")
public class RouteStoppage {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "Route_Stop_Id")
	private Long routeStopId;
	@ManyToOne
	@JoinColumn(name = "Route_Id")
	private Route route;
	@Column(name = "Stop")
	private String stop;
	@Column(name = "Price")
	private Double price;

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

	public Long getRouteStopId() {
		return routeStopId;
	}

	public void setRouteStopId(Long routeStopId) {
		this.routeStopId = routeStopId;
	}

	
}
