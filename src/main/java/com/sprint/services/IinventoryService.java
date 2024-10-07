package com.sprint.services;

import java.util.List;

import com.sprint.dto.InventoryDTO;
import com.sprint.dto.InventoryStoreProductCustomerDTO;
import com.sprint.dto.InventoryStoreProductStatusDTO;
import com.sprint.dto.ProductOrderDetailsDTO;
import com.sprint.dto.ShipmentStatusCountDTO;

public interface IinventoryService {

    // Retrieve all inventory records
    List<InventoryDTO> getAllInventory();

    // Fetch inventory and product status by store ID
    List<InventoryStoreProductStatusDTO> getInventoryByStoreId(int storeId);

    // Retrieve inventory details including store, product, and customer by order ID
    List<InventoryStoreProductCustomerDTO> getInventoryByOrderId(int orderId);

    // Get detailed product information by order ID including store, shipment status, and total amount
    List<ProductOrderDetailsDTO> getProductDetailsByOrderId(int orderId);

    // Fetch inventory based on product ID and store ID
    List<InventoryDTO> getInventoryByProductIdAndStoreId(int productId, int storeId);

    // Retrieve the count of shipments grouped by shipment status
    List<ShipmentStatusCountDTO> getShipmentCountByStatus();

    // Get inventory records that match shipment data
    List<InventoryDTO> getInventoryAndShipments();

    // Fetch inventory by product category
    List<InventoryDTO> getInventoryByProductCategory(String category);
}
