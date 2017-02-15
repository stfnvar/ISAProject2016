package com.restaurant.service;

import java.util.ArrayList;
import java.util.Set;

import com.restaurant.model.Drink;
import com.restaurant.model.DrinkCard;
import com.restaurant.model.Meal;
import com.restaurant.model.Menu;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantManager;
import com.restaurant.model.Worker;

import com.restaurant.model.RestaurantSegment;

public interface RestaurantManagerService {
	
	Restaurant getRestaurantProfile(Long id);

	Set<RestaurantSegment> getRestaurantSegments(Long id);
	
	ArrayList<Worker> getWorkers();
	
	Menu getRestaurantMenu(Long id);
	
	Set<Meal> getMenuMeals(Long id);
	
	DrinkCard getRestaurantDrinkCard(Long id);
	
	Set<Drink> getDrinkCardDrinks(Long id);
	
}
