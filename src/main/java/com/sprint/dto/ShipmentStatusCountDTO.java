package com.sprint.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ShipmentStatusCountDTO {
	@NotNull(message = "Shipment status cannot be null.")
	private String shipmentStatus;

	@Positive(message = "Total products sold must be positive.")
	private long totalProductsSold;

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public long getTotalProductsSold() {
		return totalProductsSold;
	}

	public void setTotalProductsSold(long totalProductsSold) {
		this.totalProductsSold = totalProductsSold;
	}

	public ShipmentStatusCountDTO(@NotNull(message = "Shipment status cannot be null.") String shipmentStatus,
			@Positive(message = "Total products sold must be positive.") long totalProductsSold) {
		super();
		this.shipmentStatus = shipmentStatus;
		this.totalProductsSold = totalProductsSold;
	}

	public ShipmentStatusCountDTO() {
		super();
	}
	
}
