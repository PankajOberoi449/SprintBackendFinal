package com.sprint.dto;

import com.sprint.model.Customer;
import com.sprint.model.Product;
import com.sprint.model.Store;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class InventoryStoreProductCustomerDTO {

	@NotNull(message = "Product cannot be null.")
	private Product product;

	@NotNull(message = "Store cannot be null.")
	private Store store;

	@NotNull(message = "Customer cannot be null.")
	private Customer customer;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public InventoryStoreProductCustomerDTO(@NotNull(message = "Product cannot be null.") Product product,
			@NotNull(message = "Store cannot be null.") Store store,
			@NotNull(message = "Customer cannot be null.") Customer customer) {
		super();
		this.product = product;
		this.store = store;
		this.customer = customer;
	}

	public InventoryStoreProductCustomerDTO() {
		super();
	}
	
}
