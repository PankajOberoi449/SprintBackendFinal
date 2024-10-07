//package com.sprint.services;
//
//import com.sprint.dao.CustomerRepository;
//import com.sprint.dao.OrderRepository;
//import com.sprint.dao.ProductRepository;
//import com.sprint.dao.ShipmentRepository;
//import com.sprint.dao.StoreRepository;
//import com.sprint.dto.OrderDTO;
//import com.sprint.exceptions.list.InvalidDataException;
//import com.sprint.exceptions.list.ResourceNotFoundException;
//import com.sprint.model.Order;
//import com.sprint.model.Store;
//import com.sprint.model.Customer;
//import com.sprint.util.Status;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//public class OrderServiceTest {
//
//    @Mock
//    private OrderRepository ordersRepo;
//
//    @Mock
//    private StoreRepository storeRepo;
//
//    @Mock
//    private CustomerRepository customerRepo;
//
//    @Mock
//    private ProductRepository productRepo;
//
//    @Mock
//    private ShipmentRepository shipmentRepo;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private OrderService orderService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetOrders() {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order());
//        List<OrderDTO> orderDTOs = new ArrayList<>();
//        orderDTOs.add(new OrderDTO());
//
//        when(ordersRepo.findAll()).thenReturn(orders);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTOs.get(0));
//
//        List<OrderDTO> result = orderService.getOrders();
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testGetOrdersByStore() {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order());
//        List<OrderDTO> orderDTOs = new ArrayList<>();
//        orderDTOs.add(new OrderDTO());
//
//        when(ordersRepo.findOrdersByStoreName(anyString())).thenReturn(orders);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTOs.get(0));
//
//        List<OrderDTO> result = orderService.getOrdersByStore("StoreName");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testGetOrderById() throws ResourceNotFoundException {
//        Order order = new Order();
//        OrderDTO orderDTO = new OrderDTO();
//
//        when(ordersRepo.findById(anyInt())).thenReturn(Optional.of(order));
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTO);
//
//        OrderDTO result = orderService.getOrderById(1);
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testGetOrdersByCustomerId() {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order());
//        List<OrderDTO> orderDTOs = new ArrayList<>();
//        orderDTOs.add(new OrderDTO());
//
//        when(ordersRepo.findByCustomerId(anyInt())).thenReturn(orders);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTOs.get(0));
//
//        List<OrderDTO> result = orderService.getOrdersByCustomerId(1);
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testGetOrdersByEmailValid() throws InvalidDataException {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order());
//        List<OrderDTO> orderDTOs = new ArrayList<>();
//        orderDTOs.add(new OrderDTO());
//
//        when(ordersRepo.findByEmail(anyString())).thenReturn(orders);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTOs.get(0));
//
//        List<OrderDTO> result = orderService.getOrdersByEmail("test@example.com");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testGetOrdersByEmailInvalid() {
//        Exception exception = assertThrows(InvalidDataException.class, () -> {
//            orderService.getOrdersByEmail("invalid-email");
//        });
//        assertEquals("Invalid Email!", exception.getMessage());
//    }
//
//    @Test
//    public void testGetOrdersByDate() throws InvalidDataException {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order());
//        List<OrderDTO> orderDTOs = new ArrayList<>();
//        orderDTOs.add(new OrderDTO());
//
//        when(ordersRepo.findOrdersByDateRange(any(Timestamp.class), any(Timestamp.class))).thenReturn(orders);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTOs.get(0));
//
//        List<OrderDTO> result = orderService.getOrdersByDate("01012024", "31012024");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testGetOrdersByDateInvalidStartDate() {
//        Exception exception = assertThrows(InvalidDataException.class, () -> {
//            orderService.getOrdersByDate("0101202", "31012024");
//        });
//        assertEquals("Invalid StartDate", exception.getMessage());
//    }
//
////    @Test
////    public void testCancelOrder() throws ResourceNotFoundException {
////        doNothing().when(ordersRepo).updateOrderStatus(anyInt());
////        when(ordersRepo.findById(anyInt())).thenReturn(Optional.of(new Order()));
////
////        Boolean result = orderService.cancelOrder(1);
////        assertTrue(result);
////    }
//
//    @Test
//    public void testOrderStatus() {
//        String[] statusNames = { "Open", "Complete", "Cancelled", "Paid", "Refunded", "Shipped" };
//        List<Status> statusList = new ArrayList<>();
//        for (String status : statusNames) {
//            Status statusObj = new Status(status);
//            statusObj.setCount(5);
//            statusList.add(statusObj);
//        }
//
//        for (String statusName : statusNames) {
//            when(ordersRepo.countByStatus(anyString())).thenReturn(5);
//        }
//
//        List<Status> result = orderService.orderStatus();
//        assertNotNull(result);
//        assertEquals(6, result.size());
//    }
//
//    @Test
//    public void testGetOrdersByStatus() {
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order());
//        List<OrderDTO> orderDTOs = new ArrayList<>();
//        orderDTOs.add(new OrderDTO());
//
//        when(ordersRepo.findByStatus(anyString())).thenReturn(orders);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTOs.get(0));
//
//        List<OrderDTO> result = orderService.getOrdersByStatus("Open");
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testCreateOrder() throws ResourceNotFoundException {
//        OrderDTO orderDTO = new OrderDTO();
//        Order order = new Order();
//
//        when(modelMapper.map(any(OrderDTO.class), eq(Order.class))).thenReturn(order);
//        when(ordersRepo.save(any(Order.class))).thenReturn(order);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTO);
//
//        OrderDTO result = orderService.createOrder(orderDTO);
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testUpdateOrder() throws ResourceNotFoundException {
//        OrderDTO orderDTO = new OrderDTO();
//        Order order = new Order();
//        Store store = new Store();
//        Customer customer = new Customer();
//
//        when(ordersRepo.findById(anyInt())).thenReturn(Optional.of(order));
//        when(customerRepo.findById(anyInt())).thenReturn(Optional.of(customer));
//        when(storeRepo.findById(anyInt())).thenReturn(Optional.of(store));
//        when(ordersRepo.save(any(Order.class))).thenReturn(order);
//        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTO);
//
//        OrderDTO result = orderService.updateOrder(orderDTO);
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testDeleteOrder() throws ResourceNotFoundException {
//        doNothing().when(ordersRepo).deleteById(anyInt());
//        when(ordersRepo.findById(anyInt())).thenReturn(Optional.of(new Order()));
//
//        orderService.deleteOrder(1);
//        verify(ordersRepo, times(1)).deleteById(1);
//    }
//}

package com.sprint.services;

import com.sprint.dao.CustomerRepository;
import com.sprint.dao.OrderRepository;
import com.sprint.dao.ProductRepository;
import com.sprint.dao.ShipmentRepository;
import com.sprint.dao.StoreRepository;
import com.sprint.dto.OrderDTO;
import com.sprint.exceptions.list.InvalidDataException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.model.Customer;
import com.sprint.model.Order;
import com.sprint.model.Store;
import com.sprint.util.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private OrderRepository ordersRepo;

    @Mock
    private StoreRepository storeRepo;

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private ProductRepository productRepo;

    @Mock
    private ShipmentRepository shipmentRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrders() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);

        OrderDTO orderDTO = new OrderDTO();
        when(ordersRepo.findAll()).thenReturn(orders);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getOrders();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));
    }

    @Test
    public void testGetOrdersByStore() {
        String storeName = "Test Store";
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);

        OrderDTO orderDTO = new OrderDTO();
        when(ordersRepo.findOrdersByStoreName(storeName)).thenReturn(orders);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getOrdersByStore(storeName);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));
    }

    @Test
    public void testGetOrderById() throws ResourceNotFoundException {
        int orderId = 1;
        Order order = new Order();
        OrderDTO orderDTO = new OrderDTO();
        when(ordersRepo.findById(orderId)).thenReturn(java.util.Optional.of(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        OrderDTO result = orderService.getOrderById(orderId);
        assertNotNull(result);
        assertEquals(orderDTO, result);
    }

    @Test
    public void testGetOrdersByCustomerId() {
        int customerId = 1;
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);

        OrderDTO orderDTO = new OrderDTO();
        when(ordersRepo.findByCustomerId(customerId)).thenReturn(orders);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getOrdersByCustomerId(customerId);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));
    }

    @Test
    public void testGetOrdersByEmail() throws InvalidDataException {
        String email = "test@example.com";
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);

        OrderDTO orderDTO = new OrderDTO();
        when(ordersRepo.findByEmail(email)).thenReturn(orders);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getOrdersByEmail(email);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));
    }

    @Test
    public void testGetOrdersByDate() throws InvalidDataException {
        String startDate = "01012024";
        String endDate = "31012024";
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);

        OrderDTO orderDTO = new OrderDTO();
        when(ordersRepo.findOrdersByDateRange(any(Timestamp.class), any(Timestamp.class))).thenReturn(orders);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getOrdersByDate(startDate, endDate);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));
    }

    @Test
    public void testCancelOrder() throws ResourceNotFoundException {
        int orderId = 1;
        when(ordersRepo.findById(orderId)).thenReturn(java.util.Optional.of(new Order()));

        Boolean result = orderService.cancelOrder(orderId);
        assertTrue(result);
    }

