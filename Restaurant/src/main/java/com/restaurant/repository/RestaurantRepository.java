package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Restaurant;
import com.restaurant.service.Restaurants;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	Restaurant findOne(Long id);
	
	//@Query(value="select id, name from restaurant", nativeQuery=true)
	List<Restaurant> findAll();

}
