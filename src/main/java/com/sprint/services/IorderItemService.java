package com.sprint.services;

import java.util.List;

import com.sprint.model.OrderItem;

public interface IorderItemService {

	/**
	 * Retrieves all order items.
	 *
	 * @return the list of order items
	 */
	public List<OrderItem> getAllOrderItem();
}
