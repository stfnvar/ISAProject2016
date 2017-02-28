package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.RestaurantManager;

public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, Long> {

	RestaurantManager findById(Long id);

	@Query("select rm from RestaurantManager as rm where restaurant_id = ?1")
	Set<RestaurantManager> getRestaurantManagersByRestaurantId(Long id);
}
