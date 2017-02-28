package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	@Query("select avg(r.rating) from Rating r where restaurant.id = ?1")
	Double getRestaurantRating(Long id);

	@Query("select avg(r.rating) from Rating r where waiter.id = ?1")
	Double getWaiterRating(String name, String surmane);
	
	@Query("select avg(r.rating) from Rating r where lower(meal.name) = ?1 and meal.menu.restaurant.id = ?2")
	Double getMealRating(String name, Long id);

}
