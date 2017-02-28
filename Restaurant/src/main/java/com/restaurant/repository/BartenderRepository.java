package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Bartender;
import com.restaurant.model.Waiter;

public interface BartenderRepository extends JpaRepository<Bartender, Long> {
	@Query("select bartenders from Bartender as bartenders where restaurant_id=?1")
	Set<Bartender> getRestaurantStaffBartender(Long id);
}
