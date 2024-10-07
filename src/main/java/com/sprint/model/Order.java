package com.sprint.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "order_tms", nullable = false)
	private Timestamp orderTms;

	@ManyToOne(fetch = FetchType.EAGER)//, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne(fetch = FetchType.EAGER)//, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<OrderItem> orderItems;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Order(int orderId, String orderStatus, Timestamp orderTms, Store store, Customer customer,
			List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.orderTms = orderTms;
		this.store = store;
		this.customer = customer;
		this.orderItems = orderItems;
	}

	public Order() {
		super();
	}
    
    
}