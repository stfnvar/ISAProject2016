package com.restaurant.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Bartender;
import com.restaurant.model.OrderedDrink;

public interface OrderedDrinkRepository extends JpaRepository<OrderedDrink, Long> {

	@Query("select o from OrderedDrink as o where o.drink.drinkCard.restaurant.id=?1")
	public ArrayList<OrderedDrink> findAll(Long id);
	
	@Modifying
	@Query("update OrderedDrink od set od.ready=1 where od.id=?1")
	public void updateReady(long id);
	
	public OrderedDrink findOne(Long id);
	
	@Query("select od from OrderedDrink as od where order_id=?1")
	public ArrayList<OrderedDrink> findByOrderId(Long id);
	
	@Modifying
	@Query("update OrderedDrink od set od.quantity=?1 where od.id=?2")
	public void updateQuantity(int quantity, long id);
	
	@Modifying
	@Query("update OrderedDrink set drink_id=?1 where id=?2")
	public void updateDrinkName(long idDrink, long id);

}
