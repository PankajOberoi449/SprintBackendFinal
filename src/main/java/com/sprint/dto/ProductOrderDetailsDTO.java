package com.sprint.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.sprint.model.Product;
import com.sprint.model.Store;

import lombok.Data;

@Data
public class ProductOrderDetailsDTO {
	@NotNull(message = "Product cannot be null.")
	private Product product;

	@NotNull(message = "Store cannot be null.")
	private Store store;

	@NotNull(message = "Shipment status cannot be null.")
	private String shipmentStatus;

	@Positive(message = "Total amount must be positive.")
	private BigDecimal totalAmount;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ProductOrderDetailsDTO(@NotNull(message = "Product cannot be null.") Product product,
			@NotNull(message = "Store cannot be null.") Store store,
			@NotNull(message = "Shipment status cannot be null.") String shipmentStatus,
			@Positive(message = "Total amount must be positive.") BigDecimal totalAmount) {
		super();
		this.product = product;
		this.store = store;
		this.shipmentStatus = shipmentStatus;
		this.totalAmount = totalAmount;
	}

	public ProductOrderDetailsDTO() {
		super();
	}
	
}
