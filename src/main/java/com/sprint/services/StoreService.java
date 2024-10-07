package com.sprint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.dao.StoreRepository;
import com.sprint.model.Store;

@Service
public class StoreService implements IStoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public List<Store> getAllStores() {
		System.out.println(storeRepository.findAll());
		return storeRepository.findAll();
	}

	@Override
	public Store getStoreById(Integer id) {
		return storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found"));
	}
}
