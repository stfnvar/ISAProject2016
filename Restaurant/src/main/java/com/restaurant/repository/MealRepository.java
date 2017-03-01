package com.restaurant.repository;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Drink;
import com.restaurant.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long>{
	
	@Query(value="select distinct m.id,m.name,m.price from meal m join menu s on m.menu_id=s.id join restaurant r on s.restaurant_id=?1", nativeQuery=true)
	ArrayList<Meal> mealsInRestaurant(Long id);
	
	@Query("select meals from Meal as meals where menu_id = ?1")
	Set<Meal> findByMenu_Id(Long id);
	
	@Query("select meals from Meal as meals where meals.menu.restaurant.id = ?1")
	Set<Meal> findByRestaurant(Long id);
	
	@Modifying
	@Query("delete from Meal where id = ?1")
	void removeById(Long id);

	@Modifying
	@Query("update Meal p set p.name = ?1, p.description = ?2, p.price = ?3 where p.id=?4")
	void modifyById(String name, String description, Double price, Long id);
	
	
	@Query("select avg(r.rating) from Meal m join m.ratings r where lower(m.name) like ?1 and m.menu.restaurant.id = ?3")
	Double mealRating(String name, String surname, Long id);
	
	@Query("select m from Meal as m where m.name=?1 and m.menu.restaurant.id=?2")
	public Meal findByName(String name, Long id);
}
