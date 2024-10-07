package com.sprint.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.model.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
	List<Shipment> findAll(); // This should be fine
 
}