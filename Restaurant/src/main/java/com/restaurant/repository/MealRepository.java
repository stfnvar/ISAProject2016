package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long>{
	
	@Query("select meals from Meal as meals where menu_id = ?1")
	Set<Meal> findByMenu_Id(Long id);
	
}
