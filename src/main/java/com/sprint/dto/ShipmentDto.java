package com.sprint.dto;

import lombok.Data;

@Data
public class ShipmentDto {
 
    private int shipmentId;
    private String deliveryAddress;
    private String shipmentStatus;
    private int storeId; // Assuming you need store ID
    private int customerId; // Assuming you need customer ID
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getShipmentStatus() {
		return shipmentStatus;
	}
	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public ShipmentDto(int shipmentId, String deliveryAddress, String shipmentStatus, int storeId, int customerId) {
		super();
		this.shipmentId = shipmentId;
		this.deliveryAddress = deliveryAddress;
		this.shipmentStatus = shipmentStatus;
		this.storeId = storeId;
		this.customerId = customerId;
	}
	public ShipmentDto() {
		super();
	}
 
    // Constructors, getters, setters (Lombok @Data will generate these)
    
}
