package com.sprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.dto.CustomerOrders;
import com.sprint.dto.CustomerShipment;
import com.sprint.dto.ShipmentStatusCountCustomer;
import com.sprint.exceptions.list.BadRequestException;
import com.sprint.exceptions.list.InternalServerErrorException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.model.Customer;
import com.sprint.services.IcustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	private IcustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(
			@RequestParam(name = "name", required = false) String fullName) throws ResourceNotFoundException {
		if (fullName != null) {
			List<Customer> customersByFullName = customerService.getCustomersByFullName(fullName);
			if (customersByFullName.isEmpty()) {
				throw new ResourceNotFoundException("Name is not found");
			}
			return ResponseEntity.ok(customersByFullName);
		} else {
			List<Customer> allCustomers = customerService.getAllCustomers();
			if (allCustomers.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allCustomers);
			}
			return ResponseEntity.ok(allCustomers);
		}
	}

	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) throws BadRequestException {
		if (customer.getEmailAddress() == null || customer.getFullName() == null || customer.getEmailAddress().isEmpty()
				|| customer.getFullName().isEmpty()) {
			throw new BadRequestException("Invalid request. Please provide valid customer data.");
		}

		customerService.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.OK).body("Record Created Successfully");
	}

	@PutMapping
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) throws BadRequestException {
		if (customer.getCustomerId() < 1 || customer.getEmailAddress() == null || customer.getFullName() == null) {
			throw new BadRequestException("Invalid request. Please provide valid customer data for updating.");
		}
		customerService.updateCustomer(customer);
		return new ResponseEntity<String>("Record Updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) throws BadRequestException {
		customerService.deleteCustomer(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	@GetMapping("/cust/{id}")
	public ResponseEntity<Customer> getCustomersById(@PathVariable("id") int customerId)
			throws ResourceNotFoundException {
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null)
			throw new ResourceNotFoundException("Customer with the provided ID not found.");

		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping("/{email}")
	public ResponseEntity<Customer> getCustomersByEmailAddress(@PathVariable("email") String emailAddress)
			throws ResourceNotFoundException {
		Customer customers = customerService.getCustomerByEmailAddress(emailAddress);
		if (customers == null)
			throw new ResourceNotFoundException("Customer with the provided email ID not found.");
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/fullname/{name}")
	public ResponseEntity<List<Customer>> getCustomersByFullName(@PathVariable("name") String fullName)
			throws ResourceNotFoundException {
		List<Customer> customers = customerService.getCustomersByFullName(fullName);
		if (customers.isEmpty()) {
			throw new ResourceNotFoundException("Customer with the provided name wildcard not found.");
		}
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/shipment/status")
	public ResponseEntity<List<ShipmentStatusCountCustomer>> getOrderCountByStatus() {
		List<ShipmentStatusCountCustomer> shipmentStatusCount = customerService.getOrderCountByStatus();

		if (shipmentStatusCount.isEmpty()) {
			throw new InternalServerErrorException(
					"An internal server error occurred while fetching shipment status count.");
		}

		return new ResponseEntity<List<ShipmentStatusCountCustomer>>(shipmentStatusCount, HttpStatus.OK);
	}

	@GetMapping("/{custid}/order")
	public ResponseEntity<CustomerOrders> getCustomerOrders(@PathVariable("custid") int customerId)
			throws ResourceNotFoundException {
		CustomerOrders customerOrders = customerService.getCustomerOrders(customerId);
		if (customerOrders.getCustomer() == null && customerOrders.getOrder().isEmpty()) {
			throw new ResourceNotFoundException("Orders for the specified customer ID not found.");
		}
		return ResponseEntity.ok(customerOrders);
	}

	@GetMapping("/{custid}/shipment")
	public ResponseEntity<CustomerShipment> getCustomerShipment(@PathVariable("custid") int customerId)
			throws ResourceNotFoundException {
		CustomerShipment customerShipment = customerService.getCustomerShipment(customerId);
		if (customerShipment.getCustomer() == null) {
			throw new ResourceNotFoundException("Shipment history for the specified customer ID not found.");
		}
		return ResponseEntity.ok(customerShipment);
	}

}