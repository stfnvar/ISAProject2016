package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantManager;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	Restaurant findOne(Long id);

}
