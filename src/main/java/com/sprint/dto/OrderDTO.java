package com.sprint.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDTO {
	@Min(value = 1, message = "Order ID must be a positive integer")
	private int orderId;

	@Min(value = 1, message = "Customer ID must be a positive integer")
	private int customerId;

	@NotBlank(message = "Order status is required")
	private String orderStatus;

	@NotNull(message = "Order timestamp is required")
	private Timestamp orderTms;

	//@Min(value = 1, message = "Store ID must be a positive integer")
	private int storeId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOrderTms() {
		return orderTms;
	}

	public void setOrderTms(Timestamp orderTms) {
		this.orderTms = orderTms;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public OrderDTO(@Min(value = 1, message = "Order ID must be a positive integer") int orderId,
			@Min(value = 1, message = "Customer ID must be a positive integer") int customerId,
			@NotBlank(message = "Order status is required") String orderStatus,
			@NotNull(message = "Order timestamp is required") Timestamp orderTms, int storeId) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.orderTms = orderTms;
		this.storeId = storeId;
	}

	public OrderDTO() {
		super();
	}
	
}
