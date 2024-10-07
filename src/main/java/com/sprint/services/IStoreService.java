package com.sprint.services;

import java.util.List;

import com.sprint.model.Store;

public interface IStoreService {
	public List<Store> getAllStores();

	public Store getStoreById(Integer id);
}
