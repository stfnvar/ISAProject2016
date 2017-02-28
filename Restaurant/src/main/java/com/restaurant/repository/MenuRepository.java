package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{
	
	@Query("select menu from Menu as menu where restaurant_id = ?1")
	Set<Menu> findByRestaurant_Id(Long id);
	
	@Query("select menu from Menu as menu where restaurant_id = ?1 and id=?2")
	Menu findByRestaurant_IdAndId(Long restaurantId, Long id);
	
}
