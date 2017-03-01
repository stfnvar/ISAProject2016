package com.restaurant.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.CookType;
import com.restaurant.model.OrderedDrink;
import com.restaurant.model.OrderedMeal;

public interface OrderedMealRepository extends JpaRepository<OrderedMeal, Long> { 
	
	@Modifying
	@Query("update OrderedMeal om set om.acceptedMeal=1 where om.id=?1")
	public void updateAcceptedMeal(long id);
	
	public OrderedMeal findOne(Long id);
	
	@Query("select o from OrderedMeal as o where o.meal.menu.restaurant.id=?1 and o.meal.typeM = ?2")
	public ArrayList<OrderedMeal> findAll(Long id, CookType typeC);
	
	@Query("select od from OrderedMeal as od where order_id=?1")
	public ArrayList<OrderedMeal> findByOrderId(Long id);
	
	@Modifying
	@Query("update OrderedMeal od set od.quantity=?1 where od.id=?2")
	public void updateQuantity(int quantity, long id);
	
	@Modifying
	@Query("update OrderedMeal set meal_id=?1 where id=?2")
	public void updateMealName(long idMeal, long id);
	
	@Modifying
	@Query("update OrderedMeal om set om.ready=1 where om.id=?1")
	public void updateMadeMeal(long id);
	
}
