package com.restaurant.repository;

import com.restaurant.model.Waiter;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaiterRepository extends JpaRepository<Waiter, Long>  {

	@Query("select waiters from Waiter as waiters where restaurant_id=?1")
	Set<Waiter> getRestaurantStaffWaiters(Long id);
	
	@Query("select avg(r.rating) from Waiter w join w.ratings r where lower(w.name) like ?1 or w.surname like ?2 and w.restaurant.id = ?3")
	Double waiterRating(String name, String surname, Long id);
	
}
