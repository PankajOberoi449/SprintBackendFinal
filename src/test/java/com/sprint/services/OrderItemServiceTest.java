package com.sprint.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprint.dao.OrderItemRepository;
import com.sprint.model.OrderItem;

public class OrderItemServiceTest {

    @Mock
    private OrderItemRepository repo;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrderItem() {
        // Arrange
        OrderItem item1 = new OrderItem(); // Initialize with necessary fields
        OrderItem item2 = new OrderItem(); // Initialize with necessary fields
        List<OrderItem> mockOrderItems = Arrays.asList(item1, item2);
        
        when(repo.findAll()).thenReturn(mockOrderItems);

        // Act
        List<OrderItem> result = orderItemService.getAllOrderItems();

        // Assert
        assertEquals(2, result.size());
        assertEquals(mockOrderItems, result);
    }
}
