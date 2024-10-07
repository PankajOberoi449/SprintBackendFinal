package com.sprint.dao;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.model.Order;

import jakarta.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o JOIN o.store s WHERE s.storeName = :storeName")
	List<Order> findOrdersByStoreName(@Param("storeName") String storeName);

	@Query("SELECT o FROM Order o WHERE o.customer.emailAddress = :email")
	public List<Order> findByEmail(@Param("email") String email);

	@Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId")
	public List<Order> findByCustomerId(@Param("customerId") int customerId);

	@Query("SELECT o FROM Order o WHERE o.orderStatus = :status")
	public Collection<Order> findByStatus(@Param("status") String status);

	@Query("SELECT o FROM Order o WHERE o.orderTms >= :startDate AND o.orderTms <= :endDate")
	List<Order> findOrdersByDateRange(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

	@Modifying
	@Transactional
	@Query("UPDATE Order o SET o.orderStatus = 'CANCELLED' WHERE o.orderId = :id")
	int updateOrderStatus(@Param("id") int id);

	@Query("SELECT COUNT(o) FROM Order o WHERE o.orderStatus = :status")
	int countByStatus(@Param("status") String status);
}
