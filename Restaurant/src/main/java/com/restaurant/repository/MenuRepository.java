package com.restaurant.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Menu;

public interface MenuRepository extends Repository<Menu, Long>{
	
	@Query("select menu from Menu as menu where restaurant_id = ?1")
	Menu findByRestaurant_Id(Long id);
	
}
