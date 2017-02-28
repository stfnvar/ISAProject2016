package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Cook;
import com.restaurant.model.Waiter;

public interface CookRepository extends JpaRepository<Cook, Long>{
	@Query("select cooks from Cook as cooks where restaurant_id=?1")
	Set<Cook> getRestaurantStaffCooks(Long id);
}
