package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.restaurant.model.RestaurantManager;

public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, Long>{
		
	RestaurantManager findOne(Long id);
	
}
