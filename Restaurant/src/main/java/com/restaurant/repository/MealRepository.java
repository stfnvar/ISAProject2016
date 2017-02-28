package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {

	@Query("select meals from Meal as meals where menu_id = ?1")
	Set<Meal> findByMenu_Id(Long id);

	@Modifying
	@Query("delete from Meal where id = ?1")
	void removeById(Long id);

	@Modifying
	@Query("update Meal p set p.name = ?1, p.description = ?2, p.price = ?3 where p.id=?4")
	void modifyById(String name, String description, Double price, Long id);
	
	
	@Query("select avg(r.rating) from Meal m join m.ratings r where lower(m.name) like ?1 and m.menu.restaurant.id = ?3")
	Double mealRating(String name, String surname, Long id);
}
