package com.sprint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.dao.OrderItemRepository;
import com.sprint.model.OrderItem;

import jakarta.transaction.Transactional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Method to create order items in bulk
    @Transactional
    public List<OrderItem> createOrderItems(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }

    // Method to fetch all order items for a specific order (optional)
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderItemRepository.findByOrder_OrderId(orderId);
    }

    // Optional - Fetch all order items (all orders)
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
}
