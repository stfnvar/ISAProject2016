package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Grocery;

public interface GroceryRepository extends JpaRepository<Grocery, Long>{
	
	
}
