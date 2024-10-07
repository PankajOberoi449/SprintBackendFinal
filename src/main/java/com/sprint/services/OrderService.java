package com.sprint.services;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.dao.CustomerRepository;
import com.sprint.dao.OrderRepository;
import com.sprint.dao.ProductRepository;
import com.sprint.dao.ShipmentRepository;
import com.sprint.dao.StoreRepository;
import com.sprint.dto.OrderDTO;
import com.sprint.exceptions.list.InvalidDataException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.model.Order;
import com.sprint.util.Status;

import jakarta.transaction.Transactional;

@Service
public class OrderService implements IorderService {
	@Autowired
	OrderRepository ordersRepo;

	@Autowired
	StoreRepository storeRepo;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	ShipmentRepository shipmentRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<OrderDTO> getOrders() {
		// TODO Auto-generated method stub
		List<OrderDTO> list = ordersRepo.findAll().stream().map(order -> modelMapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public List<OrderDTO> getOrdersByStore(String store) {
		// TODO Auto-generated method stub
		// if(!storeRepo.existsByName(store)) throw new ResourceNotFoundException("Store
		// doesn't exist");
		List<OrderDTO> list = ordersRepo.findOrdersByStoreName(store).stream()
				.map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public OrderDTO getOrderById(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Order order = ordersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		return modelMapper.map(order, OrderDTO.class);
	}

	@Override
	public List<OrderDTO> getOrdersByCustomerId(int customerId) {
		List<Order> orders = ordersRepo.findByCustomerId(customerId);
		return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getOrdersByEmail(String email) throws InvalidDataException {
		// TODO Auto-generated method stub
		String regex = "^[^@].*[@]+.*[^@]$";
		if (!email.matches(regex))
			throw new InvalidDataException("Invalid Email!");
		List<OrderDTO> list = ordersRepo.findByEmail(email).stream()
				.map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<OrderDTO> getOrdersByDate(String startDate, String endDate) throws InvalidDataException {
		if (startDate.length() != 8)
			throw new InvalidDataException("Invalid StartDate");
		if (endDate.length() != 8)
			throw new InvalidDataException("Invalid EndDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

		// Parse the input strings to LocalDate
		LocalDate start = LocalDate.parse(startDate, formatter);
		LocalDate end = LocalDate.parse(endDate, formatter);

		// Convert LocalDate to Timestamp
		Timestamp startTimestamp = Timestamp.valueOf(start.atStartOfDay());
		Timestamp endTimestamp = Timestamp.valueOf(end.atStartOfDay());

		// Fetch orders within the date range
		List<Order> orders = ordersRepo.findOrdersByDateRange(startTimestamp, endTimestamp);

		// Map Order entities to OrderDTOs
		return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public Boolean cancelOrder(int id) throws ResourceNotFoundException {
		ordersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		ordersRepo.updateOrderStatus(id);
		return true;
	}

	@Override
	public List<Status> orderStatus() {
		// TODO Auto-generated method stub
		String[] statusNames = { "OPEN", "COMPLETE", "CANCELLED", "PAID", "REFUNDED", "SHIPPED" };
		List<Status> statusList = new ArrayList<>();
		for (String statuses : statusNames) {
			Status status = new Status(statuses);
			status.setCount(ordersRepo.countByStatus(statuses.toUpperCase()));
			statusList.add(status);
		}
		return statusList;
	}

	@Override
	public List<OrderDTO> getOrdersByStatus(String status) {
		// TODO Auto-generated method stub
		List<OrderDTO> list = ordersRepo.findByStatus(status).stream()
				.map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
		return list;
	}

	@Transactional
	@Override
	public OrderDTO createOrder(OrderDTO orderDTO) throws ResourceNotFoundException {
		Order order = ordersRepo.save(modelMapper.map(orderDTO, Order.class));
		return modelMapper.map(order, OrderDTO.class);
	}

	@Transactional
	@Override
	public OrderDTO updateOrder(OrderDTO orderDTO) throws ResourceNotFoundException {
		Order order = ordersRepo.findById(orderDTO.getOrderId())
				.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		order.setOrderTms(orderDTO.getOrderTms());
		order.setOrderStatus(orderDTO.getOrderStatus());
		order.setCustomer(customerRepo.findById(orderDTO.getCustomerId()).orElseThrow());
		order.setStore(storeRepo.findById(orderDTO.getStoreId()).orElseThrow());
		Order savedOrder = ordersRepo.save(order);
		return modelMapper.map(savedOrder, OrderDTO.class);
	}

	@Transactional
	@Override
	public void deleteOrder(int id) throws ResourceNotFoundException {
		ordersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		ordersRepo.deleteById(id);
	}

//	@Override
//	public List<Status> orderStatus() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}