package com.sprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.model.Store;
import com.sprint.services.StoreService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/stores")
public class StoreController {

	@Autowired
	private StoreService storeService;

	@GetMapping()
	public List<Store> getAllStores() {
		List<Store> stores = storeService.getAllStores();
		for(Store s: stores) {
			System.out.println(s);
		}
		return stores;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Store> getStoreById(@PathVariable Integer id) {
		Store store = storeService.getStoreById(id);
		return ResponseEntity.ok(store);
	}
}
