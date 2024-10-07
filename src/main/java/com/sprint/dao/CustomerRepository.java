package com.sprint.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.dto.LoginDTO;
import com.sprint.dto.ShipmentStatusCountCustomer;
import com.sprint.model.Customer;
import com.sprint.model.Order;
import com.sprint.model.Shipment;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public List<Customer> findByEmailAddress(String emailAddress);

	public List<Customer> findByFullNameContains(String fullName);

	@Query("SELECT new com.sprint.dto.ShipmentStatusCountCustomer(s.shipmentStatus, COUNT(s.customer) as customerCount) "
			+ "FROM Shipment s " + "GROUP BY s.shipmentStatus")
	public List<ShipmentStatusCountCustomer> getOrderCountByStatus();

	@Query("SELECT o " + "FROM Customer c, Order o " + "WHERE c.customerId = :Id "
			+ "AND c.customerId = o.customer.customerId")
	public List<Order> getCustomerOrders(@Param("Id") int customerId);

	@Query("SELECT s FROM Shipment s " + "WHERE s.customer.customerId = :Id ")
	public List<Shipment> getCustomerShipments(@Param("Id") int customerId);

	@Query("SELECT new com.sprint.dto.LoginDTO(c.customerId, c.emailAddress, c.fullName) FROM Customer c WHERE LOWER(c.emailAddress) = LOWER(:email) AND c.password = :password")
	LoginDTO getCustomerDetails(@Param("email") String email, @Param("password") String password);
}
