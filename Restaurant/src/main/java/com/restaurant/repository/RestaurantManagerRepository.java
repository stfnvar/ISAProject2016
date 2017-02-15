package com.restaurant.repository;

import org.springframework.data.repository.Repository;

import com.restaurant.model.RestaurantManager;

public interface RestaurantManagerRepository extends Repository<RestaurantManager, Long>{
		
	RestaurantManager findOne(Long id);
	
}
