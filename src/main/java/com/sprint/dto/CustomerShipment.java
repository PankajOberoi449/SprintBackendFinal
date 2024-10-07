package com.sprint.dto;

import java.util.List;

import com.sprint.model.Customer;
import com.sprint.model.Shipment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerShipment {
	private final Customer customer;
	private final List<Shipment> shipment;
	public Customer getCustomer() {
		return customer;
	}
	public List<Shipment> getShipment() {
		return shipment;
	}
	public CustomerShipment(Customer customer, List<Shipment> shipment) {
		super();
		this.customer = customer;
		this.shipment = shipment;
	}
	
}
