package com.sprint.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.dao.ShipmentRepository;
import com.sprint.dto.ShipmentDto;
import com.sprint.model.Shipment;

@Service

public class ShipmentService implements IShipmentService {

	@Autowired

	private ShipmentRepository shipmentRepository;

	@Override

	public List<ShipmentDto> getAllShipments() {

		List<Shipment> shipments = shipmentRepository.findAll();

		return shipments.stream().map(this::convertToDTO).collect(Collectors.toList());

	}

	private ShipmentDto convertToDTO(Shipment shipment) {

		ShipmentDto dto = new ShipmentDto();

		dto.setShipmentId(shipment.getShipmentId());

		dto.setDeliveryAddress(shipment.getDeliveryAddress());

		dto.setShipmentStatus(shipment.getShipmentStatus());

		dto.setStoreId(shipment.getStore() != null ? shipment.getStore().getStoreId() : null);

		dto.setCustomerId(shipment.getCustomer() != null ? shipment.getCustomer().getCustomerId() : null);

		return dto;

	}

}
