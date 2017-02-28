package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.DrinkCard;

public interface DrinkCardRepository extends JpaRepository<DrinkCard, Long> {
	@Query("select drinkCard from DrinkCard as drinkCard where restaurant_id = ?1")
	Set<DrinkCard> findByRestaurant_Id(Long id);

	@Query("select drinkCard from DrinkCard as drinkCard where restaurant_id = ?1 and id=?2")
	DrinkCard findByRestaurant_IdAndId(Long restaurant_id, Long id);
}
