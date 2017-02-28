package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.DrinkCard;

public interface DrinkCardRepository extends JpaRepository<DrinkCard, Long> {
	@Query("select drinkCard from DrinkCard as drinkCard where restaurant_id = ?1")
	DrinkCard findByRestaurant_Id(Long id);
}
