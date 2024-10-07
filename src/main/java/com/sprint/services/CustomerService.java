package com.sprint.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.dao.CustomerRepository;
import com.sprint.dto.CustomerOrders;
import com.sprint.dto.CustomerShipment;
import com.sprint.dto.LoginDTO;
import com.sprint.dto.ShipmentStatusCountCustomer;
import com.sprint.exceptions.list.BadRequestException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.model.Customer;
import com.sprint.model.Order;
import com.sprint.model.Shipment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service

public class CustomerService implements IcustomerService {

	@Autowired
	private CustomerRepository repo;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	@Transactional
	public Customer addCustomer(Customer customer) {
		Integer lastId = (Integer) entityManager.createQuery("SELECT MAX(c.customerId) FROM Customer c")
				.getSingleResult();
		if (lastId != null) {
			customer.setCustomerId(lastId + 1);
		} else {
			customer.setCustomerId(1); // If there are no records, start with 1
		}
		return repo.save(customer);
	}

	public Customer updateCustomer(Customer customer) {
		Customer cust = repo.findById(customer.getCustomerId()).orElse(null);
		cust.setEmailAddress(customer.getEmailAddress());
		cust.setFullName(customer.getFullName());
		return repo.save(cust);
	}

	public void deleteCustomer(int customerId) throws BadRequestException {
		if (repo.existsById(customerId)) {
			repo.deleteById(customerId);
		} else {
			throw new BadRequestException("Customer with ID " + customerId + " does not exist.");
		}
	}

	public Customer getCustomerByEmailAddress(String emialAddress) {
		return repo.findByEmailAddress(emialAddress).get(0);
	}

	public List<Customer> getCustomersByFullName(String fullName) {
		return repo.findByFullNameContains(fullName);
	}

	public List<ShipmentStatusCountCustomer> getOrderCountByStatus() {
		return repo.getOrderCountByStatus();
	}

	public CustomerOrders getCustomerOrders(int customerId) {
		Customer customer = repo.findById(customerId).orElse(null);
		List<Order> order = repo.getCustomerOrders(customerId);
		return new CustomerOrders(customer, order);
	}

	public CustomerShipment getCustomerShipment(int customerId) throws ResourceNotFoundException {
		// Fetch the customer, and handle the case where the customer is not found
		Customer customer = repo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

		// Fetch the shipments and ensure the list is not null
		List<Shipment> shipment = repo.getCustomerShipments(customerId);
		if (shipment == null)
			shipment = new ArrayList<>();
		return new CustomerShipment(customer, shipment);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return repo.findById(customerId).get();
	}

	@Override
	public LoginDTO customerValidation(String email, String password) {	
		return repo.getCustomerDetails(email,password);
	}

}