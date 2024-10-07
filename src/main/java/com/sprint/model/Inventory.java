package com.sprint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {
    
    // Unique identifier for each inventory record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inventory_id")
    private int inventoryId;

    // Many-to-One relationship with the Store entity, using store_id as a foreign key
    // CascadeType.PERSIST ensures that when a new inventory is created, the associated store is also persisted if not already
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "store_id")   
    private Store store;

    // Field representing the quantity of products in inventory
    @Column(name = "product_inventory")
    private int productInventory;

    // Many-to-One relationship with the Product entity, using product_id as a foreign key
    // CascadeType.ALL ensures that any changes made to the Inventory will cascade to the associated Product (including delete)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
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

	public Inventory(int inventoryId, Store store, int productInventory, Product products) {
		super();
		this.inventoryId = inventoryId;
		this.store = store;
		this.productInventory = productInventory;
		this.products = products;
	}

	public Inventory() {
		super();
	}
    

}
