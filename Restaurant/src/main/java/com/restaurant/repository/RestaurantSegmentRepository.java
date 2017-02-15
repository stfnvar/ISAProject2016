package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.RestaurantSegment;

public interface RestaurantSegmentRepository extends Repository<RestaurantSegment, Long> {
	
	@Query("select segment from RestaurantSegment as segment where restaurant_id = ?1")
	Set<RestaurantSegment> findByRestaurant_Id(Long id);
	
}
