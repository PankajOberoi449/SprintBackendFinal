package com.sprint.services;

import com.sprint.dao.InventoryRepository;
import com.sprint.dto.InventoryDTO;
import com.sprint.dto.InventoryStoreProductCustomerDTO;
import com.sprint.dto.InventoryStoreProductStatusDTO;
import com.sprint.dto.ProductOrderDetailsDTO;
import com.sprint.dto.ShipmentStatusCountDTO;
import com.sprint.model.Customer;
import com.sprint.model.Inventory;
import com.sprint.model.Product;
import com.sprint.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllInventory() {
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventories.add(inventory);

        InventoryDTO inventoryDTO = new InventoryDTO();
        when(inventoryRepository.findAll()).thenReturn(inventories);
        when(modelMapper.map(inventory, InventoryDTO.class)).thenReturn(inventoryDTO);

        List<InventoryDTO> result = inventoryService.getAllInventory();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(inventoryDTO, result.get(0));
    }

    @Test
    public void testGetInventoryByStoreId() {
        Store store = new Store();
        Product product = new Product();
        Object[] data = { null, product, store, "InStock" };
        List<Object[]> inventoryData = new ArrayList<>();
        inventoryData.add(data);

        InventoryStoreProductStatusDTO dto = new InventoryStoreProductStatusDTO();
        dto.setProduct(product);
        dto.setStore(store);
        dto.setOrderStatus("InStock");

        when(inventoryRepository.findByStore_StoreId(anyInt())).thenReturn(inventoryData);
        
        // Directly setting values in the DTO as modelMapper will not be used in this case
        List<InventoryStoreProductStatusDTO> result = inventoryService.getInventoryByStoreId(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    public void testGetInventoryAndShipments() {
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventories.add(inventory);

        InventoryDTO inventoryDTO = new InventoryDTO();
        when(inventoryRepository.findInventoryMatchingShipments()).thenReturn(inventories);
        when(modelMapper.map(inventory, InventoryDTO.class)).thenReturn(inventoryDTO);

        List<InventoryDTO> result = inventoryService.getInventoryAndShipments();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(inventoryDTO, result.get(0));
    }

    @Test
    public void testGetInventoryByOrderId() {
        Store store = new Store();
        Product product = new Product();
        Customer customer = new Customer();
        Object[] data = { store, product, customer };
        List<Object[]> results = new ArrayList<>();
        results.add(data);

        InventoryStoreProductCustomerDTO dto = new InventoryStoreProductCustomerDTO();
        dto.setStore(store);
        dto.setProduct(product);
        dto.setCustomer(customer);

        when(inventoryRepository.findByOrderId(anyInt())).thenReturn(results);

        List<InventoryStoreProductCustomerDTO> result = inventoryService.getInventoryByOrderId(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto.getStore(), result.get(0).getStore());
        assertEquals(dto.getProduct(), result.get(0).getProduct());
        assertEquals(dto.getCustomer(), result.get(0).getCustomer());
    }

    @Test
    public void testGetShipmentCountByStatus() {
        List<Object[]> shipmentData = new ArrayList<>();
        Object[] data = { "Shipped", 10L };
        shipmentData.add(data);

        ShipmentStatusCountDTO dto = new ShipmentStatusCountDTO();
        dto.setShipmentStatus("Shipped");
        dto.setTotalProductsSold(10L);

        when(inventoryRepository.findShipmentCountByStatus()).thenReturn(shipmentData);

        List<ShipmentStatusCountDTO> result = inventoryService.getShipmentCountByStatus();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto.getShipmentStatus(), result.get(0).getShipmentStatus());
        assertEquals(dto.getTotalProductsSold(), result.get(0).getTotalProductsSold());
    }

    @Test
    public void testGetProductDetailsByOrderId() {
        Product product = new Product();
        Store store = new Store();
        Object[] data = { product, store, "Shipped", BigDecimal.valueOf(100) };
        List<Object[]> productDetails = new ArrayList<>();
        productDetails.add(data);

        ProductOrderDetailsDTO dto = new ProductOrderDetailsDTO();
        dto.setProduct(product);
        dto.setStore(store);
        dto.setShipmentStatus("Shipped");
        dto.setTotalAmount(BigDecimal.valueOf(100));

        when(inventoryRepository.findProductDetailsByOrderId(anyInt())).thenReturn(productDetails);

        List<ProductOrderDetailsDTO> result = inventoryService.getProductDetailsByOrderId(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto.getProduct(), result.get(0).getProduct());
        assertEquals(dto.getStore(), result.get(0).getStore());
        assertEquals(dto.getShipmentStatus(), result.get(0).getShipmentStatus());
        assertEquals(dto.getTotalAmount(), result.get(0).getTotalAmount());
    }

    @Test
    public void testGetInventoryByProductIdAndStoreId() {
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventories.add(inventory);

        InventoryDTO inventoryDTO = new InventoryDTO();
        when(inventoryRepository.findByProductIdAndStoreId(anyInt(), anyInt())).thenReturn(inventories);
        when(modelMapper.map(inventory, InventoryDTO.class)).thenReturn(inventoryDTO);

        List<InventoryDTO> result = inventoryService.getInventoryByProductIdAndStoreId(1, 1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(inventoryDTO, result.get(0));
    }

    @Test
    public void testGetInventoryByProductCategory() {
        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventories.add(inventory);

        InventoryDTO inventoryDTO = new InventoryDTO();
        when(inventoryRepository.findInventoryByProductCategory(anyString())).thenReturn(inventories);
        when(modelMapper.map(inventory, InventoryDTO.class)).thenReturn(inventoryDTO);

        List<InventoryDTO> result = inventoryService.getInventoryByProductCategory("Electronics");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(inventoryDTO, result.get(0));
    }
}

