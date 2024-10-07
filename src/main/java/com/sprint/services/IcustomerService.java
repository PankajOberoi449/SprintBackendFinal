package com.sprint.services;

import java.util.List;

import com.sprint.dto.CustomerOrders;
import com.sprint.dto.CustomerShipment;
import com.sprint.dto.LoginDTO;
import com.sprint.dto.ShipmentStatusCountCustomer;
import com.sprint.exceptions.list.BadRequestException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.model.Customer;

public interface IcustomerService {

	Customer getCustomerById(int customerId);

	List<Customer> getCustomersByFullName(String fullName);

	List<Customer> getAllCustomers();

	Customer addCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	void deleteCustomer(int id) throws BadRequestException;

	Customer getCustomerByEmailAddress(String emailAddress);

	List<ShipmentStatusCountCustomer> getOrderCountByStatus();

	CustomerOrders getCustomerOrders(int customerId);

	CustomerShipment getCustomerShipment(int customerId) throws ResourceNotFoundException;

	LoginDTO customerValidation(String email, String password);

}