//    @Test
//    public void testOrderStatus() {
//        String[] statusNames = { "Open", "Complete", "Cancelled", "Paid", "Refunded", "Shipped" };
//        List<Status> statusList = new ArrayList<>();
//        for (String statusName : statusNames) {
//            Status status = new Status(statusName);
//            status.setCount(10);  // Mock count value
//            statusList.add(status);
//        }
//
//        when(ordersRepo.countByStatus(anyString())).thenReturn(10);
//
//        List<Status> result = orderService.orderStatus();
//        assertNotNull(result);
//        assertEquals(statusNames.length, result.size());
//        for (int i = 0; i < statusNames.length; i++) {
//            Status status = result.get(i);
//            assertEquals(statusNames[i], status.getName());
//            assertEquals(10L, status.getCount());
//        }
//    }
    
    
    
    

    @Test
    public void testGetOrdersByStatus() {
        String status = "Open";
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);

        OrderDTO orderDTO = new OrderDTO();
        when(ordersRepo.findByStatus(status)).thenReturn(orders);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> result = orderService.getOrdersByStatus(status);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderDTO, result.get(0));
    }

    @Test
    public void testCreateOrder() throws ResourceNotFoundException {
        OrderDTO orderDTO = new OrderDTO();
        Order order = new Order();
        when(modelMapper.map(orderDTO, Order.class)).thenReturn(order);
        when(ordersRepo.save(order)).thenReturn(order);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        OrderDTO result = orderService.createOrder(orderDTO);
        assertNotNull(result);
        assertEquals(orderDTO, result);
    }

    @Test
    public void testUpdateOrder() throws ResourceNotFoundException {
        OrderDTO orderDTO = new OrderDTO();
        Order order = new Order();
        when(ordersRepo.findById(orderDTO.getOrderId())).thenReturn(java.util.Optional.of(order));
        when(customerRepo.findById(orderDTO.getCustomerId())).thenReturn(java.util.Optional.of(new Customer()));
        when(storeRepo.findById(orderDTO.getStoreId())).thenReturn(java.util.Optional.of(new Store()));
        when(ordersRepo.save(order)).thenReturn(order);
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        OrderDTO result = orderService.updateOrder(orderDTO);
        assertNotNull(result);
        assertEquals(orderDTO, result);
    }

    @Test
    public void testDeleteOrder() throws ResourceNotFoundException {
        int orderId = 1;
        when(ordersRepo.findById(orderId)).thenReturn(java.util.Optional.of(new Order()));

        orderService.deleteOrder(orderId);

        // Verify that deleteById was called once with the correct argument
        verify(ordersRepo).deleteById(orderId);
    }
}
