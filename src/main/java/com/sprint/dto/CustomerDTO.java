package com.sprint.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.sprint.model.Customer;

import lombok.Data;

@Data
public class CustomerDTO {
	private int id;
	private String fullName;
	private String emailAddress;
	private String password;

	// Constructor, getters, and setters
	



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(int id, String fullName, String emailAddress, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public static CustomerDTO fromEntity(Customer customer) {
		CustomerDTO dto = new CustomerDTO();
		dto.setId(customer.getCustomerId());
		dto.setFullName(customer.getFullName());
		dto.setEmailAddress(customer.getEmailAddress());
		return dto;
	}

	public static List<CustomerDTO> fromEntities(List<Customer> customers) {
		return customers.stream().map(CustomerDTO::fromEntity).collect(Collectors.toList());
	}

}
