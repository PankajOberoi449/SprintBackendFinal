package com.sprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.dto.OrderDTO;
import com.sprint.exceptions.list.InternalServerErrorException;
import com.sprint.exceptions.list.InvalidDataException;
import com.sprint.exceptions.list.ResourceNotFoundException;
import com.sprint.services.IorderService;
import com.sprint.util.Status;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/orders")
public class OrdersController {
	@Autowired
	IorderService ordersService;

	/*
	 * getting all orders
	 */
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getOrders() throws InternalServerErrorException {
		List<OrderDTO> list = ordersService.getOrders();
		if (list == null)
			throw new InternalServerErrorException("Orders Not found");
		return ResponseEntity.ok(list);
	}

	/*
	 * create orders
	 */
	@PostMapping(consumes = "application/json")
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order) throws ResourceNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.createOrder(order));
	}

	/*
	 * update orders
	 */
	@PutMapping
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO order) throws ResourceNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.updateOrder(order));
	}
	
	/*
	 * delete orders
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") int id) throws ResourceNotFoundException {
		ordersService.deleteOrder(id);
		return ResponseEntity.ok("Order Deleted");
	}

	/*
	 * count of orders by status
	 */
	@GetMapping("/status")
	public ResponseEntity<List<Status>> orderStatus() {
		return ResponseEntity.ok(ordersService.orderStatus());
	}

	/*
	 * getting all orders by store name
	 */
	@GetMapping("/store/{store}")
	public ResponseEntity<List<OrderDTO>> getOrdersByStore(@PathVariable("store") String store) {
		return ResponseEntity.ok(ordersService.getOrdersByStore(store));
	}

	/*
	 * getting order by id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id")int id) throws ResourceNotFoundException {
		return ResponseEntity.ok(ordersService.getOrderById(id));
	}

	/*
	 * finding orders by customer ID
	 */
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable("customerId")int customerId) {
		return ResponseEntity.ok(ordersService.getOrdersByCustomerId(customerId));
	}

	/*
	 * Cancelling the order
	 */
	@GetMapping("/{id}/cancel")
	public ResponseEntity<String> cancelOrder(@PathVariable("id") int id) throws ResourceNotFoundException {
		ordersService.cancelOrder(id);
		return ResponseEntity.ok("Order Cancelled");
	}

	/*
	 * Getting Orders By Status
	 */
	@GetMapping("/status/{status}")
	public List<OrderDTO> orderStatus(@PathVariable("status") String status) {
		return ordersService.getOrdersByStatus(status);
	}

	/*
	 * getting all orders between start and end date
	 */
	@GetMapping("/date/{startDate}/{endDate}")
	public List<OrderDTO> getOrdersByDate(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate)
			throws InvalidDataException {
		return ordersService.getOrdersByDate(startDate, endDate);
	}

	/*
	 * getting all orders specified and valid email
	 */
	@GetMapping("/customer/email/{email}")
	public List<OrderDTO> getOrdersByEmail(@PathVariable("email") String email) {
		return ordersService.getOrdersByEmail(email);
	}
}
