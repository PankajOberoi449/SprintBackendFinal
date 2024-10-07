package com.sprint.dto;

import com.sprint.model.Store;
import com.sprint.model.Product;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class InventoryDTO {

    // Ensures the inventoryId is a positive integer
    @Positive(message = "Inventory ID must be positive.")
    private int inventoryId;

    // Ensures the store object is not null when setting the store property
    @NotNull(message = "Store cannot be null.")
    private Store store;

    // Ensures the productInventory is a positive integer (non-zero quantity)
    @Positive(message = "Product inventory must be positive.")
    private int productInventory;

    // Ensures the product object is not null when setting the products property
    @NotNull(message = "Product cannot be null.")
    private Product products;

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public int getProductInventory() {
		return productInventory;
	}

	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public InventoryDTO(@Positive(message = "Inventory ID must be positive.") int inventoryId,
			@NotNull(message = "Store cannot be null.") Store store,
			@Positive(message = "Product inventory must be positive.") int productInventory,
			@NotNull(message = "Product cannot be null.") Product products) {
		super();
		this.inventoryId = inventoryId;
		this.store = store;
		this.productInventory = productInventory;
		this.products = products;
	}

	public InventoryDTO() {
		super();
	}
    
}
