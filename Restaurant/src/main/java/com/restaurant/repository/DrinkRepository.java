package com.restaurant.repository;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
	@Query("select drinks from Drink as drinks where drink_card_id = ?1")
	Set<Drink> findByDrinkCard_Id(Long id);
	
	@Query("select drinks from Drink as drinks where drinks.drinkCard.restaurant.id = ?1")
	Set<Drink> findByRestaurant(Long id);
	
	@Modifying
	@Query("delete from Drink where id = ?1")
	void removeById(Long id);
	
	@Modifying
	@Query("update Drink p set p.name = ?1, p.description = ?2, p.price = ?3 where p.id=?4")
	void modifyById(String name, String description, Double price, Long id);
	
	@Query("select d from Drink as d where d.name=?1 and d.drinkCard.restaurant.id=?2")
	public Drink findByName(String name, Long id);

	@Query("select d from Drink as d where d.id=?1")
	public ArrayList<Drink> findDrinksByOrder(Long id);
}
