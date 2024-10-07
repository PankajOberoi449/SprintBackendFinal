package com.sprint.dto;

import javax.validation.constraints.NotNull;

import com.sprint.model.Product;
import com.sprint.model.Store;

import lombok.Data;

@Data
public class InventoryStoreProductStatusDTO {
	@NotNull(message = "Product cannot be null.")
	private Product product;

	@NotNull(message = "Store cannot be null.")
	private Store store;

	@NotNull(message = "Order status cannot be null.")
	private String orderStatus;

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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public InventoryStoreProductStatusDTO(@NotNull(message = "Product cannot be null.") Product product,
			@NotNull(message = "Store cannot be null.") Store store,
			@NotNull(message = "Order status cannot be null.") String orderStatus) {
		super();
		this.product = product;
		this.store = store;
		this.orderStatus = orderStatus;
	}

	public InventoryStoreProductStatusDTO() {
		super();
	}
	
}
