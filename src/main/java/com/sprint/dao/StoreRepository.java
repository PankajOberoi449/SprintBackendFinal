package com.sprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>{
	
}
