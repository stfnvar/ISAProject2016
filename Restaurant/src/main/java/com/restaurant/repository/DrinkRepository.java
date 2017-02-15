package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Drink;

public interface DrinkRepository extends Repository<Drink, Long> {
	@Query("select drinks from Drink as drinks where drink_card_id = ?1")
	Set<Drink> findByDrinkCard_Id(Long id);
}
