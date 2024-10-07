package com.sprint.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.sprint.dao.ShipmentRepository;
//import com.sprint.dto.ShipmentDto;
//import com.sprint.model.Shipment;
//
//public class ShipmentServiceTest {
//
//    @Mock
//    private ShipmentRepository shipmentRepository;
//
//    @InjectMocks
//    private ShipmentService shipmentService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//	/*
//	 * @Test public void testGetAllShipments() { // Arrange Shipment shipment1 = new
//	 * Shipment(); shipment1.setShipmentId(1);
//	 * shipment1.setDeliveryAddress("123 Test St");
//	 * shipment1.setShipmentStatus("Shipped");
//	 * 
//	 * Shipment shipment2 = new Shipment(); shipment2.setShipmentId(2);
//	 * shipment2.setDeliveryAddress("456 Another St");
//	 * shipment2.setShipmentStatus("Delivered");
//	 * 
//	 * when(shipmentRepository.findAll()).thenReturn(Arrays.asList(shipment1,
//	 * shipment2));
//	 * 
//	 * // Act List<ShipmentDto> result = shipmentService.getAllShipments();
//	 * 
//	 * // Assert assertNotNull(result); assertEquals(2, result.size());
//	 * 
//	 * // Verify content of result ShipmentDto dto1 = result.get(0); assertEquals(1,
//	 * dto1.getShipmentId()); assertEquals("123 Test St",
//	 * dto1.getDeliveryAddress()); assertEquals("Shipped",
//	 * dto1.getShipmentStatus());
//	 * 
//	 * ShipmentDto dto2 = result.get(1); assertEquals(2, dto2.getShipmentId());
//	 * assertEquals("456 Another St", dto2.getDeliveryAddress());
//	 * assertEquals("Delivered", dto2.getShipmentStatus()); }
//	 */
//}


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.dao.ShipmentRepository;
import com.sprint.dto.ShipmentDto;
import com.sprint.model.Customer;
import com.sprint.model.Shipment;
import com.sprint.model.Store;
import com.sprint.services.ShipmentService;

public class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository; // Mock the repository

    @InjectMocks
    private ShipmentService shipmentService; // Service to be tested

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testGetAllShipments() {
        // Prepare test data
        Shipment shipment1 = new Shipment();
        shipment1.setShipmentId(1);
        shipment1.setDeliveryAddress("123 Main St");
        shipment1.setShipmentStatus("Shipped");
        shipment1.setStore(new Store());
        shipment1.setCustomer(new Customer());

        Shipment shipment2 = new Shipment();
        shipment2.setShipmentId(2);
        shipment2.setDeliveryAddress("456 Elm St");
        shipment2.setShipmentStatus("Delivered");
        shipment2.setStore(new Store());
        shipment2.setCustomer(new Customer());

        List<Shipment> shipments = new ArrayList<>();
        shipments.add(shipment1);
        shipments.add(shipment2);

        // Mock the repository method
        when(shipmentRepository.findAll()).thenReturn(shipments);

        // Expected DTOs
        ShipmentDto dto1 = new ShipmentDto();
        dto1.setShipmentId(1);
        dto1.setDeliveryAddress("123 Main St");
        dto1.setShipmentStatus("Shipped");
        dto1.setStoreId(shipment1.getStore() != null ? shipment1.getStore().getStoreId() : 0);
        dto1.setCustomerId(shipment1.getCustomer() != null ? shipment1.getCustomer().getCustomerId() : 0);

        ShipmentDto dto2 = new ShipmentDto();
        dto2.setShipmentId(2);
        dto2.setDeliveryAddress("456 Elm St");
        dto2.setShipmentStatus("Delivered");
        dto2.setStoreId(shipment2.getStore() != null ? shipment2.getStore().getStoreId() : 0);
        dto2.setCustomerId(shipment2.getCustomer() != null ? shipment2.getCustomer().getCustomerId() : 0);

        List<ShipmentDto> expectedDtos = new ArrayList<>();
        expectedDtos.add(dto1);
        expectedDtos.add(dto2);

        // Call the method under test
        List<ShipmentDto> result = shipmentService.getAllShipments();

        // Assert results
        assertNotNull(result);
        assertEquals(expectedDtos.size(), result.size());
        for (int i = 0; i < expectedDtos.size(); i++) {
            assertEquals(expectedDtos.get(i).getShipmentId(), result.get(i).getShipmentId());
            assertEquals(expectedDtos.get(i).getDeliveryAddress(), result.get(i).getDeliveryAddress());
            assertEquals(expectedDtos.get(i).getShipmentStatus(), result.get(i).getShipmentStatus());
            assertEquals(expectedDtos.get(i).getStoreId(), result.get(i).getStoreId());
            assertEquals(expectedDtos.get(i).getCustomerId(), result.get(i).getCustomerId());
        }
    }
}

